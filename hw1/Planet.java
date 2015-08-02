class Planet {
	public double x, y, xVelocity, yVelocity, mass;
	public double xNetForce, yNetForce;
	public double xAccel, yAccel;
	public String img;

	public Planet(double a, double b, double av, double bv, double m, String i) {
		x = a;
		y = b;
		xVelocity = av;
		yVelocity = bv;
		mass = m;
		img = i;
	}

	private double square(double x) {
		return x * x;
	}

	public double calcDistance(Planet p) {
		return Math.sqrt(square(x - p.x) + square(y - p.y));
	}

	public double calcPairwiseForce(Planet p) {
		return 6.67*Math.pow(10, -11) * mass * p.mass / square(calcDistance(p));
	}

	public double calcPairwiseForceX(Planet p) {
		return calcPairwiseForce(p)* Math.abs(x-p.x) / calcDistance(p);
	}

	public double calcPairwiseForceY(Planet p) {
		return calcPairwiseForce(p)* Math.abs(y-p.y) / calcDistance(p);
	}

	public void setNetForce(Planet[] p) {
		xNetForce = 0;
		yNetForce = 0;
		for(Planet item: p) {
			if(item != this) {
				xNetForce += calcPairwiseForceX(item);
				yNetForce += calcPairwiseForceY(item);
			}
		}
	}

	public void update(double dt) {
		xAccel = xNetForce/mass;
		yAccel = yNetForce/mass;
		xVelocity += xAccel*dt;
		yVelocity += yAccel*dt;
		x += xVelocity*dt;
		y += yVelocity*dt;
	}

	public void draw() {
		StdDraw.picture(x, y, img);
	}
}