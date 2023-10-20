package controller;
import model.Transaction;
import java.util.List;

////Implementing a Transaction Filter interface to be reused as per use cases and class requirements
public interface TransactionFilter {
    List<Transaction> filter(List<Transaction> transactions);
    boolean inputValidation();
}