import java.util.ArrayList;
import java.util.Random;

import javax.naming.ldap.Rdn;

/**
 * Clase gestora del tablero de juego.
 * Guarda una matriz de enteros representado el tablero.
 * Si hay una mina en una posición guarda el número -1
 * Si no hay una mina, se guarda cuántas minas hay alrededor.
 * Almacena la puntuación de la partida
 * @author JesusCarranzaArenas
 *
 */
public class ControlJuego {
	
	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;
	final int VISITADA= -7;

	private int [][] tablero;
	private int puntuacion;
	
	
	public ControlJuego() {
		//Creamos el tablero:
		
		//Inicializamos una nueva partida
		inicializarPartida();
	}
	
	
	/**Método para generar un nuevo tablero de partida:
	 * @pre: La estructura tablero debe existir. 
	 * @post: Al final el tablero se habrá inicializado con tantas minas como marque la variable MINAS_INICIALES. 
	 * 			El resto de posiciones que no son minas guardan en el entero cuántas minas hay alrededor de la celda
	 */
	public void inicializarPartida(){
		tablero = new int[LADO_TABLERO][LADO_TABLERO];

puntuacion=0;
		Random random = new Random();
	int cont=0;
		while (cont<MINAS_INICIALES) {
				int x = random.nextInt(9);
				int y=random.nextInt(9);
				if (tablero[y][x]==MINA) {
					
				}else {
					tablero[y][x]=MINA;	
					cont++;
				}		
		}
		
		
		//Al final del m�todo hay que guardar el n�mero de minas para las casillas que no son mina:
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] != MINA){
					tablero[i][j] = calculoMinasAdjuntas(i,j);
				}
			}
		}
		
		for (int i = 0; i < LADO_TABLERO; i++) {
			System.out.println();
		for (int j = 0; j < LADO_TABLERO; j++) {
			System.out.print("  "+tablero[i][j]);
		}
		}
		
		for (int k = 0; k < LADO_TABLERO; k++) {
			for (int k2 = 0; k2 < LADO_TABLERO; k2++) {
				
			}
		}
		
	}
	
	/**Cálculo de las minas adjuntas: 
	 * Para calcular el n�mero de minas tenemos que tener en cuenta que no nos salimos nunca del tablero.
	 * Por lo tanto, como mucho la i y la j valdr�an LADO_TABLERO-1.
	 * Por lo tanto, como poco la i y la j valdr�an 0.
	 * @param i: posici�n vertical de la casilla a rellenar
	 * @param j: posici�n horizontal de la casilla a rellenar
	 * @return : El número de minas que hay alrededor de la casilla [i][j]
	 **/
	private int calculoMinasAdjuntas(int i, int j){
int cont=0;

for (int k = Math.max(0, i-1); k <= Math.min(i+1, LADO_TABLERO-1 ) ; k++) {
	for (int k2 = Math.max(0, j-1); k2 <= Math.min(j+1, LADO_TABLERO-1); k2++) {
		if (tablero[k][k2]==MINA) {
			cont++;
		}	
	}	
}
		return cont;
	}
	
	/**
	 * Método que nos permite 
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por el ControlJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posición verticalmente de la casilla a abrir
	 * @param j: posición horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j){

		if (tablero[i][j]==MINA) {
			return true;
		}
	return false;	
	}
	
	public boolean esVacio(int y, int x) {
		
		if (tablero[y][x]==0) {
			return true;
		}
		
		
		return false;
	}
	
	public boolean esMina(int y, int x) {
		
		if (tablero[y][x]==MINA) {
			return true;
		}
		
		return false;
	}
	
	public void puntuacion(int y, int x) {
		if (tablero[y][x]!=MINA&&tablero[y][x]!=VISITADA) {
puntuacion++;	}
	}
	
	/**
	 * Método que checkea si se ha terminado el juego porque se han abierto todas las casillas.
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son minas.
	 **/
//	public boolean esFinJuego(){
//		
//	}
	
	
	/**
	 * Método que pinta por pantalla toda la información del tablero, se utiliza para depurar
	 */
	public void depurarTablero(){
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuaci�n: "+puntuacion);
	}

	public void esblecerVisitado(int y,int x) {
		tablero[y][x]=VISITADA;
		
	}
	
	/**
	 * Método que se utiliza para obtener las minas que hay alrededor de una celda
	 * @pre : El tablero tiene que estar ya inicializado, por lo tanto no hace falta calcularlo, símplemente consultarlo
	 * @param i : posición vertical de la celda.
	 * @param j : posición horizontal de la cela.
	 * @return Un entero que representa el número de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int i, int j) {
	return	tablero[i][j];
	}

	public int getPuntuacion() {
		return this.puntuacion;
	}
	
}
