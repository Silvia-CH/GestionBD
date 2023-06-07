package control;

import java.util.InputMismatchException;
import java.util.Scanner;

import baseDatos.accesoBD;

public class Validaciones {

	public static String pedirString(String enunciado) {
		String texto = null;

		Scanner lector = new Scanner(System.in);
		try {
			do {
				System.out.println(enunciado);
				texto = lector.nextLine();
			} while (texto.isBlank() || texto.isEmpty());
		} catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println("No es un texto");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return texto;
	}

	public static int pedirId(String tabla) {
		int numero = 0;
		int numMax = 0;

		Scanner lector = new Scanner(System.in);
		try {
			do {
				numMax = accesoBD.selectCountId(tabla);
				System.out.println("Introduzca un id válido entre 1 y " + numMax);
				numero = lector.nextInt();
				lector.nextLine();
			} while (numero > numMax || numero <= 0);
		} catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println("No es un número");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return numero;
	}

	public static char pedirChar(String enunciado) {
		String letra = null;

		Scanner lector = new Scanner(System.in);
		try {
			do {
				System.out.println(enunciado);
				letra = lector.nextLine();
			} while (letra.isBlank() || letra.isEmpty());
		} catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println("No es una letra");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return letra.toUpperCase().charAt(0);
	}

}
