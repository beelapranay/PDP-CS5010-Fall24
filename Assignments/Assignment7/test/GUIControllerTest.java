import controller.GUIController;
import model.ImageModelInterface;
import mocks.MockGUIView;
import mocks.MockModel;
import model.image.ImageInterface;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Comprehensive test class for GUIController with 20 edge cases and scenarios.
 */
public class GUIControllerTest {
  private ImageModelInterface modelImageStore;
  private MockGUIView view;
  private GUIController controller;

  @Before
  public void setUp() {
    modelImageStore = new MockModel();
    view = new MockGUIView();
    controller = new GUIController(modelImageStore);
    controller.setView(view);
    view.setController(controller);
  }

  @Test
  public void testLoadImage() {
    view.openLoadDialog();
    assertNotNull(modelImageStore.getImage("manhattan"));
    assertTrue(view.getActions().contains("openLoadDialog"));
  }

  @Test
  public void testSaveImage() {
    view.openLoadDialog();
    view.openSaveDialog();
    assertTrue(view.getActions().contains("openSaveDialog"));
    assertTrue(view.getMessages().contains("Image saved successfully."));
  }

  @Test
  public void testSaveWithoutImageLoaded() {
    view.setCurrentImageName(null);
    view.openSaveDialog();
    assertTrue(view.getMessages().contains("No image is currently displayed."));
  }

  @Test
  public void testApplyBlurOperation() {
    view.openLoadDialog();
    controller.applyBlur("manhattan", "manhattan_blurred");
    assertNotNull(modelImageStore.getImage("manhattan_blurred"));
    assertTrue(view.getMessages().contains("Blur operation applied"));
  }

  @Test
  public void testApplyBrightenOperation() {
    view.openLoadDialog();
    controller.applyBrighten("manhattan", 50,
        "manhattan_brightened");
    assertNotNull(modelImageStore.getImage("manhattan_brightened"));
    assertTrue(view.getMessages().contains("Image brightened"));
  }

  @Test
  public void testBrightenInvalidInput() {
    view.openLoadDialog();
    try {
      controller.applyBrighten("manhattan", Integer.parseInt("invalid"),
          "manhattan_brightened");
    } catch (NumberFormatException e) {
      view.displayMessage("Invalid input: " + e.getMessage());
    }
    assertTrue(view.getMessages().contains("Invalid input: For input string: \"invalid\""));
  }

  @Test
  public void testApplySharpenOperation() {
    view.openLoadDialog();
    controller.applySharpen("manhattan", "manhattan_sharpened");
    assertNotNull(modelImageStore.getImage("manhattan_sharpened"));
    assertTrue(view.getMessages().contains("Sharpen operation applied"));
  }

  @Test
  public void testApplyCompressOperation() {
    view.openLoadDialog();
    controller.applyCompress("manhattan", 50,
        "manhattan_compressed");
    assertNotNull(modelImageStore.getImage("manhattan_compressed"));
    assertTrue(view.getMessages().contains("Image compressed"));
  }

  @Test
  public void testCompressInvalidInput() {
    view.openLoadDialog();
    try {
      controller.applyCompress("manhattan", -10,
          "manhattan_compressed");
    } catch (IllegalArgumentException e) {
      view.displayMessage("Invalid input: Compression percentage must be between 0 and 100.");
    }
    assertTrue(view.getMessages().contains("Error compressing image: " +
            "Percentage must be between 0 and 100."));
  }

  @Test
  public void testGenerateHistogram() {
    view.openLoadDialog();
    ImageInterface histogram = controller.generateHistogramImage("manhattan");
    assertNotNull(histogram);
    assertTrue(view.getActions().contains("displayImage: manhattan"));
  }

  @Test
  public void testGenerateHistogramForNullImage() {
    view.setCurrentImageName(null);
    ImageInterface histogram = controller.generateHistogramImage(view.getCurrentImageName());
    assertNull(histogram);
  }

  @Test
  public void testApplyHorizontalFlip() {
    view.openLoadDialog();
    controller.applyHorizontalFlip("manhattan", "manhattan_flipped_horizontal");
    assertNotNull(modelImageStore.getImage("manhattan_flipped_horizontal"));
    assertTrue(view.getMessages().contains("Horizontal flip applied"));
  }

  @Test
  public void testApplyVerticalFlip() {
    view.openLoadDialog();
    controller.applyVerticalFlip("manhattan", "manhattan_flipped_vertical");
    assertNotNull(modelImageStore.getImage("manhattan_flipped_vertical"));
    assertTrue(view.getMessages().contains("Vertical flip applied"));
  }

  @Test
  public void testApplyDither() {
    view.openLoadDialog();
    controller.applyVerticalFlip("earth", "earth_dither");
    assertNotNull(modelImageStore.getImage("earth_dither"));
    assertTrue(view.getMessages().contains("Dither applied"));
  }

  @Test
  public void testApplySepia() {
    view.openLoadDialog();
    controller.applySepia("manhattan", "manhattan_sepia");
    assertNotNull(modelImageStore.getImage("manhattan_sepia"));
    assertTrue(view.getMessages().contains("Sepia tone applied"));
  }

  @Test
  public void testApplyLumaComponent() {
    view.openLoadDialog();
    controller.applyLumaComponent("manhattan", "manhattan_luma");
    assertNotNull(modelImageStore.getImage("manhattan_luma"));
    assertTrue(view.getMessages().contains("luma component extracted"));
  }

  @Test
  public void testApplyRedComponent() {
    view.openLoadDialog();
    controller.applyRedComponent("manhattan", "manhattan_red");
    assertNotNull(modelImageStore.getImage("manhattan_red"));
    assertTrue(view.getMessages().contains("red component extracted"));
  }

  @Test
  public void testInvalidOperation() {
    view.openLoadDialog();
    try {
      controller.applyCompress("manhattan", 200,
          "invalid_image");
    } catch (IllegalArgumentException e) {
      view.displayMessage("Invalid operation: " + e.getMessage());
    }
    assertTrue(view.getMessages().contains("Error compressing image: " +
            "Percentage must be between 0 and 100."));
  }

  @Test
  public void testNullImageHandling() {
    view.setCurrentImageName(null);
    controller.applyBlur("null_image", "null_image_blurred");
    assertNull(modelImageStore.getImage("null_image_blurred"));
    assertTrue(view.getMessages().contains("Error blurring image: Error executing command:" +
        " Image not found: null_image"));
  }

  @Test
  public void testLoadNonExistentImage() {
    try {
      controller.loadImage("non_existent.jpg", "nonExistent");
    } catch (IllegalArgumentException e) {
      view.displayMessage("Error loading image: " + e.getMessage());
    }
    assertTrue(view.getMessages().contains("Error loading image: Error executing command: " +
        "Unable to load image from non_existent.jpg: Failed to read image: non_existent.jpg"));
  }

  @Test
  public void testRemoveImage() {
    view.openLoadDialog();
    controller.removeImage("manhattan");
    assertNull(modelImageStore.getImage("manhattan"));
    assertTrue(view.getActions().contains("displayImage: manhattan"));
  }

  @Test
  public void testAdjustLevelsDescendingBMW() {
    view.openLoadDialog();
    try {
      controller.applyLevelsAdjust("manhattan", 200, 150,
          100, "manhattan_levels_adjusted");
    } catch (IllegalArgumentException e) {
      view.displayMessage("Invalid levels: " + e.getMessage());
    }
    // The image should not be created due to invalid input
    assertNull(modelImageStore.getImage("manhattan_levels_adjusted"));
    assertTrue(view.getMessages().contains("Error adjusting levels: Invalid black," +
        " mid, and white points."));
  }

  @Test
  public void testSplitViewNegativePercentage() {
    view.openLoadDialog();
    try {
      // Attempt to generate a split view with a negative percentage
      ImageInterface originalImage = modelImageStore.getImage("manhattan");
      controller.getSplitViewImage(originalImage, -50, "blur");
    } catch (IllegalArgumentException e) {
      view.displayMessage("Invalid split percentage: " + e.getMessage());
    }
    // Since the split percentage is invalid, no image should be returned
    assertTrue(view.getMessages().contains("Error creating split view image: Split" +
        " position must be between 0 and 100."));
  }

  @Test
  public void testDownscaleInvalidDimensions() {
    view.openLoadDialog();
    try {
      controller.applyDownscale("manhattan", -100, -200, "manhattan_downscaled");
    } catch (IllegalArgumentException e) {
      view.displayMessage("Invalid downscale dimensions: " + e.getMessage());
    }
    assertNull(modelImageStore.getImage("manhattan_downscaled"));
    assertTrue(view.getMessages().contains("Error downscaling image: Error executing command:" +
        " Target width and height must be positive integers."));
  }


}