package part1;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileReader {

	private String dataFilePath;
	private Map<String, Integer> count;
	public FileReader(String dataFilePath) {
		this.dataFilePath = dataFilePath;
	}



    public ArrayList<Instance> buildInstances() {
		BufferedReader reader = null;
		DataInputStream dis = null;
		count = new HashMap<String, Integer>();


		ArrayList<Instance> instances = new ArrayList<Instance>();

        try {

           File f = new File(dataFilePath);
           FileInputStream fis = new FileInputStream(f);
           reader = new BufferedReader(new InputStreamReader(fis));
           String nameClass;
           // read the first Instance of the file
           String line ;
           int numa=1;

           ArrayList<Double> attributes ;

           while (reader.ready()) {


        	   line = reader.readLine();
        	   attributes = new ArrayList<Double>();
        	   Scanner scan = new Scanner(line);

        	   while(scan.hasNextDouble()){
        		   attributes.add(scan.nextDouble());

        	   }
        	   String name = scan.nextLine();
        	   //create a new instance with attributes of arraylist and the class name
        	   instances.add(new Instance(attributes, name));
        	   if(!count.containsKey(name)){
        		   count.put(name, 0);
        	   }
        	   scan.close();

           }
           reader.close();

        }
        catch (IOException e) {
           System.out.println("Uh oh, end of the line: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Uh oh, end of the line: " + e.getMessage());
        }
//        finally {
//           if (dis != null) {
//              try {
//                 dis.close();
//              } catch (IOException ioe) {
//                 System.out.println("IOException error trying to close the file: " + ioe.getMessage());
//              }
//           }
//        }

		return instances;
	}

	public void setDataFilePath(String dataFilePath) {
		this.dataFilePath = dataFilePath;
	}

	public String getDataFilePath() {
		return dataFilePath;
	}

	public Map<String, Integer> getCount(){
		return count;
	}


//	public static void main(String[] args) {
//		new FileReader("iris-test.txt").buildInstances();
//
//
//	}








}