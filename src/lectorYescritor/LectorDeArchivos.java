package lectorYescritor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LectorDeArchivos {

	public static ArrayList<String[]> get(String archivo) {
		ArrayList<String[]> promos = new ArrayList<String[]>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File("./entrada/" + archivo + ".txt"));
			while (sc.hasNext())
				promos.add(sc.nextLine().split(";"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.close();
		return promos;
	}
}
