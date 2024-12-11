import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.io.Reader;

import controller.Controller;
import controller.ControllerInterface;

import mocks.MockModel;
import mocks.MockView;

import static org.junit.Assert.assertTrue;

/**
 * A class that tests the Controller class.
 */
public class ControllerTest {
  private MockModel mockModel;
  private MockView mockView;
  private ControllerInterface controller;

  @Before
  public void setUp() throws IOException {
    new File("resources/sampleImages").mkdirs();

    createTestImage("resources/sampleImages/earth.jpg");

    mockModel = new MockModel();
    mockView = new MockView();

    Readable input = new StringReader("");
    String[] args = new String[0];
    controller = new Controller(mockModel, input, args, mockView);
    mockView.getMessages().clear();
  }

  private void createTestImage(String path) throws IOException {
    BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    File outputfile = new File(path);
    String extension = path.substring(path.lastIndexOf('.') + 1);
    ImageIO.write(img, extension, outputfile);
  }

  @Test
  public void testBrightenCommand() {
    controller.executeCommand("load resources/sampleImages/statue-of-unity.jpg image1");
    controller.executeCommand("brighten 10 image1 brightenedImage");
    controller.executeCommand("save resources/brightenedImage.png brightenedImage");


    assertTrue(mockView.containsMessage("Command executed successfully."));
    assertTrue(mockModel.hasImage("brightenedImage"));
  }

  @Test
  public void testLoadCommandWithInvalidPath() {
    controller.executeCommand("load invalid/path.png image1");
    assertTrue(mockView.containsMessage("Error: Error executing command: Unable to load " +
        "image from invalid/path.png: Failed to read image: invalid/path.png"));
  }

  @Test
  public void testUnknownCommand() {
    controller.executeCommand("unknowncommand");
    assertTrue(mockView.containsMessage("Unknown command: unknowncommand"));
  }

  @Test
  public void testImageLoadingAndSavingWithoutOperations() {
    controller.executeCommand("load resources/sampleImages/losless-test.png original");
    controller.executeCommand("save resources/original_image_saved.png original");

    assertTrue(mockView.containsMessage("Command executed successfully."));
  }

  @Test
  public void testErrorHandlingScript() throws IOException {
    Reader scriptReader = new FileReader("resources/scripts/error_handling_test.txt");
    controller.runScript(scriptReader);

    assertTrue(mockView.containsMessage(
            "Error running script: Error executing command in script: Error executing command: " +
                "Unable to load image from resources/sampleImages/nonexistent.png: " +
                "Failed to read image: resources/sampleImages/nonexistent.png"));
  }

  @Test
  public void testMultipleImagesScript() {
    controller.executeCommand("load resources/sampleImages/manhattan.jpg manhattan");
    controller.executeCommand("load resources/sampleImages/random.jpg random");
    controller.executeCommand("sepia manhattan manhattan-sepia");
    controller.executeCommand("sepia random random-sepia");
    controller.executeCommand("red-component manhattan manhattan-red");
    controller.executeCommand("blue-component random random-blue");
    controller.executeCommand("green-component random random-green");
    controller.executeCommand("rgb-combine combined-image manhattan-red random-green random-blue");

    assertTrue(mockModel.hasImage("manhattan-sepia"));
    assertTrue(mockModel.hasImage("random-sepia"));
    assertTrue(mockModel.hasImage("manhattan-red"));
    assertTrue(mockModel.hasImage("random-green"));
    assertTrue(mockModel.hasImage("random-blue"));
    assertTrue(mockModel.hasImage("combined-image"));
  }

  @Test
  public void testRunScript() throws IOException {
    Reader scriptReader = new FileReader("resources/scripts/error_handling_test.txt");
    controller.runScript(scriptReader);

    assertTrue(mockView.containsMessage("Error running script: Error executing command in " +
        "script: Error executing command: Unable to load image from resources" +
        "/sampleImages/nonexistent.png: Failed to read image: resources/" +
        "sampleImages/nonexistent.png"));
  }

  @Test
  public void testBasicOperationsScript() throws IOException {
    Reader scriptReader = new FileReader("resources/scripts/basic_operations_test.txt");
    controller.runScript(scriptReader);

    assertTrue(mockView.containsMessage("Width: 640 pixels\n" +
            "Height: 427 pixels\n" +
            "Max Color Value: 255\n"));

    assertTrue(mockModel.hasImage("manhattan-rotated"));
    assertTrue(mockModel.hasImage("manhattan-bright"));
    assertTrue(mockModel.hasImage("manhattan-vertical"));
  }

  @Test
  public void testNestedScript() throws IOException {
    Reader scriptReader = new FileReader("resources/scripts/nested_script_test.txt");
    controller.runScript(scriptReader);

    assertTrue(mockModel.hasImage("manhattan-blur"));
    assertTrue(mockModel.hasImage("manhattan-blur-sepia"));
  }

  @Test
  public void testRgbSplitAndCombineScript() throws IOException {
    Reader scriptReader = new FileReader("resources/scripts/rgb_split_combine_test.txt");
    controller.runScript(scriptReader);

    assertTrue(mockModel.hasImage("random"));
    assertTrue(mockModel.hasImage("random-red"));
    assertTrue(mockModel.hasImage("random-green"));
    assertTrue(mockModel.hasImage("random-blue"));
    assertTrue(mockModel.hasImage("random-red-tinted"));
    assertTrue(mockModel.hasImage("random-red-bright"));
  }

  @Test
  public void testmultipleOperationsScript() throws IOException {
    Reader scriptReader = new FileReader("resources/scripts/advanced_operations_test.txt");
    controller.runScript(scriptReader);

    assertTrue(mockModel.hasImage("manhattan-sharpen"));
    assertTrue(mockModel.hasImage("manhattan-blur"));
    assertTrue(mockModel.hasImage("manhattan-sharpen-flipped"));
    assertTrue(mockModel.hasImage("manhattan-blur-bright"));
    assertTrue(mockModel.hasImage("manhattan-sharpen-flipped"));
    assertTrue(mockModel.hasImage("manhattan"));
  }

  @Test
  public void testAdvancedOperationsScript() throws IOException {
    Reader scriptReader =
            new FileReader("resources/scripts/AdvancedOperationTestScript.txt");
    controller.runScript(scriptReader);


    assertTrue(mockModel.hasImage("sample-histogram"));
    assertTrue(mockModel.hasImage("sample-compressed"));
    assertTrue(mockModel.hasImage("sample-color-corrected"));
    assertTrue(mockModel.hasImage("sample-levels-adjusted"));
    assertTrue(mockModel.hasImage("sample-blurred"));
    assertTrue(mockModel.hasImage("sample-image"));
  }

  @Test
  public void testChainedOperationsScript() throws IOException {
    Reader scriptReader = new FileReader("resources/scripts/chained_operations_test.txt");
    controller.runScript(scriptReader);

    assertTrue(mockModel.hasImage("manhattan-blur-sepia"));
    assertTrue(mockModel.hasImage("manhattan-flipped-bright"));
    assertTrue(mockModel.hasImage("manhattan"));
    assertTrue(mockModel.hasImage("manhattan-blur"));
    assertTrue(mockModel.hasImage("manhattan-blur-sepia"));
    assertTrue(mockModel.hasImage("manhattan-flipped-bright"));
  }

  @Test
  public void testComponentAndColorTransformationScript() throws IOException {
    Reader scriptReader = new FileReader(
            "resources/scripts/component_and_color_transformation_test.txt");
    controller.runScript(scriptReader);

    assertTrue(mockModel.hasImage("random-red"));
    assertTrue(mockModel.hasImage("random-green"));
    assertTrue(mockModel.hasImage("random-blue"));
    assertTrue(mockModel.hasImage("random-value"));
    assertTrue(mockModel.hasImage("random-luma"));
    assertTrue(mockModel.hasImage("random-intensity"));
  }

  @Test
  public void testFullProcessingPipelineScript() throws IOException {
    Reader scriptReader = new FileReader("resources/scripts/full_processing_pipeline.txt");
    controller.runScript(scriptReader);

    assertTrue(mockModel.hasImage("random-blur"));
    assertTrue(mockModel.hasImage("random-blur-sharpened"));
    assertTrue(mockModel.hasImage("random-grayscale"));
    assertTrue(mockModel.hasImage("random-sepia"));
    assertTrue(mockModel.hasImage("random-sepia-bright"));
    assertTrue(mockModel.hasImage("random-sepia-bright-flipped"));
  }

  @Test
  public void testFullWorkflowScript() throws IOException {
    Reader scriptReader = new FileReader("resources/scripts/full_workflow_test.txt");
    controller.runScript(scriptReader);

    assertTrue(mockModel.hasImage("random-sharpened"));
    assertTrue(mockModel.hasImage("random-luma"));
    assertTrue(mockModel.hasImage("random-luma-tinted"));
    assertTrue(mockModel.hasImage("luma-red"));
    assertTrue(mockModel.hasImage("luma-green"));
    assertTrue(mockModel.hasImage("luma-blue"));
  }

  @Test
  public void testDownscaleCommand() {
    controller.executeCommand("load resources/sampleImages/manhattan.jpg manhattan");
    controller.executeCommand("downscale 50 50 manhattan manhattan-downscaled");
    controller.executeCommand("save resources/manhattan-downscaled.jpg manhattan-downscaled");

    assertTrue(mockModel.hasImage("manhattan-downscaled"));
    assertTrue(mockView.containsMessage("Command executed successfully."));
  }

  @Test
  public void testAdjustLevelsCommand() {
    controller.executeCommand("load resources/sampleImages/random.jpg random");
    controller.executeCommand("levels-adjust 0 128 255 random random-levels ");
    controller.executeCommand("save resources/random-levels.jpg random-levels");

    assertTrue(mockModel.hasImage("random-levels"));
    assertTrue(mockView.containsMessage("Command executed successfully."));
  }

  @Test
  public void testSplitViewCommand() {
    controller.executeCommand("load resources/sampleImages/random.jpg random");
    controller.executeCommand("blur random random-split split 50");
    controller.executeCommand("save resources/random-split.jpg random-split");

    assertTrue(mockModel.hasImage("random-split"));
    assertTrue(mockView.containsMessage("Command executed successfully."));
  }

  @Test
  public void testCompressCommand() {
    controller.executeCommand("load resources/sampleImages/statue-of-unity-test.jpg statue");
    controller.executeCommand("compress 75 statue statue-compressed");
    controller.executeCommand("save resources/statue-compressed.jpg statue-compressed");

    assertTrue(mockModel.hasImage("statue-compressed"));
    assertTrue(mockView.containsMessage("Command executed successfully."));
  }


  @Test
  public void testSplitCommand() {
    controller.executeCommand("load resources/sampleImages/random.jpg random");
    controller.executeCommand("rgb-split random random-red random-green random-blue");
    controller.executeCommand("save resources/random-red.jpg random-red");
    controller.executeCommand("save resources/random-green.jpg random-green");
    controller.executeCommand("save resources/random-blue.jpg random-blue");

    assertTrue(mockModel.hasImage("random-red"));
    assertTrue(mockModel.hasImage("random-green"));
    assertTrue(mockModel.hasImage("random-blue"));
    assertTrue(mockView.containsMessage("Command executed successfully."));
  }

  @Test
  public void testDitherCommand() {
    controller.executeCommand("load sampleImages/earth.jpg earth");
    controller.executeCommand("dither earth earth-dither");
    assertTrue(mockModel.hasImage("earth-dither"));
  }

  @Test
  public void testDitherCommandWithSplit() {
    controller.executeCommand("load sampleImages/earth.jpg earth split 50");
    controller.executeCommand("dither earth earth-dither-split");
    assertTrue(mockModel.hasImage("earth-dither-split"));
  }
}
