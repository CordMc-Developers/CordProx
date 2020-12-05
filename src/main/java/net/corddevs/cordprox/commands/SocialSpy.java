package net.corddevs.cordprox.commands;

import net.corddevs.cordprox.Main;
import net.corddevs.cordprox.Utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SocialSpy extends Command{
    public static HashMap<ProxiedPlayer, ProxiedPlayer> replyhash = new HashMap<>();

    public static List<ProxiedPlayer> sp = new ArrayList<>();

    public SocialSpy() {
        super("ss", "cord.staff", "socialspy");
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)sender;
            if (sp.contains(player)) {
                sp.remove(player);
                player.sendMessage((BaseComponent)new TextComponent(Utils.chat(String.valueOf(Main.prefix) + " &7SocialSpy off")));
                return;
            }
            sp.add(player);
            player.sendMessage((BaseComponent)new TextComponent(Utils.chat(String.valueOf(Main.prefix) + " &7SocialSpy enabled")));
        } else {
            sender.sendMessage((BaseComponent)new TextComponent(Utils.chat(String.valueOf(Main.prefix) + " &7Player only command!")));
        }
    }

}
