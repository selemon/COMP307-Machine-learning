package part1;

public class IrisData {
	private double sepalLength;
	private double sepalWidth;
	private double petalLength;
	private double petalWidth;
	private int type;

	// type legend
	// 1 = Iris-setosa
	// 2 = Iris-versicolor
	// 3 = Iris-virginica

	public IrisData(double sepalLength, double sepalWidth, double petalLength,
			double petalWidth, int type) {
		super();
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
		this.type = type;
	}
	
	public double getSepalLength() {
		return sepalLength;
	}

	public double getSepalWidth() {
		return sepalWidth;
	}

	public double getPetalLength() {
		return petalLength;
	}

	public double getPetalWidth() {
		return petalWidth;
	}

	public int getType() {
		return type;
	}
	
	public String toString(){
		String i = null;
		if(type == 1){
			i = "Iris-setosa";
		}
		else if(type == 2){
			i = "Iris-versicolor";
		}
		else if(type == 3){
			i = "Iris-virginica";
		}
		else{
			throw new NullPointerException("Type is null.");
		}
		return sepalLength + " " + sepalWidth + " " + petalLength + " " + petalWidth + " " + i;
	}
}