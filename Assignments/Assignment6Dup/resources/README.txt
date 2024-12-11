ASSIGNMENT 6 (PART-3)
GRIME: Graphical Image Manipulation and Enhancement

├── CONTROLLER
│   ├── BrightenCommand.java
│   ├── BlurAndSharpenCommand.java
│   ├── ColorCorrectCommand.java
│   ├── Command.java
│   ├── CommandRegistry.java
│   ├── CompressCommand.java
│   ├── DefaultImageLoaderAndSaver.java
│   ├── DownscaleCommand.java
│   ├── FlipCommand.java
│   ├── GetAndRegisterCommands.java
│   ├── GreyscaleCommand.java
│   ├── HistogramCommand.java
│   ├── ImageController.java
│   ├── ImageControllerInterface.java
│   ├── ImageLoaderAndSaver.java
│   ├── ImageLoaderFactory.java
│   ├── LevelsAdjustCommand.java
│   ├── LoadCommand.java
│   ├── PPMImageLoaderAndSaver.java
│   ├── RGBCombineCommand.java
│   ├── RGBSplitCommand.java
│   ├── SaveCommand.java
│   └── SepiaCommand.java
├── MODEL
│   ├── ImageModel.java
│   ├── ImageModelInterface.java
│   ├── RGB.java
│   └── RGBInterface.java
├── VIEW
│   ├── ImageView.java
│   ├── ImageViewInterface.java
│   └── ImageViewerWithController.java
│   └── ImageViewerWithControllerInterface.java






Controller Summary (Scroll down for the detailed explanation)
The Controller module in this project orchestrates the interaction between the user, the model, and the view for seamless image manipulation and processing. It leverages a command-based architecture to enable dynamic and extensible operations.

Key Classes and Interfaces:
	1	ImageControllerInterface:
	◦	Defines core methods for loading, saving, and processing images, and parsing commands.
	2	ImageController:
	◦	Implements ImageControllerInterface.
	◦	Central hub for managing images, commands, and user inputs.
	◦	Handles loading, saving, and delegating image operations to the model.
	3	ImageLoaderAndSaver:
	◦	Abstract class defining the blueprint for loading and saving images.
	◦	Utilities include arrayToImage for converting RGB arrays to BufferedImage.
	4	DefaultImageLoaderAndSaver:
	◦	Supports standard formats like .jpg and .png.
	◦	Uses Java’s ImageIO for efficient image handling.
	5	PPMImageLoaderAndSaver:
	◦	Specialized for loading and saving images in the ASCII-based PPM format.
	6	ImageLoaderFactory:
	◦	Dynamically selects the appropriate ImageLoaderAndSaver based on file type.
	7	Command:
	◦	Interface for defining individual image processing actions.
	◦	Ensures consistency and extensibility across all commands.
	8	CommandRegistry:
	◦	Manages registration and retrieval of commands.
	◦	Supports dynamic command integration.
	9	GetAndRegisterCommands:
	◦	Interface defining methods for registering and retrieving commands.

Commands:
Commands encapsulate image operations and are executed by the controller. Key commands include:
	•	Basic Operations:
	◦	LoadCommand: Load images into the system.
	◦	SaveCommand: Save processed images to specified paths.
	•	Image Transformations:
	◦	FlipCommand: Perform horizontal or vertical flips.
	◦	GreyscaleCommand: Apply greyscale transformations based on color components.
	◦	SepiaCommand: Apply a sepia tone effect.
	•	Adjustments and Effects:
	◦	BrightenCommand: Adjust image brightness.
	◦	BlurAndSharpenCommand: Blur or sharpen an image.
	◦	LevelsAdjustCommand: Fine-tune image brightness using black, midtones, and white levels.
	◦	ColorCorrectCommand: Adjust image color balance.
	•	Advanced Operations:
	◦	CompressCommand: Reduce image size or quality.
	◦	RGBSplitCommand: Split an image into red, green, and blue greyscale channels.
	◦	RGBCombineCommand: Combine greyscale channels into a colored image.
	◦	HistogramCommand: Generate and store a histogram of pixel intensities.
	◦	DownscaleCommand: Downscales an image to the mentioned height and width.


Key Features:
	•	Extensibility: The command-based structure allows easy addition of new operations.
	•	Dynamic Image Handling: Supports multiple formats with loaders like DefaultImageLoaderAndSaver and PPMImageLoaderAndSaver.
	•	Error Handling: Provides meaningful feedback for invalid operations or input errors.
	•	Interfacing: Acts as a bridge between the user, model, and view for efficient image processing.
This robust, modular design ensures that the controller can efficiently manage and execute a wide range of image manipulation tasks while remaining extensible for future enhancements.




Model Summary (Scroll down for the detailed explanation)
The Model module serves as the core computational component of the system, providing robust functionality for image processing and manipulation. It is responsible for representing, transforming, and managing pixel data through well-defined interfaces and classes.

Key Components:
	1	ImageModelInterface (Interface):
	◦	Defines the contract for various image processing operations such as sepia tone, blur, sharpen, brightness adjustment, compression, color correction, and levels adjustment.
	◦	Supports split-based operations for partial transformations.
	2	ImageModel (Class):
	◦	Implements ImageModelInterface to perform advanced image transformations.
	◦	Key features include sepia tone, blur, sharpen, flipping, greyscale transformations, color correction, channel splitting and combining, and image compression.
	◦	Uses nested ImageData for managing original and transformed image data, dimensions, and split points.
	◦	Includes utilities like histogram analysis, Haar wavelet transforms, and padding for efficient processing.
	3	RGBInterface (Marker Interface):
	◦	Acts as a marker for categorizing the RGB class, enabling better design and extensibility.
	4	RGB (Class):
	◦	Represents pixel data with red, green, and blue components.
	◦	Fundamental for image transformations and operations, providing a lightweight structure for handling color data.

Key Features:
	•	Comprehensive Image Manipulation:Supports a wide range of transformations, from basic adjustments (brightness, flipping) to advanced processing (compression, levels adjustment, and color correction).
	•	Split-Based Operations:Allows partial transformations using split percentages for greater flexibility.
	•	Error Handling:Ensures robust operations with meaningful exceptions for invalid inputs or missing images.
	•	Utility Functions:Includes helper methods for color correction, histogram analysis, quadratic adjustments, and wavelet transformations.

Usage:
The Model module acts as the computational backbone of the system, enabling the controller to execute diverse image processing tasks efficiently while maintaining flexibility for future enhancements.





View Summary (Scroll down for the detailed explanation)
The View module provides the graphical interface and user interaction layer for the image processing system. It integrates seamlessly with the controller to execute commands and display results, offering both visualization and interactivity.

Key Components:
	1	ImageViewInterface (Interface):
	◦	Defines methods for generating and visualizing histograms and managing image-related graphical data.
	◦	Supports histogram calculation for red, green, and blue channels from pixel arrays.
	2	ImageView (Class):
	◦	Implements ImageViewInterface and extends JPanel to render histograms and grids.
	◦	Dynamically calculates and displays histogram data, supports split views, and integrates with the controller for seamless data flow.
	3	ImageViewerWithController (Class):
	◦	A JFrame application for interactive image manipulation.
	◦	Features include loading/saving images, executing transformations (e.g., flipping, sepia tone, compression), and visualizing histograms.
	◦	Provides user-friendly input dialogs for dynamic operations like split transformations and compression.

Key Features:
	•	Interactive Operations: Supports transformations like flipping, blurring, RGB splitting, and brightness adjustment through a GUI.
	•	Dynamic Visualization: Displays histograms and grids, allowing users to analyze image data.
	•	Flexible File Handling: Enables loading and saving images in multiple formats.
	•	Seamless Integration: Works closely with the controller to execute commands and update the interface dynamically.

Usage:
The View module acts as the visual and interactive hub of the system, making advanced image processing accessible to users while maintaining flexibility and ease of use.



SUMMARY OF CHANGES (PART 1 -> PART 2):


1. View Layer Introduction:
	◦	New View Component: The addition of ImageView and ImageViewInterface introduces a graphical layer to the project.
	▪	Histogram Support: Part 2 supports generating, storing, and rendering histograms for images, which was not present in Part 1.
	▪	Helper Functions for Histogram: Methods like drawGrid, drawChannelHistogram, and convertToRGBArray support rendering histograms with visual clarity.
2. Advanced Image Processing in Controller:
	◦	New Commands:
	▪	histogram: Generates a histogram image for analysis.
	▪	levels-adjust: Provides custom remapping of intensities for improved contrast.
	▪	color-correct: Balances the color peaks across image channels.
	◦	Extended Command Support: Added support for advanced image adjustments like levels adjustment and color correction.
3. Enhancements in Model:
	◦	Levels Adjustment: New method for remapping brightness and contrast using black, mid-tone, and white levels.
	◦	Color Correction: A new feature that ensures consistent color peaks across RGB channels.
	◦	Error Handling: More robust exception handling ensures smooth operation across all new features.
	◦	Histogram Generation: Integration of histogram data calculation to support the new view layer.
	◦	Compress: Compresses an image wrt the compression percentage
	◦	Split-view: Allowing user to have a Split View like a before and after for already present operations
4	Improved Project Design:
	◦	Expanded Project Structure: Inclusion of a new view package in Part 2, improving modularity.
	◦	Enhanced Documentation: Part 2 provides additional insights into the workflow, making implementation and command usage more accessible.
5	How to Run Section:
	◦	Updated to include instructions for accessing commands and referencing the UseMe file for detailed command descriptions.
6	Additional Image Citations:
	◦	Inclusion of a new sample image (red_tinged_image) in the citations for demonstration purposes.

Key Takeaway:
Part 2 builds significantly upon Part 1 by adding a user interface (view layer), new image processing features like histogram rendering, levels adjustment, color correction etc, making the project more functional and user-friendly.




SUMMARY OF CHANGES (PART 1,2 -> PART 3):



1. Expanded Controller Functionality:
	◦	New Command classes (e.g., FlipCommand, CompressCommand, SepiaCommand, DownscaleCommand) were added to modularize the command execution logic for specific operations.
	◦	CommandRegistry was introduced to register and retrieve commands dynamically, streamlining the process of managing command implementations.
	◦	Improved command handling by integrating split view operations and following command design patterns(e.g., applying effects to part of the image).
2. Enhanced Image Manipulation Features in Model:
	◦	Added support for Haar Wavelet Transformation for image compression.
	◦	Introduced quadratic adjustment logic for levels correction.
	◦	Added methods for handling edge cases in transformations, such as zeroed differences in wavelet transforms.
	◦	Expanded grayscale transformation support with flexible split percentages for partial application.
3. Interactive View Enhancements:
	◦	Introduced ImageViewerWithController, a GUI-based viewer providing a user-friendly interface for loading, modifying, and saving images.
	◦	Improved histogram generation and display, including a graphical representation of RGB channels.
	◦	Added dynamic buttons in the GUI for operations like sepia, flipping,compression,Downscale etc.
4. Improved Design Structure:
	◦	Introduced marker interfaces like RGBInterface to enhance design semantics and type safety.
	◦	Encapsulated repetitive logic into utility methods (e.g., kernel application, padding for compression).
	◦	Strengthened the relationship between the ImageModel, ImageController, and ImageView for better modularity and extensibility.
5. Command Processing Overhaul:
	◦	Shifted from hardcoding command parsing in the controller to a more flexible design using the Command interface and command classes.
6. Error Handling and Validation:
	◦	Improved error handling across all components, ensuring invalid inputs and unsupported operations provide clear feedback (e.g., for split percentage bounds, compression limits).
7. Added Complexity in Image Operations:
	◦	Operations like colorCorrect now utilize histogram data for enhanced accuracy.
	◦	Compression integrates advanced algorithms for thresholding and inverse transformations.

These changes reflect a significant evolution of the system, emphasizing modularity, GUI interaction, and advanced image processing features. The current design is more robust, extensible, and user-focused compared to the initial implementation.





CONTROLLER IN DETAIL

1 .ImageControllerInterface (Interface)
Purpose:This interface defines the contract for an image controller responsible for managing image operations, including loading, saving, and processing commands for image manipulation.
Key Methods:
	•	loadImage(String filePath, String imageName):Abstract method to load an image from a file and assign it a name. This provides the flexibility to support multiple image formats.
	•	saveImage(String filePath, String imageName):Abstract method for saving an image to a specified file path. Ensures uniform handling of image output.
	•	getImages():Retrieves a Map containing all loaded images, with image names as keys and corresponding 2D RGB arrays as values.
	•	commandParser(String command):Parses and processes a single command string for performing operations like transformations, enhancements, or other manipulations on images.
	•	processCommands(String[] tokens):Handles batch execution of commands for efficient image processing and automation.


2. ImageController (Class)
Purpose:This class implements the ImageControllerInterface and serves as the central controller for loading, saving, and processing images. It handles command registration and execution, and bridges communication between the model and view.
Key Features:
	•	Image Management: Stores loaded images in a HashMap for easy retrieval and manipulation.
	•	Command Processing: Registers commands dynamically and executes them based on user input.
	•	Integration: Coordinates interactions between the controller, model, and view components.
Key Methods:
	•	loadImage(String filePath, String imageName):Loads an image from the specified file path and assigns it a unique name, utilizing an appropriate ImageLoaderAndSaver instance from the factory.
	•	saveImage(String filePath, String imageName):Saves the specified image to a file at the provided path, using the correct ImageLoaderAndSaver for the format.
	•	getImages():Returns a map of all loaded images, where keys are image names and values are 2D arrays of RGB values.
	•	commandParser(String command):Parses a single command string or processes a file of commands. Supports comments and ignores empty lines in command files.
	•	processCommands(String[] tokens):Executes commands using the registered Command objects. Outputs errors if a command fails or is unrecognized.
Command Registry:
	•	Dynamically registers and manages command objects.
	•	Example commands include:
	◦	Image Loading and Saving: load, save.
	◦	Color Adjustments: red-component, green-component, blue-component, value-component, intensity-component, luma-component.
	◦	Image Transformations: horizontal-flip, vertical-flip, sepia, blur, sharpen, brighten.
	◦	Advanced Operations: compress, rgb-split, rgb-combine, histogram, color-correct, levels-adjust.
Error Handling:
	•	Handles IO exceptions gracefully during loading, saving, and command execution.
	•	Outputs helpful error messages for unknown or invalid commands.
Usage:
	•	Acts as the main entry point for handling image operations.
	•	Processes user input via commands to execute a wide range of image processing tasks efficiently.




3. ImageLoaderAndSaver (Abstract Class)
Purpose:This abstract class defines the core functionalities for loading and saving images. It serves as a blueprint for implementing classes that handle image input/output operations across various formats.
Key Methods:
	•	loadImage(String filePath, String imageName):Abstract method for loading an image from a specified file path and converting it into a 2D array of RGB values.
	•	saveImage(String filePath, String imageName):Abstract method for saving an image (as a 2D RGB array) to a specified file path.
	•	getFileFormat(String filePath):Extracts the file extension from the provided file path to determine the format. This utility method supports format-specific handling by subclasses.
	•	arrayToImage(RGB[][] image):Converts a 2D RGB array into a BufferedImage object, enabling easy manipulation and saving of the image using Java's BufferedImage API.
Key Features:
	•	Extensibility:Subclasses must implement the loadImage and saveImage methods to support specific image formats.
	•	Utility Functions:
	◦	getFileFormat: Simplifies format determination, a crucial step for format-specific processing.
	◦	arrayToImage: Encapsulates the logic for converting RGB arrays to BufferedImage, reducing boilerplate code in subclasses.
Usage:
	•	Acts as a base class for format-specific loaders and savers (e.g., PPMImageLoaderAndSaver, DefaultImageLoaderAndSaver).
	•	Subclasses leverage its utility methods to simplify format-specific implementation details.




4. ImageLoaderFactory (Class)

Purpose:The ImageLoaderFactory is responsible for dynamically creating instances of ImageLoaderAndSaver based on the file type of the image. This ensures that the correct loader/saver logic is applied for different image formats.Key Features:
	◦	Dynamic Instance Creation:The factory determines the file type by examining the file extension and returns the corresponding implementation of ImageLoaderAndSaver.
	◦	Support for Multiple Formats:Supports .ppm, .jpg, and .png file types by delegating to specific subclasses:
	▪	PPMImageLoaderAndSaver: Handles .ppm files.
	▪	DefaultImageLoaderAndSaver: Handles standard image formats such as .jpg and .png.
Key Method:
	◦	getImageLoader(String filePath, ImageControllerInterface imageController):
	▪	Purpose: Creates and returns an appropriate instance of ImageLoaderAndSaver based on the file extension in the given file path.
	▪	Parameters:
	▪	filePath: The path of the image file.
	▪	imageController: An instance of the ImageControllerInterface to be used by the loader and saver.
	▪	Returns: An instance of ImageLoaderAndSaver specific to the detected file type.
	▪	Throws: UnsupportedOperationException if the file type is unsupported.
Usage:
	◦	Ensures that the correct logic is applied when loading or saving images, abstracting file-type-specific details from the controller.
	◦	Provides a clean and extensible mechanism to support additional file formats in the future by introducing new ImageLoaderAndSaver subclasses.



5. DefaultImageLoaderAndSaver (Class)


Purpose:This class extends ImageLoaderAndSaver and provides functionality for loading and saving images in standard formats supported by Java’s ImageIO library (e.g., JPEG and PNG).
Key Features:
	•	Standard Format Support:Handles common image formats like .jpg and .png for loading and saving.
	•	Pixel-Level Conversion:Converts images into 2D arrays of RGB objects for processing and back into BufferedImage for saving.
Key Methods:
	•	loadImage(String filePath, String imageName):
	◦	Purpose: Loads an image from the specified file path and converts it into a 2D array of RGB values.
	◦	Steps:
	1	Reads the image using ImageIO.read().
	2	Extracts the width and height of the image.
	3	Iterates through each pixel, extracting red, green, and blue values, and stores them in an RGB[][] array.
	◦	Throws:
	▪	IOException if the image cannot be read or if the file path is invalid.
	◦	Returns:A 2D array of RGB values representing the image.
	•	saveImage(String filePath, String imageName):
	◦	Purpose: Saves the specified image as a standard format file at the given file path.
	◦	Steps:
	1	Retrieves the RGB[][] array for the specified image from the controller.
	2	Converts the array into a BufferedImage using arrayToImage.
	3	Uses ImageIO.write() to save the image in the specified format (jpg or png) based on the file extension.
	◦	Throws:
	▪	IOException if an error occurs during the file writing process.
Error Handling:
	•	Ensures graceful handling of IO errors during both loading and saving operations.
	•	Validates file format by checking the extension extracted using getFileFormat.
Usage:
	•	Specialized for applications requiring support for standard image formats like .jpg and .png.
	•	Utilized by the ImageController for handling non-PPM image files.



6. PPMImageLoaderAndSaver (Class)

Purpose:This class extends ImageLoaderAndSaver and provides functionality to load and save images in the PPM (Portable Pixmap) format. It handles parsing and generating PPM files, ensuring compatibility with this ASCII-based image format.Key Features:
	◦	PPM File Parsing:Reads and parses the ASCII representation of PPM files (P3 format), extracting image dimensions and pixel data.
	◦	Comment Handling:Skips comment lines in the PPM file, ensuring they do not interfere with the parsing process.
	◦	Saving in PPM Format:Writes image data in the P3 format with headers for dimensions and maximum color value.
Key Methods:
	◦	loadImage(String filePath, String imageName):
	▪	Purpose: Loads a PPM image from the given file path and converts it into a 2D array of RGB objects.
	▪	Steps:
	1	Reads the PPM file line-by-line, ignoring comments (lines starting with #).
	2	Validates the file format by checking the P3 header.
	3	Parses image dimensions (width and height) and the maximum color value.
	4	Reads pixel data (R, G, B values) and constructs a 2D array of RGB objects.
	▪	Throws:
	▪	FileNotFoundException if the file cannot be found.
	▪	IOException if the file is not in a valid PPM format.
	▪	Returns:A 2D array of RGB values representing the image.
	◦	saveImage(String filePath, String imageName):
	▪	Purpose: Saves an image as a PPM file at the specified file path.
	▪	Steps:
	1	Retrieves the 2D RGB array of the image from the controller's repository.
	2	Writes the P3 header, image dimensions, and maximum color value to the file.
	3	Iterates through the pixel array and writes R, G, and B values in ASCII format.
	▪	Throws:
	▪	IOException if an error occurs during the file writing process.
Error Handling:
	◦	Validates the PPM file format (P3) before loading.
	◦	Skips invalid lines and ensures only pixel data is processed.
	◦	Handles IOException gracefully, providing meaningful error messages for loading and saving issues.
Usage:
	◦	Specialized for applications requiring PPM format support.
	◦	Used by the controller to handle .ppm files for both input and output.



7. Command (Interface)
Purpose:This interface defines the contract for all command classes in the system. Each command encapsulates an action or operation that can be executed, such as image manipulation or enhancement.
Key Features:
	•	Uniform Command Execution:Provides a standardized method for executing commands with input arguments.
	•	Extensibility:New commands can be added to the system by implementing this interface, ensuring compatibility with the existing infrastructure.
Key Method:
	•	void execute(String[] tokens) throws IOException:
	◦	Purpose: Executes the command using the provided arguments.
	◦	Parameters:
	▪	tokens: An array of strings representing the command arguments. The format and interpretation of tokens depend on the specific implementation.
	◦	Throws:
	▪	IOException if an error occurs during command execution.
Usage:
	•	All command classes (e.g., BrightenCommand, BlurAndSharpenCommand) implement this interface, providing the specific behavior for each operation.
	•	Enables the ImageController to execute various commands dynamically without knowing their specific implementations.



8. GetAndRegisterCommands (Interface)


Purpose:This interface defines the functionality for managing a registry of commands. It allows registering new commands and retrieving registered commands for execution.
Key Features:
	•	Command Registration:Provides a mechanism to associate a command name with a Command implementation dynamically.
	•	Command Retrieval:Facilitates retrieving commands by their registered names, enabling the ImageController to execute them seamlessly.
Key Methods:
	•	void register(String commandName, Command command):
	◦	Purpose: Registers a command with a specific name in the command registry.
	◦	Parameters:
	▪	commandName: The unique name of the command to register.
	▪	command: The Command object implementing the operation associated with the name.
	•	Command get(String commandName):
	◦	Purpose: Retrieves a registered command by its name.
	◦	Parameters:
	▪	commandName: The name of the command to retrieve.
	◦	Returns:The Command object associated with the given name or null if the command is not registered.
Usage:
	•	Acts as the backbone of the command registry in the ImageController.
	•	Enables dynamic addition and retrieval of commands for versatile image processing operations.



9. CommandRegistry (Class)


Purpose:This class implements the GetAndRegisterCommands interface and serves as a registry for managing command objects. It allows registering and retrieving commands dynamically by their names.
Key Features:
	•	Command Storage:Maintains a Map of command names to Command objects for efficient retrieval.
	•	Dynamic Registration:Supports adding new commands to the registry at runtime.
	•	Seamless Retrieval:Provides quick access to registered commands by their names.
Key Methods:
	•	void register(String commandName, Command command):
	◦	Purpose: Registers a command with the specified name.
	◦	Parameters:
	▪	commandName: The unique name to identify the command.
	▪	command: The Command object to associate with the name.
	◦	Behavior:
	▪	Stores the commandName and corresponding Command in an internal HashMap.
	▪	Overwrites the previous command if a name is re-registered.
	•	Command get(String commandName):
	◦	Purpose: Retrieves a registered command by its name.
	◦	Parameters:
	▪	commandName: The name of the command to retrieve.
	◦	Returns:The Command object associated with the name, or null if the command is not found.
Usage:
	•	Acts as the command registry for the ImageController, allowing dynamic addition and execution of commands.
	•	Used to manage all image processing commands, such as brighten, blur, save, etc.



10. LoadCommand (Class)


Purpose:This class implements the Command interface and handles the loading of images into the system. It interacts with the ImageController to load an image from a specified file path and associate it with a given name.
Key Features:
	•	Image Loading:Supports loading images of various formats (e.g., PPM, JPG, PNG) into the system by delegating the operation to the controller.
	•	Error Handling:Validates the number of arguments provided to ensure proper usage of the command.
Key Method:
	•	execute(String[] tokens):
	◦	Purpose: Executes the image loading operation.
	◦	Parameters:
	▪	tokens: Command arguments, where:
	1	tokens[1]: The file path of the image to be loaded.
	2	tokens[2]: The name to associate with the loaded image.
	◦	Behavior:
	▪	Validates the token length to ensure both the file path and image name are provided.
	▪	Calls the loadImage method of the ImageController to perform the actual loading operation.
	◦	Error Handling:
	▪	Prints an error message if fewer than three tokens are provided.
Usage:
	•	Invoked by the ImageController when the "load" command is executed.
	•	Allows users to load images dynamically into the system for further processing.



11. SaveCommand (Class)


Purpose:This class implements the Command interface and handles the saving of images. It interacts with the ImageController to save a previously loaded or processed image to a specified file path.
Key Features:
	•	Image Saving:Supports saving images in various formats (e.g., PPM, JPG, PNG) by delegating the operation to the controller.
	•	Error Handling:Ensures that the command includes both a file path and an image name to execute successfully.
Key Method:
	•	execute(String[] tokens):
	◦	Purpose: Executes the image saving operation.
	◦	Parameters:
	▪	tokens: Command arguments, where:
	1	tokens[1]: The file path to save the image to.
	2	tokens[2]: The name of the image to be saved.
	◦	Behavior:
	▪	Validates the token length to ensure both the file path and image name are provided.
	▪	Calls the saveImage method of the ImageController to perform the actual saving operation.
	◦	Error Handling:
	▪	Prints an error message if fewer than three tokens are provided.
Usage:
	•	Invoked by the ImageController when the "save" command is executed.
	•	Allows users to save images after loading or processing them.



12. BlurAndSharpenCommand (Class)

Purpose:This class implements the Command interface and is responsible for executing blur or sharpen operations on an image. It delegates the actual processing to the ImageModelInterface and supports optional split operations based on user-specified percentages.Key Features:
	◦	Dynamic Blur/Sharpen Execution:Executes either a blur or sharpen operation based on the provided command (blur or sharpen).
	◦	Split Operation Support:Allows splitting the effect with a user-defined percentage for finer control over the intensity.
	◦	Input Validation:Ensures that arguments like split percentages are within the valid range (0–100).
Key Method:
	◦	execute(String[] tokens):
	▪	Purpose: Executes the blur or sharpen operation based on the provided command tokens.
	▪	Parameters:
	▪	tokens: Command arguments, where:
	1	tokens[0]: The operation (blur or sharpen).
	2	tokens[1]: Source image name.
	3	tokens[2]: Destination image name.
	4	Optional: "split" (tokens[3]) to enable split operation.
	5	Optional: splitPercentage (tokens[4]) for custom split intensity (integer between 0–100).
	▪	Behavior:
	▪	Executes a standard operation when only source and destination names are provided.
	▪	Performs a split operation with specified intensity if "split" and splitPercentage are included.
	▪	Error Handling:
	▪	Catches NumberFormatException for invalid split percentages.
	▪	Throws and handles IllegalArgumentException for split percentages outside the valid range (0–100).
	▪	Prints meaningful error messages for invalid argument lengths or unexpected input.
Usage:
	◦	Invoked by the ImageController when the "blur" or "sharpen" command is executed.
	◦	Provides dynamic and customizable control over blur/sharpen operations, with optional split adjustments for nuanced effects.

13. BrightenCommand (Class)
Purpose:This class implements the Command interface and performs brightness adjustment operations on an image by interacting with the ImageModelInterface.
Key Features:
	•	Brightness Adjustment:Allows users to increase or decrease the brightness of an image by specifying a brightness value.
	•	Error Handling:Validates user input to ensure the brightness value is an integer and the required arguments are provided.
Key Method:
	•	execute(String[] tokens):
	◦	Purpose: Executes the brightness adjustment operation.
	◦	Parameters:
	▪	tokens: Command arguments, where:
	1	tokens[1]: Brightness value (integer). Positive to brighten, negative to darken.
	2	tokens[2]: Source image name.
	3	tokens[3]: Destination image name.
	◦	Behavior:
	▪	Retrieves the brightness value from tokens[1] and applies it to the specified source image.
	▪	Stores the adjusted image under the specified destination name.
	◦	Error Handling:
	▪	Prints an error if the required number of tokens is not provided.
	▪	Catches NumberFormatException for invalid brightness values.
	▪	Provides user-friendly messages for missing or invalid arguments.
Usage:
	•	Invoked by the ImageController when the "brighten" command is executed.
	•	Enables dynamic brightness adjustments to enhance or tone down the appearance of images.



14. ColorCorrectCommand (Class)


Purpose:This class implements the Command interface and performs color correction operations on an image. It interacts with the ImageModelInterface to apply a global or split-based color correction.
Key Features:
	•	Color Correction:Adjusts the color balance of an image for enhanced visual quality.
	•	Split Adjustment Support:Allows users to specify a split percentage for finer control over the correction intensity.
	•	Error Handling:Validates input arguments and ensures split percentages are valid integers.
Key Method:
	•	execute(String[] tokens):
	◦	Purpose: Executes the color correction operation based on user-provided arguments.
	◦	Parameters:
	▪	tokens: Command arguments, where:
	1	tokens[1]: Source image name.
	2	tokens[2]: Destination image name.
	3	Optional: "split" (tokens[3]) to indicate split adjustment.
	4	Optional: splitPercentage (tokens[4]) for custom intensity.
	◦	Behavior:
	▪	Applies a default correction with an intensity of 100 when "split" is not specified.
	▪	Uses the provided splitPercentage for split-based correction if "split" and a valid percentage are provided.
	◦	Error Handling:
	▪	Prints an error message if arguments are invalid (e.g., incorrect length or non-integer split percentage).
Usage:
	•	Invoked by the ImageController when the "color-correct" command is executed.
	•	Enables users to apply flexible color adjustments to images dynamically.



15. CompressCommand (Class)

Purpose:This class implements the Command interface and performs image compression operations by reducing the image size or quality based on a specified compression percentage. It interacts with the ImageModelInterface to execute the operation.
Key Features:
	•	Image Compression:Reduces the size or quality of an image based on a user-defined compression percentage.
	•	Validation:Ensures the compression percentage is a valid integer within the range of 0–100.
	•	Error Handling:Provides clear error messages for invalid arguments or out-of-range compression percentages.
Key Method:
	•	execute(String[] tokens):
	◦	Purpose: Executes the image compression operation.
	◦	Parameters:
	▪	tokens: Command arguments, where:
	1	tokens[1]: Compression percentage (integer between 0–100).
	2	tokens[2]: Name of the source image.
	3	tokens[3]: Name of the destination image to store the compressed result.
	◦	Behavior:
	▪	Validates the token length to ensure all required arguments are provided.
	▪	Parses the compression percentage and validates its range.
	▪	Delegates the compression operation to the compressImage method in the ImageModelInterface.
	◦	Error Handling:
	▪	Prints an error if fewer than four tokens are provided.
	▪	Catches and reports errors for non-integer or out-of-range compression percentages.
	▪	Handles invalid inputs gracefully with user-friendly messages.
Usage:
	•	Invoked by the ImageController when the "compress" command is executed.
	•	Enables users to reduce image size or quality for optimization purposes.



16. FlipCommand (Class)


Purpose:This class implements the Command interface and handles flipping operations on images. It interacts with the ImageModelInterface to perform horizontal or vertical flips.
Key Features:
	•	Flipping Operations:Supports flipping images either horizontally or vertically based on the provided command.
	•	Validation:Ensures that the flip type is valid (horizontal-flip or vertical-flip) before proceeding.
	•	Error Handling:Handles missing or invalid arguments gracefully, providing user-friendly error messages.
Key Method:
	•	execute(String[] tokens):
	◦	Purpose: Executes the flip operation based on the specified command.
	◦	Parameters:
	▪	tokens: Command arguments, where:
	1	tokens[0]: The type of flip operation (horizontal-flip or vertical-flip).
	2	tokens[1]: Source image name.
	3	tokens[2]: Destination image name.
	◦	Behavior:
	▪	Validates the presence of source and destination image names.
	▪	Ensures the flip type is either horizontal-flip or vertical-flip.
	▪	Delegates the flipping operation to the flipImage method in the ImageModelInterface.
	◦	Error Handling:
	▪	Prints an error if the token length is less than three.
	▪	Prints an error if the flip type is invalid.
Usage:
	•	Invoked by the ImageController when the "horizontal-flip" or "vertical-flip" command is executed.
	•	Allows users to perform mirror transformations on images with ease.



17. GreyscaleCommand (Class)

Purpose:This class implements the Command interface and performs greyscale transformations on images. It supports various greyscale transformations, such as red, green, blue, intensity, luma, and value components, and allows for optional split-based adjustments.
Key Features:
	•	Greyscale Transformations:Applies specific greyscale transformations (e.g., red-component, green-component, blue-component, etc.) to images.
	•	Split Adjustment:Enables partial application of the transformation using a split percentage.
	•	Validation and Error Handling:Ensures valid arguments for the transformation type, source and destination image names, and split percentages.
Key Method:
	•	execute(String[] tokens):
	◦	Purpose: Executes the specified greyscale transformation.
	◦	Parameters:
	▪	tokens: Command arguments, where:
	1	tokens[0]: The type of transformation (e.g., red-component, intensity-component).
	2	tokens[1]: Source image name.
	3	tokens[2]: Destination image name.
	4	Optional: "split" (tokens[3]) to indicate split adjustment.
	5	Optional: splitPercentage (tokens[4]) for custom adjustment intensity.
	◦	Behavior:
	▪	Applies the transformation with an intensity of 100 if "split" is not specified.
	▪	Uses the provided split percentage for partial application of the transformation if "split" and a valid percentage are provided.
	▪	Delegates the transformation to the applyGreyscaleTransformation method in the ImageModelInterface.
	◦	Error Handling:
	▪	Prints an error if fewer than three tokens are provided.
	▪	Validates and catches errors for invalid or out-of-range split percentages.
Usage:
	•	Invoked by the ImageController when commands such as "red-component", "luma-component", etc., are executed.
	•	Enables users to perform detailed greyscale transformations on images with optional intensity adjustments.



18. HistogramCommand (Class)


Purpose:This class implements the Command interface and generates a histogram for a given image. It interacts with the ImageViewInterface to create and store the histogram representation.
Key Features:
	•	Histogram Generation:Creates a visual representation of the pixel intensity distribution for a specified source image.
	•	Validation:Ensures that both the source image name and the destination name for storing the histogram are provided.
	•	Error Handling:Captures and reports errors during the histogram generation process.
Key Method:
	•	execute(String[] tokens):
	◦	Purpose: Executes the histogram generation operation.
	◦	Parameters:
	▪	tokens: Command arguments, where:
	1	tokens[1]: Source image name.
	2	tokens[2]: Destination name for the generated histogram.
	◦	Behavior:
	▪	Validates the presence of both the source image name and destination name.
	▪	Delegates the generation and storage of the histogram to the generateAndStoreHistogram method in the ImageViewInterface.
	◦	Error Handling:
	▪	Prints an error if fewer than three tokens are provided.
	▪	Catches and displays exceptions raised during histogram generation.
Usage:
	•	Invoked by the ImageController when the "histogram" command is executed.
	•	Allows users to analyze image pixel intensity distributions visually.



19. LevelsAdjustCommand (Class)
Purpose:This class implements the Command interface and executes the levels adjustment operation on an image using the ImageModelInterface. It adjusts the brightness levels of an image based on specified parameters for black, midtones, and white levels.
Key Features:
	•	Flexible Levels Adjustment:Supports both standard levels adjustment and split adjustment with a custom percentage.
	•	Error Handling:Includes robust input validation for integer parsing and correct token length to avoid runtime issues.
Key Method:
	•	execute(String[] tokens):
	◦	Purpose: Executes the levels adjustment operation based on user-provided tokens.
	◦	Parameters:
	▪	tokens: Command arguments for adjusting levels, including:
	1	b (black level): An integer specifying the adjustment for the black levels.
	2	m (midtones): An integer specifying the adjustment for midtones.
	3	w (white level): An integer specifying the adjustment for the white levels.
	4	tokens[4]: Name of the source image.
	5	tokens[5]: Name of the destination image.
	6	Optional: "split" (token[6]) to indicate split adjustment.
	7	Optional: splitPercentage (token[7]) for custom split adjustment percentage.
	◦	Behavior:
	▪	Adjusts levels with default parameters when "split" is not specified.
	▪	Uses a custom split percentage if "split" and splitPercentage are provided.
	◦	Error Handling:
	▪	Prints an error message if arguments are invalid (e.g., wrong token length or invalid integer values).
	▪	Handles NumberFormatException gracefully when non-integer values are provided for numeric parameters.
Usage:
	•	Invoked by the ImageController when the "levels-adjust" command is executed.
	•	Provides dynamic control over image brightness adjustments based on user-specified levels and split percentages.




20. RGBCombineCommand (Class)

Purpose:This class implements the Command interface and combines three greyscale images (representing the red, green, and blue channels) into a single colored image. It interacts with the ImageModelInterface to perform the operation.
Key Features:
	•	Channel Combination:Combines separate greyscale images for the red, green, and blue channels into a full-color image.
	•	Validation:Ensures the presence of three source image names and one destination image name for successful execution.
	•	Error Handling:Catches and displays errors that occur during the combination process.
Key Method:
	•	execute(String[] tokens):
	◦	Purpose: Executes the RGB combination operation.
	◦	Parameters:
	▪	tokens: Command arguments, where:
	1	tokens[1]: Name of the red channel source image.
	2	tokens[2]: Name of the green channel source image.
	3	tokens[3]: Name of the blue channel source image.
	4	tokens[4]: Name of the destination image to store the combined result.
	◦	Behavior:
	▪	Validates the presence of the required arguments (three source names and one destination name).
	▪	Calls the combineGreyscale method in the ImageModelInterface to perform the combination.
	◦	Error Handling:
	▪	Prints an error message if fewer than five tokens are provided.
	▪	Catches and reports any exceptions raised during the combination process.
Usage:
	•	Invoked by the ImageController when the "rgb-combine" command is executed.
	•	Enables users to reconstruct colored images from individual greyscale channels.



21. RGBSplitCommand (Class)


Purpose:This class implements the Command interface and splits an image into three separate greyscale images representing the red, green, and blue channels. It interacts with the ImageModelInterface to perform the operation.
Key Features:
	•	Channel Splitting:Extracts and isolates the red, green, and blue channels from a source image into separate greyscale images.
	•	Validation:Ensures the presence of the source image name and three destination image names for the operation.
	•	Error Handling:Catches and displays errors encountered during the channel splitting process.
Key Method:
	•	execute(String[] tokens):
	◦	Purpose: Executes the RGB splitting operation.
	◦	Parameters:
	▪	tokens: Command arguments, where:
	1	tokens[1]: Name of the source image.
	2	tokens[2]: Name of the destination image for the red channel.
	3	tokens[3]: Name of the destination image for the green channel.
	4	tokens[4]: Name of the destination image for the blue channel.
	◦	Behavior:
	▪	Validates the presence of the source image name and three destination image names.
	▪	Calls the applyGreyscaleTransformation method in the ImageModelInterface three times for the red, green, and blue channels.
	◦	Error Handling:
	▪	Prints an error message if fewer than five tokens are provided.
	▪	Catches and reports any exceptions raised during the splitting process.
Usage:
	•	Invoked by the ImageController when the "rgb-split" command is executed.
	•	Allows users to analyze or manipulate individual color channels from an image.




22. SepiaCommand (Class)

Purpose:This class implements the Command interface and applies a sepia tone transformation to an image. It supports both full and split-based sepia adjustments by interacting with the ImageModelInterface.
Key Features:
	•	Sepia Tone Transformation:Applies a sepia tone effect to enhance images with a warm, antique aesthetic.
	•	Split Adjustment:Allows partial sepia application using a user-specified split percentage.
	•	Validation and Error Handling:Ensures valid input arguments, including a valid split percentage range (0–100).
Key Method:
	•	execute(String[] tokens):
	◦	Purpose: Executes the sepia tone transformation.
	◦	Parameters:
	▪	tokens: Command arguments, where:
	1	tokens[1]: Name of the source image.
	2	tokens[2]: Name of the destination image.
	3	Optional: "split" (tokens[3]) to indicate partial transformation.
	4	Optional: splitPercentage (tokens[4]) to define the intensity of the sepia transformation.
	◦	Behavior:
	▪	Applies a full sepia tone with an intensity of 100 when "split" is not specified.
	▪	Applies a split-based sepia tone if "split" and a valid percentage are provided.
	▪	Delegates the transformation to the applySepiaTone method in the ImageModelInterface.
	◦	Error Handling:
	▪	Prints an error message if fewer than three tokens are provided.
	▪	Validates and catches errors for invalid or out-of-range split percentages.
	▪	Handles non-integer split percentage inputs gracefully.
Usage:
	•	Invoked by the ImageController when the "sepia" command is executed.
	•	Enables users to apply sepia tone transformations with customizable intensity levels.




23 DownscaleCommand (Class)
  Purpose:This class implements the Command interface and is responsible for executing the downscaling of an image to a specified width and height. It delegates the actual resizing logic to the ImageModelInterface.

  Key Features:
  	•	Dynamic Image Resizing: Allows downscaling images to custom dimensions (width and height).
  	•	Input Validation: Ensures that width and height are valid integers before proceeding with the operation.
  	•	Error Handling: Gracefully manages cases of missing or invalid arguments, providing detailed error messages.

  Key Method:
  	•	execute(String[] tokens):
  	◦	Purpose: Executes the downscaling operation using the parameters provided in the command tokens.
  	◦	Parameters:
  	▪	tokens: Command arguments, where:
  	1	tokens[0]: The command name (e.g., "downscale").
  	2	tokens[1]: Target width (integer).
  	3	tokens[2]: Target height (integer).
  	4	tokens[3]: Source image name.
  	5	tokens[4]: Destination image name.
  	◦	Behavior:
  	▪	Validates the number of arguments (minimum 5).
  	▪	Parses targetWidth and targetHeight as integers.
  	▪	Delegates the downscaling logic to the model's downscaleImage method using the extracted parameters.
  	◦	Error Handling:
  	▪	Catches NumberFormatException for invalid width or height arguments and displays an error message:arduinoCopy codeError: Width and height must be valid integers.
  	▪
  	▪	Prints an error if the command has insufficient arguments:arduinoCopy codeError: 'downscale' requires target width, target height, source image name, and destination image name.
  	▪

  Usage:
  	•	Invoked by the ImageController when the "downscale" command is executed.
  	•	Provides the functionality to reduce an image's resolution dynamically as part of the overall image manipulation operations.




MODEL IN DETAIL





1. ImageModelInterface (Interface)

Purpose:Defines the contract for image processing operations. It serves as the backbone for applying transformations, filters, adjustments, and manipulations to images.
Key Features:
	◦	Comprehensive Operations:Provides methods for a wide range of image manipulations, including sepia tone, grayscale transformations, brightness adjustments, and more.
	◦	Split-Based Operations:Supports partial transformations using a split percentage for finer control.
	◦	Validation and Error Handling:Methods throw NoSuchElementException for invalid inputs or missing images.
Key Methods:
	◦	applySepiaTone(String imageName, String destinationImageName, int splitPercentage):Applies a sepia tone effect to an image, with support for split-based adjustments.
	◦	blurOrSharpen(String imageName, String destinationImageName, int splitPercentage, String operationName):Applies blur or sharpen effects to an image, specified by the operation name (blur or sharpen).
	◦	applyGreyscaleTransformation(String imageName, String destinationImageName, String transformationType, int splitPercentage):Transforms an image to grayscale based on a component (e.g., red, green, blue, luma).
	◦	changeBrightness(String imageName, String destinationImageName, int value):Adjusts the brightness of an image by a specified value.
	◦	colorCorrect(String imageName, String destinationImageName, int splitPercentage):Performs color correction to adjust histogram peaks within the specified split percentage.
	◦	levelsAdjust(String imageName, String destImageName, int b, int m, int w, int splitPercentage):Adjusts image brightness levels using black, mid-tone, and white points.
	◦	flipImage(String imageName, String destinationImageName, String operationName):Flips the image either horizontally or vertically based on the operation name.
	◦	combineGreyscale(String imageName, String redImage, String greenImage, String blueImage):Combines red, green, and blue greyscale images into a single colored image.
	◦	compressImage(String compressionPercentage, String imageName, String destinationImageName):Compresses the image by reducing its size or quality based on the specified percentage.
Usage:
	◦	Acts as the core API for image manipulation.
	◦	Enables the controller to perform various image operations in a modular and extensible manner.



2. ImageModel (Class)

Purpose:The ImageModel class implements the ImageModelInterface and provides functionality for various image transformations and adjustments. It interacts with the ImageControllerInterface to access and store images and utilizes the ImageViewInterface for visual feedback and histogram data.

Key Features:
	Comprehensive Image Processing:
	◦	Sepia Tone: Applies a sepia effect with optional split adjustments.
	◦	Blur and Sharpen: Uses kernel-based operations for blurring or sharpening an image.
	◦	Brightness Adjustment: Dynamically adjusts image brightness.
	◦	Greyscale Transformation: Converts images to greyscale based on specific components.
	◦	Image Compression: Compresses images using Haar wavelet transforms.
	◦	Color Correction: Balances color intensities using histogram peaks.
	◦	Levels Adjustment: Uses quadratic equations to fine-tune brightness and contrast.
	◦	Channel Combination and Splitting: Combines or splits RGB channels for advanced image manipulation.
	◦	Image Flipping: Performs horizontal and vertical flips.
	◦	Image Downscale: Downscales an image to the required height and width.
	Split-Based Processing:
	◦	Allows partial transformations to be applied to specific sections of an image (based on percentage width).
	Error Handling:
	◦	Throws meaningful exceptions like NoSuchElementException and IllegalArgumentException for invalid operations or missing images.
	Utility Functions:
	◦	Includes utilities for quadratic adjustments, histogram calculations, color corrections, and Haar wavelet transformations.

Key Methods:
	Image Transformation Methods:
	◦	applySepiaTone: Applies sepia effect based on split percentage.
	◦	blurOrSharpen: Applies blur or sharpen using convolution kernels.
	◦	changeBrightness: Adjusts pixel brightness values.
	◦	applyGreyscaleTransformation: Converts an image to greyscale based on components (e.g., red, green, luma).
	◦	flipImage: Flips an image horizontally or vertically.
	Advanced Operations:
	◦	compressImage: Performs image compression using Haar wavelet transforms.
	◦	colorCorrect: Adjusts color intensities based on histogram data.
	◦	levelsAdjust: Adjusts brightness levels using quadratic functions.
	◦	combineGreyscale: Combines three greyscale images into an RGB image.
	Helper Methods:
	◦	getImageData: Prepares image data for transformations with split processing.
	◦	applyKernel: Applies a convolution kernel to compute pixel intensity changes.
	◦	findPeak and calculateTargetPeak: Used for histogram-based color corrections.
	◦	Haar Wavelet Transform Utilities: Functions for forward and inverse Haar transformations.
	◦	Padding Utilities: Pads images to make them square matrices for compression.
	Validation and Exception Handling:
	◦	Ensures valid inputs for operations like brightness, flipping, and transformations.
	◦	Throws NoSuchElementException for missing images or invalid operations.

Key Data Structures:
	•	ImageData: A private nested class representing image data with:
	◦	Original and transformed RGB arrays.
	◦	Image dimensions (width, height).
	◦	Split point for partial transformations.

Usage:
	•	The ImageModel is the core computational component of the system, enabling the controller to perform advanced image processing tasks. It integrates seamlessly with the controller and view to provide a modular, scalable architecture for handling diverse image manipulations.




3. RGBInterface (Interface)


Purpose:Acts as a marker interface for the RGB class to improve design and maintainability. While it does not define any methods, it provides a way to categorize the RGB class within the system for type safety and future extensibility.

Key Features:
	•	Marker Interface:
	◦	Used to tag the RGB class, enabling it to be identified and processed distinctly where necessary.
	•	Design Enhancement:
	◦	Improves design by introducing an abstraction layer for the RGB class, allowing flexibility for potential enhancements.

Usage:
	•	Implemented by the RGB class to indicate its role in representing pixel data.
	•	Ensures better design practices and supports extensibility without imposing specific functionality requirements.




4. RGB (Class)

Purpose:Represents an RGB color with red, green, and blue components. It is the fundamental building block for representing pixel data in images and implements the RGBInterface as a marker interface for better design.

Key Features:
	Color Representation:
	◦	Encapsulates the red, green, and blue components of a color using integer values.
	Marker Implementation:
	◦	Implements RGBInterface to integrate seamlessly with the system's design architecture.
	Simple Design:
	◦	Lightweight class designed for ease of use in image manipulation tasks.

Key Properties:
	•	red: Represents the red component of the color.
	•	green: Represents the green component of the color.
	•	blue: Represents the blue component of the color.

Key Methods:
	•	Constructor:
	◦	RGB(int red, int green, int blue):
	▪	Constructs an RGB object with the specified red, green, and blue values.
	▪	Parameters:
	▪	red: Value of the red component (0–255).
	▪	green: Value of the green component (0–255).
	▪	blue: Value of the blue component (0–255).

Usage:
	•	Used as the primary data structure for representing and manipulating pixel colors in images.
	•	Plays a crucial role in the system's image processing operations, providing a simple yet powerful way to handle color data.




VIEW IN DETAIL:






1. ImageViewInterface (Interface)


Purpose:Provides methods for generating and handling graphical outputs related to images, such as histograms and visualization data. It acts as the connection point for graphical representations within the system.

Key Features:
	•	Histogram Generation:
	◦	Supports creating and saving histograms for specified images.
	•	Data Calculation:
	◦	Provides frequency counts for pixel intensities across red, green, and blue channels.

Key Methods:
	generateAndStoreHistogram(String imageName, String destinationImageName):
	◦	Purpose: Generates a histogram for the specified image and saves it under a given destination name.
	◦	Parameters:
	▪	imageName: The name of the source image for histogram generation.
	▪	destinationImageName: The name to store the generated histogram image.
	◦	Throws:
	▪	NoSuchElementException if the specified image is not found.
    calculateHistogramData(RGB[][] pixelArray):
	◦	Purpose: Computes histogram data for an RGB pixel array by calculating frequency counts for each color channel.
	◦	Parameters:
	▪	pixelArray: A 2D array of RGB objects representing the image.
	◦	Returns:
	▪	A Map<String, int[]> containing histogram data for red, green, and blue channels.

Usage:
	•	Serves as the graphical layer for processing and visualizing image-related data.
	•	Supports integration with the model and controller to generate histograms and provide analytical insights into image data.





2. ImageView (Class)

Purpose:Implements the ImageViewInterface and extends JPanel to provide graphical functionalities such as generating and displaying histograms, drawing grids, and managing image visualizations.

Key Features:
	Histogram Generation and Display:
	◦	Creates histograms for specified images, visualizing pixel intensity distributions for red, green, and blue channels.
	◦	Displays histograms using a scalable, customizable graphical representation.
	Grid Overlay:
	◦	Draws a light gray grid over the visualization panel to enhance readability and organization.
	Graphical Rendering:
	◦	Uses Graphics and Graphics2D to render histograms and grids dynamically.
	Data Integration:
	◦	Retrieves image data from the controller and calculates histogram data from pixel arrays.
	◦	Saves generated histogram images back into the system for further use.

Key Methods:
	generateAndStoreHistogram(String imageName, String destinationImageName):
	◦	Purpose: Generates a histogram for a specified image and stores it under a given destination name in the controller.
	◦	Throws:
	▪	NoSuchElementException if the source image is not found.
	calculateHistogramData(RGB[][] pixelArray):
	◦	Purpose: Calculates frequency counts for red, green, and blue channels from an RGB pixel array.
	◦	Returns:
	▪	A Map<String, int[]> containing histogram data for Red, Green, and Blue channels.
	paintComponent(Graphics g):
	◦	Purpose: Overrides JPanel's painting method to draw grid lines and histograms on the component.
	Private Helper Methods:
	◦	drawGrid(Graphics g): Draws a grid overlay on the panel.
	◦	drawChannelHistogram(Graphics g, String channel, Color color): Draws the histogram for a specific color channel.
	◦	convertToRGBArray(BufferedImage image): Converts a BufferedImage into a 2D array of RGB objects.

Usage:
	•	Serves as the visual layer of the system, responsible for rendering histograms and grids dynamically.
	•	Integrates with the controller for seamless data retrieval and storage, enabling visual feedback and analysis for image transformations.





3. ImageViewerWithController (Class)

Purpose:A comprehensive GUI application built using JFrame, allowing users to perform image manipulation and visualization operations interactively. It integrates with the ImageController to execute image commands and provides an interface for operations like loading, saving, transforming, and visualizing histograms.

Key Features:
	Interactive GUI:
	◦	Supports loading images of formats like .jpg, .jpeg, .png, and .ppm.
	◦	Dynamically generates buttons for available operations (e.g., flipping, sepia tone, compression).
	Comprehensive Operations:
	◦	Executes various image manipulations via commands passed to the controller.
	◦	Includes operations such as flipping, sepia tone, blur, sharpen, grayscale transformation, RGB splitting, and compression.
	Histogram Visualization:
	◦	Displays histograms for loaded or manipulated images.
	◦	Allows channel-specific visualization (red, green, and blue).
	File Handling:
	◦	Integrates a file chooser for loading and saving images.
	◦	Provides flexible options for saving in multiple formats.
	Dynamic Input:
	◦	Prompts users for inputs (e.g., split percentage, brightness levels, compression percentage) using dialogs.

Key Methods:
	Loading and Saving:
	◦	loadImage(File file): Loads an image, updates the view, and generates histogram data.
	◦	saveImage(): Prompts the user to save the currently displayed image in a chosen format.
	Manipulations:
	◦	applyOperation(String operation): Executes basic operations like flipping or RGB splitting.
	◦	applySplitOperation(String operation): Handles split-based operations (e.g., sepia tone, blur) with configurable split percentages.
	Histogram Management:
	◦	showHistogram(String imageName): Generates and displays histograms for the current or manipulated image.
	Dynamic UI Updates:
	◦	addButton(JPanel panel, String text, String command): Dynamically adds operation buttons to the UI.
	◦	updateImageView(String imageName): Refreshes the image display after manipulations.
	Utility Methods:
	◦	convertToBufferedImage(RGB[][] pixelArray): Converts an RGB array to a BufferedImage for display.
	◦	getInputValue(String message, int defaultValue, int minValue, int maxValue): Prompts the user for numeric input with validation.
	Action Handling:
	◦	Implements ActionListener to handle button clicks and command execution.

Usage:
	•	End-User Application:
	◦	Allows users to perform image processing tasks interactively through a user-friendly GUI.
	◦	Suitable for tasks like visual analysis (via histograms), image transformations, and saving results.





________________________________________________________________________________________________________________________________________________________________________________________________________________________________

HOW TO RUN YOUR SCRIPT ON OUR PROGRAM:
	Step 1: Locate the class name "Main" in the source folder
	Step 2: Run the main class.
	Step 3: Enter your commands one after the other adhering to the instructions on your console or enter 'run' followed by a file path to run your src file.
	Step 4: Refer to the UseMe file for the list of commands and their respective correct formats.
________________________________________________________________________________________________________________________________________________________________________________________________________________________________

IMAGE CITATIONS:
	earth.jpg :https://images.app.goo.gl/JnDyDFKMURhbk4d37
	balloon.png :https://www.pngwing.com/en/free-png-byvsx/download
NOTE: ALL PPM FILES ARE CREATED BY US.
________________________________________________________________________________________________________________________________________________________________________________________________________________________________





