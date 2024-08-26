package omar.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import omar.demo.Service.IService.ITempertureConversionService;
@Service
public class TemperatureConversionService implements ITempertureConversionService {

    public double celsiusToFahrenheit(double celsius) {
        validateCelsius(celsius);
        return (celsius * 9/5) + 32;
    }

    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    public double celsiusToKelvin(double celsius) {
        validateCelsius(celsius);
        return celsius + 273.15;
    }

    public double kelvinToCelsius(double kelvin) {
        validateKelvin(kelvin);
        return kelvin - 273.15;
    }

    public double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * 5/9 + 273.15;
    }

    public double kelvinToFahrenheit(double kelvin) {
        validateKelvin(kelvin);
        return (kelvin - 273.15) * 9/5 + 32;
    }

    private void validateCelsius(double celsius) {
        Assert.isTrue(celsius >= -273.15, "Celsius cannot be lower than absolute zero (-273.15°C)");
    }

    private void validateKelvin(double kelvin) {
        Assert.isTrue(kelvin >= 0, "Kelvin cannot be lower than absolute zero (0K)");
    }

}
