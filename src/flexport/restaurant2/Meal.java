package flexport.restaurant2;

public class Meal {
    private int id;
    private String name;
    private float price;

    public Meal(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public float getPrice() {
        return this.price;
    }
}
