package edu.ntnu.idatt2105.calculator.controller;

import edu.ntnu.idatt2105.calculator.dto.CalculationAnswerDTO;
import edu.ntnu.idatt2105.calculator.dto.CalculationDTO;
import edu.ntnu.idatt2105.calculator.mapper.CalculationMapper;
import edu.ntnu.idatt2105.calculator.model.Calculation;
import edu.ntnu.idatt2105.calculator.service.CalculationService;
import edu.ntnu.idatt2105.calculator.service.CalculationServiceImpl;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/private/calculation")
@EnableAutoConfiguration
@RequiredArgsConstructor
public class CalculationController {

  Logger LOGGER = LoggerFactory.getLogger(CalculationController.class);

  @Autowired
  private CalculationService calculationService;

  @Autowired
  private CalculationMapper calculationMapper;


  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CalculationAnswerDTO> createCalculation(@RequestBody CalculationDTO calculationDTO, @AuthenticationPrincipal String username) {
    LOGGER.info("POST request for calculation: {}", calculationDTO);

    Calculation calculation = calculationMapper.calculationDTOToCalculation(calculationDTO, username);
    LOGGER.info("Mapped calculationDTO to calculation: {}", calculation);

    calculation = calculationService.saveCalculation(calculation);
    LOGGER.info("Saved calculation: {}", calculation);

    CalculationAnswerDTO calculationAnswerDTO = calculationMapper.calculationToCalculationAnswerDTO(calculation);
    LOGGER.info("Mapped calculation to calculationDTO: {}", calculationAnswerDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(calculationAnswerDTO);
  }

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Iterable<CalculationAnswerDTO>> getAllCalculations(@AuthenticationPrincipal String username) {
    LOGGER.info("GET request for all calculations");

    Iterable<Calculation> calculations = calculationService.getCalculationByUser(username);
    LOGGER.info("Got all calculations: {}", calculations);

    Iterable<CalculationAnswerDTO> calculationAnswerDTOs = StreamSupport.stream(calculations.spliterator(), false).map(calculationMapper::calculationToCalculationAnswerDTO).toList();
    LOGGER.info("Mapped calculations to calculationDTOs: {}", calculationAnswerDTOs);

    return ResponseEntity.status(HttpStatus.OK).body(calculationAnswerDTOs);
  }
}
