package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

@SuppressWarnings("unused")
public class Mikasa {

	// Variables de instancia
	double x;
	double y;
	double angulo;
	Image img1;
	Image img2;
	Image img3;
	Image img4;
	boolean giro;
	boolean transformada;
	// private int radio;
	boolean seTransformo = false;
	
	public Mikasa(double x, double y,double orientacion, boolean transformada) 
	{
		this.x = x;
		this.y = y;
		this.angulo=orientacion;
		this.transformada= transformada;
		giro = false;
		img1 = Herramientas.cargarImagen("mikasa.png");
		img2 = Herramientas.cargarImagen("m2.png");
		img3 = Herramientas.cargarImagen("kyojin2.png");
		img4 = Herramientas.cargarImagen("kyojin2.1.png");
	}
	
	public void dibujar(Entorno entorno){
	
		// el numero es el tamaño de la imagen 
					
			if(giro==true) { 
				if(seTransformo == false) {
												
					entorno.dibujarImagen(img2, x, y, angulo, 0.10);
					
				}
				else{
					
					entorno.dibujarImagen(img3, x, y, angulo, 0.35);

				}
			}
			if(giro==false) {
				if(seTransformo == false) {
					entorno.dibujarImagen(img1, x, y, angulo, 0.10);
				}
				else {
					entorno.dibujarImagen(img4, x, y, angulo, 0.35);
				}
			}
	}

	public void girar(double modificador) 
	{
		this.angulo = angulo + modificador;
		if(angulo < 0) {
			angulo +=2*Math.PI;
		}
        if(angulo >2* Math.PI) {
        	angulo -= 2*Math.PI;
        }
	}
			
	
	
	public void moverAdelante() {
	
		// el numero es la velocidad de movimiento
		
			this.x += Math.cos(this.angulo)*5;
			this.y += Math.sin(this.angulo)*5;
			if(this.x > 900) {
				this.x=-100;
			}
			if(this.x < -100) {
				this.x=900;
			}
			if(this.y > 650) {
				this.y=-50;
			}
			if(this.y < -50) {
				this.y=650;
			}
	}
	
	public void moverAtras() {
		
			// el numero es la velocidad de movimiento
			
			this.x += -Math.cos(this.angulo)*5;
			this.y += -Math.sin(this.angulo)*5;
			
			if(this.x > 900) {
				this.x=-100;
			}
			if(this.x < -100) {
				this.x=900;
			}
			if(this.y > 650) {
				this.y=-50;
			}
			if(this.y < -50) {
				this.y=650;
			}
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

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	} 
	

	


}


