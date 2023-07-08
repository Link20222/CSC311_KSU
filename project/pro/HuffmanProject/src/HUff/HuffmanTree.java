package HUff;

class HuffmanTree {
	String codes[] = new String[256];
	String codeword = "";
	int bitCount = 0;
	int charCount = 0;
	int fcount = 0;

	// Method to decode the compressed message using the Huffman tree
	String decode() {
		Node temp = root;
		String decode = "";
		for (int i = 0; i < codeword.length(); i++) {
			if (codeword.charAt(i) == '0') {
				temp = temp.left;
			} else if (codeword.charAt(i) == '1') {
				temp = temp.right;
			}
			if (temp.left == null && temp.right == null) {
				decode += temp.ch;
				temp = root;
			}
		}
		return decode;
	}

	// Method to generate the Huffman code for each character in the input string
	String pCodes(String name) {
		for (int i = 0; i < name.length(); i++) {
			int index = (int) name.charAt(i);
			// codeword=codeword+codes[index]+"-";
			codeword += codes[index];
		}
		return codeword;
	}
	// Method to assign Huffman codes to each leaf node in the Huffman tree

	void TreeCodes(Node temp) {
		boolean flag = true;
		if (temp == null) {
			return;
		}
		if (temp.left != null) {
			codeword += "0";
			TreeCodes(temp.left);
		}
		if (temp.left == null && temp.right == null) {
			temp.code = codeword;
			temp.cc = codeword.length();
			bitCount += temp.count * temp.cc;
			charCount += 1;
			fcount += temp.count;
			int position = (int) temp.ch;
			codes[position] = codeword;
			codeword = codeword.substring(0, codeword.length() - 1);
			flag = false;
		}
		if (temp.right != null) {
			codeword = codeword + "1";
			TreeCodes(temp.right);
		}
		if (flag && codeword.length() > 0)
			codeword = codeword.substring(0, codeword.length() - 1);
	}
	// Method to generate Huffman codes for all characters in the input string and
	// print the binary representation of the Huffman tree

	void gCodes() {
		String codeword = "";
		TreeCodes(root);
		System.out.println();
		pCodes(root);
		float bits = 0.0f;
		for (float i = 1;; i++) {
			if (charCount <= Math.pow(2, i)) {
				bits = i;
				break;
			}
		}
		System.out.println("\n\nPrinting the Binary Tree:");
		printTree(root);
		bits = bits * fcount;
		float cr = ((bits - bitCount) / bits) * 100;
		cr = Math.round(cr * 100) / 100;
		System.out.println("\n\n\nThe Compression percentage is:" + cr + "%\n");
		printBinaryTree(root, 0);
	}

	// Method to print the character frequency and corresponding Huffman code for
	// each leaf node in the Huffman tree
	void pCodes(Node temp) {
		if (temp == null)
			return;
		if (temp.left != null) {
			pCodes(temp.left);
		}
		if (temp.left == null && temp.right == null) {
			System.out.println("Character: " + temp.ch + " Frequency: " + temp.count + "  ||  Code: " + temp.code);
		}
		if (temp.right != null) {
			pCodes(temp.right);
		}
	}

	Node root = null;

	// Method to form the Huffman tree by repeatedly merging the two lowest
	// frequency nodes
	void formTree() {
		boolean done = true;
		while (done) {
			Node l = pop();
			Node r = pop();
			if (l != null && r != null) {
				insertr(l, r);
			} else {
				pushI(l);
				done = false;
				root = l;
			}
		}
	}

	// Method to print the characters and frequencies in the Huffman tree
	void printTree(Node temp) {
		if (temp == null)
			return;
		if (temp.left != null)
			printTree(temp.left);
		if (temp.ch == '\0')
			System.out.print(temp.count);
		else {
			System.out.print(temp.ch);
		}
		if (temp.right != null)
			printTree(temp.right);
	}

	// this method creats a new node with the sum of the frequencies of its left and
	// right children and sets the left and right children as its children
	void insertr(Node l, Node r) {
		Node temp = new Node();
		temp.ch = '\0';
		temp.count = l.count + r.count;
		temp.left = l;
		temp.right = r;
		l.parent = temp;
		r.parent = temp;
		pushI(temp);
	}
	// Method to remove and return the lowest frequency node from the priority queue
	Node pop() {
		if (front == null) {
			return null;
		} else {
			PriorityQueue t = new PriorityQueue();
			t = front;
			front = front.next;
			return t.n;
		}
	}

	// This method creates a new node with the given character and frequency and adds it to the priority queue.
	void singleton(char element, int c) {
		Node n = new Node();
		n.ch = element;
		n.count = c;
		pushI(n);
	}

	PriorityQueue front = null;
	PriorityQueue rear = null;
	// This method adds a new node to the priority queue in the correct order based on its frequency
	void pushI(Node temp) {
		PriorityQueue temp_node = new PriorityQueue();
		temp_node.n = temp;
		if (front == null) {
			front = temp_node;
		} else {
			PriorityQueue tf = new PriorityQueue();
			tf = front;
			if (tf.n.count > temp_node.n.count) {
				temp_node.next = front;
				front = temp_node;
			} else if (tf.next == null) {
				tf.next = temp_node;
			} else {
				PriorityQueue tn = new PriorityQueue();
				tn = tf.next;
				boolean done = false;
				while (tn.next != null && !done) {
					if (tn.n.count > temp_node.n.count) {
						temp_node.next = tn;
						tf.next = temp_node;
						done = true;
					} else {
						tf = tf.next;
						tn = tn.next;
					}
				}
				if (tn.next == null && !done) {
					if (tn.n.count > temp_node.n.count) {
						temp_node.next = tn;
						tf.next = temp_node;
					} else {
						tn.next = temp_node;
					}
				}
			}
		}
	}
	// This method prints the binary representation of the Huffman tree using vertical bars to indicate the parent-child relationship between nodes.
	void printBinaryTree(Node root, int level) {
		if (root == null) {
			return;
		}
		printBinaryTree(root.right, level + 1);
		if (level != 0) {
			for (int i = 0; i < level - 1; i++)
				System.out.print("|\t");
			if (root.ch == '\n') {
				System.out.println("|-------" + "\\n" + "-" + root.count);
			} else if (root.ch != '\0') {
				System.out.println("|-------" + root.ch + "-" + root.count);
			} else {
				System.out.println("|-------" + root.count);
			}
		} else {
			if (root.ch == '\n') {
				System.out.println("|-------" + "\\n" + "-" + root.count);
			} else if (root.ch != '\0') {
				System.out.println(root.ch + "-" + root.ch);
			} else {
				System.out.println(root.count);
			}
		}
		printBinaryTree(root.left, level + 1);
	}
}