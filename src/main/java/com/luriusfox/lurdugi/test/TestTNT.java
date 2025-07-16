package com.luriusfox.lurdugi.test;

import com.luriusfox.lurdugi.registry.BlocksRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;

public class TestTNT extends TntBlock {

    public static final String id = "test_tnt";
    public static final Property<Integer> TNT_COUNT = IntegerProperty.create("tnt_count", 0, 50);

    public TestTNT() {
        super(BlockBehaviour.Properties.of()
                .setId(BlocksRegistry.BLOCKS.key(id))
                .explosionResistance(0.0F)
                .noCollission()
                .instabreak()
                .randomTicks());

        this.registerDefaultState(this.stateDefinition.any().setValue(TNT_COUNT, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> _builder) {
        super.createBlockStateDefinition(_builder);
        _builder.add(TNT_COUNT);
    }

    @Override
    public void onPlace(BlockState _state, Level _level, BlockPos _pos, BlockState _oldState, boolean _moved) {
        
        int _tntTotal = _level.getRandom().nextInt(0, 50);
        _level.setBlockAndUpdate(_pos, _state.setValue(TNT_COUNT, _tntTotal));
        _level.scheduleTick(_pos, this, 5); // Premier tick dans 5 ticks
        super.onPlace(_state, _level, _pos, _oldState, _moved);
    }

    @Override
    public void tick(BlockState _state, ServerLevel _level, BlockPos _pos, RandomSource _random) {
        int _remaining = _state.getValue(TNT_COUNT);

        if (_remaining > 0) {
            PrimedTnt _tnt = new PrimedTnt(_level, _pos.getX() + 0.5, _pos.getY(), _pos.getZ() + 0.5, null);
            _tnt.setFuse(40 + _random.nextInt(20));
            _level.addFreshEntity(_tnt);
            _level.playSound(null, _tnt.getX(), _tnt.getY(), _tnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            _level.gameEvent(_tnt, GameEvent.PRIME_FUSE, _pos);

            // Réduire le compteur de TNT sans setblock
            int _newCount = _remaining - 1;
            _state.setValue(TNT_COUNT, _newCount);
            // Mettre à jour l'état du bloc sans changer le bloc
            _level.setBlockAndUpdate(_pos, _state);
            _level.scheduleTick(_pos, this, 5);
        } else {
            _level.setBlock(_pos, Blocks.AIR.defaultBlockState(), 3);
        }
    }
}
