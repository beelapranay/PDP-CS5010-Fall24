package controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for managing and retrieving commands in the image manipulation framework.
 */
public class CommandRegistry implements GetAndRegisterCommands {

  private final Map<String, controller.Command> commands = new HashMap<>();

  /**
   * Registers a command with the given name.
   *
   * @param commandName the name of the command
   * @param command the command to register
   */
  public void register(String commandName, controller.Command command) {
    commands.put(commandName, command);
  }

  /**
   * Retrieves a registered command by its name.
   *
   * @param commandName the name of the command
   * @return the registered command, or null if no command is registered with the given name
   */
  public Command get(String commandName) {
    return commands.get(commandName);
  }
}
