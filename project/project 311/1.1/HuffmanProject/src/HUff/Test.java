import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Test {

	public static void displayThetext(String fn) {
		try (FileReader fr = new FileReader(fn); BufferedReader br = new BufferedReader(fr)) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] s;
				// s.useDelimiter("[^A-Za-z]+");
				s = line.split(" ");
				System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println("there is an error");
		}
	}

	public static void main(String[] args) throws IOException {
		System.out
				.println("\n======================================================================================\n");
		String name = "";
		String fn = "C:\\Users\\Lenovo\\Desktop\\test.txt";
		System.out.println("##################### The original Text ###################");
		displayThetext(fn);
		System.out.println("\n##################### The original Text ###################");
		FileReader fileReader = new FileReader(fn);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String temp = "";
		while ((temp = bufferedReader.readLine()) != null) {
			name += temp + "\n";
		}
		bufferedReader.close();
		int ch[] = new int[256];
		for (int i = 0; i < 256; i++) {
			ch[i] = 0;
		}
		for (int i = 0; i < name.length(); i++) {
			int index = (int) name.charAt(i);
			ch[index] += 1;
		}
		HuffmanTree b = new HuffmanTree();
		for (int i = 0; i < 256; i++) {
			if (ch[i] > 0) {
				b.singleton((char) i, ch[i]);
			}
		}

		b.formTree();
		System.out.println(
				"\n###############################################################################################\n");

		b.gCodes();
		System.out.println(
				"\n###############################################################################################\n");

		System.out.println("Printing Code words for each Character:");
		String code = b.pCodes(name);
		System.out.println("From Main the code is:" + code);
		FileWriter w = new FileWriter("encoded.txt");
		BufferedWriter bufferedWriter = new BufferedWriter(w);
		w.write(code);
		w.close();
		String decode = b.decode();
		FileWriter w1 = new FileWriter("decoded.txt");
		BufferedWriter bw1 = new BufferedWriter(w1);
		w1.write(decode);
		w1.close();
		System.out.println(
				"\n======================================================================================\n########Done###########");

	}
}