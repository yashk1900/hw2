package controller;
import controller.TransactionFilter;
import model.ExpenseTrackerModel;
import model.Transaction;
import java.util.ArrayList;
import java.util.List;

//Extending the TransactionFilter interface to create an Category filter class
public class CategoryFilter implements TransactionFilter {
    private String categoryToFilter;

    public CategoryFilter(String categoryToFilter) {
        this.categoryToFilter = categoryToFilter;
    }

    public boolean inputValidation() {
        if (!InputValidation.isValidCategory(categoryToFilter)) {
            return false;
        }
        return true;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {

        List<Transaction> filter_Transactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getCategory().equals(categoryToFilter)) {
                filter_Transactions.add(transaction);
            }
        }
        return filter_Transactions; //returning the filtered transaction list as per category specified
    }
}