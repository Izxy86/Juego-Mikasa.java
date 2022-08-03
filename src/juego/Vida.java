package juego;

import java.awt.Image;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import entorno.Entorno;
import entorno.Herramientas;

public class Vida {
	double x;
	double y; 
	Image img;
	String sonidoVida;
	Clip clip;
	String perdioVida;
	Clip clip2;
	
	public Vida(double x, double y) {
		this.x= x;
		this.y= y;
		img= Herramientas.cargarImagen("mikasa.png");
		sonidoVida = ".//vida.wav";
		clip= Herramientas.cargarSonido(sonidoVida);
		perdioVida= ".//perdioVida.wav";
		
		
	}
	
	public void dibujarse(Entorno entorno) {
			entorno.dibujarImagen(img, x, y, 0, 0.05);
	}
	
	public void cargarSonido(String sonidoVida)  {
		try {
			this.clip= AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
	}
	
	public void cargarSonido2(String perdioVida) {
		try {
			this.clip2= AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
	}
	
	public void play() {
		clip.start();
	}
	public void play2() {
		clip2.start();
	}
}

