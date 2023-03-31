package edu.ntnu.idatt2105.calculator.mapper;

import edu.ntnu.idatt2105.calculator.dto.UserDTO;
import edu.ntnu.idatt2105.calculator.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  /**
   * Maps a User to a UserDTO.
   * Ignores the password field.
   * @param user The User to map.
   * @return The mapped UserDTO.
   */
  UserDTO userToUserDTO(User user);
}
