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
		String key = a + "-" + b + "-" + c; // ponemos guiones para que no haya confusión con los números

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
		Map<Tupla, Set<Integer>> m = new HashMap<>();
		
		for(int i = 0; i <= a; i++) {
			for(int j = 0; j <= b; j++) {
				for(int k = 0; k <= c; k++) {
					Set<Integer> s = new HashSet<>();
					if(i<=5 || j <=3 || k <=2) {
						 s.add(2*i);
						 s.add(j + 3);
						 s.add(k);
				} else {
					s.addAll(m.get(Tupla.of(i-5,j/4,k-2)));
					s.addAll(m.get(Tupla.of(i/3,j-3,k/2)));
					}
					m.put(Tupla.of(i, j, k), s);
				}
			}
		}
		return m.get(Tupla.of(a, b, c));

	}
	
	public static record Tupla(Integer a, Integer b, Integer c) {
		public static Tupla of(Integer a, Integer b, Integer c) {
			return new Tupla(a, b, c);
		}
	}

}
