package tests;

import java.util.List;
import java.util.function.Function;

import exercises.Exercise3;
import exercises.Exercise3.Tupla;
import us.lsi.streams.Stream2;

public class TestExercise3 {
	
	public static void main(String[] args) {
		String file = "resources/files/input/PI1Exercise3InputData.txt";
		Function<String, Tupla> parseTupla = s -> {
			String[] v = s.split(",");
			return Tupla.of(Integer.valueOf(v[0]), Integer.valueOf(v[1]), Integer.valueOf(v[2]));
		};
		
		List<Tupla> ls = Stream2.file(file)
				.map(parseTupla)
				.toList();
		
		ls.forEach(p -> {
			System.out.println("1) Solucion R. Sin Mem.: " + Exercise3.ejercicio3RecursivoSinMemoria(p.a(), p.b(),p.c()));
			System.out.println("2) Solucion R. Con Mem.: " + Exercise3.ejercicio3RecursivoConMemoria(p.a(), p.b(),p.c()));
			System.out.println("3) Solucion Iterativa:   " + Exercise3.ejercicio3Iterativo(p.a(), p.b(),p.c()));
			System.out.println("________________________________");
		});
		System.out.println(".............................................................................................................................");
	}

}
