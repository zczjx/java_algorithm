import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;




public class Stack_by_list<T_item>
	implements Iterable<T_item>
{

	private Node<T_item> head;
	private int dy_sz;
	private int push_op_cnt;
	private int pop_op_cnt;
	public Stack_by_list(){
		head = null;
		dy_sz = 0;
		push_op_cnt = 0;
		pop_op_cnt  = 0;
	}

	public Stack_by_list(Stack_by_list s){
		push_op_cnt = 0;
		pop_op_cnt  = 0;
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
		this.push_op_cnt++;
		dy_sz++;
	}

	public T_item pop(){
		this.pop_op_cnt++;
		if(this.isEmpty())
			throw new NoSuchElementException("my Stack by list underflow");
		
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
		private int curr_push_op_cnt = push_op_cnt;
		private int curr_pop_op_cnt  = pop_op_cnt ;
		public boolean	hasNext(){
			if((curr_push_op_cnt != push_op_cnt) || (curr_pop_op_cnt  != pop_op_cnt))
				throw new ConcurrentModificationException();
			return (de != null);
		}

		public T_item	next(){
			if((curr_push_op_cnt != push_op_cnt) || (curr_pop_op_cnt  != pop_op_cnt))
				throw new ConcurrentModificationException();
			T_item it = de.it;
			de = de.next;
			return it;
		}

		public void remove(){
			throw new UnsupportedOperationException("have no support remove now!");

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
			else  //if(!foo.isEmpty())
				StdOut.print(foo.pop() + " ");
		}
		StdOut.println("( " + foo.size() + " left on stack)");
		try{
			for(String s : foo){
				foo.pop();
			}
		}catch(ConcurrentModificationException ex){
				StdOut.println("ex have been catch here!");
				ex.printStackTrace();
		}	

	}
}

