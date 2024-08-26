package omar.demo.Service.IService;

public interface ITempertureConversionService {
    double celsiusToFahrenheit(double celsius);

    double fahrenheitToCelsius(double fahrenheit);

    double celsiusToKelvin(double celsius);

    double kelvinToCelsius(double kelvin);

    double fahrenheitToKelvin(double fahrenheit);

    double kelvinToFahrenheit(double kelvin);
}
