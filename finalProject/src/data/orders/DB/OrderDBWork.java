package data.orders.DB;

import data.items.schemas.Item;
import data.orders.schemas.Order;
import data.users.Utils;
import domain.SQLConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDBWork {
    public static void insertOrder(Order newOrder) {
        try {
            Statement statement = SQLConnection.getStatement();
            String query;


            query = "INSERT INTO orders (order_nick, item_id, market_id, count) VALUES ('" +
                    newOrder.getOrder_id() + "', " + newOrder.getItem_id() + ", " +
                    newOrder.getMarket_id() + ", " + newOrder.getCount() + ")";

            statement.executeQuery(query);
        } catch (Exception e) {
            //...
        }
    }

    public static ArrayList<Order> getAllOrders(String currOrderer) {
        Statement statement = SQLConnection.getStatement();
        ArrayList<Order> allOrders = new ArrayList<>();
        try {
            String query =
                    "SELECT * " +
                    "FROM orders " +
                    "WHERE order_nick = '" + currOrderer + "'";
            ResultSet table = statement.executeQuery(query);
            table.beforeFirst();

            Order currOrder;

            while (table.next()) {
                currOrder = new Order(
                        table.getInt(1),
                        table.getString(2),
                        table.getInt(3),
                        table.getInt(4),
                        table.getInt(5)
                );
                allOrders.add(currOrder);
            }
        } catch (Exception e) {
            //...
        }
        return allOrders;
    }
}
