package tests;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import exercises.Exercise4;
import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestExercise4 {

    private static final String FUNC_REC_DOUBLE_FILE = "ficheros_generados/funcRecDouble.txt";
    private static final String FUNC_REC_BIG_FILE = "ficheros_generados/funcRecBig.txt";
    private static final String FUNC_IT_DOUBLE_FILE = "ficheros_generados/funcItDouble.txt";
    private static final String FUNC_IT_BIG_FILE = "ficheros_generados/funcItBig.txt";

    private static Integer nMin = 8; // n mínimo para el cálculo
    private static Integer nMax = 50; // n máximo para el cálculo
    private static Integer razon = 2; // incremento en los valores de n del cálculo
    private static Integer nIter = 10; // número de iteraciones para cada medición de tiempo
    private static Integer nIterWarmup = 1000; // número de iteraciones para warmup

    public static void genData(Consumer<Integer> algorithm, String file) {
        Function<Integer, Long> f1 = GenData.time(algorithm);
        GenData.tiemposEjecucionAritmetica(f1, file, nMin, nMax, razon, nIter, nIterWarmup);
    }

    public static void show(Fit pl, String file, String label) {
        List<WeightedObservedPoint> data = DataFile.points(file);
        pl.fit(data);
        MatPlotLib.show(file, pl.getFunction(), String.format("%s = %s", label, pl.getExpression()));
    }

    public static void showCombined() {
        MatPlotLib.showCombined("Tiempos",
                List.of(FUNC_REC_DOUBLE_FILE, FUNC_REC_BIG_FILE, FUNC_IT_DOUBLE_FILE, FUNC_IT_BIG_FILE),
                List.of("RecDouble", "RecBig", "ItDouble", "ItBig"));
    }

    public static void main(String[] args) {
        // Generamos los datos de ejecución para las 4 funciones
        genData(a -> Exercise4.funcRecDouble(a), FUNC_REC_DOUBLE_FILE);
        genData(a -> Exercise4.funcRecBig(a), FUNC_REC_BIG_FILE);
        genData(a -> Exercise4.funcItDouble(a), FUNC_IT_DOUBLE_FILE);
        genData(a -> Exercise4.funcItBig(a), FUNC_IT_BIG_FILE);

        // Mostramos los resultados individuales
        show(PowerLog.of(List.of(Pair.of(2, 0.), Pair.of(3, 0.))), FUNC_REC_DOUBLE_FILE, "RecDouble");
        show(PowerLog.of(List.of(Pair.of(2, 0.), Pair.of(3, 0.))), FUNC_REC_BIG_FILE, "RecBig");
        show(PowerLog.of(List.of(Pair.of(1, 0.), Pair.of(2, 1.), Pair.of(3, 0.))), FUNC_IT_DOUBLE_FILE, "ItDouble");
        show(PowerLog.of(List.of(Pair.of(1, 0.), Pair.of(2, 1.), Pair.of(3, 0.))), FUNC_IT_BIG_FILE, "ItBig");

        // Mostramos los gráficos combinados
        showCombined();
    }
}
