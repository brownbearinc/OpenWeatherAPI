package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GettheWeather {

	public static void getWeather(weatherBean wBean) throws IOException {

		String API_KEY = "c51af1166257f579968913b3c4da26d1";

		// Build the API call URL by adding city+country into a URL
		String URLtoSend = "http://api.openweathermap.org/data/2.5/weather?q=" + wBean.getCityStr() + ","
				+ wBean.getCountryStr() + "&APPID=" + API_KEY + "&mode=xml";

		// Set the URL that will be sent
		URL line_api_url = new URL(URLtoSend);

		// Create a HTTP connection to sent the GET request over
		HttpURLConnection linec = (HttpURLConnection) line_api_url.openConnection();
		linec.setDoInput(true);
		linec.setDoOutput(true);
		linec.setRequestMethod("GET");

		// Make a Buffer to read the response from the API
		BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

		// a String to temp save each line in the response
		String inputLine;

		// a String to save the full response to use later
		String ApiResponse = "";

		// loop through the whole response
		while ((inputLine = in.readLine()) != null) {

			// Save the temp line into the full response
			ApiResponse += inputLine;
		}
		in.close();

		// print the response
		// System.out.println(ApiResponse);

		// Call a method to make a XMLdoc out of the full response
		Document doc = convertStringToXMLDocument(ApiResponse);

		// check that the XML response is OK by getting the Root element
		// System.out.println("Root element :" +
		// doc.getDocumentElement().getNodeName());

		// Create a array Node list that gets value under the "clouds", "temp" & "date" tag
		NodeList[] weatherNode = { doc.getElementsByTagName("clouds"), doc.getElementsByTagName("speed"),
				doc.getElementsByTagName("temperature"), doc.getElementsByTagName("lastupdate") };

		Node[] node = { weatherNode[0].item(0), weatherNode[1].item(0), weatherNode[2].item(0),
				weatherNode[3].item(0) };

		if (node[0].getNodeType() == Node.ELEMENT_NODE && node[1].getNodeType() == Node.ELEMENT_NODE
				&& node[2].getNodeType() == Node.ELEMENT_NODE && node[3].getNodeType() == Node.ELEMENT_NODE) {

			// set the current node as an Element
			Element[] eElement = { (Element) node[0], (Element) node[1], (Element) node[2], (Element) node[3] };

			// get the content of an attribute in element
			String[] XMLweather = { eElement[0].getAttribute("name"), eElement[1].getAttribute("name"),
					eElement[2].getAttribute("value"), eElement[3].getAttribute("value") };

			// and print them in console
			System.out.println("\nGettheWeather.java clouds: " + XMLweather[0]);
			System.out.println("GettheWeather.java wind: " + XMLweather[1]);
			System.out.println("GettheWeather.java temp: " + XMLweather[2] + " K");
			System.out.println("GettheWeather.java date: " + XMLweather[3] + "\n");

			// save them in wBean
			wBean.setCloudsStr(XMLweather[0]);
			wBean.setWindStr(XMLweather[1]);
			wBean.setCelsiusTemp(XMLweather[2]);
			wBean.setDate(XMLweather[3]);
		}

	}

	// Method the makes a XML doc out of a string, if it is in a XML format.
	private static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
