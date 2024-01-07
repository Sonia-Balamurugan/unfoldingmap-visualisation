package module3;

/**MapTest
 * An application with an interactive map.
 * 
 * @author Sonia B
 * Date: Nov 15, 2023
 * */

import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class MapTest extends PApplet {
	
	// The map
	UnfoldingMap map;
	
	public void setup() {
		size(800, 600, OPENGL); //create canvas
		background(210, 210, 210); //set canvas colour
		
		map = new UnfoldingMap(this, 100, 50, 600, 500, new Google.GoogleMapProvider()); //load map
		map.zoomTo(2); //set zoom level
		MapUtils.createDefaultEventDispatcher(this, map);
		
		Location loc = new Location(37.38, -5.98);
		SimplePointMarker val = new SimplePointMarker(loc);
		map.addMarker(val);
	}
	
	public void draw() {
		map.draw();
	}
}
