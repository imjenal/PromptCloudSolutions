import java.io.*;
import java.util.ArrayList;

public class BallDistribution {
	public static void getBags(int K_bags, int N_players, ArrayList<Integer> bags) throws IOException {
		BufferedWriter bw = new BufferedWriter(
				new FileWriter("D:\\PromptCloud\\balls_puzzle\\output.txt"));
		if (N_players >= K_bags) { // Exit the function when Number of Players are greater than Number of Bags
			bw.close();
			return;
		}
		bags.sort(null); // Sort the list 'bags'

		int minimum = bags.get(N_players - 1) - bags.get(0);
		int difference, start_index = -1, end_index = -1;

		for (int i = 1; i <= K_bags - N_players; i++) {
			difference = bags.get(i + N_players - 1) - bags.get(i);
			if (difference < minimum) {
				minimum = difference;
				start_index = i;
			}
		}
		end_index = start_index + N_players - 1;

		String output = "";
		for (int j = start_index; j < end_index; j++)
			output = output + Integer.toString(bags.get(j)) + ",";
		output = output + Integer.toString(bags.get(end_index));
		bw.write(output); // Write to the Output File
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		try {
			File inFile = new File("D:\\PromptCloud\\balls_puzzle\\input.txt");
			BufferedReader br = new BufferedReader(new FileReader(inFile)); // Reading the Input File
			int K_bags = (Integer.parseInt(br.readLine())); // Input then number of Bags
			String[] arr_bags = br.readLine().split(","); // Input the number of balls in each bag
			int N_players = (Integer.parseInt(br.readLine())); // Input the number of Players
			ArrayList<Integer> bags = new ArrayList<Integer>(K_bags);
			for (int i = 0; i < K_bags; ++i)
				bags.add(i, Integer.parseInt(arr_bags[i]));
			getBags(K_bags, N_players, bags); // Call the method 'getBags'
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
		}
	}
}
