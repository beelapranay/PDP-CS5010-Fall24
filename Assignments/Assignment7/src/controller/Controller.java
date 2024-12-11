package controller;

import model.ImageModelInterface;
import view.View;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 * Controller class for the text-based image processing application.
 */
public class Controller implements ControllerInterface {
  private final View view;
  private final CommandExecutor commandExecutor;
  private final Readable input;
  private final String[] args;

  /**
   * Constructs a Controller with the given model, input, arguments, and view.
   *
   * @param model the image processing model
   * @param input the input stream for commands
   * @param args  the command line arguments
   * @param view  the view for displaying messages
   */
  public Controller(ImageModelInterface model, Readable input, String[] args, View view) {
    this.input = input;
    this.args = args;
    this.view = view;
    this.commandExecutor = new CommandExecutor(model, view);
  }

  @Override
  public void start() {
    if (args != null && args.length >= 2 && args[0].equals("-file")) {
      String scriptPath = args[1];
      try {
        runScriptFile(scriptPath);
      } catch (IOException e) {
        view.displayMessage("Error running script file: " + e.getMessage());
      }
    } else {
      startInteractiveMode();
    }
  }

  /**
   * Starts the interactive mode, reading commands from the user input.
   */
  private void startInteractiveMode() {
    Scanner scanner = new Scanner(this.input);
    view.displayMessage("Welcome to the Image Processing Application!");
    view.displayMessage("Enter commands (type 'exit' to quit):");

    while (true) {
      view.displayMessage("> ");
      String commandLine;
      if (scanner.hasNextLine()) {
        commandLine = scanner.nextLine().trim();
      } else {
        break;
      }

      if (commandLine.equalsIgnoreCase("exit")) {
        view.displayMessage("Exiting the application.");
        break;
      }
      if (!commandLine.isEmpty()) {
        executeCommand(commandLine);
      }
    }
    scanner.close();
  }

  @Override
  public void executeCommand(String commandLine) {
    try {
      commandExecutor.executeCommand(commandLine);
      view.displayMessage("Command executed successfully.");
    } catch (Exception e) {
      view.displayMessage("Error: " + e.getMessage());
    }
  }

  @Override
  public void runScript(Readable scriptInput) throws RuntimeException {
    try {
      commandExecutor.runScript(scriptInput);
    } catch (RuntimeException e) {
      view.displayMessage("Error running script: " + e.getMessage());
    }
  }

  /**
   * Runs a script file by reading commands from the specified file path.
   *
   * @param scriptPath the path to the script file
   * @throws IOException if an error occurs while reading the file
   */
  private void runScriptFile(String scriptPath) throws IOException {
    try (Reader scriptReader = new FileReader(scriptPath)) {
      runScript(scriptReader);
    }
  }
}