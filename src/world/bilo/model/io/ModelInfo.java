/*
 * Copyright 2017 Urs Fässler
 * SPDX-License-Identifier: Apache-2.0
 */

package world.bilo.model.io;

import world.bilo.stack.BlockId;

public class ModelInfo {

  public static int height(Iterable<BlockId> blocks) {
    int height = 0;
    for (BlockId block : blocks) {
      height = Math.max(height, block.position.z);
    }
    return height;
  }

}
