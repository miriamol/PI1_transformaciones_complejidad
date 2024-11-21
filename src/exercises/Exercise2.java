package exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Exercise2 {
	// SE PIDE: Proporcione una solución recursiva no final, una iterativa usando
	// while, una recursiva final, y una en notación funcional.

	// SOLUCIÓN RECURSIVA NO FINAL
	public static List<String> ejercicio2RecursivoNoFinal(Integer a, String s) {
		List<String> res = new ArrayList<>();
		if (a <= 3 || s.length() <= 2) { // caso base
			res.add(Integer.toString(a) + s);
		} else {
			// Caso recursivo: hacer la llamada a f con a/2 y la subcadena de s
			List<String> rec = ejercicio2RecursivoNoFinal(a / 2, s.substring(a % s.length()));
			res.addAll(rec); // Agregar el resultado de la llamada recursiva a la lista actual
			res.add(Integer.toString(a) + s); // Agregar el valor toString(a) + s
		}
		return res;
	}

	public static List<String> solucionRecursivaNoFinal(Integer a, String s) { // Definicion del enunciado
		List<String> res = new ArrayList<>();
		if (a <= 3 || s.length() <= 2) {
			res.add(s + Integer.toString(a));
		} else {
			List<String> rec = solucionRecursivaNoFinal(a/2, s.substring(a%s.length()));
			res.addAll(rec);
			res.add(Integer.toString(a)+s);
		}
		return res;
	}

	// SOLUCIÓN ITERATIVA
	public static List<String> ejercicio2Iterativo(Integer a, String s) {
		List<String> res = new ArrayList<>();
		// Mientras no se cumpla la condición base (a <= 3 o s.length <= 2)
		while (a > 3 && s.length() > 2) { // podría poner también while(!(a <= 3 || s.length <= 2))
			res.add(Integer.toString(a) + s); // Agregar toString(a) + s a la lista de resultados
			// Actualizar a a su mitad y s a la subcadena según el módulo de a con s.length

			s = s.substring(a % s.length());
			a = a / 2;
		}

		// Cuando se cumpla el caso base, agregar toString(a) + s a la lista
		res.add(Integer.toString(a) + s);

		// Invertir la lista antes de retornarla
		Collections.reverse(res);
		return res;
	}
	
	public static List<String> solucionIterativa(Integer a, String s) {
		List<String> res = new ArrayList<>();
		while (a > 3 && s.length() > 2) {
			res.add(s + Integer.toString(a));
			s = s.substring(a%s.length());
			a /= 2;
		}
		res.add(Integer.toString(a)+s);
		Collections.reverse(res);
		return res;
	}

	// RECURSIVA FINAL
	public static List<String> ejercicio2RecursivoFinal(Integer a, String s) {
		List<String> res = recFinal(a, s, new ArrayList<>()); // Llamada inicial con una lista vacía como acumulador
		Collections.reverse(res); // Invertir la lista antes de retornarla
		return res;
	}

	// Método recursivo auxiliar (recursión final)
	private static List<String> recFinal(Integer a, String s, List<String> ac) {
		if (a <= 3 || s.length() <= 2) { // caso base
			ac.add(Integer.toString(a) + s);
			return ac;
		}
		ac.add(Integer.toString(a) + s);
		return recFinal(a / 2, s.substring(a % s.length()), ac);
	}
	
	public static List<String> solucionRecursivaFinal(Integer a, String s) { 
		List<String> res = recFinal2(a, s, new ArrayList<>()); 
		Collections.reverse(res); 
		return res;
	}	
	
	private static List<String> recFinal2(Integer a, String s, List<String> ac) {
		if (a <= 3 || s.length() <= 2) {
			ac.add(s + Integer.toString(a));
			return ac;
		} 
		ac.add(Integer.toString(a)+s);
		return recFinal(a / 2, s.substring(a % s.length()), ac);
	}
	

	// NOTACIÓN FUNCIONAL

	private static record Tupla(Integer a, String s) { // UNA TUPLA PARA LOS VALORES DE A Y S
		public static Tupla of(Integer a, String s) {
			return new Tupla(a, s);
		}

		public Tupla next() { // Método para calcular el siguiente estado de la tupla
			if (a > 3 && s.length() > 2) {
				return Tupla.of(a / 2, s.substring(a % s.length())); // Actualizar 'a' dividiendo por 2 y 's' cogiendo
																		// la subcadena correspondiente
			} else {
				return null; // Condición de finalización: cuando 'a <= 3' o 's.length() <= 2' (no sea caso
								// base)
			}
		}
	}

	public static List<String> ejercicio2NotacionFuncional(Integer a, String s) {
		List<String> res = new ArrayList<>();
		Tupla i = Tupla.of(a, s);

		// Usamos Stream.iterate para iterar sobre las tuplas hasta que se llegue al
		// caso base
		Stream.iterate(i, t -> t != null, Tupla::next).forEach(t -> {
			// Añadir a la lista la concatenación de 'a' con la cadena 's'
			res.add(Integer.toString(t.a()) + t.s());
		});

		Collections.reverse(res); // Invertir la lista antes de retornarla
		return res;
	}
	
	public static List<String> recursivoFinal(List<String> ls, Integer a, Integer b) {
		return recFinal(ls, a, b, new ArrayList<>());
	}
	public static List<String> recFinal(List<String>ls,Integer a, Integer b, List<String> ac) {
		if(a==b && b<2) {
			return ac;
		} else if (b-a ==1) {
			ac.add(ls.get(a)+ls.get(b-a-1));
			return ac;
		} else {
			ac.add(ls.get(b-a));
			return recFinal(ls,a+1,b-1,ac);
		}
	}
}
