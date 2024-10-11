package exercises;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Exercise3 {
	public static Set<Integer> ejercicio3RecursivoSinMemoria(Integer a, Integer b, Integer c) {
		Set<Integer> res = new HashSet<>();
		if (a <= 5 || b <= 3 || c <= 2) { // caso base
			res.add(2 * a);
			res.add(b + 3);
			res.add(c);
		} else {
			res.addAll(ejercicio3RecursivoSinMemoria(a - 5, b / 4, c - 2));
			res.addAll(ejercicio3RecursivoSinMemoria(a / 3, b - 3, c / 2));
		}
		return res;
	}

	public static Set<Integer> ejercicio3RecursivoConMemoria(Integer a, Integer b, Integer c) {
		return recMem(a, b, c, new HashMap<>());
	}

	public static Set<Integer> recMem(Integer a, Integer b, Integer c, Map<String, Set<Integer>> ac) {
		Set<Integer> res = new HashSet<>();
		String key = a + "-" + b + "-" + c;

		// Si ya existe el resultado para estos valores, lo devolvemos directamente
		if (ac.containsKey(key)) {
			return ac.get(key);
		}
		if (a <= 5 || b <= 3 || c <= 2) {
			res.add(2 * a);
			res.add(b + 3);
			res.add(c);
		} else {
			// Caso recursivo
			Set<Integer> firstPart = recMem(a - 5, b / 4, c - 2, ac);
			Set<Integer> secondPart = recMem(a / 3, b - 3, c / 2, ac);
			res.addAll(firstPart);
			res.addAll(secondPart);
		}

		// Guardamos el resultado en el mapa antes de devolverlo
		ac.put(key, res);

		return res;

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
