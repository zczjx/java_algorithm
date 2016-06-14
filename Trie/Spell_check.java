import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;


import java.util.NoSuchElementException;



public class Spell_check
{
	
	public Spell_check(){
	}
	
	public static void main(String args[]){
		SET<String> str_set = new SET<String>();
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
			
		
	}
}

