package ecdh;

public class EllipticCurvePoint {
	int x, y;
	int a,b,p;
	public EllipticCurvePoint(EllipticCurve curve) { 
		a = curve.a;
		b = curve.b;
		p = curve.p;
	}

	public EllipticCurvePoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(EllipticCurvePoint other) {
		return this.x == other.x && this.y == other.y;
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}
}
