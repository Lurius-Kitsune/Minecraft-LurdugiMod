package com.luriusfox.lurdugi.test;

import com.luriusfox.lurdugi.registry.EntityRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class TestTNTBlockEntity extends BlockEntity {

    public TestTNTBlockEntity(BlockPos _pos, BlockState _state) {
        super(EntityRegistry.TEST_TNT.get(), _pos, _state);
    }

    private int tntCount = 0;

    public int getTntCount() {
        return tntCount;
    }

    public void setTntCount(int tntCount) {
        this.tntCount = tntCount;
        this.setChanged(); // Notify that the block entity has changed
    }

    @Override
    protected void saveAdditional(ValueOutput _tag) {
        _tag.putInt("TntCount", tntCount);
        super.saveAdditional(_tag);
    }

    @Override
    protected void loadAdditional(ValueInput _tag) {
        super.loadAdditional(_tag);
        this.tntCount = _tag.getInt("TntCount").get();
    }
}