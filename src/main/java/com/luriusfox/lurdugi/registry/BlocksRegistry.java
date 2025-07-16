package com.luriusfox.lurdugi.registry;

import java.util.function.Supplier;

import com.luriusfox.lurdugi.LurDuGiMod;
import com.luriusfox.lurdugi.registry.TabsRegistry.TabType;
import com.luriusfox.lurdugi.test.TestBlock;
import com.luriusfox.lurdugi.test.TestTNT;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class BlocksRegistry {
    private static RegistryObject<Block> RegisterBlocks(String _name, Supplier<? extends Block> _supplier) {
        return BLOCKS.register(_name, _supplier);
    }

    private static RegistryObject<Block> RegisterBlocks(String _name, Supplier<? extends Block> _supplierBlock,  BlockItem.Properties _supplierItem) {

        RegistryObject<Block> _block = RegisterBlocks(_name, _supplierBlock);
        ItemRegistry.RegisterItems(_name, () -> new BlockItem(_block.get(), _supplierItem.setId(ItemRegistry.ITEMS.key(_name))));
        return _block;
    }

    private static RegistryObject<Block> RegisterBlocks(String _name, Supplier<? extends Block> _supplierBlock,  BlockItem.Properties _supplierItem, TabType _tabType) {

        RegistryObject<Block> _block = RegisterBlocks(_name, _supplierBlock, _supplierItem);
        TabsRegistry.addToTab(_tabType, () -> _block.get());
        return _block;
    }


    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LurDuGiMod.MODID);

    public static final RegistryObject<Block> TEST_BLOCK = RegisterBlocks(TestBlock.id,  () -> new TestBlock(), new BlockItem.Properties());
    public static final RegistryObject<Block> TEST_TNT = RegisterBlocks(TestTNT.id, () -> new TestTNT(), new BlockItem.Properties().stacksTo(64), TabType.LURDUGI);
    

}
