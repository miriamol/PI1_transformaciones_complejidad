package exercises;

import java.math.BigInteger;

public class Exercise4 {
	
	public static Double funcRecDouble(Integer a) {
		Double res = 0.;
		if(a<10) {
			res = 5.0;
		} else {
			res = Math.sqrt(3 * a) * funcRecDouble(a - 2);
		}
		return res;
	}
	
	public static BigInteger funcRecBig(Integer a) {
		BigInteger res;
		if(a<10) {
			res = BigInteger.valueOf(5);
		} else {
			BigInteger sqrt = BigInteger.valueOf((long) Math.sqrt(3 * a));
	        res = sqrt.multiply(funcRecBig(a - 2));
		}
		return res;
	}
	
	public static Double funcItDouble(Integer a) {
		Double res = 5.0; // cuando a<10, el valor inicial es 5.0
		while(!(a<10)) {
			res *= Math.sqrt(3 * a);
			a -= 2;
		}
		return res;
	}
	
	public static BigInteger funcItBig(Integer a) {
		BigInteger res = BigInteger.valueOf(5L);
		while(!(a<10)) {
			// Calculamos la raíz cuadrada de (3*a) usando Double, luego lo convertimos a BigInteger
	        Double sqrtValue = Math.sqrt(3 * a.doubleValue());
	        BigInteger sqrtBigInt = BigInteger.valueOf(sqrtValue.longValue());

	        // Acumulamos el producto en res
	        res = res.multiply(sqrtBigInt);

	        // Reducimos a en 2
			a -= 2;
		}
		return res;
	}

}
