package net.corddevs.cordprox.sql;

import net.corddevs.cordprox.Main;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SQLGetter {
    private Main plugin;

    public SQLGetter(Main plugin) {
        this.plugin = plugin;
    }

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = Main.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS user_data (NAME VARCHAR(100),UUID VARCHAR(100),IP VARCHAR(100),LASTSEEN VARCHAR(100),PLAYTIME INT(100),PRIMARY KEY (Name))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(UUID uuid, ProxiedPlayer player) {
        try {
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            if (!playerExist(uuid)) {
                PreparedStatement ps2 = Main.mySQL.getConnection().prepareStatement("INSERT IGNORE INTO user_data (NAME,UUID,LASTSEEN,PLAYTIME,) VALUES (?,?,?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.setString(3, format.format(now));
                ps2.setInt(4, 0);
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean playerExist(UUID uuid) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement("SELECT * FROM user_data WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean playerNameExist(String name) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement("SELECT * FROM user_data WHERE NAME=?");
            ps.setString(1, name);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void playerUpdate(UUID uuid, ProxiedPlayer player) {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement("UPDATE user_data SET LASTSEEN=?, IP=? WHERE UUID=?");
            ps.setString(1, format.format(now));
            ps.setString(2, Objects.requireNonNull(player.getAddress()).getAddress().getHostAddress());
            ps.setString(3, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String playerLastSeenGetter(UUID uuid) {
        String s = "NaN";

        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement("SELECT * FROM user_data WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                s = String.valueOf(results.getString("LASTSEEN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public Long TotalTimePlayedGetter(UUID uuid) {
        long l = 0;

        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement("SELECT * FROM user_data WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                l = results.getLong("PLAYTIME");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public void TotalTimePlayedSetter(UUID uuid, long playtime) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement("UPDATE user_data SET PLAYTIME=? WHERE UUID=?");
            ps.setLong(1, playtime);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void TotalTimePlayedUpdate(UUID uuid) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String loggedOutAt = formatter.format(new Date());

        try {
            Date d1 = formatter.parse(playerLastSeenGetter(uuid));
            Date d2 = formatter.parse(loggedOutAt);

            long diff = d2.getTime() - d1.getTime();                      // in milliseconds
            long diffSeconds = TimeUnit.MILLISECONDS.toSeconds(diff);     // in seconds

            TotalTimePlayedSetter(uuid, TotalTimePlayedGetter(uuid) + diffSeconds);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
