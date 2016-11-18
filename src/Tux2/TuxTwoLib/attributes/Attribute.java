package Tux2.TuxTwoLib.attributes;

import java.util.UUID;

import org.bukkit.inventory.ItemStack;

import com.google.common.base.Preconditions;

import net.minecraft.server.v1_11_R1.NBTTagCompound;

/**
 * Represents a single Attribute that can be applied to ItemStacks.
 *
 * @author Ruud Verbeek
 * @see AttributeList, AttributeType, Operation
 */
public class Attribute {
    String type;
    int operation = -1;
    double amount = 0;
    UUID uuid;

    /**
     * Constructs an Attribute with the given {@link AttributeType}, {@link Operation}, amount and
     * {@link UUID}.
     *
     * @param type The type of this Attribute.
     * @param operation The operation this Attribute uses to apply its amount to the final value.
     * @param amount The amount of this Attribute.
     * @param uuid This Attribute's {@link UUID}. Must be unique.
     */
    public Attribute(final String type, final int operation, final double amount, final UUID uuid) {
        this.type = type;
        this.operation = operation;
        this.amount = amount;
        this.uuid = uuid;
    }

    /**
     * Constructs an Attribute using the given {@link AttributeType}, amount and {@link UUID}.
     * {@link Operation#ADD_NUMBER} will be used as {@link Operation}.
     *
     * @param type The type of this Attribute.
     * @param amount The amount of this Attribute.
     * @param uuid This Attribute's {@link UUID}. Must be unique.
     */
    public Attribute(final String type, final double amount, final UUID uuid) {
        this(type, Operation.ADD_NUMBER.id, amount, uuid);
    }

    /**
     * Constructs an Attribute using the given {@link AttributeType}, {@link Operation} and amount.
     * A random {@link UUID} will be used.
     *
     * @param type The type of this Attribute.
     * @param operation The operation this Attribute uses to apply its amount to the final value.
     * @param amount The amount of this Attribute
     */
    public Attribute(final String type, final int operation, final double amount) {
        this(type, operation, amount, UUID.randomUUID());
    }

    /**
     * Constructs an Attribute using the give {@link AttributeType} and amount.
     * {@link Operation#ADD_NUMBER} will be used as {@link Operation} and a random {@link UUID} will
     * be used.
     *
     * @param type The type of this Attribute.
     * @param amount The amount of this Attribute.
     */
    public Attribute(final String type, final double amount) {
        this(type, Operation.ADD_NUMBER.id, amount, UUID.randomUUID());
    }

    /**
     * Constructs an Attribute with {@link Operation#ADD_NUMBER} as {@link Operation}, 0.0 as amount
     * and a random {@link UUID}. The {@link AttributeType} must still be set using
     * {@link #setType}.
     */
    public Attribute() {
        this(null, Operation.ADD_NUMBER.id, 0.0, UUID.randomUUID());
    }

    /**
     * Sets the {@link AttributeType} of this Attribute.
     *
     * @param type The {@link AttributeType}
     * @return This Attribute
     */
    public Attribute setType(final String type) {
        this.type = type;
        return this;
    }

    /**
     * Sets the {@link Operation} of this Attribute.
     *
     * @param operation The {@link Operation}
     * @return This Attribute
     */
    public Attribute setOperation(final Operation operation) {
        this.operation = operation.id;
        return this;
    }

    /**
     * Sets the {@link Operation} of this Attribute.
     *
     * @param operation The {@link Operation}
     * @return This Attribute
     */
    public Attribute setOperation(final int operation) {
        this.operation = operation;
        return this;
    }

    /**
     * Sets the amount of this Attribute.
     *
     * @param amount The amount
     * @return This Attribute
     */
    public Attribute setAmount(final double amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Sets the {@link UUID} of this Attribute.
     *
     * @param uuid The {@link UUID}
     * @return This Attribute
     */
    public Attribute setUUID(final UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * Returns this Attribute's {@link AttributeType}
     *
     * @return The {@link AttributeType}
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns this Attribute's {@link Operation}
     *
     * @return The {@link Operation}
     */
    public int getOperation() {
        return this.operation;
    }

    /**
     * Returns this Attribute's amount
     *
     * @return The amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Returns this Attribute's {@link UUID}
     *
     * @return The {@link UUID}
     */
    public UUID getUUID() {
        return this.uuid;
    }

    NBTTagCompound write() {
        Preconditions.checkNotNull(this.type, "Type cannot be null.");
        if (this.operation == -1) {
            this.operation = Operation.ADD_NUMBER.id;
        }
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }

        final NBTTagCompound tag = new NBTTagCompound();
        tag.setString("AttributeName", this.type);
        tag.setString("Name", this.type);
        tag.setInt("Operation", this.operation);
        tag.setDouble("Amount", this.amount);
        tag.setLong("UUIDMost", this.uuid.getMostSignificantBits());
        tag.setLong("UUIDLeast", this.uuid.getLeastSignificantBits());
        return tag;
    }

    /**
     * Returns whether or not this attribute is equal to o.
     */
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Attribute)) {
            return false;
        }
        final Attribute a = (Attribute) o;
        return this.uuid.equals(a.uuid) || this.type == a.type && this.operation == a.operation && this.amount == a.amount;
    }

    static Attribute fromTag(final NBTTagCompound c) throws IllegalArgumentException {
        final Attribute a = new Attribute();
        if (c.hasKey("AttributeName")) {
            a.setType(c.getString("AttributeName"));
        } else {
            throw new IllegalArgumentException("No AttributeName specified.");
        }
        if (c.hasKey("Operation")) {
            a.setOperation(Operation.fromID(c.getInt("Operation")));
        } else {
            throw new IllegalArgumentException("No Operation specified.");
        }
        if (c.hasKey("Amount")) {
            a.setAmount(c.getDouble("Amount"));
        } else {
            throw new IllegalArgumentException("No Amount specified.");
        }
        if (c.hasKey("UUIDMost") && c.hasKey("UUIDLeast")) {
            a.setUUID(new UUID(c.getLong("UUIDLeast"), c.getLong("UUIDMost")));
        } else {
            a.setUUID(UUID.randomUUID());
        }
        return a;
    }

    /**
     * Shortcut to {@link Attributes#apply}.
     *
     * @param is ItemStack to apply this attribute to
     * @param replace Whether or not to replace existing attributes on the ItemStack
     * @return the new ItemStack containing this Attribute
     */
    public ItemStack apply(final ItemStack is, final boolean replace) {
        return Attributes.apply(is, this, replace);
    }
}
