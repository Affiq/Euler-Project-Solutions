/*A palindromic number reads the same both ways. The largest palindrome.
made from the product of two 2-digit numbers is 9009 = 91 × 99.
Find the largest palindrome made from the product of two 3-digit
numbers.

With regards to efficiency, we can complete the palindrome aspect of the problem by generating palindromes rather than
needing to generate a number and checking if it is a Palindrome. Given two 3-digit numbers, the palindrome should be 6 digits.
Starting from 999, we can then reverse the number and append it to the original number to get the next palindromes.
This cuts the efficiency as we create ~9x10^3 digits instead of creating ~9x10^6 digits and performing a check on top.

The next aspect is to check if we can divide the palindrome by a divisor >= 100 and <= 999, which produces another 3
digit number (with the same conditions - although we may have to consider if we even need this check).
It may also be feasible to start from 100 to 999, as we may encounter the divisors much earlier on (i.e we will hit 111 which
will generate an answer much earlier than if we encountered a 777 divisor).
 */

public class Problem3 {

	static int StartingPoint = 999; // Where the palindrome should start from after duplicating, reversing and appending to og string
	static int EndPoint = 100; // Once we go beneath here, i.e 99, this violates the 6 digit palindrome
	static StringBuilder stringBuilder = new StringBuilder();
	static boolean FoundDivisorFlag = false;
	
	public static void main(String[] args) {
		
		// Main block used to achieve a new palindrome
		for (int i = StartingPoint; i >= EndPoint; i--) {
			// A bit of a mouthful - convert i to string, reverse using sb, convert to string, and then convert back to int.
			int NewPalindrome = CalculatePalindrome(i);
			
			FoundDivisorFlag = Has3DigitDivisors(NewPalindrome);
			if (FoundDivisorFlag) 
				break;
			}
		}
		
		private static int CalculatePalindrome(int Number) {
			// A bit of a mouthful - convert i to string, reverse using sb, convert to string, and then convert back to int.
			int NewPalindrome = Integer.parseInt( stringBuilder.append(Integer.toString(Number)).reverse().toString());
			NewPalindrome += (Number*1000);
			// Clear the stringbuilder
			stringBuilder.delete(0, stringBuilder.length());
			return NewPalindrome;
		}
		
		private static boolean Is3Digit(int Number) {
			return ((Number >= 100) && (Number <= 999));
		}
		
		private static boolean Has3DigitDivisors(int Number) {
			for (int divisor=100; divisor <= 999; divisor++) { // By nature of our code, our initial divisor is 3 digits
				if (Number % divisor == 0) {
					if (Is3Digit(Number/divisor)) {
						System.out.println("Divisor found for " + Number);
						System.out.println(divisor + " and " + (Number/divisor));
						return true;
					}
				}
			}
			
			return false;
		}
	
}
