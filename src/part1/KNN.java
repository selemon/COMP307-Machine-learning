package part1;





import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class KNN {
	//fields to hold variables
	//	public static final String PATH_TO_TEST_SET = "iris-test.txt";
	//	public static final String PATH_TO_TRAINING_SET = "iris-training.txt";
	private double[] ranges = new double[4];
	private static int K = 1;
	private static ArrayList<Instance> testSet;
	private static ArrayList<Instance> trainingSet;
	static int c1=0;
	static int c2=0;
	Map<String, Integer> countMap = new HashMap<String, Integer>();


	private double[] min = {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE };
	private double[] max = {Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE };




	private int count;
	public static void main(String[] args){
		//arraylist of instances first to hold the test sets and another to hold the trainning set


		//for(Instance i: testSet){
		//	System.out.println(i.getClassName());
		//}
		setK ( Integer.parseInt(JOptionPane.showInputDialog("please enter an odd number or 0 to exit")));
		while(getK()!=0){

			new KNN();//.find(testSet, trainingSet, getK());
			setK(Integer.parseInt(JOptionPane.showInputDialog("please enter an odd number or 0 to exit")));

		}

	}








	public KNN(){
		//K = Integer.parseInt(JOptionPane.showInputDialog("please enter an odd number"));
		FileReader f1 = new FileReader("iris-test.txt");
		FileReader f2 = new FileReader("iris-training.txt");
		testSet = f1.buildInstances();
		trainingSet = f2.buildInstances();
		countMap=f2.getCount();
		RangeFinder(trainingSet);


		find(testSet, trainingSet);
	}

	public  void find(ArrayList<Instance> testSet, ArrayList<Instance> trainingSet){

		count=0;
		int Irissetosa = 0;
		int Irisversicolor = 0;
		int Irisvirginica = 0;



		int counter = 0;
		ArrayList<Neighbor> neighouber = new ArrayList<Neighbor>();
		for(Instance ins: testSet){
			neighouber = calculateDistances(trainingSet, ins);

			String name =findNearestNeighbors(neighouber,ins);

			if(name.equals(ins.getClassName())){
				counter++;
				System.out.println("name found = "+name+" expected == "+ins.getClassName());
				if(ins.getClassName().equalsIgnoreCase("  Iris-setosa")){
					Irissetosa++;
				}
				if(ins.getClassName().equalsIgnoreCase("  Iris-setosa")){
					Irisversicolor++;
				}
				if(ins.getClassName().equalsIgnoreCase("  Iris-virginica")){
					Irisvirginica++;
				}
			}
			else{
				count++;
				System.out.println("name found = "+name+" expected == "+ins.getClassName());
			}
		}

		//
		//
		//
		//

		System.out.println("setosa == "+Irissetosa+": versicolor == "+Irisversicolor+": virginica == "+Irisvirginica);

		System.out.println("accuracy = "+(double)counter/75*100+"%");

		System.out.println("hit = "+counter+":  miss = "+count);
	}


	private  String findNearestNeighbors(ArrayList<Neighbor> n, Instance ins) {
		// TODO Auto-generated method stub
		int K= getK();
		clear(countMap);
		String key;
		String name = null;
		for(int i=0; i<K;i++){
			key= n.get(i).getInstance().getClassName();
			countMap.put(key, countMap.get(key)+1);
		}

		int max=0;
		//Collections.max(countMap.values());
		for(String entry:countMap.keySet()){
			if (countMap.get(entry)>max) {
				name=entry;
				max=countMap.get(entry);
			}
		}



		return name;
	}

	private void clear(Map<String, Integer> map) {
		for(String key:map.keySet()){
			map.put(key, 0);
		}

	}








	public  void printNeighbors(ArrayList<Neighbor> neighbors) {
		int i = 0;
		for(Neighbor neighbor : neighbors) {
			Instance instance = neighbor.getInstance();

			System.out.println("\nNeighbor " + (i + 1) + ", distance: " + neighbor.getDistance());
			i++;



		}
	}


	public  ArrayList<Neighbor> getNearestNeighbors(ArrayList<Neighbor> distances) {
		ArrayList<Neighbor> neighbors = new ArrayList<Neighbor>();

		for(int i = 0; i < distances.size(); i++) {
			distances.get(i).getDistance();
			neighbors.add(distances.get(i));
		}

		return neighbors;
	}



	public  ArrayList<Neighbor> calculateDistances(ArrayList<Instance> allInstances, Instance singleInstance) {
		ArrayList<Neighbor> allNeighbors = new ArrayList<Neighbor>();

		for(Instance ins: allInstances){

			ArrayList<Double> array1 = singleInstance.getAttributes();
			ArrayList<Double> array2 = ins.getAttributes();
			double distance = 0;
			double sum=0.0;

			for(int i =0; i<array1.size();i++){
				sum += Math.pow(((array1.get(i)-array2.get(i))/ranges[i]),2);
			}

			distance= Math.sqrt(sum);
			allNeighbors.add(new Neighbor(ins, distance));
		}






		//for (Neighbor n: allNeighbors){
		//	n.getInstance().printToString();
		//}
		Collections.sort(allNeighbors);



		return allNeighbors;


	}


	private void RangeFinder(ArrayList <Instance> thisSet)
	{
		for(Instance myInstance : thisSet)
		{
			for(int i = 0 ; i < ranges.length ; i++)
			{
//				min[i] = myInstance.getAttributes().get(i);
				if(min[i] > myInstance.getAttributes().get(i))
					min[i] = myInstance.getAttributes().get(i);

				if(max[i] < myInstance.getAttributes().get(i))
					max[i] = myInstance.getAttributes().get(i);
			}
		}

		for(int i = 0 ; i < ranges.length ; i++)
			ranges[i] = max[i] - min[i];

//		for(int i = 0 ; i < ranges.length ; i++)
//			System.out.println(ranges[i]);
	}


	private static void setK(int k) {
		K=k;

	}

	private static int getK(){
		return K;
	}


}
