package module3;

import java.util.Calendar;

import processing.core.*;

/** SunClock
 * An application that draws a sun that changes colours based on the time of day, on an image.
 * Author: UC San Diego Coursera Intermediate Programming team
 * @author Sonia B
 * Date: Nov 14, 2023
 * */

public class SunClock extends PApplet {
	private PImage myImage;
	
	public void setup() {
		size(400, 400); //set canvas size
		background(225, 225, 225); //set canvas colour
		String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTd4kciGFwkmjJNfyVD1tetVF-qxZt3UAvfB-zelKsKPflz96S3jCSbUaljXREyx1sDG7c&usqp=CAU";
		myImage = loadImage(url, "jpg"); //load image
		myImage.resize(width, 0); //resize loaded image
		image(myImage, 0,0); //display image
	}
	
	public void draw() {
		//int[] colours = setSunColour();
		int[] colours = setSunColourSec(second());
		fill(colours[0], colours[1], colours[2]);
		ellipse(width*4/5, height/5, width/6, height/6); //draw sun
	}
	
	public int[] setSunColourSec(float seconds) {
		int[] rgb = new int[3];
		float diffFrom30 = Math.abs(30-seconds);
		float ratio = diffFrom30/30;
		
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(210*ratio);
		rgb[2] = 0;
		return rgb;
	}
	
	public int[] setSunColour() {
		int[] rgb = new int[3];
		Calendar cal = Calendar.getInstance(getLocale());
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour < 5 || hour > 19) {
			rgb[0] = 0;
			rgb[1] = 0;
			rgb[2] = 0;
		}
		else {
			float diffFromMid = 7 - Math.abs(hour - 12);
			float ratio = diffFromMid/7;
			
			rgb[0] = (int)(255*ratio);
			rgb[1] = (int)(210*ratio);
			rgb[2] = 0;
		}
		return rgb;
	}
	
}
