import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class XMLTransactionFileReader implements TransactionFileReader {

    private static final String DATE_FORMAT = "yyyy-MM-dd"; // Assumed XML date format

    @Override
    public List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();

        try {
            // Create a DocumentBuilderFactory to create a DocumentBuilder for parsing XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file and get the document
            Document document = builder.parse(new File(filePath));

            // Normalize XML structure
            document.getDocumentElement().normalize();

            // Get all transaction elements
            NodeList nodeList = document.getElementsByTagName("transaction");

            // Iterate through the transactions and extract data
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Extract the date, amount, and category values
                    String dateStr = element.getElementsByTagName("date").item(0).getTextContent();
                    String amountStr = element.getElementsByTagName("amount").item(0).getTextContent();
                    String category = element.getElementsByTagName("category").item(0).getTextContent();

                    // Parse the extracted values
                    LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DATE_FORMAT));
                    double amount = Double.parseDouble(amountStr);

                    // Create a new Transaction object
                    transactions.add(new Transaction(date, amount, category));
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading XML file: " + e.getMessage());
        }

        return transactions;
    }
}
