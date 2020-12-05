package net.corddevs.cordprox.commands;

import net.corddevs.cordprox.Main;
import net.corddevs.cordprox.Utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ping extends Command {

    public ping() {
        super("ping", "", "");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            int ping = p.getPing();
            p.sendMessage((BaseComponent) new TextComponent(Utils.chat(Main.prefix + " Your ping is &b&n" + ping)));
        }
    }
}
