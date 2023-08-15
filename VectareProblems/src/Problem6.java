/*
 * Starting in the top left corner of a 2×2 grid, and only being able to move
to the right and down, there are exactly 6 routes to the bottom right
corner.

By observing an empty base case - a straight line - this produces only one possible path.
In general, we can assume that reaching the edge of the grid produces only one possible permutation.

A 1x1 grid yields 2 possible permutations. Two movements are needed. Target pos is (1,1)
Possible permutations are DR, RD
A 2x2 grid yields 6 possible permutations. Four movements are needed. Target pos is (2,2)
Possible permutations are RRDD, DDRR, RDRD, DRDR, DRRD, RDDR
Supposing a movement applies a transformation to a position i.e (0,0) -> (0,1).

We can use previous answers to try and solve the possible permutations, although we must be wary of possible overlap -
that is to say when aggregating the solutions some paths may be counted twice. 
Given a grid 2x1, the solution must be the same as a 1x2 grid.
We will use a two dimensional array to store the values, where index represents the grid dimension.

For a 1xn grid, the solution is simply n+1 possible permutations, as there are n+1 places the down movement can be. 
Our first algorithm will attempt to observe the possible permutations of moving down and right (or other way around)
combined with the previous answer to save some computation. It is also safe to assume that a n-1xn-1 solution always
exists and so a previous solution check is not required. Therefore, the only computation left to do is the 'edge' movements.

For a general case, we assume we move one unit down, and then calculate the possible permutations for that grid. This can be 
performed recursively to reduce one of the dimensions to a 1xn dimension in which a solution exists for. The same is then
performed for the other dimension, moving right instead, to reduce it to a nx1 dimension.
 */

public class Problem6 {
	private static long[][] PreviousSolutions = InitSolutions();
	
	public static void main(String[] args) {
		System.out.println(CalculateGrid(20,20));
	}

	private static long CalculateGrid(int dimension1, int dimension2) {
		// As stated before, a 1x3 solution holds the same permutations as a 3x1 permutation, and so
		// this block attempts to prevent extra calculations by looking at at a Max x Min solution
		int Max = Math.max(dimension1, dimension2);
		int Min = Math.min(dimension1, dimension2);
		// If a solution exists, we bypass a calculation altogether
		if (Exists(PreviousSolutions[Max][Min]))
			return PreviousSolutions[Max][Min];
		
		// If one of the dimensions is 1, we use the simple computation.
		if (Min == 1)
			return OneByNComp(Max);
		
		// Assume we move down, and calculate possible permutations
		long NumPermutations = CalculateGrid(dimension1 - 1, dimension2);
		
		// Assume we move right , and calculate possible permutations
		NumPermutations += CalculateGrid(dimension1, dimension2 - 1);
		
		// We store the new calculated solution
		PreviousSolutions[dimension1][dimension2] = NumPermutations;
		return NumPermutations;
	}
	private static long[][] InitSolutions() {
		long[][] Solutions = new long[21][21];
		Solutions[1][1] = 2; // Given
		Solutions[2][2] = 6; // Given
		
		return Solutions;
	}
	
	private static long OneByNComp(int n) {
		return n+1;
	}
	
	private static boolean Exists(long previousSolutions2) {
			return ((previousSolutions2 != 0) ? true: false);
	}
	
}
