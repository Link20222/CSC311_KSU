
public class queue<T> {
	private Node<T> head, tail;
	private int size;

	/** Creates a new instance of LinkedQueue */
	public queue() {
	head = tail = null;
	size = 0;

	}
	
	public boolean full() {
		return false;

		}

		public int length (){
		return size;

		}
}
