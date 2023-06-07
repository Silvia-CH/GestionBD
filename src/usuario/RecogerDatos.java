package usuario;

import control.Validaciones;
import objetos.PaginaWeb;
import objetos.Usuario;

public class RecogerDatos {

	/*public static void pedirObjeto(String nombreTabla) {
		switch (nombreTabla) {
		case "usuario":
			pedirUsuario();
			break;
		case "paginaWeb":
			pedirPaginaWeb();
			break;
		default:
			break;
		}
	}*/
	
	public static Usuario pedirUsuario() {
		String nick;
		String email;
		String passwd;

		Usuario usuario;

		nick = Validaciones.pedirString("Introduce el nick");

		email = Validaciones.pedirString("Introduce el email");

		passwd = Validaciones.pedirString("Introduce contraseña");

		usuario = new Usuario(nick, email, passwd);

		return usuario;
	}

	public static PaginaWeb pedirPaginaWeb() {
		String dominio;
		String url;

		PaginaWeb pagWeb;

		dominio = Validaciones.pedirString("Introduce el nombre del dominio");

		url = Validaciones.pedirString("Introduce la URL");

		pagWeb = new PaginaWeb(dominio, url);

		return pagWeb;
	}

}
