package Tux2.TuxTwoLib;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import Tux2.TuxTwoLib.attributes.Attributes;
import net.minecraft.server.v1_11_R1.NBTTagCompound;

public class SpawnEggs {
    public static ItemStack getSpawnEgg(final EntityType type, final int amount) {
        final net.minecraft.server.v1_11_R1.ItemStack stack = CraftItemStack.asNMSCopy(new ItemStack(Material.MONSTER_EGG, amount));
        String name = "";
        switch (type) {
            case ARMOR_STAND:
                name = "ArmorStand";
                break;
            case ARROW:
                name = "Arrow";
                break;
            case BAT:
                name = "Bat";
                break;
            case BLAZE:
                name = "Blaze";
                break;
            case BOAT:
                name = "Boat";
                break;
            case CAVE_SPIDER:
                name = "CaveSpider";
                break;
            case CHICKEN:
                name = "Chicken";
                break;
            case COW:
                name = "Cow";
                break;
            case CREEPER:
                name = "Creeper";
                break;
            case DROPPED_ITEM:
                name = "Item";
                break;
            case EGG:
                name = "ThrownEgg";
                break;
            case ENDER_CRYSTAL:
                name = "EnderCrystal";
                break;
            case ENDER_DRAGON:
                name = "EnderDragon";
                break;
            case ENDER_PEARL:
                name = "ThrownEnderpearl";
                break;
            case ENDER_SIGNAL:
                name = "EyeOfEnderSignal";
                break;
            case ENDERMAN:
                name = "Enderman";
                break;
            case ENDERMITE:
                name = "Endermite";
                break;
            case EXPERIENCE_ORB:
                name = "XPOrb";
                break;
            case FALLING_BLOCK:
                name = "FallingSand";
                break;
            case FIREBALL:
                name = "Fireball";
                break;
            case FIREWORK:
                name = "FireworksRocketEntity";
                break;
            case GHAST:
                name = "Ghast";
                break;
            case GIANT:
                name = "Giant";
                break;
            case GUARDIAN:
                name = "Guardian";
                break;
            case HORSE:
                name = "EntityHorse";
                break;
            case IRON_GOLEM:
                name = "VillagerGolem";
                break;
            case ITEM_FRAME:
                name = "ItemFrame";
                break;
            case LEASH_HITCH:
                name = "LeashKnot";
                break;
            case MAGMA_CUBE:
                name = "LavaSlime";
                break;
            case MINECART:
                name = "MinecartRideable";
                break;
            case MINECART_CHEST:
                name = "MinecartChest";
                break;
            case MINECART_COMMAND:
                name = "MinecartCommandBlock";
                break;
            case MINECART_FURNACE:
                name = "MinecartFurnace";
                break;
            case MINECART_HOPPER:
                name = "MinecartHopper";
                break;
            case MINECART_MOB_SPAWNER:
                name = "MinecartSpawner";
                break;
            case MINECART_TNT:
                name = "MinecartTNT";
                break;
            case MUSHROOM_COW:
                name = "MushroomCow";
                break;
            case OCELOT:
                name = "Ozelot";
                break;
            case PAINTING:
                name = "Painting";
                break;
            case PIG:
                name = "Pig";
                break;
            case PIG_ZOMBIE:
                name = "PigZombie";
                break;
            case PRIMED_TNT:
                name = "PrimedTnt";
                break;
            case RABBIT:
                name = "Rabbit";
                break;
            case SHEEP:
                name = "Sheep";
                break;
            case SHULKER:
                name = "Shulker";
                break;
            case SILVERFISH:
                name = "Silverfish";
                break;
            case SKELETON:
                name = "Skeleton";
                break;
            case SLIME:
                name = "Slime";
                break;
            case SMALL_FIREBALL:
                name = "SmallFireball";
                break;
            case SNOWBALL:
                name = "Snowball";
                break;
            case SNOWMAN:
                name = "SnowMan";
                break;
            case SPIDER:
                name = "Spider";
                break;
            case SPLASH_POTION:
                name = "ThrownPotion";
                break;
            case SQUID:
                name = "Squid";
                break;
            case THROWN_EXP_BOTTLE:
                name = "ThrownExpBottle";
                break;
            case VILLAGER:
                name = "Villager";
                break;
            case WITCH:
                name = "Witch";
                break;
            case WITHER:
                name = "WitherBoss";
                break;
            case WITHER_SKULL:
                name = "WitherSkull";
                break;
            case WOLF:
                name = "Wolf";
                break;
            case ZOMBIE:
                name = "Zombie";
                break;
            default:
                break;
        }
        NBTTagCompound tag = stack.getTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        final NBTTagCompound entitytag = new NBTTagCompound();
        entitytag.setString("id", name);
        tag.set("EntityTag", entitytag);
        stack.setTag(tag);
        return CraftItemStack.asCraftMirror(stack);
    }

    public static ItemStack getSpawnEgg(final String name, final int amount) {
        final net.minecraft.server.v1_11_R1.ItemStack stack = CraftItemStack.asNMSCopy(new ItemStack(Material.MONSTER_EGG, amount));
        NBTTagCompound tag = stack.getTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        final NBTTagCompound entitytag = new NBTTagCompound();
        entitytag.setString("id", name);
        tag.set("EntityTag", entitytag);
        stack.setTag(tag);
        return CraftItemStack.asCraftMirror(stack);
    }

    public static EntityType getSpawnEggType(final ItemStack is) {
        EntityType type = null;
        final net.minecraft.server.v1_11_R1.ItemStack mcis = Attributes.getMinecraftItemStack(is);
        if (mcis == null) {
            return null;
        }
        final NBTTagCompound tag = mcis.getTag();
        if (tag == null) {
            return null;
        }
        final NBTTagCompound entitytag = tag.getCompound("EntityTag");
        if (entitytag == null) {
            return null;
        }
        final String name = entitytag.getString("id");
        switch (name) {
            case "ArmorStand":
                type = EntityType.ARMOR_STAND;
                break;
            case "Arrow":
                type = EntityType.ARROW;
                break;
            case "Bat":
                type = EntityType.BAT;
                break;
            case "Blaze":
                type = EntityType.BLAZE;
                break;
            case "Boat":
                type = EntityType.BOAT;
                break;
            case "CaveSpider":
                type = EntityType.CAVE_SPIDER;
                break;
            case "Chicken":
                type = EntityType.CHICKEN;
                break;
            case "Cow":
                type = EntityType.COW;
                break;
            case "Creeper":
                type = EntityType.CREEPER;
                break;
            case "Item":
                type = EntityType.DROPPED_ITEM;
                break;
            case "ThrownEgg":
                type = EntityType.EGG;
                break;
            case "EnderCrystal":
                type = EntityType.ENDER_CRYSTAL;
                break;
            case "EnderDragon":
                type = EntityType.ENDER_DRAGON;
                break;
            case "ThrownEnderpearl":
                type = EntityType.ENDER_PEARL;
                break;
            case "EyeOfEnderSignal":
                type = EntityType.ENDER_SIGNAL;
                break;
            case "Enderman":
                type = EntityType.ENDERMAN;
                break;
            case "Endermite":
                type = EntityType.ENDERMITE;
                break;
            case "XPOrb":
                type = EntityType.EXPERIENCE_ORB;
                break;
            case "FallingSand":
                type = EntityType.FALLING_BLOCK;
                break;
            case "Fireball":
                type = EntityType.FIREBALL;
                break;
            case "FireworksRocketEntity":
                type = EntityType.FIREWORK;
                break;
            case "Ghast":
                type = EntityType.GHAST;
                break;
            case "Giant":
                type = EntityType.GIANT;
                break;
            case "Guardian":
                type = EntityType.GUARDIAN;
                break;
            case "EntityHorse":
                type = EntityType.HORSE;
                break;
            case "VillagerGolem":
                type = EntityType.IRON_GOLEM;
                break;
            case "ItemFrame":
                type = EntityType.ITEM_FRAME;
                break;
            case "LeashKnot":
                type = EntityType.LEASH_HITCH;
                break;
            case "LavaSlime":
                type = EntityType.MAGMA_CUBE;
                break;
            case "MinecartRideable":
                type = EntityType.MINECART;
                break;
            case "MinecartChest":
                type = EntityType.MINECART_CHEST;
                break;
            case "MinecartCommandBlock":
                type = EntityType.MINECART_COMMAND;
                break;
            case "MinecartFurnace":
                type = EntityType.MINECART_FURNACE;
                break;
            case "MinecartHopper":
                type = EntityType.MINECART_HOPPER;
                break;
            case "MinecartSpawner":
                type = EntityType.MINECART_MOB_SPAWNER;
                break;
            case "MinecartTNT":
                type = EntityType.MINECART_TNT;
                break;
            case "MushroomCow":
                type = EntityType.MUSHROOM_COW;
                break;
            case "Ozelot":
                type = EntityType.OCELOT;
                break;
            case "Painting":
                type = EntityType.PAINTING;
                break;
            case "Pig":
                type = EntityType.PIG;
                break;
            case "PigZombie":
                type = EntityType.PIG_ZOMBIE;
                break;
            case "PrimedTnt":
                type = EntityType.PRIMED_TNT;
                break;
            case "Rabbit":
                type = EntityType.RABBIT;
                break;
            case "Sheep":
                type = EntityType.SHEEP;
                break;
            case "Shulker":
                type = EntityType.SHULKER;
                break;
            case "Silverfish":
                type = EntityType.SILVERFISH;
                break;
            case "Skeleton":
                type = EntityType.SKELETON;
                break;
            case "Slime":
                type = EntityType.SLIME;
                break;
            case "SmallFireball":
                type = EntityType.SMALL_FIREBALL;
                break;
            case "Snowball":
                type = EntityType.SNOWBALL;
                break;
            case "SnowMan":
                type = EntityType.SNOWMAN;
                break;
            case "Spider":
                type = EntityType.SPIDER;
                break;
            case "ThrownPotion":
                type = EntityType.SPLASH_POTION;
                break;
            case "Squid":
                type = EntityType.SQUID;
                break;
            case "ThrownExpBottle":
                type = EntityType.THROWN_EXP_BOTTLE;
                break;
            case "Villager":
                type = EntityType.VILLAGER;
                break;
            case "Witch":
                type = EntityType.WITCH;
                break;
            case "WitherBoss":
                type = EntityType.WITHER;
                break;
            case "WitherSkull":
                type = EntityType.WITHER_SKULL;
                break;
            case "Wolf":
                type = EntityType.WOLF;
                break;
            case "Zombie":
                type = EntityType.ZOMBIE;
                break;
            default:
                break;
        }
        return type;
    }
}
