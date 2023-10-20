package controller;
import java.util.Arrays;

//Input Validation Java file....for checking valid amount and valid Category input....used both in input fields and search fields
public class InputValidation {

  public static boolean isValidAmount(double amount) {    
    // Check range
    if(amount >= 1000) {
      return false;
    }
    if (amount < 0){
      return false;
    }
    if (amount == 0){
      return false;
    }
    return true;
  }

  public static boolean isValidCategory(String category) {

    if(category == null) {
      return false; 
    }
  
    if(category.trim().isEmpty()) {
      return false;
    }

    if(!category.matches("[a-zA-Z]+")) {
      return false;
    }

    //predefined valid category list
    String[] validWords = {"food", "travel", "bills", "entertainment", "other"};

    if(!Arrays.asList(validWords).contains(category.toLowerCase())) {
      return false; //invalid words
    }

    return true;  
  }
}