import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.SwingUtilities;

import controller.ImageController;
import view.ImageViewerWithController;

/**
 * Entry point for the program. Determines the mode of execution based on the provided arguments:
 * GUI mode, script execution, or text-based interactive mode.
 */
public class Main {

  /**
   * Main method to launch the program in one of three modes: GUI, script execution, or text mode.
   *
   * @param args Command-line arguments to specify the execution mode.
   */
  public static void main(String[] args) {
    ImageController controller = new ImageController();

    if (args.length == 0) {
      System.out.println("Launching GUI...");
      launchGUI(controller);
    } else if (args.length == 2 && args[0].equals("-file")) {
      System.out.println("Executing script from file: " + args[1]);
      try {
        controller.commandParser("run " + args[1]);
        System.out.println("Script execution completed.");
      } catch (Exception e) {
        System.err.println("Error executing script: " + e.getMessage());
      }
    } else if (args.length == 1 && args[0].equals("-text")) {
      System.out.println("Launching text-based interactive mode...");
      launchTextMode(controller);
    } else {
      System.out.println("Invalid arguments! Usage:");
      System.out.println("  java -jar Program.jar              : Launch GUI mode");
      System.out.println("  java -jar Program.jar -file <path> : Run script file");
      System.out.println("  java -jar Program.jar -text        : Launch text mode");
      System.exit(1);
    }
  }

  /**
   * Launches the GUI for image editing.
   *
   * @param controller The ImageController instance to handle commands.
   */
  private static void launchGUI(ImageController controller) {
    SwingUtilities.invokeLater(() -> {
      ImageViewerWithController viewer = new ImageViewerWithController(controller);
      viewer.setVisible(true);
    });
  }

  /**
   * Launches a text-based interactive mode for entering commands.
   *
   * @param controller The ImageController instance to handle commands.
   */
  private static void launchTextMode(ImageController controller) {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      while (true) {
        System.out.println("Enter a command (or type 'exit' to quit):");
        String command = reader.readLine();

        if (command.equalsIgnoreCase("exit")) {
          System.out.println("Exiting...");
          break;
        }

        try {
          controller.commandParser(command);
        } catch (Exception e) {
          System.err.println("Error: " + e.getMessage());
        }
      }
    } catch (IOException e) {
      System.err.println("Error reading input: " + e.getMessage());
    }
  }
}