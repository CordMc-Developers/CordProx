package net.corddevs.cordprox.commands;

import net.corddevs.cordprox.Utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.List;

public class ToggleMsg extends Command {

    public ToggleMsg() {
        super("togglemsg", "", new String[] { "tmsg" });
    }

    public static List<ProxiedPlayer> tmsg = new ArrayList<>();

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)sender;
            if (tmsg.contains(player)) {
                tmsg.remove(player);
                player.sendMessage((BaseComponent)new TextComponent(Utils.chat("&cMsgToggle disabled!")));
                return;
            }
            tmsg.add(player);
            player.sendMessage((BaseComponent)new TextComponent(Utils.chat("&cMsgToggle enabled!")));
        } else {
            sender.sendMessage(String.valueOf("command only for players!"));
        }
    }
}