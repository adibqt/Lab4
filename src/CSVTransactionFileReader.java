import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVTransactionFileReader implements TransactionFileReader {
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    @Override
    public List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT); // Date format constant

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    LocalDate date = LocalDate.parse(values[0], formatter); // Parse date
                    double amount = Double.parseDouble(values[1]);          // Parse amount
                    String category = values[2];                            // Parse category
                    transactions.add(new Transaction(date, amount, category));
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return transactions;
    }
}
