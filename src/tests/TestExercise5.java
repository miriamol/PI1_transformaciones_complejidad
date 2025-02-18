package tests;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


import org.apache.commons.math3.fitting.WeightedObservedPoint;

import exercises.Exercise5;
import us.lsi.common.Pair;

import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestExercise5 {
	private static Integer nMin = 100; // n mínimo para el cálculo de potencia
	private static Integer nMax = 100000; // n máximo para el cálculo de potencia
	private static Integer razon = 3330; // incremento en los valores de n del cálculo de potencia
	private static Integer nIter = 50; // número de iteraciones para cada medición de tiempo
	private static Integer nIterWarmup = 10000; // número de iteraciones para warmup

	private static Integer a = 3;
	
	public static void genData (Consumer<Integer> algorithm, String file) {
		Function<Integer,Long> f1 = GenData.time(algorithm);
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,razon,nIter,nIterWarmup);

	}
	
	public static void show(Fit pl, String file, String label) {
		List<WeightedObservedPoint> data = DataFile.points(file);
		pl.fit(data);
		MatPlotLib.show(file, pl.getFunction(), String.format("%s = %s",label,pl.getExpression()));
	}

	
	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros_generados/ejercicio5RecurDouble.txt","ficheros_generados/ejercicio5RecurBigInteger.txt","ficheros_generados/ejercicio5IterDouble.txt",
						"ficheros_generados/ejercicio5IterBigInteger.txt"), 
				List.of("Recursivo_Double","Recursivo_BigInteger","Iterativo_Double","Iterativo_BigInteger"));
	}
	
	

	public static void main(String[] args) {
		genData(t -> Exercise5.ejercicio5RecursivoDouble(a),"ficheros_generados/ejercicio5RecurDouble.txt");
		genData(t -> Exercise5.ejercicio5RecursivoBigInteger(a),"ficheros_generados/ejercicio5RecurBigInteger.txt");
		genData(t -> Exercise5.ejercicio5IterativoDouble(a),"ficheros_generados/ejercicio5IterDouble.txt");
		genData(t -> Exercise5.ejercicio5IterativoBigInteger(a),"ficheros_generados/ejercicio5IterBigInteger.txt");

		
		
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/ejercicio5RecurDouble.txt","Recursivo_Double");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))),"ficheros_generados/ejercicio5RecurBigInteger.txt","Recursivo_BigInteger");
		show(PowerLog.of(List.of(Pair.of(1, 0.),Pair.of(2, 1.),Pair.of(3, 0.))),"ficheros_generados/ejercicio5IterDouble.txt","Iterativo_Double");
		show(PowerLog.of(List.of(Pair.of(1, 0.),Pair.of(2, 1.),Pair.of(3, 0.))),"ficheros_generados/ejercicio5IterBigInteger.txt","Iterativo_BigInteger");

		
		showCombined();
	}
				

}