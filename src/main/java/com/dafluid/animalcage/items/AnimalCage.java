package com.dafluid.animalcage.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class AnimalCage extends Item {
    public AnimalCage(){
        setMaxStackSize(1);
        setMaxDamage(0);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if(hand== EnumHand.MAIN_HAND) {
            playerIn.sendMessage(new TextComponentString("Hello World"));
            return true;
        }
        return false;
    }


}
