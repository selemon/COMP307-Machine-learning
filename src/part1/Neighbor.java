package part1;
public class Neighbor implements Comparable<Neighbor> {
	private Instance instance;
	private double distance;



	public Neighbor(Instance instance, double distance) {
		this.setInstance(instance);
		this.setDistance(distance);
	}

	public void setDistance(double d) {
		this.distance = d;
	}

	public double getDistance() {
		return distance;
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	public Instance getInstance() {
		return instance;
	}

	@Override
	public int compareTo(Neighbor o) {
		// TODO Auto-generated method stub
		if(o.getDistance()>this.distance){
			return -1;
		}
		else if (o.getDistance()<this.distance){
			return 1;
		}
		return 0;
	}
}
