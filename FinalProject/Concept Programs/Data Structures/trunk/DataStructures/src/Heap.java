/**
 * 
 */
package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


/**
 * The Class Heap.
 *
 * @author TomClarke
 * 
 * Sources:
 * Algorithms and Complexity, I Anders Yeo CS2860 September 2011
 * http://www.newthinktank.com/2013/04/java-heap-tutorial/
 * Big Java Book CAY S. HORSTMANN pages 676-692
 */
public class Heap {


	/** The filename that is used to reference the data set.
	 * When the program is downloaded this will be different so needs to be changed 
	 * it was possible to reference the file within the package however i have also 
	 * added in an extra method that uses another random data set.
	 */
	static String fileName = "/Users/TomClarke/SkyDrive/ComputerScience/Year3/DataSet.txt";


	/** The list that will store the inputs from the data set. */
	static ArrayList<Integer> list = new ArrayList<Integer>();



	/**
	 * Initailise reads the file with the data set and this will contain the integers that need to populate 
	 * the tree. at each line the find integer method is called to look at each string. 
	 * the IOException will trigger if the input file is unable to be read or within the wrong location. 
	 * A list of the integers taken are printed 
	 * 
	 */
	public void Initialise(){

		Heap heap = new Heap();
/*
		try {
			File file = new File(fileName);
			BufferedReader in = new BufferedReader(new FileReader(file));
			String s; 
			while ((s = in.readLine()) != null) {
				findInteger(s);
			}
			in.close();
		} catch (IOException e) {
			System.out.println("File Read Error: ");
		}
		System.out.println("Integers: " + list);
*/
		 Random randomGenerator = new Random();
		    for (int i = 1; i <= 20; ++i){
		      int randomInt = randomGenerator.nextInt(100);
		      list.add(randomInt);
		   
		      System.out.println("Generated : " + randomInt); 
		      }
		heap.Run();


	}

	/**
	 * Find integer takes the strings taken from the dataset and they are split 
	 * so any string with a gap ", " is then split and the intergers are seperated and 
	 * added to the list. 
	 *
	 * @param s the split of the integers within the string.
	 */
	public static void findInteger(String s){
		String [] parts = s.split(", ");


		for (int i = 0; i < parts.length; i++) {
			try{
				int j = Integer.parseInt(parts[i]);
				list.add(j);
			} catch (Exception e){}
		}
	}	




	/**
	 * Run method calls the heap method and passes the list size and the list 
	 * that is full of the integer values. 
	 * 
	 * After this then prints the sorted list. 
	 */
	public void Run() {

		heap(list.size(),list);
		System.out.println(list);

	}



	/**
	 * Heap method calls the makeHeap giving the list and the size of that list 
	 * then a for loop allows the list to be iterated from the bottom up 
	 * then the values are passed to a temp value to hold the integers 
	 * that are stored within that iteration.
	 *
	 * @param size the size
	 * @param list the list
	 */
	private void heap(int size, ArrayList<Integer> list) {
		makeHeap(size, list);
		for (int i = size -1; i >= 1; i--){

			int temp = list.get(i);
			list.set(i, list.get(0));
			list.set(0, temp);
			sift(0,list,i);

		}

	}


	/**
	 * Make heap function to iterate through half of the tree
	 *and call the sift function taking the sort number which will be the parent
	 *list and size, that creates the heap.  
	 *
	 * @param size the size
	 * @param list the list
	 */
	private void makeHeap(int size, ArrayList<Integer> list) {

		for (int i = ((size / 2) - 1); i>= 0; i--){

			sift(i, list,size);
		}
		System.out.println("Heap Created :"+list);	
	}


	/**
	 * Sift takes the parent, list and the size of that list and uses a while 
	 * loop to check that each element is compared to its two children. If
	 * it is smaller then either then it is swapped to a larger of the children
	 * 
	 * This process then needs to be done until the parent is in the correct position.
	 *
	 * @param i the parent
	 * @param list is the list that stores the tree
	 * @param j the size of the list
	 */
	private void sift(int i, ArrayList<Integer> list, int j) {


		int key = list.get(i);
		int parent = i;
		int none = 1;
		int large;

		while (2*parent+1 <= (j-1)&& none > 0){
			if (2*parent + 1 < (j -1) && list.get(2*parent+1)<list.get(2*parent+2)){
				large = 2* parent + 2;
			}
			else{
				large = 2 * parent + 1;
			}

			if(key < list.get(large)){
				list.set(parent,list.get(large));
				parent = large;
			}
			else{
				none = 0;
			}
			list.set(parent, key);
		}
	}
}
