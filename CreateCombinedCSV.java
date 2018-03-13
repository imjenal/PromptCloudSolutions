import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.TreeSet;

public class CreateCombinedCSV {
	public static void main(String args[]) throws Exception {
		// Read the data of name_dob_height.json
		BufferedReader br = new BufferedReader(
				new FileReader("D:\\PromptCloud\\json_operations\\name_dob_height.json"));
		String s = "";
		StringBuilder nameDOBHeightJsonStr = new StringBuilder();
		while ((s = br.readLine()) != null) {
			nameDOBHeightJsonStr.append(s);
		}
		String nameDOBHeightStr = nameDOBHeightJsonStr.toString().replace("\t", "");
		JSONArray nameDOBHeightJson = (JSONArray) new JSONParser().parse(nameDOBHeightStr);

		// Read the data of name_dob_salary.json
		br = new BufferedReader(new FileReader("D:\\PromptCloud\\json_operations\\name_dob_salary.json"));
		StringBuilder nameDOBSalaryJsonStr = new StringBuilder();
		while ((s = br.readLine()) != null) {
			nameDOBSalaryJsonStr.append(s);
		}
		String nameDOBSalaryStr = nameDOBSalaryJsonStr.toString().replace("\t", "");
		JSONArray nameDOBSalaryJson = (JSONArray) new JSONParser().parse(nameDOBSalaryStr);

		StringBuilder csvOutput = new StringBuilder();
		CombinedCSV csvRow = null;
		String sFName;
		String sLName;
		String sDOB;
		Long sSalary;
		String hFName;
		String hLName;
		String hDOB;
		Integer hHeight;

		TreeSet<CombinedCSV> csvSet = new TreeSet<CombinedCSV>();

		for (Object jS : nameDOBSalaryJson) {
			JSONObject salaryObj = (JSONObject) jS;
			for (Object jH : nameDOBHeightJson) {
				JSONObject heightObj = (JSONObject) jH;
				sFName = salaryObj.get("First_name").toString();
				sLName = salaryObj.get("Last_name").toString();
				sDOB = salaryObj.get("Date_of_birth").toString();
				try {
					sSalary = Long.parseLong(salaryObj.get("Salary").toString());
				} catch (NumberFormatException ne) {
					sSalary = new Long(-1); // Setting salary as -1 for invalid numbers.
				}
				hFName = heightObj.get("First_name").toString();
				hLName = heightObj.get("Last_name").toString();
				hDOB = heightObj.get("Date_of_birth").toString();
				try {
					hHeight = Integer.parseInt(heightObj.get("Height").toString());
				} catch (NumberFormatException ne) {
					hHeight = new Integer(-1); // Setting height as -1 for invalid numbers.
				}
				if (sFName.equals(hFName) && sLName.equals(hLName) && sDOB.equals(hDOB))
					csvRow = new CombinedCSV(hFName, hLName, hDOB, sSalary, hHeight);
				csvSet.add(csvRow);
			}
		}
		for (CombinedCSV ob : csvSet)
			csvOutput.append(ob.toString()).append("\n");
		BufferedWriter output = null;
		try {
			File file = new File("D:\\PromptCloud\\json_operations\\CombinedData_Output.csv");
			output = new BufferedWriter(new FileWriter(file, false));
			output.write(csvOutput.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}
}
