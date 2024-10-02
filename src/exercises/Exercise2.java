package exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Exercise2 {
	// SE PIDE: Proporcione una solución recursiva no final, una iterativa usando while, una recursiva final, y una en notación funcional.
	 
		// SOLUCIÓN RECURSIVA NO FINAL
		public static List<String> ejercicio2RecursivoNoFinal(int a, String s) {
			List<String> res = new ArrayList<>();
			if(a<=3 || s.length() <= 2) { // caso base
				res.add(Integer.toString(a) + s);
			} else {
				// Caso recursivo: hacer la llamada a f con a/2 y la subcadena de s
		        List<String> rec = ejercicio2RecursivoNoFinal(a / 2, s.substring(a % s.length()));
		        res.addAll(rec); // Agregar el resultado de la llamada recursiva a la lista actual
		        res.add(Integer.toString(a) + s); // Agregar el valor toString(a) + s
			}
			return res;
		}
		
		// SOLUCIÓN ITERATIVA
		public static List<String> ejercicio2Iterativo(int a, String s) {
		    List<String> res = new ArrayList<>();
		    // Mientras no se cumpla la condición base (a <= 3 o s.length <= 2)
		    while (a > 3 && s.length() > 2) { // podría poner también while(!(a <= 3 || s.length <= 2))
		        res.add(Integer.toString(a) + s); // Agregar toString(a) + s a la lista de resultados
		        // Actualizar a a su mitad y s a la subcadena según el módulo de a con s.length
		        a = a / 2;
		        s = s.substring(a % s.length());
		    }
		    
		    // Cuando se cumpla el caso base, agregar toString(a) + s a la lista
		    res.add(Integer.toString(a) + s);
		    return res;
		}
		
		// RECURSIVA FINAL
		public static List<String> ejercicio2RecursivoFinal(int a, String s) {
		    return recFinal(a, s, new ArrayList<>()); // Llamada inicial con una lista vacía como acumulador
		}

		// Método recursivo auxiliar (recursión final)
		private static List<String> recFinal(int a, String s, List<String> ac) {
		    if (a <= 3 || s.length() <= 2) { // caso base
		        ac.add(Integer.toString(a) + s);
		        return ac;
		    }
		    ac.add(Integer.toString(a) + s);
		    return recFinal(a / 2, s.substring(a % s.length()), ac);
		}
		
		// NOTACIÓN FUNCIONAL
		
		private static record Tupla(int a, String s) { // UNA TUPLA PARA LOS VALORES DE A Y S
		    public static Tupla of(int a, String s) {
		        return new Tupla(a, s);
		    }
		    
		    public Tupla next() { // Método para calcular el siguiente estado de la tupla
		        if (a > 3 && s.length() > 2) {
		            return Tupla.of(a / 2, s.substring(a % s.length())); // Actualizar 'a' dividiendo por 2 y 's' cogiendo la subcadena correspondiente
		        } else {
		            return null; // Condición de finalización: cuando 'a <= 3' o 's.length() <= 2' (no sea caso base)
		        }
		    }
		}

		
		public static List<String> ejercicio2NotacionFuncional(int a, String s) {
		    List<String> res = new ArrayList<>();
		    Tupla i = Tupla.of(a, s);
		    
		    // Usamos Stream.iterate para iterar sobre las tuplas hasta que se llegue al caso base
		    Stream.iterate(i, t -> t != null, Tupla::next).forEach(t -> {
		        // Añadir a la lista la concatenación de 'a' con la cadena 's'
		        res.add(Integer.toString(t.a()) + t.s());
		    });
		    return res;
		}
}
