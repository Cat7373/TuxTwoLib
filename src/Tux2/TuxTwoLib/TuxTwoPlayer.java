package Tux2.TuxTwoLib;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_11_R1.CraftServer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.MinecraftServer;
import net.minecraft.server.v1_11_R1.PlayerInteractManager;

public class TuxTwoPlayer {
    /**
     * Returns an offline player that can be manipulated exactly like an online player.
     *
     * @param player The player to retrieve.
     * @return The player object. Null if we can't find the player's data.
     */
    public static Player getOfflinePlayer(final OfflinePlayer player) {
        final Player pplayer = null;

        try {
            // See if the player has data files

            // Find the player folder
            final File playerfolder = new File(Bukkit.getWorlds().get(0).getWorldFolder(), "playerdata");

            // Find player name
            for (final File playerfile : playerfolder.listFiles()) {
                final String filename = playerfile.getName();
                final String playername = filename.substring(0, filename.length() - 4);

                if (playername.trim().equalsIgnoreCase(player.getUniqueId().toString())) {
                    // This player plays on the server!
                    final MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
                    final EntityPlayer entity = new EntityPlayer(server, server.getWorldServer(0), new GameProfile(player.getUniqueId(), player.getName()), new PlayerInteractManager(server.getWorldServer(0)));
                    final Player target = entity == null ? null : (Player) entity.getBukkitEntity();
                    if (target != null) {
                        target.loadData();
                        return target;
                    }
                }
            }
        } catch (final Exception e) {
            return null;
        }

        return pplayer;
    }

    /**
     * Returns an offline player that can be manipulated exactly like an online player.
     *
     * @param player The player to retrieve.
     * @return The player object. Null if we can't find the player's data.
     */
    @Deprecated
    public static Player getOfflinePlayer(final String player) {
        final OfflinePlayer oplayer = Bukkit.getOfflinePlayer(player);
        return TuxTwoPlayer.getOfflinePlayer(oplayer);
    }
}
