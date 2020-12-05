package net.corddevs.cordprox.listeners;

import net.corddevs.cordprox.Utils.Utils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class DisconnectListener implements Listener {


    @EventHandler
    public void PlayerDisconnectEvent(PlayerDisconnectEvent e) {
        for (ProxiedPlayer pl : ProxyServer.getInstance().getPlayers()) {
            if (pl.hasPermission("Cord.staff")) {
                pl.sendMessage((BaseComponent)new TextComponent(
                        Utils.chat("&3&lCORD&B&lMC &7" + e.getPlayer().getDisplayName() + " &7has &c&ndisconnected")));
                continue;
            }
            return;
        }
    }
}
