package model.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConnectDB {
	private static Connection conn;
	private static String user = "root";
	private static String database = "listeningenglish";
	private static String password = "12345";
	private static String host = "localhost";
	private static String port = "3306";

	/**
	 * Tien hanh truy cap vao mot co so du lieu phuong thuc nay se lam o ngay
	 * dau tien cua chuong trinh nham lay duoc doi tuong conn de phuc vu cho
	 * phuong thuc getConnect
	 * 
	 * @return
	 */
	public static boolean connect() {
		readConfig();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	public static boolean closeConnect() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
				return false;
			}
		}

		return true;
	}

	public static Connection getConnect() {
		return conn;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String _user) {
		user = _user;
	}

	public static String getDatabase() {
		return database;
	}

	public static void setDatabase(String _database) {
		database = _database;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String _password) {
		password = _password;
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String _host) {
		host = _host;
	}

	public static String getPort() {
		return port;
	}

	public static void setPort(String _port) {
		port = _port;
	}

	/**
	 * Doc du lieu ket noi toi co so du lieu
	 * Suu tam :)
	 */
	public static void readConfig() {
		try {
			File fXmlFile = new File("config.xml");
			if (fXmlFile.exists()) {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("connection");

				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;

						host = getTagValue("hostname", eElement);
						port = getTagValue("port", eElement);
						user = getTagValue("username", eElement);
						password = getTagValue("password", eElement);
						database = getTagValue("database", eElement);

					}
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	// Lay gia tri theo tag name
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}
}
