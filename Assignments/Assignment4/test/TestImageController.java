import org.junit.Test;

import java.io.IOException;

import controller.ImageController;
import controller.ImageControllerInterface;

import static org.junit.Assert.assertTrue;

public class TestImageController {

  @Test
  public void testLoadJPGImage() {
    ImageControllerInterface controller = new ImageController();

    try {
      controller.commandParser("load images/earth.jpg earth");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    assertTrue(controller.getImages().containsKey("earth"));
  }

  @Test
  public void testLoadPNGImage() {
    ImageControllerInterface controller = new ImageController();

    try {
      controller.commandParser("load images/square.png square");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    assertTrue(controller.getImages().containsKey("square"));
  }

  @Test
  public void testLoadPPMImage() {
    ImageControllerInterface controller = new ImageController();

    try {
      controller.commandParser("load images/colorP3File.ppm colorP3File");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    assertTrue(controller.getImages().containsKey("colorP3File"));
  }

  @Test
  public void testSaveJPGImage() {
    ImageControllerInterface controller = new ImageController();


  }
}
