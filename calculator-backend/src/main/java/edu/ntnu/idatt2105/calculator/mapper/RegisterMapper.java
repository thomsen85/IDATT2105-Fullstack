package edu.ntnu.idatt2105.calculator.mapper;

import edu.ntnu.idatt2105.calculator.dto.RegisterDTO;
import edu.ntnu.idatt2105.calculator.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface RegisterMapper {
  RegisterMapper INSTANCE = Mappers.getMapper(RegisterMapper.class);

  User registerDTOtoUser(RegisterDTO registerDTO);
}
