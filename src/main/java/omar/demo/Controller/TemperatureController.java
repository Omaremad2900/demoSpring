package omar.demo.Controller;


import omar.demo.Service.IService.ITempertureConversionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class TemperatureController {

    private final ITempertureConversionService conversionService;

    

    @GetMapping("convert/celsiusToFahrenheit")
    public double celsiusToFahrenheit(@RequestParam double value) {
        return conversionService.celsiusToFahrenheit(value);
    }

    @GetMapping("/convert/fahrenheitToCelsius")
    public double fahrenheitToCelsius(@RequestParam double value) {
        return conversionService.fahrenheitToCelsius(value);
    }

    @GetMapping("/convert/celsiusToKelvin/{value}")
    public double celsiusToKelvin(@PathVariable double value) {
        return conversionService.celsiusToKelvin(value);
    }

    @GetMapping("/convert/kelvinToCelsius")
    public double kelvinToCelsius(@RequestParam double value) {
        return conversionService.kelvinToCelsius(value);
    }

    @GetMapping("/convert/fahrenheitToKelvin")
    public double fahrenheitToKelvin(@RequestParam double value) {
        return conversionService.fahrenheitToKelvin(value);
    }

    @GetMapping("/convert/kelvinToFahrenheit")
    public double kelvinToFahrenheit(@RequestParam double value) {
        return conversionService.kelvinToFahrenheit(value);
    }
}