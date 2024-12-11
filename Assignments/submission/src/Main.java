import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.ImageController;

/**
 * Main class for writing main function to test running out code.
 */
public class Main {
  /**
   * Main method that accepts user input to run commands.
   *
   * @param args the program arguments
   */
  public static void main(String[] args) {
    ImageController controller = new ImageController();
    int flag = 1;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    while (flag == 1) {
      System.out.println("Enter a command or 'run' followed by a file path:");
      try {
        String command = reader.readLine();
        controller.commandParser(command);
        System.out.println("Do you want to continue (y/n)?");

        if (reader.readLine().equals("n")) {
          flag = 0;
          System.out.println("Exiting...");
        }
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
