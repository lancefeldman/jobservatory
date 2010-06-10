/**
 *  Visual class to add tables to database schema
 */
package com.cincycomp.vetestproject.gui;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @author lance
 *
 */
public class DoDatabaseSetupStuff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jCreateTablesButton = null;

	/**
	 * This method initializes jCreateTablesButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJCreateTablesButton() {
		if (jCreateTablesButton == null) {
			jCreateTablesButton = new JButton();
			jCreateTablesButton.setBounds(new Rectangle(89, 21, 123, 112));
			jCreateTablesButton.setText("CreateTables");
			jCreateTablesButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				    AnnotationConfiguration config = new AnnotationConfiguration();
				    config.addAnnotatedClass(User.class);
				    config.addAnnotatedClass(UserAddress.class);
				    config.configure();
				    new SchemaExport(config).create(true, true);					
					System.out.println("actionPerformed()"); 
					// TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jCreateTablesButton;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DoDatabaseSetupStuff thisClass = new DoDatabaseSetupStuff();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public DoDatabaseSetupStuff() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("DoDatabaseSetupStuff");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJCreateTablesButton(), null);
		}
		return jContentPane;
	}

}
