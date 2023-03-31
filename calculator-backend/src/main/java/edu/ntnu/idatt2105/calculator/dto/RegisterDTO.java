package edu.ntnu.idatt2105.calculator.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegisterDTO {

  @NonNull
  @NotBlank
  private String username;

  @NonNull
  @NotBlank
  private String email;

  @NonNull
  @NotBlank
  private String firstName;

  @NonNull
  @NotBlank
  private String lastName;

  @NonNull
  @NotBlank
  private String password;
}
