package directory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import domain.City;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;

import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author macmini
 */
public class XmlReadFormatService {
    
    
    /**
     * Requierement;
     *  -   Is necessary return an structure with the points for city. 
     
     **/
    protected List<City> cities;

    private static final String FILENAME =
            "/Users/macmini/NetBeansProjects/CubeTrendsUtil/DepartmentsBaseList.xml";
    protected DocumentBuilderFactory dbf;

    public XmlReadFormatService() {
        dbf = DocumentBuilderFactory.newInstance();
    }
    
    public void readFile(String path){
        //return "";
    }

    protected Document readFile() {
        
        DocumentBuilder db = null;
        Document doc = null; 
        
        try {
            //Configuration to prevent XML injection or atacks and 0 day's ... etc

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            db = dbf.newDocumentBuilder();
            doc = db.parse(new File(FILENAME));
            doc.getDocumentElement().normalize();

            
            

        } catch (IOException e) {   
            System.out.println("Error on ioad xmI: "+e);
        } catch (SAXException aXException) {
            System.out.println("Error on Ioad xmI: "+aXException);
        } catch (ParserConfigurationException pce) {
            System.out.println("Error on Ioad xmI: "+pce);
        }
        return doc;
    }
    
    
    protected void loadCities(){
        
    }
    
//    public void readFile() {
//        try {
//            //Configuration to prevent XML injection or atacks and 0 day's ... etc
//
//            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
//
//            //parse X
//
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(new File(FILENAME));
//
//        } catch (IOException e) {            
//        } catch (SAXException aXException) {
//        } catch (ParserConfigurationException pce) {
//        }
//    }

    //public NodeList getCitiesFound(Document doc){
    public void getCitiesFound(Document doc) {
        NodeList list = doc.getElementsByTagName("register");

        City city = new City();
        
        for (int i = 0; i < list.getLength(); i++) {
            Object object = list.item(i);

            Node node = list.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String id = element.getAttribute("id");
                String name = element.getElementsByTagName("name").item(0).
                        getTextContent();
                System.out.println("Id: "+id);
                System.out.println("Name: "+name);
                
                
            }
        }
    }
    
    
    public static void main(String[] args){
        XmlReadFormatService service = new XmlReadFormatService();
        
        service.getCitiesFound(service.readFile());
    }
}
