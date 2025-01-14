package src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


//BigJava Cay S. Horstmann 896-905
/**
 * The Class XMLHandler builds the XML files or retrieves an XML file and 
 * converts the values to a list. 
 */
public class XMLHandler {

	/** The builder. */
	private DocumentBuilder builder;
	
	/** The path. */
	private XPath path;
	
	/** The document. */
	private Document doc;
	
	
	
	/**
	 * Creating the builders 
	 * Instantiates a new xML handler.
	 *
	 * @throws ParserConfigurationException the parser configuration exception
	 */
	public XMLHandler() throws ParserConfigurationException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		XPathFactory xpFactory = XPathFactory.newInstance();
		path = xpFactory.newXPath();
	}
	
	/**
	 * Retrieves the specified XML File and inputs the data within into an arrayList
	 * Parses the file using the filename
	 *
	 * @param fileName the file name
	 * @return the array list containing the items.
	 * @throws SAXException the sAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws XPathExpressionException the x path expression exception
	 */
	public ArrayList<String> parse(String fileName) throws SAXException, IOException, XPathExpressionException {
		//creates a file from the file name
		File f = new File(fileName);
		Document doc = builder.parse(f);
		ArrayList<String> items = new ArrayList<String>();
		//uses the string values within sudoku 
		String sudoku = path.compile("Sudoku").evaluate(doc);
		//adds values to the list and returns the list
		items.add(sudoku);
	
		return items;
	}
	
	
	/**
	 * Builds the document, this method creates the document and inputs the items into a new file 
	 * specifying the file location, the file is then named with the current date.
	 *
	 * @param items the items to be placed within the XML
	 * @return the document
	 * @throws TransformerException the transformer exception
	 */
	public Document build (File dir,ArrayList<String> items) throws TransformerException{
		Date date = new Date();
		doc = builder.newDocument();
	//input the list of items and insert into the document
		doc.appendChild(createItems(items));
		//http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
		// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				//StreamResult result = new StreamResult(new File("/Users/TomClarke/SkyDrive/ComputerScience/Year3/3rd Year Programming/SudokuSolver/XMLFiles/"+date+".xml"));
				StreamResult result = new StreamResult(new File(dir+"/"+date+".xml"));
				transformer.transform(source, result);
				
				System.out.println("File saved!");
		return doc;
	}
	
	
	
/**
	 * Creates the items method takes the items from the list and insert into the sudoku element and store them 
	 * between these elements. and return it to the builder to be added to the document
	 * @param items the items
	 * @return the element
	 */
	private Element createItems(ArrayList<String> items){
	Element e = doc.createElement("Sudoku");
	for(String anItem : items)
		e.appendChild(doc.createTextNode(anItem));
	return e;
}






	
}
