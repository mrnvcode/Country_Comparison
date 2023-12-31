import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BarChartSceneController {
    @FXML
    private RadioButton year2010RadioButton; // RadioButton for selecting the year 2010
    @FXML
    private RadioButton year2020RadioButton; // RadioButton for selecting the year 2020
    @FXML
    private BarChart<String, Number> barChart; // BarChart for displaying GDP data
    @FXML
    private CategoryAxis xAxis; // X-axis of the BarChart
    @FXML
    private NumberAxis yAxis; // Y-axis of the BarChart
    private MainApp mainApp; // Reference to the main application
    private ToggleGroup toggleGroup; // ToggleGroup for the RadioButtons

    @FXML
    private void initialize() {
        toggleGroup = new ToggleGroup();
        year2010RadioButton.setToggleGroup(toggleGroup);
        year2020RadioButton.setToggleGroup(toggleGroup);
        year2010RadioButton.setSelected(true);

        handleYearRadioButtonChange(); // Call the method to display the chart for the selected year
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleYearRadioButtonChange() {
        if (toggleGroup.getSelectedToggle() == year2010RadioButton) {
            displayGDPBarChart("GDP_2010"); // Display the GDP data for the year 2010
        } else if (toggleGroup.getSelectedToggle() == year2020RadioButton) {
            displayGDPBarChart("GDP_2020"); // Display the GDP data for the year 2020
        }
    }

    private void displayGDPBarChart(String column) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Comparison_data", "mrnvcode", "Naman@123");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT Country, " + column + " FROM GDP_comparison")) {

            List<GDPData> gdpDataList = new ArrayList<>();

            while (resultSet.next()) {
                String country = resultSet.getString("Country");
                double gdp = resultSet.getDouble(column);
                gdpDataList.add(new GDPData(country, gdp));
            }

            // Sort the GDP data in descending order
            Collections.sort(gdpDataList, Comparator.comparingDouble(GDPData::getGdp).reversed());

            barChart.getData().clear();
            xAxis.setLabel("Country"); // Set the label for the X-axis
            yAxis.setLabel("GDP"); // Set the label for the Y-axis

            XYChart.Series<String, Number> series = new XYChart.Series<>();

            for (GDPData data : gdpDataList) {
                series.getData().add(new XYChart.Data<>(data.getCountry(), data.getGdp()));
            }

            barChart.getData().add(series); // Add the series to the BarChart
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showTableScene() {
        mainApp.showTableScene(); // Switch to the TableScene
    }
}

class GDPData {
    private String country;
    private double gdp;
    public GDPData(String country, double gdp) {
        this.country = country;
        this.gdp = gdp;
    }

    public String getCountry() {
        return country;
    }

    public double getGdp() {
        return gdp;
    }
}