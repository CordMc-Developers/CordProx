package net.corddevs.cordprox.sql;

import net.corddevs.cordprox.Main;
import net.corddevs.cordprox.Utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private final String host = "";
    private final int port = 3306;
    private final String database = "";
    private final String username = "";
    private final String password = "";


    private Connection connection;

    public boolean isConnected() {
        return (connection != null);
    }

    public void connect() throws SQLException {
        if (!isConnected()) {
            Main.getInstance().getProxy().getConsole().sendMessage(Utils.chat(Main.prefix + " CONNECTING TO MYSQL DATABASE PLEASE WAIT...."));
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", username, password);
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
