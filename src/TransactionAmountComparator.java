import java.util.Comparator;

public class TransactionAmountComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        return Double.compare(t1.getAmount(), t2.getAmount());
    }
}
