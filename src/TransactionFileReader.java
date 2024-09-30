import java.util.List;

public interface TransactionFileReader {
    List<Transaction> readTransactions(String filePath);
}
