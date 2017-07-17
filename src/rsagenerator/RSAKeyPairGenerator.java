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
		 * We are requesting the random.org for 20 numbers generated between 18
		 * and 720 using the http GET request
		 */
		URL url = new URL("https://www.random.org/integers/?num=20&min=18&max=720&col=1&base=10&format=plain&rnd=new");
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestMethod("GET");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
		int num = 0;
		int count = 2;
		int[] arr = new int[3];
		/*
		 * In this section, we read the output that is coming from the GET
		 * request and check for Prime numbers and then call the RSA Algorithm .
		 * We also generate the inputs required for running the RSA Algorithm at
		 * this point.
		 */
		while (bufferedReader.readLine() != null && count > 0) {
			num = Integer.parseInt(bufferedReader.readLine());
			if (isPrime(num)) {
				arr[count] = num;
				count--;
			}
		}
		bufferedReader.close();
		int max = (arr[1] - 1) * (arr[0] - 1);
		int min = 1;
		max = Math.abs(max);
		Random r = new Random();
		int intermediate = r.nextInt(max - min) + min;
		while (intermediate <= 0) {
			intermediate = r.nextInt(max - min) + min;

		}
		arr[2] = intermediate;

		RSAImpl rsaImpl = new RSAImpl();
		rsaImpl.generateRSA(arr[0], arr[1], arr[2]);
	}

	/*
	 * Check if the input coming is a prime number or no
	 */
	public static boolean isPrime(int n) {
		if (n > 2 && (n & 1) == 0)
			return false;
		for (int i = 2; i <= n / 2; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
