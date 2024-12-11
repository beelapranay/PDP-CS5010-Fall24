# Advanced Image Processing Application

## Overview

This project implements an advanced image processing application based on the
Model-View-Controller (MVC) architecture. It supports a wide range of image operations, including
blurring, sharpening, flipping, color transformations, histogram generation, compression, color
correction, levels adjustment, and more. The application can run in interactive mode, script-based
mode, and via a GUI interface for enhanced user interaction.

## Features

- **Load and Save Images**: Supports multiple image formats including PPM, PNG, and JPEG.
- **Basic Image Operations**:
    - **Blur**: Apply a Gaussian blur to images.
    - **Sharpen**: Enhance edges and details.
    - **Brighten/Darken**: Adjust image brightness.
    - **Flip**: Horizontally or vertically flip images.
    - **Color Transformations**: Apply sepia tone or extract color components.
- **Advanced Image Operations**:
    - **Compress**: Reduce image size using wavelet-based compression.
    - **Histogram Generation**: Create histogram images representing color distributions.
    - **Color Correction**: Adjust colors to improve visual quality.
    - **Levels Adjustment**: Modify black, mid, and white points to enhance contrast.
    - **Split View**: Apply operations to a portion of the image for comparison.
    - **Downscale**: Reduce image resolution while maintaining aspect ratio.
    - **Masking Operations**: Apply selected image operations to specific parts of an image using a
      black-and-white mask. The syntax for applying operations with a mask is:
      `operation source-image-name mask-image-name dest-image-name`. Operations are applied only to
      areas of the image corresponding to black pixels in the mask.
    - **Improved Error Handling**: Provides detailed and user-friendly error messages for invalid commands or parameters, enhancing the overall user experience.
- **Interactive, Script, and GUI Modes**: Execute commands interactively, via scripts, or through a
  user-friendly GUI interface.
- **Extensible Design**: Easily add new image operations without significant changes to existing
  code.

## Architecture

The application follows the MVC architecture, ensuring a clear separation of concerns:

- **Model**: Manages the image data and operations on images.
- **View**: Handles user interaction and displays messages (text-based and GUI).
- **Controller**: Coordinates between the Model and View, processes user commands.

### 1. Model

- **`ImageModelInterface`**: Defines methods to manage images in memory.
- **`ImageModelImpl`**: Implements the `ImageModelInterface` using a `HashMap` to store images.
- **`ImageInterface`**: Represents an image, providing methods to access pixel data and dimensions.
- **`Image`**: Concrete implementation of `ImageInterface`.
- **`ImageProcessor`**: Interface defining basic image processing operations.
- **`AdvancedImageProcessor`**: Extends `ImageProcessor` with advanced operations.
- **`ImageProcessorImpl`**: Implements basic image processing operations.
- **`AdvancedImageProcessorImpl`**: Implements advanced image processing operations.
- **Operation Classes**: Each image operation (e.g., `BlurOperation`, `CompressOperation`) is
  implemented in its own class.

### 2. View

- **`View`**: Interface for displaying messages to the user.
- **`TextBasedView`**: Implements the `View` interface, providing a console-based UI.
- **`ImageProcessingGUIView`**: Implements a GUI for user-friendly interaction.

### 3. Controller

- **`ControllerInterface`**: Defines methods for processing user commands.
- **`GUIController`**: Implements `ControllerInterface` for GUI-based interaction.
- **`Controller`**: Implements `ControllerInterface`, parsing commands, and coordinating between the Model and View for text-based or script-based interaction.
- **`CommandExecutor`**: Centralizes the execution of commands. It maintains a registry of known commands, parses user inputs, and executes corresponding command classes. It also handles script-based execution, ensuring robust and flexible command processing.
- **`ImageIOUtil`**: A utility class responsible for reading and writing images in different formats (e.g., PPM, JPEG, PNG). This class facilitates interaction with the file system and helps load images into memory or save processed images back to the disk.
- **`Command`**: Interface for executing operations.
- **Command Classes**: Each command (e.g., `BlurCommand`, `BrightenCommand`) implements the `Command` interface and encapsulates the logic for parsing a specific command.

## GUI Key Features

The GUI mode provides an intuitive interface for image processing operations. Here are the key features of the GUI:

1. **Loading an Image**:

    - **How to Load**: Click on the Load Image button. Browse your file system to select an image.

    - Supported formats include PPM, PNG, and JPEG.

    - The image will be displayed in the preview area.

2. **Performing Operations**:

    - Choose an operation from the toolbar. Supported operations include:

        - Blur

        - Sharpen

        - Brighten/Darken

        - Flip (Horizontal/Vertical)

        - Color Transformations (Sepia, Red/Green/Blue Component, etc.)

        - Downscale

        - Histogram Generation

        - Levels Adjustment

        - **Compress**

        - **Color Correction**

    - Input required parameters (e.g., brightness value, split percentage).

    - Click Apply Operation to process the image.

3. **Viewing Split View Results**:

    - If an operation supports split view, you can preview the effect on a portion of the image.

    - Use the slider or enter a percentage to define the split area.

    - The GUI provides live previews of the split view, allowing you to adjust the split percentage dynamically.

    - **Operations supporting split view in the GUI now include Blur, Sharpen, Sepia, Brighten/Darken, Levels Adjustment, Color Correction, and more.**

4. **Interactive Parameter Inputs**:

    - The GUI prompts you for necessary parameters using intuitive input dialogs.

    - For example:

        - **Brightness Adjustment**: Specify the increment or decrement value.

        - **Compression**: Input the desired compression percentage.

        - **Levels Adjustment**: Provide black, mid, and white points via a form.

    - **Input validation ensures that invalid entries are caught and appropriate error messages are displayed.**

5. **Saving Images**:

    - After processing, click the Save Image button to save the output.

    - Select a destination folder and filename for the processed image.

6. **Viewing Image Details**:

    - Click the Image Details button to view metadata such as:

        - Image name

        - Dimensions

7. **Operation History**:

    - The Operation History tab allows you to view a list of previously applied operations.

    - You can click on a history item to reapply or review the corresponding processed image.

### Navigation Tips:

- **Menu Bar**: Access frequently used commands, such as Load, Save, and Exit.

- **Toolbar**: Quickly apply operations using intuitive icons.

- **Preview Area**: Displays the original image, processed image, or split view.

- **Operation History tab**: Shows previous operations performed and allows you to access those images just by clicking on the history item.


## Design Changes and Justifications

### 1. **Introduction of the Command Design Pattern**

**Change**: Adopted the Command design pattern to handle different operations as individual command
classes (e.g., `BlurCommand`, `BrightenCommand`). Each command class encapsulates the specific
logic, parameters, and delegates execution.

**Justification**:

- **Flexibility**: Allows for easy addition of new commands without modifying existing code in the
  `Controller` or `GUIController` class.
- **Reusability**: Commands are self-contained, making them easier to test and reuse.
- **Separation of Concerns**: Each command class is responsible for a specific operation, following
  the Single Responsibility Principle.

### 2. **Introduction of GUI**

**Change**: Developed a `GUIController` and `ImageProcessingGUIView` to support graphical user
interaction.

**Justification**:

- **User Experience**: Provides a user-friendly interface, making the application accessible to
  non-technical users.
- **Integration**: Built to seamlessly interact with the existing model and controller.

### 3. **Downscale Feature**

**Change**: Implemented a `Downscale` operation that reduces the resolution of an image to
user-specified dimensions while maintaining the aspect ratio.

**Detailed Changes**:

- Added a new `DownscaleOperation` class to handle the logic of reducing an image's resolution by
  calculating the new pixel values through weighted averaging.
- Updated the `AdvancedImageProcessor` interface and its implementation (
  `AdvancedImageProcessorImpl`) to include the `downscale` method.
- Created a dedicated `DownscaleCommand` to process user inputs and invoke the `downscale` operation
  within the `Controller`.
- Extended GUI functionality to support downscaling with live split view previews, allowing users to
  visualize changes before applying them.
- Added unit tests (`DownscaleOperationTest`) to thoroughly test various scenarios, including:
    - Downscaling square and non-square images.
    - Validating aspect ratio preservation.
    - Ensuring correct error handling for invalid dimensions.

**Justification**:

- **Enhanced Functionality**: Provides users the ability to optimize images for specific resolution
  constraints, which is critical for web usage or thumbnail creation.
- **Extensibility**: The modular design enables easy integration of similar resolution-modifying
  operations in the future.
- **User Experience**: The live split view in the GUI allows users to preview the effects of
  downscaling before finalizing changes.

### 4. **Masking Feature**

**Change**: Introduced masking functionality to enable selective application of image operations.
This feature leverages a black-and-white mask (with same dimensions) to determine where the
operation should be applied. While most of the logic is encapsulated in the newly created
ApplyMaskOperation class, minor updates were made to existing operations (Blur, Sharpen, Sepia, and
Component Operations) to ensure compatibility with masking.

**Justification**:

- **Compatibility**: The changes ensure that all relevant operations (e.g., Blur, Sharpen, Sepia)
  can leverage the masking feature without altering their core logic or behavior for non-masked
  cases.

- **Minimal Disruption**: Existing functionality was extended to support masking while preserving
  its behavior for non-masked scenarios, ensuring full backward compatibility with previous scripts.
- **Extensibility**: Masking integrates seamlessly into the overall architecture, enabling future
  operations to support this feature with minimal effort.

### 5. **Improved Testing**

**Change**: Created `MockModel` and `MockGUIView` for unit testing.

**Justification**:

- **Error Prevention**: Ensures robust application behavior under various conditions.
- **Flexibility**: Allows thorough testing of individual components.

### 6. **Improved Command Parsing Logic**

**Change**: Refactored the parsing logic in command classes (`BlurCommand`, `SharpenCommand`, `SepiaCommand`, `ComponentCommand`, etc.) to handle different command syntaxes more robustly, including proper support for split view and masking operations.

**Justification**:

- **Robustness**: Ensures that all valid command formats are correctly parsed and executed.

- **Error Handling**: Provides clear and specific error messages for invalid command inputs or syntaxes.

- **Maintainability**: Simplifies the addition of new commands and reduces the likelihood of parsing errors.

### 7. **Enhanced Error Handling Across the Application**

**Change**: Improved error handling mechanisms in both the GUI and text-based modes to provide detailed and user-friendly error messages.

**Justification**:

- **User Experience**: Helps users understand what went wrong and how to correct it.

- **Debugging**: Makes it easier to identify and fix issues during development and testing.

### 8. **Introduction of CommandExecutor**

**Change**: Introduced the `CommandExecutor` class to centralize the logic for executing commands. It maintains a registry of known commands and their corresponding functions, simplifying the parsing and execution of user inputs.

**Justification**:

- **Modularity**: Separates command parsing and execution from the Controller, adhering to the Single Responsibility Principle.
- **Flexibility**: Simplifies adding new commands by encapsulating command registration and execution in a single class.
- **Robustness**: Handles errors and exceptions gracefully, ensuring better user feedback and system stability during command execution.
- **Extensibility**: Makes the architecture more adaptable to future enhancements, such as dynamic command registration or plugin-based command extensions.

## Completion Status

- **Completed**:
    - Implementation of all basic and advanced image operations.
    - Interactive, script-based, and GUI-based command processing.
    - Comprehensive error handling and input validation.
    - Unit tests covering various scenarios and commands.
- **Incomplete**:
    - Additional image formats support beyond PPM, PNG, and JPEG.

## Usage Instructions

### Prerequisites

- Java Development Kit (JDK) 11 or higher installed.
- Command-line interface or terminal access.

### Running the Application

1. **Compile the Project**:

   Navigate to the root directory containing the `src` folder and run:

   ```bash
   javac main/ImageProcessingApplication.java
    ```

2. **Run in Interactive Text Mode:**

   Execute the following command:

   ```bash
    java -jar assignment6.jar -text
   ```

3. **Run with GUI**:

   ```bash
     java -jar assignment6.jar
   ```

4. **Run with a Script**:

   ```bash
    cd resources
    java -jar assignment6.jar -file resources/scripts/AdvancedOperationTestScript.txt
   ```

### Command Syntax

- `load [filepath] [image-name]`: Load an image from a file.
- `save [filepath] [image-name]`: Save an image to a file.

#### **Operations Syntax**
- **Blur Operation**:
  - `blur [image-name] [dest-image-name] [split p]`: Blur an image. Optionally apply as a split view
    with percentage `p`.
  - `blur [image-name] [mask-image-name] [dest-image-name]`: Apply a blur operation selectively using
    the mask image.


- **Sharpen Operation**:
  - `sharpen [image-name] [dest-image-name] [split p]`: Sharpen an image.
  - `sharpen [image-name] [mask-image-name] [dest-image-name]`: Apply a sharpen operation selectively
    using the mask image.


- **Brighten/Darken Operation**:
  - `brighten [increment] [image-name] [dest-image-name]`: Brighten or darken an image.


- **Sepia Operation**:
  - `sepia [image-name] [dest-image-name] [split p]`: Apply sepia tone.
  - `sepia [image-name] [mask-image-name] [dest-image-name]`: Apply sepia tone selectively using the
    mask image.


- **Flip Operations**:
  - `horizontal-flip [image-name] [dest-image-name]`: Flip an image horizontally.
  - `vertical-flip [image-name] [dest-image-name]`: Flip an image vertically.


- **Component Extraction**:
  - `red-component [image-name] [dest-image-name] [split p]`: Extract the red component.
  - `red-component [image-name] [mask-image-name] [dest-image-name]`: Extract the red component
    selectively using the mask image.
  - `green-component [image-name] [dest-image-name] [split p]`: Extract the green component.
  - `green-component [image-name] [mask-image-name] [dest-image-name]`: Extract the green component
    selectively using the mask image.
  - `blue-component [image-name] [dest-image-name] [split p]`: Extract the blue component.
  - `blue-component [image-name] [mask-image-name] [dest-image-name]`: Extract the blue component
    selectively using the mask image.
  - `luma-component [image-name] [dest-image-name] [split p]`: Extract the luma component.
  - `luma-component [image-name] [mask-image-name] [dest-image-name]`: Extract the luma component
    selectively using the mask image.
  - `intensity-component [image-name] [dest-image-name] [split p]`: Extract the intensity component.
  - `intensity-component [image-name] [mask-image-name] [dest-image-name]`: Extract the intensity
    component selectively using the mask image.
  - `value-component [image-name] [dest-image-name] [split p]`: Extract the value component.
  - `value-component [image-name] [mask-image-name] [dest-image-name]`: Extract the value component
    selectively using the mask image.


- **RGB Operations**:
  - `rgb-combine [dest-image-name] [red-image] [green-image] [blue-image]`: Combine RGB components.
  - `rgb-split [image-name] [red-image] [green-image] [blue-image]`: Split an image into RGB
    components.


- **Compress Operation**:
  - `compress [percentage] [image-name] [dest-image-name]`: Compress an image.


- **Histogram Generation**:
  - `histogram [image-name] [dest-image-name]`: Generate a histogram image.


- **Color Correction**:
  - `color-correct [image-name] [dest-image-name] [split p]`: Apply color correction.


- **Levels Adjustment**:
  - `levels-adjust [b] [m] [w] [image-name] [dest-image-name] [split p]`: Adjust levels.


- **Downscale Operation**:
  - `downscale [width] [height] [image-name] [dest-image-name]`:
    Downscale an image to a specific resolution.


- **Masking Operation**:
  - `operation [image-name] [mask-image-name] [dest-image-name]`: Apply the specified operation selectively using the mask image. Only areas corresponding to black pixels in the mask will be affected.


- **Other Commands**:
  - `details [image-name]`: Display image details.
  - `run [script-file]`: Execute commands from a script file.
  - `exit`: Exit the application.

### Example Script

```plaintext
load resources/sampleImages/statue-of-unity.jpg sample-image
load resources/sampleImages/mask1.jpg mask1
compress 90 sample-image compressed-image
save resources/compressed-image.png compressed-image

# Apply downscale operation
downscale 640 427 mask1 mask-sample

# Apply blur with a mask
blur sample-image mask-sample blurred-sample
save resources/blurred-sample.jpg blurred-sample

# Apply sepia with a mask
sepia sample-image mask-sample sepia-sample
save resources/sepia-sample.jpg sepia-sample
exit
```

### Example Script for all Masking Operations Supported
PLEASE UNZIP THE operatedImages.zip file to see the output images, mask images and for this script to run.
```plaintext
# Loading both sample image and mask
load resources/sampleImages/statue-of-unity.jpg sample-image
load resources/operatedImages/mask.png mask

# Applying the mask to the image for all supported operations

blur sample-image blur-image
blur sample-image mask masked-blur-image

sharpen sample-image sharpen-image
sharpen sample-image mask masked-sharpen-image

sepia sample-image sepia-image
sepia sample-image mask masked-sepia-image

red-component sample-image red-component-image
red-component sample-image mask masked-red-component-image

value-component sample-image value-component-image
value-component sample-image mask masked-value-component-image


# Saving both the operated images along with their masked images
save resources/operatedImages/blur-image.jpg blur-image
save resources/operatedImages/masked-blur-image.jpg masked-blur-image

save resources/operatedImages/sharpen-image.jpg sharpen-image
save resources/operatedImages/masked-sharpen-image.jpg masked-sharpen-image

save resources/operatedImages/sepia-image.jpg sepia-image
save resources/operatedImages/masked-sepia-image.jpg masked-sepia-image

save resources/operatedImages/red-component-image.jpg red-component-image
save resources/operatedImages/masked-red-component-image.jpg masked-red-component-image

save resources/operatedImages/green-component-image.jpg green-component-image
save resources/operatedImages/masked-green-component-image.jpg masked-green-component-image

save resources/operatedImages/value-component-image.jpg value-component-image
save resources/operatedImages/masked-value-component-image.jpg masked-value-component-image

# Exit application
exit
```

## Notes

- **Split View**:

    - For operations supporting split view, append `split [percentage]` after the destination image name.

    - The `percentage` should be an integer between 0 and 100, representing the portion of the image to which the operation is applied.

- **Masking Operations**:

    - To apply an operation selectively using a mask, include the mask image name after the source image name.

    - The syntax is: `operation [source-image-name] [mask-image-name] [dest-image-name]`.

    - Operations are applied only to areas of the image corresponding to black pixels in the mask.

- **Order of Commands**: Load an image before applying any operations.

- **Error Handling**: The application provides meaningful error messages for invalid commands or parameters. Ensure that all parameters are valid and images are properly loaded before executing commands.

---

## Image Source Citation

The images used in this project are sourced as follows:

Using PNG image from external source:

- **Image Source**: [Pixels](https://www.pexels.com/photo/bird-s-eye-view-of-city-2246476/)
- **Author**: Maxime Francis
- **License**: Free to use under the Pexels License.

Using PNG image from external source for masking:

- **Image Source
  **: [Datacarpentry](https://datacarpentry.github.io/image-processing/07-thresholding.html)
- **Author**: Data Carpentry
- **License**: Free to use under The Carpentries License.

Using own images for this project:

- **Image Source (JPG one)**: Personal photograph owned by Darshil Vipul Shah.
- **Usage**: Authorized for use in this project.

---

The class diagram is in the resources folder.

## Conclusion

This advanced image processing application offers a robust set of features suitable for both basic
and advanced users. The design changes implemented enhance flexibility, extensibility, and user
experience. The application adheres to software engineering best practices, ensuring maintainability
and scalability.

---- By Darshil Vipul Shah & Mann Arvindbhai Savani



