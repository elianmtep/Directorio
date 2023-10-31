import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class interfazdir extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	DefaultListModel modelo = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfazdir frame = new interfazdir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public interfazdir() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(55, 45, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Telefono:");
		lblNewLabel_1.setBounds(55, 94, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Correo:");
		lblNewLabel_2.setBounds(55, 149, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(146, 42, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(146, 91, 86, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(146, 146, 86, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.setBounds(55, 206, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter("agenda.txt",true));
						String cadena = txtNombre.getText()+ " , " + txtTelefono.getText()+ "," +txtCorreo.getText();
						out.write(cadena);
						txtNombre.setText("");
						txtTelefono.setText("");
						txtCorreo.setText("");
						out.newLine();
						out.close();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
			}
				
			
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setBounds(171, 206, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomBuscar = JOptionPane.showInputDialog("Que nombre quieres encontrar?");
				try {
				BufferedReader in = new BufferedReader(new FileReader("agenda.txt"));
				String linea = in.readLine();
				int indexComa1 = linea.indexOf(",",1);
				int indexComa2 = linea.indexOf(",",indexComa1 + 1);
				String tel;
				String correo;
				String nombre = linea.substring(0,indexComa1);
				while(!nombre.equals(nomBuscar) && (linea!=null)) {
					linea=in.readLine();
					indexComa1 = linea.indexOf(" , ",1);
					indexComa2 = linea.indexOf(",",indexComa1+1);
					nombre = linea.substring(0,indexComa1);
				}
				if(nombre.equals(nomBuscar)) {
					tel=linea.substring(indexComa1 + 1,indexComa2);
					correo = linea.substring(indexComa2+1,linea.length());
					txtNombre.setText(nombre);
					txtTelefono.setText(tel);
					txtCorreo.setText(correo);
				}
				else {
					JOptionPane.showMessageDialog(btnNewButton_1, "No encontre contacto");
					}
				}
				catch(IOException e1) {
					JOptionPane.showMessageDialog(btnNewButton_1, e1);
				}	
			}});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Mostrar todo");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(282, 206, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JList list = new JList();
		list = new JList<>(modelo);
        try {
            BufferedReader reader = new BufferedReader(new FileReader("agenda.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                modelo.addElement(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		list.setBounds(282, 11, 321, 168);
		contentPane.add(list);
        
		}
	{
}
}

