package viaforge.mixin;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import de.florianmichael.vialoadingbase.ViaLoadingBase;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends MixinEntity {
  @ModifyConstant(method = "onLivingUpdate", constant = @Constant(doubleValue = 0.005D))
  private double viaforge$fixMotion(double constant) {
    ProtocolVersion targetVersion = ViaLoadingBase.getInstance().getTargetVersion();
    return targetVersion.newerThanOrEqualTo(ProtocolVersion.v1_9) ? 0.003D : constant;
  }
}
