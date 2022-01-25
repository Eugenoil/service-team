package org.purpleteam.serviceteam;


import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import org.json.XML;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FormatUtils {
    public JSONObject string2Json(String source) {
        return new JSONObject(source);
    }

    /***
     * Recursive search inside tree of children JSONObject item with the same <name> and in dependence of
     * value <partial> check full equal or part equal of item name
     * @param name text to compare items name to
     * @param partial how to compare: full equality (false) or not (true)
     * @return found JSONObject or NULL if not found
     */

    public Object findData(Object startFrom, String name, boolean partial) {
        if (!(startFrom instanceof JSONObject))
            return null;
        Set<String> jsonKeys = ((JSONObject)startFrom).keySet();
        if (jsonKeys.size() == 0)
            return null;
        if (!partial && jsonKeys.contains(name))
            return ((JSONObject)startFrom).getJSONObject(name);
        Object result = null;
        for (String s : jsonKeys) {
            Object thisObj = ((JSONObject)startFrom).getJSONObject(s);
            if ((partial && s.contains(name)) ||
                    (!partial && s.equals(name)))
                return thisObj;
            else if (((JSONObject)thisObj).keySet().size() > 0)
                result = findData(thisObj, name, partial);
            if (null != result)
                return result;
        }
        return null;
    }


    public Document string2XML(String source) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            return (Document) builder.parse(IOUtils.toInputStream(source, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String xml2String(Document source) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(source), new StreamResult(writer));
            String xmlString = writer.getBuffer().toString();
            xmlString = xmlString.replaceAll("><", ">\n<");
            return xmlString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /***
     * Recursive search inside tree of children Nodes item with the same <name> and in dependence of
     * value <partial> check full equal or part equal of Node name
     * @param name text to compare items name to
     * @param partial how to compare: full equality (false) or not (true)
     * @return found Node or NULL if not found
     */
    public Node findNode(Node startFrom, String name, boolean partial) {
        if ((partial && startFrom.getNodeName().contains(name)) ||
                (!partial && startFrom.getNodeName().equals(name)))
            return startFrom;
        NodeList children = startFrom.getChildNodes();
        if (children.getLength() == 0)
            return null;
        Node result = null;
        for (int i = 0; i < children.getLength(); i++) {
            if ((partial && children.item(i).getNodeName().contains(name)) ||
                    (!partial && children.item(i).getNodeName().equals(name)))
                result = children.item(i);
            if (null == result && children.item(i).hasChildNodes())
                result = findNode(children.item(i), name, partial);
            if (null != result)
                return result;
        }
        return null;
    }

    public JSONObject xml2Json(String source) {
        //        String json = jsonObj.toString();
        return XML.toJSONObject(source);
    }
}
