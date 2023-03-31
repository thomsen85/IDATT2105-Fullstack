package edu.ntnu.idatt2105.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticateDTO {

  @NonNull
  private String username;

  @NonNull
  private String password;
}
