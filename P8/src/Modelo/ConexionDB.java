package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class ConexionDB {
	private String HOST = null;
	private String DB = null;
	private String user = null;
	private String pass = null;
	private String url = null;

	private Connection conn = null;

	private static ConexionDB instance = null;

	public ConexionDB() {
		
	}
	
	
	public ConexionDB(String hst, String bbdd, String usr, String pwd) {
		this.HOST = hst;
		this.DB = bbdd;
		this.user = usr;
		this.pass = pwd;
		this.url = "jdbc:mysql://" + HOST + ":3306/" + DB;
	}

	public boolean connectDB() {
		boolean result = true;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException ex) {
				Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
				result = false;
			} catch (IllegalAccessException ex) {
				Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
				result = false;
			}

			conn = DriverManager.getConnection(url, user, pass);

			if (conn != null) {
				System.out.println("Conexión a base de datos " + url + " ... Ok");
				// conn.close();
			}
		} catch (SQLException ex) {
			System.out.println("Hubo un problema al intentar conectarse con la base de datos " + url);
			result = false;
		} catch (ClassNotFoundException ex2) {
			System.out.println(ex2);
			result = false;
		}

		return result;
	}

	public static ConexionDB getInstance() {
		if (instance == null) {
			instance = new ConexionDB();
		}
		return instance;
	}

	public Connection getConnection() {
		return conn;
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}