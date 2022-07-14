package dev.patchi.surveymod.item.custom;

import dev.patchi.surveymod.SurveyMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public class SurveyStickItem extends Item {

    boolean targetSecond = false;
    BlockPos positionA;
    BlockPos positionB;

    public SurveyStickItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        if(pContext.getLevel().isClientSide()) {

            Player pPlayer = pContext.getPlayer();
            Level pLevel = pContext.getLevel();


            if(!targetSecond) {

                positionA = pContext.getClickedPos();
                pPlayer.sendMessage(new TextComponent("Station A Selected"), pPlayer.getUUID());
                targetSecond = true;

            } else {

                positionB = pContext.getClickedPos();

                Vec3 posAVec = new Vec3(positionA.getX(),positionA.getY(),positionA.getZ());
                Vec3 posBVec = new Vec3(positionB.getX(),positionB.getY(),positionB.getZ());

                String nameA = "X" + (int)posAVec.y() + "Y" + (int)posAVec.y() + "Z"+ (int)posAVec.z();
                String nameB = "X" + (int)posBVec.y() + "Y" + (int)posBVec.y() + "Z"+ (int)posBVec.z();

                Vec3 diff = posBVec.subtract(posAVec);
                double distance = diff.length();
                double compass = 180 - (Math.toDegrees(Math.atan2(diff.x(),diff.z())));
                double clino = Math.toDegrees(Math.asin(diff.y()/diff.length()));

                pPlayer.sendMessage(new TextComponent("Station B Selected"), pPlayer.getUUID());

                //pPlayer.sendMessage(new TextComponent("Distance = " + distance), pPlayer.getUUID());
                //pPlayer.sendMessage(new TextComponent("Compass = " + compass), pPlayer.getUUID());
                //pPlayer.sendMessage(new TextComponent("Clino = " + clino), pPlayer.getUUID());

                SurveyMod.surveyPoints.add(nameA + " " + nameB + " " + distance + " " + compass + " " + clino);

                targetSecond = false;

            }



        }

        return InteractionResult.PASS;
    }

}
