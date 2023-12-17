
public class Tree<T> {
	TreeNode<T> root, current;

	
	public Tree() {

		root = current = null;

	}

	public boolean empty() {
		return root == null;

	}

	public T retrieve() {

		return current.data;

	}

	public void update(T val) {
		current.data = val;

	}

	public boolean insert(Relative rel, T val) {

		switch (rel) {
		case Root:

			if (!empty())
				return false;
			current = root = new TreeNode<T>(val);
			return true;

		case Parent: // This is an impossible case.

			return false;
		case firstChild:

			if (current.first != null)
				return false;
			current.first = new TreeNode<T>(val);
			current = current.first;
			return true;
		case secondChild:

			if (current.second != null)
				return false;
			current.second = new TreeNode<T>(val);
			current = current.second;
			return true;

		case thirdChild:

			if (current.third != null)
				return false;
			current.third = new TreeNode<T>(val);
			current = current.third;

			return true;

		case fourthChild:

			if (current.fourth != null)
				return false;
			current.fourth = new TreeNode<T>(val);
			current = current.fourth;
			return true;

		default:
			return false;
		}
	}

	public void deleteSubtree() {
		if (current == root) {

			current = root = null;

		} else {

			TreeNode<T> p = current;
			find(Relative.Parent);// current = findparent(current, root);
			if (current.first == p)

				current.first = null;

			else

				current.second = null;

			current = root;

		}

	}

	public boolean find(Relative rel) {

		switch (rel) {
		case Root: // Easy case

			current = root;
			return true;
		case Parent:

			if (current == root)
				return false;
			current = current.parent;
			return true;
		case firstChild:

			if (current.first == null)
				return false;
			current = current.first;
			return true;
		case secondChild:

			if (current.second == null)
				return false;
			current = current.second;
			return true;

		case thirdChild:

			if (current.third == null)
				return false;
			current = current.third;
			return true;

		case fourthChild:

			if (current.fourth == null)
				return false;
			current = current.fourth;
			return true;

		default:

			return false;

		}

	}

}
