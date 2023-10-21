// import javax.sound.midi.SysexMessage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import controller.AmountFilter;
import controller.CategoryFilter;
import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;

public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }

    });

    // // handler remove transaction btn clicks
    // view.getRemoveTransactionBtn().addActionListener(e -> {
    //   // Get transaction data from view
    //   int rowNumber = view.getTransactionsTable().getSelectedRow();
    //   double selected_amount = (double)view.getTransactionsTable().getValueAt(rowNumber, 1);
    //   String selected_category = (String)view.getTransactionsTable().getValueAt(rowNumber, 2);
    //   String selected_date = (String)view.getTransactionsTable().getValueAt(rowNumber, 3);

    //   // pass this data to controller
    //   controller.removeTransaction(selected_amount, selected_category,selected_date);
    // });

    view.getFilterAmountBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountFilterField();

      controller.setStrategy(new AmountFilter(amount));

      boolean filtered = controller.applyFilter();

      if (!filtered) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }

    });

    view.getFilterCategoryBtn().addActionListener(e -> {
      // Get transaction data from view
      String category = view.getCategoryFilterField();

      controller.setStrategy(new CategoryFilter(category));

      // Call controller to filter transactions
      boolean filtered = controller.applyFilter();

      if (!filtered) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }

    });

  }

}