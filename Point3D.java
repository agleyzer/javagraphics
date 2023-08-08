public class Point3D {
    public final Matrix matrix;
    
    public Point3D(double x, double y, double z) {
	this.matrix = new Matrix(new double[][]{
		{x},
		{y},
		{z},
        });
    }
    
    public Point3D(Matrix matrix) {
	this.matrix = matrix;
    }
    
    public double getX() {
	return this.matrix.getValue(0, 0);
    }

    public double getY() {
	return this.matrix.getValue(1, 0);
    }
    
    public double getZ() {
	return this.matrix.getValue(2, 0);
    }
} 
