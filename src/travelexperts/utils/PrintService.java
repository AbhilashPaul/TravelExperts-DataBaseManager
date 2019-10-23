package travelexperts.utils;

import javafx.collections.ObservableSet;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;

public class PrintService {

    public static void print(Node node, Text jobStatus)
    {
        // Define the Job Status Message
        jobStatus.textProperty().unbind();
        jobStatus.setText("Creating a printer job...");
        Printer pdfPrinter = null;
        ObservableSet<Printer> printers = Printer.getAllPrinters();
        for (Printer p : printers){
            if(p.getName().contains("PDF")){
                pdfPrinter = p;
            }
        }

        //page
        PageLayout page = pdfPrinter.createPageLayout(Paper.A4, PageOrientation.PORTRAIT,Printer.MarginType.HARDWARE_MINIMUM);

        // Create a printer job for the default printer
        PrinterJob job = PrinterJob.createPrinterJob(pdfPrinter);

        double pWidth = page.getPrintableWidth();
        double pHeight = page.getPrintableHeight();

        // Node's (Image) dimensions
        double nWidth = node.getBoundsInParent().getWidth();
        double nHeight = node.getBoundsInParent().getHeight();

        double widthLeft = pWidth - nWidth;
        double heightLeft = pHeight - nHeight;
        // scale the image to fit the page in width, height or both
        double scale = 0;

        if (widthLeft < heightLeft) {
            scale = nWidth / pWidth;
        } else {
            scale = nHeight / pHeight;
        }

        // preserve ratio (both values are the same)
        //node.getTransforms().add(new Scale(scale, scale));

        if (job != null)
        {
            // Show the printer job status
            jobStatus.textProperty().bind(job.jobStatusProperty().asString());
            job.showPrintDialog(node.getScene().getWindow());

            job.getJobSettings().setPageLayout(page);
            // Print the node
            boolean printed = job.printPage(node);

            if (printed)
            {
                // End the printer job
                job.endJob();
            }
            else
            {
                // Write Error Message
                jobStatus.textProperty().unbind();
                jobStatus.setText("Printing failed.");
            }
        }
        else
        {
            // Write Error Message
            jobStatus.setText("Could not create a printer job.");
        }
    }
}
