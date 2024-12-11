package main;

import controller.Controller;
import controller.ControllerInterface;
import controller.GUIController;
import model.ImageModelImpl;
import model.ImageModelInterface;
import view.ImageProcessingGUIView;
import view.TextBasedView;
import view.View;

import java.io.InputStreamReader;

/**
 * The main class for the image processing application.
 * This class is responsible for creating the controller and view objects,
 * and running the application.
 * The application can be run in three modes:
 * Interactive GUI mode: The user can interact with the application through the graphical interface.
 * Script mode: The application reads a script file and executes the commands in the file.
 * Text mode: The user interacts with the application through the console.
 * Usage: java -jar Program.jar [-file script-path] [-text]
 * -file script-path: Optional argument to specify a script file to execute.
 * -text: Runs the application in text mode.
 * If no arguments are provided, the application runs in GUI mode.
 * Example: java -jar Program.jar -file script.txt
 * Example: java -jar Program.jar -text
 * Example: java -jar Program.jar
 */
public class ImageProcessingApplication {

  /**
   * The main method for the image processing application.
   * This method creates the controller and view objects, and runs the application.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    ImageModelInterface model = new ImageModelImpl();

    if (args != null && args.length >= 2 && args[0].equals("-file")) {
      // Script mode
      runTextBasedMode(model, new InputStreamReader(System.in), args);
    } else if (args != null && args.length >= 1 && args[0].equals("-text")) {
      // Text mode
      runTextBasedMode(model, new InputStreamReader(System.in), args);
    } else {
      // GUI mode (default)
      runGUIMode(model);
    }
  }

  /**
   * Runs the application in text-based mode (interactive or script).
   *
   * @param model the image processing model
   * @param input the input stream for commands
   * @param args  the command line arguments
   */
  private static void runTextBasedMode(ImageModelInterface model, Readable input, String[] args) {
    View view = new TextBasedView();
    ControllerInterface controller = new Controller(model, input, args, view);
    controller.start();
  }

  /**
   * Runs the application in GUI mode.
   *
   * @param model the image processing model
   */
  private static void runGUIMode(ImageModelInterface model) {
    ImageProcessingGUIView guiView = new ImageProcessingGUIView();
    GUIController guiController = new GUIController(model);
    guiController.setView(guiView);
    guiView.setController(guiController);
  }
}