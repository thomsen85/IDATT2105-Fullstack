package edu.ntnu.idatt2105.calculator.service;

import edu.ntnu.idatt2105.calculator.model.Calculation;
import java.util.List;

public interface CalculationService {
  Calculation saveCalculation(Calculation calculation);
  List<Calculation> getCalculationByUser(String username);
}
