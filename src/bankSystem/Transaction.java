package bankSystem;
import java.time.LocalDateTime;

public class Transaction {
    private final LocalDateTime timestamp;
    private final String type;
    private final double amount;

    public Transaction(String type, double amount) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return timestamp + " - " + type + ": " + amount;
    }
}
