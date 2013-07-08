package be.demo.good.examples;

import be.demo.bad.exception.TechnicalException;
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
import java.io.File;
import java.net.URL;

/**
 * User: massoo
 */
public class XMLSchemaValidation {

    private static final Logger LOG = LoggerFactory.getLogger(XMLSchemaValidation.class);

    private static final String JAXP_SCHEMA_SOURCE =
            "http://java.sun.com/xml/jaxp/properties/schemaSource";
    private static final String JAXP_SCHEMA_LANGUAGE =
            "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static final String W3C_XML_SCHEMA =
            "http://www.w3.org/2001/XMLSchema";

    private static final DocumentBuilderFactory documentBuilderFactory;
    private static final DocumentBuilder builder;

    static {

        documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {

            documentBuilderFactory.setNamespaceAware(true);
            documentBuilderFactory.setValidating(true);

            // must be declared before we can assign the schema validation underneath
            //documentBuilderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);

            // Using a schema source results in ignoring DTD's
            //documentBuilderFactory.setAttribute(JAXP_SCHEMA_SOURCE, new File("src/main/resources/xml/books.xsd"));

            // protects against xml bomb attack
            // protected against recursion out of the box (without security measures)
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // protects against doc-type related issues by prohibiting a doctype in the xml
            // must be enabled if we really want to process DTD
            documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl",false);
            //documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar",false);

            // if DTD processing is allowd we will prohibit external entities
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);

            builder = documentBuilderFactory.newDocumentBuilder();
            //builder.setErrorHandler(new MyErrorHandler());

        } catch (Exception e) {
            throw new TechnicalException(e);
        }


    }

    public static void main(String[] args) {
        Source xmlFile = new StreamSource(new File(getResource("xml/external-entity.xml").getFile()));
        validate(xmlFile);
    }

    public static URL getResource(String resource) {
        return XMLSchemaValidation.class.getClassLoader().getResource(resource);
    }

    public static void validate(Source xmlFile) {

        try {

            builder.parse(xmlFile.getSystemId());
            LOG.info("XML is valid: {}", xmlFile.getSystemId());

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    public static class MyErrorHandler implements ErrorHandler {
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


