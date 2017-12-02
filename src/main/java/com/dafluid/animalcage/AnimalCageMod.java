package com.dafluid.animalcage;

import com.dafluid.animalcage.block.AnimalCageBlock;
import com.dafluid.animalcage.environment.IModEnvironment;
import com.dafluid.animalcage.item.AnimalCageItem;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = AnimalCageMod.MODID, name = AnimalCageMod.NAME, version = AnimalCageMod.VERSION)
@Mod.EventBusSubscriber
public class AnimalCageMod
{
    public static final String MODID = "animalcage";
    public static final String NAME = "Animal Cage";
    public static final String VERSION = "@VERSION@";

    @Mod.Instance(MODID)
    public static AnimalCageMod instance;

    public static AnimalCageBlock animalCageBlock = initializeBlock(new AnimalCageBlock());
    public static AnimalCageItem animalCageItem;

    private static AnimalCageBlock initializeBlock(AnimalCageBlock block){
        block.setRegistryName("animal_cage");
        block.setUnlocalizedName("animal_cage");
        animalCageItem = new AnimalCageItem(block);
        animalCageItem.setUnlocalizedName("animal_cage");
        animalCageItem.setCreativeTab(CreativeTabs.MISC);
        return block;
    }

    @SidedProxy(clientSide = "com.dafluid.animalcage.environment.client.ClientProxy", serverSide = "com.dafluid.animalcage.environment.server.ServerProxy")
    public static IModEnvironment proxy;

    public static final Logger LOGGER = LogManager.getLogger(AnimalCageMod.MODID);

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        LOGGER.info("Starting initialization...");
        proxy.init();
        LOGGER.info("Initialization complete!");
    }

    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event){
        AnimalCageMod.LOGGER.info("Registering blocks...");
        IForgeRegistry<Block> blockRegistry = event.getRegistry();
        blockRegistry.register(animalCageBlock);
        AnimalCageMod.LOGGER.info("Registering blocks done.");
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event){
        AnimalCageMod.LOGGER.info("Registering items... ");
        IForgeRegistry<Item> itemRegistry = event.getRegistry();
        itemRegistry.register(animalCageItem);/*
        ModelLoader.setCustomModelResourceLocation(AnimalCageMod.animalCage, 0,
                new ModelResourceLocation(MODID + ":" + "animal_cage","inventory"));
        ModelBakery.registerItemVariants(animalCage,new ModelResourceLocation(MODID + ":" + "animal_cage_model","inventory"));*/
        AnimalCageMod.LOGGER.info("Registering items done.");
    }



    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        event.getRegistry().register(
                new ShapedOreRecipe(new ResourceLocation("animal_cage"), new ItemStack(AnimalCageMod.animalCageItem, 1),
                        new Object[]{"BPP", "BHP", "BPP",
                                'B', Blocks.IRON_BARS,
                                'P', Blocks.PLANKS,
                                'H', Blocks.HAY_BLOCK
                        }).setRegistryName(AnimalCageMod.MODID, "animal_cage"));
    }

}
