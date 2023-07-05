import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.*;

public class TableSceneController {
    @FXML
    private TableView<PopulationData> tableView;
    @FXML
    private Button backButton;
    private MainApp mainApp;

    /**
     * Sets the reference to the main application.
     *
     * @param mainApp The reference to the main application
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        populateTableData();
    }

    /**
     * Populates the table view with data from the database.
     */
    private void populateTableData() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Comparison_data", "mrnvcode", "Naman@123");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM GDP_comparison")) {

            tableView.getItems().clear();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String country = resultSet.getString("Country");
                double gdp2010 = resultSet.getDouble("GDP_2010");
                double gdp2020 = resultSet.getDouble("GDP_2020");
                String population2010Str = resultSet.getString("Population_2010");
                String population2020Str = resultSet.getString("Population_2020");
                int population2010 = parsePopulation(population2010Str);
                int population2020 = parsePopulation(population2020Str);
                double gdpPerCapita2010 = resultSet.getDouble("GDP_per_capita_2010");
                double gdpPerCapita2020 = resultSet.getDouble("GDP_per_capita_2020");

                PopulationData data = new PopulationData(id, country, gdp2010, gdp2020, population2010, population2020,
                        gdpPerCapita2010, gdpPerCapita2020);
                tableView.getItems().add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses the population value from the provided string and returns an integer value.
     *
     * @param populationString The population value as a string
     * @return The parsed population as an integer
     */
    private int parsePopulation(String populationString) {
        // Remove the word 'million' and any whitespace, then parse the remaining value
        String cleanedString = populationString.replaceAll("[^\\d.]", "");
        double populationValue = Double.parseDouble(cleanedString);
        int population = (int) (populationValue * 1_000_000); // Convert to an integer value
        return population;
    }

    /**
     * Shows the bar chart scene when the button is clicked.
     */
    @FXML
    private void showBarChartScene() {
        mainApp.showBarChartScene();
    }

    /**
     * Initializes the controller.
     * This method is automatically called after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
        backButton.setOnAction(event -> showBarChartScene());

        // Set cell value factories for columns
        TableColumn<PopulationData, Double> gdp2010Column = new TableColumn<>("GDP 2010");
        gdp2010Column.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getGdp2010()).asObject());
        gdp2010Column.setCellFactory(column -> new TableCell<PopulationData, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item));
                }
            }
        });

        TableColumn<PopulationData, Double> gdp2020Column = new TableColumn<>("GDP 2020");
        gdp2020Column.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getGdp2020()).asObject());
        gdp2020Column.setCellFactory(column -> new TableCell<PopulationData, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item));
                }
            }
        });

        TableColumn<PopulationData, Integer> population2010Column = new TableColumn<>("Population 2010");
        population2010Column.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPopulation2010()).asObject());
        population2010Column.setCellFactory(column -> new TableCell<PopulationData, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%,d", item));
                }
            }
        });

        TableColumn<PopulationData, Integer> population2020Column = new TableColumn<>("Population 2020");
        population2020Column.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPopulation2020()).asObject());
        population2020Column.setCellFactory(column -> new TableCell<PopulationData, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%,d", item));
                }
            }
        });

        tableView.getColumns().addAll(gdp2010Column, gdp2020Column, population2010Column, population2020Column);
    }

    /**
     * The PopulationData class represents data related to population, GDP, and GDP per capita for a specific country.
     * It provides getter methods to access the data fields.
     * <p>
     * The class includes private data fields to store the population data for different years and the associated GDP and GDP per capita values.
     * <p>
     * The class defines a constructor that initializes the PopulationData object with the provided data.
     * <p>
     * It also provides getter methods for each data field to retrieve the values.
     * <p>
     * Overall, the PopulationData class serves as a data model for population-related data, encapsulating the fields and providing access to the data through getter methods.
     */
    public static class PopulationData {
        private int id;
        private String country;
        private double gdp2010;
        private double gdp2020;
        private int population2010;
        private int population2020;
        private double gdpPerCapita2010;
        private double gdpPerCapita2020;

        /**
         * Constructs a new PopulationData object with the provided data.
         *
         * @param id               The ID of the population data
         * @param country          The name of the country
         * @param gdp2010          The GDP value for the year 2010
         * @param gdp2020          The GDP value for the year 2020
         * @param population2010   The population value for the year 2010
         * @param population2020   The population value for the year 2020
         * @param gdpPerCapita2010 The GDP per capita value for the year 2010
         * @param gdpPerCapita2020 The GDP per capita value for the year 2020
         */
        public PopulationData(int id, String country, double gdp2010, double gdp2020, int population2010, int population2020,
                              double gdpPerCapita2010, double gdpPerCapita2020) {
            this.id = id;
            this.country = country;
            this.gdp2010 = gdp2010;
            this.gdp2020 = gdp2020;
            this.population2010 = population2010;
            this.population2020 = population2020;
            this.gdpPerCapita2010 = gdpPerCapita2010;
            this.gdpPerCapita2020 = gdpPerCapita2020;
        }

        /**
         * Returns the ID of the population data.
         *
         * @return The ID of the population data
         */
        public int getId() {
            return id;
        }

        /**
         * Returns the name of the country.
         *
         * @return The name of the country
         */
        public String getCountry() {
            return country;
        }

        /**
         * Returns the GDP value for the year 2010.
         *
         * @return The GDP value for the year 2010
         */
        public double getGdp2010() {
            return gdp2010;
        }

        /**
         * Returns the GDP value for the year 2020.
         *
         * @return The GDP value for the year 2020
         */
        public double getGdp2020() {
            return gdp2020;
        }

        /**
         * Returns the population value for the year 2010.
         *
         * @return The population value for the year 2010
         */
        public int getPopulation2010() {
            return population2010;
        }

        /**
         * Returns the population value for the year 2020.
         *
         * @return The population value for the year 2020
         */
        public int getPopulation2020() {
            return population2020;
        }

        /**
         * Returns the GDP per capita value for the year 2010.
         *
         * @return The GDP per capita value for the year 2010
         */
        public double getGdpPerCapita2010() {
            return gdpPerCapita2010;
        }

        /**
         * Returns the GDP per capita value for the year 2020.
         *
         * @return The GDP per capita value for the year 2020
         */
        public double getGdpPerCapita2020() {
            return gdpPerCapita2020;
        }

        /**
         * Returns a string representation of the PopulationData object.
         *
         * @return A string representation of the PopulationData object
         */
        @Override
        public String toString() {
            return String.format("PopulationData [id=%d, country=%s, gdp2010=%.2f, gdp2020=%.2f, population2010=%,d, population2020=%,d, gdpPerCapita2010=%.2f, gdpPerCapita2020=%.2f]",
                    id, country, gdp2010, gdp2020, population2010, population2020, gdpPerCapita2010, gdpPerCapita2020);
        }
    }
}