import java.util.ArrayList;
import java.util.List;


public class Operations {
	public static ArrayList<Node2> copyArrayList(ArrayList<Node2> listToCopy){
		ArrayList<Node2> newList=new ArrayList<Node2>();
		for(Node2 n: listToCopy){
			newList.add(new Node2(n));
		}
		return newList;
	}
	
}
