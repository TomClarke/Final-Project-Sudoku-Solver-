package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;


/**
 * The Class GAC class uses a process of reducing the domains to provide the variable with
 * a value. This is done by reducing the domains of all of the current scope of each variable
 * 
 */
public class GAC implements Constraint {


	/** The constraint variables is a list of the variables within a scope. */
	public List<Variable> constraintVariables = new LinkedList<Variable>();

	/** The variables that are a list of all the variables. */
	public List<Variable> variables = new ArrayList<Variable>();

	/** The network that is creating a CSP. */
	public Network net = new Network() ;

	/** if the board is solved. */
	boolean solved = false;

	/** The allDiff is the constraint that can be applied in this case all the values should
	 * be different. */
	public AllDifferent allDiff = new AllDifferent();


	/** The queue that contains the variable in order of the most constrained variable is
	 * at the head
	 */
	PriorityQueue<Variable> queue = new PriorityQueue<Variable>();

	/** The restrictions list contains the current scope */
	LinkedList<Variable> restrictions = new LinkedList<Variable>();



	/** 
	 * Clears the constraints within the scope for each application of the algorithm. 
	 * the global variable list is also cleared for a new instance of the algorithm. 
	 * 
	 * the variable list is added to the current variables within this class  
	 * The reduce Arc method is called
	 * the GAC algorithm method is called and given the variables list and started at the
	 * first variable within that list. 
	 * When the algorithm returns with a boolean it is set to true if the sudoku 
	 * was solved and false if it was not possible. This is then throws an error. 
	 * 
	 * the updated variables list is then returned. 
	 * 
	 **/
	@Override
	public List<Variable> applyConstraints(List<Variable> variableList) {
		constraintVariables.clear();
		variables.clear();

		this.variables = variableList;

		System.out.println("GAC:");

		reduceArc();

		solved = GACSolve(queue.poll().number,variables);

		if (!solved){
			System.out.println("cannot be solved");
		}

		return variables;
	}


	/**
	 * This is GAC algorithm that i implemented. this is spread over 3 classes and does differ
	 * http://en.wikipedia.org/wiki/AC-3_algorithm
	 * Input:
	 * A set of variables "variables"
	 * A set of domains variables.get(i).domain.domain D(x) for each variable x 
	 * in X. D(x) contains  vx0, vx1... vxn, the possible values of x
	 * A set of unary constraints R1(x) on variable x that must be satisfied
	 * A set of binary constraints R2(x, y) on variables x and y that must be satisfied
	 * Output:
	 * Arc consistent domains for each variable.
	 * 
	 * function ac3 (X, D, R1, R2)
	 * // Initial domains are made consistent with unary constraints.
	 * for each x in X
	 * D(x) := { x in D(x) | R1(x) }
	 * // 'worklist' contains all arcs we wish to prove consistent or not.
	 * worklist := { (x, y) | there exists a relation R2(x, y) or a relation R2(y, x) }
	 * 
	 * do while worklist not empty
	 * select any arc (x, y) from worklist
	 * worklist := worklist - (x, y)
	 * if arc-reduce (x, y)
	 * if D(x) is empty
	 * return failure
	 * else
	 * worklist := worklist + { (z, x) | z != y and there exists a relation R2(x, z) or a relation R2(z, x) }
	 * }
	 * 
	 * GAC restricts the domains of the variables to find the most constrained variable, to use this first is 
	 * helpful, thus the use of a priority queue, which supplies the algorithm with a set of variable that 
	 * have had their domains pruned from within their scope.
	 *
	 * @param num the number of the current variable
	 * @param var the list of all the variables
	 * @return true, if successful
	 */
	private boolean GACSolve(int num, List<Variable> var) {


		if(queue.isEmpty()){

			return true;

		}

		if(var.get(num).isAssigned()){

			return GACSolve(queue.poll().number,var);
		}


		for(int val : var.get(num).domain.domain){

			if(allDiff.allDifferent(num,val,var)) {



				//assigns variable and sets it to assigned = true
				var.get(num).assignVar(val);
				reduceArc();


				if(GACSolve(queue.poll().number,var))

					//only returns true if board can be solved 
					return true;

			}
		}
		var.get(num).unAssignVar();

		return false;
	}

	/**
	 * function arc-reduce (x, y)
	 * bool change = false
	 * for each vx in D(x)
	 * find a value vy in D(y) such that vx and vy satisfy the constraint R2(x, y)
	 * if there is no such vy {
	 * D(x) := D(x) - vx
	 * change := true
	 * }
	 * return change.
	 * 
	 * this algorithm takes all of the variables and passes them to the reduce arc method within
	 * the all different class that compares the values within the scope of the current , the queue
	 * is then updated. 
	 *
	 */

	private void reduceArc() {
		queue.clear();
		for(int i = 0; i < 81; i ++){
			if(!variables.get(i).isAssigned()){

				variables = allDiff.reduceArc(i,variables);
				variables.get(i).number = i;
			}
			queue.add(variables.get(i));
			//System.out.println("queue:"+queue.element().domain.domain.size());
		}
	}


	/**
	 * this is called to return if the algorithm was solved and returns the variable solved. 
	 **/
	@Override
	public boolean solved() {

		return solved;
	}

}



