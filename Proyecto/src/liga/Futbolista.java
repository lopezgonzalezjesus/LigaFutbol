package liga;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

/**
 * Clase Futbolista que hereda de IntegranteEquipo y nos permite especificar las caracter�sticas de un futbolista
 * @author Jes�s L�pez Gonz�lez
 *
 */
public class Futbolista extends IntegranteEquipoEntrenable implements Serializable {
	
	/**
	 * Patr�n para limitar el dorsal de un jugador de 1 a 99
	 */
	private static final Pattern PATRON_DORSAL = Pattern.compile("^[1-9][\\d]?$");
	private static Matcher matcher;
	private static final long serialVersionUID = 1L;
	private String dorsal =null;
	private float tecnica;
	private float tecnicaAnterior;
	private float tecnicaGanada=0;

	/**
	 * Constructor de Futbolista a partir de nombre y apellidos
	 * @param nombre Nombre del Futbolista
	 * @param apellidos Apellidos del Futbolista
	 */
	public Futbolista(String nombre, String apellidos) {
		super(nombre, apellidos);
	}

	/**
	 * Constructor del Futbolista
	 * @param nombre Nombre del futbolista
	 * @param apellidos Apellidos del Futbolista
	 * @param nacionalidad Nacionalidad del Futbolista
	 * @param equipo Equipo del Futbolista
	 * @param foto Foto del Futbolista
	 * @param dorsal Dorsal del Futbolista
	 * @param cargo Puesto del Futbolista
	 */
	public Futbolista(String nombre, String apellidos,
			String nacionalidad, Equipo equipo, ImageIcon foto, String dorsal, Cargo cargo) {
		super(nombre, apellidos, nacionalidad, equipo, foto, cargo);
		generarTecnica();
		generarSueldo();
		setDorsal(dorsal);
	}

	
	/**
	 * Getter de dorsal
	 * @return
	 */
	public String getDorsal() {
		return dorsal;
	}

	/**
	 * Setter de dorsal
	 * @param dorsal
	 */
	private void setDorsal(String dorsal) {
		this.dorsal = dorsal;
	}

	/**
	 * Getter de cargo
	 */
	public Cargo getCargo() {
		return cargo;
	}

	/**
	 * Getter de Tecnica
	 * @return
	 */
	public float getTecnica() {
		return tecnica;
	}

	/**
	 * Setter de Tecnica
	 * @param tecnica
	 */
	private void setTecnica(float tecnica) {
		this.tecnica = tecnica;
	}

	@Override
	/**
	 * Metodo que genera el sueldo multiplicando por 10000 cada unidad de t�cnica del futbolista
	 */
	public void generarSueldo() {
		setSueldo(10000*getTecnica());

	}

	@Override
	/**
	 * M�todo que genera la t�cnica de un futbolista aleatoriamente entre 1 y 100
	 */
	public void generarTecnica(){
		setTecnica((float)(Math.random()*100+1));
	}
	
	@Override
	/**
	 * M�todo que le suma una cantidad de t�cnica a la t�cnica propia del jugador (entrenar)
	 */
	public void entrenar(){
		setTecnicaAnterior(getTecnica());
		float tecnicaResultante = getTecnica()+(float)(Math.random()*Entrenable.TECNICA_JUGADOR);
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
	 * Setter de TecnicaAnterior
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
	 * @param tecnicaGanada
	 */
	public void setTecnicaGanada(float tecnicaGanada) {
		this.tecnicaGanada = tecnicaGanada;
	}
	
	/**
	 * M�todo que comprueba si el dorsal de un jugador es v�lido o no
	 * @param dorsal Dorsal del jugador
	 * @return True en caso de ser valido y False en caso contrario
	 */
	public static boolean validarDorsal(String dorsal){
		matcher = PATRON_DORSAL.matcher(dorsal);
		return matcher.matches();
		
	}
	
}
