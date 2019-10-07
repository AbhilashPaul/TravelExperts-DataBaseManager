package travelexperts.main;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    void initialize() {
        displayTopDestinations();
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
        yAxis.setTickUnit(2);
        XYChart.Series dataSeries = new XYChart.Series();

        for(Map.Entry entry : topDestinationList.entrySet()) {
            dataSeries.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }
        barChartTopDestinations.getData().add(dataSeries);
    }



}
