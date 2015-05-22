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
import javax.swing.JScrollPane;

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
		setTitle("Ayuda");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 800, 671);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("<html>\r\n<head></head>\r\n<body><h1> Instrucciones de La Liga</h1>\r\n<h3>A\u00F1adir Equipo</h3>\r\n<ul>\r\n<li>La Liga dispondr\u00E1 de un n\u00FAmero de equipos indefinido, ya que en esta versi\u00F3n a\u00FAn no existir\u00E1 el modo competici\u00F3n. </li>\r\n<li>Dos equipos no pueden tener el mismo nombre.</li>\r\n<li>El nombre del equipo y su estadio deben estar formados por una o varias palabras que comiencen por letra may\u00FAscula. Ej: Real Madrid, Atl\u00E9tico De Madrid, Vicente Calderon, etc.</li>\r\n<li>Cada equipo debe tener un escudo</li></ul>\r\n</br>\r\n<h3>A\u00F1adir Integrante</h3>\r\n<ul><li>Un equipo puede contener en su plantilla Futbolistas, T\u00E9cnicos y Directivos</li>\r\n<li>El nombre y los apellidos de los integrantes deben estar compuestos por palabras que empiecen por may\u00FAscula y contengan tildes, el car\u00E1cter \u00E7 y la \u00F1</li>\r\n<li>Un equipo puede tener un m\u00E1ximo de 25 Futbolistas</li>\r\n<li>Un equipo s\u00F3lo puede tener un Entrenador</li>\r\n<li>Un equipo s\u00F3lo puede tener un Presidente</li>\r\n<li>El dorsal de un Futbolista es \u00FAnico</li>\r\n<li>Un equipo no puede tener dos miembros con el mismo nombre y apellidos</li>\r\n<li>Cada integrante debe disponer de una foto, una nacionalidad, pertenecer a un equipo y tener definido un cargo dentro de la plantilla</li>\r\n<li>La t\u00E9cnica de los integrantes Futbolista y T\u00E9cnico se generar\u00E1 de forma aleatoria atendiendo a las constantes de la interfaz Entrenable</li>\r\n<li>El sueldo de un Futbolista se generar\u00E1 en funci\u00F3n de su t\u00E9cnica. En el caso de los Directivos, el sueldo ser\u00E1 de 1.000.000 para el Presidente y de 500.000 para los demas Directivos. Los Entrenadores cobrar\u00E1n 800.000 y los Masajistas 500.000</li></ul>\r\n</br>\r\n<h3>Eliminar Equipos o Integrantes</h3>\r\n<ul><li>Para eliminar un equipo debemos de entrar en la pesta\u00F1a de Equipos y pulsar en Eliminar. Si eliminamos un equipo se eliminar\u00E1 toda su plantilla.</li>\r\n<li>Para eliminar un integrante, debemos de irnos a su equipo y hacer click en Ver Plantilla. Desde ah\u00ED podremos eliminar un integrante</li></ul>");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		contentPanel.add(lblNewLabel_1);
		lblNewLabel_1.setBounds(10, 0, 774, 598);
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
