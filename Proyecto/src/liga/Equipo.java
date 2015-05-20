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
import excepciones.IntegranteYaExistenteException;
import excepciones.NacionalidadNullException;
import excepciones.NombreInvalidoException;
import excepciones.NumeroMaximoJugadoresException;
import excepciones.PresidenteYaDefinidoException;

/**
 * Clase Equipo desde la que podremos crear un equipo para nuestra Liga
 * @author Jes�s L�pez Gonz�lez
 *
 */
public class Equipo implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Pattern PATRON_NOMBRE = Pattern
			.compile("^[A-Z�����]([a-z�������]){1,}$");
	private static Matcher matcher;
	private String nombre;
	private String estadio;
	private Calendar fundacion;
	private ImageIcon escudo;
	public ArrayList<IntegranteEquipo> plantilla = new ArrayList<IntegranteEquipo>();

	/**
	 * Constructor de Equipo
	 * @param nombre Nombre del Equipo
	 * @param estadio Estadio del equipo
	 * @param fundacion Fecha de fundacion del equipo
	 * @param escudo Escudo del equipo
	 */
	public Equipo(String nombre, String estadio, Calendar fundacion,
			ImageIcon escudo) {
		setNombre(nombre);
		setEstadio(estadio);
		setFundacion(fundacion);
		setEscudo(escudo);
	}

	/**
	 * Constructor de equipo por nombre
	 * @param nombre nombre del equipo
	 */
	public Equipo(String nombre) {
		setNombre(nombre);
	}

	/**
	 * M�todo que a�ade un directivo a la plantilla de un equipo
	 * 
	 * @param nombre Nombre del directivo
	 * @param apellidos Apellidos del directivo
	 * @param nacionalidad Nacionalidad del directivo
	 * @param equipo Equipo del directivo
	 * @param cargo Cargo del directivo
	 * @return A�ade un directivo si se cumplen las condiciones necesarias
	 * @throws NombreInvalidoException Si el nombre no es correcto
	 * @throws EquipoNullException Si el equipo no es v�lido
	 * @throws FotoNullException Si la foto est� vac�a
	 * @throws NacionalidadNullException Si la nacionalidad est� vac�a
	 * @throws PresidenteYaDefinidoException Si el equipo ya contiene un presidente
	 * @throws IntegranteYaExistenteException  Si el integrante ya existe
	 */
	public boolean anadirDirectivo(String nombre, String apellidos,
			String nacionalidad, Equipo equipo, ImageIcon foto, Cargo cargo)
			throws NombreInvalidoException, EquipoNullException,
			FotoNullException, NacionalidadNullException, PresidenteYaDefinidoException, IntegranteYaExistenteException {
		if (!IntegranteEquipo.validarNombre(nombre))
			throw new NombreInvalidoException(
					"El nombre del directivo es inv�lido");
		if (!IntegranteEquipo.validarNombre(apellidos))
			throw new NombreInvalidoException(
					"Los apellidos del directivo no son v�lidos");
		if (equipo == null)
			throw new EquipoNullException("No has seleccionado un equipo");
		if (nacionalidad.equals(""))
			throw new NacionalidadNullException(
					"No has seleccionado una nacionalidad");
		if (foto == null)
			throw new FotoNullException("No has seleccionado una foto");
		if(cargo == Cargo.PRESIDENTE && contarPresidente())
			throw new PresidenteYaDefinidoException("Este equipo ya tiene un presidente");
		if(plantilla.contains(new Directivo(nombre, apellidos)))
			throw new IntegranteYaExistenteException("Este integrante ya existe");
		return plantilla.add(new Directivo(nombre, apellidos, nacionalidad,
				equipo, foto, cargo));
	}

	/**
	 * M�todo que a�ade un tecnico a la plantilla de un equipo
	 * 
	 * @param nombre Nombre del t�cnico
	 * @param apellidos Apellidos del t�cnico
	 * @param nacionalidad Nacionalidad del t�cnico
	 * @param equipo Equipo del t�cnico
	 * @param titulacion Titulaci�n del t�cnico
	 * @return A�ade un t�cnico si se cumplen las condiciones
	 * @throws NombreInvalidoException Si el nombre es inv�lido
	 * @throws EquipoNullException Si el equipo est� vac�o
	 * @throws NacionalidadNullException Si la nacionalidad est� vac�a
	 * @throws FotoNullException Si la foto est� vac�a
	 * @throws EntrenadorYaExistenteException Si ya existe un entrenador en el equipo
	 * @throws IntegranteYaExistenteException Si el integrante ya existe
	 */
	public boolean anadirTecnico(String nombre, String apellidos,
			String nacionalidad, Equipo equipo, ImageIcon foto,
			Cargo titulacion) throws NombreInvalidoException,
			EquipoNullException, NacionalidadNullException, FotoNullException, EntrenadorYaExistenteException, IntegranteYaExistenteException {
		if (!IntegranteEquipo.validarNombre(nombre))
			throw new NombreInvalidoException(
					"El nombre del t�cnico es inv�lido");
		if (!IntegranteEquipo.validarNombre(apellidos))
			throw new NombreInvalidoException(
					"Los apellidos del t�cnico no son v�lidos");
		if (equipo == null)
			throw new EquipoNullException("No has seleccionado un equipo");
		if (nacionalidad.equals(""))
			throw new NacionalidadNullException(
					"No has seleccionado una nacionalidad");
		if (foto == null)
			throw new FotoNullException("No has seleccionado una foto");
		if(titulacion==Cargo.ENTRENADOR && contarEntrenador())
			throw new EntrenadorYaExistenteException("Ya existe un entrenador para este equipo");
		if(plantilla.contains(new Tecnico(nombre, apellidos)))
			throw new IntegranteYaExistenteException("Este integrante ya existe");
		else
			return plantilla.add(new Tecnico(nombre, apellidos, nacionalidad,
					equipo, foto, titulacion));
	}

	/**
	 * M�todo que a�ade un futbolista a la plantilla de un equipo
	 * 
	 * @param nombre Nombre del futbolista
	 * @param apellidos Apellidos del futbolista
	 * @param nacionalidad Nacionalidad del futbolista
	 * @param equipo Equipo del futbolista
	 * @param dorsal Dorsal del futbolista
	 * @param demarcacion Demarcaci�n en el campos del futbolista
	 * @return A�ade un futbolista al equipo si se cumplen las condiciones
	 * @throws NombreInvalidoException Si el nombre es inv�lido
	 * @throws EquipoNullException Si el equipo est� vac�o
	 * @throws NacionalidadNullException Si la nacionalidad est� vac�a
	 * @throws FotoNullException Si la foto est� vac�a
	 * @throws DorsalNullException Si el dorsal est� vac�o
	 * @throws NumeroMaximoJugadoresException  Si se supera el n�mero m�ximo de jugadores
	 * @throws DorsalExisteException Si el dorsal ya existe
	 * @throws IntegranteYaExistenteException Si el integrante ya existe
	 */
	public boolean anadirFutbolista(String nombre, String apellidos,
			String nacionalidad, Equipo equipo, ImageIcon foto, String dorsal,
			Cargo demarcacion) throws NombreInvalidoException,
			EquipoNullException, NacionalidadNullException, FotoNullException,
			DorsalNullException, NumeroMaximoJugadoresException, DorsalExisteException, IntegranteYaExistenteException {
		if (!IntegranteEquipo.validarNombre(nombre))
			throw new NombreInvalidoException(
					"El nombre del futbolista es inv�lido");
		if (!IntegranteEquipo.validarNombre(apellidos))
			throw new NombreInvalidoException(
					"Los apellidos del futbolista no son v�lidos");
		if (equipo == null)
			throw new EquipoNullException("No has seleccionado un equipo");
		if (nacionalidad.equals(""))
			throw new NacionalidadNullException(
					"No has seleccionado una nacionalidad");
		if (foto == null)
			throw new FotoNullException("No has seleccionado una foto");
		if (dorsal.equals("") || !Futbolista.validarDorsal(dorsal))
			throw new DorsalNullException("El dorsal no es v�lido (1-99)");
		if(!contarJugadores())
			throw new NumeroMaximoJugadoresException("El cupo de futbolistas est� completo.");
		if(dorsalExiste(dorsal))
			throw new DorsalExisteException("El dorsal ya existe");
		if(plantilla.contains(new Futbolista(nombre, apellidos)))
			throw new IntegranteYaExistenteException("Este integrante ya existe");
		else {
			return plantilla.add(new Futbolista(nombre, apellidos,
					nacionalidad, equipo, foto, dorsal, demarcacion));
		}
	}

	/**
	 * M�todo que se utiliza para obtener un integrante del equipo
	 * @param indice Indice del integrante a mostrar
	 * @return Devuelve el integrante si existe o null en caso contrario
	 */
	public IntegranteEquipo get(int indice) {
		if (plantilla.get(indice) != null && !(plantilla.isEmpty()))
			return plantilla.get(indice);
		else if (indice < 0 || indice > (plantilla.size() - 1))
			return null;
		else
			return null;
	}

	/**
	 * M�todo que elimina a un integrante de un equipo
	 * @param integranteEquipo Integrante a eliminar
	 * @return Elimina al integrant si existe o devuelve false en caso contrario
	 */
	public boolean eliminarIntegrante(IntegranteEquipo integranteEquipo) {
		if (plantilla.contains(integranteEquipo))
			return plantilla.remove(integranteEquipo);
		return false;
	}

	/**
	 * Muestra el tama�o de la plantilla del equipo
	 * @return Tama�o de la plantilla
	 */
	public int size() {
		return plantilla.size();
	}

	/**
	 * Getter de nombre
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter de nombre
	 * @param nombre
	 */
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter de estadio
	 * @return
	 */
	public String getEstadio() {
		return estadio;
	}

	/**
	 * Setter de estadio
	 * @param estadio
	 */
	private void setEstadio(String estadio) {
		this.estadio = estadio;
	}

	/**
	 * Getter de fundacion
	 * @return
	 */
	public Calendar getFundacion() {
		return fundacion;
	}

	/**
	 * Setter de fundacion
	 * @param fundacion
	 */
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

	/**
	 * Getter de escudo
	 * @return
	 */
	public ImageIcon getEscudo() {
		return escudo;
	}

	/**
	 * Setter de escudo
	 * @param escudo
	 */
	public void setEscudo(ImageIcon escudo) {
		this.escudo = escudo;
	}

	/**
	 * M�todo que comprueba si el nombre de un equipo es v�lido en base a un patr�n
	 * @param nombre Nombre del equipo
	 * @return True en caso de ser v�lido y false en caso contrario
	 */
	public static boolean validarNombre(String nombre) {
		matcher = PATRON_NOMBRE.matcher(nombre);
		return matcher.matches();

	}
	
	/**
	 * Comprueba si existe un integrante en un equipo
	 * @param integrante Integrante a comprobar
	 * @return True en caso de que exista y False en caso contrario
	 */
	public boolean comprobarExiste(IntegranteEquipo integrante){
		for (IntegranteEquipo integranteEquipo : plantilla) {
			if(integranteEquipo.getNombre().equals(integrante.getNombre()) && integranteEquipo.getApellidos().equals(integrante.getApellidos()))
				return true;
		}
		return false;
	}

	

	@Override
	/**
	 * Metodo hashCode
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	/**
	 * M�todo equals que tiene en cuenta el nombre del equipo
	 */
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
	
	/**
	 * M�todo que cuenta el numero de jugadores para comprobar si se supera el limite
	 * @return True en caso de que no se supere el limite y False en caso contrario
	 */
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
	
	/**
	 * M�todo que compruebe si existe un presidente en un equipo
	 * @return True si existe el presidente y False en caso contrario
	 */
	private boolean contarPresidente(){
		for (int i = 0; i <plantilla.size(); i++) {
			if(plantilla.get(i) instanceof Directivo)
				if(((Directivo) plantilla.get(i)).getCargo()==Cargo.PRESIDENTE)
					return true;
		}
		return false;
	}
	
	/**
	 * M�todo que comprueba si existe un entrenador en un equipo
	 * @return True si existe y False en caso contrario
	 */
	private boolean contarEntrenador(){
		for (int i = 0; i <plantilla.size(); i++) {
			if(plantilla.get(i) instanceof Tecnico)
				if(((Tecnico) plantilla.get(i)).getCargo()==Cargo.ENTRENADOR)
					return true;
		}
		return false;
	}
	
	/**
	 * M�todo que comprueba si existe un dorsal en un equipo
	 * @param dorsal Dorsal a comprobar
	 * @return True si existe el dorsal y false en caso contrario
	 */
	private boolean dorsalExiste(String dorsal){
		for (int i = 0; i < plantilla.size(); i++) {
			if(plantilla.get(i) instanceof Futbolista)
				if(((Futbolista) plantilla.get(i)).getDorsal().equals(dorsal))
					return true;
					
		}
		return false;
	}

}
