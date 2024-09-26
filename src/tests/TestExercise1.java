package tests;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import exercises.Exercise1;
import us.lsi.geometria.Punto2D;
import us.lsi.streams.Stream2;

public class TestExercise1 {
	
	public static void main(String[] args) {
		Function<String, Punto2D> parsePunto = s -> {
			String[] v = s.split(",");
			return Punto2D.of(Double.valueOf(v[0]), Double.valueOf(v[1]));
		};
		String file = "resources/files/input/PI1Exercise1InputDataXXX.txt";
		List<String> lsInput = Arrays.asList("Small", "Medium", "Large");
		
		lsInput.forEach(s->{
			List<Punto2D> ls = Stream2.file(file.replace("XXX",s)).map(parsePunto).toList();
			System.out.println("Fichero "+file.replace("XXX",s));
			
			System.out.println("1) Solucion Funcional:\n" + Exercise1.ejercicio1NotacionFuncional(ls));
			System.out.println("2) Solucion Iterativa:\n" + Exercise1.ejercicio1Iterativo(ls));
			System.out.println("3) Solucion Rec. Final:\n" + Exercise1.ejercicio1Recursivo(ls));
			System.out.println(".............................................................................................................................");
		
		});

	}
	
	
}
