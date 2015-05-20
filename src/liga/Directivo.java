package liga;

import java.io.Serializable;
import java.util.Calendar;

import javax.swing.ImageIcon;

public class Directivo extends IntegranteEquipo implements Retributable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cargo cargo;

	public Directivo(String nombre, String apellidos,
			 String nacionalidad, Equipo equipo, ImageIcon foto, Cargo cargo) {
		super(nombre, apellidos, nacionalidad, equipo, foto);
		setCargo(cargo);
		generarSueldo();
	}

	
	
	public Cargo getCargo() {
		return cargo;
	}



	private void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}


	@Override
	public void generarSueldo(){
		if(getCargo()==Cargo.PRESIDENTE)
			setSueldo(1000000);
		else
			setSueldo(500000);
	}
}
