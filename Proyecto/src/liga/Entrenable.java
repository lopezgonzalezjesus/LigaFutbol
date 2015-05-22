package liga;

/**
 * Interfaz entrenable que se aplica a las clases Tecnico y Futbolista
 * @author Jesús López González
 *
 */
public interface Entrenable {
	
	public static final float TECNICA_TECNICO = 0.5f;
	public static final float TECNICA_JUGADOR = 1f;
	/**
	 * Metodo que entrena a un integrante de tipo Futbolista o Técnico
	 */
	void entrenar();
	
	/**
	 * Método que genera la técnica de un Futbolista o Técnico
	 */
	void generarTecnica();

}
