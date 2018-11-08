import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;



public class Solution{
	private Double cost; //ΤΕΛΙΚΟ ΚΟΣΤΟΣ ΤΗΣ ΛΥΣΗΣ
	private BenchMark2 problem; //ΠΡΟΒΛΗΜΑ ΤΗΣ ΛΥΣΗΣ
	private ArrayList<Node2> nodes; //ΛΙΣΤΑ ΚΟΜΒΩΝ
	public Solution(){
		
	}
	public Solution(Solution another){
		this.cost = another.cost;
		this.problem=another.problem;
		this.nodes=another.nodes;
	}
	public Solution(BenchMark2 problem) { //1ΟΣ ΚΑΤΑΣΚΕΥΑΣΤΗΣ ΟΤΑΝ ΘΕΛΩ ΝΑ ΔΗΜΙΟΥΡΓΗΣΩ ΤΟ ΠΡΩΤΟ SOLUTION
		//ΑΡΧΙΚΟΠΟΙΗΣΗ ΙΔΙΟΤΗΤΩΝ
		this.problem = problem;
		this.nodes = new ArrayList<Node2>();
		this.cost = 0.0;
	}
	public Solution(BenchMark2 problem,ArrayList<Node2> nodes) { //2OS ΚΑΤΑΣΚΕΥΑΣΤΗΣ ΟΤΑΝ ΕΡΧΟΜΑΙ ΑΠΟ ΠΡΟΗΓΟΥΜΕΝΗ ΛΥΣΗ
																//ΑΡΑ ΕΧΩ ΤΟΥΣ ΚΟΜΒΟΥΣ ΕΤΟΙΜΟΥΣ
		//ΑΡΧΙΚΟΠΟΙΗΣΗ ΜΕ ΒΑΣΗ ΤΗ ΛΙΣΤΑ ΚΟΜΒΩΝ
		this.problem = problem;
		this.nodes = nodes;
		this.cost = 0.0;
	}

	public Solution randomSolution(){
		/*
		 * ΔΗΜΙΟΥΡΓΕΙ ΜΙΑ ΤΥΧΑΙΑ ΛΥΣΗ
		 */
		Random r = new Random();
		ArrayList<Node2> randomNodes =  new ArrayList<Node2>();
		ArrayList<Node2> cloneNodes =  (ArrayList<Node2>)nodes.clone();
		int allnodes=cloneNodes.size(); //ΤΟ ΠΛΗΘΟΣ ΤΩΝ ΚΟΜΒΩΝ ΠΟΥ ΕΧΕΙ Η ΛΥΣΗ
		for (int i = 0; i < allnodes; i++) {
			int ind = r.nextInt(cloneNodes.size());
			//System.out.println(ind+" random");
			randomNodes.add(cloneNodes.get(ind)); //ΠΡΟΣΘΕΤΩ ΤΟΝ ΚΟΜΒΟ ΣΤΟ ΛΙΣΤΑ ΜΕ ΤΟΥΣ ΤΥΧΑΙΟΥΣ ΚΟΜΒΟΥΣ
			cloneNodes.remove(ind); //ΑΦΑΙΡΩ ΑΠΟ ΤΗ ΑΡΧΙΚΗ ΛΙΣΤΑ
		}
		Solution s= new Solution(this.problem, randomNodes);
		return s;
	}
	
	

	public boolean isFisibleSolution() {

		return true;
	}
	public void exchange(int violate,int i){
		Collections.swap(nodes, violate, i);
	}
	public boolean isAnyDuplicate(ArrayList<Node2> list) {
		
		HashSet set = new HashSet();
		for (int i = 0; i < list.size(); i++) {
			boolean val = set.add(list.get(i).getNumber());
			if (val == false) {
				return val;
			}
		}
		return true;
	}
	public Solution clone() {
		ArrayList<Node2> clonedNodes=Operations.copyArrayList(nodes);
		return new Solution(problem, clonedNodes);

	}
	
	public ArrayList<Node2> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node2> nodes) {
		this.nodes = nodes;
	}

	public BenchMark2 getProblem() {
		return problem;
	}
	public void setProblem(BenchMark2 problem) {
		this.problem = problem;
	}

	
	public void printNodes(){
		/*
		 * ΤΥΠΩΝΩ ΟΛΟΥΣ ΤΟΥΣ ΚΟΜΒΟΥΣ
		 */
		System.out.println("NODES");
		for (Node2 n : nodes) {
			System.out.println(n.toString());
		}
	}
	
	public double getSolutionCost(){
		
		double cost = 0;
		CostMatrixElement[][] costMatrix = this.problem.getCostMatrix();
		for (int i = 0; i < nodes.size()-1; i++) {
			cost += costMatrix[nodes.get(i).getNumber()][nodes.get(i + 1).getNumber()].getCost();
			
			
			
		}
		return cost;
	}
	
	public void printSolution(){
		
		double cost = 0;
		CostMatrixElement[][] costMatrix = this.problem.getCostMatrix();
		for (int i = 0; i < nodes.size()-1; i++) {
			cost += costMatrix[nodes.get(i).getNumber()][nodes.get(i + 1).getNumber()].getCost();
			
			
			
		}
		System.out.println("cost:"+cost);
	}
	
	public void printNodesNumber(){
		
		System.out.println("NODES");
		for (Node2 n : nodes) {
			System.out.print(n.getNumber()+"\t");
		}
		System.out.println();
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}

	
}
