import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

import controller.ImageController;
import model.ImageModel;
import model.RGB;

public class ImageModelTest {
  private ImageModel model;
  private ImageController controller;

  @Before
  public void setUp() {
    controller = new ImageController();
    model = new ImageModel(controller);
  }

  @Test
  public void testApplySepiaTonePPMImage() throws IOException {
    controller.loadImage("images/testppm.ppm", "ppmImage");

    model.applySepiaTone("ppmImage", "sepiaPPMImage");

    assertTrue(controller.getImages().containsKey("sepiaPPMImage"));

    RGB[][] sepiaImage = controller.getImages().get("sepiaPPMImage");
    RGB[][] originalImage = controller.getImages().get("ppmImage");

    for (int y = 0; y < sepiaImage.length; y++) {
      for (int x = 0; x < sepiaImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        // Manual computation of expected sepia values based on the formula
        int expectedRed = (int) Math.min(0.393 * originalPixel.red + 0.769 * originalPixel.green + 0.189 * originalPixel.blue, 255);
        int expectedGreen = (int) Math.min(0.349 * originalPixel.red + 0.686 * originalPixel.green + 0.168 * originalPixel.blue, 255);
        int expectedBlue = (int) Math.min(0.272 * originalPixel.red + 0.534 * originalPixel.green + 0.131 * originalPixel.blue, 255);

        // Check if the sepiaImage has the expected values
        assertEquals(expectedRed, sepiaImage[y][x].red);
        assertEquals(expectedGreen, sepiaImage[y][x].green);
        assertEquals(expectedBlue, sepiaImage[y][x].blue);
      }
    }
  }


  @Test(expected = NoSuchElementException.class)
  public void testApplySepiaToneNoSuchImage() {
    model.applySepiaTone("nonexistent", "sepia-nonexistent");
  }
}
