package juego;

import java.awt.Image;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import entorno.Entorno;
import entorno.Herramientas;

public class Ganaste {
	
	private double x;
	private double y;
	private Image imgFondo;
	double anguloGanaste;
	String sonidoGanaste;
	Clip clip;
	public Ganaste(double x, double y) {
		this.x = x;
		this.y = y;
		imgFondo = Herramientas.cargarImagen("ganaste.png");
		anguloGanaste=0;
		sonidoGanaste=".//ganaste.wav";
		clip= Herramientas.cargarSonido(sonidoGanaste);
	}
	public void dibujarse(Entorno entorno)
	{
		// El numero es el tamaño de la imagen
		entorno.dibujarImagen(imgFondo, this.x, this.y, this.anguloGanaste, 0.8);
	}


	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void cargarSonido(String sonidoMisil)  {
		try {
			this.clip= AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
	}
	
	public void play() {
		clip.start();
	}	
}
	
