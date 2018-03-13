import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class SupportingNumbers {

	public static void isSupporting(String[] a, String[] str) throws IOException {
		File outFile = new File("D:\\PromptCloud\\match_your_binary_supporter\\output.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
		int len = a.length;
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for (int i = 0; i < len; i++) {
			if (hm.containsKey(a[i]))
				hm.put(a[i], 1);
			else
				hm.put(a[i], 0);
		}
		int number = 0;
		for (int i = 0; i < str.length; i++) {
			try {
				number = Integer.parseInt(str[i]);
				int noOfDigits = Integer.toBinaryString(number).length();
				int num = number + noOfDigits;
				if (hm.containsKey(str[i])) {
					if (hm.get(str[i]) < 1) {
						hm.put(str[i], hm.get(str[i]) + 1);
						String s = Integer.toString(num);
						if (hm.containsKey(s)) {
							bw.write(str[i] + " is SUPPORTED BY THE NUMBER " + num);
							bw.newLine();
						} else {
							bw.write(str[i] + " is NOT SUPPORTED");
							bw.newLine();
						}
					} else {
						bw.write("Exception : " + str[i] + " is a duplicate entry!");
						bw.newLine();
					}
				} else {
					bw.write("Exception : " + str[i] + " is not in the list!");
					bw.newLine();
				}
			} catch (NumberFormatException e) {
				if (str[i].isEmpty()) {
					bw.write("No input provided!");
					bw.newLine();
				} else {
					bw.write("Exception : " + str[i] + " is a string!");
					bw.newLine();
				}
			}
		}
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		try {
			File inFile = new File("D:\\PromptCloud\\match_your_binary_supporter\\input.txt");
			BufferedReader br = new BufferedReader(new FileReader(inFile));
			String[] in = br.readLine().split(", ");
			int len = in.length;
			in[0] = in[0].replace("[", "");
			in[len - 1] = in[len - 1].replace("]", "");
			String s = "";
			StringBuffer strr = new StringBuffer();
			while ((s = br.readLine()) != null) {
				strr.append(s);
				strr.append(",");
			}
			strr.deleteCharAt(strr.length() - 1);
			String str = strr.toString();
			String[] str_arr = str.split(",");
			isSupporting(in, str_arr);
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
		}
	}
}