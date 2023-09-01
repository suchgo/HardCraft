package io.github.suchgo.hardcraft.event;

import io.github.suchgo.hardcraft.HardCraft;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HardCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
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
}
