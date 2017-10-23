
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

		System.out.println("Initialisation : ");
		System.out.println(ballons);
		ballons.translate(3, 2);

		System.out.println("Deplacement de 3, 2 : ");
		System.out.println(ballons);

		ballons.reInit();
		System.out.println("Retour à l'origine : ");
		System.out.println(ballons);


	}
}
