import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;



public class String_SET
{
	private int val;
	Three_ST<Integer> st;
	
	public String_SET(){
		this.st = new Three_ST<Integer>();
		this.val = 1;
	}
	
	public void add(String key){
		st.put(key, val);
		val++;
	}

	

	public void delete(String key){
		if(key == null)
			throw new NullPointerException("argument to contains() is null");
		if(!contains(key))
			return ;
		st.delete(key);
	}

	
	public boolean contains(String key){
		if(key == null)
			throw new NullPointerException("argument to contains() is null");
		return st.contains(key);

	}
	public boolean isEmpty(){
		return st.isEmpty();

	}
	public int size(){
		return st.size();

	}

	public String toString(){
        StringBuilder s = new StringBuilder();
		StdOut.println("run here!");
		for(String tmp : st.keys())
        	s.append(tmp + "\n");
       
        return s.toString();
    }
	
	
	public static void main(String args[]){
		String_SET str_set = new String_SET();
		In dict = new In(args[0]);
		while(!dict.isEmpty()){
			String wd = dict.readString();
			str_set.add(wd);
		}
		StdOut.println("load dictionary done!");
		In check = new In();
		while(!check.isEmpty()){
			String wd = check.readString();
			if(!str_set.contains(wd))
				StdOut.println(wd + " is not found!");
			else
				StdOut.println(wd + " has found!");
		}
		
		StdOut.println(str_set);
			
		
	}
}

