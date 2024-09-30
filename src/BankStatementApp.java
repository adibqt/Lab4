import java.util.List;

public class BankStatementApp {

    public static void main(String[] args) {
        // Specify the file path (could be CSV, JSON, or XML)
        String filePath = "path_to_file.csv"; // Change this to test different formats

        // Use the factory to get the appropriate file reader
        TransactionFileReader fileReader = TransactionFileReaderFactory.getFileReader(filePath);

        // Read transactions from the file
        List<Transaction> transactions = fileReader.readTransactions(filePath);

        // Proceed only if transactions were read successfully
        if (transactions.isEmpty()) {
            System.out.println("No transactions found or error reading file.");
            return;
        }
        BankStatementAnalyzer analyzer = new BankStatementAnalyzer(transactions);
