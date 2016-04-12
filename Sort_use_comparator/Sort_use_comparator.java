import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Arrays;
import java.util.Comparator;


public class Sort_use_comparator{
	private static final Comparator<String> CAN_ORDER = new Can_comparator();

	private static class Can_comparator implements Comparator<String>{
		private final String order = "RWQOJMVAHBSGZXNTCIEKUPDYFL";
		public int	compare(String o1, String o2){
			if(o1 == o2)
				return 0;
			int n = Math.min(o1.length(), o2.length());
			for(int i = 0; i < n; i++){
				int o1_idx = order.indexOf(o1.charAt(i));
				int o2_idx = order.indexOf(o2.charAt(i));
				if(o1_idx < o2_idx)
					return -1;
				else if(o1_idx > o2_idx)
					return 1;
			}
			return o1.length()- o2.length();


		}


	}
	
	public static void main(String args[]){
		String [] candidates = StdIn.readAll().toUpperCase().split("\\n+");
		int nr = candidates.length;
		Arrays.sort(candidates, Sort_use_comparator.CAN_ORDER);
		for(int i = 0; i < nr; i++)
			StdOut.println(candidates[i]);

	}
		
}


