package edu.ntnu.idatt2105.calculator.service;


import edu.ntnu.idatt2105.calculator.exceptions.DatabaseException;
import edu.ntnu.idatt2105.calculator.exceptions.EmailAlreadyExistsException;
import edu.ntnu.idatt2105.calculator.exceptions.UserDoesNotExistsException;
import edu.ntnu.idatt2105.calculator.exceptions.UsernameAlreadyExistsException;
import edu.ntnu.idatt2105.calculator.model.User;
import java.util.List;
import java.util.Set;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
  public boolean usernameExists(String username) throws NullPointerException;

  public boolean emailExists(String email) throws NullPointerException;

  public User getUserByUsername(String username) throws UserDoesNotExistsException;

  public User getUserByEmail(String email) throws UserDoesNotExistsException;

  public User saveUser(User user)
      throws UsernameAlreadyExistsException, EmailAlreadyExistsException, DatabaseException;

  public void deleteUser(User user) throws DatabaseException;

  public void deleteUserByUsername(String username) throws UserDoesNotExistsException;

  public void deleteUserByEmail(String email) throws UserDoesNotExistsException;

  public User partialUpdate(
      User user,
      String email,
      String firstName,
      String lastName,
      String oldPassword,
      String newPassword
  ) throws UserDoesNotExistsException, BadCredentialsException;


  public boolean authenticateUser(String username, String password)
      throws UserDoesNotExistsException, BadCredentialsException;
}
