import java.util.ArrayList;

/*
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
that the 6th prime is 13. What is the 10,001st prime number?

This one is a little trickier as the usual brute force approach may stop this from running in under a minute.
Heuristics can be used to predict if a number is prime or not, but does not solve it for the nth prime where n is specific.
Furthermore, inaccuracies could potentially give false positives or negatives messing up the sequence.

We could circumvent checking even numbers altogether by applying a 2n-1 transformation.

We could also consider this a dynamic programming problem. As we approach some number in the list, all numbers before it are the
prime factors, hence we can avoid the silly loop of trying to do modulo with all numbers up to n.
 */

public class Problem5 {
	private static ArrayList<Integer> PrimesList = InitPrimes();
	private static int LastTerm = 10001;
	
	public static void main(String[] args) {
		
		int i=2;
		int NewNumber = 0;
		while (PrimesList.size() < LastTerm) {
			NewNumber = (i*2)-1;
			if (IsPrime(NewNumber))
				PrimesList.add(NewNumber);
			i++;
		}
		
		System.out.println(PrimesList);
		System.out.println(PrimesList.get(LastTerm-1));
		
	}
	
	private static ArrayList<Integer> InitPrimes() {
		ArrayList<Integer> Primes = new ArrayList<Integer>();
		Primes.add(2); // Add the 1st prime which is 2
		return Primes;
	}
	
	private static boolean IsPrime(int Number) {
		for (int i=0; i < PrimesList.size(); i++) {
			if (Number % PrimesList.get(i) == 0)
				return false;
		}
		
		return true;
	}
}
