package viaforge.mixin;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import de.florianmichael.vialoadingbase.ViaLoadingBase;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Entity.class)
public abstract class MixinEntity {
  @ModifyConstant(method = "getCollisionBorderSize", constant = @Constant(floatValue = 0.1F))
  private float viaforge$fixHitboxSize(float constant) {
    ProtocolVersion targetVersion = ViaLoadingBase.getInstance().getTargetVersion();
    return targetVersion.newerThanOrEqualTo(ProtocolVersion.v1_9) ? 0.0F : constant;
  }
}
