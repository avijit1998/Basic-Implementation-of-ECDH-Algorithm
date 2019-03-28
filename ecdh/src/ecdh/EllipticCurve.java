package ecdh;

import java.util.Scanner;

public class EllipticCurve {
	// Field is Z_p
	int a, b, p;
	
	public EllipticCurve() {
//		 y^2 = x^3 + ax + b
//		to check 4*a^3 + 27*b^2 != 0
		Scanner s = new Scanner(System.in);
		do {
		System.out.println("Enter the curve parameters");
		a = s.nextInt();
		b = s.nextInt();
		System.out.println("Enter the field parameter");
		p = s.nextInt();
		}while(((4*a*a*a) + (27*b*b)) == 0);
		
	}
	
	public EllipticCurvePoint addNTimes(EllipticCurvePoint p1, EllipticCurvePoint p2, int n) {
		EllipticCurvePoint result = p1;
		while(n > 0) {
			result = add(result, p2);
			n--;
		}
		return result;
	}
	
	public EllipticCurvePoint add(EllipticCurvePoint p1, EllipticCurvePoint p2) {
//		System.out.println("Adding " + p1 + " and " + p2);
		if(p1.equals(p2)) {
			return doublePoint(p1);
		}else if(p1.x == p2.x) {
			return new EllipticCurvePoint(0,0); // vertical line
		}else{
			return distinctAdd(p1, p2);
		}
	}
	
	public EllipticCurvePoint doublePoint(EllipticCurvePoint pt) {
		if(pt.y == 0) return pt;
		int top = mod((3*pt.x*pt.x+a));
		int bottom = mod(2*pt.y);
		int s = mod(top * inverse(bottom)); // 0
		int x = mod(s*s-2*pt.x); 
		int y = mod(s*(pt.x-x)-pt.y);
//		System.out.println("s: " + s + " x: " + x + " y: " + y);
		return new EllipticCurvePoint(x,y);
	}

	public EllipticCurvePoint distinctAdd(EllipticCurvePoint p1, EllipticCurvePoint p2) {
		int top = mod(p1.y-p2.y);
		int bottom = mod(p1.x-p2.x);
		int s = mod(top * inverse(bottom));
		int x = mod((s*s-p1.x-p2.x));
		int y = mod((s*(p1.x-x) - p1.y));
//		System.out.println("s: " + s + " x: " + x + " y: " + y);
		return new EllipticCurvePoint(x,y);
	}
	
	private int mod(int num) {
		return Math.floorMod(num, p);
	}
	
	private int inverse(int num) {
		for(int x = 1; x < p; x++) {
			if((num*x) % p == 1) {
				return x;
			}
		}
		return num;
	}
	public int retprime() {
		return p;
	}
	
	public int retParameterA() {
		return a;
	}
	
	public int retParameterB() {
		return b;
	}
	
	public String toString() {
		return "a: " + a + ", b: " + b + ", p: " + p;
	}
}
