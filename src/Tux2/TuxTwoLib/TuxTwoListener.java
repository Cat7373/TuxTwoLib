package Tux2.TuxTwoLib;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_11_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftInventoryPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class TuxTwoListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLogin(final PlayerLoginEvent event) {
        final Player player = event.getPlayer();
        if (player instanceof CraftHumanEntity) {
            final CraftHumanEntity cplayer = (CraftHumanEntity) player;
            try {
                final Field f = CraftHumanEntity.class.getDeclaredField("inventory");
                f.setAccessible(true);
                f.set(cplayer, new TuxTwoInventoryPlayer((CraftInventoryPlayer) f.get(cplayer)));
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
                e1.printStackTrace();
            }
        }
    }

}
