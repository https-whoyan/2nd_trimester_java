package data.items.DB;
import data.items.schemas.Item;

import domain.SQLConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemsDBWork {
    public static void insertItem(Item newItem) {
        try {
            Statement statement = SQLConnection.getStatement();
            String query;

            if (getCurrItem(newItem.getName(), newItem.getMarket_id()) != null) {
                updateItemCount(newItem);
                return;
            }

            query = "INSERT INTO items (name, market_id, count, price) VALUES ('" +
                    newItem.getName() + "', " + newItem.getMarket_id() + ", " +
                    newItem.getCount() + ", " + newItem.getPrice() + ")";

            statement.executeQuery(query);
        } catch (Exception e) {
            //...
        }
    }

    public static Item getCurrItem(String name, int market_id) {

        Statement statement = SQLConnection.getStatement();
        try {
            String query = "SELECT * " +
                    "FROM items " +
                    "WHERE name = '" + name + "' AND market_id = " + market_id;
            ResultSet table = statement.executeQuery(query);
            table.beforeFirst();

            Item currItem;

            while (table.next()) {
                currItem = new Item(
                        table.getInt(1),
                        table.getString(2),
                        table.getInt(3),
                        table.getInt(4),
                        table.getInt(5)
                );
                return currItem;
            }
        } catch (Exception e) {
            //...
        }
        return null;
    }

    public static ArrayList<Item> getAllItems(String name) {
        Statement statement = SQLConnection.getStatement();
        ArrayList<Item> allItems = new ArrayList<>();
        try {
            String query = "SELECT * " +
                    "FROM items " +
                    "WHERE name = '" + name + "' AND count != 0 " +
                    "ORDER BY price, count DESC";
            ResultSet table = statement.executeQuery(query);
            table.beforeFirst();

            Item currItem;

            while (table.next()) {
                currItem = new Item(
                        table.getInt(1),
                        table.getString(2),
                        table.getInt(3),
                        table.getInt(4),
                        table.getInt(5)
                );
                allItems.add(currItem);
            }
        } catch (Exception e) {
            //...
        }
        return allItems;
    }

    public static ArrayList<Item> getAllItems(int marketID) {
        Statement statement = SQLConnection.getStatement();
        ArrayList<Item> allItems = new ArrayList<>();
        try {
            String query = "SELECT * " +
                    "FROM items " +
                    "WHERE market_id = " + marketID;
            ResultSet table = statement.executeQuery(query);
            table.beforeFirst();

            Item currItem;

            while (table.next()) {
                currItem = new Item(
                        table.getInt(1),
                        table.getString(2),
                        table.getInt(3),
                        table.getInt(4),
                        table.getInt(5)
                );
                allItems.add(currItem);
            }
        } catch (Exception e) {
            //...
        }
        return allItems;
    }

    public static void updateItemCount(Item updatedItem) {
        try {
            Statement statement = SQLConnection.getStatement();

            int currItemCount = getCurrItem(updatedItem.getName(), updatedItem.getMarket_id()).getCount();
            String itemName = updatedItem.getName();
            int itemMarketID = updatedItem.getMarket_id();
            int newItemCount = currItemCount + updatedItem.getCount();

            String query = "UPDATE items SET count = " + newItemCount + " WHERE " +
                    "name = '" + itemName + "' AND market_id = " + itemMarketID;
            statement.executeQuery(query);
        } catch (Exception e) {
            //...
        }
    }

    public static void setItemCountZero(int itemID) {
        try {
            Statement statement = SQLConnection.getStatement();
            String query = "UPDATE items SET count = 0" +
                    " WHERE id = " + itemID;
            statement.executeQuery(query);
        } catch (Exception e) {
            //...
        }
    }
}
