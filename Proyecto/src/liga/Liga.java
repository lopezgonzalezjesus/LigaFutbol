package liga;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;

import excepciones.EquipoNoExisteException;
import excepciones.EstadioExisteException;
import excepciones.EstadioInvalidoException;
import excepciones.ImagenNoSeleccionadaException;
import excepciones.NombreInvalidoException;
import gui.Gestion;

/**
 * Clase Liga de la que colgarán las demás clases. Una liga contendrá Equipos, que a su vez, contendrán Integrantes.
 * @author Jesús López González
 *
 */
public class Liga implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * ArrayList que contiene los equipos de la liga
	 */
	public ArrayList<Equipo> equipos = new ArrayList<Equipo>();
	
	/**
	 * Método que extrae los equipos de la liga en un array de String
	 * @return Array de String de equipos de la liga
	 */
	public String[] extraerEquipos(){
		String[] clubs = new String[equipos.size()];
		for (int j = 0; j < equipos.size(); j++) {
			clubs[j]=equipos.get(j).getNombre();
		}
		return clubs;
	}
	
	/**
	 * Método que añade un equipo a la liga siempre que se cumplan las condiciones
	 * @param nombre Nombre del equipo
	 * @param estadio Estadio del equipo
	 * @param fundacion Fecha de fundación del equipo
	 * @param escudo Escudo del equipo
	 * @return Añade un equipo a la liga
	 * @throws NombreInvalidoException Si el nombre del equipo no es válido	
	 * @throws EstadioInvalidoException Si el nombre del estadio no es válido
	 * @throws ImagenNoSeleccionadaException Si no se ha seleccionado imagen para el escudo del equipo
	 * @throws EstadioExisteException Si el estadio pertenece a otro equipo
	 */
	public boolean anadirEquipo(String nombre, String estadio, Calendar fundacion, ImageIcon escudo) throws NombreInvalidoException, EstadioInvalidoException, ImagenNoSeleccionadaException, EstadioExisteException{
		if(!Equipo.validarNombre(nombre))
			throw new NombreInvalidoException("Nombre del equipo Invalido");
		else if(!Equipo.validarNombre(estadio))
			throw new EstadioInvalidoException("Nombre del estadio Invalido");
		else if(escudo==null)
			throw new ImagenNoSeleccionadaException("No has seleccionado una imagen");
		else if(existeEstadio(estadio))
			throw new EstadioExisteException("Este estadio ya es de otro equipo");
		else{
			Gestion.setModificado(true);
			return equipos.add(new Equipo(nombre, estadio, fundacion, escudo));
		}
	}

	/**
	 * Método que elimina un equipo de la liga
	 * @param nombre Nombre del equipo
	 * @return Elimina un equipo de la Liga
	 * @throws EquipoNoExisteException Si el equipo no existe
	 */
	public boolean eliminarEquipo(String nombre) throws EquipoNoExisteException{
		Gestion.setModificado(true);
		return equipos.remove(new Equipo(nombre));
	}
	
	/**
	 * Devuelve el numero de equipos de la liga
	 * @return Numero de equipos de la liga
	 */
	public int size(){
		return equipos.size();
	}
	
	/**
	 * Devuelve un equipo de la liga
	 * @param indice Indice del equipo a seleccionar
	 * @return Devuelve un equipo en caso de que se encuentre en el indice y null en caso contrario
	 */
	public Equipo get(int indice){
		if(equipos.get(indice)!=null && !(equipos.isEmpty()))
			return equipos.get(indice);
		else if(indice<0 || indice>(equipos.size()-1))
				return null;
		else
			return null;
	}
	
	/**
	 * Método que comprueba si existe un estadio
	 * @param estadio Nombre del estadio
	 * @return Devuelve True en caso de que exista y False en caso contrario.
	 */
	private boolean existeEstadio(String estadio){
		for (int i = 0; i < equipos.size(); i++) {
			if(equipos.get(i).getEstadio().equals(estadio))
				return true;
		}
		return false;
	}
	
}
