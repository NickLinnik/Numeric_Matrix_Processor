package processor;

import static processor.Matrix.scanner;

public class Main {
    public static void main(String[] args) {
        label: while (true) {
            System.out.print("1. Add matrices\n" +
                    "2. Multiply matrix by a constant\n" +
                    "3. Multiply matrices\n" +
                    "4. Transpose matrix\n" +
                    "5. Calculate a determinant\n" +
                    "6. Inverse matrix\n" +
                    "0. Exit\n" +
                    "Your choice: ");
            try {
                switch (scanner.nextInt()) {
                    case 0:
                        break label;

                    case 1:
                        System.out.print("Enter size of first matrix: ");
                        int rows = scanner.nextInt();
                        int cols = scanner.nextInt();
                        System.out.println("Enter first matrix:");
                        var firstMatrix = new Matrix(rows, cols);
                        System.out.print("Enter size of second matrix: ");
                        rows = scanner.nextInt();
                        cols = scanner.nextInt();
                        System.out.println("Enter second matrix:");
                        var secondMatrix = new Matrix(rows, cols);
                        var resultMatrix = firstMatrix.add(secondMatrix);
                        System.out.println("The result is:");
                        resultMatrix.print();
                        break;

                    case 2:
                        System.out.print("Enter size of matrix: ");
                        rows = scanner.nextInt();
                        cols = scanner.nextInt();
                        System.out.println("Enter matrix:");
                        var matrix = new Matrix(rows, cols);
                        System.out.print("Enter constant:");
                        int num = scanner.nextInt();
                        resultMatrix = matrix.multiply(num);
                        System.out.println("The result is:");
                        resultMatrix.print();
                        break;

                    case 3:
                        System.out.print("Enter size of first matrix: ");
                        rows = scanner.nextInt();
                        cols = scanner.nextInt();
                        System.out.println("Enter first matrix:");
                        firstMatrix = new Matrix(rows, cols);
                        System.out.print("Enter size of second matrix: ");
                        rows = scanner.nextInt();
                        cols = scanner.nextInt();
                        System.out.println("Enter second matrix:");
                        secondMatrix = new Matrix(rows, cols);
                        resultMatrix = firstMatrix.multiply(secondMatrix);
                        System.out.println("The result is:");
                        resultMatrix.print();
                        break;

                    case 4:
                        System.out.print("\n 1.Main diagonal\n" +
                                "2. Side Diagonal\n" +
                                "3. Vertical line\n" +
                                "4. Horizontal line\n" +
                                "Your choice: ");
                        int choice = scanner.nextInt();
                        System.out.print("Enter matrix size: ");
                        rows = scanner.nextInt();
                        cols = scanner.nextInt();
                        System.out.println("Enter matrix:");
                        matrix = new Matrix(rows, cols);
                        System.out.println("The result is:");
                        switch (choice) {
                            case 1:
                                matrix.transposeAlongMainDiagonal().print();
                                break;

                            case 2:
                                matrix.transposeAlongSideDiagonal().print();
                                break;

                            case 3:
                                matrix.transposeAlongVerticalLine().print();
                                break;

                            case 4:
                                matrix.transposeAlongHorizontalLine().print();
                                break;
                        }
                        break;

                    case 5:
                        System.out.print("Enter size of matrix: ");
                        rows = scanner.nextInt();
                        cols = scanner.nextInt();
                        System.out.println("Enter matrix:");
                        matrix = new Matrix(rows, cols);
                        double det = matrix.getDeterminant();
                        System.out.println("The result is:\n" + det);
                        break;

                    case 6:
                        System.out.print("Enter matrix size: ");
                        rows = scanner.nextInt();
                        cols = scanner.nextInt();
                        System.out.println("Enter matrix:");
                        matrix = new Matrix(rows, cols);
                        resultMatrix = matrix.inverse();
                        System.out.println("The result is:");
                        resultMatrix.print(2);
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
