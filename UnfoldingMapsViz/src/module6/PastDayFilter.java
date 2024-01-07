package module6;

public class PastDayFilter implements EarthquakeFilter{
	private boolean filterOff;
	
	PastDayFilter(boolean val){
		filterOff = !val;
	}
	
	public boolean satisfies(EarthquakeMarker m){
		//returns true if it's in the Past Day else false
		return m.getProperty("age").equals("Past Day") || filterOff;
	}
}
