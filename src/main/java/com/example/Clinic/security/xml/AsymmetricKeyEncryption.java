package com.example.Clinic.security.xml;

import com.example.Clinic.model.PatientBook;
import org.apache.xml.security.encryption.EncryptedData;
import org.apache.xml.security.encryption.EncryptedKey;
import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.encryption.XMLEncryptionException;
import org.apache.xml.security.keys.KeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
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
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

//Generise tajni kljuc
//Kriptije sadrzaj elementa student tajnim kljucem
//Kriptuje tajni kljuc javnim kljucem
//Kriptovani tajni kljuc se stavlja kao KeyInfo kriptovanog elementa
public class AsymmetricKeyEncryption {
//	private static final String IN_FILE = "./data/univerzitet.xml";
//	private static final String OUT_FILE = "./data/univerzitet_enc2.xml";
	private static final String KEY_STORE_FILE = "./src/main/java/com/example/clinic/security/xml/my-keystore.jks";



	static {
		// staticka inicijalizacija
		Security.addProvider(new BouncyCastleProvider());
		org.apache.xml.security.Init.init();
	}

	public String encryptIt(PatientBook book) throws IOException, SAXException, ParserConfigurationException {
		// ucitava se dokument
//		PatientBook book = patientBookRepository.findAll().get(0);
//
//		System.out.println("Im here");
//		System.out.println(book);

		String bookXml = book.toXML();
		System.out.println("book xml: ");
		System.out.println(bookXml);

//		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//		InputSource is = new InputSource();
//		is.setCharacterStream(new StringReader(bookXml));
//		Document doc = db.parse(is);
		System.out.println("document: ");
		//DocumentBuilder builder = new DocumentBuilder();
		//Document doc = DocumentBuilder.builder.parse(new InputSource(new StringReader(bookXml)));
		Document doc = convertStringToXMLDocument(bookXml);
		System.out.println(doc.getDocumentElement().getNodeName());
		//Document doc = loadDocument(IN_FILE);
		
		// generise tajni session kljuc
		System.out.println("Generating secret key ....");
		SecretKey secretKey = generateDataEncryptionKey();
		
		// ucitava sertifikat za kriptovanje tajnog kljuca
		Certificate cert = readCertificate();
		
		// kriptuje se dokument
		System.out.println("Encrypting....");
		String encrypted = encrypt(doc, secretKey, cert);
		
		// snima se tajni kljuc
		// snima se dokument
		//saveDocument(doc, OUT_FILE);
		System.out.println(encrypted);
		System.out.println("Encryption done");
		return encrypted;
	}

	/**
	 * Kreira DOM od XML dokumenta
	 */
//	private Document loadDocument(String file) {
//		try {
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//			dbf.setNamespaceAware(true);
//			DocumentBuilder db = dbf.newDocumentBuilder();
//			Document document = db.parse(new File(file));
//
//			return document;
//		} catch (FactoryConfigurationError e) {
//			e.printStackTrace();
//			return null;
//		} catch (ParserConfigurationException e) {
//			e.printStackTrace();
//			return null;
//		} catch (SAXException e) {
//			e.printStackTrace();
//			return null;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	/**
	 * Ucitava sertifikat is KS fajla alias primer
	 */
	private Certificate readCertificate() {
		try {
			// kreiramo instancu KeyStore
			KeyStore ks = KeyStore.getInstance("JKS", "SUN");
			// ucitavamo podatke
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(KEY_STORE_FILE));
			ks.load(in, "jelena".toCharArray());

			if (ks.isKeyEntry("jelena")) {
				Certificate cert = ks.getCertificate("jelena");
				return cert;
			} else
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
		}
	}


	/**
	 * Generise tajni kljuc
	 */
	private SecretKey generateDataEncryptionKey() {

		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede"); // Triple
																			// DES
			return keyGenerator.generateKey();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Kriptuje sadrzaj prvog elementa odsek
	 * @return
	 */
	private String encrypt(Document doc, SecretKey key, Certificate certificate) {

		try {

			// cipher za kriptovanje XML-a
			XMLCipher xmlCipher = XMLCipher.getInstance(XMLCipher.TRIPLEDES);
			
			// inicijalizacija za kriptovanje
			xmlCipher.init(XMLCipher.ENCRYPT_MODE, key);

			// cipher za kriptovanje tajnog kljuca,
			// Koristi se Javni RSA kljuc za kriptovanje
			//XMLCipher keyCipher = XMLCipher.getInstance(XMLCipher.RSA_v1dot5);

			XMLCipher keyCipher = XMLCipher.getInstance(XMLCipher.RSA_v1dot5);
			
			// inicijalizacija za kriptovanje tajnog kljuca javnim RSA kljucem
			keyCipher.init(XMLCipher.WRAP_MODE, certificate.getPublicKey());
			System.out.println("public key: ");
			System.out.println(certificate.getPublicKey());
			System.out.println("doc again: ");
			System.out.println(doc.getDocumentElement().getNodeName());
			System.out.println(key);
			// kreiranje EncryptedKey objekta koji sadrzi  enkriptovan tajni (session) kljuc
			EncryptedKey encryptedKey = keyCipher.encryptKey(doc, key);
			
			// u EncryptedData element koji se kriptuje kao KeyInfo stavljamo
			// kriptovan tajni kljuc
			// ovaj element je koreni elemnt XML enkripcije
			EncryptedData encryptedData = xmlCipher.getEncryptedData();
			
			// kreira se KeyInfo element
			KeyInfo keyInfo = new KeyInfo(doc);
			
			// postavljamo naziv 
			keyInfo.addKeyName("Kriptovani tajni kljuc");
			
			// postavljamo kriptovani kljuc
			keyInfo.add(encryptedKey);
			
			// postavljamo KeyInfo za element koji se kriptuje
			encryptedData.setKeyInfo(keyInfo);

			// trazi se element ciji sadrzaj se kriptuje
			NodeList patientBook = doc.getElementsByTagName("patientBook");
			Element book = (Element) patientBook.item(0);

			xmlCipher.doFinal(doc, book, true); // kriptuje sa sadrzaj


			return getStringFromDocument(doc);

		} catch (XMLEncryptionException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	public static Document main(String[] args) throws ParserConfigurationException, SAXException, IOException {
//		AsymmetricKeyEncryption encrypt = new AsymmetricKeyEncryption();
//		Document encrypted = encrypt.testIt();
//
//		return encrypted;
//	}

	public static String encryptMain(PatientBook patientBook) throws ParserConfigurationException, SAXException, IOException {
		AsymmetricKeyEncryption encrypt = new AsymmetricKeyEncryption();
		String encrypted = encrypt.encryptIt(patientBook);

		return encrypted;
	}

	public static String getStringFromDocument(Document doc)
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
