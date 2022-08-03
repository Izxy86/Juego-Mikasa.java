package juego;

import java.awt.Color;
import java.awt.Image;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import entorno.Entorno;
import entorno.Herramientas;
import juego.Mikasa;

@SuppressWarnings("unused")
public class Misil {
	//Variables de instancias
	private Mikasa mikasa;
	double x;
	double y;
	double angulo;
	boolean giro;
	Image imgMisil;
	Image imgMisil2;
	String sonidoMisil;
	Clip clip;
	private int radio;
	public Misil(double x, double y, double angulo) {
		this.x= x;
		this.y= y;
		this.angulo= angulo;
		giro= false;
		sonidoMisil=".//misil.wav";
		imgMisil2= Herramientas.cargarImagen("misil.png");
		imgMisil= Herramientas.cargarImagen("bomba.png");
		clip= Herramientas.cargarSonido(sonidoMisil);
	}
	
	public void dibujarse(Entorno entorno) {
		
		//entorno.dibujarRectangulo(x, y, 50, 50, 0, Color.red);
			if(giro)
				entorno.dibujarImagen(imgMisil,x, y,angulo, 0.1);
			else
				entorno.dibujarImagen(imgMisil2, x, y, angulo, 0.1);
			
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

	public void disparar(double angulo) {
	
		this.x += Math.cos(this.angulo)*50 ;
		this.y += Math.sin(this.angulo)*50;
		
	}		
	
	public void mover() {
		
		if(giro==false) {
			
			this.x += Math.cos(this.angulo)*5;
			this.y += Math.sin(this.angulo)*5;
			}
		
		if(giro==true) {
			this.x -= Math.cos(this.angulo)*3;
			this.y -= Math.sin(this.angulo)*3;
		}
		
		
	} 


		public double getX() {
		return x;
	}
		public boolean chocasteCon(Entorno entorno) {
			return x <= radio || y <= radio || x >= entorno.ancho() - radio || y >= entorno.alto() - radio;		
		}
	public double setX(double x) {
		return this.x = x;
	}

	public double getY() {
		return y;
	}

	public double setY(double y) {
		return this.y = y;
	}

	public double getAngulo() {
		return angulo;
	}

	public double setAngulo(double angulo) {
		return this.angulo = angulo;
	}

}
	
	


