package ejemplos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.common.Map2;
import us.lsi.geometria.Cuadrante;
import us.lsi.geometria.Punto2D;

public class Ejemplo1 {

	// ENUNCIADO:
	public static Map<Cuadrante, Double> solucionFuncional(List<Punto2D> ls) {
		return ls.stream().collect(
				Collectors.groupingBy(Punto2D::cuadrante, Collectors.reducing(0., p -> p.x(), (a, b) -> a + b)));
	}
	
	// Analice el código que se muestra y proporcione una solución iterativa y otra recursiva 
	// final equivalentes.
	
	// SOLUCIÓN ITERATIVA
	
	public static Map<Cuadrante, Double> solucionIterativa(List<Punto2D> ls) {
		// Map2.empty();
		Map<Cuadrante, Double> ac = new HashMap<>();
		Integer i = 0;
		while(i < ls.size()) {
			Punto2D p = ls.get(i);  // obtenemos el punto actual
			Cuadrante key = p.cuadrante();
			if (ac.containsKey(key)) {
				ac.put(key, ac.get(key) + p.x()); // Si el cuadrante ya está en el mapa,
									// se actualiza su valor sumando la coordenada x del punto p a la suma existente.
			} else {
				ac.put(key, p.x());
			}	
			i++; // en cada ITERACIÓN, el índice se incrementa para procesar el siguiente punto de la lista
		}
		return ac;
	}

	// RECURSIVA FINAL
	
	public static Map<Cuadrante, Double> solucionRecursivaFinal(List<Punto2D> ls) {
		return recFinal(0, new HashMap<>(), ls);
	}
	
	private static Map<Cuadrante, Double> recFinal(Integer e, Map<Cuadrante, Double> ac, List<Punto2D> ls) {
		Map<Cuadrante, Double> r = ac;
		if (e < ls.size()) {
			Punto2D p = ls.get(e);
			Cuadrante key = p.cuadrante();
			if (ac.containsKey(key)) {
				ac.put(key, ac.get(key) + p.x());
			} else {
				ac.put(key, p.x());
			}
			r = recFinal(e+1, ac, ls);
		}
		return r;
	}
}
