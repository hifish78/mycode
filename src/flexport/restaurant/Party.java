package flexport.restaurant;

public class Party {
    private int id;
    private int size;

    public Party(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
}
