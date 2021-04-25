import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class serachBusStop {

	// reading lines from file
	public static void fileBuffer(String filename, String address) {

		TST<busStopInfo> terenarySearchTree = new TST<busStopInfo>();
		BufferedReader bufferR;
		try {
			// read through each line of the file
			if (filename != null) {

				bufferR = new BufferedReader(new FileReader(filename));
				String str;
				str = bufferR.readLine();

				while (str != null) {

					// array of elements
					String[] strArr = str.split(",");

					// does the string contain elements
					StringBuffer currentStr = new StringBuffer(strArr[2]);

					if (currentStr.lastIndexOf("WB", 1) == 0 || currentStr.lastIndexOf("NB", 1) == 0
							|| currentStr.lastIndexOf("SB", 1) == 0 || currentStr.lastIndexOf("EB", 1) == 0) {

						StringBuilder sb = new StringBuilder();

						for (int i = 3; i < strArr[2].length(); i++) {
							sb.append(strArr[2].charAt(i));
						}

						sb.append(" ");
						sb.append(strArr[2].charAt(0));
						sb.append(strArr[2].charAt(1));

						String finalStr = sb.toString();

						// create object to be inserted in value of symbol table
						busStopInfo value = new busStopInfo();

						// System.out.println(value);
						// put string and corresponding value into tst
						// !!!!!!!!!!!!!!!!!check hoe to obtain str[9]
						value.stop_id = strArr[0];
						value.stop_code = strArr[1];
						value.stop_desc = strArr[3];
						value.stop_lat = strArr[4];
						value.stop_lon = strArr[5];
						value.zone_id = strArr[6];
						value.stop_url = strArr[7];
						value.location_type = strArr[8];

						terenarySearchTree.put(strArr[2], value);
					}
					str = bufferR.readLine();
				}
				
				for (String ob : terenarySearchTree.keyWithPrefix(address)) {
					System.out.println(ob);
					busStopInfo v = terenarySearchTree.get(ob);
					
					System.out.println(v.getStopId());
				}
				bufferR.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		fileBuffer("stops.txt", "SB IOCO RD FS SUTER BROOK WAY");
	}
}
