package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import javax.management.timer.Timer;
import entorno.Board;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

@SuppressWarnings("unused")
public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	
	private Entorno entorno;
	private Mikasa mikasa;
	Fondo fondo;
	Gameover gameover;
	Ganaste ganaste;
	Kyojin clip3;
	Kyojin[] kyojines;
	Misil misil;
	Obstaculos [] casas;
	Pocion pocion ;
	Vida vida;

	int tiempo = 3570;
	int segundos = 0;
	int cantSegundos=0;
	int puntaje = 0;
	int cantKyojines=0;
	int cantPociones = 0;
	//int tiempoColision = 0;
	int total = 0;
	int cantVidas = 3;

	
	// Variables y métodos propios de cada grupo
	// ...

	Juego() 
	{
		// Inicializa el objeto entorno
		
		this.entorno = new Entorno(this, "Attack on Titan, Final Season - Grupo ... - v1", 800, 600);

		// Inicializar lo que haga falta para el juego

		// Crea mikasa
		
		mikasa = new Mikasa(400, 300, 0, false);

		// Crea el fondo
		
		fondo = new Fondo(400, 300);
		// Crea el gameover
		
		gameover = new Gameover(400, 300);
		
		ganaste = new Ganaste(400, 300);
		
		// Crea el array de kyojines sin superponerse con los obstaculos y con Mikasa
		
		kyojines= new Kyojin[6];
		for(int i = 0 ; i <kyojines.length; i++ ) {
			kyojines[i]= new Kyojin(Math.random()*entorno.ancho(),Math.random()*entorno.alto(),1,0,50);
			
			while((kyojines[i].getX() > 150 && kyojines[i].getX() < 650) || (kyojines[i].getY() > 200 && kyojines[i].getY() < 400)) {
				
				kyojines[i]= new Kyojin(Math.random()*entorno.ancho(),Math.random()*entorno.alto(),1,0,50);
			
			}
		
		}
	
		// Crea la vida extra
		
		vida = new Vida(800,600);
		
		//Crea las pociones 
		
		pocion = new Pocion(550,350);
		

		
		
		//Crea el array de casas
		
		casas= new Obstaculos[6];
		casas[0]=new Obstaculos(entorno.ancho()/4, 150);
		casas[1]= new Obstaculos (entorno.ancho()/2, 150);
		casas[2]= new Obstaculos(entorno.ancho()/1.3,150);
		casas[3]= new Obstaculos(entorno.ancho()/4, 400);
		casas[4]=new Obstaculos(entorno.ancho()/2,400);
		casas[5]= new Obstaculos(entorno.ancho()/1.3,400);
		
		// Inicia el juego!
		
		this.entorno.iniciar();
	}

	// Detecta colisiones
	
	public boolean colision(double x1, double y1, double x2, double y2, double dist) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) < dist * dist;
	}
	
		
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{ 
		if ((cantVidas > 0 && cantVidas < 4) && cantKyojines < 10 && (tiempo < 3571 && tiempo >0))
		{
		// Carga las imagenes en pantalla 
		
		
		// Inicializa el tiempo
		
		tiempo --;
	
		// Calcula los segundos
		
		if(tiempo % 60 == 0) {
			segundos ++;
		}
		
		// Dibuja el fondo
		
		fondo.dibujarse(entorno);
		
		// Dibuja los textos 
		
		fondo.dibujarse(entorno);
		entorno.cambiarFont("gang of three",32, Color.black);
		entorno.escribirTexto("Kyojines eliminados:"+cantKyojines, 5, 580);
		entorno.escribirTexto("Puntaje:" + puntaje, 50, 50);
		entorno.escribirTexto("Cantidad de vidas: " + cantVidas, 250, 50);
		entorno.escribirTexto("Tiempo " + segundos , 620, 50);

		//Dibuja el misil y si salio de pantalla lo elimina
		
		if(misil != null){
			misil.dibujarse(entorno);
			misil.mover();
			if(misil.chocasteCon(entorno)) {
				misil=null;
			}
		}

		// Si mikasa colisionó con la pocion cambia el tamaño y elimina la pocion
		
		if ((pocion != null) && colision(mikasa.getX(), mikasa.getY(), pocion.x , pocion.y ,42)) {
			pocion.play();
			pocion = null;
			mikasa.seTransformo = true;
			cantSegundos = segundos;
			cantPociones ++;
		}
		
		// Si no colisiono contra ningun kyojin a los 5 segundos le saca el poder de la pocion
		
		if (mikasa.seTransformo == true && (segundos - cantSegundos) == 5) {
			mikasa.seTransformo = false;
 		}

		// Dibuja las pociones y verifica que no se superponga con un obstaculo
		
		
		if (pocion !=null && cantPociones < 3) {
			while(pocion.x > 150 && pocion.y < 650) {
				pocion = new Pocion(Math.random()*550,Math.random()*350);
			}
			pocion.dibujarse(entorno);
			
		}
		else {
			if(segundos % 10 == 0 && cantPociones < 3) {
				
				pocion = new Pocion(Math.random()*550,Math.random()*350);
				while(pocion.x > 150 && pocion.y < 650) {
					pocion = new Pocion(Math.random()*550,Math.random()*350);
				}
				pocion.dibujarse(entorno);
			
			}
		}
		
		// Dibuja la vida extra y verifica que no se superponga con un obstaculo
		
		
		if (vida !=null && segundos > 15 && cantVidas < 3) {
			while(vida.x > 150 && vida.y < 650) {
				vida = new Vida(Math.random()*550,Math.random()*350);
			}
			vida.dibujarse(entorno);
			
		}
		
		// Si mikasa colisionó con la vida extra aumenta las vidas 
		
		if ((vida != null) && colision(mikasa.getX(), mikasa.getY(), vida.x , vida.y ,42)) {
			vida.play();
			vida = null;
			cantVidas ++;
		}

		// Dibuja a mikasa
		
		if (mikasa != null) {
			mikasa.dibujar(entorno);
		}
		
		// Dibuja los kyojines 
		
		for(int i = 0 ; i <kyojines.length; i++ ) {
			
			if(kyojines[i] != null)	{
				kyojines[i].mover();
				kyojines[i].cambiarAngulo(mikasa.getX(),mikasa.getY());
				kyojines[i].dibujar(entorno);
				
				if(kyojines[i] != null && kyojines[i].chocasteCon(entorno)) {
	                kyojines[i].cambiarTrayectoria();
              }
					
			}
		}
					
		
		// Si los kyojines colisionan con el misil los elimina y reproduce sonido
		 for (int i= 0 ; i < kyojines.length ; i ++) {
			if(misil != null && kyojines[i] !=null) {
				if (colision(kyojines[i].getX(), kyojines[i].getY(), misil.getX(), misil.getY(),32)) {
					kyojines[i].play();
					kyojines[i] = null;
					misil = null;
					cantKyojines++;					
					puntaje = puntaje + 100;
					
					if (cantKyojines < 5 )
						for (int j =0; j< kyojines.length; j++) {
							if(kyojines[j]==null)
								kyojines[j]= new Kyojin(Math.random()*entorno.ancho(),Math.random()*entorno.alto(),1,0,50);
								kyojines[j].cambiarAngulo(mikasa.getX(), mikasa.getY());
								kyojines[j].dibujar(entorno);
								if(kyojines[j] != null && kyojines[j].chocasteCon(entorno)) {
					                kyojines[j].cambiarTrayectoria();
				              }
						}
				}
			}
		 }	
		 

		
		// Si los kyojines colisionan contra mikasa transformada los elimina
		// reproduce sonido y asigna puntaje
		// Si esta sin transformar saca una vida
 
		for (int i = 0 ; i < kyojines.length ; i++) {	
			if (kyojines[i] !=null && mikasa != null) {
				if((colision(kyojines[i].getX(), kyojines[i].getY(), mikasa.getX(), mikasa.getY(),32))) {
					if(mikasa.seTransformo == false) {
						cantVidas = cantVidas - 1;
						kyojines[i] = null;
						cantKyojines++;
						
					}
					if(mikasa.seTransformo == true) {
						mikasa.seTransformo = false;
						kyojines[i].play();
						kyojines[i] = null;
						puntaje = puntaje + 50;
						cantKyojines++;
					}
					if (cantKyojines < 5)
						for (int j =0; j< kyojines.length; j++) {
							if(kyojines[j]==null)
								kyojines[j]= new Kyojin(Math.random()*entorno.ancho(),Math.random()*entorno.alto(),1,0,50);
								kyojines[j].cambiarAngulo(mikasa.getX(), mikasa.getY());
								kyojines[j].dibujar(entorno);
								if(kyojines[j] != null && kyojines[j].chocasteCon(entorno)) {
					                kyojines[j].cambiarTrayectoria();
				              }
						}
					}
		
				}
			}
		
	
			// Hace rebotar a los kyojines contra las casas
				for (int i =0 ; i < kyojines.length ; i++) {	
					for(int j = 0; j < casas.length;j++) {
						if(kyojines[i]!=null && kyojines[j] != null) {
							if (colision(kyojines[i].getX(), kyojines[i].getY(),casas[j].getX(),casas[j].getY(),62))
								kyojines[i].cambiarTrayectoria();
							}
						}
					}
									
				//Colision entre kyojines
				
		        for (int j =0 ; j< kyojines.length ; j++) {  
		          for(int i =0; i< kyojines.length; i++) {		        	  
		              if(kyojines[j]!=null) {
		                kyojines[j].colisionDeKyojines(kyojines,i);
						if(kyojines[j] != null && kyojines[j].chocasteCon(entorno)) {
			                kyojines[j].cambiarTrayectoria();
			                
		              }

		              }
		            }
		        }

		
		
		// Si el misil le pegó a una casa la prende fuego y reroduce sonido
		
			for(int i = 0 ; i < casas.length ; i++) {
				if(casas[i] !=null){
					casas[i].dibujarse(entorno);
					if(misil != null ) {
						if (colision(casas[i].getX(), casas[i].getY(), misil.getX(), misil.getY(),72)) {
							casas[i].fuego=true;
							misil = null;
							casas[i].playExplosion();
							casas[i].playIncendio();
							puntaje = puntaje - 30;
						}
					}
				}
			} 
			
		// Mueve a mikasa de acuerdo a la tecla presionada
		

		if (entorno.estaPresionada(entorno.TECLA_DERECHA))
			mikasa.girar(Herramientas.radianes(1));
			/*if(mikasa.getAngulo()<3.8  && mikasa.getAngulo()>2.3)
			
				mikasa.setAngulo(0);*/

		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
			mikasa.girar(Herramientas.radianes(-1));
			/*if(mikasa.getAngulo()<3.8  && mikasa.getAngulo()>2.3)
				mikasa.setAngulo(0);*/
		
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
			mikasa.giro=false;
			
			//No deja que se pase de la pantalla con la tecla arriba presionada
						
			if (mikasa.x > (entorno.ancho()-20)) {
				if (mikasa.angulo < 1.5*Math.PI && mikasa.angulo > 0.5*Math.PI) {
					mikasa.moverAdelante();
				}
			}
			
			else if (mikasa.x < 20) {
				if ((mikasa.angulo < 0.5*Math.PI && mikasa.angulo > 0*Math.PI)||(mikasa.angulo < 2*Math.PI && mikasa.angulo > 1.5*Math.PI)) {
					mikasa.moverAdelante();
				}
			}
			else if (mikasa.y < 20) {
				if (mikasa.angulo < Math.PI && mikasa.angulo > 0*Math.PI) {
					mikasa.moverAdelante();
				}
			}
			
			else if (mikasa.y > (entorno.alto()-20)) {
				
				if ((mikasa.angulo < 1.5*Math.PI && mikasa.angulo > 1*Math.PI)||(mikasa.angulo >1.5*Math.PI && mikasa.angulo > 0.5*Math.PI ))  {
					mikasa.moverAdelante();
				}
			}
			else
				mikasa.moverAdelante();
		}
			
		if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
			mikasa.giro=true;
			
			//No deja que se pase de la pantalla con la tecla abajo presionada 				

			if (mikasa.x > (entorno.ancho()-20)) {
				if ((mikasa.angulo < 0.5*Math.PI && mikasa.angulo > 0*Math.PI)||(mikasa.angulo < 2*Math.PI && mikasa.angulo > 1.5*Math.PI)) {
					mikasa.moverAtras();
				}
			}
			else if (mikasa.x < 20) {
				if (mikasa.angulo < 1.5*Math.PI && mikasa.angulo > 0.5*Math.PI) {
					mikasa.moverAtras();
				}
			}
			
			else if (mikasa.y < 20) {
				if ((mikasa.angulo < 1.5*Math.PI && mikasa.angulo > 1*Math.PI)||(mikasa.angulo < 2*Math.PI && mikasa.angulo > 1.5* Math.PI)) {
					mikasa.moverAtras();
				}
			}
			
			else if (mikasa.y > (entorno.alto()-20)) {
				
				if (mikasa.angulo < 1*Math.PI && mikasa.angulo > 0*Math.PI)  {
					mikasa.moverAtras();
				}
			}
			
			else
				mikasa.moverAtras();
		}
	
		// Calcula colision de Mikasa con obstaculos 
		
			for(int i = 0 ; i < casas.length ; i++) {
				if (mikasa.giro==false && colision(casas[i].getX(), casas[i].getY(),mikasa.getX(), mikasa.getY(),  62))
					mikasa.moverAtras(); 
				if (mikasa.giro==true && colision(casas[i].getX(), casas[i].getY(),mikasa.getX(), mikasa.getY(),  62 ))
					mikasa.moverAdelante();
			}
	
		// Dispara misil y reproduce sonido
		
		if(entorno.sePresiono(entorno.TECLA_ESPACIO)) {
			if (misil == null){
				misil= new Misil(mikasa.getX(), mikasa.getY(), mikasa.getAngulo());
				misil.giro=mikasa.giro;
				misil.disparar(mikasa.getAngulo());
				misil.play();			}
		
	}
}
		else { 
			if (cantVidas == 0 || tiempo == 0) {
			gameover.play();
			gameover.dibujarse(entorno);
			entorno.cambiarFont("gang of three",62, Color.white);
			entorno.escribirTexto("GAME OVER" , 210, 150);
			entorno.cambiarFont("gang of three",30, Color.white);
			entorno.escribirTexto("Kyojines eliminados: "+cantKyojines, 240, 270);
			entorno.escribirTexto("Puntaje: " + puntaje, 330, 370);
			entorno.escribirTexto("Presione DELETE para salir" , 220, 470);
			entorno.escribirTexto("Presione ENTER para volver a jugar", 150,550);
			
			}
			
			if(cantKyojines == 10) {
			ganaste.play();
			ganaste.dibujarse(entorno);
			entorno.cambiarFont("gang of three",62, Color.white);
			entorno.escribirTexto("¡¡Felicitaciones!!" , 50, 150);
			entorno.cambiarFont("gang of three",30, Color.white);
			entorno.escribirTexto("Exterminaste a "+cantKyojines + " kyojines", 87, 270);
			entorno.escribirTexto("Hiciste: " + puntaje + " puntos", 87, 370);
			entorno.escribirTexto("Presione DELETE salir" , 87, 470);
			entorno.escribirTexto("Presione ENTER para volver a jugar", 150,550);
			}
			
		}
		if(entorno.estaPresionada(entorno.TECLA_DELETE)) {
			System.exit(0);
		
			
		}
		
		if(entorno.estaPresionada(entorno.TECLA_ENTER)) {
			System.exit(0);
		}
		
	}
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
		
		
	
	}
	
}

