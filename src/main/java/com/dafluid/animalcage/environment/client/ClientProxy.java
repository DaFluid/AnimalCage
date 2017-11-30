package com.dafluid.animalcage.environment.client;

import com.dafluid.animalcage.AnimalCageMod;
import com.dafluid.animalcage.environment.IModEnvironment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

import static com.dafluid.animalcage.AnimalCageMod.MODID;

public class ClientProxy implements IModEnvironment {
    @Override
    public void init() {
        registerItemModel(AnimalCageMod.animalCage, 0);
    }

    private void registerItemModel(Item item, int meta) {
        AnimalCageMod.LOGGER.info("Registering item model: "+MODID + ":" + item.getUnlocalizedName());
        ModelResourceLocation resourceLocation = new ModelResourceLocation(MODID + ":" + item.getUnlocalizedName(), "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, resourceLocation);
    }
}
