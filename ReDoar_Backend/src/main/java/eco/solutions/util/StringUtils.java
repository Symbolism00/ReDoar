package eco.solutions.util;

public class StringUtils {

    /**
     * Method that checks if a string is null or empty
     * @param str the string to check
     * @return true if it is, false if not
     */
    public static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    /**
     * Method that trims a string if possible (!= null)
     * @param str the string to trim
     * @return the string trimmed
     */
    public static String trim(String str){
        return str == null ? null : str.trim();
    }

    /**
     * Method that checks if a tax number is valid
     * @param taxNumber the tax number
     * @param entity the entity type to be in the error message
     * @return a non null string declaring the error, null if there is no error
     */
    public static String isValidTaxNumber(String taxNumber, String entity){
        if(StringUtils.isNullOrEmpty(taxNumber)){
            return "The " + entity + " tax number can't be null or empty!";
        }
        taxNumber = StringUtils.trim(taxNumber);
        String errorMessage = "The " + entity + " tax number " + taxNumber + " is invalid!";

        //check if is numeric and has 9 numbers
        if (!NumberUtils.isInteger(taxNumber) || taxNumber.length() != GlobalConstant.TAX_NUMBER_SIZE){
            return errorMessage;
        }
        int checkSum = 0;
        //calculate checkSum
        for (int i = 0; i < GlobalConstant.TAX_NUMBER_SIZE - 1; i++){
            checkSum += (taxNumber.charAt(i) - '0') * (GlobalConstant.TAX_NUMBER_SIZE - i);
        }
        int checkDigit = 11 - (checkSum % 11);
        // if checkDigit is higher than 9 set it to zero
        if (checkDigit > 9){
            checkDigit = 0;
        }
        //compare checkDigit with the last number of NIF
        return checkDigit != taxNumber.charAt(GlobalConstant.TAX_NUMBER_SIZE  -1) - '0' ? errorMessage : null;
    }

    /**
     * Method that checks if a phone number is valid
     * @param phoneNumber the phone number
     * @param entity the entity type to be in the error message
     * @return a non null string declaring the error, null if there is no error
     */
    public static String isValidPhoneNumber(String phoneNumber, String entity){
        if(StringUtils.isNullOrEmpty(phoneNumber)){
            return "The " + entity + " phone number can't be null or empty!";
        }
        phoneNumber = StringUtils.trim(phoneNumber);
        String errorMessage = "The " + entity + " phone number " + phoneNumber + " is invalid!";

        // if its not a number
        if(!NumberUtils.isInteger(phoneNumber)){
            return errorMessage;
        }

        // if does not start with 91, 92, 93, 96, and 2 and has 9 chars
        return !phoneNumber.matches("9[1|2|3|6]\\d{7}|2\\d{8}") ? errorMessage : null;
    }

    /**
     * Method that checks if an email is valid
     * @param email the email
     * @param entity the entity type to be in the error message
     * @return a non null string declaring the error, null if there is no error
     */
    public static String isValidEmail(String email, String entity){
        if(StringUtils.isNullOrEmpty(email)){
            return "The " + entity + " email can't be null or empty!";
        }
        email = StringUtils.trim(email);

        // checks email
        return !email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+" +
                "(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$") ? "The " + entity + " email " + email + " is invalid!" : null;
    }

    /**
     * Method that checks if a zip code is valid
     * @param zipCode the zip code
     * @param entity the entity type to be in the error message
     * @return a non null string declaring the error, null if there is no error
     */
    public static String isValidZipCode(String zipCode, String entity){
        if(StringUtils.isNullOrEmpty(zipCode)){
            return "The " + entity + " zip code can't be null or empty!";
        }
        zipCode = StringUtils.trim(zipCode);

        // checks zip code
        return zipCode.matches("[1-9][0-9]{3}-[0-9]{3}") ? null :
                "The " + entity + " zip code " + zipCode + " is invalid!";
    }
}
