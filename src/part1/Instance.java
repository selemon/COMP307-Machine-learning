package part1;
import java.util.ArrayList;

public class Instance {
	private ArrayList<Double> attributes;



	private String className;

	public Instance(ArrayList<Double> a, String c ){
		attributes=a;
		className=c;
	}

	public void setAttributes(ArrayList<Double> attributes) {
		this.attributes = attributes;
	}

	public ArrayList<Double> getAttributes() {
		return attributes;
	}



	public String getClassName() {
		return className;
	}


	public void printToString() {
		for (Double d: attributes){
			System.out.print(d+"	");
		}
		System.out.println(this.className);

	}



}
