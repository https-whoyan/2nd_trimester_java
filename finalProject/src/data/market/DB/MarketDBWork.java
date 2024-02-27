package data.market.DB;

import data.market.schemas.Market;
import domain.SQLConnection;

import java.awt.desktop.SystemEventListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class MarketDBWork {
    public static void insertMarket(String marketName) {
        try {
            Statement statement = SQLConnection.getStatement();
            String query = "INSERT INTO markets (name) VALUES ('" + marketName + "')";
            statement.executeQuery(query);
        } catch (Exception e) {
            //...
        }
    }

    public static Market getCurrMarket(String marketName) {
        Statement statement = SQLConnection.getStatement();
        Market currMarket = new Market();
        try {
            String query = "SELECT * " +
                    "FROM markets " +
                    "WHERE name = '" + marketName + "'";
            ResultSet table = statement.executeQuery(query);
            table.beforeFirst();

            while (table.next()) {
                currMarket = new Market(
                        table.getInt(1),
                        table.getString(2)
                );
            }
        } catch (Exception e) {
            //...
        }
        return currMarket;
    }

    public static Market getCurrMarket(int marketId) {
        Statement statement = SQLConnection.getStatement();
        Market currMarket = new Market();
        try {
            String query = "SELECT * " +
                    "FROM markets " +
                    "WHERE id = " + marketId;
            ResultSet table = statement.executeQuery(query);
            table.beforeFirst();

            while (table.next()) {
                currMarket = new Market(
                        table.getInt(1),
                        table.getString(2)
                );
            }
        } catch (Exception e) {
            //...
        }
        return currMarket;
    }
}
