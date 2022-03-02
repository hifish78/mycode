package flexport.restaurant;

public class Table {
    private int id;
    private int capacity;
    private boolean available;
    private Order order;

    public Table(int id , int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.available = true;
        order = null;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void markAvailable() {
        this.available = true;
    }

    public void markUnavailable() {
        this.available = false;
    }

    public Order getOrder() {
        return this.order;
    }


}
