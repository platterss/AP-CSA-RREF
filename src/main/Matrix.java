package main;

import java.math.*;
import java.util.*;

public class Matrix {
    private final double[][] matrix;

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public int getRows() {
        return matrix.length;
    }

    public int getColumns() {
        return matrix[0].length;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void print() {
        for (double[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public void printRREF() {
        boolean noSolution = false;
        boolean infiniteSolutions = false;

        // Gaussian elimination
        for (int r = 0; r < this.getRows(); r++) {
            // Make the diagonal 1
            if (matrix[r][r] != 0) {
                double divFactor = matrix[r][r];
                for (int c = 0; c < this.getColumns(); c++) {
                    matrix[r][c] /= divFactor;
                }
            }

            System.out.println("Making the diagonal 1: (Row " + (r + 1) + ")");
            print();

            // Make all rows below this one 0 in the current column
            for (int i = r + 1; i < this.getRows(); i++) {
                double factor = matrix[i][r];
                for (int c = 0; c < this.getColumns(); c++) {
                    matrix[i][c] -= factor * matrix[r][c];
                }
            }

            sanitizeMatrix();
            System.out.println("Making everything else in the column 0: (Row " + (r + 1) + ")");
            print();
        }

        // Back substitution to get RREF
        for (int r = this.getRows() - 1; r >= 0; r--) {
            for (int i = r - 1; i >= 0; i--) {
                double factor = matrix[i][r];
                for (int c = 0; c < this.getColumns(); c++) {
                    matrix[i][c] -= factor * matrix[r][c];
                }
            }
        }

        System.out.println("Back substitution:");
        print();

        // Rounding the results
        for (int r = 0; r < this.getRows(); r++) {
            matrix[r][this.getColumns() - 1] = round(matrix[r][this.getColumns() - 1]);
        }

        System.out.println("Rounding:");
        print();

        // Checks for no solutions
        for (int r = 0; r < this.getRows(); r++) {
            // Checks if a row's coefficients are all zeros
            boolean rowAllZeros = true;
            for (int c = 0; c < this.getColumns() - 1; c++) {
                if (matrix[r][c] != 0) {
                    rowAllZeros = false;
                    break;
                }
            }

            // Checks if the last element of a row is not 0
            // 0x + 0y + 0z = k implies 0 = k, which is false where k != 0
            if (rowAllZeros && matrix[r][this.getColumns() - 1] != 0) {
                noSolution = true;
                break;
            }
        }

        // Count the non-zero rows to determine whether it's infinite solutions
        // 0x + 0y + 0z = 0 -> 0 = 0, therefore anything works
        int nonZeroRows = 0;
        for (int r = 0; r < this.getRows(); r++) {
            for (int c = 0; c < this.getColumns(); c++) {
                if (matrix[r][c] != 0) {
                    nonZeroRows++;
                    break;
                }
            }
        }

        // Checks if the number of non-zero rows is less than the amount of variables
        if (nonZeroRows < this.getColumns() - 1) {
            infiniteSolutions = true;
        }

        // Print
        System.out.println("Result:");
        if (noSolution) {
            System.out.println("The system has no solution.");
        } else if (infiniteSolutions) {
            System.out.println("The system has infinitely many solutions.");
        } else {
            print();
        }
    }

    // Replaces -0.0 with 0.0 because it can be negative for some reason
    private void sanitizeMatrix() {
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getColumns(); c++) {
                if (matrix[r][c] == 0.0) {
                    matrix[r][c] = 0.0;
                }
            }
        }
    }

    private static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(10, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
