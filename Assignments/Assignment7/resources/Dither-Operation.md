**Dither implementation: Y**\
**Script command for dither: Y (dither image-name dest-image-name [split p])**\
**Dither from GUI: Y**

## Controller
The `DitheringCommand` class was added by following the existing design pattern of command classes. 
It integrates with the `AdvancedImageProcessor` interface to apply the dithering operation and fits 
seamlessly into the command structure. The class validates input, supports split preview, and 
invokes the appropriate processor method (applyDithering). It also ensures the result is stored back
into the ImageModelInterface for consistency. This approach adheres to the modular and reusable 
design of the existing framework.

### CommandExecutor Class
The line 
`knownCommands.put("dither", tokens -> new DitheringCommand(tokens, imageProcessor, model))` maps 
the `dither` command to its corresponding `DitheringCommand`.

### GUIController Class
The method `applyDithering(String imageName, String destImageName)` triggers the `dither` command 
using the `CommandExecutor`. It ensures seamless execution by validating inputs, handling errors, 
and providing user feedback via the view, adhering to the existing command-driven architecture.

## Model
### DitheringOperation Class
The `DitheringOperation` class implements the `ImageOperation` interface to apply `Floyd-Steinberg` 
dithering to an image. It converts the image to grayscale, applies error diffusion to create a 
black-and-white dithered effect, and reconstructs the image in binary form. This class fits into the 
model's design, ensuring modularity and compatibility with the existing operation framework.

### AdvancedImageProcessorImpl Class (Implements the AdvancedImageProcessor Interface)
The `applyDithering` method was added to the `AdvancedImageProcessorImpl` class to integrate the 
dithering functionality into the processor. It validates the input image and delegates the dithering 
operation to the DitheringOperation class, maintaining consistency with the existing design for 
modularity and reusability.

### SplitViewOperation Class
The new dither case allows applying dithering in the split view by calling `applyDithering` 
from `AdvancedImageProcessor`.

## View
### ImageProcessingGUIView Class
The `Dither` button was added to the UI as part of the operations panel in the 
`ImageProcessingGUIView` class. It was created using the `createOperationButton` method, ensuring 
consistent styling with other buttons in the UI.

An action listener was set for the button using the `performOperationWithSplitView` method. This 
enables the dithering operation to be applied with an optional split preview feature. When clicked, 
the action listener either allows users to directly apply dithering to the entire image or preview 
the split view, depending on the user's choice.