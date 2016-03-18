import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;




public class Underflow{
	public static void main(String args[]){
		int push_nr = 0;  
		int pop_nr = 0;
		boolean flow_flg = false;
		while(!StdIn.isEmpty()){
			String tmp = StdIn.readString();
			if(!tmp.equals("-"))
				push_nr++;
			else 
				pop_nr++;
			if(pop_nr > push_nr){
				flow_flg = true;
				StdOut.println("The serial have underflow!!!");
				break;
			}
		}
		if(flow_flg == false)
			StdOut.println("The serial is right");
	} 
}


