package data.market.schemas;

public class Market {
    private int id;
    public String name;

    public Market() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Market(int id, String marketName) {
        this.id = id;
        setName(marketName);
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", MarketName: " + this.name;
    }
}
