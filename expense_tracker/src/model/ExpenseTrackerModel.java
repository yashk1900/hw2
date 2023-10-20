package model;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerModel {

  // IMPLEMENTING ENCAPSULATION
  private final List<Transaction> transactions;

  public ExpenseTrackerModel() {
    transactions = new ArrayList<>(); 
  }

  public void addTransaction(Transaction t) {
    transactions.add(t);
  }

  public void removeTransaction(Transaction t) {
    transactions.remove(t);
  }

  public List<Transaction> getTransactions() {

    // RETURN A SHALLOW COPY OF THE LIST, PREVENTING UNWANTED MUTABILITY
    List<Transaction> copyOfTransactions = new ArrayList<Transaction>();
    copyOfTransactions.addAll(this.transactions);
    return copyOfTransactions;
  }

}