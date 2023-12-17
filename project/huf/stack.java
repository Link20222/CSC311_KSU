
public class stack<T> {
	private Node<T> top;

	public stack() {

		top = null;

	}

	public boolean empty() {
		return top == null;

	}

	public boolean full() {

		return false;

	}

	public void push(T e) {

		Node<T> tmp = new Node<T>(e);
		tmp.next = top;
		top = tmp;

	}

	public T pop() {

		T e = top.data;
		top = top.next;
		return e;

	}
}
