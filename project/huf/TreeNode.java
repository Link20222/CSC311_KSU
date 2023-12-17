
public class TreeNode<T> {
	public T data;
	public TreeNode<T> first, second, third, fourth, parent;

	/** Creates a new instance of TreeNode */
	public TreeNode(T val) {
		data = val;
		first = second = third = fourth = parent = null;

	}

	public TreeNode(T val, TreeNode<T> f, TreeNode<T> s, TreeNode<T> t, TreeNode<T> o, TreeNode<T> p) {

		data = val;
		first = f;
		second = s;
		third = t;
		fourth = o;
		parent = p;

	}

}
