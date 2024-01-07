package module6;

public class MagnitudeFilter implements EarthquakeFilter{
	private float magMin;
	private boolean filterOff;
	
	MagnitudeFilter(boolean bool, float val){
		filterOff = !bool;
		magMin = val;
	}
	
	public boolean satisfies(EarthquakeMarker m){
		//returns true if mag > magMin else false
		return (m.getMagnitude() > magMin) || filterOff;
	}
	
}
