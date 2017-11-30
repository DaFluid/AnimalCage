package com.dafluid.animalcage;

import com.dafluid.animalcage.environment.IModEnvironment;
import com.dafluid.animalcage.items.AnimalCage;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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


}
