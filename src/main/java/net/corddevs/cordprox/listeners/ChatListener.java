package net.corddevs.cordprox.listeners;

import net.corddevs.cordprox.commands.AdminChat;
import net.corddevs.cordprox.commands.StaffChat;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatListener implements Listener {

    @EventHandler
    public void chatListener(ChatEvent e) {
        ProxiedPlayer player = (ProxiedPlayer) e.getSender();
        if(StaffChat.isStaffchat.contains(player)) {
            e.setCancelled(true);
        } else {
            if(AdminChat.isAdminchat.contains(player)) {
                e.setCancelled(true);
            }
        }
    }
}
