package controller;
import controller.TransactionFilter;
import model.ExpenseTrackerModel;
import model.Transaction;
import java.util.ArrayList;
import java.util.List;

//Extending the TransactionFilter interface to create an amount filter class
public class AmountFilter implements TransactionFilter {
    private double amount;

    public AmountFilter(double amount) {
        this.amount = amount;
    }

    public boolean inputValidation() {
        if (!InputValidation.isValidAmount(amount)) {
            return false;
        }
        return true;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {

        List<Transaction> filter_Transactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            double amount = transaction.getAmount();
            if (amount == this.amount) {
                filter_Transactions.add(transaction);
            }
        }
        
        return filter_Transactions; //returning the filtered transaction list as per amount specified
    }
}
