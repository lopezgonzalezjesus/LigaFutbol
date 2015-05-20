package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase Ayuda en la que se explican las características que requieren los diferentes objetos de la aplicación.
 * @author Jesús López González
 *
 */
public class Ayuda extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructor de Ayuda
	 */
	public Ayuda() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>\r\n<head></head>\r\n<body><h1> Instrucciones de La Liga</h1>\r\n<ul>\r\n<li>La Liga dispondr\u00E1 de un n\u00FAmero de equipos indefinido, ya que en esta versi\u00F3n a\u00FAn no existir\u00E1 el modo competici\u00F3n. </li>\r\n<li>Cada equipo puede contar en su plantilla con 25 miembros, de los cuales como m\u00E1ximo, puede existir un entrenador y un presidente. El tipo de resto de miembros es indiferente.</li>\r\n<li>Dos equipos no pueden tener el mismo nombre</li>\r\n<li>Un equipo no puede contener dos integrantes con el mismo nombre y apellidos.</li>\r\n<li>No se pueden crear integrantes sin antes haber creado al menos un equipos</li></ul>");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBounds(0, 0, 434, 228);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Volver");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
