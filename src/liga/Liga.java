package liga;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;

import excepciones.EquipoNoExisteException;
import excepciones.EstadioInvalidoException;
import excepciones.ImagenNoSeleccionadaException;
import excepciones.NombreInvalidoException;
import gui.Gestion;

public class Liga implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public ArrayList<Equipo> equipos = new ArrayList<Equipo>();
	
	public String[] extraerEquipos(){
		String[] clubs = new String[equipos.size()];
		for (int j = 0; j < equipos.size(); j++) {
			clubs[j]=equipos.get(j).getNombre();
		}
		return clubs;
	}
	
	public boolean anadirEquipo(String nombre, String estadio, Calendar fundacion, ImageIcon escudo) throws NombreInvalidoException, EstadioInvalidoException, ImagenNoSeleccionadaException{
		if(!Equipo.validarNombre(nombre))
			throw new NombreInvalidoException("Nombre del equipo Invalido");
		else if(!Equipo.validarNombre(estadio))
			throw new EstadioInvalidoException("Nombre del estadio Invalido");
		else if(escudo==null)
			throw new ImagenNoSeleccionadaException("No has seleccionado una imagen");
		else{
			Gestion.setModificado(true);
			return equipos.add(new Equipo(nombre, estadio, fundacion, escudo));
		}
	}

	public boolean eliminarEquipo(String nombre) throws EquipoNoExisteException{
		Gestion.setModificado(true);
		return equipos.remove(new Equipo(nombre));
	}
	
	public int size(){
		return equipos.size();
	}
	
	public Equipo get(int indice){
		if(equipos.get(indice)!=null && !(equipos.isEmpty()))
			return equipos.get(indice);
		else if(indice<0 || indice>(equipos.size()-1))
				return null;
		else
			return null;
	}	
	
}
