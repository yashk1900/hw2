package view;
import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import controller.InputValidation;
import java.awt.*;
import java.text.NumberFormat;
import model.Transaction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JButton filterCategoryBtn;
  private JButton filterAmountBtn;
  // to remove a transaction
  // private JButton removeTransactionBtn;
  private JFormattedTextField amountFilterField;
  private JTextField categoryFilterField;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(800, 300); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");
    filterAmountBtn = new JButton("Filter via Amount");
    filterCategoryBtn = new JButton("Filter via Category");
    // removeTransactionBtn = new JButton("Remove Selected Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);

    JLabel amountFilterLabel = new JLabel("Amount Filter:");

    amountFilterField = new JFormattedTextField(format);
    amountFilterField.setColumns(10);

    JLabel categoryFilterLabel = new JLabel("Category Filter:");

    categoryFilterField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);
    // transactionsTable.setSelectionBackground(Color.red);
    // transactionsTable.setSelectionForeground(Color.black);

    // Layout components
    JPanel filterPanel = new JPanel();
    filterPanel.add(amountFilterLabel);
    filterPanel.add(amountFilterField);
    filterPanel.add(filterAmountBtn);
    filterPanel.add(categoryFilterLabel);
    filterPanel.add(categoryFilterField);
    filterPanel.add(filterCategoryBtn);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
    // buttonPanel.add(removeTransactionBtn);
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(inputPanel);
    mainPanel.add(filterPanel);

    // Add the main panel and the table to the frame
    add(mainPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Setting frame properties to keep in check on load UI
    setSize(800, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
      highlightTableReset();
  
  }

  public JButton getFilterAmountBtn() {
    return filterAmountBtn;
  }

  // public JButton getRemoveTransactionBtn(){
  //   return removeTransactionBtn;
  // }

  public JButton getFilterCategoryBtn() {
    return filterCategoryBtn;
  }
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }


  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  public double getAmountFilterField() {
    if(amountFilterField.getText().isEmpty()) {
      return 0;
    }else {
      double amount = Double.parseDouble(amountFilterField.getText());
      return amount;
    }
  }

  public String getCategoryFilterField() {
    return categoryFilterField.getText();
  }

  //row highlight setter
  public void highlightTable(List<Transaction> transactions) {
    //re rendering the transaction view with highlights
    DefaultTableCellRenderer rendered_view = new DefaultTableCellRenderer();
    rendered_view.setBackground(Color.GREEN);

    //storing required rows for future highlighting
    Set<Integer> rowIndex = findTransactionRowIndex(transactions);

    transactionsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        // Check if the current row is to be highlighted
        if (rowIndex.contains(row)) {
          c.setBackground(Color.GREEN);
        } else {
          c.setBackground(table.getBackground());
        }

        return c;
      }
    });
    transactionsTable.repaint();
  }

  //row highlight setter
  public void highlightTableReset() {
    //re rendering the transaction view with highlights
    DefaultTableCellRenderer rendered_view = new DefaultTableCellRenderer();
    
    //storing required rows for future highlighting
    Set<Integer> rowIndex = findAllTransactionsRowIndex();

    transactionsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        // Check if the current row is to be highlighted
        if (rowIndex.contains(row)) {
          c.setBackground(Color.GREEN);
        } else {
          c.setBackground(table.getBackground());
        }

        return c;
      }
    });
    transactionsTable.repaint();
  }


  //function to find rows indexes and return to the highlight function, by matching all the received row field values
  private Set<Integer> findTransactionRowIndex(List<Transaction> transactions) {
    Set<Integer> rowIndexes = new HashSet<>();
    for (Transaction transaction : transactions) {
      for (int row = 0; row < model.getRowCount()-1; row++) {
        if (model.getValueAt(row, 1).equals(transaction.getAmount())
                && model.getValueAt(row, 2).equals(transaction.getCategory())
                && model.getValueAt(row, 3).equals(transaction.getTimestamp())) {
          rowIndexes.add(row);
        }
      }
    }
    return rowIndexes;
  }

  //function to return all rows indexes
  private Set<Integer> findAllTransactionsRowIndex() {
    Set<Integer> allRowIndexes = new HashSet<>();
    return allRowIndexes;
  }

}
