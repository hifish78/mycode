package c1;

/***
 * 题目一：转账
 * # Initialize SavingsManager - {“A”: 100.0, “B”: 10.0, “C”: 20.0}
 * 比如ABC三个用户，完成get(“A”) deposit(“B”, 25.0) withdraw(“C”, 5.0) transfer(“C”, “A”, 10.0)这几个函数
 */

import java.util.*;

class User {
    public String user;
    public double balance;
    User(String user, double balance) {
        this.user = user;
        this.balance = balance;
    }
}

public class SavingManager {
    public Set<User> customers;

    public SavingManager(Set<User> customers) {
         this.customers = customers;
    }

    public double get(User user) throws Exception {
        if (!customers.contains(user)) {
            throw new Exception("can't find the user!");
        }
        return user.balance;
    }

    public double deposit(User user, double money) throws Exception {
        if (!customers.contains(user)) {
            throw new Exception("can't find the user!");
        }
        user.balance = get(user) + money;
        return user.balance;
    }

    public double withDraw(User user, double money) throws Exception {
        if (!customers.contains(user)) {
            throw new Exception("can't find the user!");
        }
        double curBalance = get(user);
        if (curBalance < money) {
            throw new Exception("no enough money can be withdrawn.");
        }
        user.balance = get(user) - money;
        return user.balance;
    }

    public void transfer(User from, User to, double money) throws Exception {
        if (!customers.contains(from) || !customers.contains(to)) {
            throw new Exception("can't find the user!");
        }
        if (get(from) < money) {
            throw new Exception("No enough money can be transfer from " + from.user);
        }

        from.balance = get(from) - money;
        to.balance = get(to) + money;
    }

    public static void main(String[] args) throws Exception {
        User userA = new User("A", 100.0);
        User userB = new User("B", 25.0);
        User userC = new User("C", 20.0);
        Set<User> customers  = new HashSet();
        customers.add(userA);
        customers.add(userB);
        customers.add(userC);

        SavingManager savingManager = new SavingManager(customers);

        System.out.println(savingManager.get(userA)); //100
        System.out.println(savingManager.deposit(userB, 25.0)); //50.0
        System.out.println(savingManager.withDraw(userC, 5.0)); // 15.0
        savingManager.transfer(userC, userA, 10.0); //C: 5.0, A:110.0
        System.out.println(savingManager.get(userC));
        System.out.println(savingManager.get(userA));

        savingManager.withDraw(userC, 100.0);


    }

}
