import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class BenchMark2 { 
	private String filename; // onoma arxeiou
	private ArrayList<Node2> nodes; // lista me tous komvous
	private int number_of_nodes; // plithos komvwn
	private CostMatrixElement costMatrix[][]; // pinakas costous ΔΙΔΙΑΣΤΑΤΟΣ

	public BenchMark2(String filename) {
		this.filename = filename;
		nodes = new ArrayList<Node2>();
		initCost(); 

	}

	public void initCost() { // ΑΡΧΙΚΟΠΟΙΗΣΗ ΚΟΣΤΟΥΣ
	
		try {

			this.number_of_nodes = parser(filename); 

//			 System.out.println(number_of_nodes);
			this.costMatrix = new CostMatrixElement[number_of_nodes][number_of_nodes]; // ΟΠΙΝΑΚΑΣ ΚΟΣΤΟΥΣ ΑΡΧΙΚΟΠΟΙΕΙΤΕ ΜΕ ΒΑΣΗ ΤΟ ΠΛΗΘΟΣ ΚΟΜΒΩΝ
			// ΥΠΟΛΟΓΙΖΕΙ ΤΙΣ ΕΥΚΛΕΙΔΙΕΣ ΑΠΟΣΤΑΣΕΙΣ
			for (int i = 0; i < number_of_nodes; i++) {
				for (int j = 0; j < number_of_nodes; j++) {
					// ΤΥΠΟΣ ΕΥΚΛΕΙΔΙΑΣ ΑΠΟΣΤΑΣΗΣ
					double cost=Math.sqrt(Math.pow((nodes.get(j)
							.getX() - nodes.get(i).getX()), 2)
							+ Math.pow((nodes.get(j).getY() - nodes.get(i)
									.getY()), 2));
					
					costMatrix[i][j]=new CostMatrixElement(cost,false);
					
				}
				
			}
			for (int i = 0; i < number_of_nodes; i++) {
				for (int j = 0; j < number_of_nodes; j++) {
					if (costMatrix[i][j].getCost() == 1	&& costMatrix[j][i].getCost() == 0) {
						costMatrix[j][i].setCost(1);
					}
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkAndSetNotAccesibleElements() {
	
		for (int i = 0; i < costMatrix.length; i++) {
			for (int j = 0; j < costMatrix.length; j++) {
				double cost=costMatrix[i][j].getCost();

					costMatrix[i][j].setAccessible(true);	
//				}
			}
		}
	}

	public int parser(String filename) {
		
		int lines = 0;
		try {

			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			boolean foundfirstline = false;
			int count = 0;
			while ((strLine = br.readLine()) != null ) {
				if(strLine.equals("EOF")) {
					continue;
				}
				if (!foundfirstline && strLine.startsWith("  1")) { // kapou edw
																	// ksekinoun
																	// ta
																	// dedomena
																	// tou
																	// provlimatos

					foundfirstline = true;
				}

				if (foundfirstline) {
					StringTokenizer st = new StringTokenizer(strLine, "\t "); // spaw to line se token me vasi to tab
					st.nextToken();
					lines++; 
					if(lines==280) {
						System.out.println(strLine);
					}
					double xelement = Double.parseDouble(st.nextToken());
					double yelement = Double.parseDouble(st.nextToken());
					

					Node2 n = new Node2(count, xelement, yelement);

					nodes.add(n); 
					count++;
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lines; 
	}

	public void printCostMatrix(){
		for(int i=0;i<this.costMatrix.length;i++){
			for(int j=0;j<this.costMatrix.length;j++){
				System.out.print(costMatrix[i][j].getCost()+" \t");
			}
			System.out.println();
		}
		
	}
	public int getNumber_of_nodes() {
		return number_of_nodes;
	}

	public void setNumber_of_nodes(int number_of_nodes) {
		this.number_of_nodes = number_of_nodes;
	}

	public CostMatrixElement[][] getCostMatrix() {
		return costMatrix;
	}

	public ArrayList<Node2> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node2> nodes) {
		this.nodes = nodes;
	}

}
