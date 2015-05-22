package gui;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;

import liga.Liga;

/**
 * Clase que se encarga de la gestión del programa.
 * @author Jesús López González
 *
 */
public class Gestion {
	
	public static Liga liga = new Liga();
	public static boolean modificado=false;
	public static File fichero;
	public static boolean abierto = false;

	/**
	 * Método que nos permite abrir un fichero
	 * @param fichero Fichero a abrir
	 * @return Devuelve un objeto de tipo Liga.
	 * @throws FileNotFoundException Si el archivo no ha sido encontrado
	 * @throws IOException Si existe un error en el flujo de datos
	 * @throws ClassNotFoundException Si no se encuentra la clase
	 */
	static Liga abrir(File fichero) throws FileNotFoundException, IOException,
			ClassNotFoundException {
		try (ObjectInputStream entrada = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fichero)))) {
			return liga = (Liga) entrada.readObject();
		}
	}

	/**
	 * Método que abre una imagen
	 * @param fichero imagen a abrir
	 * @return ImageIcon
	 */
	public static ImageIcon abrirImagen(File fichero) {
		return new ImageIcon(fichero.toString());
	}

	/**
	 * Método que nos permite escribir en un fichero
	 * @param fichero Fichero a escribir
	 * @throws FileNotFoundException Si el fichero no existe
	 * @throws IOException Si se produce un error en el flujo de datos
	 */
	static void escribir(File fichero) throws FileNotFoundException,
			IOException {
		try (ObjectOutputStream salida = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(extensionValida(fichero))))) {
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

	/**
	 * Comprueba si existe un fichero determinado
	 * @param fichero Fichero a comprobar
	 * @return True si existe y false en caso contrario
	 */
	static boolean existe(File fichero) {
		return fichero.exists();
	}

	/**
	 * Getter de Liga
	 * @return Liga
	 */
	public static Liga getLiga() {
		return liga;
	}

	/**
	 * Setter de Liga
	 * @param liga
	 */
	public static void setLiga(Liga liga) {
		Gestion.liga = liga;
	}

	/**
	 * Comprueba si la liga está modificada
	 * @return True si está modificada y False en caso contrario
	 */
	public static boolean isModificado() {
		return modificado;
	}

	/**
	 * Setter de modificado
	 * @param modificado 
	 */
	public static void setModificado(boolean modificado) {
		Gestion.modificado = modificado;
	}

	/**
	 * Getter de Fichero
	 * @return Fichero
	 */
	public static File getFichero() {
		return fichero;
	}

	/**
	 * Setter de Fichero
	 * @param fichero
	 */
	public static void setFichero(File fichero) {
		Gestion.fichero = fichero;
	}

	/**
	 * Comprueba si una liga ha sido abierta desde un fichero
	 * @return True si ha sido abierta y False en caso contrario
	 */
	public static boolean isAbierto() {
		return abierto;
	}

	/**
	 * Setter de abierto
	 * @param abierto
	 */
	public static void setAbierto(boolean abierto) {
		Gestion.abierto = abierto;
	}
	
	/**
	 * Método que hace que a la hora de guardar un archivo, este lo haga con extensión obj
	 * @param archivo Archivo a comprobar
	 * @return Archivo con extensión .obj
	 */
	public static File extensionValida(File archivo){
		if(archivo.getPath().endsWith(".obj"))
			return archivo;
		else
			return new File(archivo+".obj");
	}

}
