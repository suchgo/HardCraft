package io.github.suchgo.hardcraft.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.suchgo.hardcraft.init.EffectInit;
import io.github.suchgo.hardcraft.init.SoundInit;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class BandageItem extends Item {
    private boolean shouldPlayUseSound = false;
    private final float healValue;
    public BandageItem(Properties properties, float healValue) {
        super(properties);

        this.healValue = healValue;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        player.startUsingItem(interactionHand);
        shouldPlayUseSound = true;
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public int getUseDuration(@NotNull ItemStack itemStack) {
        return 48;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemStack) {
        return UseAnim.CUSTOM;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        livingEntity.heal(healValue);
        livingEntity.removeEffect(EffectInit.BLEEDING_EFFECT.get());

        if (!(livingEntity instanceof Player) || !((Player)livingEntity).getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        return itemStack;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponent, @NotNull TooltipFlag pToolTipFlag) {
        pTooltipComponent.add(Component.translatable("tooltip.hardcraft.bandage"));

        super.appendHoverText(pStack, pLevel, pTooltipComponent, pToolTipFlag);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
                if (player.isUsingItem() && player.getUseItemRemainingTicks() > 0 && player.getUseItem() == itemInHand) {
                    float f = (float)player.getUseItemRemainingTicks() - partialTick + 1.0F;
                    float f1 = f / (float)itemInHand.getUseDuration();
                    if (f1 < 9F) {
                        float f2 = Mth.abs(Mth.cos(f / 14.0F * (float)Math.PI) * 0.1F);
                        poseStack.translate(0.0F, f2, 0.0F);
                    }

                    int j = player.getUseItemRemainingTicks();
                    boolean flag = j <= itemInHand.getUseDuration() - 4;

                    if (flag && j % 4 == 0 && shouldPlayUseSound) {
                        shouldPlayUseSound = false;
                        player.playSound(SoundInit.BANDAGE_USE.get(), 0.5F, player.level().random.nextFloat() * 0.1F + 0.9F);
                    }

                    float f3 = 1.0F - (float)Math.pow((double)f1, 27.0D);
                    int i = arm == HumanoidArm.RIGHT ? 1 : -1;
                    poseStack.translate(f3 * 0.4F * (float)i, f3 * -0.5F, f3 * 0.0F);
                    poseStack.mulPose(Axis.YP.rotationDegrees((float)i * f3 * 90.0F));
                    poseStack.mulPose(Axis.XP.rotationDegrees(f3 * 10.0F));
                    poseStack.mulPose(Axis.ZP.rotationDegrees((float)i * f3 * 30.0F));
                    poseStack.translate((float)i * 0.56F, -0.52F + equipProcess * -0.6F, -0.72F);
                }

                return IClientItemExtensions.super.applyForgeHandTransform(poseStack, player, arm, itemInHand, partialTick, equipProcess, swingProcess);
            }
        });
    }
}
