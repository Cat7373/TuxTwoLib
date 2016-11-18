package Tux2.TuxTwoLib;

import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * TuxTwoLib for Bukkit
 *
 * @author Tux2
 */
public class TuxTwoLib extends JavaPlugin {
    public static final String ttlbuild = "7";
    public static final String targetMCversion = "1.11";
    public static final String targetNMS = "v1_11_R1";

    private String currentMCversion;

    @Override
    public void onEnable() {
        final Pattern bukkitversion = Pattern.compile("(\\d+\\.\\d+\\.?\\d*)-R(\\d\\.\\d)");
        final String ver = this.getServer().getBukkitVersion();
        final Matcher bukkitmatch = bukkitversion.matcher(ver);

        if (bukkitmatch.find()) {
            this.currentMCversion = bukkitmatch.group(1);
            if (!this.currentMCversion.equals(TuxTwoLib.targetMCversion) || !TuxTwoLib.targetNMS.equals(this.getNMSVersion())) {
                this.getLogger().log(Level.SEVERE, "Your version of TuxTwoLib is incompatible with this version of Craftbukkit! Update immediately!");
            } else {
                this.getServer().getPluginManager().registerEvents(new TuxTwoListener(), this);
            }
        } else {
            this.getLogger().warning("Unable to verify minecraft version! MC version reported: " + ver);
        }
    }

    private String getNMSVersion() {
        final String name = this.getServer().getClass().getPackage().getName();
        final String mcVersion = name.substring(name.lastIndexOf('.') + 1);
        return mcVersion;
    }
}
