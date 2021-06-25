package processor;

import java.util.Scanner;

public class Matrix {
    double[][] matrix;

    public static Scanner scanner = new Scanner(System.in);

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(int rows, int cols) {
        this.matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] = scanner.nextDouble();
            }
        }
    }

    public Matrix(Matrix matrixForCopy) {
        this.matrix = matrixForCopy.matrix.clone();
    }

    public boolean isAddable(Matrix secondMatrix) {
        if (this.matrix.length != secondMatrix.matrix.length) return false;
        for (int i = 0; i < this.matrix.length; i++) {
            if (this.matrix[i].length != secondMatrix.matrix[i].length) return false;
        }
        return true;
    }

    public boolean isMultipliable(Matrix secondMatrix) {
        return this.matrix[0].length == secondMatrix.matrix.length;
    }

    public Matrix add(Matrix secondMatrix) {
        if (!this.isAddable(secondMatrix)) throw new IllegalArgumentException("The operation cannot be performed.");
        Matrix result = new Matrix(this);
        for (int i = 0; i < result.matrix.length; i++) {
            for (int j = 0; j < result.matrix[i].length; j++) {
                result.matrix[i][j] += secondMatrix.matrix[i][j];
            }
        }
        return result;
    }

    public Matrix multiply(double multiplier) {
        Matrix result = new Matrix(this);
        for (int i = 0; i < result.matrix.length; i++) {
            for (int j = 0; j < result.matrix[i].length; j++) {
                result.matrix[i][j] *= multiplier;
            }
        }
        return result;
    }
    public Matrix multiply(Matrix secondMatrix) {
        if (!this.isMultipliable(secondMatrix)) throw new IllegalArgumentException("The operation cannot be performed.");
        Matrix result = new Matrix(new double[this.matrix.length][secondMatrix.matrix[0].length]);
        for (int i = 0; i < result.matrix.length; i++) {
            for (int j = 0; j < result.matrix[i].length; j++) {
                for (int k = 0; k < secondMatrix.matrix.length; k++) {
                    result.matrix[i][j] += this.matrix[i][k] * secondMatrix.matrix[k][j];
                }
            }
        }
        return result;
    }

    public Matrix transposeAlongMainDiagonal() {
        Matrix result = new Matrix(new double[this.matrix[0].length][this.matrix.length]);
        for (int i = 0; i < result.matrix.length; i++) {
            for (int j = 0; j < result.matrix[i].length; j++) {
                result.matrix[i][j] = this.matrix[j][i];
            }
        }
        return result;
    }

    public Matrix transposeAlongSideDiagonal() {
        Matrix result = new Matrix(new double[this.matrix[0].length][this.matrix.length]);
        for (int i = 0; i < result.matrix.length; i++) {
            for (int j = 0; j < result.matrix[i].length; j++) {
                result.matrix[result.matrix.length - 1 - i][j] = this.matrix[result.matrix[i].length - 1 - j][i];
            }
        }
        return result;
    }

    public Matrix transposeAlongVerticalLine() {
        Matrix result = new Matrix(new double[this.matrix[0].length][this.matrix.length]);
        for (int i = 0; i < result.matrix.length; i++) {
            for (int j = 0; j < result.matrix[i].length; j++) {
                result.matrix[i][j] = this.matrix[i][result.matrix[i].length - 1 - j];
            }
        }
        return result;
    }

    public Matrix transposeAlongHorizontalLine() {
        Matrix result = new Matrix(new double[this.matrix[0].length][this.matrix.length]);
        for (int i = 0; i < result.matrix.length; i++) {
            //noinspection ManualArrayCopy
            for (int j = 0; j < result.matrix[i].length; j++) {
                result.matrix[i][j] = this.matrix[result.matrix.length - 1 - i][j];
            }
        }
        return result;
    }

    public Matrix getMinor(int row, int col) {
        var temp = new Matrix(new double[matrix.length - 1][matrix.length - 1]);
        int tempRowIndex = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == row) continue;
            int tempColIndex = 0;
            for (int j = 0; j < matrix[row].length; j++) {
                if (j == col) continue;
                temp.matrix[tempRowIndex][tempColIndex] = this.matrix[i][j];
                tempColIndex++;
            }
            tempRowIndex++;
        }
        return temp;
    }

    public double getDeterminant() {
        if (matrix.length != matrix[0].length) throw new IllegalArgumentException("Only square matrices have the determinant.");
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        double result = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            result += matrix[0][i] * (i % 2 == 1 ? -1 : 1) * this.getMinor(0, i).getDeterminant();
        }
        return result;
    }

    public Matrix inverse() {
        double det = this.getDeterminant();
        if (det == 0) throw new NullPointerException("This matrix doesn't have an inverse.");
        Matrix cofactors = new Matrix(new double[matrix.length][matrix.length]);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                cofactors.matrix[i][j] = ((i + j) % 2 == 1 ? -1 : 1) * this.getMinor(i, j).getDeterminant();
            }
        }
        return cofactors.transposeAlongMainDiagonal().multiply(1 / det);
    }

    public void print() {
        for (double[] row : matrix) {
            for (double col : row) {
                System.out.print(col - (int) col == 0 ? (int) col + " " : col + " ");
            }
            System.out.println();
        }
    }

    public void print(int numsAfterPoint) {
        for (double[] row : matrix) {
            for (double col : row) {
                System.out.printf("%." + numsAfterPoint + "f ", col - (int) col == 0 ? (int) col : col);
            }
            System.out.println();
        }
    }
}