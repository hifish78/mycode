package flexport.restaurant2;

public class NoTableException extends Exception {
    public NoTableException(Party p) {
        super("No table available for party size: " + p.getSize());
    }
}
