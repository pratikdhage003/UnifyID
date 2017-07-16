package rsagenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class RSAKeyPairGenerator {

	public static void main(String args[]) throws IOException {
		/*
		 * At this point, we are requesting the random org api 30 numbers
		 * ranging between 20 and 789 using the GET request
		 */
		URL url = new URL("https://www.random.org/integers/?num=30&min=20&max=789&col=1&base=10&format=plain&rnd=new");
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(http.getInputStream()));
		String line;
		int pr = 0;
		int passcount = 0;
		int[] pass = new int[3];
		/*
		 * In this section, we read the output that is coming from the GET
		 * request and check for Prime numbers and then call the RSA Algorithm .
		 * We also generate the inputs required for running the RSA Algorithm at
		 * this point.
		 */
		while (passcount < 2 && rd.readLine() != null) {
			line = rd.readLine();
			pr = Integer.parseInt(line);
			if (isPrime(pr)) {
				pass[passcount] = pr;
				passcount++;
			}

		}
		rd.close();
		int max = (pass[1] - 1) * (pass[0] - 1);
		int min = 1;
		max = Math.abs(max);
		Random r = new Random();
		int c_e = r.nextInt(max - min) + min;
		while (c_e <= 0) {
			c_e = r.nextInt(max - min) + min;

		}
		pass[2] = c_e;

		/*
		 * 
		 * 
		 * */
	}

	/*
	 * Check if the input coming is a prime number or no
	 */
	private static boolean isPrime(int pr) {
		if (pr % 2 == 0)
			return false;
		for (int i = 3; i * i <= pr; i += 2) {
			if (pr % i == 0)
				return false;
		}
		return true;
	}
}
