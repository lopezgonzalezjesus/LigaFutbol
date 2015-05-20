package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Clase AcercaDe que nos informa sobre los créditos de la aplicación
 * @author Jesús López González
 *
 */
public class AcercaDe extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	/**
	 * Constructor de AcercaDe
	 */
	public AcercaDe() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextArea txtrLigaDeFutbol = new JTextArea();
		txtrLigaDeFutbol.setEditable(false);
		txtrLigaDeFutbol.setText("Proyecto: Liga de Futbol\r\nAutor: Jes\u00FAs L\u00F3pez Gonz\u00E1lez\r\n2015 \u00A9\r\n");
		txtrLigaDeFutbol.setBounds(0, 0, 434, 228);
		contentPanel.add(txtrLigaDeFutbol);
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
