package control;

import objetos.PaginaWeb;
import objetos.Usuario;

public class GenerarSQL {

	// - - - - - - - - - - M O D I F I C A R - - - - - - - - - -

	// ---------------------INSERT-------------------------
	// 1. Insertar en la tabla 1
	public static String insertPaginaWeb(PaginaWeb pagWeb) {
		String sentencia;

		sentencia = "INSERT INTO paginaWeb (dominio, url) VALUES ('" + pagWeb.getDominio() + "', '" + pagWeb.getUrl()
				+ "');";

		return sentencia;
	}

	// 2. Insertar en la tabla 2 asociado con la tabla 1
	public static String insertUsuario(Usuario user) {
		String sentencia;

		sentencia = "INSERT INTO usuario (nick, email, passwd) VALUES ('" + user.getNick() + "', '" + user.getEmail()
				+ "', '" + user.getPaswd() + "');";

		return sentencia;
	}

	// 2.2 Y si se borra desde la t2 se borra de la tabla intermedia && 5. Se puede
	// asociar un t2 a un t1
	public static String insertUsuario_has_paginaWeb(int idUser, int idPaginaWeb) {
		String sentencia;

		sentencia = "INSERT INTO usuario_has_paginaWeb (Usuario_id, PaginaWeb_id) VALUES (" + idUser + ", "
				+ idPaginaWeb + ")";

		return sentencia;
	}

	// -----------------------DELETE----------------------------

	// 3. Se puede borrar desde la t2 directamente
	public static String deleteUsuario(int id) {
		String sentencia;

		sentencia = "DELETE FROM usuario WHERE idUsuario = " + id + ";";

		return sentencia;
	}

	public static String deleteUsuarioHasPaginaWeb(int id) {
		String sentencia;

		sentencia = "DELETE FROM usuario_has_paginaWeb WHERE Usuario_id = " + id + ";";

		return sentencia;
	}

	// -----------------------UPDATE----------------------------

	// 4. Se puede modificar en t1 y t2 EXCEPTO ID

	// 4.1
	public static String updatePaginaWeb(PaginaWeb pagWeb, int id) {
		String sentencia;

		sentencia = "UPDATE paginaWeb SET dominio = '" + pagWeb.getDominio() + "', url = '" + pagWeb.getUrl() + "' "
				+ "WHERE idPaginaWeb = " + id + ";";

		return sentencia;
	}

	// 4.2
	public static String updateUsuario(Usuario user, int id) {
		String sentencia;

		sentencia = "UPDATE usuario SET nick = '" + user.getNick() + "', email = '" + user.getEmail() + "', passwd = '"
				+ user.getPaswd() + "' WHERE idUsuario = " + id + ";";

		return sentencia;
	}

	// - - - - - - - - - - C O N S U L T A S - - - - - - - - - -

	// 6. Se pide de la t2 y dice las relaciones con t1
	public static String paginasWebVisitadasPorUsuario(int id) {
		String sentencia;

		sentencia = "SELECT usuario.nick, paginaWeb.dominio FROM usuario "
				+ "INNER JOIN usuario_has_paginaWeb ON usuario_has_paginaWeb.Usuario_id = usuario.idUsuario "
				+ "INNER JOIN paginaWeb ON usuario_has_paginaWeb.PaginaWeb_id = paginaWeb.idPaginaWeb "
				+ "WHERE idUsuario = " + id + ";";

		return sentencia;
	}

	// 7. Se pide de la t1 y dice las relaciones con t2
	public static String usuariosDeUnaPaginaWeb(int id) {
		String sentencia;

		sentencia = "SELECT paginaWeb.dominio, usuario.nick FROM paginaWeb "
				+ "INNER JOIN usuario_has_paginaWeb ON usuario_has_paginaWeb.paginaWeb_id = paginaWeb.idPaginaWeb "
				+ "INNER JOIN usuario ON usuario_has_paginaWeb.Usuario_id = usuario.idUsuario WHERE idPaginaWeb = " + id
				+ ";";

		return sentencia;
	}

	// 8. Cuantos de t1 hay
	public static String selectCountPaginaWeb() {
		String sentencia;

		sentencia = "SELECT COUNT(*) FROM paginaWeb;";

		return sentencia;
	}

	// 9. El dato de t2 más usado
	public static String selectUsuarioMasUsado() {
		String sentencia;

		sentencia = "SELECT usuario.nick, COUNT(*) FROM usuario_has_paginaWeb "
				+ "INNER JOIN usuario ON usuario_has_paginaWeb.Usuario_id = usuario.idUsuario "
				+ "GROUP BY usuario_has_paginaWeb.Usuario_id ORDER BY COUNT(*) DESC LIMIT 1;";

		return sentencia;
	}

	// 10. Cuantos datos de t1 sin relaciones
	public static String paginasWebSinUsuarios() {
		String sentencia;

		sentencia = "SELECT paginaWeb.dominio FROM paginaWeb "
				+ "LEFT JOIN usuario_has_paginaWeb ON usuario_has_paginaWeb.paginaWeb_id = paginaWeb.idPaginaWeb "
				+ "WHERE usuario_has_paginaWeb.paginaWeb_id IS NULL;";

		return sentencia;
	}

	// ---mostrar tablas---

	// página web
	public static String mostrarPaginaWeb() {
		String sentencia;

		sentencia = "SELECT * FROM paginaWeb;";

		return sentencia;
	}

	// usuarios
	public static String mostrarUsuario() {
		String sentencia;

		sentencia = "SELECT * FROM usuario;";

		return sentencia;
	}

	// usuarios_has_paginasWeb
	public static String mostrarUsuarios_has_paginasWeb() {
		String sentencia;

		sentencia = "SELECT usuario.nick, usuario_has_paginaWeb.Usuario_id, "
				+ "usuario_has_paginaWeb.paginaWeb_id, paginaWeb.dominio FROM usuario_has_paginaWeb "
				+ "INNER JOIN paginaWeb ON usuario_has_paginaWeb.paginaWeb_id = paginaWeb.idPaginaWeb "
				+ "INNER JOIN usuario ON usuario_has_paginaWeb.Usuario_id = usuario.idUsuario;";

		return sentencia;
	}

	// usuarios
	public static String mostrarUsuarioCopia() {
		String sentencia;

		sentencia = "SELECT * FROM usuarioCopia;";

		return sentencia;
	}

	// usuarios_has_paginasWeb
	public static String mostrarUsuarios_has_paginasWebCopia() {
		String sentencia;

		sentencia = "SELECT usuario_has_paginaWebCopia.usuario_has_paginaWebCopiaID, usuario.nick, usuario_has_paginaWebCopia.Usuario_id, "
				+ "usuario_has_paginaWebCopia.paginaWeb_id, paginaWeb.dominio, "
				+ "usuario_has_paginaWebCopia.fechaUsuario_has_PaginaWebCopia, "
				+ "usuario_has_paginaWebCopia.usuario FROM usuario_has_paginaWebCopia "
				+ "INNER JOIN paginaWeb ON usuario_has_paginaWebCopia.paginaWeb_id = paginaWeb.idPaginaWeb "
				+ "INNER JOIN usuario ON usuario_has_paginaWebCopia.Usuario_id = usuario.idUsuario;";

		return sentencia;
	}
}
