/*******************************************************************************
* Copyright (C), 2000-2016,  Electronic Technology Co., Ltd.
*                
* @filename: Parentheses.java 
*                
* @author: Clarence.Chow <zhou_chenz@163.com> 
*                
* @version:
*                
* @date: 2016-3-13    
*                
* @brief:          
*                  
*                  
* @details:        
*                 
*    
*    
* @comment           
*******************************************************************************/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;


class Len_fixed_stack<T_item> {

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

	public int size(){
		return dy_sz;
	}

	public boolean isEmpty(){
		return (dy_sz == 0);
	}

	public boolean isFull(){
		return (dy_sz == cap);
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
	} 
}


public class Parentheses{

	private static final char LEFT_PAREN = '(';
	
	private static final char LEFT_BRACE = '{';

	private static final char LEFT_BRACKET = '[';

	private static final char RIGHT_PAREN = ')';
	
	private static final char RIGHT_BRACE = '}';
	
	private static final char RIGHT_BRACKET = ']';

	public static boolean isBalanced(String str){
		Len_fixed_stack<Character> foo = new Len_fixed_stack<Character>(256); 
		for(int i = 0; i < str.length(); i++){
			switch(str.charAt(i)){
				case '(':
				case '[':
				case '{':
						if(foo.isFull())
							System.out.println("Parenthese match succuss!");
						foo.push(str.charAt(i));
						break;

				case ')':
				case ']':
				case '}':
						char tmp;
						if(foo.isEmpty())
							return false;
						tmp = foo.pop();
						switch(str.charAt(i)){
							case ')':
									if(tmp == '(')
										break;
									else
										return false;
							case ']':
									if(tmp == '[')
										break;
									else
										return false;
								
							case '}':
									if(tmp == '{')
										break;
									else
										return false;

							default:
									return false;
						}
						break;
					
				case ' ':
						break; // skip space
				default:
						return false;		
			}
		}
	return true;
		

	}
	public static void main(String args[]){
		In inp = new In();
		String str = inp.readAll().trim();
		boolean ret;
		System.out.println("str is " + str);
		ret = isBalanced(str);
		if(ret == true)
			System.out.println("Parenthese match succuss!");
		else
			System.out.println("Parenthese match fail!");
	}


}


