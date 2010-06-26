/**
 * 
 */
package com.cincycomp.vetestproject.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Example;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @author lance
 *
 */
public class GetUserData extends JFrame {

	private static final long serialVersionUID = 1L;
	//
	// Hibernate related and other useful variables
	private AnnotationConfiguration config = null;  //  @jve:decl-index=0:
	private Calendar cal = null;  //  @jve:decl-index=0:
	private DateFormat dateFormat = null;  //  @jve:decl-index=0:
    private Session session = null;  //  @jve:decl-index=0:
    private SessionFactory factory = null;  //  @jve:decl-index=0:
	private Transaction tx = null;
    private User u = null;  //  @jve:decl-index=0:
    private UserAddress ua = null;  //  @jve:decl-index=0:
    //
    // Visual class related variables
	private JPanel jContentPane = null;
	private JLabel jTestLabel = null;
	private JTextField jPasswordTextField = null;
	private JButton jAddDataButton = null;
	private JLabel jFirstNameLabel = null;
	private JLabel jLastNameLabel = null;
	private JTextField jFirstNameTextField = null;
	private JTextField jLastNameTextField = null;
	private JLabel jStreetAddressLabel = null;
	private JLabel jCityNameLabel = null;
	private JLabel jStateNameLabel = null;
	private JTextField jStreetAddressTextField = null;
	private JTextField jCityNameTextField = null;
	private JTextField jStateNameTextField = null;
	private JLabel jZipCodeLabel = null;
	private JTextField jZipCodeTextField = null;
	private JButton jResetDatabaseButton = null;
	private JButton jRetrieveUserRecordsButton = null;
	private JButton jRetrieveUserUsingCriteriaButton = null;
	private JButton jRetrieveUserUsingKeyButton = null;
	private JButton jUpdateUserButton = null;
	private JButton jDeleteUserButton = null;
	/**
	 * This method initializes jPasswordTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJPasswordTextField() {
		if (jPasswordTextField == null) {
			jPasswordTextField = new JTextField();
			jPasswordTextField.setBounds(new Rectangle(150, 30, 125, 20));
			jPasswordTextField.setNextFocusableComponent(getJFirstNameTextField());
		}
		return jPasswordTextField;
	}

	/**
	 * This method initializes jAddDataButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJAddDataButton() {
		if (jAddDataButton == null) {
			jAddDataButton = new JButton();
			jAddDataButton.setLocation(new Point(30, 150));
			jAddDataButton.setSize(new Dimension(115, 20));
			jAddDataButton.setNextFocusableComponent(getJPasswordTextField());
			jAddDataButton.setText("Add User");
			jAddDataButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				    // 
					// Enclose Hibernate transaction with exception handling
					try {
							//
				        	// Open connection to database specified in hibernate.cfg.xml
							// and get instance of Hibernate Session class
							session = factory.openSession();
							//
				        	// Get instances of necessary POJO classes
						    u = new User();
						    ua = new UserAddress();
					        //
					        // Get Hibernate Transaction object and begin transaction
					        tx = session.beginTransaction();
					        //
					        // Print out transaction start time
							cal = Calendar.getInstance();
						    System.out.println("Start time   : " + dateFormat.format(cal.getTime()));
						    //
						    // Populate transient User object
						    u.setPassword(jPasswordTextField.getText());
						    u.setFirstName(jFirstNameTextField.getText());
						    u.setLastName(jLastNameTextField.getText());
						    //
						    // Use Hibernate to persist the User object
						    session.saveOrUpdate(u);
						    //
						    // Populate transient UserAddress object
						    ua.setStreetAddress(jStreetAddressTextField.getText());
						    ua.setCityName(jCityNameTextField.getText());
						    ua.setStateName(jStateNameTextField.getText());
						    ua.setZipCode(jZipCodeTextField.getText());
						    //
						    // Note: The user.userId field is now populated since the user object was
						    //       persisted so use it to populate the userAddress.userId field 
						    ua.setUserId(u.getUserId());  
						    //
						    // Use Hibernate to persist the UserAddress object
						    session.saveOrUpdate(ua);
					        //
					        // Print out information being persisted
						    System.out.println("Creating user   : " + jFirstNameTextField.getText() + " " + jLastNameTextField.getText() + " " + jPasswordTextField.getText());
						    System.out.println("Creating address: " + jStreetAddressTextField.getText() + ", " + jCityNameTextField.getText() + ", " + jStateNameTextField.getText() + ", " + jZipCodeTextField.getText());
					        //
					        // Print out transaction stop time
							cal = Calendar.getInstance();
						    System.out.println("Stop time    : " + dateFormat.format(cal.getTime()));
					        //
					        // Reset data entry jTextField objects to be empty for next record
						    jPasswordTextField.setText("");
						    jFirstNameTextField.setText("");
						    jLastNameTextField.setText("");
						    jStreetAddressTextField.setText("");
						    jCityNameTextField.setText("");
						    jStateNameTextField.setText("");
						    jZipCodeTextField.setText("");
					        //
					        // Commit Transaction
					        tx.commit();
					}
					catch (Exception ex) {
				        //
				        // If any exceptions are thrown, rollback Transaction
					     if (tx!=null) tx.rollback();
				        //
				        // Printout StackTrace to avoid declaring enclosing method as 
					    // throwing the various exceptions
					     ex.printStackTrace();
					}
					finally {
				        //
				        // Close database connection and dispose of instances used
					     session.close();
					     session = null;
					     tx = null;
					     u = null;
					     ua = null;
					     cal = null;
					}
				}
			});
		}
		return jAddDataButton;
	}

	/**
	 * This method initializes jFirstNameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJFirstNameTextField() {
		if (jFirstNameTextField == null) {
			jFirstNameTextField = new JTextField();
			jFirstNameTextField.setBounds(new Rectangle(150, 60, 125, 20));
			jFirstNameTextField.setNextFocusableComponent(getJLastNameTextField());
		}
		return jFirstNameTextField;
	}

	/**
	 * This method initializes jLastNameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJLastNameTextField() {
		if (jLastNameTextField == null) {
			jLastNameTextField = new JTextField();
			jLastNameTextField.setBounds(new Rectangle(150, 90, 125, 20));
			jLastNameTextField.setNextFocusableComponent(getJStreetAddressTextField());
		}
		return jLastNameTextField;
	}

	/**
	 * This method initializes jStreetAddressTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJStreetAddressTextField() {
		if (jStreetAddressTextField == null) {
			jStreetAddressTextField = new JTextField();
			jStreetAddressTextField.setBounds(new Rectangle(415, 30, 125, 20));
			jStreetAddressTextField.setNextFocusableComponent(getJCityNameTextField());
		}
		return jStreetAddressTextField;
	}

	/**
	 * This method initializes jCityNameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJCityNameTextField() {
		if (jCityNameTextField == null) {
			jCityNameTextField = new JTextField();
			jCityNameTextField.setBounds(new Rectangle(415, 60, 125, 20));
			jCityNameTextField.setNextFocusableComponent(getJStateNameTextField());
		}
		return jCityNameTextField;
	}

	/**
	 * This method initializes jStateNameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJStateNameTextField() {
		if (jStateNameTextField == null) {
			jStateNameTextField = new JTextField();
			jStateNameTextField.setBounds(new Rectangle(415, 90, 125, 20));
			jStateNameTextField.setNextFocusableComponent(getJZipCodeTextField());
		}
		return jStateNameTextField;
	}

	/**
	 * This method initializes jZipCodeTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJZipCodeTextField() {
		if (jZipCodeTextField == null) {
			jZipCodeTextField = new JTextField();
			jZipCodeTextField.setBounds(new Rectangle(415, 120, 125, 20));
			jZipCodeTextField.setNextFocusableComponent(getJAddDataButton());
		}
		return jZipCodeTextField;
	}

	/**
	 * This method initializes jResetDatabaseButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJResetDatabaseButton() {
		if (jResetDatabaseButton == null) {
			jResetDatabaseButton = new JButton();
			jResetDatabaseButton.setBounds(new Rectangle(30, 120, 115, 20));
			jResetDatabaseButton.setText("Reset Schema");
			jResetDatabaseButton.setHorizontalTextPosition(SwingConstants.LEFT);
			jResetDatabaseButton.setMargin(new Insets(2, 10, 2, 10));
			jResetDatabaseButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO Auto-generated Event stub actionPerformed()
				    String text = "";
				    boolean flag = true;
				    while (flag == true)
				    	{
				    		if (text.equalsIgnoreCase("schemapassword")) {
				    			new SchemaExport(config).create(true, true);
				    			flag = false;
				    		}else {
				    			text = JOptionPane.showInputDialog((Component) e.getSource(), "What is the schema password?");
				    			// Check for Cancel button being hit (text has null value).
				    			if (text == null){
				    				text = "";
					    			flag = false;
				    				}
				    		}
				    	}
				}
			});
		}
		return jResetDatabaseButton;
	}

	/**
	 * This method initializes jRetrieveUserRecordsButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJRetrieveUserRecordsButton() {
		if (jRetrieveUserRecordsButton == null) {
			jRetrieveUserRecordsButton = new JButton();
			jRetrieveUserRecordsButton.setMargin(new Insets(2, 10, 2, 10));
			jRetrieveUserRecordsButton.setSize(new Dimension(115, 30));
			jRetrieveUserRecordsButton.setLocation(new Point(30, 181));
			jRetrieveUserRecordsButton.setText("Retrieve Users");
			jRetrieveUserRecordsButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							//
							// Declare List object to hold Users object returned from Query
							// java.util.List allUsers;
						    // 
							// Enclose Hibernate transaction with exception handling
							try {
									//
						        	// Open connection to database specified in hibernate.cfg.xml
									// and get instance of Hibernate Session class
									session = factory.openSession();
							        // Get Hibernate Transaction object and begin transaction
							        tx = session.beginTransaction();
							        //
							        // Print out transaction start time
									cal = Calendar.getInstance();
								    System.out.println("Start time   : " + dateFormat.format(cal.getTime()));
								    String queryString = "from User";

									// Create Query object using Session object
								    Query query = session.createQuery(queryString); 
								    List allUsers;
									allUsers = query.list();
									for (int i = 0; i < allUsers.size(); i++) {
									    User user = (User) allUsers.get(i);
									    System.out.println("Retrieving user   : " + user.getFirstName() + " " + user.getLastName() + " Password: " + user.getPassword());
									    System.out.println(user.getPassword());
									}
									System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
							        //
							        // Print out transaction stop time
									cal = Calendar.getInstance();
								    System.out.println("Stop time    : " + dateFormat.format(cal.getTime()));
							        // Commit Transaction
							        tx.commit();
							}
							catch (Exception ex) {
						        //
						        // If any exceptions are thrown, rollback Transaction
							     if (tx!=null) tx.rollback();
						        //
						        // Printout StackTrace to avoid declaring enclosing method as 
							    // throwing the various exceptions
							     ex.printStackTrace();
							}
							finally {
						        //
						        // Close database connection and dispose of instances used
							     session.close();
							     session = null;
							     tx = null;
							     u = null;
							     ua = null;
							     cal = null;
							}
						}
					});
		}
		return jRetrieveUserRecordsButton;
	}

	/**
	 * This method initializes jRetrieveUserRecordButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJRetrieveUserUsingCriteriaButton() {
		if (jRetrieveUserUsingCriteriaButton == null) {
			jRetrieveUserUsingCriteriaButton = new JButton();
//			jRetrieveUserRecordButton.setPreferredSize(new Dimension(110, 26));
			jRetrieveUserUsingCriteriaButton.setMargin(new Insets(5, 5, 5, 5));
			jRetrieveUserUsingCriteriaButton.setVerticalTextPosition(SwingConstants.CENTER);
			jRetrieveUserUsingCriteriaButton.setPreferredSize(new Dimension(93, 30));
			jRetrieveUserUsingCriteriaButton.setLocation(new Point(150, 180));
			jRetrieveUserUsingCriteriaButton.setSize(new Dimension(125, 30));
			jRetrieveUserUsingCriteriaButton.setText("<html>Retrieve User <br>using criteria");
			jRetrieveUserUsingCriteriaButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							User u = new User();
							Example example = null;
							String firstName = null;
							String lastName = null;
							String userPassword = null;
							
							// Enclose Hibernate transaction with exception handling
							try {
								session = factory.openSession();
						        // Get Hibernate Transaction object and begin transaction
						        tx = session.beginTransaction();
						        //
						        // Print out transaction start time
//								cal = Calendar.getInstance();
//							    System.out.println("Start time   : " + dateFormat.format(cal.getTime()));
								// 						
								/*
								 * Create a criteria object and add the user example created above
								 * 
								 */
						        firstName = jFirstNameTextField.getText();
								lastName =  jLastNameTextField.getText();
								userPassword = jPasswordTextField.getText();
								
								if (firstName.length() > 0){
									u.setFirstName(firstName);
									System.out.println("Example: firstName="+u.getFirstName());
								}
								else{
									System.out.println("Example: firstname = null");
								}
								if (lastName.length() > 0){
									u.setLastName(lastName);
									System.out.println("         lastName="+u.getLastName());
								}
								else{
									System.out.println("         lastname = null");
								}
								if (userPassword.length() > 0){
									u.setPassword(userPassword);
									System.out.println("         password="+u.getPassword());	
								}
								else{
									System.out.println("         password = null");
								}
								
								example = Example.create(u);
						   
								Criteria criteria = session.createCriteria(User.class);
								criteria.add(example);
								u = (User)criteria.uniqueResult();
								if (u != null){
									System.out.println("User.firstName="+u.getFirstName());
									System.out.println("User.lastName="+u.getLastName());
									System.out.println("User.password="+u.getPassword());
								}

//								java.util.List results = criteria.list();
//								System.out.println("results.size()="+results.size());
//								for (int i = 0; i<results.size(); i++) {
//									User result = (User) results.get(i);
//									System.out.println("User["+i+"].firstName="+result.getFirstName());
//									System.out.println("User["+i+"].lastName="+result.getLastName());
//									System.out.println("User["+i+"].password="+result.getPassword());
//								   System.out.println(results.get(i).toString());
//								}

//							    System.out.println("Retrieve User: FirstName ="+jFirstNameTextField.getText()+
//							    		"\n LastName ="+jLastNameTextField.getText()+ "UserPassword="+jPasswordTextField.getText());
															
								System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
								
						        // Print out transaction stop time
//								cal = Calendar.getInstance();
//							    System.out.println("Stop time    : " + dateFormat.format(cal.getTime()));
							    
								tx.commit();
							}
							catch(Exception ex){
						        //
						        // If any exceptions are thrown, rollback Transaction
							     if (tx!=null) tx.rollback();
						        //
						        // Printout StackTrace to avoid declaring enclosing method as 
							    // throwing the various exceptions
							     ex.printStackTrace();							
							}
							finally{
						        //
						        // Close database connection and dispose of instances used
							     session.close();
							     session = null;
							     tx = null;
							     u = null;
							     ua = null;
							     cal = null;
							}							
														
							
														
//
//				Original hard coded criteria test
//							
//							session = factory.openSession();
//					        // Get Hibernate Transaction object and begin transaction
//					        tx = session.beginTransaction();
//					        //
//					        // Print out transaction start time
////							cal = Calendar.getInstance();
////						    System.out.println("Start time   : " + dateFormat.format(cal.getTime()));
//							// 						
//							/*
//							 * Create a criteria object and add the user example created above
//							 * 
//							 */
//					        
//							u.setFirstName("D");
//							example = Example.create(u);
//							example.enableLike(MatchMode.START);
//							example.ignoreCase();
//					   
//							Criteria criteria = session.createCriteria(User.class);
//							criteria.add(example);
//							java.util.List results = criteria.list();
//							System.out.println("results.size()="+results.size());
//							for (int i = 0; i<results.size(); i++) {
//								User result = (User) results.get(i);
//								System.out.println("User["+i+"].firstName="+result.getFirstName());
//								System.out.println("User["+i+"].lastName="+result.getLastName());
//								System.out.println("User["+i+"].password="+result.getPassword());
////							   System.out.println(results.get(i).toString());
//							}
//
////						    System.out.println("Retrieve User: FirstName ="+jFirstNameTextField.getText()+
////						    		"\n LastName ="+jLastNameTextField.getText()+ "UserPassword="+jPasswordTextField.getText());
//														
//							System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
//							
//					        // Print out transaction stop time
////							cal = Calendar.getInstance();
////						    System.out.println("Stop time    : " + dateFormat.format(cal.getTime()));
//						    
//							tx.commit();
						}
					});
		}
		return jRetrieveUserUsingCriteriaButton;
	}

	/**
	 * This method initializes jRetrieveUserUsingKeyButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJRetrieveUserUsingKeyButton() {
		if (jRetrieveUserUsingKeyButton == null) {
			jRetrieveUserUsingKeyButton = new JButton();
			jRetrieveUserUsingKeyButton.setMnemonic(KeyEvent.VK_UNDEFINED);
			jRetrieveUserUsingKeyButton.setLocation(new Point(290, 180));
			jRetrieveUserUsingKeyButton.setSize(new Dimension(115, 30));
			jRetrieveUserUsingKeyButton.setText("<html>Retrieve User<br>using key");
			jRetrieveUserUsingKeyButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {

			actionEvent:	try {							
							// Enclose Hibernate transaction with exception handling
									//
						        	// Open connection to database specified in hibernate.cfg.xml
									// and get instance of Hibernate Session class
									session = factory.openSession();
							        // Get Hibernate Transaction object and begin transaction
							        tx = session.beginTransaction();
							        //
							        // Print out transaction start time
									cal = Calendar.getInstance();
								    System.out.println("Start time   : " + dateFormat.format(cal.getTime()));
								    String queryString = "from User where id = :id"; 
									// 
									// Create Query object using Session object
								    Query query = session.createQuery(queryString);
								    
								    String text = "";
								    int idVariable = 0;
								    boolean flag = true;
								    while (flag == true){
								    		text = JOptionPane.showInputDialog((Component) e.getSource(), "Enter userId: ");
								    		if (text == null){
								    			break actionEvent;  // Check for Cancel button being hit (text is null).
								    		}
								    		if (text.length() > 0) {  // if length = 0 get another userId
											    try{
											    	idVariable = Integer.parseInt(text);
											    	flag = false;
											    }
											    catch(NumberFormatException nfe){
											    	System.out.println("invalid key: re-enter");
											    }
								    		}
								    }
									query.setInteger("id", idVariable);
									Object queryResult = query.uniqueResult();
//									User user = (User)queryResult;
									u = (User)queryResult;
									if (u != null){
										System.out.println("Retrieving user   : " + u.getUserId() + " " + u.getFirstName() + " " + u.getLastName() + " Password: " + u.getPassword());
								        jFirstNameTextField.setText(u.getFirstName());
										jLastNameTextField.setText(u.getLastName());
										jPasswordTextField.setText(u.getPassword());
									}
									else{
										System.out.println("userId not found");
								        jFirstNameTextField.setText("");
										jLastNameTextField.setText("");
										jPasswordTextField.setText("");
									}
									System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
							        //
							        // Print out transaction stop time
									cal = Calendar.getInstance();
								    System.out.println("Stop time    : " + dateFormat.format(cal.getTime()));
							        //
							        // Commit Transaction
							        tx.commit();
							}
							catch (Exception ex) {
						        //
						        // If any exceptions are thrown, rollback Transaction
							     if (tx!=null) tx.rollback();
						        //
						        // Printout StackTrace to avoid declaring enclosing method as 
							    // throwing the various exceptions
							     ex.printStackTrace();
							}
							finally {
						        //
						        // Close database connection and dispose of instances used
							     session.close();
							     session = null;
							     tx = null;
//							     u = null;
							     ua = null;
							     cal = null;
							}								
						}
					});
		}
		return jRetrieveUserUsingKeyButton;
	}

	/**
	 * This method initializes jUpdateUserButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJUpdateUserButton() {
		if (jUpdateUserButton == null) {
			jUpdateUserButton = new JButton();
			jUpdateUserButton.setLocation(new Point(150, 150));
			jUpdateUserButton.setMnemonic(KeyEvent.VK_UNDEFINED);
			jUpdateUserButton.setText("Update User");
			jUpdateUserButton.setSize(new Dimension(125, 20));
			jUpdateUserButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					try {
						//
			        	// Open connection to database specified in hibernate.cfg.xml
						// and get instance of Hibernate Session class
						session = factory.openSession();
						
				        //
				        // Get Hibernate Transaction object and begin transaction
				        tx = session.beginTransaction();
				        //
				        // Print out transaction start time
						cal = Calendar.getInstance();
					    System.out.println("Start time   : " + dateFormat.format(cal.getTime()));
					    //
					    // Populate transient User object
					    u.setPassword(jPasswordTextField.getText());
					    u.setFirstName(jFirstNameTextField.getText());
					    u.setLastName(jLastNameTextField.getText());
					    //
					    // Use Hibernate to persist the User object
					    session.update(u);
					    //
				        //
				        // Print out information being persisted
					    System.out.println("Updating user   : userId " + u.getUserId() + " " + jFirstNameTextField.getText() + " " + jLastNameTextField.getText() + " " + jPasswordTextField.getText());
					    
				        //
				        // Print out transaction stop time
						cal = Calendar.getInstance();
					    System.out.println("Stop time    : " + dateFormat.format(cal.getTime()));
				        //
				        // Reset data entry jTextField objects to be empty for next record
					    jPasswordTextField.setText("");
					    jFirstNameTextField.setText("");
					    jLastNameTextField.setText("");
				        //
				        // Commit Transaction
				        tx.commit();
					}
					catch (Exception ex) {
			        //
			        // If any exceptions are thrown, rollback Transaction
						if (tx!=null) tx.rollback();
			        //
			        // Printout StackTrace to avoid declaring enclosing method as 
				    // throwing the various exceptions
						ex.printStackTrace();
					}
					finally {
			        //
			        // Close database connection and dispose of instances used
						session.close();
						session = null;
						tx = null;
						u = null;
						ua = null;
						cal = null;
					}	
				}
			});
		}
		return jUpdateUserButton;
	}

	/**
	 * This method initializes jDeleteUserButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJDeleteUserButton() {
		if (jDeleteUserButton == null) {
			jDeleteUserButton = new JButton();
			jDeleteUserButton.setSize(new Dimension(115, 20));
			jDeleteUserButton.setText("Delete User");
			jDeleteUserButton.setLocation(new Point(290, 150));
			jDeleteUserButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					try {
						//
			        	// Open connection to database specified in hibernate.cfg.xml
						// and get instance of Hibernate Session class
						session = factory.openSession();
						
				        //
				        // Get Hibernate Transaction object and begin transaction
				        tx = session.beginTransaction();
				        //
				        // Print out transaction start time
						cal = Calendar.getInstance();
					    System.out.println("Start time   : " + dateFormat.format(cal.getTime()));

					    //
					    // Use Hibernate to persist the User object
					    session.delete(u);
					    //
				        //
				        // Print out information being persisted
					    System.out.println("Deleting user   : userId " + u.getUserId() + " " + jFirstNameTextField.getText() + " " + jLastNameTextField.getText() + " " + jPasswordTextField.getText());
					    
				        //
				        // Print out transaction stop time
						cal = Calendar.getInstance();
					    System.out.println("Stop time    : " + dateFormat.format(cal.getTime()));
				        //
				        // Reset data entry jTextField objects to be empty for next record
					    jPasswordTextField.setText("");
					    jFirstNameTextField.setText("");
					    jLastNameTextField.setText("");
				        //
				        // Commit Transaction
				        tx.commit();
					}
					catch (Exception ex) {
			        //
			        // If any exceptions are thrown, rollback Transaction
						if (tx!=null) tx.rollback();
			        //
			        // Printout StackTrace to avoid declaring enclosing method as 
				    // throwing the various exceptions
						ex.printStackTrace();
					}
					finally {
			        //
			        // Close database connection and dispose of instances used
						session.close();
						session = null;
						tx = null;
						u = null;
						ua = null;
						cal = null;
					}				
				}
			});
		}
		return jDeleteUserButton;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GetUserData thisClass = new GetUserData();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public GetUserData() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setTitle("UserDataEntryScreen");

		this.setBounds(new Rectangle(0, 0, 727, 306));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jZipCodeLabel = new JLabel();
			jZipCodeLabel.setBounds(new Rectangle(290, 120, 115, 20));
			jZipCodeLabel.setText("ZIP Code : ");
			jStateNameLabel = new JLabel();
			jStateNameLabel.setBounds(new Rectangle(290, 90, 115, 20));
			jStateNameLabel.setText("State Name : ");
			jCityNameLabel = new JLabel();
			jCityNameLabel.setBounds(new Rectangle(290, 60, 115, 20));
			jCityNameLabel.setText("City Name : ");
			jStreetAddressLabel = new JLabel();
			jStreetAddressLabel.setBounds(new Rectangle(290, 30, 115, 20));
			jStreetAddressLabel.setText("Street Address : ");
			jLastNameLabel = new JLabel();
			jLastNameLabel.setBounds(new Rectangle(30, 90, 115, 20));
			jLastNameLabel.setText("Last Name:");
			jFirstNameLabel = new JLabel();
			jFirstNameLabel.setBounds(new Rectangle(30, 60, 115, 20));
			jFirstNameLabel.setText("First Name :");
			jTestLabel = new JLabel();
			jTestLabel.setBounds(new Rectangle(30, 30, 115, 20));
			jTestLabel.setText("User Password :");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jTestLabel, null);
			jContentPane.add(getJPasswordTextField(), null);
			jContentPane.add(getJAddDataButton(), null);
			jContentPane.add(jFirstNameLabel, null);
			jContentPane.add(jLastNameLabel, null);
			jContentPane.add(getJFirstNameTextField(), null);
			jContentPane.add(getJLastNameTextField(), null);
			jContentPane.add(jStreetAddressLabel, null);
			jContentPane.add(jCityNameLabel, null);
			jContentPane.add(jStateNameLabel, null);
			jContentPane.add(getJStreetAddressTextField(), null);
			jContentPane.add(getJCityNameTextField(), null);
			jContentPane.add(getJStateNameTextField(), null);
			jContentPane.add(jZipCodeLabel, null);
			jContentPane.add(getJZipCodeTextField(), null);
			//
			jContentPane.add(getJResetDatabaseButton(), null);
			// Initialize necessary Hibernate related classes
			jContentPane.add(getJRetrieveUserRecordsButton(), null);
			jContentPane.add(getJRetrieveUserUsingCriteriaButton(), null);
			jContentPane.add(getJRetrieveUserUsingKeyButton(), null);
			jContentPane.add(getJUpdateUserButton(), null);
			jContentPane.add(getJDeleteUserButton(), null);
		    config = new AnnotationConfiguration();
		    config.addAnnotatedClass(User.class);
		    config.addAnnotatedClass(UserAddress.class);
		    config.configure();
		    factory = config.buildSessionFactory();
			dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.sss");
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="15,11"
