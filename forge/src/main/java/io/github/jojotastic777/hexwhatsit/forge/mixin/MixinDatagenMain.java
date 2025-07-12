package io.github.jojotastic777.hexwhatsit.forge.mixin;

import io.github.jojotastic777.hexwhatsit.Hexwhatsit;
import org.spongepowered.asm.mixin.Mixin;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

// scuffed workaround for https://github.com/architectury/architectury-loom/issues/189
@Mixin(net.minecraft.data.Main.class)
public class MixinDatagenMain {
    @WrapMethod(method = "main", remap = false)
    private static void hexwhatsit$systemExitAfterDatagenFinishes(String[] strings, Operation<Void> original) {
        try {
            original.call((Object) strings);
        } catch (Throwable throwable) {
            Hexwhatsit.LOGGER.error("Datagen failed!", throwable);
            System.exit(1);
        }
        Hexwhatsit.LOGGER.info("Datagen succeeded, terminating.");
        System.exit(0);
    }
}
