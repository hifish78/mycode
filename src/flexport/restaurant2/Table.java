package flexport.restaurant2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Table implements Comparable<Table> {
    private int id;
    private int capacity;
    private boolean available;
    private Order order;
    List<Date> reservations;

    public Table(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.available = true;
        this.order = null;
        reservations = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public List<Date> getReservations() {
        return reservations;
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

    public Order getCurrentOrder() {
        return this.order;
    }

    public void setOrder(Order o) {
        this.order.mergeOrder(o);
    }

    private int findDatePosition(Date d) {
        int len = reservations.size();
        if (len == 0) {
            return 0;
        }
        if (d.getTime() > reservations.get(len - 1).getTime()) {
            return len;
        }

        int i = 0;
        int j = len;
        while ( i < j) {
            int m = (i + j) / 2;
            if (d.getTime() > reservations.get(m).getTime()) {
                i = m + 1;
            } else {
                j = m;
            }
        }
        return j;
    }

    public boolean noFollowReservation(Date d) {
        final int MILLI_TO_HR = 1000 * 60 * 60;
        int pos = findDatePosition(d);
        if (pos < reservations.size()) {
            Date nextReservation = reservations.get(pos);
            int diff = (int) (nextReservation.getTime() - d.getTime()) / MILLI_TO_HR;
            if (diff < Restaurant.MAX_DINEHOUR) {
                return false;
            }
        }
        return true;
    }

    public boolean reserveForDate(Date d) {
        final int MILLI_TO_HR = 1000 * 60 * 60;
        int pos = findDatePosition(d);
        int before  = pos - 1;
        int after = pos;
        if (before >= 0) {
            Date prev = reservations.get(before);
            int diff = (int)(d.getTime() - prev.getTime()) / MILLI_TO_HR;
            if (diff < Restaurant.MAX_DINEHOUR) {
                return false;
            }
        }
        if (after < reservations.size()) {
            Date next = reservations.get(after);
            int diff = (int)(next.getTime() - d.getTime()) / MILLI_TO_HR;
            if (diff < Restaurant.MAX_DINEHOUR) {
                return false;
            }
        }
        reservations.add(pos, d);
        return true;
    }
    public void removeReservation(Date d) {
        reservations.remove(d);
    }



    // table
    @Override
    public int compareTo(Table o) {
        return this.capacity - o.capacity;
    }
}
