/**
 * Dialog cho phep cau hinh viec ket noi den co so du lieu
 */
package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.DBManager.ConnectDB;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
 * @author heroandtn3
 * @date Nov 2, 2012
 */
public class ConfigDlg extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbHost;
	private JLabel lbPass;
	private JLabel lbTitle;
	private JLabel lbNotice;
	private JButton butExit;
	private JButton butReset;
	private JButton butConnect;
	private JTextField tfPort;
	private JLabel lbPort;
	private JPasswordField pfPass;
	private JTextField tfDatabase;
	private JTextField tfUser;
	private JTextField tfHost;
	private JLabel lbDatabase;
	private JLabel lbUser;

	/**
	 * 
	 */
	public ConfigDlg() {
		// TODO Auto-generated constructor stub
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				{
					lbHost = new JLabel();
					getContentPane().add(lbHost);
					lbHost.setText("Host");
					lbHost.setBounds(26, 80, 29, 15);
				}
				{
					lbUser = new JLabel();
					getContentPane().add(lbUser);
					lbUser.setText("User");
					lbUser.setBounds(26, 123, 29, 15);
				}
				{
					lbPass = new JLabel();
					getContentPane().add(lbPass);
					lbPass.setText("Password");
					lbPass.setBounds(26, 169, 60, 15);
				}
				{
					lbDatabase = new JLabel();
					getContentPane().add(lbDatabase);
					lbDatabase.setText("Database");
					lbDatabase.setBounds(26, 218, 61, 15);
				}
				{
					tfHost = new JTextField();
					getContentPane().add(tfHost);
					tfHost.setText("localhost");
					tfHost.setBounds(116, 77, 108, 22);
				}
				{
					tfUser = new JTextField();
					getContentPane().add(tfUser);
					tfUser.setText("root");
					tfUser.setBounds(116, 120, 108, 22);
				}
				{
					tfDatabase = new JTextField();
					getContentPane().add(tfDatabase);
					tfDatabase.setText("listeningenglish");
					tfDatabase.setBounds(116, 215, 108, 22);
				}
				{
					pfPass = new JPasswordField();
					getContentPane().add(pfPass);
					pfPass.setText("12345");
					pfPass.setBounds(116, 166, 108, 22);
				}
				{
					lbPort = new JLabel();
					getContentPane().add(lbPort);
					lbPort.setText("Port");
					lbPort.setBounds(249, 84, 26, 15);
				}
				{
					tfPort = new JTextField();
					getContentPane().add(tfPort);
					tfPort.setText("3306");
					tfPort.setBounds(302, 81, 108, 22);
				}
				{
					butConnect = new JButton();
					getContentPane().add(butConnect);
					butConnect.setText("Connect");
					butConnect.setBounds(302, 123, 108, 22);
					butConnect.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							//TODO add your code for butConnect.actionPerformed
							writeConfig();
							ConnectDB.readConfig();
							if (ConnectDB.connect()) {
								lbNotice.setForeground(Color.GREEN);
								lbNotice.setText("Successful connection!");
								(ConfigDlg.this).setVisible(false);
								
								// chay mainUI o day
								(new MainUI()).setVisible(true);
							} else {
								lbNotice.setForeground(Color.RED);
								lbNotice.setText("Connection failure!");
							}
						}
					});
				}
				{
					butReset = new JButton();
					getContentPane().add(butReset);
					butReset.setText("Reset");
					butReset.setBounds(302, 169, 108, 22);
					butReset.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							tfHost.setText("localhost");
							tfPort.setText("3306");
							tfDatabase.setText("listeningenglish");
							tfUser.setText("root");
							pfPass.setText("12345");
							lbNotice.setText("");
						}
					});
				}
				{
					butExit = new JButton();
					getContentPane().add(butExit);
					butExit.setText("Exit");
					butExit.setBounds(302, 215, 108, 22);
					butExit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.exit(0);
						}
					});
				}
				{
					lbNotice = new JLabel();
					getContentPane().add(lbNotice);
					lbNotice.setBounds(26, 265, 384, 57);
				}
				{
					lbTitle = new JLabel();
					lbTitle.setLayout(null);
					getContentPane().add(lbTitle);
					lbTitle.setText("Config Connection");
					lbTitle.setBounds(154, 19, 148, 23);
					lbTitle.setFont(new java.awt.Font("Arial",1,14));
				}
			}
			{
				this.setSize(438, 371);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // ket thuc phuong thuc initGUI()
	
	/**
	* Ham de viet du lieu ra XML
	* Suu tam :)
	*/
    private void writeConfig() {
    	
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("connection");
            doc.appendChild(rootElement);

            // child elements
            Element eHostname = doc.createElement("hostname");
            eHostname.appendChild(doc.createTextNode(tfHost.getText().trim()));
            rootElement.appendChild(eHostname);

            Element ePort = doc.createElement("port");
            ePort.appendChild(doc.createTextNode(tfPort.getText().trim()));
            rootElement.appendChild(ePort);

            Element eUsername = doc.createElement("username");
            eUsername.appendChild(doc.createTextNode(tfUser.getText().trim()));
            rootElement.appendChild(eUsername);

            Element ePassword = doc.createElement("password");
            ePassword.appendChild(doc.createTextNode(String.valueOf(pfPass.getPassword())));
            rootElement.appendChild(ePassword);

            Element eDatabase = doc.createElement("database");
            eDatabase.appendChild(doc.createTextNode(tfDatabase.getText().trim()));
            rootElement.appendChild(eDatabase);

            // Viet noi dung ra xml
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("config.xml"));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
            transformer.transform(source, result);

        } catch (TransformerException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }

    }
}
