package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JugadorDB {

	// Conexion a la Base de Datos
	private Connection conn;

	// Objeto Jugador
	private Jugador jugador;

	// Objeto para ejecutar una orden sobre la Base de Datos
	private java.sql.Statement sqlStmnt = null;

	// Constructor: recoge la conexión establecida para la base de datos
	public JugadorDB(Jugador j, Connection c) {
		this.jugador = j;
		this.conn = c;
	}

	// Método que permite insertar un jugador en la Base de Datos
	// La BBDD se llama "matdice" y la tabla correspondiente "jugador"
	public int  insertarJugador() {
		int result = -1; // En caso de error devuelvo -1

		try {
			sqlStmnt = conn.createStatement();
			
			String sql = "INSERT INTO jugadores (nombre, apellido, alias, edad) " + "VALUES ('" + jugador.getNombre()
					+ "', '" + jugador.getApellido1() + "', '" + jugador.getAlias() + "', "
					+ Integer.parseInt(jugador.getEdad()) + ")";
			sqlStmnt.execute(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = sqlStmnt.getGeneratedKeys();
			if (rs.next()) {
			    result = rs.getInt(1);
			}
			;

		} catch (SQLException se) {
			// Se produce un error con la consulta
			se.printStackTrace();
		} catch (Exception e) {
			// Se produce cualquier otro error
			e.printStackTrace();
		} finally {
			try {
				if (sqlStmnt != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return result;
	}

}
