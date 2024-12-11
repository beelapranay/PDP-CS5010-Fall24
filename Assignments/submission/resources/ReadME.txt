ASSIGNMENT 5 (PART-2)
IME: Image Manipulation and Enhancement


PROJECT DESIGN STRUCTURE
src
├── controller
│   ├── DefaultImageLoaderAndSaver.java
│   ├── ImageController.java
│   ├── ImageControllerInterface.java
│   ├── ImageLoaderAndSaver.java
│   ├── ImageLoaderFactory.java
│   ├── PPMImageLoaderAndSaver.java
├── model
│   ├── ImageModel.java
│   ├── ImageModelInterface.java
│   ├── RGB.java
│   ├── RGBInterface.java
├── view
│   ├── ImageView.java
│   ├── ImageViewInterface.java



Controller
1. ImageLoaderAndSaver (Abstract Class)
    Defines core methods for loading and saving images.
    Methods: loadImage, saveImage, arrayToImage, getFileFormat.
2. DefaultImageLoaderAndSaver
    Handles standard formats like JPEG and PNG.
    Reads pixel data into a 2D RGB array.
3. PPMImageLoaderAndSaver
    Specialized for handling PPM format images, extracting and writing pixel data in ASCII format.
4. ImageLoaderFactory
    Returns an appropriate loader instance based on file type (PPM, JPG, PNG).
    Throws UnsupportedOperationException for unsupported file types.
5. ImageControllerInterface
    Interface defining core methods for loading, saving, and manipulating images.
6. ImageController
    Implements ImageControllerInterface for processing commands and storing images.

    Key Commands Supported:
    Load & Save: load <filePath> <imageName>, save <filePath> <imageName>
    Color Components: red-component, green-component, blue-component
    Transformations: horizontal-flip, vertical-flip
    Adjustments: brighten, levels-adjust, color-correct
    Filters & Effects: blur, sharpen, sepia
    Image Analysis: histogram
    Advanced Adjustments:
    Color Correction: Balances color peaks across channels.
    Levels Adjustment: Remaps intensities using black, mid-tone, and white levels for contrast control.

Model
    1. ImageModelInterface
        Interface defining image processing operations such as brightness adjustments, channel extraction, and filters.
    2. ImageModel
        Implements ImageModelInterface with methods for:
    Effects: Sepia, blur, sharpen.
    Adjustments: Brightness, color correction, and levels adjustment.
    Image Manipulations: Horizontal/vertical flip, histogram generation.
    Levels Adjustment: Custom brightness and contrast remapping using specified black, mid-tone, and white levels.
    3. RGBInterface
        A marker interface for identifying RGB color values.
    4. RGB Class
        Encapsulates red, green, and blue components in an image pixel.

VIEW
    ImageViewInterface
        Purpose:
            Defines the essential functionalities for rendering image histograms and calculating histogram data based on RGB arrays.
        Methods:
            generateAndStoreHistogram(String imageName, String destinationImageName):
                Generates and stores a histogram image for the specified image.
            calculateHistogramData(RGB[][] pixelArray):
                Calculates the histogram data for each color channel in the provided image.

    ImageView
        Implements:
            ImageViewInterface
        Purpose:
            Renders histograms of images by processing RGB data and displaying them with grid lines and color channels.
        Key Responsibilities:
            Histogram Generation and Rendering:
                Converts pixel data from an image into histogram data and stores it in histogramData.
                Draws histograms for red, green, and blue channels based on independent scaling.
                Grid lines are drawn on the histogram for clarity, using custom scaling to enhance visibility.
        Helper Methods:
            drawGrid(Graphics g):
                Draws grid lines on the histogram background.
            drawChannelHistogram(Graphics g, String channel, Color color):
                Draws the histogram for a specific color channel.
            convertToRGBArray(BufferedImage image):
                Converts BufferedImage to a 2D array of RGB values.

________________________________________________________________________________________________________________________________________________________________________________________________________________________________

HOW TO RUN YOUR SCRIPT ON OUR PROGRAM:
	Step 1: Locate the class name "Main" in the source folder
	Step 2: Run the main class.
	Step 3: Enter your commands one after the other adhering to the instructions on your console or enter 'run' followed by a file path to run your src file.
	Step 4: Refer to the UseMe file for the list of commands and their respective correct formats.
________________________________________________________________________________________________________________________________________________________________________________________________________________________________

IMAGE CITATIONS:
	earth.jpg :https://images.app.goo.gl/JnDyDFKMURhbk4d37
	square.png :https://img.freepik.com/free-photo/photo-delicious-hamburger-isolated-white-background_125540-3378.jpg?semt=ais_hybrid
    red_tinged_image: https://commons.wikimedia.org/wiki/File:Red_Color.jpg
NOTE: ALL PPM FILES ARE CREATED BY US. 
________________________________________________________________________________________________________________________________________________________________________________________________________________________________