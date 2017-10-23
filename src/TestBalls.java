
import gui.*;

import java.awt.Color;
import java.awt.Point;

public class TestBalls {
	public static void main(String[] args) {
		Point a = new Point(0, 0);
		Point b = new Point(0, 1);
		Point c = new Point(0, 2);
		Point[] tab = {a, b, c};
		Balls ballons = new Balls(tab);
		System.out.println(ballons.toString());
	}
}
