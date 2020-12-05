package net.corddevs.cordprox;

import net.corddevs.cordprox.Utils.Utils;
import net.corddevs.cordprox.commands.*;
import net.corddevs.cordprox.listeners.ChatListener;
import net.corddevs.cordprox.listeners.DisconnectListener;
import net.corddevs.cordprox.listeners.JoinListener;
import net.corddevs.cordprox.listeners.ProxyPingListener;
import net.md_5.bungee.api.plugin.Plugin;

public final class Main extends Plugin {

    public static String prefix = Utils.chat("&3&lCORD&B&LMC &7");
    public static Main instance;

    @Override
    public void onEnable() {
        getCommands();
        getListeners();
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }

    public void getCommands() {
        getProxy().getPluginManager().registerCommand(this, new ping());
        getProxy().getPluginManager().registerCommand(this, new StaffChat());
        getProxy().getPluginManager().registerCommand(this, new AdminChat());
        getProxy().getPluginManager().registerCommand(this, new Msg());
        getProxy().getPluginManager().registerCommand(this, new Reply());
        getProxy().getPluginManager().registerCommand(this, new ToggleMsg());
        getProxy().getPluginManager().registerCommand(this, new Hub());
        getProxy().getPluginManager().registerCommand(this, new test());
        getProxy().getPluginManager().registerCommand(this, new SocialSpy());
    }


    public void getListeners() {
        getProxy().getPluginManager().registerListener(this, new JoinListener());
        getProxy().getPluginManager().registerListener(this, new DisconnectListener());
        getProxy().getPluginManager().registerListener(this, new ProxyPingListener());
        getProxy().getPluginManager().registerListener(this, new ChatListener());
    }
}
