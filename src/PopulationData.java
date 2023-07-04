import javafx.beans.binding.BooleanExpression;

public class PopulationData {
    private int id;
    private String country;
    private double gdp2010;
    private double gdp2020;
    private int population2010;
    private int population2020;
    private double gdpPerCapita2010;
    private double gdpPerCapita2020;

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

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public double getGdp2010() {
        return gdp2010;
    }

    public double getGdp2020() {
        return gdp2020;
    }

    public int getPopulation2010() {
        return population2010;
    }

    public int getPopulation2020() {
        return population2020;
    }

    public double getGdpPerCapita2010() {
        return gdpPerCapita2010;
    }

    public double getGdpPerCapita2020() {
        return gdpPerCapita2020;
    }
}
