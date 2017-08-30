/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.model.io;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import world.bilo.stack.BlockId;
import world.bilo.stack.BlockType;
import world.bilo.stack.Rotation;
import world.bilo.stack.Vector;

public class BlockLoader {
  public static String loadName(Document doc) {
    Element root = doc.getDocumentElement();
    return root.getAttribute("name");
  }

  public static List<BlockId> loadBlocks(Document doc) {
    Element root = doc.getDocumentElement();
    NodeList blocks = root.getElementsByTagName("block");

    List<BlockId> list = new ArrayList<>();
    for (int i = 0; i < blocks.getLength(); i++) {
      Element element = (Element) blocks.item(i);

      BlockType type = parseBlockType(element.getAttribute("type"));
      Vector position = parsePosition(element.getAttribute("x"), element.getAttribute("y"), element.getAttribute("z"));
      Rotation rotation = parseRotation(element.getAttribute("rotation"));

      list.add(new BlockId(type, position, rotation));
    }
    return list;
  }

  private static Rotation parseRotation(String value) {
    switch (value) {
      case "0":
        return Rotation.Deg0;
      case "90":
        return Rotation.Deg90;
      case "180":
        return Rotation.Deg180;
      case "270":
        return Rotation.Deg270;
    }

    throw new IllegalArgumentException("unknown rotation: " + value);
  }

  private static Vector parsePosition(String x, String y, String z) {
    return new Vector(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z));
  }

  private static BlockType parseBlockType(String value) {
    switch (value) {
      case "2x2":
        return BlockType.Block2x2;
      case "4x2":
        return BlockType.Block4x2;
    }

    throw new IllegalArgumentException("unknown block type: " + value);
  }
}