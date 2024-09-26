package tests.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import exercises.Exercise3.Tupla;

public class TestExercise3JUnit {
	private static List<Tupla>					input;
	private static List<Set<Integer>>			expectedOutput;
	private	static String 						inputFileName 	= "/files/input/PI1Exercise3InputData.txt";
	private	static String 						outputFileName 	= "/files/output/PI1Exercise3OutputData.txt";
	private static Class<TestExercise3JUnit> 	thisClass 		= TestExercise3JUnit.class;
	
	@BeforeAll
	private static void readInput() {
		InputStream is = thisClass.getResourceAsStream(inputFileName);
		InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(streamReader);
	        
		Function<String, Tupla> parseTupla = s -> {
			String[] v = s.split(",");
			return Tupla.of(Integer.valueOf(v[0]), Integer.valueOf(v[1]), Integer.valueOf(v[2]));
		};
		input = reader.lines().map(parseTupla).toList();
		//readOutput();
	}

	@BeforeAll
	private static void readOuput() {		
		InputStream is = thisClass.getResourceAsStream(outputFileName);
		InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(streamReader);	        
		Function<String, Set<Integer>> parseArray= s -> {
			return new HashSet<Integer>(Stream.of(s.split(",")).mapToInt(Integer::parseInt).boxed().toList());
		};
		expectedOutput = reader.lines().map(parseArray).toList();
	}
	
	@ParameterizedTest
	@CsvSource({ 
		  "ejercicio3RecursivoSinMemoria, 0", 
		  "ejercicio3RecursivoSinMemoria, 1", 
		  "ejercicio3RecursivoSinMemoria, 2", 
		  "ejercicio3RecursivoSinMemoria, 3", 
		  "ejercicio3RecursivoSinMemoria, 4", 
		  "ejercicio3RecursivoSinMemoria, 5",
		  "ejercicio3RecursivoSinMemoria, 6", 
		  "ejercicio3RecursivoSinMemoria, 7", 
		  "ejercicio3RecursivoSinMemoria, 8", 
		  "ejercicio3RecursivoSinMemoria, 9", 
		  "ejercicio3RecursivoSinMemoria, 10",
		  "ejercicio3RecursivoConMemoria, 0", 
		  "ejercicio3RecursivoConMemoria, 1", 
		  "ejercicio3RecursivoConMemoria, 2", 
		  "ejercicio3RecursivoConMemoria, 3", 
		  "ejercicio3RecursivoConMemoria, 4", 
		  "ejercicio3RecursivoConMemoria, 5", 
		  "ejercicio3RecursivoConMemoria, 6", 
		  "ejercicio3RecursivoConMemoria, 7", 
		  "ejercicio3RecursivoConMemoria, 8", 
		  "ejercicio3RecursivoConMemoria, 9", 
		  "ejercicio3RecursivoConMemoria, 10", 
		  "ejercicio3Iterativo, 0", 
		  "ejercicio3Iterativo, 1",
		  "ejercicio3Iterativo, 2",
		  "ejercicio3Iterativo, 3",
		  "ejercicio3Iterativo, 4",
		  "ejercicio3Iterativo, 5",
		  "ejercicio3Iterativo, 6",
		  "ejercicio3Iterativo, 7",
		  "ejercicio3Iterativo, 8",
		  "ejercicio3Iterativo, 9",
		  "ejercicio3Iterativo, 10"
		})
	void testOutput(String methodName, int i) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		Class<?> 	ejercicioClass 	= Class.forName("exercises.Exercise3"); 
        Class<?>[] 	paramTypes 		= {Integer.class, Integer.class, Integer.class};
        Method 		ejercicioMethod = ejercicioClass.getMethod(methodName, paramTypes);
		Tupla t = input.get(i);			
        Set<Integer> output = (Set<Integer>)ejercicioMethod.invoke(null, t.a(), t.b(), t.c()); // pass args
        assertEquals(expectedOutput.get(i),output,"Expected output: "+expectedOutput.get(i)+"-"+methodName+" output: "+output);
	}
}
