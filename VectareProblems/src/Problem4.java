import java.math.BigDecimal;
import java.util.ArrayList;

/*
2520 is the smallest number that can be divided by each of the numbers
from 1 to 10 without any remainder.
What is the smallest positive number that is evenly divisible by all of the
numbers from 1 to 20?

 This problem is simply asking for the lowest common multiple for all numbers given 1 to 20. We can create a general
 program by first deriving the prime factors for each number, and then keeping track of each times the prime factor appears
 the most for each number. For instance, given 2x2x3 and 3x3x2, the program calculates that the highest recurrence for 2 is two
 from the first number, and for 3 is also two from the second number. Hence the LCM is 2x2x3x3.
 
  Now we run into the problem of how we will represent our data. We will use an arraylist where the ith number represents the
  ith prime in the sequence of prime numbers.
 */
public class Problem4 {

	private static int UpperBound = 20;
	private static ArrayList<Integer> PrimeFactors = InitPrimeFactors(); // All prime factors up to upperbound
	private static ArrayList<Integer> PrimeNumsTracker = InitPrimeNumsTracker();
	
	public static void main(String[] args) {
		for (int i=1; i <= UpperBound ;i++) {
			UpdatePrimeNumsTracker(i);
		}
		System.out.println(PrimeNumsTracker);
        System.out.println(String.format(new BigDecimal(CalculateLCM()).toPlainString()));

	}
	
	// Used to update the list for a single number...
	private static void UpdatePrimeNumsTracker(int Number) {
		int CurrentValue = Number;
		int CurrentRecurence = 0;
		int CurrentDivisor = 0;
		
		// Decompose the number down...
		for (int i=0; i < PrimeFactors.size(); i++) {
			CurrentRecurence = 0;
			CurrentDivisor = PrimeFactors.get(i);
			
			// While we can still divide by current primefactor, decompose currentvalue and increment recurrence
			while (CurrentValue % CurrentDivisor == 0) {
				CurrentValue = CurrentValue / CurrentDivisor;
				CurrentRecurence++;
			}
			// If it exceeds the recurrence in our overall tracker, we will update it with new max value...
			if (CurrentRecurence > PrimeNumsTracker.get(i))
				PrimeNumsTracker.set(i, CurrentRecurence);
		}
	}
	
	// Initialises all prime factors up to upper bound
	private static ArrayList<Integer> InitPrimeFactors() {
		ArrayList<Integer> PrimeFactors = new ArrayList<Integer>();
		for (int i=2; i<=UpperBound; i++) {
			if (IsPrimeFactor(i))
				PrimeFactors.add(i);
		}
		return PrimeFactors;
	}
	
	// Initalises zero valued prime nums tracker
	private static ArrayList<Integer> InitPrimeNumsTracker() {
		ArrayList<Integer> ZeroList = new ArrayList<Integer>();
		for (int i=0; i < PrimeFactors.size() ; i++ ) {
			ZeroList.add(0);
		}
		return ZeroList;
	}
	
	private static boolean IsPrimeFactor(int Number) {
		for (int divisor = 2; divisor < Number; divisor++) {
			if ((Number % divisor) == 0) 
				return false; // Even divider found
		}
		
		return true;
	}
	
	private static double CalculateLCM() {
		double sum=1;
		
		for (int i=0; i<PrimeFactors.size(); i++) {
			sum = (sum * Math.pow(PrimeFactors.get(i), PrimeNumsTracker.get(i))); // Need power to do 2x2x2 for example
		}
		
		return sum;
	}
}
