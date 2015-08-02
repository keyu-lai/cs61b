class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		In fileIn = new In(filename);
		int num = fileIn.readInt();
		double radius = fileIn.readDouble();
		Planet[] planets = new Planet[num];
		for (int i = 0; i < num ; i++) {
			planets[i] = getPlanet(fileIn);
		}

		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for(Planet p: planets) {
			StdDraw.picture(p.x, p.y, "images/"+p.img);
		}

		for (double t = 0; t <= T; t += dt) {
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(Planet p: planets) {
				p.setNetForce(planets);
				p.update(dt);
				StdDraw.picture(p.x, p.y, "images/"+p.img);
			}
			StdDraw.show(10);
		}

		//StdAudio.play("audio/2001.mid");
 	}

	public static Planet getPlanet(In file) {
		return new Planet(file.readDouble(), file.readDouble(), file.readDouble(), //
			file.readDouble(), file.readDouble(), file.readString());
	}
}