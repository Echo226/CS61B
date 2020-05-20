/* PixImage.java */

import java.rmi.activation.ActivationGroup_Stub;

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

  /**
   *  Define any variables associated with a PixImage object here.  These
   *  variables MUST be private.
   */

  private short[][][] imagerepresentation;


  /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   * @author Xinting
   */
  public PixImage(int width, int height) {
    // Your solution here.
    imagerepresentation = new short[width][height][3];
    int x, y, z;
    for (x = 0; x < width; x++) {
      for (y = 0; y < height; y++) {
        for (z = 0; z < 3; z++) {
          imagerepresentation[x][y][z] = 0;
        }
      }
    }
  }

  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   * @author Xinting
   */
  public int getWidth() {
    // Replace the following line with your solution.
    return imagerepresentation.length;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   * @author Xinting
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return imagerepresentation[0].length;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   * @author Xinting
   */
  public short getRed(int x, int y) {
    // Replace the following line with your solution.
    return imagerepresentation[x][y][0];
  }

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   * @author Xinting
   */
  public short getGreen(int x, int y) {
    // Replace the following line with your solution.
    return imagerepresentation[x][y][1];
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   * @author Xinting
   */
  public short getBlue(int x, int y) {
    // Replace the following line with your solution.
    return imagerepresentation[x][y][2];
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   * @author Xinting
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here.
    if ((x >= 0 && x <= getWidth()-1) && (y >= 0 && y <= getHeight()-1) &&
            (red >= 0 && red <= 255) && (green >= 0 && green <= 255) &&
            (blue >= 0 && blue <= 255)) {
      imagerepresentation[x][y][0] = red;
      imagerepresentation[x][y][1] = green;
      imagerepresentation[x][y][2] = blue;
    }
  }

  /**
   * toString() returns a String representation of this PixImage.
   *
   * This method isn't required, but it should be very useful to you when
   * you're debugging your code.  It's up to you how you represent a PixImage
   * as a String.
   *
   * @return a String representation of this PixImage.
   * @author Xinting
   */
  public String toString() {
    // Replace the following line with your solution.
    String result = "";
    int width = getWidth();
    int height = getHeight();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        result = result + getRed(x,y) + "    ";
      }
      result = result + '\n';
    }
    return result;
  }

  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   *
   * @author Xinting
   */
  public PixImage boxBlur(int numIterations) {
    // Replace the following line with your solution.
    int width = getWidth();
    int height = getHeight();
    PixImage original_image = this;
    int i;

    for (i = 0; i < numIterations; i++) {

      // get a extended_image, add a frame to the original image and set all frame_pixels to zero.
      PixImage extended_image = new PixImage(width + 2, height + 2);
      for (int y = 0; y < height + 2; y++) {
        extended_image.setPixel(0, y,(short)0, (short)0, (short)0);
        extended_image.setPixel(width+1, y,(short)0, (short)0, (short)0);
      }
      for (int x = 0; x < width + 2; x++) {
        extended_image.setPixel(x, 0,(short)0, (short)0, (short)0);
        extended_image.setPixel(x, height+1,(short)0, (short)0, (short)0);
      }
      for (int x = 1; x < width+1; x++) {
        for (int y = 1; y < height+1; y++) {
          extended_image.setPixel(x, y, original_image.getRed(x-1, y-1),
                  original_image.getGreen(x-1, y-1), original_image.getBlue(x-1, y-1));
        }
      }

      PixImage blurred_image = new PixImage(width, height);
      // operation
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          short[] blurredPixels = original_image.blurredPixels(x, y, extended_image);
          short redPixel = blurredPixels[0];
          short greenPixel = blurredPixels[1];
          short bluePixel = blurredPixels[2];
          blurred_image.setPixel(x, y, redPixel, greenPixel, bluePixel);
        }
      }
      original_image = blurred_image;
    }
    return original_image;
  }

  /**
   * blurredPixel() computes the blurred pixel vector (average pixel)
   * containing RGB value in a vector.
   * for a given pixel.
   * @param x the x location of the given pixel.
   * @param y the y location of the given pixel.
   * @param extended_image the extended_image.
   * @return
   */
  public short[] blurredPixels(int x, int y, PixImage extended_image) {
    short[] blurredPixels = new short[3];
    int width = getWidth();
    int height = getHeight();
    int sumRedPixel, sumGreenPixel, sumBluePixel;
    int numberOfNeighbors;
    sumRedPixel = extended_image.getRed(x, y) + extended_image.getRed(x, y+1) + extended_image.getRed(x, y+2)
            + extended_image.getRed(x+1, y) + extended_image.getRed(x+1, y+1) + extended_image.getRed(x+1, y+2)
            + extended_image.getRed(x+2, y) + extended_image.getRed(x+2, y+1) + extended_image.getRed(x+2, y+2);
    sumGreenPixel = extended_image.getGreen(x, y) + extended_image.getGreen(x, y+1) + extended_image.getGreen(x, y+2)
            + extended_image.getGreen(x+1, y) + extended_image.getGreen(x+1, y+1) + extended_image.getGreen(x+1, y+2)
            + extended_image.getGreen(x+2, y) + extended_image.getGreen(x+2, y+1) + extended_image.getGreen(x+2, y+2);
    sumBluePixel = extended_image.getBlue(x, y) + extended_image.getBlue(x, y+1) + extended_image.getBlue(x, y+2)
            + extended_image.getBlue(x+1, y) + extended_image.getBlue(x+1, y+1) + extended_image.getBlue(x+1, y+2)
            + extended_image.getBlue(x+2, y) + extended_image.getBlue(x+2, y+1) + extended_image.getBlue(x+2, y+2);
    // three situations?
    if ((x == 0 && y == 0) || (x == 0 && y == height - 1) || (x == width - 1 && y == 0) || (x == width - 1 && y == height - 1)) {
      numberOfNeighbors = 4;
    } else if ((x == 0) || (x == width - 1) || (y == 0) || (y == height - 1)) {
      numberOfNeighbors = 6;
    } else {
      numberOfNeighbors = 9;
    }

    blurredPixels[0] = (short)(sumRedPixel/numberOfNeighbors);
    blurredPixels[1] = (short)(sumGreenPixel/numberOfNeighbors);
    blurredPixels[2] = (short)(sumBluePixel/numberOfNeighbors);
    return blurredPixels;
  }


  /**
   * reflectImage() output the reflected image for a given image.
   * @return the reflected image.
   * @author Xinting
   */

  public PixImage reflectImage() {
    int width = getWidth();
    int height = getHeight();
    PixImage reflect_image = new PixImage(width+2, height+2);
    // nine situations?
    reflect_image.setPixel(0, 0, getRed(0, 0), getGreen(0, 0), getBlue(0, 0));
    reflect_image.setPixel(0, height+1, getRed(0, height-1), getGreen(0, height-1), getBlue(0, height-1));
    reflect_image.setPixel(width+1, 0, getRed(width-1, 0), getGreen(width-1, 0), getBlue(width-1, 0));
    reflect_image.setPixel(width+1, height+1, getRed(width-1, height-1), getGreen(width-1, height-1), getBlue(width-1, height-1));
    for (int y = 1; y < height+1; y++) {
      reflect_image.setPixel(0, y, getRed(0, y-1), getGreen(0, y-1), getBlue(0, y-1));
      reflect_image.setPixel(width+1, y, getRed(width-1, y-1), getGreen(width-1, y-1), getBlue(width-1, y-1));
    }
    for (int x = 1; x < width+1; x++) {
      reflect_image.setPixel(x, 0, getRed(x-1, 0), getGreen(x-1, 0), getBlue(x-1, 0));
      reflect_image.setPixel(x, height+1, getRed(x-1, height-1), getGreen(x-1, height-1), getBlue(x-1, height-1));
    }
    for (int x = 1; x < width+1; x++) {
      for (int y = 1; y < height+1; y++) {
        reflect_image.setPixel(x, y, getRed(x-1, y-1), getGreen(x-1, y-1), getBlue(x-1, y-1));
      }
    }

    return reflect_image;
  }

  /**
   * sumElementWise() computes the sum of the dot_product of two 2_dimensional arrays.
   * @param a the first 2_dimensional array.
   * @param b the second 2_dimensional array.
   * @return the sum of the dot_product of two 2_dimensional arrays.
   * @author Xinting
   */
  public static int sumElementWise(int[][] a, int[][] b) {
    int result = 0;
    int width = a.length;
    int height = a[0].length;
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        result = result + a[x][y] * b[x][y];
      }
    }
    return result;
  }


  /**
   * computeGradientV() calculate the gradientVector for the given (x,y) pixel and color channel.
   * @param x the x position of the given pixel.
   * @param y the y position of the given pixel.
   * @param reflected_image the reflected_image which extends the image.
   * @return the intensity of the output pixel.
   *
   * @author Xinting
   */

  public int[] computeGradientV(int x, int y, PixImage reflected_image) {
    int[] gradientVector = new int[6];

    int[][] gx_kernel = new int[][] { { 1, 2, 1},
                                      { 0, 0, 0},
                                      { -1, -2, -1}};
    int[][] gy_kernel = new int[][] { {  1,  0,  -1},
                                      {  2,  0,  -2},
                                      {  1,  0,  -1}};

    int[][] redNeighbors = new int[][] {{reflected_image.getRed(x, y), reflected_image.getRed(x, y+1), reflected_image.getRed(x, y+2)},
            {reflected_image.getRed(x+1, y), reflected_image.getRed(x+1, y+1), reflected_image.getRed(x+1, y+2)},
            {reflected_image.getRed(x+2, y), reflected_image.getRed(x+2, y+1), reflected_image.getRed(x+2, y+2)}};

    int[][] greenNeighbors = new int[][] {{reflected_image.getGreen(x, y), reflected_image.getGreen(x, y+1), reflected_image.getGreen(x, y+2)},
            {reflected_image.getGreen(x+1, y), reflected_image.getGreen(x+1, y+1), reflected_image.getGreen(x+1, y+2)},
            {reflected_image.getGreen(x+2, y), reflected_image.getGreen(x+2, y+1), reflected_image.getGreen(x+2, y+2)}};

    int[][] blueNeighbors = new int[][] {{reflected_image.getBlue(x, y), reflected_image.getBlue(x, y+1), reflected_image.getBlue(x, y+2)},
            {reflected_image.getBlue(x+1, y), reflected_image.getBlue(x+1, y+1), reflected_image.getBlue(x+1, y+2)},
            {reflected_image.getBlue(x+2, y), reflected_image.getBlue(x+2, y+1), reflected_image.getBlue(x+2, y+2)}};

    gradientVector[0] = PixImage.sumElementWise(gx_kernel, redNeighbors);
    gradientVector[1] = PixImage.sumElementWise(gy_kernel, redNeighbors);
    gradientVector[2] = PixImage.sumElementWise(gx_kernel, greenNeighbors);
    gradientVector[3] = PixImage.sumElementWise(gy_kernel, greenNeighbors);
    gradientVector[4] = PixImage.sumElementWise(gx_kernel, blueNeighbors);
    gradientVector[5] = PixImage.sumElementWise(gy_kernel, blueNeighbors);


    return gradientVector;
  }

  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
   * correct images and pass the autograder.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */
  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }


  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */
  public PixImage sobelEdges() {
    // Replace the following line with your solution.
    // Don't forget to use the method mag2gray() above to convert energies to
    // pixel intensities.
    int width = getWidth();
    int height = getHeight();
    PixImage result_image = new PixImage(width, height);
    PixImage reflected_image = reflectImage();


    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        // compute the gradient vector (red_gx, red_gy, green_gx, green_gy, blue_gx, blue_gy)
        int[] GradientV = computeGradientV(x, y, reflected_image);

        // compute the energy = red_gx^2 + red_gy^2 + green_gx^2 + green_gy^2 + blue_gx^2 + blue_gy^2  -> long
        long energy = (long)(Math.pow(GradientV[0], 2) + Math.pow(GradientV[1], 2) +
                Math.pow(GradientV[2], 2) + Math.pow(GradientV[3], 2) +
                Math.pow(GradientV[4], 2) + Math.pow(GradientV[5], 2));
        // compute the intensity using "mag2gray" method -> short
        short intensity = PixImage.mag2gray(energy);
        // set each pixel on (x,y) location of result_image
        result_image.setPixel(x, y, intensity, intensity, intensity);
      }
    }


    return result_image;

  }


  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * main() runs a series of tests to ensure that the convolutions (box blur
   * and Sobel) are correct.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
    System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                       "Input image:");
    System.out.print(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");
    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());


    PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image2);
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 2x3 image.");
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());



  }
}
