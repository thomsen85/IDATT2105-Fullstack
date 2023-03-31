package edu.ntnu.idatt2105.calculator.exceptions;


public class EmailAlreadyExistsException extends Exception {

  /**
   * Constructor for EmailAlreadyExistsException.
   * @param message The message to be displayed.
   */
  public EmailAlreadyExistsException(String message) {
    super(message);
  }

  /**
   * Constructor for EmailAlreadyExistsException.
   * Has a default message.
   */
  public EmailAlreadyExistsException() {
    super("Email already exists");
  }
}
