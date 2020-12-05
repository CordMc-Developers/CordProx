package net.corddevs.cordprox.commands;

import net.corddevs.cordprox.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.List;

import static net.corddevs.cordprox.Utils.Utils.chat;
import static net.corddevs.cordprox.Main.prefix;
import static net.corddevs.cordprox.Utils.Utils.getPrefix;
import static net.corddevs.cordprox.Utils.Utils.getserver;

public class AdminChat extends Command {

    public static List<ProxiedPlayer> isAdminchat = new ArrayList<>();


    public AdminChat() {
        super("adminchat", "cord.admin", "ac");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if(args.length == 0) {
                if(!isAdminchat.contains(p)) {
                    isAdminchat.add(p);
                    p.sendMessage((BaseComponent) new TextComponent(prefix + " StaffChat &a&n enabled!"));
                } else {
                    isAdminchat.remove(p);
                    p.sendMessage((BaseComponent) new TextComponent(prefix + " StaffChat &c&n disabled!"));
                    return;
                }
            } else {
                StringBuilder message = new StringBuilder();
                for (int i = 0; i < args.length; i++)
                    message.append(String.valueOf(String.valueOf(String.valueOf(args[i]))) + " ");
                for(ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                    staff.sendMessage((BaseComponent) new TextComponent("\n&7&o(" + getserver(p) + ") &a&lAC &f" +
                            getPrefix(p.getUniqueId()) + " &f" + p.getName() + "&8: &f" + message + "\n&7"));
                }
            }
        }
    }

    @Override
    protected void setPermissionMessage(String permissionMessage) {
        super.setPermissionMessage(chat(Main.prefix + " You don't have enough permissions!"));
    }
}
