package juego;

import java.awt.Image;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import entorno.Entorno;
import entorno.Herramientas;

public class Gameover {

		private double x;
		private double y;
		private Image imgGameover;
		double anguloGameover;
		String sonidoGameover;
		Clip clip;
		
		public Gameover(double x, double y) {
			this.x = x;
			this.y = y;
			imgGameover = Herramientas.cargarImagen("gameover.png");
			anguloGameover=0;
			sonidoGameover=".//gameover.wav";
			clip= Herramientas.cargarSonido(sonidoGameover);
		}
		public void dibujarse(Entorno entorno)
		{
			// El numero es el tamaño de la imagen
			entorno.dibujarImagen(imgGameover, this.x, this.y, this.anguloGameover, 0.8);
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
