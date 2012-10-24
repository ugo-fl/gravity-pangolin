package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class MapGenerator {

	private static final String SYM_BLOCK = "X";
	private static final String X = "x";
	private static final String Y = "y";
	private static final String WALL_BLOCK = "wallBlock";

	private static Document doc = null;
	private static String fileName;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		fileName = in.readLine();
		Element root = getRootElement();

		int sizeX = Integer.valueOf(root.getAttribute(X));
		int sizeY = Integer.valueOf(root.getAttribute(Y));

		int y = 0;
		int x = 0;
		for (String s; y >= 0 && ((s = in.readLine()) != null && s.length() != 0); y++) {
			x = 0;
			for (; x < sizeX && x < s.length(); x++) {

				String sym = String.valueOf(s.charAt(x));

				if (SYM_BLOCK.equalsIgnoreCase(sym)) {
					// create child element having tagName=number
					Element childElement = doc.createElement(WALL_BLOCK);

					childElement.setAttribute("x", String.valueOf(x));
					childElement.setAttribute("y", String.valueOf(y));

					// Adding number tag havind id attribute to root element
					root.appendChild(childElement);
				}
			}
		}
		saveFile();
	}

	private static Element getRootElement() {
		File file = new File(fileName);

		// Create instance of DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// Get the DocumentBuilder
		DocumentBuilder docBuilder;
		try {
			docBuilder = factory.newDocumentBuilder();

			// Parsing XML Document
			doc = docBuilder.parse(file);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc == null ? null : doc.getDocumentElement();
	}
	
	private static void saveFile() throws TransformerFactoryConfigurationError, FileNotFoundException, IOException {
		try {
			// setting up a transformer
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();

			// generating string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(doc);
			trans.transform(source, result);
			String xmlString = sw.toString();

			// Saving the XML content to File
			OutputStream f0;
			byte buf[] = xmlString.getBytes();
			f0 = new FileOutputStream(fileName);
			for (int i = 0; i < buf.length; i++) {
				f0.write(buf[i]);
			}
			f0.close();
			buf = null;
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
