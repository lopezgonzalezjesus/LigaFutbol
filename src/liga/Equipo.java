package liga;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import excepciones.DorsalExisteException;
import excepciones.DorsalNullException;
import excepciones.EntrenadorYaExistenteException;
import excepciones.EquipoNullException;
import excepciones.FotoNullException;
import excepciones.NacionalidadNullException;
import excepciones.NombreInvalidoException;
import excepciones.NumeroMaximoJugadoresException;
import excepciones.PresidenteYaDefinidoException;

/**
 * @author Jesús
 *
 */
public class Equipo implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Pattern PATRON_NOMBRE = Pattern
			.compile("^[A-ZÁÉÍÚÓ]([a-záéíóúñç]){1,}$");
	private static Matcher matcher;
	private String nombre;
	private String estadio;
	private Calendar fundacion;
	private ImageIcon escudo;
	private ArrayList<IntegranteEquipo> plantilla = new ArrayList<IntegranteEquipo>();

	/**
	 * Constructor de Equipo
	 */
	public Equipo(String nombre, String estadio, Calendar fundacion,
			ImageIcon escudo) {
		setNombre(nombre);
		setEstadio(estadio);
		setFundacion(fundacion);
		setEscudo(escudo);
	}

	public Equipo(String nombre) {
		setNombre(nombre);
	}

	/**
	 * Método que añade un directivo a la plantilla de un equipo
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param nacimiento
	 * @param nacionalidad
	 * @param equipo
	 * @param cargo
	 * @return
	 * @throws NombreInvalidoException
	 * @throws EquipoNullException
	 * @throws FotoNullException
	 * @throws NacionalidadNullException
	 * @throws PresidenteYaDefinidoException 
	 */
	public boolean anadirDirectivo(String nombre, String apellidos,
			String nacionalidad, Equipo equipo, ImageIcon foto, Cargo cargo)
			throws NombreInvalidoException, EquipoNullException,
			FotoNullException, NacionalidadNullException, PresidenteYaDefinidoException {
		if (!IntegranteEquipo.validarNombre(nombre))
			throw new NombreInvalidoException(
					"El nombre del directivo es inválido");
		if (!IntegranteEquipo.validarNombre(apellidos))
			throw new NombreInvalidoException(
					"Los apellidos del directivo no son válidos");
		if (equipo == null)
			throw new EquipoNullException("No has seleccionado un equipo");
		if (nacionalidad.equals(""))
			throw new NacionalidadNullException(
					"No has seleccionado una nacionalidad");
		if (foto == null)
			throw new FotoNullException("No has seleccionado una foto");
		if(cargo == Cargo.PRESIDENTE && contarPresidente())
			throw new PresidenteYaDefinidoException("Este equipo ya tiene un presidente");
		return plantilla.add(new Directivo(nombre, apellidos, nacionalidad,
				equipo, foto, cargo));
	}

	/**
	 * Método que añade un tecnico a la plantilla de un equipo
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param nacimiento
	 * @param nacionalidad
	 * @param equipo
	 * @param titulacion
	 * @return
	 * @throws NombreInvalidoException
	 * @throws EquipoNullException
	 * @throws NacionalidadNullException
	 * @throws FotoNullException
	 * @throws EntrenadorYaExistenteException 
	 */
	public boolean anadirTecnico(String nombre, String apellidos,
			String nacionalidad, Equipo equipo, ImageIcon foto,
			Titulacion titulacion) throws NombreInvalidoException,
			EquipoNullException, NacionalidadNullException, FotoNullException, EntrenadorYaExistenteException {
		if (!IntegranteEquipo.validarNombre(nombre))
			throw new NombreInvalidoException(
					"El nombre del técnico es inválido");
		if (!IntegranteEquipo.validarNombre(apellidos))
			throw new NombreInvalidoException(
					"Los apellidos del técnico no son válidos");
		if (equipo == null)
			throw new EquipoNullException("No has seleccionado un equipo");
		if (nacionalidad.equals(""))
			throw new NacionalidadNullException(
					"No has seleccionado una nacionalidad");
		if (foto == null)
			throw new FotoNullException("No has seleccionado una foto");
		if(titulacion==Titulacion.ENTRENADOR && contarEntrenador())
			throw new EntrenadorYaExistenteException("Ya existe un entrenador para este equipo");
		else
			return plantilla.add(new Tecnico(nombre, apellidos, nacionalidad,
					equipo, foto, titulacion));
	}

	/**
	 * Método que añade un futbolista a la plantilla de un equipo
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param nacimiento
	 * @param nacionalidad
	 * @param equipo
	 * @param dorsal
	 * @param demarcacion
	 * @return
	 * @throws NombreInvalidoException
	 * @throws EquipoNullException
	 * @throws NacionalidadNullException
	 * @throws FotoNullException
	 * @throws DorsalNullException
	 * @throws NumeroMaximoJugadoresException 
	 * @throws DorsalExisteException 
	 */
	public boolean anadirFutbolista(String nombre, String apellidos,
			String nacionalidad, Equipo equipo, ImageIcon foto, String dorsal,
			Demarcacion demarcacion) throws NombreInvalidoException,
			EquipoNullException, NacionalidadNullException, FotoNullException,
			DorsalNullException, NumeroMaximoJugadoresException, DorsalExisteException {
		if (!IntegranteEquipo.validarNombre(nombre))
			throw new NombreInvalidoException(
					"El nombre del futbolista es inválido");
		if (!IntegranteEquipo.validarNombre(apellidos))
			throw new NombreInvalidoException(
					"Los apellidos del futbolista no son válidos");
		if (equipo == null)
			throw new EquipoNullException("No has seleccionado un equipo");
		if (nacionalidad.equals(""))
			throw new NacionalidadNullException(
					"No has seleccionado una nacionalidad");
		if (foto == null)
			throw new FotoNullException("No has seleccionado una foto");
		if (dorsal.equals(""))
			throw new DorsalNullException("No has introducido un dorsal");
		if(!contarJugadores())
			throw new NumeroMaximoJugadoresException("El cupo de futbolistas está completo.");
		if(dorsalExiste(dorsal))
			throw new DorsalExisteException("El dorsal ya existe");
		else {
			return plantilla.add(new Futbolista(nombre, apellidos,
					nacionalidad, equipo, foto, dorsal, demarcacion));
		}
	}

	public IntegranteEquipo get(int indice) {
		if (plantilla.get(indice) != null && !(plantilla.isEmpty()))
			return plantilla.get(indice);
		else if (indice < 0 || indice > (plantilla.size() - 1))
			return null;
		else
			return null;
	}

	public boolean eliminarIntegrante(IntegranteEquipo integranteEquipo) {
		if (plantilla.contains(integranteEquipo))
			return plantilla.remove(integranteEquipo);
		return false;
	}

	public int size() {
		return plantilla.size();
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstadio() {
		return estadio;
	}

	private void setEstadio(String estadio) {
		this.estadio = estadio;
	}

	public Calendar getFundacion() {
		return fundacion;
	}

	private void setFundacion(Calendar fundacion) {
		this.fundacion = fundacion;
	}

	/**
	 * Metodo que entrena al equipo.
	 */
	public void entrenar() {
		for (int i = 0; i < plantilla.size(); i++) {
			if (!(plantilla.get(i) instanceof Directivo))
				plantilla.get(i).entrenar();
		}
	}

	public ImageIcon getEscudo() {
		return escudo;
	}

	public void setEscudo(ImageIcon escudo) {
		this.escudo = escudo;
	}

	public static boolean validarNombre(String nombre) {
		matcher = PATRON_NOMBRE.matcher(nombre);
		return matcher.matches();

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", estadio=" + estadio
				+ ", fundacion=" + fundacion + ", escudo=" + escudo
				+ ", plantilla=" + plantilla + "]";
	}
	
	private boolean contarJugadores(){
		int contador=0;
		for (int i = 0; i < plantilla.size(); i++) {
			if(plantilla.get(i) instanceof Futbolista)
				contador++;
		}
		if(contador<25)
			return true;
		return false;
	}
	
	private boolean contarPresidente(){
		for (int i = 0; i <plantilla.size(); i++) {
			if(plantilla.get(i) instanceof Directivo)
				if(((Directivo) plantilla.get(i)).getCargo()==Cargo.PRESIDENTE)
					return true;
		}
		return false;
	}
	
	private boolean contarEntrenador(){
		for (int i = 0; i <plantilla.size(); i++) {
			if(plantilla.get(i) instanceof Tecnico)
				if(((Tecnico) plantilla.get(i)).getTitulacion()==Titulacion.ENTRENADOR)
					return true;
		}
		return false;
	}
	
	private boolean dorsalExiste(String dorsal){
		for (int i = 0; i < plantilla.size(); i++) {
			if(plantilla.get(i) instanceof Futbolista)
				if(((Futbolista) plantilla.get(i)).getDorsal().equals(dorsal))
					return true;
					
		}
		return false;
	}

}
