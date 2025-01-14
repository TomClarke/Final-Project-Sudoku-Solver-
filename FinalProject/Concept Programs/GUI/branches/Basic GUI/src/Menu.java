package src;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class Menu extends JFrame {
	 
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JButton btnNewButton = new JButton("Make Me Colourful");
		btnNewButton.setBackground(Color.GRAY);
		
		btnNewButton.setBounds(176, 286, 207, 29);
		contentPane.add(btnNewButton);
		
		
		
		final JLabel lblNewLabel = new JLabel("Background\n");
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/src/SudokuTitle.jpg")));
		lblNewLabel.setBounds(16, 1, 561, 218);
		lblNewLabel.setVisible(false);
		contentPane.add(lblNewLabel);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addItem("Red");
		comboBox.addItem("Blue");
		comboBox.addItem("Green");
		comboBox.addItem("White");
		comboBox.addItem("Black");
		
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"Red ", "Blue ", "Green ", "White ", "Black"}));
		comboBox.setBounds(180, 327, 203, 27);
		contentPane.add(comboBox);
		
		JLabel lblTooColourful = new JLabel("Too Colourful? -->");
		lblTooColourful.setBounds(42, 331, 126, 16);
		contentPane.add(lblTooColourful);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setVisible(true);
				btnNewButton.setBackground(Color.RED);
				contentPane.setBackground(new Color(255, 0, 0));
				
			
				
			}
		});
		
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			
		
				//Object option = e.getItem();
				
				if (e.getItem() == "Red"){
					contentPane.setBackground(Color.RED);
				}
				if (e.getItem() == "Blue"){
					contentPane.setBackground(Color.BLUE);
				}
				if (e.getItem() == "Green"){
					contentPane.setBackground(Color.GREEN);
				}
				if (e.getItem() == "White"){
					contentPane.setBackground(Color.WHITE);
				}
				if (e.getItem() == "Black"){
					contentPane.setBackground(Color.BLACK);
				}
				
			}
		});
	}	
}
