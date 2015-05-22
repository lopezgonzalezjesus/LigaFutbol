package liga;

import java.io.Serializable;

import javax.swing.ImageIcon;

public abstract class IntegranteEquipoEntrenable extends IntegranteEquipo implements Entrenable, Serializable{

	private static final long serialVersionUID = 1L;
	protected float tecnica;
	protected float tecnicaAnterior;
	protected float tecnicaGanada=0;
	
	public IntegranteEquipoEntrenable(String nombre, String apellidos,
			String nacionalidad, Equipo equipo, ImageIcon foto, Cargo cargo) {
		super(nombre, apellidos, nacionalidad, equipo, foto, cargo);
	}

	public IntegranteEquipoEntrenable(String nombre, String apellidos) {
		super(nombre, apellidos);
	}


	

}
