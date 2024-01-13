import java.math.*;
import java.util.*;

public class Matrix {
    private int[][] matrix;

    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getRows() {
        return matrix.length;
    }

    public int getColumns() {
        return matrix[0].length;
    }

    public void printMatrix() {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void printRREF() {
        double[][] newMatrix = new double[this.getRows()][this.getColumns()];

        // Convert array to doubles to be able to divide
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }

        // Gaussian elimination
        for (int r = 0; r < this.getRows(); r++) {
            // Make the diagonal contain all 1's
            if (newMatrix[r][r] != 0) {
                double divFactor = newMatrix[r][r];
                for (int c = 0; c < this.getColumns(); c++) {
                    newMatrix[r][c] /= divFactor;
                }
            }

            // Make all rows below this one 0 in the current column
            for (int i = r + 1; i < this.getRows(); i++) {
                double factor = newMatrix[i][r];
                for (int c = 0; c < this.getColumns(); c++) {
                    newMatrix[i][c] -= factor * newMatrix[r][c];
                }
            }
        }

        // Back substitution to get reduced echelon form
        for (int r = this.getRows() - 1; r >= 0; r--) {
            for (int i = r - 1; i >= 0; i--) {
                double factor = newMatrix[i][r];
                for (int c = 0; c < this.getColumns(); c++) {
                    newMatrix[i][c] -= factor * newMatrix[r][c];
                }
            }
        }

        // Rounding
        for (int r = 0; r < this.getRows(); r++) {
            newMatrix[r][this.getColumns() - 1] = round(newMatrix[r][this.getColumns() - 1]);
        }

        // Printing
        for (double[] row : newMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(10, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
