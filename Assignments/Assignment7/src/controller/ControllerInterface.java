package controller;

import java.io.IOException;

/**
 * Interface for the Controller in an MVC application.
 * This interface defines the main operations that any controller implementation must provide.
 * The controller is responsible for handling user input and executing commands.
 * It interacts with the view to display output and with the image processor to process images.
 * The controller can run in interactive mode or execute commands from a script file.
 */
public interface ControllerInterface {

  /**
   * Starts the controller, which may involve initializing the view and other components.
   */
  void start();

  /**
   * Executes a given command by parsing and processing the input.
   *
   * @param command the user input command
   */
  void executeCommand(String command);

  /**
   * Runs a script from a file path, executing each command in sequence.
   *
   * @param scriptInput readable input stream for the script file
   * @throws IOException if the file cannot be read
   */
  void runScript(Readable scriptInput) throws IOException;

}