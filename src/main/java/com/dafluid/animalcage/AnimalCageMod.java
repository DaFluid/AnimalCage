package com.dafluid.animalcage;

import com.dafluid.animalcage.environment.IModEnvironment;
import com.dafluid.animalcage.item.AnimalCage;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockSpecial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
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

    public static final Item animalCage = initializeItemInstance(new AnimalCage(),"animal_cage");

    private static Item initializeItemInstance(Item item, String name){
        return item.setUnlocalizedName(name)
                .setRegistryName(AnimalCageMod.MODID,name)
                .setCreativeTab(CreativeTabs.MISC);
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
    public static void registerItem(RegistryEvent.Register<Item> event){
        AnimalCageMod.LOGGER.info("Registering items...");
        IForgeRegistry<Item> itemRegistry = event.getRegistry();
        itemRegistry.register(animalCage);
        AnimalCageMod.LOGGER.info("Registering items done.");
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        event.getRegistry().register(
                new ShapedOreRecipe(new ResourceLocation("animal_cage"), new ItemStack(AnimalCageMod.animalCage, 1),
                        new Object[]{"BPP", "BHP", "BPP",
                                'B', Blocks.IRON_BARS,
                                'P', Blocks.PLANKS,
                                'H', Blocks.HAY_BLOCK
                        }).setRegistryName(AnimalCageMod.MODID, "animal_cage"));
    }

}
