import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] array = getInput();
        Matrix matrix = new Matrix(array);

        matrix.printMatrix();
        matrix.printRREF();
    }

    public static int[][] getInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Number of columns: ");
        int columns = scanner.nextInt();

        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.println("Value in (" + i + ", " + j + "):");
                matrix[i][j] = scanner.nextInt();
            }
        }

        return matrix;
    }
}