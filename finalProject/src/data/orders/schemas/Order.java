package data.orders.schemas;

public class Order {
    private int id;
    private String order_nick;
    private int item_id;
    private int market_id;
    private int count;

    public int getId() {
        return id;
    }

    public int getMarket_id() {
        return market_id;
    }

    public void setMarket_id(int market_id) {
        this.market_id = market_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setOrder_nick(String order_nick) {
        this.order_nick = order_nick;
    }

    public String getOrder_id() {
        return order_nick;
    }

    public Order(int id, String order_nick, int item_id, int market_id, int count) {
        this.id = id;
        setOrder_nick(order_nick);
        setItem_id(item_id);
        setMarket_id(market_id);
        setCount(count);
    }
}
