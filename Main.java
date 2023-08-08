import java.awt.*;
import java.awt.event.*;

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
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int sideLength = 100;

        int x = centerX - sideLength / 2;
        int y = centerY - sideLength / 2;

        g.setColor(Color.BLACK);
        g.drawRect(x, y, sideLength, sideLength);
    }
}
