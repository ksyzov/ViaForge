package viaforge.mixin;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import de.florianmichael.vialoadingbase.ViaLoadingBase;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends MixinEntity {
  @ModifyConstant(method = "onUpdateWalkingPlayer", constant = @Constant(doubleValue = 9.0E-4D))
  private double viaforge$fixPointThree(double constant) {
    ProtocolVersion targetVersion = ViaLoadingBase.getInstance().getTargetVersion();
    return targetVersion.newerThanOrEqualTo(ProtocolVersion.v1_18_2) ? 4.0E-8D : constant;
  }

  @ModifyConstant(method = "onUpdateWalkingPlayer", constant = @Constant(intValue = 20))
  private int viaforge$fixPosUpdateTicks(int constant) {
    ProtocolVersion targetVersion = ViaLoadingBase.getInstance().getTargetVersion();
    return targetVersion.newerThanOrEqualTo(ProtocolVersion.v1_9) ? 19 : constant;
  }
}
