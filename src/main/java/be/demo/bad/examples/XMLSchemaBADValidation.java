package be.demo.bad.examples;

import be.demo.bad.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * User: massoo
 * <p/>
 * INFO:
 *
 * Class still vulnerable for xml bomb attack
 *
 * To resolve schema's from other locations we need to implement a org.w3c.dom.ls.LSResourceResolver
 * Because of this we are protected from schema poisoning attacks
 */
public class XMLSchemaBADValidation {

    private static final Logger LOG = LoggerFactory.getLogger(XMLSchemaBADValidation.class);

    private static final SchemaFactory schemaFactory;
    private static final Validator validator;

    static {

        try {
            URL schemaFile = getResource("xml/books.xsd");
            schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // This has no effect on DoS (out of memory)
            schemaFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
            //schemaFactory.setFeature("http://xerces.apache.org/xerces1-j/features.html#external-general-entities", true);
            //schemaFactory.setFeature(Constants.XERCES_FEATURE_PREFIX + Constants.EXTERNAL_GENERAL_ENTITIES_FEATURE,false);

            // not recognized
            //schemaFactory.setFeature(Constants.XERCES_FEATURE_PREFIX + Constants.IGNORE_EXTERNAL_DTD,true);
            //schemaFactory.setFeature("http://xml.org/sax/features/validation", false);
            //schemaFactory.setFeature(Constants.XERCES_FEATURE_PREFIX + Constants.USE_ENTITY_RESOLVER2_FEATURE,false);
            //schemaFactory.setFeature(Constants.XERCES_FEATURE_PREFIX + Constants.RESOLVE_DTD_URIS_FEATURE,false);

            Schema schema = schemaFactory.newSchema(schemaFile);

            validator = schema.newValidator();
        } catch (SAXException e) {
            throw new TechnicalException(e);
        }

    }

    public static void main(String[] args) {
        Source xmlFile = new StreamSource(new File(getResource("xml/entity-expansion.xml").getFile()));
        validate(xmlFile);
    }

    public static URL getResource(String resource) {
        return XMLSchemaBADValidation.class.getClassLoader().getResource(resource);
    }

    public static void validate(Source xmlFile) {

        try {
            validator.validate(xmlFile);
            LOG.info("XML file is valid: {}",xmlFile.getSystemId());
        } catch (SAXException e) {
            LOG.error("XML file is NOT valid: {}", xmlFile.getSystemId());
            LOG.error("Reason: {}",e.getMessage());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

}
