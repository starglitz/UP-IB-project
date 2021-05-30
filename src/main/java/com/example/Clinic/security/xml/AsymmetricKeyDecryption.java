package com.example.Clinic.security.xml;

import com.example.Clinic.model.PatientBook;
import com.example.Clinic.repository.PatientBookRepository;
import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.encryption.XMLEncryptionException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;

//Dekriptuje tajni kljuc privatnim kljucem
//Tajnim kljucem dekriptuje podatke
public class AsymmetricKeyDecryption {

	@Autowired
	private PatientBookRepository patientBookRepository;

//	private static final String IN_FILE = "./data/univerzitet_enc2.xml";
//	private static final String OUT_FILE = "./data/univerzitet_dec2.xml";
	private static final String KEY_STORE_FILE = "./src/main/java/com/example/clinic/security/xml/my-keystore.jks";
	
    static {
    	//staticka inicijalizacija
        Security.addProvider(new BouncyCastleProvider());
        org.apache.xml.security.Init.init();
    }
    
	public String decryptIt(PatientBook book) throws IOException, SAXException, ParserConfigurationException {
		//ucitava se dokument
		//Document doc = loadDocument(IN_FILE);

//		PatientBook book = patientBookRepository.getOne(55L);
//
		String bookXml = book.getXml();


		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(bookXml));

		System.out.println("test test test");
		System.out.println(book.getXml());
		Document doc = convertStringToXMLDocument(book.getXml());
//		doc = AsymmetricKeyEncryption.encryptMain();


		PrivateKey pk = readPrivateKey();
		

		System.out.println("Decrypting....");
		String docXml = decrypt(doc, pk);

		System.out.println("decrypted document: ");
		System.out.println(docXml);

		System.out.println("Decryption done");
		return docXml;
	}
	

	/**
	 * Ucitava privatni kljuc is KS fajla
	 * alias primer
	 */
	private PrivateKey readPrivateKey() {
		try {
			//kreiramo instancu KeyStore
			KeyStore ks = KeyStore.getInstance("JKS", "SUN");
			
			//ucitavamo podatke
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(KEY_STORE_FILE));
			ks.load(in, "jelena".toCharArray());
			
			if(ks.isKeyEntry("jelena")) {
				PrivateKey pk = (PrivateKey) ks.getKey("jelena", "jelena".toCharArray());
				System.out.println("Private key:");
				System.out.println(pk);
				return pk;
			}
			else
				return null;
			
		} catch (KeyStoreException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (CertificateException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	/**
	 * Kriptuje sadrzaj prvog elementa odsek
	 */
	private String decrypt(Document doc, PrivateKey privateKey) {
		
		try {
			System.out.println("document im decrypting:");
			System.out.println(getStringFromDocument(doc));

			String docStr = getStringFromDocument(doc);

			XMLCipher xmlCipher = XMLCipher.getInstance();

			xmlCipher.init(XMLCipher.DECRYPT_MODE, null);

			xmlCipher.setKEK(privateKey);

			//NodeList test = doc.getElementsByTagName("xenc:EncryptedData");
			//Element test2 =(Element) test.item(0);

			NodeList encDataList = doc.getElementsByTagNameNS("http://www.w3.org/2001/04/xmlenc#", "EncryptedData");
			Element encData =(Element) encDataList.item(0);
//			System.out.println("encData:");
//			System.out.println(encData);
			System.out.println("-----------------------------------");
			//NodeList encDataList = doc.getElementsByTagName("EncryptedData");
//			NodeList encDataList = doc.getElementsByTagName("xenc:EncryptedData");
//			Element encData = (Element) encDataList.item(0);
			System.out.println("encData:");
			System.out.println(encData.getNodeName());

			xmlCipher.doFinal(doc, encData);
			System.out.println("pls");

			return getStringFromDocument(doc);
		} catch (XMLEncryptionException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
//		AsymmetricKeyDecryption decrypt = new AsymmetricKeyDecryption();
//		decrypt.testIt();
//	}

	public static String decryptMain(PatientBook book) throws ParserConfigurationException, SAXException, IOException {
		AsymmetricKeyDecryption decrypt = new AsymmetricKeyDecryption();
		String decrypted = decrypt.decryptIt(book);

		return decrypted;
	}




	public String getStringFromDocument(Document doc)
	{
		try
		{
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		}
		catch(TransformerException ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	private static Document convertStringToXMLDocument(String xmlString)
	{
		//Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		//API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try
		{
			//Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			//Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}


}


