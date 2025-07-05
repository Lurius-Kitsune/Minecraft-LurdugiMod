package com.luriusfox.lurdugi.test;

import com.luriusfox.lurdugi.registry.BlocksRegistry;

import net.minecraft.world.level.block.Block;

public class TestBlock extends Block {

    public static final String id = "test_block";
    public TestBlock() {
        super(Block.Properties.of()
        .setId(BlocksRegistry.BLOCKS.key(id)));
    }
    
}