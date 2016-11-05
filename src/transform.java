
/*
ID: moser.el
LANG: JAVA
TASK: transform
 */

import java.io.*;
import java.util.*;

class transform {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("transform.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		
		int N = Integer.parseInt(f.readLine());
		
		char[][] initialMatrix = new char[N][N];
		for (int line = 0; line < N; line++) {
			initialMatrix[line] = f.readLine().toCharArray();
		}
		
		char[][] finalMatrix = new char[N][N];
		for (int line = 0; line < N; line++) {
			finalMatrix[line] = f.readLine().toCharArray();
		}
		
		int minTransform = getMinimumTransform(initialMatrix, finalMatrix);
		
		out.println(minTransform);
		
		out.close();
	}
	
	public static int getMinimumTransform(char[][] initialMatrix, char[][] finalMatrix) {
		if (isEqual(finalMatrix, rotate90(initialMatrix)))
			return 1;
		else if (isEqual(finalMatrix, rotate180(initialMatrix)))
			return 2;
		else if (isEqual(finalMatrix, rotate270(initialMatrix)))
			return 3;
		else if (isEqual(finalMatrix, reflect(initialMatrix)))
			return 4;
		else if (isEqual(finalMatrix, reflect(rotate90(initialMatrix))) ||
				 isEqual(finalMatrix, reflect(rotate180(initialMatrix))) ||
				 isEqual(finalMatrix, reflect(rotate270(initialMatrix))))
			return 5;
		else if (isEqual(finalMatrix, initialMatrix))
			return 6;
		else
			return 7;
	}
	
	public static void printMatrix(char[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	public static boolean isEqual(char[][] matrixA, char[][] matrixB) {
		int N = matrixA.length;
		for (int line = 0; line < N; line++) {
			for (int column = 0; column < N; column++) {
				if (matrixA[line][column] != matrixB[line][column])
					return false;
			}
		}
		return true;
	}
	
	public static char[][] rotate90(char[][] startingMatrix) {
		int N = startingMatrix.length;
		char[][] transformedMatrix = new char[N][N];
		
		for(int line = 0; line < N; line++) {
			for (int column = 0; column < N; column++) {
				int nextLine = column;
				int nextColumn = N - 1 - line;
				transformedMatrix[nextLine][nextColumn] = startingMatrix[line][column];
			}
		}
		
		return transformedMatrix;
	}
	
	public static char[][] rotate180(char[][] startingMatrix) {
		return rotate90(rotate90(startingMatrix));
	}
	
	public static char[][] rotate270(char[][] startingMatrix) {
		return rotate90(rotate90(rotate90(startingMatrix)));
	}
	
	public static char[][] reflect(char[][] startingMatrix) {
		int N = startingMatrix.length;
		char[][] transformedMatrix = new char[N][N];
		
		for(int line = 0; line < N; line++) {
			for (int column = 0; column < N; column++) {
				int nextLine = line;
				int nextColumn = N - 1 - column;
				transformedMatrix[nextLine][nextColumn] = startingMatrix[line][column];
			}
		}
		
		return transformedMatrix;
	}
}
