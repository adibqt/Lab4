public class ExpenseFilter extends TransactionFilter {
    @Override
    public boolean matches(Transaction transaction) {
        return transaction.getAmount() < 0;
    }
}
