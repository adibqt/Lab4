import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankStatementAnalyzer {
    private List<Transaction> transactions;
    public BankStatementAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    public double calculateTotalProfitOrLoss(){
        double total = 0;
        for (Transaction transaction : transactions) {
            total += transaction.getAmount();
        }
        return total;
    }
    public long countTransactionsForMonth(int month, int year) {
        long count = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getMonthValue() == month && transaction.getDate().getYear() == year) {
                count++;
            }
        }
        return count;
    }
    public List<Transaction> getTop10Expenses() {
        List<Transaction> expenses = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                expenses.add(transaction);
            }
        }
        expenses.sort(new TransactionAmountComparator());
        List<Transaction> top10Expenses = new ArrayList<>();
        for (int i = 0; i < Math.min(10, expenses.size()); i++) {
            top10Expenses.add(expenses.get(i));
        }
        return top10Expenses;
    }

    public String getCategoryWithMostSpending() {
        Map<String, Double> spendingByCategory = new HashMap<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) { // Only expenses
                String category = t.getCategory();
                spendingByCategory.put(category, spendingByCategory.getOrDefault(category, 0.0) + t.getAmount());
            }
        }
        String categoryWithMostSpending = null;
        double mostSpending = Double.POSITIVE_INFINITY;
        for (Map.Entry<String, Double> entry : spendingByCategory.entrySet()) {
            if (entry.getValue() < mostSpending) {
                mostSpending = entry.getValue();
                categoryWithMostSpending = entry.getKey();
            }
        }
        return categoryWithMostSpending != null ? categoryWithMostSpending : "No expenses available";
    }





}


