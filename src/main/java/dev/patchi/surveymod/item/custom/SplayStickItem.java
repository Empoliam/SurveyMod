package dev.patchi.surveymod.item.custom;

import dev.patchi.surveymod.SurveyMod;
import dev.patchi.surveymod.SurveySplay;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class SplayStickItem extends Item {

    public SplayStickItem(Properties pProperties) {
        super(pProperties);
    }

    boolean leftRightMode = false;
    boolean justClicked = false;

    double distanceUp = 0;
    String stationName = "";

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        Player pPlayer = pContext.getPlayer();

        if (pContext.getLevel().isClientSide()) {

            if(pPlayer.isShiftKeyDown()) {

                if(leftRightMode) {
                    leftRightMode = false;
                    justClicked = false;
                    pPlayer.sendMessage(new TextComponent("Cancelled LRUD"), pPlayer.getUUID());
                }

            } else {

                if(!leftRightMode) {

                    BlockPos targetPos = pContext.getClickedPos();
                    stationName = "X" + (int) targetPos.getX() + "Y" + (int) targetPos.getY() + "Z" + (int) targetPos.getZ();

                    BlockHitResult clip = pContext.getLevel().clip(new ClipContext(vecFromBlockPos(targetPos), vecFromBlockPos(targetPos.above(200)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));

                    double distance = clip.getLocation().distanceTo(vecFromBlockPos(targetPos));
                    //pPlayer.sendMessage(new TextComponent(Double.toString(distance)), pPlayer.getUUID());

                    leftRightMode = true;
                    justClicked = true;

                    distanceUp = distance;

                }

            }

        }

        return InteractionResult.PASS;

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        if (pLevel.isClientSide()) {

            if(justClicked) {
                justClicked = false;
            } else {
                if (leftRightMode) {

                    Vec3 upVec = new Vec3(0,1,0);
                    Vec3 playerLookDir = pPlayer.getLookAngle();
                    Vec3 playerLeftDir = upVec.cross(playerLookDir).normalize().multiply(200,200,200);
                    Vec3 playerRightDir = playerLeftDir.reverse();

                    Vec3 playerHeadPos = pPlayer.getEyePosition();

                    BlockHitResult clipLeft = pLevel.clip(new ClipContext(playerHeadPos, playerLeftDir, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));
                    BlockHitResult clipRight = pLevel.clip(new ClipContext(playerHeadPos, playerRightDir, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));

                    double distanceLeft = clipLeft.getLocation().distanceTo(playerHeadPos);
                    double distanceRight = clipRight.getLocation().distanceTo(playerHeadPos);

                    //pPlayer.sendMessage(new TextComponent("Left = " + distanceLeft), pPlayer.getUUID());
                    //pPlayer.sendMessage(new TextComponent("Right = " + distanceRight), pPlayer.getUUID());

                    pPlayer.sendMessage(new TextComponent("Splay taken from station " + stationName), pPlayer.getUUID());

                    SurveySplay S = new SurveySplay(stationName, distanceLeft, distanceRight, distanceUp, 0.0);
                    SurveyMod.activeTrip.addSplay(S);

                    leftRightMode = false;

                }
            }

        }

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));

    }


    private Vec3 vecFromBlockPos(BlockPos bp) {
        return new Vec3(bp.getX(), bp.getY(), bp.getZ());
    }

}
