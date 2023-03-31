package edu.ntnu.idatt2105.calculator.service;

import edu.ntnu.idatt2105.calculator.model.Calculation;
import edu.ntnu.idatt2105.calculator.repository.CalculationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

  @Autowired
  private CalculationRepository calculationRepository;


  @Override
  public Calculation saveCalculation(Calculation calculation) {
    return calculationRepository.save(calculation);
  }

  @Override
  public List<Calculation> getCalculationByUser(String username) {
    return calculationRepository.findAllByUserUsername(username);
  }
}
