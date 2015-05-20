package liga;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Tecnico extends IntegranteEquipo implements Entrenable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Titulacion titulacion;
	private float tecnica;
	private float tecnicaAnterior;
	private float tecnicaGanada;

	public Tecnico(int id) {
		super(id);
	}
	
	public Tecnico(String nombre, String apellidos,
			String nacionalidad, Equipo equipo, ImageIcon foto, Titulacion titulacion) {
		super(nombre, apellidos, nacionalidad, equipo, foto);
		setTitulacion(titulacion);
		generarTecnica();
		generarSueldo();
	}
	
	public Titulacion getTitulacion() {
		return titulacion;
	}

	private void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}

	public float getTecnica() {
		return tecnica;
	}

	private void setTecnica(float tecnica) {
		this.tecnica = tecnica;
	}

	@Override
	public void generarSueldo() {
		if(getTitulacion()==Titulacion.ENTRENADOR)
			setSueldo(800000);
		else
			setSueldo(500000);

	}

	@Override
	public void generarTecnica(){
		setTecnica((float)(Math.random()*51+1));
	}
	
	@Override
	public void entrenar(){
		setTecnicaAnterior(getTecnica());
		float tecnicaResultante = getTecnica()+(float)(Math.random()*0.5);
		if(tecnicaResultante<=100){
			setTecnica(tecnicaResultante);
			setTecnicaGanada(getTecnica()-getTecnicaAnterior());
		}
	}

	public float getTecnicaAnterior() {
		return tecnicaAnterior;
	}

	public void setTecnicaAnterior(float tecnicaAnterior) {
		this.tecnicaAnterior = tecnicaAnterior;
	}

	public float getTecnicaGanada() {
		return tecnicaGanada;
	}

	public void setTecnicaGanada(float tecnicaResultante) {
		this.tecnicaGanada = tecnicaResultante;
	}
}
