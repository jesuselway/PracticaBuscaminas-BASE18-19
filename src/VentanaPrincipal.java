import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class VentanaPrincipal {

	/**
	 * @author Jes�s Carranza Arenas
	 * @version 1.7
	 * @since 25/10/2018
	 * @see #ControlJuego clase control juego
	 * Esta clase es la ventana principal del buscaminaas, donde se genera la parte visual
	 * y se hacen algunas comprobaciones del juego.
	 * Todo empieza aqu� {@link #inicializar()}
	 * {@code <pre>
	 * public void inicializar(){
		ventana.setVisible(true);
		inicializarComponentes();	
		inicializarListeners();	
	 * </pre>}
	 * 
	 * 
	 * 
	 * */
	
	//La ventana principal, en este caso, guarda todos los componentes:
	JFrame ventana;
	JPanel panelImagen;
	JPanel panelEmpezar;
	JPanel panelPuntuacion;
	JPanel panelJuego;
	
	//Todos los botones se meten en un panel independiente.
	//Hacemos esto para que podamos cambiar después los componentes por otros
	JPanel [][] panelesJuego;
	JButton [][] botonesJuego;
	
	//Correspondencia de colores para las minas:
	Color correspondenciaColores [] = {Color.BLACK, Color.CYAN, Color.GREEN, Color.ORANGE, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED};
	
	JButton botonEmpezar;
	JTextField pantallaPuntuacion;
	
	
	//LA VENTANA GUARDA UN CONTROL DE JUEGO:
	ControlJuego juego;
	
	
	//Constructor, marca el tamaño y el cierre del frame
	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 700, 500);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego = new ControlJuego();
	}
	
	//Inicializa todos los componentes del frame
	public void inicializarComponentes(){
		
		//Definimos el layout:
		ventana.setLayout(new GridBagLayout());
		
		//Inicializamos componentes
		panelImagen = new JPanel();
		panelEmpezar = new JPanel();
		panelEmpezar.setLayout(new GridLayout(1,1));
		panelPuntuacion = new JPanel();
		panelPuntuacion.setLayout(new GridLayout(1,1));
		panelJuego = new JPanel();
		panelJuego.setLayout(new GridLayout(10,10));
		
		
		botonEmpezar = new JButton("Go!");
		
		botonEmpezar.addActionListener( new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarTablero();
			}

		});
		
		pantallaPuntuacion = new JTextField("0");
		pantallaPuntuacion.setEditable(false);
		pantallaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Bordes y colores:
		panelImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelEmpezar.setBorder(BorderFactory.createTitledBorder("Empezar"));
		panelPuntuacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelJuego.setBorder(BorderFactory.createTitledBorder("Juego"));
		
			
		//Colocamos los componentes:
		//AZUL
		GridBagConstraints settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelImagen, settings);
		//VERDE
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelEmpezar, settings);
		//AMARILLO
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelPuntuacion, settings);
		//ROJO
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 1;
		settings.weightx = 1;
		settings.weighty = 10;
		settings.gridwidth = 3;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelJuego, settings);
		
		//Paneles
		panelesJuego = new JPanel[10][10];
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				panelesJuego[i][j] = new JPanel();
				panelesJuego[i][j].setLayout(new GridLayout(1,1));
				panelJuego.add(panelesJuego[i][j]);
			}
		}
		
		//Botones
		botonesJuego = new JButton[10][10];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("-");
				panelesJuego[i][j].add(botonesJuego[i][j]);
			}
		}
		
		//BotónEmpezar:
		panelEmpezar.add(botonEmpezar);
		panelPuntuacion.add(pantallaPuntuacion);
		
	}
	
	/**
	 * Método que inicializa todos los lísteners que necesita inicialmente el programa
	 */
	public void inicializarListeners(){
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int i2 =i;
				int j2 = j;
				botonesJuego[i][j].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {		
					
if (juego.abrirCasilla(i2, j2)) {
	mostrarFinJuego(true);
	
	for (int k = 0; k < botonesJuego.length; k++) {
		for (int l = 0; l < botonesJuego.length; l++) {
			botonesJuego[k][l].setEnabled(false);
		}
		
	}
	
}else {
	mostrarNumMinasAlrededor(i2, j2);
	actualizarPuntuacion();
	refrescarPantalla();

}						
					}
			
				}); 
		}
			}
	}
	
	private void iniciarTablero() {
		
		panelJuego.removeAll();
		
		panelesJuego = new JPanel[10][10];
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				panelesJuego[i][j] = new JPanel();
				panelesJuego[i][j].setLayout(new GridLayout(1,1));
				panelJuego.add(panelesJuego[i][j]);
			}
		}
		
		//Botones
		botonesJuego = new JButton[10][10];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("-");
				panelesJuego[i][j].add(botonesJuego[i][j]);
			}
		}		
		refrescarPantalla();
		inicializarListeners();
		
		juego.inicializarPartida();
		actualizarPuntuacion();
		
	}
	
	
	/**
	 * Pinta en la pantalla el número de minas que hay alrededor de la celda
	 * Saca el botón que haya en la celda determinada y añade un JLabel centrado y no editable con el número de minas alrededor.
	 * Se pinta el color del texto según la siguiente correspondecia (consultar la variable correspondeciaColor):
	 * - 0 : negro
	 * - 1 : cyan
	 * - 2 : verde
	 * - 3 : naranja
	 * - 4 ó más : rojo 
	 * @param i: posición vertical de la celda.
	 * @param j: posición horizontal de la celda.
	 */
	public void mostrarNumMinasAlrededor(int i , int j) {
		
		
		if (juego.esVacio(i, j)) {	
			panelesJuego[i][j].removeAll();
	JTextField aux = new JTextField();
	aux.setText(Integer.toString(juego.getMinasAlrededor(i,j)));
	aux.setForeground(correspondenciaColores[juego.getMinasAlrededor(i, j)]);
	aux.setHorizontalAlignment(SwingConstants.CENTER);
	aux.setEditable(false);
	panelesJuego[i][j].add(aux);
	juego.puntuacion(i, j);
	juego.esblecerVisitado(i, j);
	refrescarPantalla();
	
			for (int k = Math.max(0, i-1); k <= Math.min(i+1, juego.LADO_TABLERO-1 ) ; k++) {
			for (int k2 = Math.max(0, j-1); k2 <= Math.min(j+1, juego.LADO_TABLERO-1); k2++) {
				if (!juego.abrirCasilla(k, k2)) {
				if (juego.getMinasAlrededor(k, k2)!=juego.VISITADA) {	
	mostrarNumMinasAlrededor(k, k2);
	if (juego.getPuntuacion()==80) {
		mostrarFinJuego(false);
	}
				}
				}		
				}
			}	
		}else {
			panelesJuego[i][j].removeAll();
			JTextField aux = new JTextField();
			aux.setText(Integer.toString(juego.getMinasAlrededor(i, j)));
			aux.setForeground(correspondenciaColores[juego.getMinasAlrededor(i, j)]);
			aux.setHorizontalAlignment(SwingConstants.CENTER);
			aux.setEditable(false);
			panelesJuego[i][j].add(aux);
			refrescarPantalla();
			juego.puntuacion(i, j);
			juego.esblecerVisitado(i, j);
			
		}
		if (juego.getPuntuacion()==80) {
			mostrarFinJuego(false);
		}
		
			}
	
	
	/**
	 * Muestra una ventana que indica el fin del juego
	 * @param porExplosion : Un booleano que indica si es final del juego porque ha explotado una mina (true) o bien porque hemos desactivado todas (false) 
	 * @post : Todos los botones se desactivan excepto el de volver a iniciar el juego.
	 */
	public void mostrarFinJuego(boolean porExplosion) {
if (porExplosion) {
	JOptionPane.showMessageDialog(null, "�Ha explotado una bomba!");
	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 10; j++) {
			
			if (juego.esMina(i, j)) {
				botonesJuego[i][j].setBackground(Color.red);
				botonesJuego[i][j].setText("#");
				
			}
			
		}
	}
}else {
	JOptionPane.showMessageDialog(null, "�Enhorabuena, has ganado!");
	
}
	}

	/**
	 * Método que muestra la puntuación por pantalla.
	 */
	public void actualizarPuntuacion() {
		pantallaPuntuacion.setText(Integer.toString(juego.getPuntuacion()));
		if (juego.getPuntuacion()==80) {
			mostrarFinJuego(false);
		}
	}
	
	/**
	 * Método para refrescar la pantalla
	 */
	public void refrescarPantalla(){
		ventana.revalidate(); 
		ventana.repaint();
	}

	/**
	 * Método que devuelve el control del juego de una ventana
	 * @return un ControlJuego con el control del juego de la ventana
	 */
	public ControlJuego getJuego() {
		return juego;
	}

	/**
	 * Método para inicializar el programa
	 */
	public void inicializar(){
		//IMPORTANTE, PRIMERO HACEMOS LA VENTANA VISIBLE Y LUEGO INICIALIZAMOS LOS COMPONENTES.
		ventana.setVisible(true);
		inicializarComponentes();	
		inicializarListeners();		
	}



}
