package edu.ntnu.idatt2105.calculator.exceptions;


public class UsernameAlreadyExistsException extends Exception {

  /**
   * Constructor for UsernameAlreadyExistsException.
   * @param message The message to be displayed.
   */
  public UsernameAlreadyExistsException(String message) {
    super(message);
  }

  /**
   * Constructor for UsernameAlreadyExistsException.
   * Has a default message.
   */
  public UsernameAlreadyExistsException() {
    super("A user with this username already exists");
  }
}
