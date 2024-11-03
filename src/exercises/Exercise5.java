package exercises;

import java.math.BigInteger;

public class Exercise5 {


	public static Double ejercicio5RecursivoDouble(Integer n) {
		Double res = 0.0;
		if (n<=6) {
			res = 1.0;
		} else {
			res =  1 + ejercicio5RecursivoDouble(n-1) * Math.log(n-1);
		}
		return res;
	}
	
	public static BigInteger ejercicio5RecursivoBigInteger(Integer n) {
		BigInteger res = BigInteger.valueOf(0);
		if(n<=6) {
			res = BigInteger.valueOf(1);
		} else {
			BigInteger log = BigInteger.valueOf((long) Math.log(n-1));
			res = ejercicio5RecursivoBigInteger(n-1);
			res = res.multiply(log);
			res = res.add(BigInteger.valueOf(1));
		}
		return res;
	}
	
	public static Double ejercicio5IterativoDouble(Integer n) {
		Double res = 0.0;
		while(!(n<=6)) {
			res = 1 + res * Math.log(n);
			n-=1;
		}
		res = 1.0;
		return res;
	}
	
	public static BigInteger ejercicio5IterativoBigInteger(Integer n) {
		BigInteger res = BigInteger.valueOf(0);
		while(!(n<=6)) {
			BigInteger log = BigInteger.valueOf((long) Math.log(n-1));
			res = res.multiply(log);
			res = res.add(BigInteger.valueOf(1));
			res = res.add(res);
			n-=1;
		}
		res = BigInteger.valueOf(1);
		return res;
	}

	public static int log2(int n){
	    if(n <= 0) throw new IllegalArgumentException();
	    return 31 - Integer.numberOfLeadingZeros(n);
	}
}