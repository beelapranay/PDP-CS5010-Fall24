import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ImageController;

/**
 * This class contains JUnit test cases for the ImageController class.
 * It verifies the image loading, saving, and transformation functionalities.
 */
public class ImageControllerTest {

  private ImageController controller;

  /**
   * Sets up the ImageController instance before each test.
   */
  @Before
  public void setUp() {
    controller = new ImageController();
  }

  /**
   * Tests loading a JPG image.
   *
   * @throws IOException if an error occurs while loading the image.
   */
  @Test
  public void testLoadingJPGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");
    assertTrue(controller.getImages().containsKey("earth"));
  }

  /**
   * Tests loading a PNG image.
   *
   * @throws IOException if an error occurs while loading the image.
   */
  @Test
  public void testLoadingPNGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "square");
    assertTrue(controller.getImages().containsKey("square"));
  }

  /**
   * Tests loading a PPM image.
   *
   * @throws IOException if an error occurs while loading the image.
   */
  @Test
  public void testLoadingPPMImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/3x2.ppm", "3x2");
    assertTrue(controller.getImages().containsKey("3x2"));
  }

  /**
   * Tests saving a PPM image as a JPG image.
   *
   * @throws IOException if an error occurs while saving the image.
   */
  @Test
  public void testSavingPPMImageAsJPGImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/3x2.ppm", "3x2");
    controller.saveImage("resources/res/jpgRes/3x2PPMAsJPGImage.jpg", "3x2");
    assertTrue(controller.getImages().containsKey("3x2"));
  }

  /**
   * Tests saving a PPM image as a PNG image.
   *
   * @throws IOException if an error occurs while saving the image.
   */
  @Test
  public void testSavingPPMImageAsPNGImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/3x2.ppm", "3x2");
    controller.saveImage("resources/res/pngRes/3x2PPMAsPNGImage.png", "3x2");
    assertTrue(controller.getImages().containsKey("3x2"));
  }

  /**
   * Tests saving a JPG image as a PNG image.
   *
   * @throws IOException if an error occurs while saving the image.
   */
  @Test
  public void testSavingJPGImageAsPNGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");
    controller.saveImage("resources/res/pngRes/earthJPGAsPNG.png", "earth");
    assertTrue(controller.getImages().containsKey("earth"));
  }

  /**
   * Tests saving a JPG image as a PPM image.
   *
   * @throws IOException if an error occurs while saving the image.
   */
  @Test
  public void testSavingJPGImageAsPPMImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");
    controller.saveImage("resources/res/ppmRes/earthJPGAsPPM.ppm", "earth");
    assertTrue(controller.getImages().containsKey("earth"));
  }

  /**
   * Tests saving a PNG image as a JPG image.
   *
   * @throws IOException if an error occurs while saving the image.
   */
  @Test
  public void testSavingPNGImageAsJPGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "square");
    controller.saveImage("resources/res/jpgRes/squarePNGAsJPG.jpg", "square");
    assertTrue(controller.getImages().containsKey("square"));
  }

  /**
   * Tests saving a PNG image as a PPM image.
   *
   * @throws IOException if an error occurs while saving the image.
   */
  @Test
  public void testSavingPNGImageAsPPMImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "square");
    controller.saveImage("resources/res/ppmRes/squarePNGAsPPM.ppm", "square");
    assertTrue(controller.getImages().containsKey("square"));
  }

  /**
   * Tests command parser functionality for loading images.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserLoadingImage() throws IOException {
    String command = "load resources/testImages/jpg/earth.jpg earth";
    controller.commandParser(command);
    assertTrue(controller.getImages().containsKey("earth"));
  }

  /**
   * Tests command parser functionality for red component split.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserRedSplitImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");
    String command = "red-component earth earth-red";
    controller.commandParser(command);
    assertTrue(controller.getImages().containsKey("earth-red"));
  }

  /**
   * Tests command parser functionality for green component split.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserGreenSplitImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");
    String command = "green-component earth earth-green";
    controller.commandParser(command);
    assertTrue(controller.getImages().containsKey("earth-green"));
  }

  /**
   * Tests command parser functionality for blue component split.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserBlueSplitImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");
    String command = "blue-component earth earth-blue";
    controller.commandParser(command);
    assertTrue(controller.getImages().containsKey("earth-blue"));
  }

  /**
   * Tests command parser functionality for value component.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserValueImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");
    String command = "value-component earth earth-value";
    controller.commandParser(command);
    assertTrue(controller.getImages().containsKey("earth-value"));
  }

  /**
   * Tests command parser functionality for luma component.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserLumaImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");
    String command = "luma-component earth earth-luma";
    controller.commandParser(command);
    assertTrue(controller.getImages().containsKey("earth-luma"));
  }

  /**
   * Tests command parser functionality for intensity component.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserIntensityImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");
    String command = "intensity-component earth earth-intensity";
    controller.commandParser(command);
    assertTrue(controller.getImages().containsKey("earth-intensity"));
  }

  /**
   * Tests command parser functionality for horizontal flip.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserHFlipImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");
    String command = "horizontal-flip earth earth-hflip";
    controller.commandParser(command);
    assertTrue(controller.getImages().containsKey("earth-hflip"));
  }

  /**
   * Tests command parser functionality for vertical flip.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserVFlipImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");
    String command = "vertical-flip earth earth-vflip";
    controller.commandParser(command);
    assertTrue(controller.getImages().containsKey("earth-vflip"));
  }

  /**
   * Tests command parser functionality for brightening an image.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserBrightenDarkenImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");
    String command = "brighten 50 earth earth-bright";
    controller.commandParser(command);
    assertTrue(controller.getImages().containsKey("earth-bright"));
  }

  /**
   * Tests command parser functionality for RGB splitting.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserRGBSplitImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");
    String command = "rgb-split earth earth-red earth-green earth-blue";
    controller.commandParser(command);
    assertTrue(controller.getImages().containsKey("earth-red"));
    assertTrue(controller.getImages().containsKey("earth-green"));
    assertTrue(controller.getImages().containsKey("earth-blue"));
  }

  /**
   * Tests command parser functionality for RGB combining.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserRGBCombineImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");

    String splitCommand = "rgb-split earth earth-red earth-green earth-blue";
    String combineCommand = "rgb-combine earth-combined earth-red earth-green earth-blue";

    controller.commandParser(splitCommand);
    controller.commandParser(combineCommand);

    assertTrue(controller.getImages().containsKey("earth-combined"));
  }

  /**
   * Tests command parser functionality for blurring an image.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserBlurImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");

    String command = "blur earth earth-blur";

    controller.commandParser(command);

    assertTrue(controller.getImages().containsKey("earth-blur"));
  }

  /**
   * Tests command parser functionality for sharpening an image.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserSharpenImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");

    String command = "sharpen earth earth-sharpen";

    controller.commandParser(command);

    assertTrue(controller.getImages().containsKey("earth-sharpen"));
  }

  /**
   * Tests command parser functionality for applying sepia tone to an image.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserSepiaImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");

    String command = "sepia earth earth-sepia";

    controller.commandParser(command);

    assertTrue(controller.getImages().containsKey("earth-sepia"));
  }

  /**
   * Tests command parser functionality for running a script.
   *
   * @throws IOException if an error occurs during command execution.
   */
  @Test
  public void testCommandParserRunFile() throws IOException {
    String command = "run resources/scripts/script.txt";

    controller.commandParser(command);

    assertTrue(controller.getImages().containsKey("man"));
  }
}