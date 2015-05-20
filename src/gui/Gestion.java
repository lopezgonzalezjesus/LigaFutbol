package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;

import liga.Liga;

public class Gestion {
	
	public static Liga liga = new Liga();
	public static boolean modificado=false;
	public static File fichero;
	public static boolean abierto = false;

	static Liga abrir(File fichero) throws FileNotFoundException, IOException,
			ClassNotFoundException {
		try (ObjectInputStream entrada = new ObjectInputStream(
				new FileInputStream(fichero))) {
			return liga = (Liga) entrada.readObject();
		}
	}

	public static ImageIcon abrirImagen(File fichero) {
		return new ImageIcon(fichero.toString());
	}

	static void escribir(File fichero) throws FileNotFoundException,
			IOException {
		try (ObjectOutputStream salida = new ObjectOutputStream(
				new FileOutputStream(fichero))) {
			salida.writeObject(liga);
		}
	}
	/**
	 * Resetea los campos de la Gestion al crear una nueva liga
	 */
	static void reset(){
		liga = new Liga();
		modificado = true;
		fichero = null;
		abierto = false;
	}

	static boolean existe(File fichero) {
		return fichero.exists();
	}

	public static Liga getLiga() {
		return liga;
	}

	public static void setLiga(Liga liga) {
		Gestion.liga = liga;
	}

	public static boolean isModificado() {
		return modificado;
	}

	public static void setModificado(boolean modificado) {
		Gestion.modificado = modificado;
	}

	public static File getFichero() {
		return fichero;
	}

	public static void setFichero(File fichero) {
		Gestion.fichero = fichero;
	}

	public static boolean isAbierto() {
		return abierto;
	}

	public static void setAbierto(boolean abierto) {
		Gestion.abierto = abierto;
	}
	
	

}
