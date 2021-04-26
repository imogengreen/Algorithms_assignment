import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @param filename: the file from which we wish to extract the information about
 *                  bus stops
 * @param address:  the bus stop the user is trying to find information about
 * @return void
 * @throws IOException
 */
public class serachBusStop {

	public static void fileBuffer(String filename, String address) {

		TST<busStopInfo> ternarySearchTree = new TST<busStopInfo>();

		BufferedReader bufferR;
		try {
			// read through each line of the file
			if (filename != null) {

				bufferR = new BufferedReader(new FileReader(filename));
				String str = bufferR.readLine();

				while (str != null) {

					// make the current line that you are reading into a string array
					String[] strArr = str.split(",", -1);

					StringBuffer currentStr = new StringBuffer(strArr[2]);

					// checking if bus stop contains initials wb,sb, nb or eb
					if (currentStr.lastIndexOf("WB", 1) == 0 || currentStr.lastIndexOf("NB", 1) == 0
							|| currentStr.lastIndexOf("SB", 1) == 0 || currentStr.lastIndexOf("EB", 1) == 0) {

						String modifiedStr = modifyStopName(strArr);
						busStopInfo value = busStopDetails(strArr);
						ternarySearchTree.put(modifiedStr, value);

					}

					else {

						busStopInfo value = busStopDetails(strArr);
						ternarySearchTree.put(strArr[2], value);
					}

					str = bufferR.readLine();
				}
				bufferR.close();
				returnBusStopInfo(ternarySearchTree, address);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param currentLine: the array which contains all the details about the
	 *                     respective bus stop
	 * @return String: returns the modified version of the string with the initials
	 *         at the end of the string
	 * @throws IOException
	 */
	public static String modifyStopName(String[] currentLine) {

		StringBuilder sb = new StringBuilder();

		for (int i = 3; i < currentLine[2].length(); i++) {
			sb.append(currentLine[2].charAt(i));
		}

		sb.append(" ");
		sb.append(currentLine[2].charAt(0));
		sb.append(currentLine[2].charAt(1));

		String finalStr = sb.toString();

		return finalStr;
	}

	/**
	 * @param currentLine: the array which contains all the details about the
	 *                     respective bus stop
	 * @return busStopInfo: returns the object of the bus stop which contains all
	 *         the details about it
	 * @throws IOException
	 */
	public static busStopInfo busStopDetails(String[] currentLine) {

		// create object to be inserted in value of symbol table
		busStopInfo value = new busStopInfo();

		// System.out.println(value);
		// put string and corresponding value into tst
		// !!!!!!!!!!!!!!!!!check hoe to obtain str[9]
		value.stop_id = currentLine[0];
		value.stop_code = currentLine[1];
		value.stop_desc = currentLine[3];
		value.stop_lat = currentLine[4];
		value.stop_lon = currentLine[5];
		value.zone_id = currentLine[6];
		value.stop_url = currentLine[7];
		value.location_type = currentLine[8];
		value.parent_station = currentLine[9];

		return value;
	}

	/**
	 * @param tst:     the ternary data structure containing all the bus stops in
	 *                 the file
	 * @param busStop: the bus stop that the user is trying to find info about
	 * @return void: we just want to print out the info about the bus stop
	 * @throws IOException
	 */
	public static void returnBusStopInfo(TST<busStopInfo> tst, String busStop) {

		int counter = 0;
		for (String ob : tst.keyWithPrefix(busStop)) {

			busStopInfo v = tst.get(ob);

			System.out.println("Stop name:" + ob);
			System.out.println("Stop id of " + ob + ":" + v.getStopId());
			System.out.println("Stop code of " + ob + ":" + v.getStopCode());
			System.out.println("Stop desc of " + ob + ":" + v.getStopDesc());
			System.out.println("Stop lat of " + ob + ":" + v.getStopLat());
			System.out.println("Stop lon of " + ob + ":" + v.getStopLon());
			System.out.println("Zone id of " + ob + ":" + v.getZoneId());
			System.out.println("Stop url of " + ob + ":" + v.getStopUrl());
			System.out.println("Location type of " + ob + ":" + v.getLocationType());
			System.out.println("Parent Location of " + ob + ":" + v.getParentStation());
			System.out.println();
			counter++;
		}

		if (counter == 0) {
			System.out.println("No bus stop information found");
		}

	}

	public static void main(String[] args) {
		fileBuffer("stops.txt", "STEVESTON");
	}
}
