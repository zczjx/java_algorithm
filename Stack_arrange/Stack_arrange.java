import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Stack_arrange{
	public static void main(String args[]){
		int nr = Integer.parseInt(args[0]);
		
		int [] arr = new int[nr];
		for(int i = 0; i < nr; i++)
			arr[i] = i;
		while(!StdIn.isEmpty()){
			String tmp = StdIn.readString();
			char [] pop_arr = tmp.toCharArray();
			Stack_by_list<Integer> foo = new Stack_by_list<Integer>();
			int push_cnt = 0;
			for(int idx_pop = 0; idx_pop < nr; idx_pop++){
				for(int i = push_cnt; i < nr; i++){
					if(arr[push_cnt] <= Character.getNumericValue(pop_arr[idx_pop]))
						foo.push(arr[push_cnt++]);
					else
						break;
				}
				if(foo.isEmpty()){
					StdOut.println(tmp + " is impossible");
					break;
				}
				else if(foo.pop() == Character.getNumericValue(pop_arr[idx_pop]))
					continue;
				else{
					StdOut.println(tmp + " is impossible");
					break;
				}
			}
		}
	}
}

