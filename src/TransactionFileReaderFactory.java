public class TransactionFileReaderFactory {

    public static TransactionFileReader getFileReader(String filePath) {
        if (filePath.endsWith(".csv")) {
            return new CSVTransactionFileReader();
        } else if (filePath.endsWith(".json")) {
            return new JSONTransactionFileReader();
        } else if (filePath.endsWith(".xml")) {
            return new XMLTransactionFileReader();
        } else {
            throw new IllegalArgumentException("Unsupported file format");
        }
    }
}
