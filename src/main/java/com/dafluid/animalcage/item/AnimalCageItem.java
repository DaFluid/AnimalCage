package com.dafluid.animalcage.item;

import com.dafluid.animalcage.block.AnimalCageBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import java.lang.reflect.AnnotatedArrayType;

public class AnimalCageItem extends ItemBlock {
    public AnimalCageItem(AnimalCageBlock block) {
        super(block);
        setRegistryName(block.getRegistryName());
    }
}
