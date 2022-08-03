package juego;
import java.util.Random;
import java.util.random.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
@SuppressWarnings("unused")

public class Kyojin {
	public boolean choco = true;
	private double x;
	private double y;
	private double velocidad;
	private double angulo;
	private double ancho;
	private double alto;
	private double radio;
	//public double mikasax;
	//public double mikasay;
	
	Clip clip;
	String explosion;
	public int kyojinTiempo = 0;
	Image img2;
	
	public Kyojin(double x, double y, double velocidad, double angulo, double radio) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.radio = radio;
		ancho=40;
		alto=60;
		explosion=".//explosion.wav";
		clip= Herramientas.cargarSonido(explosion);
		img2 = Herramientas.cargarImagen("kyojin.png");
	}
	
	



	public void dibujar(Entorno entorno) {
		//entorno.dibujarRectangulo(x, y, 32, 82, 0, Color.RED);
		entorno.dibujarCirculo(x, y, radio, Color.TRANSLUCENT);
		entorno.dibujarImagen(img2, x, y, 0, 0.10);
	}
	
	public void cargarSonido(String explosion)  {
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
	public void cambiarAngulo(double x2, double y2){
		//this.angulo = Math.atan2(y2 - this.y, x2 - this.x);
		this.angulo=Math.PI*Math.atan2(y2-this.y, x2-this.x);


	}

	public void mover() {
		//x += velocidad * Math.sin(angulo);
		//y += velocidad * Math.cos(angulo);

		
		x += Math.cos(angulo)*1.3;
		y += Math.sin(angulo)*1.3;

	} 
	
	
	//FIXME
	public boolean chocasteCon(Entorno entorno) {
		return x <= radio || y <= radio || x <= entorno.ancho() - radio || y <= entorno.alto() - radio;	
		

	}
	
	
	
	public void cambiarTrayectoria() {
		angulo += Herramientas.radianes(270);
		
	}
	
	public void colisionDeKyojines(Kyojin[] kyojines,int indice) {
	     for (int i = 0; i < kyojines.length; i++) {
	      if (kyojines[i]!=null && indice != i && this.x < (kyojines[i].getX() + kyojines[i].getAncho() / 2 + ancho)
	        && this.x > (kyojines[i].getX() - kyojines[i].getAncho() / 2 - ancho)
	        && this.y < (kyojines[i].getY() + kyojines[i].getAlto() / 2 + alto)
	        && this.y > (kyojines[i].getY() - kyojines[i].getAlto() / 2 - alto)) {
	       angulo += Herramientas.radianes(270)*Math.PI;
	       choco = false;
	     
	      }
	     }
	    }		
	
	public void acelerar() {
		
		x += Math.cos(angulo)*0.8;
		y += Math.sin(angulo)*0.8;
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


	public double getVelocidad() {
		return velocidad;
	}


	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}


	public double getAngulo() {
		return angulo;
	}


	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}


	public int getRadio() {
		return radio;
	}


	public void setRadio(int radio) {
		this.radio = radio;
	}
	public double getAncho() {
		return ancho;
	}


	public double getAlto() {
		return alto;
	}
	

}