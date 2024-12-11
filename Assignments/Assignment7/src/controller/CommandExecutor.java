package controller;


import controller.commands.Command;
import controller.commands.BlurCommand;
import controller.commands.BrightenCommand;
import controller.commands.ColorCorrectCommand;
import controller.commands.ComponentCommand;
import controller.commands.CompressCommand;
import controller.commands.DetailsCommand;
import controller.commands.DitheringCommand;
import controller.commands.DownscaleCommand;
import controller.commands.FlipCommand;
import controller.commands.HistogramCommand;
import controller.commands.LevelsAdjustCommand;
import controller.commands.LoadCommand;
import controller.commands.RGBCombineCommand;
import controller.commands.RGBSplitCommand;
import controller.commands.SaveCommand;
import controller.commands.SepiaCommand;
import controller.commands.SharpenCommand;

import model.ImageModelInterface;
import model.operationimpls.AdvancedImageProcessorImpl;
import model.operationinterface.AdvancedImageProcessor;
import view.View;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * CommandExecutor handles the execution of commands without relying on any view.
 * It throws exceptions when errors occur, allowing controllers to handle them appropriately.
 * It also provides the ability to run scripts and execute commands from a readable input.
 */
public class CommandExecutor {
  private final ImageModelInterface model;
  private final AdvancedImageProcessor imageProcessor;
  private final Map<String, Function<String[], Command>> knownCommands;
  private View view;

  /**
   * Constructs a CommandExecutor with the given model.
   *
   * @param model the image model to apply commands
   */
  public CommandExecutor(ImageModelInterface model) {
    this.model = model;
    this.imageProcessor = new AdvancedImageProcessorImpl();
    this.knownCommands = new HashMap<>();
    initializeCommands();
  }

  /**
   * Constructs a CommandExecutor with the given model and view.
   *
   * @param model the image model to apply commands
   * @param view  the view to display messages
   */
  public CommandExecutor(ImageModelInterface model, View view) {
    this.model = model;
    this.view = view;
    this.imageProcessor = new AdvancedImageProcessorImpl();
    this.knownCommands = new HashMap<>();
    initializeCommands();
  }

  /**
   * Executes a given command by parsing and processing the input.
   *
   * @param commandLine the user input command
   */
  public void executeCommand(String commandLine) {
    String[] tokens = commandLine.trim().split("\\s+");
    if (tokens.length == 0) {
      throw new IllegalArgumentException("No command entered.");
    }
    String commandName = tokens[0].toLowerCase();

    if (commandName.equals("exit")) {
      System.exit(0);
    } else if (commandName.equals("run")) {
      if (tokens.length != 2) {
        throw new IllegalArgumentException("Usage: run script-file");
      }
      String scriptPath = tokens[1];
      try (Reader scriptReader = new FileReader(scriptPath)) {
        runScript(scriptReader);
      } catch (IOException e) {
        throw new RuntimeException("Error running script: " + e.getMessage(), e);
      }
      return;
    }

    Function<String[], Command> commandFunction = knownCommands.get(commandName);
    if (commandFunction == null) {
      throw new IllegalArgumentException("Unknown command: " + commandName);
    }
    Command cmd = commandFunction.apply(tokens);
    try {
      cmd.execute();
    } catch (Exception e) {
      throw new RuntimeException("Error executing command: " + e.getMessage(), e);
    }
  }

  /**
   * Runs a script from a readable input.
   *
   * @param scriptInput the readable script input
   * @throws RuntimeException if an error occurs during script execution
   */
  public void runScript(Readable scriptInput) {
    try (Scanner scanner = new Scanner(scriptInput)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();
        if (!line.isEmpty() && !line.startsWith("#")) {
          try {
            executeCommand(line);
          } catch (Exception e) {
            throw new RuntimeException("Error executing command in script: " + e.getMessage(), e);
          }
        }
      }
    }
  }

  /**
   * Initializes the known commands for the executor.
   */
  private void initializeCommands() {
    // Load and Save Commands
    knownCommands.put("load", tokens -> new LoadCommand(tokens, model));
    knownCommands.put("save", tokens -> new SaveCommand(tokens, model));

    // Details Command
    knownCommands.put("details", tokens -> new DetailsCommand(tokens, model, view));

    // Image Processing Commands
    knownCommands.put("blur", tokens -> new BlurCommand(tokens, imageProcessor, model));
    knownCommands.put("brighten", tokens -> new BrightenCommand(tokens, imageProcessor, model));
    knownCommands.put("sharpen", tokens -> new SharpenCommand(tokens, imageProcessor, model));
    knownCommands.put("sepia", tokens -> new SepiaCommand(tokens, imageProcessor, model));

    knownCommands.put("horizontal-flip",
        tokens -> new FlipCommand(tokens, true, imageProcessor, model));
    knownCommands.put("vertical-flip",
        tokens -> new FlipCommand(tokens, false, imageProcessor, model));

    // Component Commands
    Function<String[], Command> componentCommand =
        tokens -> new ComponentCommand(tokens, imageProcessor, model);
    knownCommands.put("red-component", componentCommand);
    knownCommands.put("green-component", componentCommand);
    knownCommands.put("blue-component", componentCommand);
    knownCommands.put("luma-component", componentCommand);
    knownCommands.put("intensity-component", componentCommand);
    knownCommands.put("value-component", componentCommand);

    knownCommands.put("rgb-combine",
        tokens -> new RGBCombineCommand(tokens, imageProcessor, model));
    knownCommands.put("rgb-split",
        tokens -> new RGBSplitCommand(tokens, imageProcessor, model));

    // Additional Commands
    knownCommands.put("color-correct",
        tokens -> new ColorCorrectCommand(tokens, imageProcessor, model));
    knownCommands.put("levels-adjust",
        tokens -> new LevelsAdjustCommand(tokens, imageProcessor, model));
    knownCommands.put("compress",
        tokens -> new CompressCommand(tokens, imageProcessor, model));
    knownCommands.put("histogram",
        tokens -> new HistogramCommand(tokens, imageProcessor, model));
    knownCommands.put("downscale",
        tokens -> new DownscaleCommand(tokens, imageProcessor, model));
    knownCommands.put("dither",
        tokens -> new DitheringCommand(tokens, imageProcessor, model));
  }
}