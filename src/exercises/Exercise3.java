package exercises;

import java.util.Set;


public class Exercise3 {
	public static Set<Integer> ejercicio3RecursivoSinMemoria(Integer a, Integer b, Integer c) {
		return null; 
	}
	
	public static Set<Integer> ejercicio3RecursivoConMemoria(Integer a, Integer b, Integer c) {
		return null;
	}
	
	public static Set<Integer> ejercicio3Iterativo(Integer a, Integer b, Integer c) {
		return null;
	}
	
	public static record Tupla(Integer a, Integer b, Integer c) {
		public static Tupla of(Integer a, Integer b, Integer c) {
			return new Tupla(a, b, c);
		}
	}

}
