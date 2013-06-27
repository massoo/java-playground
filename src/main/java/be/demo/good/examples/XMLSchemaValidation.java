package be.demo.good.examples;

import be.demo.good.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
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
import javax.xml.validation.Validator;
import java.io.File;
import java.net.URL;

/**
 * User: massoo
 * We parse the XML first so we can check if it doesn't contain any parser attacks which
 * might blow up our JVM while validating. We can prove that entity-expansion attacks work against
 * simple validation with even settings certain features for the parser.
 */
public class XMLSchemaValidation {

    private static final Logger LOG = LoggerFactory.getLogger(XMLSchemaValidation.class);

    private static final DocumentBuilderFactory documentBuilderFactory;
    private static final DocumentBuilder builder;
    private static final Validator validator;

    static {

        documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {

            URL schemaFile = getResource("xml/books.xsd");
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(schemaFile);

            documentBuilderFactory.setNamespaceAware(true);
            // protects against xml bomb attack
            // protected against recursion out of the box (without security measures)
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // protects against XML bomb attack if you don't want to set secure processing
            //documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);

            validator = schema.newValidator();
            validator.setErrorHandler(new MyErrorHandler());

            builder = documentBuilderFactory.newDocumentBuilder();

        } catch (Exception e) {
            throw new TechnicalException(e);
        }


    }

    public static void main(String[] args) {
        Source xmlFile = new StreamSource(new File(getResource("xml/entity-expansion.xml").getFile()));
        validate(xmlFile);
    }

    public static URL getResource(String resource) {
        return XMLSchemaValidation.class.getClassLoader().getResource(resource);
    }

    public static void validate(Source xmlFile) {

        try {

            Document document = builder.parse(xmlFile.getSystemId());
            validator.validate(xmlFile);

            // process document

            LOG.info("XML is valid: {}", xmlFile.getSystemId());
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    public static class MyErrorHandler implements ErrorHandler {
        @Override
        public void warning(SAXParseException exception) throws SAXException {
            // do something
            LOG.error(exception.getMessage());
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
            // do something with the error
            LOG.error(exception.getMessage());
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
            // do something with the error
            LOG.error(exception.getMessage());
        }
    }

}


