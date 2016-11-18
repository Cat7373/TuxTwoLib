package Tux2.TuxTwoLib.attributes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_11_R1.util.CraftMagicNumbers;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_11_R1.Item;
import net.minecraft.server.v1_11_R1.NBTTagCompound;
import net.minecraft.server.v1_11_R1.NBTTagList;

/**
 * Class to apply {@link Attribute}s or {@link Collection}s containing Attributes to ItemStacks.
 *
 * <b>Example code:</b>
 *
 * <pre>
 * // Instantiate some Attributes
 * Attribute a1 = new Attribute(AttributeType.ATTACK_DAMAGE, Operation.ADD_NUMBER, 20.0), a2 = new Attribute().setType(AttributeType.KNOCKBACK_RESISTANCE).setOperation(Operation.ADD_PERCENTAGE).setAmount(0.75);
 *
 * // Create an ItemStack with these Attributes
 * ItemStack is = new ItemStack(Material.NETHER_STAR);
 *
 * // Apply the attributes to said ItemStack
 * List<Attribute> list = new ArrayList<>();
 * list.add(a1);
 * list.add(a2);
 * Attributes.apply(is, list, true);
 * </pre>
 *
 * @author Ruud Verbeek
 * @see Attribute
 */
public class Attributes {
    /**
     * Applies the given attribute to the given ItemStack
     *
     * @param original The original ItemStack to apply the attribute to
     * @param attribute The Attribute to apply.
     * @param replace Whether or not to remove the attributes that were already on the ItemStack
     * @return A new ItemStack containing the attribute
     */
    public static ItemStack apply(final ItemStack original, final Attribute attribute, final boolean replace) {
        if (original instanceof CraftItemStack) {
            final net.minecraft.server.v1_11_R1.ItemStack stack = CraftItemStack.asNMSCopy(original);
            final NBTTagCompound tag = stack.getTag();
            NBTTagList list;
            if (replace) {
                list = new NBTTagList();
            } else {
                list = tag.getList("AttributeModifiers", 10);
            }
            list.add(attribute.write());
            tag.set("AttributeModifiers", list);
            stack.setTag(tag);
            return original;
        } else {
            return original;
        }
    }

    /**
     * Applies the given attributes to the given ItemStack
     *
     * @param original The original ItemStack to apply the attribute to
     * @param attributes The Attributes to apply.
     * @param replace Whether or not to remove the attributes that were already on the ItemStack
     * @return A new ItemStack containing the attributes
     */
    public static ItemStack apply(final ItemStack original, final Collection<? extends Attribute> attributes, final boolean replace) {
        if (attributes.size() == 0 && !replace) {
            return original;
        }

        final net.minecraft.server.v1_11_R1.ItemStack stack = Attributes.getMinecraftItemStack(original);
        NBTTagCompound tag = stack.getTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        NBTTagList list;
        if (replace) {
            list = new NBTTagList();
        } else {
            list = tag.getList("AttributeModifiers", 10);
        }
        for (final Attribute attribute : attributes) {
            if (attribute != null) {
                list.add(attribute.write());
            }
        }
        tag.set("AttributeModifiers", list);
        stack.setTag(tag);
        return CraftItemStack.asCraftMirror(stack);
    }

    /**
     * Returns a {@link List} containing the {@link Attribute}s on the given {@link ItemStack}.
     *
     * @param is the ItemStack to take the Attributes from
     * @return a List containing the Attributes, or an empty list if there weren't any Attributes on
     *         the ItemStack or an error occurred.
     */
    public static ArrayList<Attribute> fromStack(final ItemStack is) {
        try {
            final net.minecraft.server.v1_11_R1.ItemStack mcis = Attributes.getMinecraftItemStack(is);
            if (mcis == null) {
                return new ArrayList<>();
            }
            final NBTTagCompound tag = mcis.getTag();
            if (tag == null) {
                return new ArrayList<>();
            }
            NBTTagList attributes;
            if ((attributes = tag.getList("AttributeModifiers", 10)) == null) {
                return new ArrayList<>();
            }
            final ArrayList<Attribute> list = new ArrayList<>();
            for (int i = 0; i < attributes.size(); ++i) {
                final NBTTagCompound attribute = attributes.get(i);
                list.add(Attribute.fromTag(attribute));
            }
            return list;
        } catch (final Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static net.minecraft.server.v1_11_R1.ItemStack getMinecraftItemStack(final ItemStack is) {
        if (!(is instanceof CraftItemStack)) {
            final Item item = CraftMagicNumbers.getItem(is.getType());

            if (item == null) {
                return null;
            }

            final net.minecraft.server.v1_11_R1.ItemStack stack = new net.minecraft.server.v1_11_R1.ItemStack(item, is.getAmount(), is.getDurability());

            final CraftItemStack cis = CraftItemStack.asCraftMirror(stack);
            try {
                final Field handle = CraftItemStack.class.getDeclaredField("handle");
                handle.setAccessible(true);
                final net.minecraft.server.v1_11_R1.ItemStack mis = (net.minecraft.server.v1_11_R1.ItemStack) handle.get(cis);
                if (is.hasItemMeta()) {
                    CraftItemStack.setItemMeta(mis, is.getItemMeta());
                }
                return mis;
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
                e1.printStackTrace();
            }
        } else {
            final CraftItemStack cis = (CraftItemStack) is;
            try {
                final Field handle = CraftItemStack.class.getDeclaredField("handle");
                handle.setAccessible(true);
                final net.minecraft.server.v1_11_R1.ItemStack mis = (net.minecraft.server.v1_11_R1.ItemStack) handle.get(cis);
                return mis;
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
}
