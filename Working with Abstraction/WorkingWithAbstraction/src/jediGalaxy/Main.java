package jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        fillField(rows, cols, matrix);

        String command = scanner.nextLine();
        long sum = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] jediPositions = readPositions(command);
            int[] evilPositions = readPositions(scanner.nextLine());
            int rowEvil = evilPositions[0];
            int colEvil = evilPositions[1];

            moveEvil(rowEvil, colEvil, matrix);

            int rowJedi = jediPositions[0];
            int colJedi = jediPositions[1];

            sum = getColectedStars(rowJedi, colJedi, matrix, sum);

            command = scanner.nextLine();
        }

        System.out.println(sum);


    }

    private static long getColectedStars(int rowJedi, int colJedi, int[][] matrix, long sum) {
        while (rowJedi >= 0 && colJedi < matrix[1].length) {
            if (rowJedi >= 0 && rowJedi < matrix.length && colJedi >= 0 && colJedi < matrix[0].length) {
                sum += matrix[rowJedi][colJedi];
            }

            colJedi++;
            rowJedi--;
        }
        return sum;
    }

    private static void moveEvil(int rowEvil, int colEvil, int[][] matrix) {
        while (rowEvil >= 0 && colEvil >= 0) {
            if (rowEvil < matrix.length && colEvil < matrix[0].length) {
                matrix[rowEvil][colEvil] = 0;
            }
            rowEvil--;
            colEvil--;
        }
    }

    private static int[] readPositions(String command) {
        return Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static void fillField(int rows, int cols, int[][] matrix) {
        int value = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = value++;
            }
        }
    }
}
