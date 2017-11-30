package com.dafluid.animalcage.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;

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
