ASSIGNMENT 4 (PART-1)
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




Controller:
	1. ImageLoaderAndSaver (Abstract Class)
		Purpose: 
			This abstract class defines the core functionalities for loading and saving images. 
			Any class that handles image formats must extend this and implement methods for loading and saving images.

		Key Methods:
			loadImage(String filePath, String imageName): Abstract method for loading an image from a file and converting it into a 2D array of RGB values.
			saveImage(String filePath, String imageName): Abstract method for saving a 2D RGB array as an image file.
			arrayToImage(RGB[][] image): Converts a 2D RGB array into a BufferedImage.
			getFileFormat(String filePath): Utility method to extract the file format from a file path.

	2. DefaultImageLoaderAndSaver
		Purpose: 
			This class extends ImageLoaderAndSaver and implements functionality to load and save images in standard formats such as JPEG and PNG.

		Key Features:
			Converts a loaded image into a 2D RGB array by reading pixel data.
			Saves an image by writing the BufferedImage to the desired file format using the ImageIO class.

		Usage: 
			It is primarily used for handling standard image formats that Java’s ImageIO supports, such as .jpg and .png.

	3. PPMImageLoaderAndSaver
		Purpose: 
			This class is responsible for loading and saving images in the custom PPM format (Portable Pixmap F

		Key Features:
			Parses the PPM file, extracting its width, height, and pixel data, and converts it into a 2D RGB array.
			Saves a given image in the PPM format, writing the header (P3, width, height, max color value) and pixel data in ASCII format.

		Usage: 
			It is specialized for working with the PPM image format, providing an alternative to handling standard image formats.

	4. ImageLoaderFactory
		Purpose: 
			The factory class is responsible for returning the correct ImageLoaderAndSaver instance based on the file type (extension) provided.

		Key Features:
			Dynamically creates either a PPMImageLoaderAndSaver or DefaultImageLoaderAndSaver based on whether the file is .ppm, .jpg, or .png.
			If the file type is unsupported, it throws an UnsupportedOperationException.

		Usage: 
			This ensures that the correct loading and saving logic is applied depending on the format of the file.

	5. ImageControllerInterface
		Purpose:
			This interface defines the essential methods for an image controller

		Methods:
			loadImage(String filePath, String imageName): Loads an image from the specified file path and associates it with a name. This method throws an IOException if loading fails.
			saveImage(String filePath, String imageName): Saves the specified image to the provided file path, throwing an IOException on failure.
			getImages(): Returns a HashMap containing all loaded images, where the keys are image names, and the values are the corresponding RGB pixel representations.
			commandParser(String command): Parses and executes a single command string, throwing an IOException if processing encounters an error.
			processCommands(String[] tokens): Processes an array of command tokens extracted from user input or a command file, throwing an IOException on errors.

	6.ImageController
		Purpose:
			The ImageController class implements the ImageControllerInterface, providing the concrete logic for the defined methods.

		Key Features:
			Image Storage: It uses a HashMap<String, RGB[][]> to store images, allowing for easy retrieval and manipulation by name.

		Loading and Saving Images:
			The loadImage method utilizes an ImageLoaderAndSaver (created through the ImageLoaderFactory) to load images from disk. 
			The loaded image's pixel data is stored in the images map.
			The saveImage method similarly leverages the ImageLoaderAndSaver to save images to the specified file paths.
		
		Command Processing:
			The commandParser method allows the controller to interpret commands entered by the user. 
			It supports both single commands and batch command processing from a file. 
			Comments and empty lines in command files are ignored.
			The processCommands method interprets specific commands such as load, save, and various image processing operations. 
			It delegates the actual image manipulations to an ImageModelInterface, which contains methods for manipulating images.

		Supported Commands:
			The ImageController can process a variety of commands for image manipulation.	
	
			Loading and Saving:
				load <filePath> <imageName>: Loads an image.
				save <filePath> <imageName>: Saves the image.

			Color Component Extraction:
				red-component <imageName> <newImageName>: Extracts the red component.
				green-component <imageName> <newImageName>: Extracts the green component.
				blue-component <imageName> <newImageName>: Extracts the blue component.
				Image Transformations:
				horizontal-flip <imageName> <newImageName>: Flips the image horizontally.
				vertical-flip <imageName> <newImageName>: Flips the image vertically.

			Color Adjustments:
				brighten <value> <imageName> <newImageName>: Brightens the image.

			Filters and Effects:
				blur <imageName> <newImageName>: Applies a blur effect.
				sharpen <imageName> <newImageName>: Sharpens the image.
				sepia <imageName> <newImageName>: Applies a sepia tone.

Model:
	1.ImageModelInterface
		Purpose:
			The ImageModelInterface defines a contract for image processing operations, enabling the application of various effects and manipulations to images. 
			This interface serves as a blueprint for implementing classes that will handle tasks such as applying filters, adjusting brightness, and performing color manipulations on images.
	
		Key Features:
			Applying effects such as sepia tone, blur, and sharpen.
			Modifying brightness of images.
			Flipping images horizontally and vertically.
			Extracting color channels (red, green, and blue).
			Calculating image metrics like value, intensity, and luma.
			Combining color components from greyscale images into a single colored image.
		
		Method Descriptions:
			1. applySepiaTone(String imageName, String destinationImageName)
				Applies a sepia tone effect to the specified image.
				Parameters:
					imageName: The name of the image to apply the effect to.
					destinationImageName: The name of the destination image to save the result.
					Throws: NoSuchElementException if the image is not found.

			2. blurImage(String imageName, String destinationImageName)
				Applies a blur effect to the specified image.
				Parameters:
					imageName: The name of the image to blur.
					destinationImageName: The name of the destination image to save the result.
					Throws: NoSuchElementException if the image is not found.

			3. sharpenImage(String imageName, String destinationImageName)
				Applies a sharpen effect to the specified image.
				Parameters:
					imageName: The name of the image to sharpen.
					destinationImageName: The name of the destination image to save the result.
					Throws: NoSuchElementException if the image is not found.

			4. changeBrightness(String imageName, String destinationImageName, int value)
				Changes the brightness of the specified image by a given value.
				Parameters:
					imageName: The name of the image to modify.
					destinationImageName: The name of the destination image to save the result.
					value: The value to adjust the brightness.
					Throws: NoSuchElementException if the image is not found.

			5. horizontalFlipImage(String imageName, String destinationImageName)
				Flips the specified image horizontally.
				Parameters:
					imageName: The name of the image to flip.
					destinationImageName: The name of the destination image to save the result.
					Throws: NoSuchElementException if the image is not found.

			6. verticalFlipImage(String imageName, String destinationImageName)
				Flips the specified image vertically.
				Parameters:
					imageName: The name of the image to flip.
					destinationImageName: The name of the destination image to save the result.
					Throws: NoSuchElementException if the image is not found.

			7. splitRedChannel(String imageName, String destinationImageName)
				Extracts the red channel from the specified image.
				Parameters:
					imageName: The name of the image to process.
					destinationImageName: The name of the destination image to save the result.
					Throws: NoSuchElementException if the image is not found.

			8. splitGreenChannel(String imageName, String destinationImageName)
				Extracts the green channel from the specified image.
				Parameters:
					imageName: The name of the image to process.
					destinationImageName: The name of the destination image to save the result.
					Throws: NoSuchElementException if the image is not found.

			9. splitBlueChannel(String imageName, String destinationImageName)
				Extracts the blue channel from the specified image.
				Parameters:
					imageName: The name of the image to process.
					destinationImageName: The name of the destination image to save the result.
					Throws: NoSuchElementException if the image is not found.

			10. calculateValue(String imageName, String destinationImageName)
				Calculates the value (maximum of RGB components) for the specified image.
				Parameters:
					imageName: The name of the image to process.
					destinationImageName: The name of the destination image to save the result.
					Throws: NoSuchElementException if the image is not found.

			11. calculateIntensity(String imageName, String destinationImageName)
				Calculates the intensity (average of RGB components) for the specified image.
				Parameters:
					imageName: The name of the image to process.
					destinationImageName: The name of the destination image to save the result.
					Throws: NoSuchElementException if the image is not found.

			12. calculateLuma(String imageName, String destinationImageName)
				Calculates the luma (weighted average of RGB components) for the specified image.
				Parameters:
					imageName: The name of the image to process.
					destinationImageName: The name of the destination image to save the result.
					Throws: NoSuchElementException if the image is not found.

			13. combineGreyscale(String imageName, String redImage, String greenImage, String blueImage)
				Combines greyscale images into a single image using the red, green, and blue components.
				Parameters:
					imageName: The name of the combined image.
					redImage: The image containing the red component.
					greenImage: The image containing the green component.
					blueImage: The image containing the blue component.
					Throws: NoSuchElementException if any of the images are not found.

			Usage:
				Implementations of this interface will handle the actual image processing logic. 
				Users can create instances of the implementing class and call the methods defined in this interface to perform image manipulations as needed.

	2.ImageModel Class
		Purpose:
		The ImageModel class implements image processing functionalities as defined in the ImageModelInterface. 
		This class interacts with the ImageController to perform operations such as applying a sepia tone, blurring, sharpening, and changing brightness.

		Key Responsibilities:
			Image Manipulation: 	Each method in the ImageModel class performs specific pixel manipulations on images by modifying their RGB values.
			Kernel Operations: 	For blurring and sharpening, the class utilizes a convolution approach where a kernel (a 2D array) is applied to each pixel's neighborhood to compute new pixel values.
			Color Transformation: 	The applySepiaTone method modifies each pixel's RGB values by applying a specific transformation matrix that enhances the brownish hue typical of sepia tones.
			Brightness Adjustment: 	The changeBrightness method iterates through each pixel and adjusts its RGB values by a specified brightness value, 
					       	ensuring that the resulting values remain within the valid RGB range (0-255).
			Image Flipping: 	The horizontalFlipImage and verticalFlipImage methods utilize array manipulation techniques to swap pixel positions efficiently, 
						achieving the flip effect without additional memory overhead.
			Channel Isolation: 	The splitRedChannel method creates a new image where only the red channel values from the original image are preserved, and green and blue channels are set to zero.

		Constructor:
			Parameters:
			imageController: An instance of ImageController used to manage and access images.

		Method Implementations:
			1. Apply Sepia Tone:
				This method retrieves the original image and processes each pixel to apply a sepia transformation. 
				It calculates new RGB values based on a predefined matrix, ensuring that the output image maintains the sepia effect.
			2. Blur Image:
				For blurring, a 3x3 kernel is constructed, and the class iterates over each pixel. It calculates the average color values from surrounding pixels while handling edge cases, 
				which ensures smooth transitions in the blurred image.
			3. Sharpen Image:
				Sharpening is achieved by applying a specific kernel designed to enhance contrast. 
				The implementation involves iterating through the image and amplifying the differences between neighboring pixels, resulting in a more defined image.
			4. Change Brightness:
				This method adjusts pixel brightness by adding a specified value to each RGB component. It ensures that all adjusted values remain within the permissible range (0-255) to prevent overflow.
			5. Horizontal Flip:
				The image is flipped horizontally by swapping pixel columns in each row. This is done efficiently by manipulating array indices directly.
			6. Vertical Flip:
				A vertical flip is implemented similarly, where pixel rows are swapped to achieve the desired effect.
			7. Split Red Channel:
				This functionality extracts the red channel from the original image, setting the green and blue channels to zero. 
				The resulting image highlights only the red component, providing a clear visualization of that channel.

		Error Handling
			The class throws NoSuchElementException when attempting to process an image that does not exist in the controller’s image repository, ensuring that operations are only performed on valid images.

	3.RGBInterface

		Purpose:
			This marker interface indicates that a class represents RGB color values. It does not define any methods, serving primarily for type identification.
		Usage:
			Essentially, this class enables the storage of exact RGB values for each pixel in an image or for each index in an array, in our case.

	4.RGB Class
		Purpose:
			This class represents an RGB color, encapsulating the red, green, and blue components. It provides a straightforward structure for managing color values in image processing tasks.
		Key Features:
			Fields:
				red: An integer storing the red component of the color (0-255).
				green: An integer storing the green component of the color (0-255).
				blue: An integer storing the blue component of the color (0-255).
		Constructor:
			RGB(int red, int green, int blue): Initializes an RGB object with specified red, green, and blue values.
		Usage:
			To create an RGB color, instantiate the class with desired RGB values. For example, RGB color = new RGB(255, 0, 0); creates a red color.

________________________________________________________________________________________________________________________________________________________________________________________________________________________________

HOW TO RUN YOUR SCRIPT ON OUR PROGRAM:
	Step 1: Locate the class name "Main" in the source folder
	Step 2: Run the main class.
	Step 3: Enter your commands one after the other adhering to the instructions on your console or enter 'run' followed by a file path to run your src file. 
________________________________________________________________________________________________________________________________________________________________________________________________________________________________

IMAGE CITATIONS:
	earth.jpg :https://images.app.goo.gl/JnDyDFKMURhbk4d37
	square.png :https://img.freepik.com/free-photo/photo-delicious-hamburger-isolated-white-background_125540-3378.jpg?semt=ais_hybrid

NOTE: ALL PPM FILES ARE CREATED BY US. 
________________________________________________________________________________________________________________________________________________________________________________________________________________________________
