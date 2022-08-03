package juego;

import java.awt.Color;
import java.awt.Image;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import entorno.Entorno;
import entorno.Herramientas;

@SuppressWarnings("unused")
public class Obstaculos {
	double x;
	double y; 
	double ancho;
	double alto;
	Image imgCasas; 
	Image imgCasaFuego;
	boolean fuego;
	Clip clip;
	Clip clip2;
	String explosion;
	String incendio;
	
	public Obstaculos( double x, double y) {
		this.x = x;
		this.y = y;
		ancho=120;
		alto=120;
		imgCasas= Herramientas.cargarImagen("Casa.png");
		imgCasaFuego= Herramientas.cargarImagen("casa-fuego.png");
		fuego=false;
		explosion=".//explosion.wav";
		clip= Herramientas.cargarSonido(explosion);
		incendio= ".//fuego.wav";
		clip2=Herramientas.cargarSonido(incendio);
	} 
	
	public void dibujarse(Entorno entorno) {

		if (fuego==false)
			entorno.dibujarImagen(imgCasas, x, y, 0, 0.20);
		if(fuego==true)
			entorno.dibujarImagen(imgCasaFuego, x, y, 0, 0.20);
		
	}
	
	public void cargarSonido(String explosion)  {
		try {
			this.clip= AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
	}
	
	public void playExplosion() {
		clip.start();
	}
	
	public void cargarSonido2(String incendio)  {
		try {
			this.clip2= AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
	}
	
	public void playIncendio() {
		clip2.start();
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
