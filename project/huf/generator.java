import java.util.*;
public class generator {
	private static List<Integer> numbers = new LinkedList<>();
	
	public static List<Integer> numberGenerator(int n) {
		/* adds random to a linked list 
		of size n and makes it elements randomly sorted */
		 for (int i = 1; i <= n; i++) {
	            numbers.add(i);
	        }
	        Collections.shuffle(numbers);
	        return numbers;
	}
}
