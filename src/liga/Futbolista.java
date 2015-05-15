package liga;

import java.io.Serializable;
import javax.swing.ImageIcon;

public class Futbolista extends IntegranteEquipo implements Entrenable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dorsal =null;
	private Demarcacion demarcacion;
	private float tecnica;
	private float tecnicaAnterior;
	private float tecnicaGanada=0;

	public Futbolista(int id) {
		super(id);
	}

	public Futbolista(String nombre, String apellidos,
			String nacionalidad, Equipo equipo, ImageIcon foto, String dorsal, Demarcacion demarcacion) {
		super(nombre, apellidos, nacionalidad, equipo, foto);
		generarTecnica();
		generarSueldo();
		setDorsal(dorsal);
		setDemarcacion(demarcacion);
	}

	
	
	public String getDorsal() {
		return dorsal;
	}

	private void setDorsal(String dorsal) {
		this.dorsal = dorsal;
	}

	public Demarcacion getDemarcacion() {
		return demarcacion;
	}

	private void setDemarcacion(Demarcacion demarcacion) {
		this.demarcacion = demarcacion;
	}

	public float getTecnica() {
		return tecnica;
	}

	private void setTecnica(float tecnica) {
		this.tecnica = tecnica;
	}

	@Override
	public void generarSueldo() {
		setSueldo(10000*getTecnica());

	}

	@Override
	public void generarTecnica(){
		setTecnica((float)(Math.random()*101+1));
	}
	
	@Override
	public void entrenar(){
		setTecnicaAnterior(getTecnica());
		float tecnicaResultante = getTecnica()+(float)(Math.random());
			if(tecnicaResultante<=100){
				setTecnica(tecnicaResultante);
				setTecnicaGanada(getTecnica()-getTecnicaAnterior());
			}
	}

	

	@Override
	public String toString() {
		return "Futbolista [dorsal=" + dorsal + ", demarcacion=" + demarcacion
				+ ", tecnica=" + tecnica + ", tecnicaAnterior="
				+ tecnicaAnterior + ", tecnicaGanada=" + tecnicaGanada + "]";
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

	public void setTecnicaGanada(float tecnicaGanada) {
		this.tecnicaGanada = tecnicaGanada;
	}
	
	
}
