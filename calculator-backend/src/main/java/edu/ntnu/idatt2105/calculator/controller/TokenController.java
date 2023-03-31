package edu.ntnu.idatt2105.calculator.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import edu.ntnu.idatt2105.calculator.dto.AuthenticateDTO;
import edu.ntnu.idatt2105.calculator.exceptions.UserDoesNotExistsException;
import edu.ntnu.idatt2105.calculator.model.User;
import edu.ntnu.idatt2105.calculator.service.UserService;
import java.time.Duration;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(value = "/api/v1/public/token")
@EnableAutoConfiguration
@RequiredArgsConstructor
public class TokenController {

  private final UserService userService;

  private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);

  // keyStr is hardcoded here for testing purpose
  // in a real scenario, it should either be stored in a database or injected from the environment
  public static final String JWT_TOKEN_SECRET = "JWT_TOKEN_SECRET";

  private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(5);

  /**
   * Generate a JWT token for the given user.
   * @param authenticate The user to generate a token for.
   * @return The generated token.
   * @throws UserDoesNotExistsException if the user does not exist.
   * @throws BadCredentialsException if the user credentials are wrong.
   */
  @PostMapping(value = "")
  @ResponseStatus(value = HttpStatus.CREATED)
  public String generateToken(@RequestBody AuthenticateDTO authenticate)
      throws UserDoesNotExistsException, BadCredentialsException, ResponseStatusException {

    LOGGER.info("Authenticating user: {}", authenticate.getUsername());

    if (userService.authenticateUser(authenticate.getUsername(), authenticate.getPassword())) {
      LOGGER.info("User authenticated: {}", authenticate.getUsername());
      User user = userService.getUserByUsername(authenticate.getUsername());
      return generateToken(user);
    }

    LOGGER.info("Wrong credentials: {}", authenticate.getUsername());
    throw new BadCredentialsException("Access denied, wrong credentials...");
  }

  public String generateToken(final User user) {
    final Instant now = Instant.now();
    final Algorithm hmac512 = Algorithm.HMAC512(JWT_TOKEN_SECRET);

    return JWT
        .create()
        .withSubject(user.getUsername())
        .withIssuer("idatt2105_calculator")
        .withIssuedAt(now)
        .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
        .sign(hmac512);
  }
}
