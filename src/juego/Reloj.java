package juego;



public class Reloj {
	//Variables de instancias
	int minutos, segundos; 
	
	
	public Reloj(int minutos, int segundos) {
		
		this.minutos= minutos;
		this.segundos= segundos;
	}  
	

	
	
	public void cronometro()  {
		while (!(minutos == 0 && segundos == 0)) {
		
			if(segundos==0) {
				if (minutos == 0) {
					minutos= 59;
					segundos= 59;
				}
				else if(minutos != 0) {
					minutos--;
					segundos=59;
				}
			}else {
				segundos--;
			}
			
		}
	}

}
