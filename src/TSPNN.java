import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
 
public class TSPNN
{
    private int numberOfNodes;
    private Stack<Integer> stack;
 
    public TSPNN()
    {
        stack = new Stack<Integer>();
    }
 
    public void tsp(double adjacencyMatrix[][],ArrayList<Node> nodes )
    {
        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];
        visited[1] = 1;
        stack.push(1);
        int element, dst = 0, i;
        double min = Double.MAX_VALUE;
        boolean minFlag = false;
        System.out.println(1);
        double cost=0;
        while (!stack.isEmpty())
        {
            element = stack.peek();
            i = 1;
            min = Double.MAX_VALUE;
            //chekarw to element me olous tous komvous gia na vrw to kontinotero geitona
            while (i <= numberOfNodes)
            {
                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0)
                {
                	double tempcost=cost+adjacencyMatrix[element][i];
                    if (min > adjacencyMatrix[element][i] && nodes.get(i).getReadytime()<=tempcost && nodes.get(i).getDuetime()>=tempcost)
                    {
                        min = adjacencyMatrix[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag)
            {
                visited[dst] = 1;
                stack.push(dst);
                System.out.println(dst);
                minFlag = false;
                cost+=adjacencyMatrix[element][dst];
                continue;
            }
            stack.pop();
        }
    }
 

    
}
