/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.model.io;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlParser_Test {
  @Test
  public void parse_an_xml() {
    String xml = "";
    xml += "<model name=\"test\">";
    xml += "<block type=\"4x2\" rotation=\"180\"/>";
    xml += "</model>";

    Document doc = XmlParser.parse(stream(xml));
    Element root = doc.getDocumentElement();
    NodeList children = root.getChildNodes();

    assertEquals("test", root.getAttribute("name"));
    assertEquals(1, children.getLength());
    assertEquals("block", children.item(0).getNodeName());
    assertEquals("4x2", children.item(0).getAttributes().getNamedItem("type").getNodeValue());
    assertEquals("180", children.item(0).getAttributes().getNamedItem("rotation").getNodeValue());
  }

  private InputStream stream(String xml) {
    return new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
  }

}
