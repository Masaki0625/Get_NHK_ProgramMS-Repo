import java.util.Base64;

public class EncodeAPIKey {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		String encodeAPIKey = Base64.getEncoder().encodeToString(args[0].getBytes());
		
		System.out.println(encodeAPIKey);
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Processing Time: " + (endTime - startTime) + " ms");
	}
}
