package gui;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Calendar;

import liga.Equipo;
import liga.Liga;
import datechooser.beans.DateChooserCombo;
import excepciones.EstadioExisteException;
import excepciones.EstadioInvalidoException;
import excepciones.ImagenNoSeleccionadaException;
import excepciones.NombreInvalidoException;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Clase que nos permite crear un nuevo equipo en nuestra Liga
 * @author Jesús López González
 *
 */
public class NuevoEquipo extends PadreEquipo {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DateChooserCombo dateChooserCombo;

	/**
	 * Constuctor de NuevoEquipo
	 */
	public NuevoEquipo(final Liga liga) {
		super();
		btAnadirEscudo.setText("A\u00F1adir escudo");
		btAnadirEscudo.setBounds(21, 168, 137, 23);
		setTitle("Nuevo Equipo");
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
		tfEstadio.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				tfEstadio.setForeground(java.awt.Color.BLACK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(!Equipo.validarNombre(tfNombre.getText()))
					tfEstadio.setForeground(java.awt.Color.RED);
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
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG","jpg","png");
				elegirImagen.setFileFilter(filtro);
				elegirImagen.setCurrentDirectory(new File("src\\imagenes\\"));
				if(JFileChooser.APPROVE_OPTION == elegirImagen.showOpenDialog(elegirImagen))
					lblImagen.setIcon((Gestion.abrirImagen(elegirImagen.getSelectedFile())));
			}
		});
		panelDatos.setBounds(21, 11, 206, 146);
		
		dateChooserCombo = new DateChooserCombo();
		dateChooserCombo.setNothingAllowed(false);
		dateChooserCombo.setBounds(10, 115, 155, 20);
		dateChooserCombo.setMaxDate(Calendar.getInstance());
		panelDatos.add(dateChooserCombo);
		tfFundacion.setVisible(false);
	}
	
	/**
	 * Método que nos añade un nuevo equipo a la liga siempre que ya no exista previamente
	 */
	private void annadirEquipo(){
		if(!Gestion.liga.equipos.contains(new Equipo(tfNombre.getText())))
			try {
				Gestion.liga.anadirEquipo(tfNombre.getText(), tfEstadio.getText(), dateChooserCombo.getSelectedDate(), (ImageIcon)(lblImagen.getIcon()));
				JOptionPane.showMessageDialog(contentPanel, "El equipo se ha añadido con éxito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
			} catch (NombreInvalidoException | EstadioInvalidoException
					| ImagenNoSeleccionadaException | EstadioExisteException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		else
			JOptionPane.showMessageDialog(contentPanel, "Este equipo ya existe en la liga", "Error", JOptionPane.ERROR_MESSAGE);
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
