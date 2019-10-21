package travelexperts.models;

public class Invoice {
    private int InvoiceNo;

    public int getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        InvoiceNo = invoiceNo;
    }

    public Invoice(int invoiceNo) {
        InvoiceNo = invoiceNo;
    }
}
