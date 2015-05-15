package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

public class PadreEquipo extends JDialog {

	protected final JPanel contentPanel = new JPanel();
	protected JTextField tfNombre;
	protected JTextField tfEstadio;
	protected JTextField tfFundacion;
	protected JPanel panelDatos;
	protected JButton btAnadirEscudo;
	protected JLabel lblNombre;
	protected JLabel lblEstadio;
	protected JLabel lblFundacion;
	protected JLabel lblImagen;
	protected JPanel buttonPane;
	protected JButton okButton;
	protected JButton cancelButton;


	/**
	 * Create the dialog.
	 */
	public PadreEquipo() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			panelDatos = new JPanel();
			panelDatos.setBorder(new TitledBorder(null, "Datos club", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDatos.setBounds(21, 11, 206, 123);
			contentPanel.add(panelDatos);
			panelDatos.setLayout(null);
			{
				tfNombre = new JTextField();
				tfNombre.setBounds(90, 30, 86, 20);
				panelDatos.add(tfNombre);
				tfNombre.setColumns(10);
			}
			{
				tfEstadio = new JTextField();
				tfEstadio.setBounds(90, 61, 86, 20);
				panelDatos.add(tfEstadio);
				tfEstadio.setColumns(10);
			}
			{
				tfFundacion = new JTextField();
				tfFundacion.setBounds(90, 92, 86, 20);
				panelDatos.add(tfFundacion);
				tfFundacion.setColumns(10);
			}
			{
				lblNombre = new JLabel("Nombre");
				lblNombre.setBounds(10, 33, 59, 14);
				panelDatos.add(lblNombre);
			}
			{
				lblEstadio = new JLabel("Estadio");
				lblEstadio.setBounds(10, 64, 59, 14);
				panelDatos.add(lblEstadio);
			}
			{
				lblFundacion = new JLabel("Fundacion");
				lblFundacion.setBounds(10, 95, 59, 14);
				panelDatos.add(lblFundacion);
			}
		}
		{
			btAnadirEscudo = new JButton("A\u00F1adir escudo...");
			btAnadirEscudo.setBounds(21, 168, 113, 23);
			contentPanel.add(btAnadirEscudo);
		}
		{
			lblImagen = new JLabel("");
			lblImagen.setBounds(307, 55, 46, 14);
			contentPanel.add(lblImagen);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
