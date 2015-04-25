package part1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Classifier {
	private int k;
	private float accuracy = 0f;

	private ArrayList<IrisData> irisTrain = new ArrayList<IrisData>();
	private ArrayList<IrisData> irisTest = new ArrayList<IrisData>();

	private Map<IrisData, ArrayList<Comparison>> compare = new HashMap<IrisData, ArrayList<Comparison>>();

	public double[] ranges = new double[4];

	private double sepalLRangeMAX = 0;
	private double sepalWRangeMAX = 0;
	private double petalLRangeMAX = 0;
	private double petalWRangeMAX = 0;

	private double sepalLRangeMIN = Double.MAX_VALUE;
	private double sepalWRangeMIN = Double.MAX_VALUE;
	private double petalLRangeMIN = Double.MAX_VALUE;
	private double petalWRangeMIN = Double.MAX_VALUE;

	public Classifier(String training, String test, int kValue) {
		k = kValue;

		readTraining(training);
		updateRanges();
		readTest(test);
		compareDists();

		performClassificationTest();
	}

	public void updateRanges() {
		double sepalLRange = sepalLRangeMAX - sepalLRangeMIN;
		double sepalWRange = sepalWRangeMAX - sepalWRangeMIN;
		double petalLRange = petalLRangeMAX - petalLRangeMIN;
		double petalWRange = petalWRangeMAX - petalWRangeMIN;

		ranges[0] = sepalLRange;
		ranges[1] = sepalWRange;
		ranges[2] = petalLRange;
		ranges[3] = petalWRange;
	}

	/**
	 * Does this to every node: Finds 'k' number of the NEAREST IrisData, and
	 * decides what group it belongs to.
	 *
	 * Also calculates the percentage of accuracy with this 'k' value
	 */

	private void performClassificationTest() {
		float totalCorrect = 0f;
		for (IrisData iris : irisTest) {
			ArrayList<Comparison> minimums = new ArrayList<Comparison>();
			ArrayList<Comparison> comp = compare.get(iris);
			for (int i = 0; i < k; i++) {
				double min = Double.MAX_VALUE;
				Comparison currentMin = null;
				for (Comparison c : comp) {
					if (c.done == false) {
						if (c.getDistance() < min) {
							min = c.getDistance();
							currentMin = c;
						}
					}
				}
				// Here the minimum has been decided
					currentMin.done = true;
					comp.set(comp.indexOf(currentMin), currentMin);
					minimums.add(currentMin);
			}
			int tally1 = 0;
			int tally2 = 0;
			int tally3 = 0;
			for (Comparison c : minimums) {
				int type = c.getDestination().getType();
				if (type == 1) {
					tally1++;
				} else if (type == 2) {
					tally2++;
				} else if (type == 3) {
					tally3++;
				}
			}
			int typeOfIris = 0;
			String type = "null";
			if (tally1 > tally3 && tally1 > tally2) {
				typeOfIris = 1;
				type = "Iris-setosa";
						
			} else if (tally2 > tally1 && tally2 > tally1) {
				typeOfIris = 2;
				type = "Iris-versicolor";
			} else if (tally3 > tally1 && tally3 > tally2) {
				typeOfIris = 3;
				type = "Iris-virginica";
			}
			System.out.println(iris.getPetalLength()+"   "+iris.getPetalWidth()+"   "+iris.getSepalLength()+"   "+iris.getSepalWidth()+"  "+type);

			if (iris.getType() == typeOfIris) {
				totalCorrect++;
			}
		}
		System.out.println("Total Instances Correct:    " + totalCorrect);
		System.out.println("Total Instances Altogether: " + irisTest.size());
		accuracy = (totalCorrect / irisTest.size());
		System.out.println("Accuracy: " + accuracy*100 + "% (using k=" + k + ")");
	}

	/**
	 * Compares the features of each instance within the test set with the
	 * training set.
	 */

	private void compareDists() {

		for (IrisData test : irisTest) {
			ArrayList<Comparison> forThisIris = new ArrayList<Comparison>();
			for (IrisData train : irisTrain) {

				forThisIris.add(new Comparison(this, test, train));
			}
			compare.put(test, forThisIris);
		}

	}

	private void readTraining(String training) {
		int i = -1;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(training));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				String[] x = line.split("\\s+");
				if (x[0].isEmpty()) {
					break;
				}
				i++;
				int type = 0;
				if (x[4].equalsIgnoreCase("Iris-setosa")) {
					type = 1;
				} else if (x[4].equalsIgnoreCase("Iris-versicolor")) {
					type = 2;
				} else if (x[4].equalsIgnoreCase("Iris-virginica")) {
					type = 3;
				}

				// Updating Ranges
				if (sepalLRangeMIN > Double.parseDouble(x[0])) {
					sepalLRangeMIN = Double.parseDouble(x[0]);
				}
				if (sepalLRangeMAX < Double.parseDouble(x[1])) {
					sepalLRangeMAX = Double.parseDouble(x[1]);
				}
				if (petalLRangeMIN > Double.parseDouble(x[2])) {
					petalLRangeMIN = Double.parseDouble(x[2]);
				}
				if (petalLRangeMAX < Double.parseDouble(x[3])) {
					petalLRangeMAX = Double.parseDouble(x[3]);
				}

				IrisData iris = new IrisData(Double.parseDouble(x[0]),
						Double.parseDouble(x[1]), Double.parseDouble(x[2]),
						Double.parseDouble(x[3]), type);
				irisTrain.add(iris);
			}
			System.out.println("Training Build Done with " + i + " instances");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readTest(String test) {
		int i = -1;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(test));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				String[] x = line.split("\\s+");
				if (x[0].isEmpty()) {
					break;
				}
				i++;
				int type = 0;
				if (x[4].equalsIgnoreCase("Iris-setosa")) {
					type = 1;
				} else if (x[4].equalsIgnoreCase("Iris-versicolor")) {
					type = 2;
				} else if (x[4].equalsIgnoreCase("Iris-virginica")) {
					type = 3;
				}

				IrisData iris = new IrisData(Double.parseDouble(x[0]),
						Double.parseDouble(x[1]), Double.parseDouble(x[2]),
						Double.parseDouble(x[3]), type);
				irisTest.add(iris);
			}
			System.out.println("Test Build Done with " + i + " instances");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getK() {
		return k;
	}

	public ArrayList<IrisData> getIrisTrain() {
		return irisTrain;
	}

	public ArrayList<IrisData> getIrisTest() {
		return irisTest;
	}

	public Map<IrisData, ArrayList<Comparison>> getCompare() {
		return compare;
	}

	public float getAccuracy() {
		return accuracy;
	}

}
