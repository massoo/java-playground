package be.demo.utility;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathVariableResolver;
import java.util.HashMap;
import java.util.Map;

/**
 * User: massoo
 */
public class MapVariableResolver implements XPathVariableResolver {

    private Map<String,String> variableMap = new HashMap<String, String>();

    public void setVariable(String key, String value) {
        variableMap.put(key,value);
    }

    @Override
    public Object resolveVariable(QName variableName) {
        return variableMap.get(variableName.getLocalPart());
    }

}
