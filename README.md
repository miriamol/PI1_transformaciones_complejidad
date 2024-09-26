# Individual Practice 1 - Iterative, recursive and functional notation exercises. Empirical analysis of complexity

## Exercise 1. 

Analyze the code shown below:

```
public static Map<Cuadrante, String> ejercicio1NotacionFuncional(List<Punto2D> ls) {
	        UnaryOperator<Punto2D> nx = punto -> {
	            double nuevaX = punto.x() + 3;
	            return Punto2D.of(nuevaX, punto.y());
	        };

	        return ls.stream()
	                .filter(p -> p.x() % 5 != 0)  
	                .map(nx)  
	                .collect(Collectors.groupingBy(
	                        Punto2D::cuadrante,
	                        Collectors.reducing(
	                                "",
	                                p -> p.x() % 2 == 0 ? p.x() + "¡" : p.x() + "!",
	                                (a, b) -> a.isEmpty() ? b : a + "-" + b
	                        )
	                ));
}
```
 
**WHAT YOU NEED TO DO**: Provide an equivalent iterative and final recursive solution. 
	
## Exercise 2. 

Given the following recursive definition of the function f (which takes as input 1 positive number and a string, and returns a list of strings):

```math
f\left(a,s\right)=\
\begin{cases}
\left[toString\left(a\right)+s\right], a \leq 3 ⋁ s.length \leq 2\\
f\left(\frac{a}{2},s.substring\left(s.length\right)\right)+\left(toString\left(a\right)+s\right), \text{otherwise}
\end{cases}
```

where:
* [elem]: list with a single item **elem**
* s1+s2: concatenation of strings s1 and s2
* list + elem: insertion of the elem element at the end of the list **list**
* toString(a): string associated to the integer a.
* s.substring(num): substring of the string s, starting at position 0 and with length equal to num.

**WHAT YOU NEED TO DO**:  Provide a non-final recursive solution, an iterative one using while, a final recursive one, and one in functional notation.

## Exercise 3. 

Given the following recursive definition of the function g (which takes as input 3 positive integers and returns a set of integers):

```math
g\left(a,b,c\right)=\
\begin{cases}
\left\{2*a,b+3,c\right\},a \leq 5 ⋁ b \leq 3 ⋁ c \leq 2 \\
g\left(a-5,\frac{b}{4},c-2\right)+g\left(\frac{a}{3},b-3,\frac{c}{2}\right), \text{otherwise}
\end{cases}
```

where + is an operator representing the union of sets.

**WHAT YOU NEED TO DO**:   Provide a recursive solution without memory, a recursive solution with memory, and an iterative solution.

## Exercise 4. 

Given the following recursive definition of the function f (which takes as input a positive integer):

```math
h\left(a\right)=\
\begin{cases}
5,     a<10\\
Math.sqrt\left(3*a\right)*h\left(a-2\right),\text{otherwise}
\end{cases} 
```

**WHAT YOU NEED TO DO**: Analyze the execution times of the recursive version of that function, comparing according to whether the results are of type Double or BigInteger. To do this, you must obtain 3 graphs:
<ol type="a">
   <li>The one obtained when the results are of type Double.</li>
   <li>The one obtained when the results are of type BigInteger</li>
   <li>The one obtained by combining the 2 previous ones.</li>
</ol>

## Exercise 5. 

Given the following recursive definition of the function f (which takes as input a positive integer):

```math
k\left(n\right)=\
\begin{cases}
1, n \leq 6\\
1+k\left(n-1\right)*log2\left(n-1\right), n > 6
\end{cases}
```

```
public static int log2(int n){
    if(n <= 0) throw new IllegalArgumentException();
    return 31 - Integer.numberOfLeadingZeros(n);
}
```

**WHAT YOU NEED TO DO**: Analyze the execution times of the recursive version of this function, comparing according to whether the results are of type Double or BigInteger. To do this, you must obtain 3 graphs:
<ol type="a">
   <li>The one obtained when the results are of type Double.</li>
   <li>The one obtained when the results are of type BigInteger</li>
   <li>The one obtained by combining the 2 previous ones.</li>
</ol>

**You must solve all exercises efficiently. Please note that:
	For each exercise you must read the input data from a file, and display the output on screen. This reading must be independent of the particular algorithm that solves the exercise. 
	The solution must be in accordance with the course material provided.**



