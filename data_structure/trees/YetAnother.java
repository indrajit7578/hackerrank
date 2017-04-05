import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class YetAnother {
	public static void main(String[] args) throws IOException {
		//HSSFEventFactory hs;
		//VBAMacroExtractor macEx = new VBAMacroExtractor();
		File input = new File("Newtest.xlsx");
		//macEx.extract(input, null);
		File output = new File("Res.xlsm");
		copyFile(input, output);
	}
	
	static void copyFile(File zipFile, File newFile) throws IOException {
	    ZipFile zipSrc = new ZipFile(zipFile);
	    ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(newFile));
	    boolean isInjected = false;
	    Enumeration srcEntries = zipSrc.entries();
	    while (srcEntries.hasMoreElements()) {
	            ZipEntry entry = (ZipEntry) srcEntries.nextElement();
	            ZipEntry newEntry = new ZipEntry(entry.getName());
	            zos.putNextEntry(newEntry);
	            //System.out.println(newEntry.getName());
	            BufferedInputStream bis = null;
	            
	            if(newEntry.getName().equalsIgnoreCase("xl/vbaProject.bin")) {
	            	//System.out.println("Matched");
	            	bis = new BufferedInputStream(new FileInputStream(new File("vbaProject.bin")));
	            	isInjected = true;
	            	System.out.println("Replaced Old bin");
	            } else if(newEntry.getName().equalsIgnoreCase("[Content_Types].xml")) {
	            	//System.out.println("Matched again");
	            	
	            	BufferedInputStream bis1 = new BufferedInputStream(zipSrc.getInputStream(entry));
	            	try {
	            		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	            		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	            		Document doc = docBuilder.parse(bis1);
	            		
	            		//root i.e Type
	            		Node type_head = doc.getFirstChild();
	            		
	            		/*NamedNodeMap nnm = company.getAttributes();
	            		Node attr = nnm.getNamedItem("xmlns");
	            		System.out.println(attr.getTextContent()+"");*/
	            		
	            		Element default_element = doc.createElement("Default");
	            		default_element.setAttribute("Extension", "bin");
	            		default_element.setAttribute("ContentType", "application/vnd.ms-office.vbaProject");
	            		//append the default_element to 
	            		type_head.appendChild(default_element);
	            		
	            		//Modify the Override Component
	            		NodeList override_list = doc.getElementsByTagName("Override");
	            		for(int i=0;i<override_list.getLength();i++) {
	            			Node n = override_list.item(i);
	            			NamedNodeMap attr_map = n.getAttributes();
	            			if(attr_map.getNamedItem("PartName").getTextContent().equalsIgnoreCase("/xl/workbook.xml")) {
	            				attr_map.getNamedItem("ContentType").setTextContent("application/vnd.ms-excel.sheet.macroEnabled.main+xml");
	            				break;
	            			}
	            		}
	            		
	            		
	            		// write the content into xml file
	            		TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            		Transformer transformer = transformerFactory.newTransformer();
	            		DOMSource source = new DOMSource(doc);
	            		StreamResult result = new StreamResult(new File("temp.xml"));
	            		
	            		transformer.transform(source, result);
	            		bis = new BufferedInputStream(new FileInputStream(new File("temp.xml")));
	            		
	            	} catch(Exception e) {
	            		
	            	}
	            } else if(newEntry.getName().equalsIgnoreCase("xl/_rels/workbook.xml.rels")) {
	            	BufferedInputStream bis1 = new BufferedInputStream(zipSrc.getInputStream(entry));
	            	
	            	try{
	            		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	            		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	            		Document doc = docBuilder.parse(bis1);
	            		
	            		//root i.e Relationships
	            		Node rel_head = doc.getFirstChild();
	            		
	            		Element rel_element = doc.createElement("Relationship");
	            		rel_element.setAttribute("Id","rId7");
	            		rel_element.setAttribute("Type", "http://schemas.microsoft.com/office/2006/relationships/vbaProject");
	            		rel_element.setAttribute("Target", "vbaProject.bin");
	            		
	            		rel_head.appendChild(rel_element);
	            		// write the content into xml file
	            		TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            		Transformer transformer = transformerFactory.newTransformer();
	            		DOMSource source = new DOMSource(doc);
	            		StreamResult result = new StreamResult(new File("temp1.xml"));
	            		
	            		transformer.transform(source, result);
	            		bis = new BufferedInputStream(new FileInputStream(new File("temp1.xml")));
	            	}catch(Exception e) {
	            		
	            	}
	            	
	            	
	            } else {
	            	bis = new BufferedInputStream(zipSrc.getInputStream(entry));

	            }
	             
	            while ( bis.available() > 0 ) {
	                    zos.write(bis.read());
	            }
	            zos.closeEntry();

	            bis.close();
	    }
	    if(! isInjected) {
	    	zos.putNextEntry(new ZipEntry("xl/vbaProject.bin"));
	    	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("vbaProject.bin")));
	    	while ( bis.available() > 0 ) {
                zos.write(bis.read());
	    	}
	    	zos.closeEntry();

	    	bis.close();
	    	System.out.println("Injected Externally");
	    }
	    
	    zos.finish();
	    zos.close();
	    zipSrc.close();
	 }
}
