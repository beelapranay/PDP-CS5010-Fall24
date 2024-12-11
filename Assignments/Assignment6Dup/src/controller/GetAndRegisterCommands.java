package controller;

/**
 * Interface for registering and retrieving commands in a command registry.
 */
public interface GetAndRegisterCommands {

  /**
   * Registers a command with a specific name.
   *
   * @param commandName the name of the command to register
   * @param command     the command instance to associate with the given name
   */
  void register(String commandName, controller.Command command);

  /**
   * Retrieves a command by its registered name.
   *
   * @param commandName the name of the command to retrieve
   * @return the command associated with the given name, or null if no such command exists
   */
  Command get(String commandName);
}
