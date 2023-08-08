import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

class Main {
    public static void main(String[] args) {
	Frame frame = new Frame("Square Drawing");
	frame.setSize(400, 400);
        
	frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
	
	frame.add(new SquareCanvas());
	frame.setVisible(true);
    }
}

class SquareCanvas extends Canvas {
    private Point3D[] points = new Point3D[]{
	new Point3D(-1.0, -1.0, 1.0),
	new Point3D(1.0, -1.0, 1.0),
	new Point3D(1.0,  1.0, 1.0),
	new Point3D(-1.0, 1.0, 1.0),
	new Point3D(-1.0, -1.0, -1.0),
	new Point3D(1.0, -1.0, -1.0),
	new Point3D(1.0, 1.0, -1.0),
	new Point3D(-1.0, 1.0, -1.0),
    };

    private Matrix projectionMatrix =
	new Matrix(new double[][]{
		{1.0, 0.0, 0.0},
		{0.0, 1.0, 0.0},
	});

    private Point2D[] projectionPoints = new Point2D[]{
	new Point2D(0, 0),
	new Point2D(0, 0),
	new Point2D(0, 0),
	new Point2D(0, 0),
	new Point2D(0, 0),
	new Point2D(0, 0),
	new Point2D(0, 0),
	new Point2D(0, 0),
    };

    public void connectPoints(Graphics g, int i, int j) {
        g.setColor(Color.BLACK);
	g.drawLine((int)projectionPoints[i].getX(),
		   (int)projectionPoints[i].getY(),
		   (int)projectionPoints[j].getX(),
		   (int)projectionPoints[j].getY());
    }

    private double angle = 0;

    private double scale = 100;
    
    @Override
    public void paint(Graphics g) {
	super.paint(g);
	
	Matrix rotationZ = new Matrix(new double[][]{
		{Math.cos(angle), -Math.sin(angle), 0},
		{Math.sin(angle), Math.cos(angle), 0},
		{0, 0, 1},
	    });

	Matrix rotationY = new Matrix(new double[][]{
		{Math.cos(angle), 0, Math.sin(angle)},
		{0, 1, 0},
		{-Math.sin(angle), 0, Math.cos(angle)},
	    });

	Matrix rotationX = new Matrix(new double[][]{
		{1, 0, 0},
		{0, Math.cos(angle), -Math.sin(angle)},
		{0, Math.sin(angle), Math.cos(angle)},
	    });

	for (int i = 0; i < points.length; i++) {	   
	    Matrix p = rotationZ.multiply(points[i].matrix);
	    p = rotationY.multiply(p);
	    p = rotationX.multiply(p);
	    p = projectionMatrix.multiply(p);
	    
	    int x = (int)(p.getValue(0, 0) * scale) + getWidth()/2;  
	    int y = (int)(p.getValue(1, 0) * scale) + getHeight()/2;
	    projectionPoints[i] = new Point2D(x, y);

	    g.setColor(Color.RED);
	    g.fillOval(x-3, y-3, 6, 6);
	}

	for (int i = 0; i < 4; i++ ) {
	    connectPoints(g, i, (i+1) % 4);
	    connectPoints(g, i+4, ((i+1) % 4) + 4);
	    connectPoints(g, i, (i+4));
	}
    }
}
