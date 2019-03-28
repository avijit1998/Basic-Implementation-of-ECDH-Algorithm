package ecdh;

import java.util.Scanner;

public class Main {
	public static boolean check(int x, int y, int p, int a, int b) {
		int s,t;
		s = x*x*x + a*x + b;
		s = s % p;
		t = (y*y);
		t = t % p;
		if(s == t)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		int x,y;
		System.out.println();
		// Generating Public Info
		EllipticCurve curve = new EllipticCurve();
		int p = curve.retprime();
		int a = curve.retParameterA();
		int b = curve.retParameterB();
		System.out.println("Shared curve is: " + curve);
		x = 1 + (int)(Math.random() * p);
		y = 1 + (int)(Math.random() * p);
	
		while(check(x,y,p,a,b) == false) {
//			System.out.println("X-"+x+" "+"Y-"+y);
			x = 1 + (int)(Math.random() * p);
			y = 1 + (int)(Math.random() * p);
		}
		
		EllipticCurvePoint g = new EllipticCurvePoint(x,y);
		System.out.println("Generator is: " + g);

		System.out.println();
		// Choosing Private Keys
		Scanner s = new Scanner(System.in);
		System.out.print("Charlie, choose a private key >> ");
		int c = s.nextInt();
		System.out.print("Dave, choose a private key >> ");
		int d = s.nextInt();
//		System.out.println();

		System.out.println();
		// Calculating Resulting Public Keys
		EllipticCurvePoint cg = curve.addNTimes(g, g, c-1);
		EllipticCurvePoint dg = curve.addNTimes(g, g, d-1);
		System.out.println("Charlie Public Key: " + cg);
		System.out.println("Dave Public Key: " + dg);
//		System.out.println();

		System.out.println();
		// Calculating Shared Private Key from other public key and own private key
		EllipticCurvePoint dcg = curve.addNTimes(cg, g, d);
		EllipticCurvePoint cdg = curve.addNTimes(dg, g, c);
		System.out.println("Dave's shared key: " + dcg);
		System.out.println("Charlie's shared key: " + cdg);
		System.out.println();
		
		s.close();
	}

}
