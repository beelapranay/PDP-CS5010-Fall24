import org.junit.Before;
import org.junit.Test;

import controller.ImageController;
import model.ImageModel;
import model.RGB;
import view.ImageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Unit tests for testing level adjustment functionality on images.
 */
public class LevelAdjustTest {
  private ImageController controller;
  private ImageModel model;
  private int splitPercentage;

  @Before
  public void setUp() {
    splitPercentage = 100;
    controller = new ImageController();
    ImageView view = new ImageView(controller);
    model = new ImageModel(controller, view);
  }

  // Invalid level adjustment cases
  @Test
  public void testInvalidLevelsAdjust_bNegative() {
    assertThrows("Expected IllegalArgumentException for b < 0",
            IllegalArgumentException.class, () -> model.levelsAdjust("testImage",
                    "destImage", -1, 100, 200, splitPercentage));
  }

  @Test
  public void testInvalidLevelsAdjust_bEqualToM() {
    assertThrows("Expected IllegalArgumentException for b >= m",
            IllegalArgumentException.class, () -> model.levelsAdjust("testImage",
                    "destImage", 100, 100, 200, splitPercentage));
  }

  @Test
  public void testInvalidLevelsAdjust_mGreaterThanW() {
    assertThrows("Expected IllegalArgumentException for m >= w",
            IllegalArgumentException.class, () -> model.levelsAdjust("testImage",
                    "destImage", 50, 200, 150, splitPercentage));
  }

  @Test
  public void testInvalidLevelsAdjust_wGreaterThan255() {
    assertThrows("Expected IllegalArgumentException for w > 255",
            IllegalArgumentException.class, () -> model.levelsAdjust("testImage",
                    "destImage", 50, 100, 256, splitPercentage));
  }

  // Test grayscale gradient
  @Test
  public void testGrayscaleGradientLevelsAdjust() {
    RGB[][] grayscaleGradient = {
            {new RGB(50, 50, 50), new RGB(100, 100, 100)},
            {new RGB(150, 150, 150), new RGB(200, 200, 200)}
    };
    controller.getImages().put("grayscaleGradient", grayscaleGradient);

    RGB[][] expectedAdjustedImage = new RGB[2][2];
    for (int y = 0; y < grayscaleGradient.length; y++) {
      for (int x = 0; x < grayscaleGradient[0].length; x++) {
        int adjustedIntensity = applyQuadraticFormula(grayscaleGradient[y][x].red, 20, 128,
                230);
        expectedAdjustedImage[y][x] = new RGB(adjustedIntensity, adjustedIntensity,
                adjustedIntensity);
      }
    }

    model.levelsAdjust("grayscaleGradient", "adjustedGrayscaleGradient",
            20, 128, 230, splitPercentage);
    RGB[][] adjustedImage = controller.getImages().get("adjustedGrayscaleGradient");

    for (int y = 0; y < expectedAdjustedImage.length; y++) {
      for (int x = 0; x < expectedAdjustedImage[0].length; x++) {
        assertEquals(expectedAdjustedImage[y][x].red, adjustedImage[y][x].red);
        assertEquals(expectedAdjustedImage[y][x].green, adjustedImage[y][x].green);
        assertEquals(expectedAdjustedImage[y][x].blue, adjustedImage[y][x].blue);
      }
    }
  }

  // Max intensity
  @Test
  public void testMaxIntensityLevelsAdjust() {
    RGB[][] maxIntensity = {{new RGB(255, 255, 255), new RGB(255, 255,
            255)}};
    controller.getImages().put("maxIntensity", maxIntensity);

    RGB[][] expectedAdjustedImage = {{new RGB(255, 255, 255), new RGB(255,
            255, 255)}};
    model.levelsAdjust("maxIntensity", "adjustedMaxIntensity",
            20, 128, 230, splitPercentage);
    RGB[][] adjustedImage = controller.getImages().get("adjustedMaxIntensity");

    for (int y = 0; y < expectedAdjustedImage.length; y++) {
      for (int x = 0; x < expectedAdjustedImage[0].length; x++) {
        assertEquals(expectedAdjustedImage[y][x].red, adjustedImage[y][x].red);
        assertEquals(expectedAdjustedImage[y][x].green, adjustedImage[y][x].green);
        assertEquals(expectedAdjustedImage[y][x].blue, adjustedImage[y][x].blue);
      }
    }
  }

  // Min intensity
  @Test
  public void testMinIntensityLevelsAdjust() {
    RGB[][] minIntensity = {{new RGB(0, 0, 0), new RGB(0,
            0, 0)}};
    controller.getImages().put("minIntensity", minIntensity);

    RGB[][] expectedAdjustedImage = {{new RGB(0, 0, 0), new RGB(0, 0,
            0)}};
    model.levelsAdjust("minIntensity", "adjustedMinIntensity",
            20, 128, 230, splitPercentage);
    RGB[][] adjustedImage = controller.getImages().get("adjustedMinIntensity");

    for (int y = 0; y < expectedAdjustedImage.length; y++) {
      for (int x = 0; x < expectedAdjustedImage[0].length; x++) {
        assertEquals(expectedAdjustedImage[y][x].red, adjustedImage[y][x].red);
        assertEquals(expectedAdjustedImage[y][x].green, adjustedImage[y][x].green);
        assertEquals(expectedAdjustedImage[y][x].blue, adjustedImage[y][x].blue);
      }
    }
  }

  // Non-uniform image
  @Test
  public void testNonUniformLevelsAdjust() {
    RGB[][] nonUniformImage = {
            {new RGB(20, 50, 100), new RGB(80, 130, 200)},
            {new RGB(150, 180, 220), new RGB(230, 240, 250)}
    };
    controller.getImages().put("nonUniformImage", nonUniformImage);

    RGB[][] expectedAdjustedImage = new RGB[2][2];
    for (int y = 0; y < nonUniformImage.length; y++) {
      for (int x = 0; x < nonUniformImage[0].length; x++) {
        int adjustedRed = applyQuadraticFormula(nonUniformImage[y][x].red, 20, 128, 230);
        int adjustedGreen = applyQuadraticFormula(nonUniformImage[y][x].green, 20, 128, 230);
        int adjustedBlue = applyQuadraticFormula(nonUniformImage[y][x].blue, 20, 128, 230);
        expectedAdjustedImage[y][x] = new RGB(adjustedRed, adjustedGreen, adjustedBlue);
      }
    }

    model.levelsAdjust("nonUniformImage", "adjustedNonUniformImage",
            20, 128, 230, splitPercentage);
    RGB[][] adjustedImage = controller.getImages().get("adjustedNonUniformImage");

    for (int y = 0; y < expectedAdjustedImage.length; y++) {
      for (int x = 0; x < expectedAdjustedImage[0].length; x++) {
        assertEquals(expectedAdjustedImage[y][x].red, adjustedImage[y][x].red);
        assertEquals(expectedAdjustedImage[y][x].green, adjustedImage[y][x].green);
        assertEquals(expectedAdjustedImage[y][x].blue, adjustedImage[y][x].blue);
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOrderOfBmwThrowsException() {
    RGB[][] image = {{new RGB(100, 100, 100), new RGB(100, 100, 100)}};
    controller.getImages().put("invalidImage", image);
    model.levelsAdjust("invalidImage", "outputImage",
            150, 100, 50, splitPercentage);
  }

  // Narrow range
  @Test
  public void testNarrowRangeLevelsAdjust() {
    RGB[][] narrowRangeImage = {
            {new RGB(30, 30, 30), new RGB(40, 40, 40)},
            {new RGB(50, 50, 50), new RGB(60, 60, 60)}
    };
    controller.getImages().put("narrowRangeImage", narrowRangeImage);

    RGB[][] expectedAdjustedImage = new RGB[2][2];
    for (int y = 0; y < narrowRangeImage.length; y++) {
      for (int x = 0; x < narrowRangeImage[0].length; x++) {
        int adjustedIntensity = applyQuadraticFormula(narrowRangeImage[y][x].red, 10, 20, 30);
        expectedAdjustedImage[y][x] = new RGB(adjustedIntensity, adjustedIntensity,
                adjustedIntensity);
      }
    }

    model.levelsAdjust("narrowRangeImage", "adjustedNarrowRangeImage",
            10, 20, 30, splitPercentage);
    RGB[][] adjustedImage = controller.getImages().get("adjustedNarrowRangeImage");

    for (int y = 0; y < expectedAdjustedImage.length; y++) {
      for (int x = 0; x < expectedAdjustedImage[0].length; x++) {
        assertEquals(expectedAdjustedImage[y][x].red, adjustedImage[y][x].red);
        assertEquals(expectedAdjustedImage[y][x].green, adjustedImage[y][x].green);
        assertEquals(expectedAdjustedImage[y][x].blue, adjustedImage[y][x].blue);
      }
    }
  }

  // Wide range
  @Test
  public void testWideRangeLevelsAdjust() {
    RGB[][] wideRangeImage = {
            {new RGB(50, 50, 50), new RGB(100, 100, 100)},
            {new RGB(150, 150, 150), new RGB(200, 200, 200)}
    };
    controller.getImages().put("wideRangeImage", wideRangeImage);

    RGB[][] expectedAdjustedImage = new RGB[2][2];
    for (int y = 0; y < wideRangeImage.length; y++) {
      for (int x = 0; x < wideRangeImage[0].length; x++) {
        int adjustedIntensity = applyQuadraticFormula(wideRangeImage[y][x].red, 0, 128, 255);
        expectedAdjustedImage[y][x] = new RGB(adjustedIntensity, adjustedIntensity,
                adjustedIntensity);
      }
    }

    model.levelsAdjust("wideRangeImage", "adjustedWideRangeImage",
            0, 128, 255, splitPercentage);
    RGB[][] adjustedImage = controller.getImages().get("adjustedWideRangeImage");

    for (int y = 0; y < expectedAdjustedImage.length; y++) {
      for (int x = 0; x < expectedAdjustedImage[0].length; x++) {
        assertEquals(expectedAdjustedImage[y][x].red, adjustedImage[y][x].red);
        assertEquals(expectedAdjustedImage[y][x].green, adjustedImage[y][x].green);
        assertEquals(expectedAdjustedImage[y][x].blue, adjustedImage[y][x].blue);
      }
    }
  }

  // Edge cases
  @Test
  public void testEdgeCaseLevelsAdjust() {
    RGB[][] edgeCaseImage = {
            {new RGB(10, 10, 10), new RGB(20, 20, 20)},
            {new RGB(240, 240, 240), new RGB(250, 250, 250)}
    };
    controller.getImages().put("edgeCaseImage", edgeCaseImage);

    RGB[][] expectedAdjustedImage = new RGB[2][2];
    for (int y = 0; y < edgeCaseImage.length; y++) {
      for (int x = 0; x < edgeCaseImage[0].length; x++) {
        int adjustedIntensity = applyQuadraticFormula(edgeCaseImage[y][x].red, 10, 128, 245);
        expectedAdjustedImage[y][x] = new RGB(adjustedIntensity, adjustedIntensity,
                adjustedIntensity);
      }
    }

    model.levelsAdjust("edgeCaseImage", "adjustedEdgeCaseImage",
            10, 128, 245, splitPercentage);
    RGB[][] adjustedImage = controller.getImages().get("adjustedEdgeCaseImage");

    for (int y = 0; y < expectedAdjustedImage.length; y++) {
      for (int x = 0; x < expectedAdjustedImage[0].length; x++) {
        assertEquals(expectedAdjustedImage[y][x].red, adjustedImage[y][x].red);
        assertEquals(expectedAdjustedImage[y][x].green, adjustedImage[y][x].green);
        assertEquals(expectedAdjustedImage[y][x].blue, adjustedImage[y][x].blue);
      }
    }
  }

  // High contrast
  @Test
  public void testHighContrastLevelsAdjust() {
    RGB[][] highContrast = {
            {new RGB(0, 0, 0), new RGB(255, 255, 255)},
            {new RGB(0, 0, 0), new RGB(255, 255, 255)}
    };
    controller.getImages().put("highContrast", highContrast);

    RGB[][] expectedAdjustedImage = new RGB[2][2];
    for (int y = 0; y < highContrast.length; y++) {
      for (int x = 0; x < highContrast[0].length; x++) {
        int adjustedIntensity = applyQuadraticFormula(highContrast[y][x].red, 20, 128, 230);
        expectedAdjustedImage[y][x] = new RGB(adjustedIntensity, adjustedIntensity,
                adjustedIntensity);
      }
    }

    model.levelsAdjust("highContrast", "adjustedHighContrast",
            20, 128, 230, splitPercentage);
    RGB[][] adjustedImage = controller.getImages().get("adjustedHighContrast");

    for (int y = 0; y < expectedAdjustedImage.length; y++) {
      for (int x = 0; x < expectedAdjustedImage[0].length; x++) {
        assertEquals(expectedAdjustedImage[y][x].red, adjustedImage[y][x].red);
        assertEquals(expectedAdjustedImage[y][x].green, adjustedImage[y][x].green);
        assertEquals(expectedAdjustedImage[y][x].blue, adjustedImage[y][x].blue);
      }
    }
  }

  // Red-tinted image
  @Test
  public void testRedTintedImageLevelsAdjust() {
    RGB[][] redTinted = {
            {new RGB(255, 0, 0), new RGB(255, 0, 0)},
            {new RGB(255, 0, 0), new RGB(255, 0, 0)}
    };
    controller.getImages().put("redTinted", redTinted);

    RGB[][] expectedAdjustedImage = new RGB[2][2];
    for (int y = 0; y < redTinted.length; y++) {
      for (int x = 0; x < redTinted[0].length; x++) {
        int adjustedRed = applyQuadraticFormula(redTinted[y][x].red, 20, 128, 230);
        expectedAdjustedImage[y][x] = new RGB(adjustedRed, 0, 0);
      }
    }

    model.levelsAdjust("redTinted", "adjustedRedTinted",
            20, 128, 230, splitPercentage);
    RGB[][] adjustedImage = controller.getImages().get("adjustedRedTinted");

    for (int y = 0; y < expectedAdjustedImage.length; y++) {
      for (int x = 0; x < expectedAdjustedImage[0].length; x++) {
        assertEquals(expectedAdjustedImage[y][x].red, adjustedImage[y][x].red);
        assertEquals(expectedAdjustedImage[y][x].green, adjustedImage[y][x].green);
        assertEquals(expectedAdjustedImage[y][x].blue, adjustedImage[y][x].blue);
      }
    }
  }

  @Test
  public void testPPMImageLevelsAdjust() throws Exception {
    controller.loadImage("resources/testImages/ppm/red_tinged_image.ppm",
            "ppmImage");

    RGB[][] originalImage = controller.getImages().get("ppmImage");
    RGB[][] expectedAdjustedImage = new RGB[originalImage.length][originalImage[0].length];
    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        int adjustedRed = applyQuadraticFormula(originalImage[y][x].red, 20, 128, 230);
        int adjustedGreen = applyQuadraticFormula(originalImage[y][x].green, 20, 128, 230);
        int adjustedBlue = applyQuadraticFormula(originalImage[y][x].blue, 20, 128, 230);
        expectedAdjustedImage[y][x] = new RGB(adjustedRed, adjustedGreen, adjustedBlue);
      }
    }

    model.levelsAdjust("ppmImage", "adjustedPPMImage",
            20, 128, 230, splitPercentage);
    RGB[][] adjustedImage = controller.getImages().get("adjustedPPMImage");

    for (int y = 0; y < expectedAdjustedImage.length; y++) {
      for (int x = 0; x < expectedAdjustedImage[0].length; x++) {
        assertEquals(expectedAdjustedImage[y][x].red, adjustedImage[y][x].red);
        assertEquals(expectedAdjustedImage[y][x].green, adjustedImage[y][x].green);
        assertEquals(expectedAdjustedImage[y][x].blue, adjustedImage[y][x].blue);
      }
    }
  }

  @Test
  public void testPNGImageLevelsAdjust() throws Exception {
    controller.loadImage("resources/testImages/png/red_tinged_image.png", "pngImage");

    RGB[][] originalImage = controller.getImages().get("pngImage");
    RGB[][] expectedAdjustedImage = new RGB[originalImage.length][originalImage[0].length];
    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        int adjustedRed = applyQuadraticFormula(originalImage[y][x].red, 20, 128, 230);
        int adjustedGreen = applyQuadraticFormula(originalImage[y][x].green, 20, 128, 230);
        int adjustedBlue = applyQuadraticFormula(originalImage[y][x].blue, 20, 128, 230);
        expectedAdjustedImage[y][x] = new RGB(adjustedRed, adjustedGreen, adjustedBlue);
      }
    }

    model.levelsAdjust("pngImage", "adjustedPNGImage",
            20, 128, 230, splitPercentage);
    RGB[][] adjustedImage = controller.getImages().get("adjustedPNGImage");

    for (int y = 0; y < expectedAdjustedImage.length; y++) {
      for (int x = 0; x < expectedAdjustedImage[0].length; x++) {
        assertEquals(expectedAdjustedImage[y][x].red, adjustedImage[y][x].red);
        assertEquals(expectedAdjustedImage[y][x].green, adjustedImage[y][x].green);
        assertEquals(expectedAdjustedImage[y][x].blue, adjustedImage[y][x].blue);
      }
    }
  }

  @Test
  public void testJPEGImageLevelsAdjust() throws Exception {
    controller.loadImage("resources/testImages/jpg/red_tinged_image.jpg",
            "jpegImage");

    RGB[][] originalImage = controller.getImages().get("jpegImage");
    RGB[][] expectedAdjustedImage = new RGB[originalImage.length][originalImage[0].length];
    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        int adjustedRed = applyQuadraticFormula(originalImage[y][x].red, 20, 128, 230);
        int adjustedGreen = applyQuadraticFormula(originalImage[y][x].green, 20, 128, 230);
        int adjustedBlue = applyQuadraticFormula(originalImage[y][x].blue, 20, 128, 230);
        expectedAdjustedImage[y][x] = new RGB(adjustedRed, adjustedGreen, adjustedBlue);
      }
    }

    model.levelsAdjust("jpegImage", "adjustedJPEGImage",
            20, 128, 230, splitPercentage);
    RGB[][] adjustedImage = controller.getImages().get("adjustedJPEGImage");

    for (int y = 0; y < expectedAdjustedImage.length; y++) {
      for (int x = 0; x < expectedAdjustedImage[0].length; x++) {
        assertEquals(expectedAdjustedImage[y][x].red, adjustedImage[y][x].red);
        assertEquals(expectedAdjustedImage[y][x].green, adjustedImage[y][x].green);
        assertEquals(expectedAdjustedImage[y][x].blue, adjustedImage[y][x].blue);
      }
    }
  }


  /**
   * Helper method to apply the quadratic levels adjustment formula.
   */
  private int applyQuadraticFormula(int intensity, int b, int m, int w) {
    double aCoefficient = (b * b * (m - w) - b * (m * m - w * w) + w * m * m - m * w * w);
    double aFactor = -b * (128 - 255) + 128 * w - 255 * m;
    double bFactor = b * b * (128 - 255) + 255 * m * m - 128 * w * w;
    double cFactor = b * b * (255 * m - 128 * w) - b * (255 * m * m - 128 * w * w);

    double a = aFactor / aCoefficient;
    double bCoef = bFactor / aCoefficient;
    double c = cFactor / aCoefficient;

    double result = a * intensity * intensity + bCoef * intensity + c;
    return (int) Math.min(255, Math.max(0, Math.round(result)));
  }
}