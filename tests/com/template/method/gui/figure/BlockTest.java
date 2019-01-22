package com.template.method.gui.figure;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

//TODO: implement this tests
class BlockTest {

   @Test
   void blockShouldBeCorrectlyInitializedAfterCreation() {
      Block sut = new Block(20, 21, 22, 23, Color.RED);
      assertEquals(20, sut.getBlockWidth());
      assertEquals(21,  sut.getBlockHeight());
      assertEquals(22, sut.getBlockX());
      assertEquals(23, sut.getBlockY());
      assertEquals(Color.RED, sut.getColor());

   }
}