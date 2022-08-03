package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {
	
	private double x;
	private double y;
	private Image imgFondo;
	double anguloFondo;
	
	public Fondo(double x, double y) {
		this.x = x;
		this.y = y;
		imgFondo = Herramientas.cargarImagen("fondo.jpg");
		anguloFondo=0;
	}
	public void dibujarse(Entorno entorno)
	{
		// El numero es el tamaño de la imagen
		entorno.dibujarImagen(imgFondo, this.x, this.y, this.anguloFondo, 0.8);
	}


	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	
}