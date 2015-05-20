package liga;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * Clase Directivo que hereda de IntegranteEquipo y nos permite especificar las características de un directivo
 * @author Jesús López González
 *
 */
public class Directivo extends IntegranteEquipo implements Retributable, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de directivo por nombre y apellidos
	 * @param nombre Nombre del directivo
	 * @param apellidos Apellidos del directivo
	 */
	public Directivo(String nombre, String apellidos){
		super(nombre, apellidos);
	}
	
	/**
	 * Constructor de Directivo
	 * @param nombre Nombre del directivo
	 * @param apellidos Apellidos del directivo
	 * @param nacionalidad Nacionalidad del directivo
	 * @param equipo Equipo al que pertenece el directivo
	 * @param foto Foto del directivo
	 * @param cargo Cargo del directivo
	 */
	public Directivo(String nombre, String apellidos,
			 String nacionalidad, Equipo equipo, ImageIcon foto, Cargo cargo) {
		super(nombre, apellidos, nacionalidad, equipo, foto, cargo);
		generarSueldo();
	}

	
	
	@Override
	/**
	 * Método que genera el sueldo del directivo dependiendo de su cargo
	 */
	public void generarSueldo(){
		if(getCargo()==Cargo.PRESIDENTE)
			setSueldo(1000000);
		else
			setSueldo(500000);
	}
}
