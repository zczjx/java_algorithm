import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Var_queue<T_item>
	implements Iterable<T_item>
{

	private T_item[] arr;
	private int head;
	private int tail;
	private int nr;
	public Var_queue(){
		arr = (T_item[])new Object[2];
		head = 0;
		tail = 0;
		nr 	 = 0;
	}

	public void enqueue(T_item it){
		if(nr ==  arr.length)
			resize(nr * 2);
		arr[tail++] = it;
		if(tail == arr.length)
			tail = 0;
		nr++ ;
	}

	public T_item dequeue(){
		T_item tmp = arr[head];
		arr[head] = null;
		nr--;
		head++;
		if(head == arr.length)
			head = 0;
		if((nr > 0) && (nr < (arr.length / 4)))
			resize(arr.length / 2);
		return tmp ;
	}

	public T_item peek(){
		if(this.isEmpty())
			return (T_item) null;
		return arr[head];
	}
	public int size(){
		return nr;
	}

	private void resize(int max){
		assert max >= nr;
		T_item[] tmp = (T_item[]) new Object[max];
		
		for (int i = 0; i < nr; i++)
			tmp[i] = arr[(head + i) % arr.length];
		arr = tmp;
		head = 0;
		tail = nr;

	}
	public boolean isEmpty(){
		return (nr == 0);
	}

	public boolean isFull(){
		return (nr == arr.length);
	}
	private class Queue_iterator implements Iterator<T_item>{
		private int i = 0;

		public boolean	hasNext(){
			return (i < nr);
		}

		public T_item	next(){
			if(hasNext()){
				T_item t =  arr[(head + i) % arr.length];
				i++;
				return t;

			}
			return null;
		}

		public void remove(){

		}

	}
	public Iterator<T_item>  iterator(){
		return new Queue_iterator();

	}
	public static void main(String args[]){
		Var_queue<String> foo;
		foo = new Var_queue<String>();
		while(!StdIn.isEmpty()){
			String tmp = StdIn.readString();
			if(!tmp.equals("-"))
				foo.enqueue(tmp);
			else if(!foo.isEmpty())
				StdOut.print(foo.dequeue() + " ");
		}
		StdOut.println("(" + foo.size() + " left on queue)");
		//for(String s : foo){
			//StdOut.println("left have :" + s);
		//} 		
	}
}

