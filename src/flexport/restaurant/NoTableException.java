package flexport.restaurant;

public class NoTableException extends Exception{
    public NoTableException(Party p) {
        super("No table avaialble for party size: " + p.getSize());
    }
}
