import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Len_fixed_stack<T_item>
	implements Iterable<T_item>
{

	private T_item[] arr;
	private int dy_sz;
	private int cap;
	public Len_fixed_stack(int len){
		dy_sz = 0;
		cap = len;
		arr = (T_item[])new Object[len];
	}

	public void push(T_item it){
		arr[dy_sz++] = it;
	}

	public T_item pop(){
		return arr[--dy_sz];
	}

	public T_item peek(){
		if(this.isEmpty())
			return (T_item) null;
		return arr[dy_sz];
	}
	public int size(){
		return dy_sz;
	}

	public boolean isEmpty(){
		return (dy_sz == 0);
	}

	public boolean isFull(){
		return (dy_sz == cap);
	}
	private class Stack_iterator implements Iterator<T_item>{
		private int i = dy_sz;

		public boolean	hasNext(){
			return (i > 0);
		}

		public T_item	next(){
			return arr[--i];
		}

		public void remove(){

		}

	}
	public Iterator<T_item>  iterator(){
		return new Stack_iterator();

	}
	public static void main(String args[]){
		Len_fixed_stack<String> foo;
		foo = new Len_fixed_stack<String>(100);
		while(!StdIn.isEmpty()){
			String tmp = StdIn.readString();
			if(!tmp.equals("-"))
				foo.push(tmp);
			else if(!foo.isEmpty())
				StdOut.print(foo.pop() + " ");
		}
		StdOut.println("(" + foo.size() + " left on stack)");
		for(String s : foo){
			StdOut.println("left have :" + s);
		} 		
	}
}

