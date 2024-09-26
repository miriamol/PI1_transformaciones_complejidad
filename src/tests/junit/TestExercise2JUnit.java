package tests.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import us.lsi.common.Pair;

public class TestExercise2JUnit {
	private static List<Pair<Integer,String>>	input;
	private static List<List<String>>			expectedOutput;
	private	static String 						inputFileName 	= "/files/input/PI1Exercise2InputData.txt";
	private	static String 						outputFileName 	= "/files/output/PI1Exercise2OutputData.txt";
	private static Class<TestExercise2JUnit> 	thisClass 		= TestExercise2JUnit.class;
	
	@BeforeAll
	private static void readInput() {
		InputStream is = thisClass.getResourceAsStream(inputFileName);
		InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(streamReader);        
		input = reader.lines().map(s -> Pair.parse(s,",",s1->Integer.parseInt(s1),s2->s2))
				.toList();
	}
	
	@BeforeAll
	private static void readOutput() {
		InputStream is = thisClass.getResourceAsStream(outputFileName);
		InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(streamReader);        
		Function<String, List<String>> parseArray= s -> {
			return Stream.of(s.split(",")).toList();
		};
		expectedOutput = reader.lines().map(parseArray).toList();
	}
	
	@ParameterizedTest
	@CsvSource({ 
		  "ejercicio2RecursivoNoFinal, 0", 
		  "ejercicio2RecursivoNoFinal, 1", 
		  "ejercicio2RecursivoNoFinal, 2", 
		  "ejercicio2RecursivoNoFinal, 3", 
		  "ejercicio2RecursivoNoFinal, 4", 
		  "ejercicio2RecursivoNoFinal, 5", 
		  "ejercicio2Iterativo, 0", 
		  "ejercicio2Iterativo, 1", 
		  "ejercicio2Iterativo, 2", 
		  "ejercicio2Iterativo, 3", 
		  "ejercicio2Iterativo, 4", 
		  "ejercicio2Iterativo, 5", 
		  "ejercicio2RecursivoFinal, 0", 
		  "ejercicio2RecursivoFinal, 1", 
		  "ejercicio2RecursivoFinal, 2", 
		  "ejercicio2RecursivoFinal, 3", 
		  "ejercicio2RecursivoFinal, 4", 
		  "ejercicio2RecursivoFinal, 5", 
		  "ejercicio2NotacionFuncional, 0", 
		  "ejercicio2NotacionFuncional, 1",
		  "ejercicio2NotacionFuncional, 2", 
		  "ejercicio2NotacionFuncional, 3",
		  "ejercicio2NotacionFuncional, 4", 
		  "ejercicio2NotacionFuncional, 5"
		})
	void testOutput(String methodName, int i) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		Class<?> 	ejercicioClass 	= Class.forName("exercises.Exercise2"); 
        Class<?>[] 	paramTypes 		= {Integer.class, String.class};
        Method 		ejercicioMethod = ejercicioClass.getMethod(methodName, paramTypes);
        Pair<Integer,String> t = input.get(i);			
        List<String> output = (List<String>)ejercicioMethod.invoke(null, t.first(), t.second()); // pass args
        assertEquals(expectedOutput.get(i),output,"Expected output: "+expectedOutput.get(i)+"-"+methodName+" output: "+output);
	}
}
