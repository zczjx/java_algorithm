import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class S_Matrix{

	private S_Vector[] rows;
	private int dm;

	public S_Matrix(int d){
		this.dm = d;
		this.rows = new S_Vector[d];
		for(int i = 0; i < this.dm; i++)
			this.rows[i] = new S_Vector(d);

	}

	public void put(int i, int j, Double v){
		if((i < 0) || (i >= this.dm))
			throw new IndexOutOfBoundsException("Illegal index");
		if((j < 0) || (j >= this.dm))
			throw new IndexOutOfBoundsException("Illegal index");
		this.rows[i].put(j, v);

	}

	public Double get(int i, int j){
		if((i < 0) || (i >= this.dm))
			throw new IndexOutOfBoundsException("Illegal index");
		if((j < 0) || (j >= this.dm))
			throw new IndexOutOfBoundsException("Illegal index");

		return this.rows[i].get(j);

	}

	public int nonzero_nr(){
		int sum = 0;
		for(int i = 0; i < this.dm; i++)
			sum += rows[i].nonzero_nr();
		return sum;


	}

	public int size(){
		return this.dm * this.dm;


	}

	public int dimentsion(){
			return this.dm;


	}

	public S_Matrix plus(S_Matrix that){
		if(this.dm != that.dm)
			throw new IllegalArgumentException("Vector lengths disagree");
		S_Matrix tmp = new S_Matrix(this.dm);
		for(int i = 0; i < this.dm; i++)
			tmp.rows[i] = this.rows[i].plus(that.rows[i]);
		return tmp;

	}

	public S_Vector multiply_vector(S_Vector a){
		if(this.dimentsion() != a.dimentsion())
			throw new IllegalArgumentException("Vector lengths disagree");
			
		S_Vector tmp = new S_Vector(this.dm);
		for(int i = 0; i < this.dm; i++)
			tmp.put(i, this.rows[i].dot(a));
		return tmp;
	}
	public String toString(){
		String s = "N = " + this.dimentsion()+ ", nonzeros = " + this.nonzero_nr() + "\n";
        for (int i = 0; i < this.dimentsion(); i++) {
            s += i + ": " + this.rows[i] + "\n";
        }
        return s;

	}

	public static void main(String args[]){
		S_Matrix A = new S_Matrix(5);
        S_Vector x = new S_Vector(5);
        A.put(0, 0, 1.0);
        A.put(1, 1, 1.0);
        A.put(2, 2, 1.0);
        A.put(3, 3, 1.0);
        A.put(4, 4, 1.0);
        A.put(2, 4, 0.3);
        x.put(0, 0.75);
        x.put(2, 0.11);
        StdOut.println("x     : " + x);
        StdOut.println("A     : " + A);
        StdOut.println("Ax    : " + A.multiply_vector(x));
        StdOut.println("A + A : " + A.plus(A));

	}
}

