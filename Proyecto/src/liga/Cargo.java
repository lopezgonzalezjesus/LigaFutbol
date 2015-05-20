package liga;

/**
 * Enum de los diferentes cargos que puede tomar un integrante dependiendo de su tipo
 * @author Jesús López González
 *
 */
public enum Cargo {
	PRESIDENTE(Directivo.class),
	DIRECTIVO(Directivo.class),
	PORTERO(Futbolista.class),
	DEFENSA(Futbolista.class),
	CENTROCAMPISTA(Futbolista.class),
	DELANTERO(Futbolista.class),
	MASAJISTA(Tecnico.class),
	ENTRENADOR(Tecnico.class);
	
	/**
	 * Clase del integrante
	 */
	private Class clase;
	
	/**
	 * Constructor de Cargo
	 * @param clase
	 */
	Cargo(Class clase){
		setClase(clase);
	}

	/**
	 * Getter de clase
	 * @return
	 */
	public Class getClase() {
		return clase;
	}

	/**
	 * Setter de clase
	 * @param clase
	 */
	public void setClase(Class clase) {
		this.clase = clase;
	}
	
	
}

