package module6;

public class DepthFilter implements EarthquakeFilter{
	private float depMin;
	private boolean filterOff;
	
	DepthFilter(boolean bool, float val){
		filterOff = !bool;
		depMin = val;
	}
	
	public boolean satisfies(EarthquakeMarker m){
		//returns true if deeper than depMax else false
		return (m.getDepth() > depMin) || filterOff;
	}
}
