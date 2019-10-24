package travelexperts.utils;
import javafx.scene.control.TextField;
import java.util.regex.Pattern;

//Author: Abhilash Paul

public class InputValidator {

    //method t verify if an input is a numeric value
    public static boolean isNumeric(String input) {
        try {
            int i = Integer.parseInt(input);
            float f = Float.parseFloat(input);
            Double d = Double.parseDouble(input);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isPresent(TextField textField) {
            return !textField.getText().trim().isEmpty();
    }

    public static boolean validateEmail(String email){
        String patternString = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern emailPattern = Pattern.compile(patternString);
        return emailPattern.matcher(email).matches();
    }

    public static boolean validatePostalCode(String postalcode){
        String patternString = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z][ ]?[0-9][A-Z][0-9]$";
        Pattern postalPattern = Pattern.compile(patternString);
        return postalPattern.matcher(postalcode.toUpperCase()).matches();
    }

    public static boolean isTextOnly(String text){
        String patternString = "^[a-zA-Z ]*$";
        Pattern textPattern = Pattern.compile(patternString);
        return textPattern.matcher(text).matches();
    }

    public static boolean validatePhoneNumber(String phonenumber){
        String patternString = "^\\(?([0-9]{3})\\)?[-.●]?([0-9]{3})[-.●]?([0-9]{4})$";
        Pattern phonePattern = Pattern.compile(patternString);
        return phonePattern.matcher(phonenumber).matches();
    }


    public static boolean validateUserName(String username){
        String patternString = "^[a-z0-9._-]{5,15}$";
        Pattern usernamePattern = Pattern.compile(patternString);
        return usernamePattern.matcher(username).matches();
    }
}
