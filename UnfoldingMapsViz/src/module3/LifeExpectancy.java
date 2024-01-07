package module3;

import java.util.*;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

/**LifeExpectancy
 * An application with an interactive map that visualizes life expectancy at birth in different countries.
 * 
 * @author Sonia B
 * Date: Nov 15, 2023
 * */

public class LifeExpectancy extends PApplet{
	UnfoldingMap map;
	
	Map<String, Float> lifeExpByCountry;
	
	List<Feature> countries;
	List<Marker> countryMarkers;
	
	public void setup() {
		size(800, 600, OPENGL);
		background(210, 210, 210); //set canvas colour
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		lifeExpByCountry = loadLifeExpectancyFromCSV("LifeExpectancyWorldBankModule3.csv");
		
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		shadeCountries();
	}

	private Map<String, Float> loadLifeExpectancyFromCSV(String fileName){
		Map<String, Float> lifeExpMap = new HashMap<String, Float>();
		String[] rows = loadStrings(fileName);
		for(String row: rows) {
			String[] columns = row.split(",");
			if (columns.length > 5 && isNumeric(columns[5])) {
				float value = Float.parseFloat(columns[5]);
				lifeExpMap.put(columns[4], value);
			}
		}
		
		return lifeExpMap;
	}
	
	private static boolean isNumeric(String str) { //To check that the life expectancy column has a numeric value
		try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	}
	
	private void shadeCountries() { //set the country colours from red to blue
		for (Marker marker: countryMarkers) {
			String countryId = marker.getId();
			
			if(lifeExpByCountry.containsKey(countryId)) {
				float lifeExp = lifeExpByCountry.get(countryId);
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color(255-colorLevel, 100, colorLevel));
			}
			else {
				marker.setColor(color(150, 150, 150));
			}
		}
	}
	
	public void draw() {
		map.draw();
	}
}
