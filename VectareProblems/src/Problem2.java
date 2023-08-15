
/* A good approach would be to somehow have a general formula that allows us to bypass the calculations of some numbers
 * This would be ideally done by creating a method to jump from one even Fibonacci number to another. For convenience purposes,
 * we will add a 1 to the start of the list for the continuity of evenness of every 3rd term.
 * 1,1,2,3,5,8,13,21,34,55,89,144 => 2,8,34,144... 
 * 
 *  Hence we need to do a process of substitution for the nth term that will use even terms before it (only n-3rd terms, n-6th 
 *  terms or n-9th terms ... etc). Where n is the current term, (it may get a little messy)
 *  
 *  f(n) = f(n-1) + f(n-2) =				Sum of last two terms...
 *  (f(n-2) + f(n-3) + f(n-2)) = 			Substitute n-1 term
 *  2f(n-2) + f(n-3) = 						
 *  2(f(n-3)+ f(n-4)) + f(n-3) =			Substitute n-2 term
 *  3f(n-3) + 2f(n-4) =						Substitute one n-4 term
 * 	3f(n-3) + f(n-4) + (f(n-5) + f(n-6)) =	Substitute f(n-4) + f(n-3)
 * 	3f(n-3) + f(n-3) + f(n-6) =				Simplify
 * 	4f(n-3) + f(n-6)
 * 
 * 	Hence we can calculate every 3rd term of our sequence (if we consider the appended sequence, it will be every 3n+2th term
 *  for the sequence the problem was given to us), where the first two terms will be 2 and 8.
 * 

*/

public class Problem2 {

	private static double UpperBound = 4000000;
	
	public static void main(String[] args) {
		double FibSum = 2 + 8 + FibonacciSum(2,8); // Hardcoding the intial values
		System.out.println(FibSum);
	}
	
	private static double FibonacciSum(double SecondLastTerm, double LastTerm) {
		double NextNum = (4*LastTerm) + SecondLastTerm;
		
		if (NextNum >= UpperBound) { // If the next number is above the upper bound, return 0 to terminate recursion
			return 0;
		}
		// Can integrate elseif to check 1st term= 2 and 2nd term=8 but may increase complexity
		else {
			return NextNum + FibonacciSum(LastTerm, NextNum);
		}
	}
	
}
