import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class serachBusStop {

	    public static void fileBuffer(String filename){
	        
	        BufferedReader bufferR;
			try {
				// read through each line of the file
				if (filename != null) {
					
					bufferR = new BufferedReader(new FileReader(filename));
					String str;
					str = bufferR.readLine();

					while (str != null) {

	                    System.out.println(str);
	                    str = bufferR.readLine();
					}
					bufferR.close();

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    public static void main(String[] args){
	        fileBuffer("stops.txt");
	    }
}
