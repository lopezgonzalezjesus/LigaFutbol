package gui;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import liga.Equipo;
import liga.Liga;
import datechooser.beans.DateChooserCombo;
import excepciones.EstadioInvalidoException;
import excepciones.ImagenNoSeleccionadaException;
import excepciones.NombreInvalidoException;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class NuevoEquipo extends PadreEquipo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DateChooserCombo dateChooserCombo;

	/**
	 * Create the dialog.
	 */
	public NuevoEquipo(final Liga liga) {
		super();
		setModal(true);
		setResizable(false);
		tfNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!Equipo.validarNombre(tfNombre.getText()))
					tfNombre.setForeground(java.awt.Color.RED);
			}
			@Override
			public void focusGained(FocusEvent e) {
					tfNombre.setForeground(java.awt.Color.BLACK);
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				annadirEquipo();
			}
		});
		cancelButton.setText("Cancelar");
		okButton.setText("A\u00F1adir");
		lblImagen.setBounds(256, 11, 168, 146);
		btAnadirEscudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser elegirImagen = new JFileChooser();
				elegirImagen.setCurrentDirectory(new File("src\\imagenes\\"));
				if(JFileChooser.APPROVE_OPTION == elegirImagen.showOpenDialog(elegirImagen))
					lblImagen.setIcon((Gestion.abrirImagen(elegirImagen.getSelectedFile())));
			}
		});
		panelDatos.setBounds(21, 11, 206, 146);
		
		dateChooserCombo = new DateChooserCombo();
		dateChooserCombo.setNothingAllowed(false);
		dateChooserCombo.setBounds(10, 115, 155, 20);
		panelDatos.add(dateChooserCombo);
		tfFundacion.setVisible(false);
	}
	
	private void annadirEquipo(){
		try {
			Gestion.liga.anadirEquipo(tfNombre.getText(), tfEstadio.getText(), dateChooserCombo.getSelectedDate(), (ImageIcon)(lblImagen.getIcon()));
			JOptionPane.showMessageDialog(contentPanel, "El equipo se ha añadido con éxito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			limpiarCampos();
		} catch (NombreInvalidoException | EstadioInvalidoException
				| ImagenNoSeleccionadaException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Metodo que resetea los campos del formulario
	 */
	private void limpiarCampos() {
		tfNombre.setText("");
		tfEstadio.setText("");;
		tfFundacion.setText("");
		lblImagen.setIcon(null);
	}
}
