package edu.ntnu.idatt2105.calculator.mapper;

import edu.ntnu.idatt2105.calculator.dto.CalculationAnswerDTO;
import edu.ntnu.idatt2105.calculator.dto.CalculationDTO;
import edu.ntnu.idatt2105.calculator.exceptions.UserDoesNotExistsException;
import edu.ntnu.idatt2105.calculator.model.Calculation;
import edu.ntnu.idatt2105.calculator.model.User;
import edu.ntnu.idatt2105.calculator.service.UserService;
import lombok.RequiredArgsConstructor;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public abstract class CalculationMapper {

  public static CalculationMapper INSTANCE = Mappers.getMapper(CalculationMapper.class);

  private static final Logger LOGGER = LoggerFactory.getLogger(CalculationMapper.class);

  @Autowired
  private UserService userService;

  public abstract CalculationDTO calculationsToCalculationAnswerDTOs(Calculation calculation);

  @Named("getUser")
  public User getUser(String username) throws UserDoesNotExistsException {
    LOGGER.info("Getting user with username: {}", username);
    return userService.getUserByUsername(username);
  }

  @Mappings({
      @Mapping(target = "answer", source = "calculationDTO", qualifiedByName = "getAnswer"),
      @Mapping(target = "user", source = "username", qualifiedByName = "getUser")
  })
  public abstract Calculation calculationDTOToCalculation(CalculationDTO calculationDTO, String username);

  public abstract CalculationAnswerDTO calculationToCalculationAnswerDTO(Calculation calculation);

  @Named("getAnswer")
  public String getAnswer(CalculationDTO calculation) {
    LOGGER.info("Getting answer for calculation: {}", calculation);
    Expression expression = new ExpressionBuilder(calculation.getCalculation()).build();
    if (expression.validate().isValid()) {
      String answer = String.valueOf(expression.evaluate());
      LOGGER.info("Answer for calculation: {}", answer);
      return answer;
    }
    return "Invalid expression";
  }
}
