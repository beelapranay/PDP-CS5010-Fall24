package controller;

import java.io.IOException;

/**
 * Represents a command that can be executed within the image manipulation framework.
 */
public interface Command {

  /**
   * Executes the command based on the provided tokens.
   *
   * @param tokens an array of strings representing the arguments required for the command
   * @throws IOException if an error occurs during command execution
   */
  void execute(String[] tokens) throws IOException;
}
