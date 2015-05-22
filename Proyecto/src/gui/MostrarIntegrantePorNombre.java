package gui;

import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

import liga.Directivo;
import liga.Futbolista;
import liga.IntegranteEquipo;
import liga.Tecnico;

/**
 * Clase que nos muestra un integrante dado un nombre
 * @author Jesús López González
 *
 */
public class MostrarIntegrantePorNombre extends PadreIntegrante {
	
	private static final long serialVersionUID = 1L;
	DecimalFormat formato = new DecimalFormat("0.00");
	int i = 0;


	/**
	 * Constructor de MostrarIntegrantePorNombre
	 */
	public MostrarIntegrantePorNombre() {
		super();
		setTitle("Integrante por Nombre");
		setModal(true);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfNombre.getText().equals("") || tfApellidos.getText().equals(""))
					JOptionPane.showMessageDialog(contentPanel, "Rellena nombres y apellidos", "Aviso", JOptionPane.ERROR_MESSAGE);
				else
					mostrarIntegrante(buscarIntegrante());
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setText("Volver");
		okButton.setText("Buscar");
		btnAnadirFoto.setVisible(false);
		tfDorsal.setEnabled(false);
		tfTecnica.setEnabled(false);
		tfSueldo.setEnabled(false);
		cbTipo.setEnabled(false);
		cbRol.setEnabled(false);
		cbPaises.setEnabled(false);
		cbEquipos.setEnabled(false);

	}
	
	/**
	 * Método que buscar en el equipo al integrante a partir de su nombre y apellidos
	 * @return Devuelve el integrante a buscar.
	 */
	private IntegranteEquipo buscarIntegrante(){
		IntegranteEquipo integrante = null;
		for (int i = 0; i < Gestion.liga.size(); i++) {
			for (int j = 0; j < Gestion.liga.get(i).size(); j++) {
				if (Gestion.liga.get(i).get(j).getNombre().equals(tfNombre.getText()) && Gestion.liga.get(i).get(j).getApellidos().equals(tfApellidos.getText()))
					integrante = Gestion.liga.get(i).get(j);
			}
		}
		return integrante;
	}
	
	/**
	 * Método que rellena los campos del formulario con las características del integrante a mostar
	 * @param integrante Integrante a mostar
	 */
	private void mostrarIntegrante(IntegranteEquipo integrante){
			if (integrante==null){
				JOptionPane.showMessageDialog(contentPanel, "No existe este integrante", "Aviso", JOptionPane.ERROR_MESSAGE);
				limpiarCampos();
			}
			else{
				tfNombre.setText(integrante.getNombre());
				tfApellidos.setText(integrante.getApellidos());
				cbEquipos.setSelectedItem((String) integrante.getEquipo()
						.getNombre());
				cbEquipos.addItem(integrante.getEquipo().getNombre());
				cbEquipos.setSelectedItem(integrante.getEquipo().getNombre());
				if (integrante instanceof Futbolista) {
					cbPaises.addItem(integrante.getNacionalidad());
					cbPaises.setSelectedItem(integrante.getNacionalidad());
					cbRol.setSelectedIndex(0);
					cbTipo.addItem(((Futbolista) integrante).getCargo());
					cbTipo.setSelectedItem(((Futbolista) integrante).getCargo());
					lblDorsal.setVisible(true);
					tfDorsal.setText(String.valueOf(((Futbolista) integrante)
							.getDorsal()));
					tfDorsal.setVisible(true);
					lblTecnica.setVisible(true);
					tfTecnica.setText(String.valueOf(formato
							.format(((Futbolista) integrante).getTecnica())));
					tfTecnica.setVisible(true);
				} else if (integrante instanceof Tecnico) {
					cbPaises.addItem(integrante.getNacionalidad());
					cbPaises.setSelectedItem(integrante.getNacionalidad());
					cbRol.setSelectedIndex(1);
					cbTipo.addItem(((Tecnico) integrante).getCargo());
					cbTipo.setSelectedItem(((Tecnico) integrante).getCargo());
					lblDorsal.setVisible(false);
					tfDorsal.setVisible(false);
					lblTecnica.setVisible(true);
					tfTecnica.setText(String.valueOf(formato
							.format(((Tecnico) integrante).getTecnica())));
					tfTecnica.setVisible(true);
				} else if (integrante instanceof Directivo) {
					cbPaises.addItem(integrante.getNacionalidad());
					cbPaises.setSelectedItem(integrante.getNacionalidad());
					cbRol.setSelectedIndex(2);
					cbTipo.addItem(((Directivo) integrante).getCargo());
					cbTipo.setSelectedItem(((Directivo) integrante).getCargo());
					lblDorsal.setVisible(false);
					tfDorsal.setVisible(false);
					tfTecnica.setVisible(false);
					lblTecnica.setVisible(false);
				}
				tfSueldo.setText(String.valueOf(integrante.getSueldo()));
				tfSueldo.setVisible(true);
				lblSueldo_1.setVisible(true);
				lblImagen.setIcon(integrante.getFoto());
			}
	}
	
	/**
	 * Método que resetea los campos del formulario
	 */
	private void limpiarCampos() {
		tfNombre.setText("");
		tfApellidos.setText("");;
		cbEquipos.removeAll();
		cbRol.removeAll();
		cbTipo.removeAll();
		tfSueldo.setText("");
		tfDorsal.setText("");
		lblImagen.setIcon(null);
		tfTecnica.setText("");
		cbPaises.removeAllItems();
	}

}
