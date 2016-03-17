import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;



public class Stack_by_list<T_item>
	implements Iterable<T_item>
{

	private Node<T_item> head;
	private int dy_sz;
	public Stack_by_list(){
		head = null;
		dy_sz = 0;
	}

	public Stack_by_list(Stack_by_list s){
		if(s.head != null){
			this.head = new Node(s.head) ;
			this.dy_sz = s.dy_sz;
		}
		for(Node<T_item> x = head; x.next != null; x = x.next)
			x.next = new Node(x.next) ;
	
	}
	public void push(T_item it){
		Node<T_item> old = head;
		head = new Node<T_item>();
		head.it = it;
		head.next = old;
		dy_sz++;
	}

	public T_item pop(){
		if(this.isEmpty())
			return (T_item) null;
		
		Node<T_item> old = head;
		head = head.next;
		dy_sz--;
		return old.it;
	}

	public T_item peek(){
		if(this.isEmpty())
			return (T_item) null;
		return head.it;
	}
	public int size(){
		return dy_sz;
	}

	public boolean isEmpty(){
		return (dy_sz == 0);
	}

	public boolean isFull(){
		return false;
	}
	private class Node<N_item> {
		N_item it;
		Node<N_item> next;

		public Node(){
			this.it = null;
			this.next = null;
		}
		public Node(Node<N_item> n){
			this.it = n.it;
			this.next = n.next;
		}
	}
	
	private class Stack_iterator implements Iterator<T_item>{
		private Node<T_item> de = head;

		public boolean	hasNext(){
			return (de.next != null);
		}

		public T_item	next(){
			de = de.next;
			return de.it;
		}

		public void remove(){

		}

	}
	public Iterator<T_item>  iterator(){
		return new Stack_iterator();

	}
	public static void main(String args[]){
		Stack_by_list<String> foo;
		Stack_by_list<String> bar;
		foo = new Stack_by_list<String>();
		while(!StdIn.isEmpty()){
			String tmp = StdIn.readString();
			if(!tmp.equals("-"))
				foo.push(tmp);
			else if(!foo.isEmpty())
				StdOut.print(foo.pop() + " ");
		}
		StdOut.println("(" + foo.size() + " left on stack)");
		for(String s : foo){
			StdOut.println("foo left have :" + s);
		} 
		bar = new Stack_by_list<String>(foo);
		
		for(String s : bar){
			StdOut.println("bar left have :" + s);
		} 
		StdOut.println(foo.pop() + " ");
		for(String s : foo){
			StdOut.println("after pop foo left have :" + s);
		} 

		for(String s : bar){
			StdOut.println("after pop  bar left have :" + s);
		} 
	}
}

