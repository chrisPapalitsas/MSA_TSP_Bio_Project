import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String... arg) {
		BenchMark2 bm = new BenchMark2("problems/bayg29.tsp");

		//dhmiourgw lysh opws einai oi komvoi sto arxeio
		Solution sl = new Solution(bm);
		sl.setNodes(bm.getNodes());
		//dhmiourgw mia random lysi
		sl=sl.randomSolution();
		long time1=System.currentTimeMillis();
		VNSoptimize vnsc =  new VNSoptimize(sl);
		long time2=System.currentTimeMillis();
		long timeinsec=(time2-time1)/1000;
	}

	
}
