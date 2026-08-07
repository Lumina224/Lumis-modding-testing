package net.luminal.world.dimension;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class LevelEffects extends DimensionSpecialEffects{

    public LevelEffects() {
        super(
                -1,
                false,
                DimensionSpecialEffects.SkyType.END,
                false,
                true
        );
    }


    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 color, float brightness) {
        return color;
    }

    @Override
    public boolean isFoggyAt(int x, int z) {
        return true;
    }
}
