
import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;


// scrambles the photo and stores each frame in the folder specified in the pictureScrambler method

public class HBOEffect {

/*
 * This class will take in a photo and scramble it.
 * 
 * We want to create N number of images in various stage of scrambledness and play
 * them in sequence so that displayed image goes from totally scrambled to the original.
 * 
 * We are going to scan the image one pixel at a time and swap that pixel value with another
 * random pixel on the image.
 * 
 * We are writing images index in the reverse order because we want the player to play
 * from scrambled to the unscrambled stage.
 * 
 */  


	public static void arrayRandomizer(int[] rows) {
		Random randomNumber = new Random();
		for (int i = 0; i < rows.length; i++) {
			int randomPosition = randomNumber.nextInt(rows.length); // random position within the array
			int a = rows[i]; // swaps value of array at position i with value at a random position
			rows[i] = rows[randomPosition];
			rows[randomPosition] = a;		
		}
		
	}
	
	public static void print_arr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
		System.out.print(arr[i]);
		}
	}
	
	

	public static void pictureScrambler(Picture v) {
		int i = 1;

		// two arrays of type integer, one for rows and another for columns
		int[] rowArray = new int[480];
		int[] columnArray = new int[600];
		for(int p = 0; p < v.getHeight(); p++) { // filling rowArray
			rowArray[p] = p;
		}

		for(int q = 0; q < v.getWidth(); q++) { // filling columnArray
			columnArray[q] = q;
		} // randomizing the rows and columns
		arrayRandomizer(rowArray);
		arrayRandomizer(columnArray);
		
		
		Random randomNumber = new Random();

		// setting pixel at column, row to random position
		for (int rowX = 0; rowX < v.getHeight(); rowX++) {
			int row = rowArray[rowX];

			/* the name of the folder (in this case called "test") will change depending
			on where you want to save the frames. */

			v.write("/Users/aarti/Documents/workspace/Code/test/Image" + i + ".bmp");

			i++;

			for (int p = 0; p < 20; p++) {
			int whiteRow = randomNumber.nextInt(v.getHeight()); // new random row for white
			int whiteColumn = randomNumber.nextInt(v.getWidth()); // new random column for white
			Pixel pix = v.getPixel(whiteColumn, whiteRow);
			pix.setRed(255);
			pix.setGreen(255);
			pix.setBlue(255);
			}
			
			for (int columnX =0; columnX < v.getWidth(); columnX++) {
				int column = columnArray[columnX];
				int newRow = randomNumber.nextInt(v.getHeight()); // new random row
				int newColumn = randomNumber.nextInt(v.getWidth()); // new random column
				int rgbSource = v.getBasicPixel(column, row); // source rgb value
				int rgbNew = v.getBasicPixel(newColumn, newRow); // new rgb value
				v.setBasicPixel(column, row, rgbNew); // setting original position with new rgb
				v.setBasicPixel(newColumn, newRow, rgbSource); // setting new position with old rgb
				
			}
		}	
	}

	public static void moviePlayer(String directory) {

	    int framesPerSec = 30;
	    FrameSequencer frameSequencer = new FrameSequencer(directory);
	    frameSequencer.setShown(true);
	    
	    // loop through the first second
	    for (int i = 10001; i < 69600; i+=60) { // the bounds of i will depend on the picture size
	    
	      // draw a filled rectangle
	      Picture p = new Picture(directory + "Image" + i + ".bmp");

	      // add frame to sequencer
	      frameSequencer.addFrame(p);
	    } // this ensures that the last frame is the OG picture

	    frameSequencer.addFrame(new Picture(directory + "Image69600.bmp"));
	    
	    // play the movie
	    frameSequencer.play(framesPerSec);
	  }

	public static void main(String[] args) {

		Picture image = new Picture("Bart.bmp");
		// scrambles the photo and stores each frame in the folder specified in the pictureScrambler method
		pictureScrambler(image);
	
		//plays the movie
		moviePlayer("/Users/aarti/Documents/workspace/Code/b/");
	
	
	}


}
