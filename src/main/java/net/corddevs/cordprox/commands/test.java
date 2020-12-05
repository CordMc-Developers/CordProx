package net.corddevs.cordprox.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static net.corddevs.cordprox.Main.prefix;
import static net.corddevs.cordprox.Utils.Utils.chat;
import static net.corddevs.cordprox.Utils.Utils.getPrefix;

public class test extends Command {

    public test() {
        super("test", "*", "t");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage((BaseComponent) new TextComponent(chat(prefix +  " This is a player only command!")));
            return;
        }  else {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            p.sendMessage((BaseComponent) new TextComponent(chat(prefix + getPrefix(p.getUniqueId()) + p.getName() + " Just ran the test command")));
            return;
        }
    }
}