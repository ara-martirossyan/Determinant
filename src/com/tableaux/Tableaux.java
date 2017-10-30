package com.tableaux;

import java.util.Random;

public class Tableaux {

	public static void main(String[] args) {
		int[][] m = new int[10][10];		
		populateMatrix(1000, m);
		printMatrix(m);
		System.out.println("");
		System.out.println("detM = " + determinant(m));

	}
	
	public static int pow(int a, int n) {
		int res = 1;
		for (int i = 0; i < n; i++) {
			res*=a;
		}
		return res;
	}
	
	public static int determinant(int[][] matrix) {
		if (matrix.length == 1) {
			return matrix[0][0];
		} else {
			int det = 0;
			for (int i = 0; i < matrix.length; i++) {
				int[][] minor_i_0 = minor(matrix, i, 0);
				det +=  pow(-1, 2+i)*matrix[i][0]*determinant(minor_i_0);
			}
			return det;
		}
	}
	
	public static int[][] minor(int[][] matrix, int i, int j) {			
		int dim = matrix.length - 1;
		int[][] m = new int[dim][dim];
		if (i > matrix.length - 1 || j > matrix.length - 1 || i < 0 || j < 0) {
			return m;
		}
		int pseudoRow;
		int pseudoCol;
		for (int row = 0; row < m.length; row++) {
			for (int col = 0; col < m[row].length; col++) {
				if (row >= i) {
					pseudoRow = row + 1;
				} else {
					pseudoRow = row;
				}
				if (col >= j) {
					pseudoCol = col + 1;
				} else {
					pseudoCol = col;
				}

				m[row][col] = matrix[pseudoRow][pseudoCol];
			}
		}
		
		return m;
	}
	
	public static void printMatrix(int[][] matrix) {
		int max = maximumVal(matrix);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (j==0) {
					System.out.print("| ");
				}				
				System.out.print(matrix[i][j]);
				int space = numberOfDigits(max) + 1 - numberOfDigits(matrix[i][j]);
				for (int j2 = 0; j2 < space; j2++) {
					System.out.print(" ");
				}
				if (j==matrix[i].length - 1) {
					System.out.println("|");
				}
			}
		}
	}
	
	private static int maximumVal(int[][] matrix) {
		int max = matrix[0][0];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j] > max) {
					max = matrix[i][j];
				}
			}
		}
		return max;
	}
	
	public static void populateMatrix(int range, int[][] matrix) {
		Random r = new Random();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = r.nextInt(range);
			}
		}
	}
	
	public static int numberOfDigits(int n) {
		if (n == 0) {
			return 1;
		}
		int i = 0;
		while (n != 0) {
			n /= 10;
			i++;
		}
		return i;
	}
	
}
