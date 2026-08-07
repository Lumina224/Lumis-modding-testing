package net.luminal.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.internal.BrandingControl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {

    protected TitleScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void modifyButtons(CallbackInfo ci) {
        this.children().removeIf(this::shouldRemove);
        this.renderables.removeIf(this::shouldRemove);

        for (var child : this.children()) {
            if (child instanceof AbstractWidget widget) {
                String text = widget.getMessage().getString().toLowerCase();

                if (text.contains("options") || text.contains("quit")) {
                    widget.setY(widget.getY() - 58);
                }
            }
        }
    }

    private boolean shouldRemove(Object element) {
        if (element instanceof Button button) {
            String text = button.getMessage().getString().toLowerCase();
            return text.contains("realms")
                    || text.contains("mods")
                    || text.contains("language")
                    || text.contains("accessibility");
        }
        return false;
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void drawCustomVersion(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        guiGraphics.drawString(this.font, "Minecraft 1.7.3", 2, this.height - 10, 0xFFFFFF);
    }
}

@Mixin(BrandingControl.class)
class BrandingControlMixin {
    @Inject(method = "forEachLine", at = @At("HEAD"), cancellable = true)
    private static void removeBranding(boolean includeMC, boolean reverse, BiConsumer<Integer, String> lineConsumer, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "forEachAboveCopyrightLine", at = @At("HEAD"), cancellable = true)
    private static void removeBrandingAboveCopyright(BiConsumer<Integer, String> lineConsumer, CallbackInfo ci) {
        ci.cancel();
    }
}