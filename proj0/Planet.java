import java.lang.*;

public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	
	public double calcDistance(Planet p){
		double dx2 = (this.xxPos - p.xxPos)*(this.xxPos - p.xxPos);
		double dy2 = (this.yyPos - p.yyPos)*(this.yyPos - p.yyPos);
		return Math.sqrt(dx2 + dy2);
	}
	
	public double calcForceExertedBy(Planet p){
		double G = 6.67 *Math.pow(10,-11);
		double r = this.calcDistance(p);
		double force = (G*(this.mass)*(p.mass))/(r*r);
		return force;
	}
	
	public double calcForceExertedByX(Planet p){
		if(this.equals(p)){
			return 0;
		}
		double dx = -(this.xxPos - p.xxPos);
		double force = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double forceX = (force*dx)/r;
		return forceX;
	}
	
	public double calcForceExertedByY(Planet p){
		if(this.equals(p)){
			return 0;
		}
		double dy = -(this.yyPos - p.yyPos);
		double force = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double forceY = (force*dy)/r;
		return forceY;
	}
	
	public double calcNetForceExertedByX(Planet p[]){
		int i;
		double totalForceX = 0;
		for(i=0;i<p.length;i++){
			totalForceX += this.calcForceExertedByX(p[i]);
		}
		return totalForceX;
	}
	
	public double calcNetForceExertedByY(Planet p[]){
		int i;
		double totalForceY = 0;
		for(i=0;i<p.length;i++){	
			totalForceY += this.calcForceExertedByY(p[i]);
		}
		return totalForceY;
	}
	
	public void update(double dt, double forceX, double forceY){
		double accX = forceX/this.mass;
		double accY = forceY/this.mass;
		this.xxVel = xxVel + (dt*accX);
		this.yyVel = yyVel + (dt*accY);
		this.xxPos = xxPos + (dt*xxVel);
		this.yyPos = yyPos + (dt*yyVel);
	}
	
	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
	}
}