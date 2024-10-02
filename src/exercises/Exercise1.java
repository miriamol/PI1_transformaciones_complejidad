package exercises;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import us.lsi.common.Map2;
import us.lsi.geometria.Cuadrante;
import us.lsi.geometria.Punto2D;

public class Exercise1 {
	// ENUNCIADO:
	// Analice el código que se muestra a continuación:
	public static Map<Cuadrante, String> ejercicio1NotacionFuncional(List<Punto2D> ls) {
		UnaryOperator<Punto2D> nx = punto -> {
			double nuevaX = punto.x() + 3; // se modifica la coordenada X de un punto sumándole 3
			return Punto2D.of(nuevaX, punto.y()); // devuelve un nuevo objeto Punto2D con la nueva coordenada x y la misma coordenada y
		};
		return ls.stream()
				.filter(p -> p.x() % 5 != 0) // si el punto cuya coordenada X es divisible entre 5, se excluye
				.map(nx) // se aplica la función definida anteriormente
				.collect(Collectors.groupingBy(Punto2D::cuadrante, // agrupamos por cuadrantes
						Collectors.reducing("",
						p -> p.x() % 2 == 0 ? p.x() + "¡" : p.x() + "!", // Para cada punto p, si su coordenada x es par (p.x() % 2 == 0),
																		 // se convierte en un texto que incluye la x seguida de un carácter "¡".
																		 // Si x es impar, la x se convierte en un texto que termina con el carácter "!".
								(a, b) -> a.isEmpty() ? b : a + "-" + b))); // Si a está vacío, el resultado es simplemente b.
																			// Si a ya tiene un valor, se concatenan a y b con un guion ("-") entre ellos.
	}
	
	// SE PIDE: Proporcione una solución iterativa y otra recursiva final equivalentes.
	
	// SOLUCIÓN ITERATIVA
	public static Map<Cuadrante, String> ejercicio1Iterativo(List<Punto2D> ls) {
	    Map<Cuadrante, String> ac = new HashMap<>();
	    Integer i = 0;	    
	    // iterar sobre la lista de puntos
	    while (i < ls.size()) {
	        Punto2D punto = ls.get(i); // Obtener el punto actual
	        if (punto.x() % 5 != 0) {
	            double nuevaX = punto.x() + 3; // a la coordenada X se le suma 3, así obtenemos nuevaX
	            Punto2D puntoTransformado = Punto2D.of(nuevaX, punto.y());
	            Cuadrante cuadrante = puntoTransformado.cuadrante(); // Obtener el cuadrante del punto transformado
	            
	            String textoX = nuevaX % 2 == 0 ? nuevaX + "¡" : nuevaX + "!"; // // Generar el texto de la coordenada x con "¡" si es par, "!" si es impar
	            
	            // Si el cuadrante ya tiene una cadena asociada, la concatenamos con "-"... 
	            // esto lo hacemos para que los valores del diccionario queden separados... --> Cuadrante2: "2¡-10¡"
	            if (ac.containsKey(cuadrante)) {
	                ac.put(cuadrante, ac.get(cuadrante) + "-" + textoX);
	            } else {
	                // Si no, agregamos la nueva cadena
	                ac.put(cuadrante, textoX);
	            }
	        }
	        
	        // Incrementar el índice
	        i++;
	    }
	    
	    return ac;
	}
	
	// SOLUCIÓN RECURSIVA FINAL
	
	public static Map<Cuadrante, String> ejercicio1Recursivo(List<Punto2D> ls) {
	    return recFinal(0, new HashMap<>(), ls);
	}

	// Método recursivo final
	private static Map<Cuadrante, String> recFinal(int i, Map<Cuadrante, String> ac, List<Punto2D> ls) {
	    // Caso base: si hemos procesado todos los elementos, devolver el mapa acumulado
	    if (i >= ls.size()) {
	        return ac;
	    }

	    Punto2D punto = ls.get(i);
	    if (punto.x() % 5 != 0) {
	        double nuevaX = punto.x() + 3; // obtener la nuevaX sumándole 3 a la coordenada X que tenemos
	        Punto2D puntoTransformado = Punto2D.of(nuevaX, punto.y());
	        Cuadrante cuadrante = puntoTransformado.cuadrante(); // Obtener el cuadrante del punto transformado
	        
	        // Generar el texto de la coordenada x con "¡" si es par, "!" si es impar
	        String textoX = nuevaX % 2 == 0 ? nuevaX + "¡" : nuevaX + "!";
	        
	        // Si el cuadrante ya tiene una cadena asociada, la concatenamos con "-"
	        // esto lo hacemos para que los valores del diccionario queden separados... --> Cuadrante2: "2¡-10¡"
	        if (ac.containsKey(cuadrante)) {
	            ac.put(cuadrante, ac.get(cuadrante) + "-" + textoX);
	        } else {
	            ac.put(cuadrante, textoX);
	        }
	    }
	    
	    // Llamada recursiva para procesar el siguiente elemento de la lista
	    return recFinal(i + 1, ac, ls);
	}
	
}
