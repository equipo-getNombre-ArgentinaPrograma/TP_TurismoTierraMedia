package fileManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

	public static ArrayList<String[]> get(String folder, String file) {
		ArrayList<String[]> promos = new ArrayList<String[]>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File("./" + folder + "/" + file + ".txt"));
			while (sc.hasNext())
				promos.add(sc.nextLine().split(";"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.close();
		return promos;
	}
}
