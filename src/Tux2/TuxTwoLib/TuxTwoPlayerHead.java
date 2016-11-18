package Tux2.TuxTwoLib;

import java.util.UUID;

import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import Tux2.TuxTwoLib.attributes.Attributes;
import net.minecraft.server.v1_11_R1.NBTTagCompound;
import net.minecraft.server.v1_11_R1.NBTTagList;

public class TuxTwoPlayerHead {
    public static ItemStack getHead(final ItemStack is, final NMSHeadData head) {
        return TuxTwoPlayerHead.getHead(is, head.getId(), head.getTexture());
    }

    public static ItemStack getHead(final ItemStack is, final UUID id, final String texture) {
        final net.minecraft.server.v1_11_R1.ItemStack stack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound tag = stack.getTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        final NBTTagCompound skullowner = new NBTTagCompound();
        skullowner.setString("Id", id.toString());
        final NBTTagCompound properties = new NBTTagCompound();
        final NBTTagList textures = new NBTTagList();
        final NBTTagCompound ntexture = new NBTTagCompound();
        ntexture.setString("Value", texture);
        textures.add(ntexture);
        properties.set("textures", textures);
        skullowner.set("Properties", properties);
        tag.set("SkullOwner", skullowner);
        stack.setTag(tag);
        return CraftItemStack.asCraftMirror(stack);
    }

    public static NMSHeadData getHeadData(final ItemStack is) {
        try {
            final net.minecraft.server.v1_11_R1.ItemStack mcis = Attributes.getMinecraftItemStack(is);
            if (mcis == null) {
                return null;
            }
            final NBTTagCompound tag = mcis.getTag();
            if (tag == null) {
                return null;
            }
            final NBTTagCompound skullowner = tag.getCompound("SkullOwner");
            if (skullowner == null) {
                return null;
            }
            final UUID id = UUID.fromString(skullowner.getString("Id"));
            final NBTTagCompound properties = skullowner.getCompound("Properties");
            if (properties != null) {
                final NBTTagList textures = properties.getList("textures", 10);
                if (textures != null && textures.size() > 0) {
                    final NBTTagCompound ntexture = textures.get(0);
                    if (ntexture != null) {
                        final String texture = ntexture.getString("Value");
                        return new NMSHeadData(id, texture);
                    }
                }
            }
            return null;
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
