/*
 * By starting at the top of the triangle below and moving to adjacent
numbers on the row below, the maximum total from top to bottom is 23.
3
7 4
2 4 6
8 5 9 3
That is, 3 + 7 + 4 + 9 = 23.
Find the maximum total from top to bottom in triangle.txt (attached to
the email), a 15K text file containing a triangle with one-hundred rows.

My initial thought would be to apply a sum starting from the bottom as greedy algorithms present the problem of missing
large values that could be placed at the edge. This implementation is probably better than a bread-first search.
This also allows a 'mathematically built in dynamic program' and avoids having to recalculate all possible permutations of
the problem. The largest sum will then naturally 'bubble up' to the top row, and the maximal sum will be in the top row.

The first mode would operate as an equilateral triangle, s.t the next path can only be the two numbers below it, and hence
n or n+1 of the next row. This can be visualised as 
  x
 x x
x x x

The second mode would operate as a rectangle, s.t the next path can be three numbers diagonally or below it, 
and so n-1, n or n+1 of the next row. This can be visualised as
x
x x
x x x

 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Problem7 {
	
    static ArrayList<ArrayList<Integer>> triangle = InitTriangle("resources/triangle.txt");

	public static void main(String[] args) {
		// We will begin summing from the bottom-up.
		for (int i= triangle.size()-2; i >= 0; i--) {
			// Change to operate between different modes - both should not be used at once as they operate on triangle var
			 AddTwoFromRowsBelow(i); 
			//  AddThreeFromRowsBelow(i); 
		}
		
		System.out.println("The maximal sum is "+ triangle.get(0).get(0));
		// Printing the whole triangle...
		PrintTriangle();
    }

	// Looks at n and n+1 indexes only.
	private static void AddTwoFromRowsBelow(int RowNumber) {
		ArrayList<Integer> CurrentRow = triangle.get(RowNumber);
		ArrayList<Integer> NextRow = triangle.get(RowNumber+1);
		
		// For each element in the row, add the max value from the two numbers below.
		for (int i=0; i < CurrentRow.size(); i++) {
			int max = Math.max(NextRow.get(i), NextRow.get(i+1));
			CurrentRow.set(i, CurrentRow.get(i) + max);
		}
	}
	
	// Looks at n-1, n and n+1 indexes
	private static void AddThreeFromRowsBelow(int RowNumber) {
		ArrayList<Integer> CurrentRow = triangle.get(RowNumber);
		ArrayList<Integer> NextRow = triangle.get(RowNumber+1);
		
		// Have to deal with the left-most number, where no left number exists...
		int max = Math.max(NextRow.get(0) , NextRow.get(1));
		CurrentRow.set(0, CurrentRow.get(0) + max);
		
		// For each other element in the row, add the max value from three numbers below
		for (int i=1; i < CurrentRow.size(); i++) {
			max = MaxOf3(NextRow.get(i-1), NextRow.get(i), NextRow.get(i+1));
			CurrentRow.set(i, CurrentRow.get(i) + max);
		}
	}
	
	private static int MaxOf3(int num1, int num2, int num3) {
		return Math.max(Math.max(num1, num2), num3);
	}
	
	// Code to read the triangle from the file
    private static ArrayList<ArrayList<Integer>> InitTriangle(String filePath) {
        ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>();
    	
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            // For each line, split string, cast to int and store in array...
            while ((line = reader.readLine()) != null) {
            	String[] Constituents = line.split(" ");
            	ArrayList<Integer> temp = new ArrayList<Integer>();
            	
            	for (int i=0; i < Constituents.length ; i++) {
            		temp.add(Integer.parseInt(Constituents[i]));
            	}
            	// Add to overall array
            	triangle.add(temp);
            }
            return triangle;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
     
    }
    
    
    // Code that prints out entire triangle
    private static void PrintTriangle() {
    	for (int i=0; i < triangle.size(); i++) {
    		System.out.println(triangle.get(i));
    	}
    }
}
