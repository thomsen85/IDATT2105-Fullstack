package edu.ntnu.idatt2105.calculator.exceptions;


public class UserDoesNotExistsException extends Exception {

  /**
   * Constructor for UserDoesNotExistsException.
   * @param message The message to be displayed.
   */
  public UserDoesNotExistsException(String message) {
    super(message);
  }

  /**
   * Constructor for UserDoesNotExistsException.
   * Has a default message.
   */
  public UserDoesNotExistsException() {
    super("User does not exist");
  }
}
