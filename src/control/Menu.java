package control;

import baseDatos.accesoBD;
import objetos.PaginaWeb;
import objetos.Usuario;
import usuario.RecogerDatos;

public class Menu {
	public static final String blue = "\u001B[34m";
	public static final String red = "\u001B[31m";
	public static final String reset = "\u001B[0m";

	public static void elegir() {
		boolean continuar = true;

		do {

			switch (Validaciones.pedirChar(
					"\n¿Qué quieres hacer? \nA: Modificar tablas \nB: Hacer consultas \nT: Mostrar Tablas \nZ: Terminar proceso")) {
			case 'A':
				modificarTablas();
				break;
			case 'B':
				consultarTablas();
				break;
			case 'T':
				mostrarTablas();
				break;
			case 'Z':
				System.out.println("FIN DEL PROGRAMA");
				continuar = false;
				break;

			default:
				colorearError("Esta opción no existe");
			}
		} while (continuar);
	}

	private static void modificarTablas() {
		int columnasModif = 0;
		String sentencia;
		int id;
		int id2;

		Usuario user;
		PaginaWeb webPage;
		boolean continuar = true;

		do {
			colorearTexto("\n->A: MODIFICAR TABLAS");
			switch (Validaciones.pedirChar("\n¿Qué opción quieres elegir? "
					+ "\nA: Insertar en tabla  \nB: Borrar de la tabla 'Usuario' "
					+ "\nC: Modificar tablas \nD: Asociar un Usuario a una Página Web \nT: Mostrar Tablas \nZ: Volver hacia atrás")) {
			case 'A':
				colorearTexto("->A: Insertar en tabla");
				switch (Validaciones.pedirChar("¿A qué tabla quieres insertar datos? \n A: Usuario\n B: Página Web")) {
				case 'A':
					colorearTexto("->A: usuario");
					user = RecogerDatos.pedirUsuario();

					sentencia = GenerarSQL.insertUsuario(user);

					columnasModif = accesoBD.modificarBD(sentencia);

					// insertar en t1t2

					id = accesoBD.selectID("SELECT MAX(idUsuario) FROM usuario;");

					mostrarWebSite();

					System.out.println("¿Cuál es la primera página que ha visitado este usuario?");
					id2 = Validaciones.pedirId("paginaWeb");

					sentencia = GenerarSQL.insertUsuario_has_paginaWeb(id, id2);

					columnasModif += accesoBD.modificarBD(sentencia);
					break;

				case 'B':
					colorearTexto("->B: página web");
					webPage = RecogerDatos.pedirPaginaWeb();

					sentencia = GenerarSQL.insertPaginaWeb(webPage);

					columnasModif = accesoBD.modificarBD(sentencia);
					break;

				default:
					colorearError("Esta opción no existe");
					break;
				}
				System.out.println("Se han insertado " + columnasModif + " columna(s).");
				break;

			case 'B':
				colorearTexto("->B: Borrar en tabla usuario");

				mostrarUser();

				System.out.println("¿Qué usuario quieres borrar?");
				id = Validaciones.pedirId("usuario");

				// borra primero todas las relaciones que tiene un usuario
				sentencia = GenerarSQL.deleteUsuarioHasPaginaWeb(id);

				columnasModif = accesoBD.modificarBD(sentencia);

				// y luego elimina el usuario
				sentencia = GenerarSQL.deleteUsuario(id);

				columnasModif += accesoBD.modificarBD(sentencia);

				System.out.println("Se han borrado " + columnasModif + " columna(s).");
				break;

			case 'C':
				colorearTexto("->C: Modificar tablas");
				switch (Validaciones.pedirChar("¿Qué tabla quieres modificar? \n A: Usuario\n B: Página Web")) {
				case 'A':
					colorearTexto("->A: usuario");

					mostrarUser();

					// pide el id de un usuario
					System.out.println("¿Qué usuario quieres modificar?");
					id = Validaciones.pedirId("usuario");

					user = RecogerDatos.pedirUsuario();

					sentencia = GenerarSQL.updateUsuario(user, id);

					columnasModif = accesoBD.modificarBD(sentencia);
					break;

				case 'B':
					colorearTexto("->B: página web");

					mostrarWebSite();

					// pide el id de una página Web
					System.out.println("¿Qué página web quieres modificar?");
					id = Validaciones.pedirId("paginaWeb");

					webPage = RecogerDatos.pedirPaginaWeb();

					sentencia = GenerarSQL.updatePaginaWeb(webPage, id);

					columnasModif = accesoBD.modificarBD(sentencia);
					break;

				default:
					colorearError("Esta opción no existe");
					break;
				}
				System.out.println("Se han modificado " + columnasModif + " columna(s).");
				break;

			case 'D':
				colorearTexto("->D: Asociar usuario a página web");

				mostrarUser();

				System.out.println("¿Qué usuario quieres asociar?");
				id = Validaciones.pedirId("usuario");

				mostrarWebSite();

				System.out.println("¿Qué página ha visitado?");
				id2 = Validaciones.pedirId("paginaWeb");

				sentencia = GenerarSQL.insertUsuario_has_paginaWeb(id, id2);

				columnasModif += accesoBD.modificarBD(sentencia);

				break;

			case 'T':
				mostrarTablas();
				break;

			case 'Z':
				continuar = false;
				break;

			default:
				colorearError("Esta opción no existe");
			}
			columnasModif = 0;
		} while (continuar);
	}

	private static void consultarTablas() {
		char eleccionSentencia;
		String sentencia;
		int id;
		boolean continuar = true;

		do {
			colorearTexto("\n->B: HACER CONSULTAS");
			eleccionSentencia = Validaciones.pedirChar("\n¿Qué opción quieres elegir? "
					+ "\nA: ¿Cuántas Páginas Web ha visitado un Usuario? \nB: ¿Cuántos Usuarios tiene una Página Web?  \nC: ¿Cuántas Páginas Web hay? "
					+ "\nD: ¿Qué Usuario ha visitado más páginas? \nE: ¿Cuántas Páginas Web no tienen Usuarios? \nT: Mostrar Tablas \nZ: Volver hacia atrás");
			switch (eleccionSentencia) {

			case 'A':
				colorearTexto("->A: ¿Cuántas páginas web ha visitado un usuario?");

				mostrarUser();

				System.out.println("Elige el usuario al cual le quieras hacer la consulta");
				id = Validaciones.pedirId("usuario");

				sentencia = GenerarSQL.paginasWebVisitadasPorUsuario(id);

				accesoBD.selectBD(sentencia, "usuario", eleccionSentencia);
				break;

			case 'B':
				colorearTexto("->B: ¿Cuántos usuarios tiene una página web?");

				mostrarWebSite();

				System.out.println("Elige la página web a la cual le quieras hacer la consulta");
				id = Validaciones.pedirId("paginaWeb");

				sentencia = GenerarSQL.usuariosDeUnaPaginaWeb(id);

				accesoBD.selectBD(sentencia, "paginaWeb", eleccionSentencia);
				break;

			case 'C':
				colorearTexto("->C: ¿Cuántas páginas web hay?");
				sentencia = GenerarSQL.selectCountPaginaWeb();

				accesoBD.selectBD(sentencia, "paginaWeb", eleccionSentencia);
				break;

			case 'D':
				colorearTexto("->D: ¿Qué usuario ha visitado más páginas?");
				sentencia = GenerarSQL.selectUsuarioMasUsado();

				accesoBD.selectBD(sentencia, "usuario", eleccionSentencia);
				break;

			case 'E':
				colorearTexto("->E: ¿Cuántas páginas web no tienen usuarios?");
				sentencia = GenerarSQL.paginasWebSinUsuarios();

				accesoBD.selectBD(sentencia, "paginaWeb", eleccionSentencia);
				break;

			case 'T':
				mostrarTablas();
				break;

			case 'Z':
				continuar = false;
				break;

			default:
				colorearError("Esta opción no existe");
			}
		} while (continuar);

	}

	private static void colorearTexto(String texto) {
		System.out.println(blue + "" + texto + "" + reset);
	}

	private static void colorearError(String texto) {
		System.out.println(red + "" + texto + "" + reset);
	}

	// ELECCIÓN DE MOSTRAR TABLAS
	private static void mostrarTablas() {
		switch (Validaciones.pedirChar(
				"¿Qué tabla quieres ver? \n A: Usuario\n B: Página Web\n C: Usuario_has_PaginaWeb \n D: Copia Usuario \n E: Copia Usuario_has_PaginaWeb")) {
		case 'A':
			colorearTexto("->A: usuario");
			mostrarUser();
			break;
		case 'B':
			colorearTexto("->B: página Web");
			mostrarWebSite();
			break;
		case 'C':
			colorearTexto("->C: usuario_has_paginaWeb");
			mostrarUser_has_WebSite();
			break;
		case 'D':
			colorearTexto("->D: Copia de usuario");
			mostrarUserCopy();
			break;
		case 'E':
			colorearTexto("->E: Copia de usuario_has_paginaWeb");
			mostrarUser_has_WebSiteCopy();
			break;

		default:
			colorearError("Esta opción no existe");
			break;
		}
	}

	private static void mostrarUser() {
		accesoBD.selectBD(GenerarSQL.mostrarUsuario(), "usuario", 'z');
	}

	private static void mostrarWebSite() {
		accesoBD.selectBD(GenerarSQL.mostrarPaginaWeb(), "paginaWeb", 'z');
	}

	private static void mostrarUser_has_WebSite() {
		accesoBD.selectBD(GenerarSQL.mostrarUsuarios_has_paginasWeb(), "usuario_has_paginaWeb", 'z');
	}

	private static void mostrarUserCopy() {
		accesoBD.selectBD(GenerarSQL.mostrarUsuarioCopia(), "usuariocopia", 'z');
	}

	private static void mostrarUser_has_WebSiteCopy() {
		accesoBD.selectBD(GenerarSQL.mostrarUsuarios_has_paginasWebCopia(), "usuario_has_paginaWebcopia", 'z');
	}
}
