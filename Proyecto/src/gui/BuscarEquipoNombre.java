package gui;

import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import liga.Equipo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase BuscarEquipoNombre que nos permite buscar un equipo seg�n su nombre.
 * @author Jes�s L�pez Gonz�lez
 *
 */
public class BuscarEquipoNombre extends PadreEquipo {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de BuscarEquipoNombre
	 */
	public BuscarEquipoNombre() {
		super();
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		setTitle("Buscar Equipo");
		lblImagen.setBounds(247, 11, 130, 149);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarEquipo(buscarEquipo());
			}
		});
		cancelButton.setText("Volver");
		okButton.setText("Buscar");
		tfFundacion.setEditable(false);
		tfEstadio.setEditable(false);
		btAnadirEscudo.setVisible(false);
		lblImagen.setVisible(true);
	}

	/**
	 * M�todo que nos muestra las caracter�sticas de un equipo
	 * @param equipo Equipo a mostrar
	 */
	private void mostrarEquipo(Equipo equipo){
		if (equipo==null) {
			JOptionPane.showMessageDialog(contentPanel, "Este equipo no existe", "Aviso", JOptionPane.ERROR_MESSAGE);
			limpiarCampos();
		}
		else{
			tfNombre.setText(equipo.getNombre());
			tfEstadio.setText(equipo.getEstadio());
			tfFundacion.setText(String.valueOf(new SimpleDateFormat(
					"dd-MM-yyyy").format(equipo.getFundacion().getTime())));
			lblImagen.setIcon(equipo.getEscudo());
		}
	}
	
	/**
	 * M�todo que busca un equipo en nuestra liga mediante el nombre
	 * @return Devuelve el equipo si lo encuentra y null en caso contrario
	 */
	private Equipo buscarEquipo(){
		for (int i = 0; i < Gestion.liga.size(); i++) {
			if(Gestion.liga.get(i).getNombre().equals(tfNombre.getText()))
					return Gestion.liga.get(i);
		}
		return null;
	}
	
	/**
	 * M�todo que limpia los campos de la interfaz despu�s de realizar una b�squeda
	 */
	private void limpiarCampos(){
		tfNombre.setText("");
		tfEstadio.setText("");
		tfFundacion.setText("");
		lblImagen.setIcon(null);
	}
	
}
