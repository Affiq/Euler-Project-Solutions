

// Problem definition:
// If we list all the natural numbers below 10 that are multiples of 3 or 5, we
// get 3, 5, 6 and 9. The sum of these multiples is 23.
// Find the sum of all the multiples of 3 or 5 below 1000


// We can approach this problem through pure brute force, but for simplicity we may
// also approach the problem using Gaussian Summation we will just need some parameters
// Namely the first term, the last term, and the number of terms. We will create a func
// to calculate the sum from these parameters.


// We now run into the another problem - numbers that appear in both sums have been counted twice
// For example 15, 30, 45, etc... The solution to this would then be to calculate the gaussian sum
// of multiples of 15, and subtract that from our final sum!

public class Problem1 {
	
	public static void main(String[] args) {
		int TargetNumber = 1000;
		TargetNumber -= 1; // We actually exclude the target numbers i.e look below it
		int sum = 0;
		int LastTerm = 0;
		
		// Summation of multiples of 3 block
		LastTerm = CalculateLastTerm(TargetNumber, 3);
		sum = GaussSum(LastTerm, 3);
		
		// Summation of multiples of 5 block
		LastTerm = CalculateLastTerm(TargetNumber, 5);
		sum = sum + GaussSum(LastTerm, 5);
		
		// Summation of multiples of 15 block
		LastTerm = CalculateLastTerm(TargetNumber, 15);
		sum = sum - GaussSum(LastTerm, 15);
		
		System.out.print(sum);
	}
	
	// Calculates the last term - the highest number before our target number that can be divided by the divisor
	private static int CalculateLastTerm(int TargetNumber, int Divisor) {
		int remainder = TargetNumber % Divisor;
		return TargetNumber - remainder;
	}
	
	// Calculates the N for our formula, our first term will coincidentally also be the divisor.
	private static int CalculateN(int LastTerm, int Divisor) {
		return (LastTerm / Divisor);
	}
	
	// Calculates Gaussian Summation for given divisor
	private static int GaussSum(int LastTerm, int Divisor) {
		int n = CalculateN(LastTerm, Divisor);
		return n*(Divisor + LastTerm)/2;
	}
}
