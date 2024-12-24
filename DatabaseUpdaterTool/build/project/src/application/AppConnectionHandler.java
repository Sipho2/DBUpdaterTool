package application;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppConnectionHandler {
    private static final AppConnectionHandler INSTANCE = new AppConnectionHandler();

    public AppConnectionHandler() {}

    public static AppConnectionHandler getInstance() {
        return INSTANCE;
    }
    // Method to parse XML file and create Connection objects
    public List<AppConnection> readConnectionsFromXML(String filePath) {
        List<AppConnection> connections = new ArrayList<>();
        try {
            // Load and parse the XML file
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Normalize XML structure
            doc.getDocumentElement().normalize();

            // Get all <connection> nodes
            NodeList nodeList = doc.getElementsByTagName("connection");

            // Loop through each <connection> node
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Extract fields from the XML
                    String serverName = element.getElementsByTagName("serverName").item(0).getTextContent();
                    String user = element.getElementsByTagName("user").item(0).getTextContent();
                    String password = element.getElementsByTagName("password").item(0).getTextContent();
                    String scriptsFolder = element.getElementsByTagName("scriptsFolder").item(0).getTextContent();
                    String scriptsLogTable = element.getElementsByTagName("scriptsLogTable").item(0).getTextContent();

                    // Create a Connection object
                    AppConnection connection = new AppConnection.Builder()
                    		.setServerName(serverName)
                    		.setUser(user)
                    		.setPassword(password)
                    		.setScriptsFolder(scriptsFolder)
                    		.setScriptsLogTable(scriptsLogTable)
                    		.build();

                    // Add the Connection object to the list
                    connections.add(connection);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connections;
    }
}