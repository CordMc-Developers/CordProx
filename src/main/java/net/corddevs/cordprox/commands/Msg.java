package net.corddevs.cordprox.commands;

import net.corddevs.cordprox.Main;
import net.corddevs.cordprox.Utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Msg extends Command {
    public Msg() {
        super("msg", "", new String[] { "message" });
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0 || args.length == 1) {
            sender.sendMessage((BaseComponent)new TextComponent(Utils.chat(String.valueOf(String.valueOf(Main.prefix)) + " &7Please use &b&n/msg [player] [message]&7.")));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer)sender;
        ProxiedPlayer player2 = ProxyServer.getInstance().getPlayer(args[0]);
        if (player.getName() == player2.getName()) {
            sender.sendMessage((BaseComponent)new TextComponent(Utils.chat(String.valueOf(String.valueOf(Main.prefix)) + " &7You can't message yourself.")));
            return;
        }
        if (player2 == null) {
            sender.sendMessage((BaseComponent)new TextComponent(Utils.chat(String.valueOf(String.valueOf(Main.prefix)) + " &7Player not found.")));
            return;
        }
        if (ToggleMsg.tmsg.contains(player2)) {
            sender.sendMessage((BaseComponent)new TextComponent(Utils.chat("&cYou cannot message that player!")));
        } else {
            StringBuilder message = new StringBuilder();
            for (int i = 1; i < args.length; i++)
                message.append(String.valueOf(String.valueOf(String.valueOf(args[i]))) + " ");
            if (message.toString().toLowerCase().contains(".net") || message.toString().contains(".com") || message
                    .toString().contains(".org") || message.toString().contains(".club") || message
                    .toString().contains(".gg") || message.toString().contains("fuck") || message
                    .toString().contains("shit") || message.toString().contains("cunt") || message
                    .toString().contains("nigga") || message.toString().contains("moron") || message
                    .toString().contains("bitch") || message.toString().contains("fuck")) {
                sender.sendMessage((BaseComponent)new TextComponent(Utils.chat(String.valueOf(String.valueOf(Main.prefix)) + " &7Please don't use profanity or links to your &7messages.")));
                return;
            }
            player.sendMessage((BaseComponent)new TextComponent(Utils.chat("&8&o(" + Utils.getserver(player2) + "&8&o) &7You &8&7" + player2.getName() + " &8: &f " + message)));
            player2.sendMessage((BaseComponent)new TextComponent(Utils.chat("&8&o(" + Utils.getserver(player) + "&8&o) &7" + player.getName() + " &8  &7You&8: &f " + message)));
            for (ProxiedPlayer staffs : ProxyServer.getInstance().getPlayers()) {
                if (staffs.hasPermission("cord.staff") &&
                        SocialSpy.sp.contains(staffs))
                    staffs.sendMessage((BaseComponent)new TextComponent(Utils.chat("&c&lSPY &7" + player.getName() + " &8 &7 " + player2.getName() + " &8: &f " + message)));
            }
            if (Reply.replyhash.containsKey(player) || Reply.replyhash.containsKey(player2)) {
                Reply.replyhash.remove(player);
                Reply.replyhash.remove(player2);
                Reply.replyhash.put(player, player2);
                Reply.replyhash.put(player2, player);
            }
            Reply.replyhash.put(player, player2);
            Reply.replyhash.put(player2, player);
        }
    }
}