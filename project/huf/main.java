import java.util.List;

public class main {

	public static void main(String[] args) {
		int[][] grid = new int[8][8];
		List<Integer> b = generator.numberGenerator(8*8);
		int k = 0;
		for(int i = 0; i < 8; i++) 
			for(int j = 0; j < 8; j++) {
				grid[i][j] = b.get(k++);
			}
		
		for(int i = 0; i < 8; i++) 
			for(int j = 0; j < 8; j++)
				if(grid[i][j] > 8)
					grid[i][j] = 0;
		
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++)
				System.out.print(grid[i][j] + " ");
			System.out.println();
		}
	}

}
