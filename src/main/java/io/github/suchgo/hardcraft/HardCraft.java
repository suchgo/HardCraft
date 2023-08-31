package io.github.suchgo.hardcraft;

import com.mojang.logging.LogUtils;
import io.github.suchgo.hardcraft.init.BlockInit;
import io.github.suchgo.hardcraft.init.CreativeTabInit;
import io.github.suchgo.hardcraft.init.ItemInit;
import io.github.suchgo.hardcraft.init.SoundInit;
import io.github.suchgo.hardcraft.init.LootModifiersInit;
import net.minecraft.client.Minecraft;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HardCraft.MODID)
public class HardCraft
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "hardcraft";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public HardCraft()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BlockInit.BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ItemInit.ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CreativeTabInit.CREATIVE_MODE_TABS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so sounds get registered
        SoundInit.SOUND_EVENTS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so loot modifiers get registered
        LootModifiersInit.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");

        LOGGER.info("Game rule 'naturalRegeneration' set to false");
        event.getServer().getWorldData().getGameRules().getRule(GameRules.RULE_NATURAL_REGENERATION).set(false, event.getServer());
    }

    // Make all Logs breakable only to axes
    @SubscribeEvent
    public void unbreakableWood(PlayerEvent.BreakSpeed event) {
        if (event.getState().getTags().noneMatch(tagKey -> tagKey == BlockTags.LOGS)) {
            return;
        }

        ItemStack itemStack = event.getEntity().getMainHandItem();
        if (itemStack.isEmpty()) {
            event.getEntity().hurt(event.getEntity().damageSources().cactus(), 1f);
        }

        if (!(itemStack.getItem() instanceof AxeItem)) {
            event.setCanceled(true);
        }
    }

    // Player take damage if he tries break cactus at empty hand
    @SubscribeEvent
    public void damageableCactus(PlayerEvent.BreakSpeed event) {
        if (!event.getState().is(Blocks.CACTUS)) {
            return;
        }

        ItemStack itemStack = event.getEntity().getMainHandItem();
        if (itemStack.isEmpty()) {
            event.getEntity().hurt(event.getEntity().damageSources().cactus(), 1f);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
