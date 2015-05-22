package gui;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import liga.Cargo;
import liga.Directivo;
import liga.Futbolista;
import liga.IntegranteEquipo;
import liga.Tecnico;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que nos muestra los integrantes de un tipo determinado
 * @author Jesús López González
 *
 */
public class MostrarIntegrantePorTipo extends PadreIntegrante {

	private static final long serialVersionUID = 1L;
	private int indice = 0;
	DecimalFormat formato = new DecimalFormat("0.00");
	private ArrayList<IntegranteEquipo> integrantes;
	private JButton btSiguiente;
	private JButton btAnterior;


	/**
	 * Constructor de MostrarIntegrantePorTipo
	 */
	public MostrarIntegrantePorTipo(final ArrayList<IntegranteEquipo> integrantes) {
		super();
		this.integrantes = integrantes;
		cbPaises.setEnabled(false);
		tfTecnica.setEditable(false);
		tfDorsal.setLocation(198, 214);
		lblDorsal.setLocation(24, 217);
		setModal(true);
		setResizable(false);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setText("Volver");
		cbTipo.setEnabled(false);
		setTitle("Integrante por Tipo");
		cbRol.setEnabled(false);
		cbEquipos.setEnabled(false);
		lblImagen.setText("");
		tfDorsal.setEditable(false);
		tfSueldo.setEditable(false);
		tfApellidos.setEditable(false);
		tfNombre.setEditable(false);
		lblTecnica.setVisible(false);
		mostrarIntegrante(integrantes.get(0));
		btAnterior = new JButton("<");
		btAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(integrantes.get(indice-1)!=null){
					mostrarIntegrante(integrantes.get(--indice));
					caparBoton();
				}
			}
		});
		btAnterior.setBounds(260, 177, 67, 35);
		contentPanel.add(btAnterior);
		
		btSiguiente = new JButton(">");
		btSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(integrantes.get(indice+1)!=null)
					mostrarIntegrante(integrantes.get(++indice));
					caparBoton();
			}
		});
		btSiguiente.setBounds(337, 177, 67, 35);
		contentPanel.add(btSiguiente);
		caparBoton();
		btnAnadirFoto.setVisible(false);
		okButton.setVisible(false);
		
	}

	/**
	 * Método que rellena los campos del formulario con las características del integrante a mostar
	 * @param integrante Integrante a mostar
	 */
	private void mostrarIntegrante(IntegranteEquipo integrante) {
		tfNombre.setText(integrante.getNombre());
		tfApellidos.setText(integrante.getApellidos());
		cbEquipos.setSelectedItem((String) integrante.getEquipo().getNombre());
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

	/**
	 * Método que habilita o deshabilita los botones de < y > según el número de equipos
	 * de la liga y la posición en la que nos encontremos
	 */
	private void caparBoton(){
		if(indice-1<0)
			btAnterior.setEnabled(false);
		else
			btAnterior.setEnabled(true);
		if(indice+1>(integrantes.size()-1))
			btSiguiente.setEnabled(false);
		else
			btSiguiente.setEnabled(true);
	}
}
