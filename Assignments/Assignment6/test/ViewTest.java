import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ImageController;
import controller.ImageControllerInterface;

import static org.junit.Assert.assertNotNull;

/**
 * Test class for verifying the functionality of the image processing application.
 * Tests include image loading, transformations, and script execution.
 */
public class ViewTest {
  private ImageControllerInterface controller;

  /**
   * Sets up the test environment by initializing the controller and loading test images.
   *
   * @throws IOException if an error occurs during image loading.
   */
  @Before
  public void setUp() throws IOException {
    controller = new ImageController();

    String jpgCommand = "load resources/testImages/jpg/earth.jpg earth";
    String pngCommand = "load resources/testImages/png/balloon.png balloon";
    String ppmCommand = "load resources/testImages/ppm/ppmTest.ppm ppmTest";

    controller.commandParser(jpgCommand);
    controller.commandParser(pngCommand);
    controller.commandParser(ppmCommand);
  }

  /**
   * Tests if images are successfully loaded into the controller.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testImageLoading() throws IOException {
    assertNotNull(controller.getImages().get("earth"));
    assertNotNull(controller.getImages().get("balloon"));
    assertNotNull(controller.getImages().get("ppmTest"));
  }

  /**
   * Tests the horizontal flip transformation of an image.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testHorizontalFlip() throws IOException {
    String flipCommand = "horizontal-flip earth earth-horizontal";
    controller.commandParser(flipCommand);
    assertNotNull(controller.getImages().get("earth-horizontal"));
  }

  /**
   * Tests the vertical flip transformation of an image.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testVerticalFlip() throws IOException {
    String flipCommand = "horizontal-flip earth earth-vertical";
    controller.commandParser(flipCommand);
    assertNotNull(controller.getImages().get("earth-vertical"));
  }

  /**
   * Tests image compression functionality.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCompress() throws IOException {
    String compressCommand = "compress 95 earth earth-compressed";
    controller.commandParser(compressCommand);
    assertNotNull(controller.getImages().get("earth-compressed"));
  }

  /**
   * Tests the blur effect on an image.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testBlur() throws IOException {
    String blurCommand = "blur earth earth-blur";
    controller.commandParser(blurCommand);
    assertNotNull(controller.getImages().get("earth-blur"));
  }

  /**
   * Tests the sharpen effect on an image.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testSharpen() throws IOException {
    String sharpenCommand = "sharpen earth earth-sharpen";
    controller.commandParser(sharpenCommand);
    assertNotNull(controller.getImages().get("earth-sharpen"));
  }

  /**
   * Tests the greyscale transformation on an image.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testGreyscale() throws IOException {
    String greyscaleCommand = "luma-component earth earth-greyscale";
    controller.commandParser(greyscaleCommand);
    assertNotNull(controller.getImages().get("earth-greyscale"));
  }

  /**
   * Tests the sepia tone effect on an image.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testSepia() throws IOException {
    String sepiaCommand = "sepia earth earth-sepia";
    controller.commandParser(sepiaCommand);
    assertNotNull(controller.getImages().get("earth-sepia"));
  }

  /**
   * Tests color correction on an image.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testColorCorrect() throws IOException {
    String colorCommand = "color-correct earth earth-color-corrected";
    controller.commandParser(colorCommand);
    assertNotNull(controller.getImages().get("earth-color-corrected"));
  }

  /**
   * Tests levels adjustment on an image.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testLevelsAdjust() throws IOException {
    String levelsAdjustCommand = "levels-adjust 50 100 150 earth earth-levels-adjusted";
    controller.commandParser(levelsAdjustCommand);
    assertNotNull(controller.getImages().get("earth-levels-adjusted"));
  }

  @Test
  public void testDownscaling() throws IOException {
    String downscaleCommand = "downscale 100 100 earth earth-downscaled";
    controller.commandParser(downscaleCommand);
    assertNotNull(controller.getImages().get("earth-downscaled"));
  }

  /**
   * Tests the execution of a script file containing multiple commands.
   *
   * @throws IOException if an error occurs during script execution.
   */
  @Test
  public void testScriptFile() throws IOException {
    String scriptFile = "run resources/scripts/script.txt";
    controller.commandParser(scriptFile);

    assertNotNull(controller.getImages().get("earth"));

    assertNotNull(controller.getImages().get("earthRed"));
    assertNotNull(controller.getImages().get("earthGreen"));
    assertNotNull(controller.getImages().get("earthBlue"));
    assertNotNull(controller.getImages().get("earthValue"));
    assertNotNull(controller.getImages().get("earthLuma"));
    assertNotNull(controller.getImages().get("earthIntensity"));

    assertNotNull(controller.getImages().get("earth-h"));
    assertNotNull(controller.getImages().get("earth-v"));
    assertNotNull(controller.getImages().get("earthRedBrightened"));

    assertNotNull(controller.getImages().get("red-earth"));
    assertNotNull(controller.getImages().get("green-earth"));
    assertNotNull(controller.getImages().get("blue-earth"));

    assertNotNull(controller.getImages().get("earth-combine"));

    assertNotNull(controller.getImages().get("earthBlur"));
    assertNotNull(controller.getImages().get("earthSharpen"));

    assertNotNull(controller.getImages().get("earthCompressed"));

    assertNotNull(controller.getImages().get("earthSepia"));

    assertNotNull(controller.getImages().get("earthHistogram"));

    assertNotNull(controller.getImages().get("earthColorCorrected"));

    assertNotNull(controller.getImages().get("earthLevelsAdjusted"));
  }
}