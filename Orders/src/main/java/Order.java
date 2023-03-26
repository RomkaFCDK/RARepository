public class Order {
    private int id;
    private Client client;
    private Goods goods;

    public Order() {
    }

    public Order(Client client, Goods goods) {
        this.client = client;
        this.goods = goods;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" + "client=" + client +
                ", goods=" + goods +
                '}';
    }
}
