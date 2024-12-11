import model.ImageModelImpl;
import model.ImageModelInterface;
import model.image.Image;
import model.image.ImageInterface;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * A class that tests the ImageModelImpl class.
 */
public class ImageModelImplTest {

  @Test
  public void testAddAndGetImage() {
    ImageModelInterface model = new ImageModelImpl();

    int[][][] pixels = {
            {{255, 0, 0}, {0, 255, 0}},
            {{0, 0, 255}, {255, 255, 0}}
    };
    ImageInterface image = new Image(2, 2, 255, pixels);

    model.addImage("testImage", image);

    ImageInterface retrievedImage = model.getImage("testImage");

    assertNotNull(retrievedImage);
    assertEquals(image.getWidth(), retrievedImage.getWidth());
    assertEquals(image.getHeight(), retrievedImage.getHeight());
    assertEquals(image.getMaxValue(), retrievedImage.getMaxValue());
    assertArrayEquals(image.getPixels(), retrievedImage.getPixels());

    retrievedImage.getPixels()[0][0][0] = 0;

    ImageInterface retrievedImage2 = model.getImage("testImage");

    assertEquals(255, retrievedImage2.getPixels()[0][0][0]);
  }

  @Test
  public void testRemoveImage() {
    ImageModelInterface model = new ImageModelImpl();

    int[][][] pixels = {
            {{255, 0, 0}}
    };
    ImageInterface image = new Image(1, 1, 255, pixels);

    model.addImage("testImage", image);
    assertNotNull(model.getImage("testImage"));

    model.removeImage("testImage");
    assertNull(model.getImage("testImage"));
  }

  @Test
  public void testGetImageNotExists() {
    ImageModelInterface model = new ImageModelImpl();
    assertNull(model.getImage("nonExistentImage"));
  }

  @Test
  public void testAddImageCloning() {
    ImageModelInterface model = new ImageModelImpl();

    int[][][] pixels = {{{100, 150, 200}}};
    ImageInterface image = new Image(1, 1, 255, pixels);

    model.addImage("image1", image);

    image.getPixels()[0][0][0] = 0;

    ImageInterface retrievedImage = model.getImage("image1");

    assertEquals(100, retrievedImage.getPixels()[0][0][0]);
  }

  @Test
  public void testGetImageCloning() {
    ImageModelInterface model = new ImageModelImpl();

    int[][][] pixels = {{{100, 150, 200}}};
    ImageInterface image = new Image(1, 1, 255, pixels);

    model.addImage("image1", image);

    ImageInterface retrievedImage = model.getImage("image1");

    retrievedImage.getPixels()[0][0][0] = 0;

    ImageInterface retrievedImage2 = model.getImage("image1");

    assertEquals(100, retrievedImage2.getPixels()[0][0][0]);
  }

  @Test
  public void testMultipleImages() {
    ImageModelInterface model = new ImageModelImpl();

    int[][][] pixels1 = {{{255, 0, 0}}};
    ImageInterface image1 = new Image(1, 1, 255, pixels1);

    int[][][] pixels2 = {{{0, 255, 0}}};
    ImageInterface image2 = new Image(1, 1, 255, pixels2);

    model.addImage("redImage", image1);
    model.addImage("greenImage", image2);

    ImageInterface retrievedImage1 = model.getImage("redImage");
    ImageInterface retrievedImage2 = model.getImage("greenImage");

    assertArrayEquals(pixels1, retrievedImage1.getPixels());
    assertArrayEquals(pixels2, retrievedImage2.getPixels());
  }
}
