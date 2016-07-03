import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;






public class B_Tree<Key extends Comparable<Key>, Val> 
{
	private static final int ORDER = 4;
	private Page root;
	private int height;
	private int nr_keys;

	private static class Entry{
		private Comparable key;
		private Object val;
		private Page next;

		Entry(Comparable k, Object v, Page n){
			this.key = k;
			this.val = v;
			this.next = n;
		}
	}
	private static  class Page {
		private int cnt;
		private final int M_order;
		private Entry[] child;
		
		private Page(int cnt, int order){
			this.cnt = cnt;
			this.M_order = order;
			this.child = new Entry[order];
		}	

	}
	
	public B_Tree(){
		this.root = new Page(0, this.ORDER);
	}
	
	public boolean isEmpty(){
		return size() == 0;
			
	}
	public int size(){
		return this.nr_keys;
	}
	public int height(){
		return this.height;
	}
	public Val get(Key k){
		if(k == null)
			return null;
		return search(this.root, k, this.height);

	}
	
	private Val search(Page st, Key k, int ht){
		Entry[] child = st.child;
		//StdOut.println("key is : " + k);
		if(ht == 0){
			// search in external page
			for(int j = 0; j < st.cnt; j++){
			//	StdOut.println("val is : " + child[j].val);
				if(eq(k, child[j].key))
					return (Val) child[j].val;
			}
		}
		else{
			for(int j = 0; j < st.cnt; j++){
				if(j+1 == st.cnt || less(k, child[j+1].key))
					return search(child[j].next, k ,ht-1);
			}

		}
		return null;
	}

	public void put(Key k, Val v){
		if (k == null) 
			throw new NullPointerException("key must not be null");
		Page u = insert(root, k, v, this.height);
		this.nr_keys++;
		if(u == null)
			return ;
		Page tmp = new Page(2, this.ORDER);
		tmp.child[0] = new Entry(root.child[0].key, null, root);
		tmp.child[1] = new Entry(u.child[0].key, null, u);
		this.root = tmp;
		this.height++;
	}
	
	private Page insert(Page st, Key k, Val v, int ht){
		int idx;
		Entry tmp = new Entry(k, v, null);
		if(ht == 0){
			for(idx = 0; idx < st.cnt; idx++)
				if(less(k, st.child[idx].key))
					break;
		}
		
		else{
			for(idx = 0; idx < st.cnt; idx++){
				if((idx+1 == st.cnt) || less(k, st.child[idx+1].key)){
					Page u = insert(st.child[idx++].next, k, v, ht-1);
					if(u == null)
						return null;
					tmp.key = u.child[0].key;
					tmp.next = u;
					break;
				}
			}	
		}
		
		for(int i = st.cnt; i > idx; i--)
			st.child[i] = st.child[i-1];
		st.cnt++;
		st.child[idx] = tmp;
		if(st.cnt < this.ORDER)
			return null;
		else 
			return split(st);	
	}
	
	

	public Page split(Page old){
		Page tmp = new Page(this.ORDER/2, this.ORDER);
		old.cnt = this.ORDER/2;
		for(int j = 0; j < this.ORDER/2; j++)
			tmp.child[j] = old.child[this.ORDER/2+j];
		return tmp;
	}

	  public String toString() {
        return toString(root, height, "") + "\n";
    }

    private String toString(Page h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        Entry[] children = h.child;

        if (ht == 0) {
            for (int j = 0; j < h.cnt; j++) {
                s.append(indent + children[j].key + " " + children[j].val + "\n");
            }
        }
        else {
            for (int j = 0; j < h.cnt; j++) {
                if (j > 0) 
					s.append(indent + "[" + children[j].key + "]\n");
                s.append(toString(children[j].next, ht-1, indent + "     "));
            }
        }
        return s.toString();
    }

	private boolean less(Comparable k1, Comparable k2){
		return k1.compareTo(k2) < 0;
	}

	private boolean eq(Comparable k1, Comparable k2){
		return  k1.compareTo(k2) == 0;
	}

	
	public static void main(String args[]){
        B_Tree<String, String> st = new B_Tree<String, String>();

        st.put("www.cs.princeton.edu", "128.112.136.12");
        st.put("www.cs.princeton.edu", "128.112.136.11");
        st.put("www.princeton.edu",    "128.112.128.15");
        st.put("www.yale.edu",         "130.132.143.21");
        st.put("www.simpsons.com",     "209.052.165.60");
        st.put("www.apple.com",        "17.112.152.32");
        st.put("www.amazon.com",       "207.171.182.16");
        st.put("www.ebay.com",         "66.135.192.87");
        st.put("www.cnn.com",          "64.236.16.20");
        st.put("www.google.com",       "216.239.41.99");
        st.put("www.nytimes.com",      "199.239.136.200");
        st.put("www.microsoft.com",    "207.126.99.140");
        st.put("www.dell.com",         "143.166.224.230");
        st.put("www.slashdot.org",     "66.35.250.151");
        st.put("www.espn.com",         "199.181.135.201");
        st.put("www.weather.com",      "63.111.66.11");
        st.put("www.yahoo.com",        "216.109.118.65");


   		StdOut.println("cs.princeton.edu:  " + st.get("www.cs.princeton.edu"));
      	StdOut.println("hardvardsucks.com: " + st.get("www.harvardsucks.com"));
      	StdOut.println("simpsons.com:      " + st.get("www.simpsons.com"));
      	StdOut.println("apple.com:         " + st.get("www.apple.com"));
     	StdOut.println("ebay.com:          " + st.get("www.ebay.com"));
   		StdOut.println("dell.com:          " + st.get("www.dell.com"));
    	StdOut.println();

       	StdOut.println("size:    " + st.size());
      	StdOut.println("height:  " + st.height());
        //StdOut.println(st);
        StdOut.println();
    }
}

