package be.demo.bad.examples;

import be.demo.bad.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.File;

/**
 * User: massoo
 */

/**
 * Regex we could use [^a-zA-Z0-9 '"":\.\-]
 * <p/>
 * xpath.setXPathVariableResolver(variableResolver); works only at compile time
 */
public class XPathProtectionPreCompilation {

    private static final DocumentBuilderFactory domFactory;
    private static final XPathExpression xPathExpression;

    static {
        domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);

        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        try {
            xPathExpression = xpath.compile("//book[author='Author 1']/title/text()");
        } catch (XPathExpressionException ex) {
            throw new TechnicalException(ex);
        }

    }

    private static final Logger LOG = LoggerFactory.getLogger(XPathProtectionPreCompilation.class);

    public static void main(String[] args) {

        Document document = getXMLDocument("xml/books.xml");

        if (document == null) {
            throw new TechnicalException("XML document not loaded");
        }

        try {
            Object result = xPathExpression.evaluate(document, XPathConstants.STRING);
            String bookTitle = String.valueOf(result);
            LOG.info("Title of the book = {}",bookTitle);
        } catch (XPathExpressionException ex) {
            // error with evaluating the xpath expressing
            LOG.error(ex.getMessage());
        }

    }

    public static Document getXMLDocument(String xmlDocument) {

        File file = new File(XPathProtectionPreCompilation.class.getClassLoader().getResource(xmlDocument).getFile());

        if (!file.exists()) {
            LOG.error("File does not exists: {}", file.getAbsolutePath());
        } else {
            LOG.debug("File found: {}", file.getAbsolutePath());
        }

        try {
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            return builder.parse(file.getAbsolutePath());
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
    }


}
