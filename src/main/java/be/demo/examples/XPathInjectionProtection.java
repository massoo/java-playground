package be.demo.examples;

import be.demo.exception.TechnicalException;
import be.demo.utility.MapVariableResolver;
import be.demo.utility.Utility;
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
 * note
 * xpath.setXPathVariableResolver(variableResolver); works only at compile time
 */
public class XPathInjectionProtection {

    private static final DocumentBuilderFactory domFactory;
    private static final XPath xPath;

    static {
        domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);

        XPathFactory factory = XPathFactory.newInstance();
        xPath = factory.newXPath();

    }

    private static final Logger LOG = LoggerFactory.getLogger(XPathInjectionProtection.class);

    public static void main(String[] args) {

        String dirtyString = "' or 1=1'";

        Document document = getXMLDocument("xml/books.xml");

        if (document == null) {
            throw new TechnicalException("XML document not loaded");
        }

        //xPathProtectionVariableResolver(document,dirtyString);
        xPathProtectionStringCleaner(document, dirtyString);

    }

    public static void xPathProtectionStringCleaner(Document document, String dirtyString) {
        try {

            dirtyString = Utility.normalize(dirtyString);

            String cleanString = dirtyString.replaceAll("[^a-zA-Z0-9]", "");
            LOG.info(cleanString);
            XPathExpression xPathExpression = xPath.compile("//book[author='" + cleanString + "']/title/text()");

            printResult(document, xPathExpression);

        } catch (XPathExpressionException ex) {
            throw new TechnicalException(ex);
        }
    }

    public static void xPathProtectionVariableResolver(Document document, String dirtyString) {
        try {

            dirtyString = Utility.normalize(dirtyString);

            String cleanString = dirtyString.replaceAll("[^a-zA-Z0-9]", "");

            MapVariableResolver mapVariableResolver = new MapVariableResolver();
            mapVariableResolver.setVariable("author_name", cleanString);

            xPath.setXPathVariableResolver(mapVariableResolver);
            XPathExpression xPathExpression = xPath.compile("//book[author=$author_name]/title/text()");

            printResult(document, xPathExpression);

        } catch (XPathExpressionException ex) {
            throw new TechnicalException(ex);
        }
    }

    public static Document getXMLDocument(String xmlDocument) {

        File file = new File(XPathInjectionProtection.class.getClassLoader().getResource(xmlDocument).getFile());

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

    public static void printResult(Document document, XPathExpression xPathExpression) {
        try {
            Object result = xPathExpression.evaluate(document, XPathConstants.STRING);
            String bookTitle = String.valueOf(result);
            LOG.info("Title of the book = " + bookTitle);

        } catch (XPathExpressionException ex) {
            throw new TechnicalException(ex);
        }
    }

}
