package test;

import main.Matrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputContent));
    }

    @Test
    void testThreeByThree() {
        Matrix matrix = new Matrix(new double[][]{{3.0, -2.0, 4.0, 19.0}, {2.0, 7.0, 3.0, 5.0}, {4.0, 3.0, -5.0, -1.0}});
        matrix.printRREF();

        assertArrayEquals(new double[][]{{1, 0, 0, 3}, {0, 1, 0, -1}, {0, 0, 1, 2}}, matrix.getMatrix());
    }

    @Test
    void testFourByFour() {
        Matrix matrix = new Matrix(new double[][]{{1, 2, -3, 4, 12}, {2, 2, -2, 3, 10}, {0, 1, 1, 0, -1}, {1, -1, 1, -2, -4}});
        matrix.printRREF();

        assertArrayEquals(new double[][]{{1, 0, 0, 0, 1}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, -1}, {0, 0, 0, 1, 2}}, matrix.getMatrix());
    }

    @Test
    void testNoSolutions() {
        Matrix matrix = new Matrix(new double[][]{{1, 1, 1, 2}, {0, 1, -3, 1}, {2, 1, 5, 0}});
        matrix.printRREF();
        String output = outputContent.toString();
        assertTrue(output.contains("The system has no solution."), "Output should indicate no solution");
    }

    @Test
    void testInfiniteSolutions() {
        Matrix matrix = new Matrix(new double[][]{{-3, -5, 36, 10}, {-1, 0, 7, 5}, {1, 1, -10, -4}});
        matrix.printRREF();
        String output = outputContent.toString();
        assertTrue(output.contains("The system has infinitely many solutions."), "Output should indicate infinite solutions");
    }
}