package net.Usebess.FirstMod.item.Custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class SnowballGunItem extends Item {

    public SnowballGunItem(Properties pProperties) {
        super(pProperties);

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!pLevel.isClientSide)
        {
            if (!(itemStack.getDamageValue() + 1 == itemStack.getMaxDamage())) {

                Snowball snowball = new Snowball(pLevel, pPlayer);
                snowball.setItem(new ItemStack(Items.SNOWBALL));
                snowball.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0f, 1.5F, 1.0F);
                pLevel.addFreshEntity(snowball);

                itemStack.hurtAndBreak(1, pPlayer, player -> pPlayer.broadcastBreakEvent(pUsedHand));
            }
            else return InteractionResultHolder.fail(itemStack);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
    }
}
