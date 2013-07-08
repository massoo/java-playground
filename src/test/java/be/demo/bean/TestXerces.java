package be.demo.bean;

import be.demo.bad.exception.TechnicalException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.net.URL;

/**
 * User: massoo
 */
public class TestXerces {

    private static final Logger LOG = LoggerFactory.getLogger(TestXerces.class);

    private DocumentBuilder builder;
    static final String JAXP_SCHEMA_SOURCE =
            "http://java.sun.com/xml/jaxp/properties/schemaSource";
    static final String JAXP_SCHEMA_LANGUAGE =
            "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA =
            "http://www.w3.org/2001/XMLSchema";

    @Test
    public void testXerces() {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {

            URL schemaFile = getResource("src/main/resources/xml/books.xsd");
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(schemaFile);

            documentBuilderFactory.setNamespaceAware(true);
            documentBuilderFactory.setValidating(true);

            documentBuilderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            documentBuilderFactory.setAttribute(JAXP_SCHEMA_SOURCE,
                    new File("src/main/resources/xml/books.xsd"));

            // protects against xml bomb attack
            // protected against recursion out of the box (without security measures)
            //documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // protects against doc-type related issues by prohibiting a doctype in the xml
            //documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);

            // not working
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);

            builder = documentBuilderFactory.newDocumentBuilder();
            //builder.setErrorHandler(new MyErrorHandler());

        } catch (Exception e) {
            throw new TechnicalException(e);
        }

        Source xmlFile = new StreamSource(new File("src/main/resources/xml/external-entity.xml"));
        validate(xmlFile);
    }

    public URL getResource(String resource) throws Exception {
        return new File(resource).toURL();
    }

    public void validate(Source xmlFile) {

        try {

            builder.parse(xmlFile.getSystemId());
            //validator.validate(xmlFile);

            LOG.info("XML is valid: {}", xmlFile.getSystemId());
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    public class MyErrorHandler implements ErrorHandler {
        public void warning(SAXParseException exception) throws SAXException {
            // do something
            LOG.error(exception.getMessage());
        }

        public void error(SAXParseException exception) throws SAXException {
            // do something with the error
            LOG.error(exception.getMessage());
        }

        public void fatalError(SAXParseException exception) throws SAXException {
            // do something with the error
            LOG.error(exception.getMessage());
        }
    }

}
