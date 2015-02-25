/**
 * 
 */
package src;

import java.util.Scanner;

/**
 * The Class Runner.
 *
 * @author TomClarke
 * Runs all the data structures and allows them to be selected by the user
 * using a switch function that asks for an input from the user.
 */
public class Runner {
	
	/**
	 * The main method.
	 * initalises the data structures and asks for user input.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		PrioirtyQueueClass queue = new PrioirtyQueueClass();
		DynamicTree tree = new DynamicTree();
		HashFunction hash = new HashFunction();
		Heap heap = new Heap();
		Scanner scan = new Scanner(System.in);
		int in = 0;
		
		System.out.println("How would you like to change the DataSet? (input number) ");
		System.out.println("0: END PROGRAM ");
		System.out.println("1: Priority Queue ");
		System.out.println("2: Dynamic Tree ");
		System.out.println("3: Hashing ");
		System.out.println("4: Heap ");
		
		in = scan.nextInt();
		
		switch (in) {
		case 1: queue.Initialise();
		System.out.println("Use another data structure input: ");
		in = scan.nextInt();
		case 2: tree.Initailise();
		System.out.println("Use another data structure input: ");
		in = scan.nextInt();
		case 3: hash.Initialise();
		System.out.println("Use another data structure input: ");
		in = scan.nextInt();
		case 4: heap.Initialise();
		System.out.println("Use another data structure input: ");
		in = scan.nextInt();
		default: System.out.println("Please input a number corresponding to the Data Structures");
		in = scan.nextInt();
		if (in == 0) break;
		}
		}
		
		
	}


