/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.model.io;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import world.bilo.stack.BlockId;
import world.bilo.stack.BlockType;
import world.bilo.stack.Rotation;
import world.bilo.stack.Vector;

public class BlockLoader_Test {
  private final Document doc = createDoc();

  @Test
  public void loading_an_empty_file_returns_no_blocks() {

    List<BlockId> blocks = BlockLoader.loadBlocks(doc);

    assertEquals(0, blocks.size());
  }

  @Test
  public void read_the_model_name() {
    doc.getDocumentElement().setAttribute("name", "the name");

    assertEquals("the name", BlockLoader.loadName(doc));
  }

  @Test
  public void return_one_block() {
    Element block = doc.createElement("block");
    block.setAttribute("type", "4x2");
    block.setAttribute("x", "7");
    block.setAttribute("y", "3");
    block.setAttribute("z", "4");
    block.setAttribute("rotation", "180");
    doc.getDocumentElement().appendChild(block);

    List<BlockId> blocks = BlockLoader.loadBlocks(doc);

    assertEquals(1, blocks.size());
    assertEquals(new BlockId(BlockType.Block4x2, new Vector(7, 3, 4), Rotation.Deg180), blocks.get(0));
  }

  @Test
  public void return_four_block() {
    {
      Element block = doc.createElement("block");
      block.setAttribute("type", "2x2");
      block.setAttribute("x", "1");
      block.setAttribute("y", "2");
      block.setAttribute("z", "3");
      block.setAttribute("rotation", "0");
      doc.getDocumentElement().appendChild(block);
    }
    {
      Element block = doc.createElement("block");
      block.setAttribute("type", "4x2");
      block.setAttribute("x", "4");
      block.setAttribute("y", "5");
      block.setAttribute("z", "6");
      block.setAttribute("rotation", "90");
      doc.getDocumentElement().appendChild(block);
    }
    {
      Element block = doc.createElement("block");
      block.setAttribute("type", "2x2");
      block.setAttribute("x", "1");
      block.setAttribute("y", "0");
      block.setAttribute("z", "-1");
      block.setAttribute("rotation", "180");
      doc.getDocumentElement().appendChild(block);
    }
    {
      Element block = doc.createElement("block");
      block.setAttribute("type", "4x2");
      block.setAttribute("x", "-10");
      block.setAttribute("y", "-20");
      block.setAttribute("z", "-30");
      block.setAttribute("rotation", "270");
      doc.getDocumentElement().appendChild(block);
    }

    List<BlockId> blocks = BlockLoader.loadBlocks(doc);

    assertEquals(4, blocks.size());
    assertEquals(new BlockId(BlockType.Block2x2, new Vector(1, 2, 3), Rotation.Deg0), blocks.get(0));
    assertEquals(new BlockId(BlockType.Block4x2, new Vector(4, 5, 6), Rotation.Deg90), blocks.get(1));
    assertEquals(new BlockId(BlockType.Block2x2, new Vector(1, 0, -1), Rotation.Deg180), blocks.get(2));
    assertEquals(new BlockId(BlockType.Block4x2, new Vector(-10, -20, -30), Rotation.Deg270), blocks.get(3));
  }

  private Document createDoc() {
    try {
      Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
      doc.appendChild(doc.createElement("model"));
      return doc;
    } catch (ParserConfigurationException e) {
      throw new RuntimeException("could not create xml document");
    }
  }

}
