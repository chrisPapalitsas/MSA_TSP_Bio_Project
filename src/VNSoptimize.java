import java.util.ArrayList;
import java.util.Random;

public class VNSoptimize {
	private Solution X;
	private Solution Xnew;
	private Solution SolBest;

	public VNSoptimize(Solution x) {
		this.X = x;
		this.startVNS();
	}
	
	public void startVNS() {

		int startTime = (int)System.currentTimeMillis()/1000;
		do {
		
			Xnew = this.X.clone();

			Xnew = Xnew.randomSolution();
			do {
	
				double best_distance = this.X.getSolutionCost();
				for(int i=1; i< Xnew.getNodes().size()-1; i++) {
	
//					System.out.print(X.getNodes().size());
					for(int k = i+1 ; k<Xnew.getNodes().size();k++) {
						ArrayList<Node2> new_nodes = shift(Xnew.getNodes(), i, k);
						Xnew.setNodes(new_nodes);
	
					}
				}

				System.out.println("Xnew "+Xnew.getSolutionCost());
				System.out.println("X "+this.X.getSolutionCost());
				if(better(Xnew,this.X)) {
					this.X=Xnew;
				}

				
			}while(Xnew.getSolutionCost()<this.X.getSolutionCost());
			

		} while (((int)System.currentTimeMillis()/1000-startTime)<120);

		System.out.println("Final "+this.X.getSolutionCost());
	}
	
	private static int perturbationCount = 0 ;
	
	public boolean compareSolutions(Solution sol1,Solution sol2)
	{
		double sol1cost = sol1.getSolutionCost();
		double sol2cost = sol2.getSolutionCost();
		
		if(sol1cost<sol2cost) {
			return false;
		}else {
			return true;
		}
	}
	public Solution perturbation(Solution sol){

		Random r=new Random();

		
		 QubitNManager p = new QubitNManager(sol.getNodes());
	     p.prepeareAll();
	     ArrayList<Node2> res;
	     res = p.getNodes();
		
		
		int random;
		for (int i = 0; i < sol.getNodes().size()-2; i++) {
			random=r.nextInt(sol.getNodes().size());
			sol.exchange(i, i+2);
			sol.setNodes(shift(sol.getNodes(), i, 2));
		}
	       
		return sol;
		
		
		
	}
	// Do all 2-opt combinations
	private void TwoOpt()
	{
	    
	}
	
	public boolean better(Solution X1, Solution X2) {

		
		if (X1.getSolutionCost()<X2.getSolutionCost()) {
			return true;
		}
	
		
		return false;
	
}
	public ArrayList<Node2> shift(ArrayList<Node2> temp, int index, int levelNew) {
		int theseis = 0;
		theseis = (index + levelNew) % temp.size();
		Node2 a = new Node2(temp.get(index));
		if (theseis < 0)
			theseis += temp.size();
		temp.remove(index);
		temp.add(theseis, a);
		return temp;
	}
	public boolean isSolutionSame(Solution a,Solution b) {
		for(int i=0;i<a.getNodes().size();i++){
			if(a.getNodes().get(i).getNumber()!= b.getNodes().get(i).getNumber())
				return false;
		}
		return true;
	}
}
