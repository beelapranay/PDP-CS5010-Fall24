public interface ImageModelInterface {
  RGB[][] applySepiaTone(RGB[][] pixelArray);

  RGB[][] convertToGreyscale(RGB[][] pixelArray);

  RGB[][] blurImage(RGB[][] pixelArray);

  RGB[][] sharpenImage(RGB[][] pixelArray);

  RGB[][] brightenImage(RGB[][] pixelArray, int value);

  RGB[][] darkenImage(RGB[][] pixelArray, int value);

  RGB[][] horizontalFlipImage(RGB[][] pixelArray);

  RGB[][] verticalFlipImage(RGB[][] pixelArray);
}
