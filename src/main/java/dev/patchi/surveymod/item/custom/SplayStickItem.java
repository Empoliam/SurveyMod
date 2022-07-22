package dev.patchi.surveymod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class SplayStickItem extends Item {

    public SplayStickItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        Player pPlayer = pContext.getPlayer();

        if (pContext.getLevel().isClientSide()) {

            BlockPos targetPos = pContext.getClickedPos();
            BlockHitResult clip = pContext.getLevel().clip(new ClipContext(vecFromBlockPos(targetPos), vecFromBlockPos(targetPos.above(250)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));

            double distance = clip.getLocation().distanceTo(vecFromBlockPos(targetPos));

            pPlayer.sendMessage(new TextComponent(Double.toString(distance)), pPlayer.getUUID());

        }

        return InteractionResult.PASS;

    }

    private Vec3 vecFromBlockPos(BlockPos bp) {
        return new Vec3(bp.getX(), bp.getY(), bp.getZ());
    }

}
