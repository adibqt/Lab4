import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JSONTransactionFileReader implements TransactionFileReader {

    private static final String DATE_FORMAT = "yyyy-MM-dd"; // Assumed date format

    @Override
    public List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();

        try {
            // Step 1: Read the JSON file as a plain text string
            StringBuilder jsonContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            reader.close();

            // Step 2: Parse the JSON string into a JSONArray
            JSONArray jsonArray = new JSONArray(jsonContent.toString());

            // Step 3: Iterate through the JSONArray and extract data for each transaction
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonTransaction = jsonArray.getJSONObject(i);

                // Extract the date, amount, and category fields
                String dateStr = jsonTransaction.getString("date");
                double amount = jsonTransaction.getDouble("amount");
                String category = jsonTransaction.getString("category");

                // Convert the date string into a LocalDate object
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DATE_FORMAT));

                // Step 4: Create a Transaction object and add it to the list
                transactions.add(new Transaction(date, amount, category));
            }

        } catch (Exception e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }

        return transactions;
    }
}
