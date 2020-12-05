package net.corddevs.cordprox.listeners;

import net.corddevs.cordprox.Utils.Utils;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Arrays;
import java.util.List;

public class ProxyPingListener implements Listener {

    private final List<String> lines = Arrays.asList(Utils.chat("&7"),
            Utils.chat("&7Store: &bstore.cordmc.net"), Utils.chat("&7Website: &bwww.cordmc.net"),
            Utils.chat("&7Discord: &bdiscord.cordmc.net"), Utils.chat("&7"));

    String motd = "&3&m&l---&b&m&l---&f&m&l---&f &b&lCORD&3&lMC &7[1.8-1.16] &f&m&l---&b&m&l---&3&m&l---\n";

    String motdl2 = "&f     &3store.cordmc.net &8/ &b&ndiscord.cordmc.net";


    @EventHandler
    public void onProxyPing(ProxyPingEvent e) {
        e.getResponse().setDescription(Utils.chat(String.valueOf(String.valueOf(this.motd)) + this.motdl2));
        ServerPing.PlayerInfo[] sample = new ServerPing.PlayerInfo[this.lines.size()];
        for (int i = 0; i < sample.length; i++)
            sample[i] = new ServerPing.PlayerInfo(this.lines.get(i), "");
        e.getResponse().getPlayers().setSample(sample);
    }
}
