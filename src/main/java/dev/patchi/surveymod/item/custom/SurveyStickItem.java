package dev.patchi.surveymod.item.custom;

import dev.patchi.surveymod.SurveyLeg;
import dev.patchi.surveymod.SurveyMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public class SurveyStickItem extends Item {

    BlockPos positionA;
    BlockPos positionB;
    boolean measuring = false;

    public SurveyStickItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        if(pContext.getLevel().isClientSide()) {

            if(SurveyMod.activeTrip == null) {
                Player pPlayer = pContext.getPlayer();
                pPlayer.sendMessage((new TextComponent("Survey not initialized!")), pPlayer.getUUID());
                return InteractionResult.FAIL;
            }

            Player pPlayer = pContext.getPlayer();

            if(pPlayer.isShiftKeyDown()) {

                if(measuring) {
                    measuring = false;
                    pPlayer.sendMessage(new TextComponent("Stopped surveying"), pPlayer.getUUID());
                }

            } else {

                if (!measuring) {

                    measuring = true;
                    pPlayer.sendMessage(new TextComponent("Started surveying"), pPlayer.getUUID());
                    positionA = pContext.getClickedPos();

                } else {

                    positionB = pContext.getClickedPos();

                    Vec3 posAVec = new Vec3(positionA.getX(), positionA.getY(), positionA.getZ());
                    Vec3 posBVec = new Vec3(positionB.getX(), positionB.getY(), positionB.getZ());

                    String nameA = "X" + (int) posAVec.x() + "Y" + (int) posAVec.y() + "Z" + (int) posAVec.z();
                    String nameB = "X" + (int) posBVec.x() + "Y" + (int) posBVec.y() + "Z" + (int) posBVec.z();

                    Vec3 diff = posBVec.subtract(posAVec);
                    double distance = diff.length();
                    double compass = 180 - (Math.toDegrees(Math.atan2(diff.x(), diff.z())));
                    double clino = Math.toDegrees(Math.asin(diff.y() / diff.length()));

                    pPlayer.sendMessage(new TextComponent("Measured to station " + nameB), pPlayer.getUUID());

                    SurveyMod.activeTrip.addLeg(new SurveyLeg(nameA, nameB, distance, clino, compass));
                    positionA = positionB;

                }

            }

        }

        return InteractionResult.PASS;
    }

}
