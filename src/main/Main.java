package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Matrix matrix = new Matrix(getInput());
            System.out.println();

            System.out.println("Inputted matrix:");
            matrix.print();

            System.out.println("RREF:");
            matrix.printRREF();

            System.out.println("\nWould you like to enter a new matrix? N to exit");
            String restart = scanner.nextLine().toLowerCase();

            if (restart.equals("n")) {
                break;
            }
        }
    }

    public static double[][] getInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Number of columns: ");
        int columns = scanner.nextInt();

        double[][] matrix = new double[rows][columns];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.println("Value in (" + i + ", " + j + "):");
                matrix[i][j] = scanner.nextDouble();
            }
        }

        return matrix;
    }
}