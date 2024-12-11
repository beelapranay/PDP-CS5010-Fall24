# USEME.md - Image Processing Application

## Overview

This document provides instructions for using the Image Processing Application. The application
supports various image processing commands in both interactive and script-based modes. Each command
is documented here with examples and any conditions or dependencies that may apply.

---

## Usage

### Running the Application

The application can be run in **interactive mode** (commands entered one by one) or script mode
(batch processing via a command file). Use the following commands to start:

1. **Compile the project** (from the root directory):
   ```bash
    javac main/ImageProcessingApplication.java
    ```
2. **Run the application:**:

- Text Interactive Mode: Run the application without arguments.
  ```bash
    java -jar assignment6.jar -text
  ```
- Script Mode: Run the application with a script file.
  ```bash
    cd resources
    java -jar assignment6.jar -file resources/scripts/AdvancedOperationTestScript.txt
  ```

3. **GUI Mode:**
    ```bash
      java -jar assignment6.jar
    ```

---

### GUI Mode Usage

### Overview of GUI Mode

- This version introduces a **Graphical User Interface (GUI)** for the application, providing an intuitive and interactive way to perform image processing tasks.
- Key features of the GUI include:
  - **User-Friendly Interface**: Perform operations with simple button clicks and input dialogs.
  - **Split View Preview**: View the effects of operations on a portion of the image before applying them fully.
  - **Dynamic Parameter Input**: Specify operation parameters like brightness values or compression percentage directly in dialogs.
  - **Real-Time Feedback**: Preview image changes instantly.

### Launching the GUI

1. **Run the following command to launch the application in GUI mode:**

  ```bash
      java -jar assignment6.jar
  ```

2. The GUI window will open, displaying options for:

- Loading an image
- Selecting an operation
- Configuring operation parameters
- Either directly apply the operation or preview it in split mode for a certain percentage of the
  image.
- Saving the output image

---

### Detailed GUI Operations

This section provides step-by-step instructions for each image processing operation in GUI mode. Use the buttons and dialog boxes available in the GUI for performing operations as described below.

---

#### 1. **Load Image**
- **Action**: Click the **Load Image** button in the left panel.
- **Steps**:
  1. A file browser dialog will open.
  2. Select the desired image file (supported formats: PPM, PNG, JPEG).
  3. Click **Open**, and the selected image will be displayed in the main preview area.

---

#### 2. **Save Image**
- **Action**: Click the **Save Image** button in the left panel.
- **Steps**:
  1. A save dialog will open.
  2. Choose the destination folder and filename.
  3. Click **Save**, and the image will be saved in the selected location.

---

#### 3. **Blur**
- **Action**: Click the **Blur** button in the left panel.
- **Steps**:
  1. A dialog will open with options to:
    - Directly apply the blur operation.
    - **Use Split View**: This allows you to preview the effect on part of the image.
  2. For Split View:
    - Use the **text box** to enter a precise percentage value (e.g., 50%).
    - The preview will dynamically update based on the slider or percentage input.
  3. Once satisfied:
    - Click **Apply** to finalize the effect on the full image.
    - Save the result using the **Save Image** button.

---

#### 4. **Sharpen**
- **Action**: Click the **Sharpen** button in the left panel.
- **Steps**:
  1. A dialog will open with options to:
    - Directly apply the sharpen operation.
    - **Use Split View**: This allows you to preview the effect on part of the image.
  2. For Split View:
    - Use the **text box** to enter a precise percentage value (e.g., 50%).
    - The preview will dynamically update based on the slider or percentage input.
  3. Once satisfied:
    - Click **Apply** to finalize the effect on the full image.
    - Save the result using the **Save Image** button.

---

#### 5. **Brighten/Darken**
- **Action**: Click the **Brighten** button in the left panel.
- **Steps**:
  1. A dialog will appear asking for the brightness value.
  2. Enter a positive value to brighten or a negative value to darken (range: -255 to 255).
  3. For Split View:
    - Use the **text box** to enter a precise percentage value (e.g., 50%).
    - The preview will dynamically update based on the slider or percentage input.
  4. Once satisfied:
    - Click **Apply** to finalize the effect on the full image.
    - Save the result using the **Save Image** button.

---

#### 6. **Flip (Horizontal/Vertical)**
- **Action**: Click **Flip Horizontal** or **Flip Vertical** in the left panel.
- **Steps**:
  1. The selected flip operation will be applied immediately to the entire image.
  2. Save the result using the **Save Image** button.

---

#### 7. **Sepia Tone**
- **Action**: Click the **Sepia** button in the left panel.
- **Steps**:
  1. A dialog will open with options to:
    - Directly apply the sepia operation.
    - **Use Split View**: This allows you to preview the effect on part of the image.
  2. For Split View:
    - Use the **text box** to enter a precise percentage value (e.g., 50%).
    - The preview will dynamically update based on the slider or percentage input.
  3. Once satisfied:
    - Click **Apply** to finalize the effect on the full image.
    - Save the result using the **Save Image** button.

---

#### 8. **Adjust Levels**
- **Action**: Click the **Adjust Levels** button in the left panel.
- **Steps**:
  1. A dialog will appear asking for:
    - Black point value (0-255).
    - Midpoint value (0-255).
    - White point value (0-255).
  2. Ensure `black < mid < white`.
  3. For Split View:
    - Use the **text box** to enter a precise percentage value (e.g., 50%).
    - The preview will dynamically update based on the slider or percentage input.
  4. Once satisfied:
    - Click **Apply** to finalize the effect on the full image.
    - Save the result using the **Save Image** button.

---

#### 9. **Compress**
- **Action**: Click the **Compress** button in the left panel.
- **Steps**:
  1. A dialog will ask for the compression percentage (0-100).
  2. Enter the desired value and click **Apply** to process.
  3. Note: **Split View** is not available for this operation.
  4. Save the result using the **Save Image** button.

---

#### 10. **Downscale**
- **Action**: Click the **Downscale** button in the left panel.
- **Steps**:
  1. A dialog will ask for the target width and height.
  2. Enter the desired dimensions.
  3. Note: **Split View** is not available for this operation.
  4. Click **Apply** to process the image.
  5. Save the result using the **Save Image** button.

---

#### 11. **Visualize Color Components**
- **Action**: Click any of the following buttons: **Red Component**, **Green Component**, **Blue Component**, **Luma Component**, **Intensity Component**, or **Value Component**.
- **Steps**:
  1. The selected component will be visualized and displayed.
  2. Save the result if needed.
  3. Note: **Split View** is not applicable for these operations.

---

#### 12. **Color Correction**
- **Action**: Click the **Color Correct** button in the left panel.
- **Steps**:
  1. A dialog will open with options to:
    - Directly apply the color correction operation.
    - **Use Split View**: This allows you to preview the effect on part of the image.
  2. For Split View:
    - Use the **text box** to enter a precise percentage value (e.g., 50%).
    - The preview will dynamically update based on the slider or percentage input.
  3. Once satisfied:
    - Click **Apply** to finalize the effect on the full image.
    - Save the result using the **Save Image** button.

---

#### 13. **Histogram Generation**
- **Action**: The histogram is automatically generated for any loaded image.
- **Steps**:
  1. View the histogram in the right panel.
  2. Save the histogram using the **Save Image** button.
  3. Note: **Split View** is not applicable for this operation.

---

#### 14. **Show Image Details**
- **Action**: Click the **Show Details** button in the left panel.
- **Steps**:
  1. A dialog will display the metadata of the current image (e.g., dimensions, file type).
  2. Use this information for reference or further operations.

---

### Split View Overview:

1. **Supported Operations**: Blur, Brighten, Sepia, Adjust Levels, Sharpen, and Color Correction.
2. **How It Works**:
  - Input a specific percentage value for finer control.
  - Preview updates dynamically in real-time.
3. **Steps**:
  - Make adjustments in the preview dialog.
  - Click **Apply** to finalize the changes for the entire image.
4. **Note**: Split View is not available for operations like Compress or Downscale.

---

### Notes:
- Operations like **Blur**, **Sharpen**, **Brighten**, etc., support the **Split View** feature for enhanced interactivity.
- Use the **Operation History** tab to view past operations and revisit previous versions.

---

## Supported Commands (for Text and Script Modes)

The following commands are available in the application. Each command has specific requirements for
usage.

### 1. Load an Image

- **Command**: `load [filepath] [image-name]`
- **Description**:  Loads an image from the specified filepath and assigns it an identifier
  `<image-name>`.
- **Example**:
  ```bash
    load resources/sampleImages/statue-of-unity.jpg statue
   ```
  ### 2. Save an Image
- **Command**: `save <filepath> <image-name>`
- **Description**:  Saves the specified image to the given filepath.
- **Example**:
  ```bash
    save resources/outputs/statue-edited.jpg statue
   ```
  ### 3. Blur an Image
- **Command**: `blur <image-name> <dest-image-name> [split <percentage>]`
- **Description**:  Blurs the specified image. Optionally, a split percentage can be specified to
  blur only part of the image.
- **Example**:
  ```bash
    blur statue blurred_statue
    blur statue partially_blurred_statue split 50
   ```
  ### 4. Brighten an Image
- **Command**: `brighten <value> <image-name> <dest-image-name>`
- **Description**: Adjusts the brightness of an image by the given value. Positive values brighten,
  negative values darken.
- **Example**:
  ```bash
    brighten 30 statue brightened_statue
   ```
  ### 5. Sharpen an Image
- **Command**: `sharpen <image-name> <dest-image-name> [split <percentage>]`
- **Description**:  Sharpens the specified image. Optionally, a split percentage can be specified to
  sharpen only part of the image.
- **Example**:
  ```bash
    sharpen statue-brightened statue
    sharpen statue-brightened statue split 50
   ```
  ### 6. Sepia Tone
- **Command**: `sepia <image-name> <dest-image-name> [split <percentage>]`
- **Description**:  Applies a sepia tone to the specified image. Optionally, a split percentage can
  be used to apply sepia to part of the image.
- **Example**:
  ```bash
    sepia statue sepia_statue
    sepia statue partial_sepia_statue split 50
   ```
  ### 7. Flip an Image
- **Command**: `horizontal-flip <image-name> <dest-image-name>` or
  `vertical-flip <image-name> <dest-image-name>`
- **Description**:  Flips an image horizontally or vertically.
- **Example**:
  ```bash
    horizontal-flip statue flipped_statue_horizontal
    vertical-flip statue flipped_statue_vertical
   ```
  ### 8. Extract Image Component
- **Command**: `<component>-component <image-name> <dest-image-name> [split percentage]`
- **Description**:  Extracts the specified component from the image, where `<component>` can be
  `red`, `green`, `blue`, `luma`, `intensity`, or `value`.
- **Example**:
  ```bash
    red-component statue red_statue
    red-component statue red_statue split [percentage]
    green-component statue green_statue
    green-component statue green_statue split [percentage]
    blue-component statue blue_statue
    blue-component statue blue_statue split [percentage]
    luma-component statue luma_statue
    luma-component statue luma_statue split [percentage]
    intensity-component statue intensity_statue
    intensity-component statue intensity_statue split [percentage]
    value-component statue value_statue
    value-component statue value_statue split [percentage]
   ```
  ### 9. RGB Combine
- **Command**: `rgb-combine <dest-image-name> <red-image-name> <green-image-name> <blue-image-name>`
- **Description**:  Combines three grayscale images (red, green, blue channels) into one RGB image.
- **Example**:
  ```bash
    rgb-combine combined_statue red_statue green_statue blue_statue
   ```
  ### 10. RGB Split
- **Command**:
  `rgb-split <image-name> <red-dest-image-name> <green-dest-image-name> <blue-dest-image-name>`
- **Description**:  Splits an RGB image into separate red, green, and blue grayscale images.
- **Example**:
  ```bash
    rgb-split statue red_statue green_statue blue_statue
   ```
  ### 11. Color Correction
- **Command**: `color-correct <image-name> <dest-image-name> [split <percentage>]`
- **Description**:  Adjusts colors for visual balance. An optional split percentage can apply the
  effect to part of the image.
- **Example**:
  ```bash
    color-correct statue corrected_statue
    color-correct statue partially_corrected_statue split 60
   ```
  ### 12. Levels Adjustment
- **Command**:
  `levels-adjust <black> <mid> <white> <image-name> <dest-image-name> [split <percentage>]`
- **Description**:  Adjusts levels for black, mid-tone, and white points. Optionally, apply a split
  percentage to affect part of the image.
- **Example**:
  ```bash
    levels-adjust 10 128 245 statue adjusted_statue
    levels-adjust 20 100 220 statue partially_adjusted_statue split 50
   ```
  ### 13. Compress
- **Command**: `compress <percentage> <image-name> <dest-image-name>`
- **Description**:  Compresses an image to the specified percentage quality.
- **Example**:
  ```bash
    compress 50 statue compressed_statue
   ```
  ### 14. Histogram Generation
- **Command**: `histogram <image-name> <dest-image-name>`
- **Description**:  Generates a histogram of the image and saves it as a new image.
- **Example**:
  ```bash
    histogram statue histogram_statue
   ```

  ### 15. Downscale an Image
- **Command**: `downscale <width> <height> <image-name> <dest-image-name>`
- **Description**: Reduces the resolution of an image to the specified dimensions while maintaining
  the aspect ratio.
- **Example**:
  ```bash
    downscale 800 600 statue downscaled_statue
   ```

  ### 16. Applying Operations with Masks
- **Command**: `operation <source-image> <mask-image> <dest-image>`
- **Description**: Applies the specified operation to the source image based on the mask image
  (with same dimensions). The mask image must be a black-and-white image of the same dimensions as
  the source image. The operation is applied only to areas corresponding to black pixels in the mask
  image.
- **Example**:
  ```bash
  blur sample.jpg mask.jpg blurred-sample
  save outputs/blurred-sample.jpg blurred-sample

---

## Important Notes and Conditions

- **Command Order**: Some commands have dependencies. For instance, you must `load` an image before
  using any operations on it.
- **Supported File Formats**: The application supports images in PPM, PNG, and JPEG formats.
- **Script Comments**: Script files can include comments by starting a line with `#`.
- **Exiting**: Use the `exit` command in interactive mode to terminate the application.
- The GUI mode requires a valid Java environment with Swing support.
- Split View previews are designed to improve usability but do not affect the final image's processing speed.

---

## Example Script

Here is an example of a script file that performs a sequence of operations on an image:

```plaintext
# Load an image and name it 'sample-image'
load resources/sampleImages/statue-of-unity.jpg sample-image

# Apply 90% compression
compress 90 sample-image sample-compressed
save resources/csample-compressed.jpg sample-compressed

# Color correct the image
color-correct sample-image sample-color-corrected
save resources/csample-color-corrected.jpg sample-color-corrected

# Adjust levels of the image
levels-adjust 30 128 220 sample-image sample-levels-adjusted
save resources/csample-levels-adjusted.jpg sample-levels-adjusted

# Generate a histogram for the image
histogram sample-image sample-histogram
save resources/csample-histogram.jpg sample-histogram

# Apply a split view with blur
blur sample-image sample-blurred split 50
save resources/csample-final-split-view.jpg sample-blurred

# Load a mask image
load resources/sampleImages/mask1.jpg mask1

# Apply downscale operation
downscale 640 427 mask1 mask-sample

# Apply blur with a mask
blur sample-image mask-sample blurred-sample
save resources/blurred-sample.jpg blurred-sample

# Apply sepia with a mask
sepia sample-image mask-sample sepia-sample
save resources/sepia-sample.jpg sepia-sample

# Exit the application
exit
```

To execute this script, SAVE it as `check_script.txt` and run:

```bash
    run resources/scripts/check_script.txt
```

OR

```bash
    java -jar assignment5.jar -file resources/scripts/check_script.txt
```    

---

## Troubleshooting

- **Error: "Unknown command":** Ensure the command is typed correctly.
- **File Not Found Errors:** Verify the file path and extension.
- **Unsupported Operation on Partial Split:** Certain operations may not support partial splits.
  Refer to the individual command descriptions.
