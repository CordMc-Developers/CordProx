package net.corddevs.cordprox.commands;

import net.corddevs.cordprox.Main;
import net.corddevs.cordprox.Utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.HashMap;

public class Reply extends Command {
    public Reply() {
        super("r", "", new String[] { "reply" });
    }

    public static HashMap<ProxiedPlayer, ProxiedPlayer> replyhash = new HashMap<>();

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)sender;
            if (replyhash.containsKey(player)) {
                ProxiedPlayer player2 = replyhash.get(player);
                if (replyhash.containsValue(player2)) {
                    if (player2 != null) {
                        if (player2 != null) {
                            if (args.length >= 1) {
                                if (ToggleMsg.tmsg.contains(player2)) {
                                    sender.sendMessage((BaseComponent)new TextComponent(Utils.chat("&cYou cannot message that player!")));
                                    replyhash.remove(player);
                                    replyhash.remove(player2);
                                    return;
                                }
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
                                    if (replyhash.containsKey(player) || replyhash.containsKey(player2)) {
                                        replyhash.remove(player);
                                        replyhash.remove(player2);
                                        replyhash.put(player, player2);
                                        replyhash.put(player2, player);
                                    }
                                    replyhash.put(player, player2);
                                    replyhash.put(player2, player);
                                }
                            } else {
                                player.sendMessage((BaseComponent)new TextComponent(Utils.chat(String.valueOf(Main.prefix) + " &7Please use, /reply [message]")));
                            }
                        } else {
                            player.sendMessage((BaseComponent)new TextComponent(Utils.chat(String.valueOf(Main.prefix) + " &7Player is not online!")));
                        }
                    } else {
                        player.sendMessage((BaseComponent)new TextComponent(Utils.chat(String.valueOf(Main.prefix) + " &7Player is not online!")));
                    }
                } else {
                    player.sendMessage((BaseComponent)new TextComponent(Utils.chat(String.valueOf(Main.prefix) + " &7No one has messaged you!")));
                }
            } else {
                player.sendMessage((BaseComponent)new TextComponent(Utils.chat(String.valueOf(Main.prefix) + " &7No one has messaged you!")));
            }
        } else {
            sender.sendMessage((BaseComponent)new TextComponent(Utils.chat(String.valueOf(Main.prefix) + " &7Player only command!")));
        }
    }
}
