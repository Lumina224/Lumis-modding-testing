package net.luminal.world.dimension;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;

public class LevelEffects extends DimensionSpecialEffects{

    public LevelEffects() {
        super(
                Float.NaN,
                false,
                DimensionSpecialEffects.SkyType.NONE,
                false,
                false
        );
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 color, float brightness) {
        return color;
    }

    @Override
    public boolean isFoggyAt(int x, int z) {
        return false;
    }
}
