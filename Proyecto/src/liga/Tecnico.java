package liga;

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Clase Tecnico que sirve para definir un técnico y sus características
 * @author Jesús López González
 *
 */
public class Tecnico extends IntegranteEquipoEntrenable implements Serializable {

	private static final long serialVersionUID = 1L;
	private float tecnica;
	private float tecnicaAnterior;
	private float tecnicaGanada;

	/**
	 * Constructor de Tecnico por nombre y apellido
	 * @param nombre Nombre del tecnico
	 * @param apellidos Apellidos del tecnico
	 */
	public Tecnico(String nombre, String apellidos) {
		super(nombre, apellidos);
	}
	
	/**
	 * Constructor de Tecnico
	 * @param nombre Nombre del tecnico
	 * @param apellidos Apellidos del tecnico
	 * @param nacionalidad Nacionalidad del Tecnico
	 * @param equipo Equipo del tecnico
	 * @param foto Foto del tecnico
	 * @param cargo Cargo de tecnico
	 */
	public Tecnico(String nombre, String apellidos,
			String nacionalidad, Equipo equipo, ImageIcon foto, Cargo cargo) {
		super(nombre, apellidos, nacionalidad, equipo, foto, cargo);
		generarTecnica();
		generarSueldo();
	}

	/**
	 * Getter de tecnica
	 * @return
	 */
	public float getTecnica() {
		return tecnica;
	}

	/**
	 * Setter de tecnica
	 * @param tecnica
	 */
	private void setTecnica(float tecnica) {
		this.tecnica = tecnica;
	}

	@Override
	/**
	 * Método que genera el sueldo de un tecnico en funcion de su cargo
	 */
	public void generarSueldo() {
		if(getCargo()==Cargo.ENTRENADOR)
			setSueldo(800000);
		else
			setSueldo(500000);

	}

	@Override
	/**
	 * Método que genera la técnica de un técnico de forma aleatoria entre y 1 50
	 */
	public void generarTecnica(){
		setTecnica((float)(Math.random()*51+1));
	}
	
	@Override
	/**
	 * Método que aumenta la técnica de un técnico de forma aleatoria (entrenar)
	 */
	public void entrenar(){
		setTecnicaAnterior(getTecnica());
		float tecnicaResultante = getTecnica()+(float)(Math.random()*Entrenable.TECNICA_TECNICO);
		if(tecnicaResultante<=100){
			setTecnica(tecnicaResultante);
			setTecnicaGanada(getTecnica()-getTecnicaAnterior());
		}
	}

	/**
	 * Getter de TecnicaAnterior
	 * @return
	 */
	public float getTecnicaAnterior() {
		return tecnicaAnterior;
	}
	
	/**
	 * Setter de TecnicaAnterios
	 * @param tecnicaAnterior
	 */
	public void setTecnicaAnterior(float tecnicaAnterior) {
		this.tecnicaAnterior = tecnicaAnterior;
	}

	/**
	 * Getter de TecnicaGanada
	 * @return
	 */
	public float getTecnicaGanada() {
		return tecnicaGanada;
	}
	
	/**
	 * Setter de TecnicaGanada
	 * @param tecnicaResultante
	 */
	public void setTecnicaGanada(float tecnicaResultante) {
		this.tecnicaGanada = tecnicaResultante;
	}
}
