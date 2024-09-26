package tests.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import us.lsi.geometria.Cuadrante;
import us.lsi.geometria.Punto2D;

public class TestExercise1JUnit {
	private static List<List<Punto2D>> 			input;
	private static List<Map<Cuadrante,String>>	expectedOutput;
	private	static String 						inputFileName 	= "/files/input/PI1Exercise1InputDataXXX.txt";
	private	static String 						outputFileName 	= "/files/output/PI1Exercise1OutputDataXXX.txt";
	private static Class<TestExercise1JUnit> 	thisClass 		= TestExercise1JUnit.class;
	private static List<String> 				lsInput 		= Arrays.asList("Small", "Medium", "Large");
	
	@BeforeAll
	private static void readInput() {
		Function<String, Punto2D> parsePunto = s -> {
			String[] v = s.split(",");
			return Punto2D.of(Double.valueOf(v[0]), Double.valueOf(v[1]));
		};
		int i = 0;
		input = new ArrayList<>();
		for(String version:lsInput) {
			InputStream is = thisClass.getResourceAsStream(inputFileName.replace("XXX", version));
			InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(streamReader);	        
			input.add(i, reader.lines().map(parsePunto).toList());
			i++;
		}
	}
	
	@BeforeAll
	private static void readOutput() {
		int i = 0;
		expectedOutput = new ArrayList<>();	
		for(String version:lsInput) {
			InputStream is = thisClass.getResourceAsStream(outputFileName.replace("XXX", version));
			InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(streamReader);
			expectedOutput.add(i, new HashMap<Cuadrante,String>());	
			for(String s:reader.lines().toList()) {
				String[] v = s.split(",");
				expectedOutput.get(i).put(Cuadrante.valueOf(v[0]), v[1]);
			}
			i++;
		}
	}
	
	@ParameterizedTest
	@CsvSource({ 
		  "ejercicio1NotacionFuncional, 0", 
		  "ejercicio1Iterativo, 0",
		  "ejercicio1Recursivo, 0",
		  "ejercicio1NotacionFuncional, 1", 
		  "ejercicio1Iterativo, 1",
		  "ejercicio1Recursivo, 1",
		  "ejercicio1NotacionFuncional, 2", 
		  "ejercicio1Iterativo, 2",
		  "ejercicio1Recursivo, 2"
		})
	void testOutput(String methodName, int version) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		Class<?> 	ejercicioClass 	= Class.forName("exercises.Exercise1"); 
        Class<?>[] 	paramTypes 		= {List.class};
        Method 		ejercicioMethod = ejercicioClass.getMethod(methodName, paramTypes);
        Map<Cuadrante,String> output = (Map<Cuadrante, String>) ejercicioMethod.invoke(null, input.get(version)); 
        assertEquals(expectedOutput.get(version),output,"Expected output: "+expectedOutput.get(version)+"-"+methodName+" output: "+output);
	}

}
