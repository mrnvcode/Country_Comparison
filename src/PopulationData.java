import javafx.beans.binding.BooleanExpression;

/**

 The PopulationData class represents data related to population, GDP, and GDP per capita for a specific country.

 It provides getter methods to access the data fields.
 */
public class PopulationData {
    private int id; // Unique identifier for the data
    private String country; // Country name
    private double gdp2010; // GDP value for the year 2010
    private double gdp2020; // GDP value for the year 2020
    private int population2010; // Population value for the year 2010
    private int population2020; // Population value for the year 2020
    private double gdpPerCapita2010; // GDP per capita value for the year 2010
    private double gdpPerCapita2020; // GDP per capita value for the year 2020

    /**

     Constructs a PopulationData object with the specified data.
     @param id The unique identifier for the data
     @param country The name of the country
     @param gdp2010 The GDP value for the year 2010
     @param gdp2020 The GDP value for the year 2020
     @param population2010 The population value for the year 2010
     @param population2020 The population value for the year 2020
     @param gdpPerCapita2010 The GDP per capita value for the year 2010
     @param gdpPerCapita2020 The GDP per capita value for the year 2020
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

     Returns the unique identifier of the data.
     @return The unique identifier
     */
    public int getId() {
        return id;
    }
    /**

     Returns the name of the country.
     @return The country name
     */
    public String getCountry() {
        return country;
    }
    /**

     Returns the GDP value for the year 2010.
     @return The GDP value for the year 2010
     */
    public double getGdp2010() {
        return gdp2010;
    }
    /**

     Returns the GDP value for the year 2020.
     @return The GDP value for the year 2020
     */
    public double getGdp2020() {
        return gdp2020;
    }
    /**

     Returns the population value for the year 2010.
     @return The population value for the year 2010
     */
    public int getPopulation2010() {
        return population2010;
    }
    /**

     Returns the population value for the year 2020.
     @return The population value for the year 2020
     */
    public int getPopulation2020() {
        return population2020;
    }
    /**

     Returns the GDP per capita value for the year 2010.
     @return The GDP per capita value for the year 2010
     */
    public double getGdpPerCapita2010() {
        return gdpPerCapita2010;
    }
    /**

     Returns the GDP per capita value for the year 2020.
     @return The GDP per capita value for the year 2020
     */
    public double getGdpPerCapita2020() {
        return gdpPerCapita2020;
    }
}