package part1;


public class Comparison {
	private IrisData origin;
	private IrisData destination;
	private double distance;
	public boolean done = false; // Whether it is processed

	public Comparison(Classifier cl,IrisData originate,IrisData dest){
		origin = originate;
		destination = dest;

		double petLength =Math.pow(Math.abs(origin.getPetalLength() - dest.getPetalLength()),2)/cl.ranges[0];
		double petWidth = Math.pow(Math.abs(origin.getPetalWidth() - dest.getPetalWidth()),2)/cl.ranges[1];
		double sepLength = Math.pow(Math.abs(origin.getSepalLength() - dest.getSepalLength()),2)/cl.ranges[2];
		double sepWidth = Math.pow(Math.abs(origin.getSepalWidth() - dest.getSepalWidth()),2)/cl.ranges[3];

		distance = Math.sqrt(petLength + petWidth + sepLength + sepWidth);
	}

	public IrisData getOrigin() {
		return origin;
	}

	public IrisData getDestination() {
		return destination;
	}

	public double getDistance() {
		return distance;
	}
}
