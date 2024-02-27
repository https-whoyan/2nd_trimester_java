package data.items.schemas;

public class Item {
    private int id;
    private String name;
    private int market_id;
    private int count;
    private int price;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setMarket_id(int market_id) {
        this.market_id = market_id;
    }

    public int getMarket_id() {
        return market_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public Item(int id, String name, int market_id, int count, int price) {
        this.id = id;
        setName(name);
        setCount(count);
        setMarket_id(market_id);
        setPrice(price);
    }
}
