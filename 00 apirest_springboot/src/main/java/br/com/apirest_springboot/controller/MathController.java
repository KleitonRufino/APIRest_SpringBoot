package br.com.apirest_springboot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.apirest_springboot.exception.UnsuportedMathOperationException;

@RestController
public class MathController {

	// path params
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value");
		}
		Double sum = convertToDouble(numberOne) + convertToDouble(numberTwo);
		return sum;
	}

	@RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double subtraction(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		Double subtract = convertToDouble(numberOne) - convertToDouble(numberTwo);
		return subtract;
	}

	@RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double multiplication(@PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		Double mult = convertToDouble(numberOne) * convertToDouble(numberTwo);
		return mult;
	}

	@RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		Double div = convertToDouble(numberOne) / convertToDouble(numberTwo);
		return div;
	}

	@RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		Double mean = (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
		return mean;
	}

	@RequestMapping(value = "/squareRoot/{number}", method = RequestMethod.GET)
	public Double squareRoot(@PathVariable("number") String number) throws Exception {
		if (!isNumeric(number)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		Double sqrt = (Double) Math.sqrt(convertToDouble(number));
		return sqrt;
	}

	private Double convertToDouble(String strNumber) {
		if (strNumber == null)
			return 0d;
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(number))
			return Double.parseDouble(number);
		return 0d;
	}

	private boolean isNumeric(String strNumber) {
		if (strNumber == null)
			return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

}
