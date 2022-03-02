package flexport.restaurant2;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private List<Meal> meals;
    private Table table;
    private Party party;

    public Order() {
        meals = new ArrayList<>();
    }

    public void mergeOrder(Order order) {
        for (Meal meal : order.getMeals()) {
            meals.add(meal);
        }
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public float getBill() {
        float sum = 0;
        for (Meal meal : meals) {
            sum += meal.getPrice();
        }
        return sum;
    }

}
