import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

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
	public static void pictureScrambler(Picture v) {
		int i = 10000 + (v.getHeight() * v.getWidth());
		Random randomNumber = new Random();
		for (int row = 0; row < v.getHeight(); row++) { // setting pixel at column, row to random position
			for (int column =0; column < v.getWidth(); column++) { 
				int newRow = randomNumber.nextInt(v.getHeight()); // new random row
				int newColumn = randomNumber.nextInt(v.getWidth()); // new random column
				int rgbSource = v.getBasicPixel(column, row); // source rgb value
				int rgbNew = v.getBasicPixel(newColumn, newRow); // new rgb value
				v.setBasicPixel(column, row, rgbNew); // setting original position with new rgb
				v.setBasicPixel(newColumn, newRow, rgbSource); // setting new position with old rgb
				v.write("/Users/aarti/Documents/workspace/Code/b/Image" + i + ".bmp");
				// the name of the folder (in this case called "b") will change depending
				//on where you want to save the frames.
				i--;
			}
		}
	}
	
	  public static void moviePlayer(String directory)
	  {
	    int framesPerSec = 30;
	    FrameSequencer frameSequencer = new FrameSequencer(directory);
	    frameSequencer.setShown(true);
	    
	    // loop through the first second
	    for (int i = 10001; i < 69600; i+=60) // the bounds of i will depend on the picture size
	    {
	      // draw a filled rectangle
	      Picture p = new Picture(directory + "Image" + i + ".bmp");

	      // add frame to sequencer
	      frameSequencer.addFrame(p);
	    }										// this ensures that the last frame is the OG picture
	    frameSequencer.addFrame(new Picture(directory + "Image69600.bmp"));
	    
	    // play the movie
	    frameSequencer.play(framesPerSec);
	  }

	public static void main(String[] args) {
	Picture image = new Picture("BartCopy.bmp");
	// scrambles the photo and stores each frame in the folder specified in the pictureScrambler method
	pictureScrambler(image);
	
	//plays the movie
	moviePlayer("/Users/aarti/Documents/workspace/Code/b/");
	
	
	}
}
