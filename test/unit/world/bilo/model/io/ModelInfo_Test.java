/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.model.io;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import world.bilo.stack.BlockId;
import world.bilo.stack.BlockType;
import world.bilo.stack.Rotation;
import world.bilo.stack.Vector;

public class ModelInfo_Test {
  final private List<BlockId> blocks = new ArrayList<>();

  @Test
  public void height_returns_heighest_block() {
    assertEquals(0, ModelInfo.height(blocks));

    blocks.add(new BlockId(BlockType.Block2x2, new Vector(0, 0, 0), Rotation.Deg0));
    assertEquals(0, ModelInfo.height(blocks));

    blocks.add(new BlockId(BlockType.Block2x2, new Vector(0, 0, 9), Rotation.Deg0));
    assertEquals(9, ModelInfo.height(blocks));

    blocks.add(new BlockId(BlockType.Block2x2, new Vector(0, 0, 2), Rotation.Deg0));
    assertEquals(9, ModelInfo.height(blocks));
  }

}
