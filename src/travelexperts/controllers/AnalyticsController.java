package travelexperts.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import travelexperts.dbhandler.AnalyticsDB;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AnalyticsController {

    @FXML
    private BarChart<String, Integer> barChartTopDestinations;

    @FXML
    private ScatterChart<String, Integer> scatterPlotTopCustomers;

    @FXML
    private CategoryAxis xAxis_dest, xAxis_cust;

    @FXML
    private NumberAxis yAxis_dest,yAxis_cust;

    @FXML
    void initialize() {
        displayTopDestinations();
        displayTopCustomers();
    }

    private void displayTopDestinations(){
        HashMap<String, Integer> topDestinationList = null;
        try {
            topDestinationList = AnalyticsDB.getTopDestinations();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        barChartTopDestinations.getXAxis().setLabel("Destination");
        barChartTopDestinations.getYAxis().setLabel("Number of Bookings");
        yAxis_dest.setTickUnit(2);
        XYChart.Series dataSeries = new XYChart.Series();

        for(Map.Entry entry : topDestinationList.entrySet()) {
            dataSeries.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }
        barChartTopDestinations.getData().add(dataSeries);
        barChartTopDestinations.setLegendVisible(false);
    }

    private void displayTopCustomers(){
        HashMap<String, Integer> topCustomersList = null;
        try {
            topCustomersList = AnalyticsDB.getTopCustomers();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        //barChartTopDestinations.getXAxis().setLabel("Destination");
        scatterPlotTopCustomers.getYAxis().setLabel("Number of Bookings");
        yAxis_cust.setTickUnit(2);
        XYChart.Series dataSeries = new XYChart.Series();

        for(Map.Entry entry : topCustomersList.entrySet()) {
            dataSeries.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }
        scatterPlotTopCustomers.getData().add(dataSeries);
        scatterPlotTopCustomers.setLegendVisible(false);
    }


}
