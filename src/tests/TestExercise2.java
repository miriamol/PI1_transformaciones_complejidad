package tests;

import java.util.List;

import exercises.Exercise2;
import us.lsi.common.Pair;
import us.lsi.streams.Stream2;

public class TestExercise2 {
	public static void main(String[] args) {
		String file = "resources/files/input/PI1Exercise2InputData.txt";
		List<Pair<Integer,String>> ls = Stream2.file(file)
				.map(s -> Pair.parse(s,",",s1->Integer.parseInt(s1),s2->s2))
				.toList();
		// Explicar diferencia entre Stream2.file(_) y Files2.streamFromFile(_)

		ls.forEach(p -> {
			System.out.println("1) Solucion R. NO Final: " + Exercise2.ejercicio2RecursivoNoFinal(p.first(), p.second()));
			System.out.println("2) Solucion R. Final:    " + Exercise2.ejercicio2RecursivoFinal(p.first(), p.second()));
			System.out.println("3) Solucion Iterativa:   " + Exercise2.ejercicio2Iterativo(p.first(), p.second()));
			System.out.println("4) Solucion Funcional:   " + Exercise2.ejercicio2NotacionFuncional(p.first(), p.second()));
			System.out.println("____________________________________________________");
		});
		System.out.println(".............................................................................................................................");
	}
	
}
