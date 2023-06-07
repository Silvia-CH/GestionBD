package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class accesoBD {

	// SENTENCIAS QUE MODIFICAN LA BD
	public static int modificarBD(String sentencia) {
		Connection conexion = null;
		Statement sentenciaSQL = null;
		int resultado = 0;
		String sql = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/hoja10", "root", "");

			sentenciaSQL = conexion.createStatement();

			sql = sentencia;

			resultado = sentenciaSQL.executeUpdate(sql);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return resultado; // devuelve las líneas modificadas
	}

// SENTENCIAS QUE HACEN CONSULTAS
	public static void selectBD(String sentencia, String tabla, char eleccionConsulta) {
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		String sql = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/hoja10", "root", "");

			sentenciaSQL = conexion.createStatement();

			sql = sentencia;

			rs = sentenciaSQL.executeQuery(sql);

			// dependiendo de lo que se pide, necesita devolver diferentes cosas
			mostrarTabla(rs, tabla, eleccionConsulta);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// devuleve un id
	public static int selectID(String sentencia) {
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		String sql = "";
		int id = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/hoja10", "root", "");

			sentenciaSQL = conexion.createStatement();

			sql = sentencia;

			rs = sentenciaSQL.executeQuery(sql);

			while (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return id;
	}

	// cuenta la cantidad de inserciones que hay en una tabla
	public static int selectCountId(String tabla) {
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		String sql = "";
		int resultado = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/hoja10", "root", "");

			sentenciaSQL = conexion.createStatement();

			sql = "SELECT COUNT(*) FROM " + tabla + ";";

			rs = sentenciaSQL.executeQuery(sql);

			while (rs.next()) {
				resultado = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return resultado;
	}

	// los diferentes métodos de enseñar el resultado de las consultas
	private static void mostrarTabla(ResultSet rs, String tabla, char eleccionConsulta) {
		int idUsuario;
		String nick;
		String email;
		String passwd;

		int idPaginaWeb;
		String dominio;
		String url;

		int usuarioID;
		int paginaWebID;

		String nombre = null;
		String nombre2 = null;
		int count = 0;

		int idCopia;
		LocalDateTime momento;
		String usuario;

		// primer switch contempla las posibilidades de las consultas de enunciado
		// el segundo contempla todos los "mostrar Tabla"
		try {
			switch (eleccionConsulta) {
			case 'A', 'B':
				System.out.println("NOMBRE	COUNT");
				while (rs.next()) {
					nombre = rs.getString(1);
					nombre2 = rs.getString(2);
					System.out.println(" " + nombre + "	" + nombre2);
				}
				break;
			case 'D':
				System.out.println("NOMBRE	COUNT");
				while (rs.next()) {
					nombre = rs.getString(1);
					count = rs.getInt(2);
					System.out.println(" " + nombre + "	" + count);
				}
				break;
			case 'C':
				System.out.println("COUNT");
				while (rs.next()) {
					count = rs.getInt(1);
					System.out.println("-> " + count);
				}
				break;
			case 'E':
				System.out.println("NOMBRE");
				while (rs.next()) {
					nombre = rs.getString(1);
					System.out.println("-> " + nombre);
				}
				break;

			default:
				switch (tabla) {
				case "usuario":
					System.out.println("ID	NICK	EMAIL			CONTRASEÑA");

					while (rs.next()) {
						idUsuario = rs.getInt(1);
						nick = rs.getString(2);
						email = rs.getString(3);
						passwd = rs.getString(4);

						System.out.println(" " + idUsuario + "	" + nick + "	" + email + "		" + passwd);
					}
					break;
				case "paginaWeb":
					System.out.println("ID	DOMINIO		URL");

					while (rs.next()) {
						idPaginaWeb = rs.getInt(1);
						dominio = rs.getString(2);
						url = rs.getString(3);

						System.out.println(" " + idPaginaWeb + "	" + dominio + "		" + url);
					}
					break;
				case "usuario_has_paginaWeb":
					System.out.println("NICK	USUARIO_ID	PAGINAWEB_ID	DOMINIO");

					while (rs.next()) {
						nick = rs.getString(1);
						usuarioID = rs.getInt(2);
						paginaWebID = rs.getInt(3);
						dominio = rs.getString(4);

						System.out.println(nick + "		" + usuarioID + "	" + paginaWebID + "		" + dominio);
					}
					break;
				case "usuariocopia":
					System.out.println("IDCOPIA	ID	NICK	EMAIL			CONTRASEÑA		HORA 	USUARIO");

					while (rs.next()) {
						idCopia = rs.getInt(1);
						idUsuario = rs.getInt(2);
						nick = rs.getString(3);
						email = rs.getString(4);
						passwd = rs.getString(5);
						momento = rs.getTimestamp(6).toLocalDateTime();
						usuario = rs.getString(7);

						System.out.println(idCopia + "	 " + idUsuario + "	" + nick + "	" + email + "		"
								+ passwd + "	" + momento + "	" + usuario);
					}
					break;
				case "usuario_has_paginaWebcopia":
					System.out.println("IDCOPIA	NICK	USUARIO_ID	PAGINAWEB_ID	DOMINIO		HORA 	USUARIO");

					while (rs.next()) {
						idCopia = rs.getInt(1);
						nick = rs.getString(2);
						usuarioID = rs.getInt(3);
						paginaWebID = rs.getInt(4);
						dominio = rs.getString(5);
						momento = rs.getTimestamp(6).toLocalDateTime();
						usuario = rs.getString(7);

						System.out.println(idCopia + " " + nick + "		" + usuarioID + "	" + paginaWebID + "		"
								+ dominio + "	" + momento + "	" + usuario);
					}
					break;
				}
			}
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
