package Principal;

import Ventanas.Registro;
import Modelo.ConexionDB;

//Lanzo ventana(s)
public class Principal {

	private static Registro registro;

	public static void main(String[] args) {

		// Ventanas usadas

		// Creacion ventana Registro y enlace con ventana Jugar
		registro = new Registro();

		// Hacer visible la ventana Registro
		registro.setVisible(true);

	}

}
