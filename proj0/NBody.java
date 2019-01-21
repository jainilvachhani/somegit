public class NBody{
	
	public static double readRadius(String fileName){
		In in = new In(fileName);
		int planets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	
	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int totalPlanets = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[totalPlanets];
		int i;
		for(i=0;i<totalPlanets;i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String file = in.readString();
			Planet p = new Planet(xxPos,yyPos,xxVel,yyVel,mass,file);
			planets[i] = p;
		}
		return planets;
	}
	
	public static void main(String args[]){
		StdDraw.enableDoubleBuffering();
		double time = 0;
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename); 	
		while(time<T){
			int i;
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(i=0;i<planets.length;i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(i=0;i<planets.length;i++){
				planets[i].update(dt,xForces[i],yForces[i]);			
			}
			StdDraw.setScale(-radius,radius);
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(i=0;i<planets.length;i++){
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}