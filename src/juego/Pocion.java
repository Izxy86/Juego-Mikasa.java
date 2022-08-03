package juego;

import java.awt.Image;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import entorno.Entorno;
import entorno.Herramientas;

public class Pocion {
	double x;
	double y; 
	Image img;
	String sonidoPocima;
	Clip clip;
	
	public Pocion(double x, double y) {
		this.x= x;
		this.y= y;
		img= Herramientas.cargarImagen("pocion.png");
		sonidoPocima = ".//pocima.wav";
		clip= Herramientas.cargarSonido(sonidoPocima);
		
	}
	
	public void dibujarse(Entorno entorno) {
			entorno.dibujarImagen(img, x, y, 0, 0.1);
	}
	
	public void cargarSonido(String sonidoPocima)  {
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
