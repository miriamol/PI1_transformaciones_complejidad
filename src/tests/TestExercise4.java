package tests;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import exercises.Exercise4;
import us.lsi.common.Pair;
import us.lsi.common.String2;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestExercise4 {

	// REPETIR, NO SÉ CÓMO GENERAR LAS GRÁFICAS
	public static void main(String[] args) {
		System.out.println("Genero los ficheros de las gráficas para cada función");
		genDataDoubleRec();
		genDataDoubleIter();
		genDataBigIntegerRec();
		genDataBigIntegerIter();

		System.out.println("Muestro las gráficas para cada función");
		showDoubleRec();
		showDoubleIter();
		showBigIntegerRec();
		showBigIntegerIter();

		System.out.println("Muestro una gráfica que muestre todas las estimaciones de complejidad");
		showCombined();
		showCombinedTiempos();
	}

	private static Integer nMin = 10; // n mínimo para el cálculo de potencia
	private static Integer nMax = 1000; // n máximo para el cálculo de potencia
	private static Integer nIncr = 33; // incremento en los valores de n del cálculo de potencia
	private static Integer nIter = 50; // número de iteraciones para cada medición de tiempo
	private static Integer nIterWarmup = 10000; // número de iteraciones para warmup

//	private static Integer a = 4;

	// GEN DATA DEL RECURSIVO DOUBLE
	public static void genDataDoubleRec() {
		String file = "ficheros_generados/Exercise4DoubleRecursiva.txt";
		Function<Integer, Long> f1 = GenData.time(t -> Exercise4.funcRecDouble(t));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1, file, nMin, nMax, nIncr, nIter, nIterWarmup);
	}

	// GEN DATA DEL RECURSIVO BIGINTEGER
	public static void genDataBigIntegerRec() {
		String file = "ficheros_generados/Exercise4BigIntegerRecursiva.txt";
		Function<Integer, Long> f1 = GenData.time(t -> Exercise4.funcRecBig(t));
//			Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1, file, nMin, nMax, nIncr, nIter, nIterWarmup);
	}

	// GEN DATA DEL ITERATIVO DOUBLE
	public static void genDataDoubleIter() {
		String file = "ficheros_generados/Exercise4DoubleIterativo.txt";
		Function<Integer, Long> f1 = GenData.time(t -> Exercise4.funcItDouble(t));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1, file, nMin, nMax, nIncr, nIter, nIterWarmup);
	}

	// GEN DATA DEL ITERATIVO BIGINTEGER
	public static void genDataBigIntegerIter() {
		String file = "ficheros_generados/Exercise4BigIntegerIterativo.txt";
		Function<Integer, Long> f1 = GenData.time(t -> Exercise4.funcItBig(t));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1, file, nMin, nMax, nIncr, nIter, nIterWarmup);
	}

	public static void showDoubleRec() {
		String file = "ficheros_generados/Exercise4DoubleRecursiva.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 0.), Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}

	public static void showBigIntegerRec() {
		String file = "ficheros_generados/Exercise4BigIntegerRecursiva.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 0.), Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}

	public static void showDoubleIter() {
		String file = "ficheros_generados/Exercise4DoubleIterativo.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 0.), Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}

	public static void showBigIntegerIter() {
		String file = "ficheros_generados/Exercise4BigIntegerIterativo.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 0.), Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}

	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros_generados/Exercise4DoubleRecursiva.txt",
						"ficheros_generados/Exercise4DoubleIterativo.txt",
						"ficheros_generados/Exercise4BigIntegerRecursiva.txt",
						"ficheros_generados/Exercise4BigIntegerIterativo.txt"),
				List.of("Recursiva-Double", "Iterativa-Double", "Recursiva-BigInteger", "Iterativa-BigInteger"));
	}

	public static void showCombinedTiempos() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros_generados/Exercise4DoubleRecursiva.txt",
						"ficheros_generados/Exercise4DoubleIterativo.txt"),
				List.of("Recursiva-Double", "Iterativa-Double"));
	}

}
