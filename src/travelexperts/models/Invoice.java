package travelexperts.models;

public class Invoice {

    private String InvoiceRef;

    public String getInvoiceRef() {
        InvoiceRef=generateInvoiceRef(6);
        return InvoiceRef;
    }

    public void setInvoiceRef(String invoiceRef) {
        InvoiceRef = invoiceRef;
    }

    public Invoice(String invoiceRef) {
        InvoiceRef = invoiceRef;
    }

    // function to generate a random string of length n
    public static String generateInvoiceRef(int n)
    {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"+"abcdefghijklmnopqrstuvwxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
