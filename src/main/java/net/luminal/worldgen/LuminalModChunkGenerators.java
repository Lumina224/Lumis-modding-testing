package net.luminal.worldgen;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.luminal.Luminal.MOD_ID;

public class LuminalModChunkGenerators {
    public static final DeferredRegister<MapCodec<? extends ChunkGenerator>> REGISTRY =
            DeferredRegister.create(Registries.CHUNK_GENERATOR, MOD_ID);

    public static final DeferredHolder<MapCodec<? extends ChunkGenerator>, MapCodec<? extends ChunkGenerator>> SBER_GENERATOR =
            REGISTRY.register("level_generator", () -> LevelChunkGenerator.CODEC);
}

