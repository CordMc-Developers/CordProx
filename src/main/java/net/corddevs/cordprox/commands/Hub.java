package net.corddevs.cordprox.commands;

import net.corddevs.cordprox.Main;
import net.corddevs.cordprox.Utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Hub extends Command {
    public Hub() {
        super("hub", "", new String[] { "lobby" });
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(Utils.chat(String.valueOf(String.valueOf(Main.prefix)) + " &cYou must be a player to move servers."));
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer)sender;
        if (p.getServer().getInfo().getName().equalsIgnoreCase("Hub")) {
            p.sendMessage(Utils.chat(String.valueOf(String.valueOf(Main.prefix)) + " &7You are already connected to a Hub!"));
            return;
        }
        p.connect((ServerInfo)ProxyServer.getInstance().getServers().get("Hub"));
        p.sendMessage(Utils.chat(String.valueOf(String.valueOf(Main.prefix)) + " &7You were moved to a Hub."));
    }
}
