package io.github.suchgo.hardcraft.event;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.init.EffectInit;
import io.github.suchgo.hardcraft.util.BasicUtil;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = HardCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    // Make all Logs breakable only to axes
    @SubscribeEvent
    public static void unbreakableWood(PlayerEvent.BreakSpeed event) {
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
    public static void damageableCactus(PlayerEvent.BreakSpeed event) {
        if (!event.getState().is(Blocks.CACTUS)) {
            return;
        }

        ItemStack itemStack = event.getEntity().getMainHandItem();
        if (itemStack.isEmpty()) {
            event.getEntity().hurt(event.getEntity().damageSources().cactus(), 1f);
        }
    }

    // Calculate when bleeding effect should be applied
    @SubscribeEvent
    public static void applyBleedingEffect(LivingDamageEvent event) {
        DamageSource source = event.getSource();

        if (event.getEntity() instanceof Skeleton) {
            return;
        }

        boolean shouldApplyEffect = Stream.of(DamageTypes.ARROW, DamageTypes.THORNS, DamageTypes.TRIDENT).anyMatch(source::is);

        if (!shouldApplyEffect && event.getSource().getDirectEntity() instanceof LivingEntity directEntity) {
            ItemStack itemStack = directEntity.getMainHandItem();
            shouldApplyEffect = Stream.of(ItemTags.SWORDS, ItemTags.PICKAXES, ItemTags.AXES, ItemTags.SHOVELS, ItemTags.HOES).anyMatch(itemStack::is);
            shouldApplyEffect |= directEntity instanceof Zombie || directEntity instanceof Wolf;
        }

        if (!shouldApplyEffect) {
            return;
        }

        if (BasicUtil.randomChance(15)) {
            event.getEntity().addEffect(new MobEffectInstance(EffectInit.BLEEDING_EFFECT.get(), 220));
        }
    }
}
