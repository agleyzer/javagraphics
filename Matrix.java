public class Matrix {
    private final int rows;
    private final int columns;
    private final double[][] data;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new double[rows][columns];
    }

    public Matrix(double [][]matrix) {
        this.rows = matrix.length;
        this.columns = matrix[0].length;
        this.data = matrix;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double getValue(int row, int column) {
        return data[row][column];
    }

    public void setValue(int row, int column, double value) {
        data[row][column] = value;
    }

    public Matrix multiply(Matrix other) {
        if (columns != other.rows) {
            throw new IllegalArgumentException("Matrix dimensions are not compatible for multiplication.");
        }

        Matrix result = new Matrix(rows, other.columns);
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                double sum = 0;
                for (int k = 0; k < columns; k++) {
                    sum += data[i][k] * other.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }

        return result;
    }

    // Other utility methods like toString, identity matrix creation, etc. can be added here.
    public void print() {	
	for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }
}
