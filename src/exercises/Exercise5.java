package exercises;

import java.math.BigInteger;

public class Exercise5 {


	public static Double ejercicio5Double(Integer n) {
		return null;
	}
	
	public static BigInteger ejercicio5BigInteger(Integer n) {
		return null;
	}

	public static int log2(int n){
	    if(n <= 0) throw new IllegalArgumentException();
	    return 31 - Integer.numberOfLeadingZeros(n);
	}
}