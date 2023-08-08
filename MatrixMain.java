public class MatrixMain {
    public static void main(String[] args) {

        Matrix matrix1 = new Matrix(new double[][] {
		{1, 2, 3},
		{4, 5, 6},
	});

	Matrix matrix2 = new Matrix(new double[][]{
		{7, 8},
		{9, 10},
		{11, 12},
	});
	
        Matrix result = matrix1.multiply(matrix2);
	result.print();
    }
}
