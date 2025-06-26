package viaforge.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import viaforge.ViaForge;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
  @Inject(method = "startGame", at = @At(value = "RETURN"))
  private void viaforge$init(CallbackInfo ci) {
    ViaForge.init();
  }
}
