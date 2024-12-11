import controller.ImageIOUtil;
import model.image.ImageInterface;
import model.image.Image;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * A class that tests the ImageIOUtil class.
 * It tests the readImage and writeImage methods.
 * It also tests the PPM format.
 */
public class ImageIOUtilTest {

  @Test
  public void testReadPPM() throws IOException {
    String ppmContent = "P3\n"
            + "2 2\n"
            + "255\n"
            + "255 0 0   0 255 0\n"
            + "0 0 255   255 255 0\n";

    File tempFile = File.createTempFile("test_image", ".ppm");
    tempFile.deleteOnExit();
    try (PrintWriter out = new PrintWriter(tempFile)) {
      out.print(ppmContent);
    }

    ImageInterface image = ImageIOUtil.readImage(tempFile.getAbsolutePath());

    assertNotNull(image);
    assertEquals(2, image.getWidth());
    assertEquals(2, image.getHeight());
    assertEquals(255, image.getMaxValue());

    int[][][] expectedPixels = {
            {{255, 0, 0}, {0, 255, 0}},
            {{0, 0, 255}, {255, 255, 0}}
    };

    assertArrayEquals(expectedPixels, image.getPixels());
  }

  @Test
  public void testWritePPM() throws IOException {
    int[][][] pixels = {
            {{255, 0, 0}, {0, 255, 0}},
            {{0, 0, 255}, {255, 255, 0}}
    };
    ImageInterface image = new Image(2, 2, 255, pixels);

    File tempFile = File.createTempFile("test_output", ".ppm");
    tempFile.deleteOnExit();

    ImageIOUtil.writeImage(image, tempFile.getAbsolutePath());

    StringBuilder content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line.trim()).append("\n");
      }
    }

    String expectedContent = "P3\n"
            + "2 2\n"
            + "255\n"
            + "255\n0\n0\n"
            + "0\n255\n0\n"
            + "0\n0\n255\n"
            + "255\n255\n0\n";

    assertEquals(expectedContent, content.toString());
  }

  @Test
  public void testReadPPMWithComments() throws IOException {
    String ppmContent = "P3\n"
            + "# This is a comment\n"
            + "2 1\n"
            + "255\n"
            + "# Another comment\n"
            + "255 0 0   0 255 0\n";

    File tempFile = File.createTempFile("test_image_comments", ".ppm");
    tempFile.deleteOnExit();
    try (PrintWriter out = new PrintWriter(tempFile)) {
      out.print(ppmContent);
    }

    ImageInterface image = ImageIOUtil.readImage(tempFile.getAbsolutePath());

    assertNotNull(image);
    assertEquals(2, image.getWidth());
    assertEquals(1, image.getHeight());
    assertEquals(255, image.getMaxValue());

    int[][][] expectedPixels = {
            {{255, 0, 0}, {0, 255, 0}}
    };

    assertArrayEquals(expectedPixels, image.getPixels());
  }

  @Test
  public void testWritePPMFormat() throws IOException {
    int[][][] pixels = {
            {{123, 45, 67}, {89, 10, 11}},
            {{12, 13, 14}, {15, 16, 17}}
    };
    ImageInterface image = new Image(2, 2, 255, pixels);

    File tempFile = File.createTempFile("test_output_format", ".ppm");
    tempFile.deleteOnExit();

    ImageIOUtil.writeImage(image, tempFile.getAbsolutePath());

    StringBuilder content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line).append("\n");
      }
    }

    String expectedContent = "P3\n"
            + "2 2\n"
            + "255\n"
            + "123\n45\n67\n"
            + "89\n10\n11\n"
            + "12\n13\n14\n"
            + "15\n16\n17\n";

    assertEquals(expectedContent, content.toString());
  }

}
