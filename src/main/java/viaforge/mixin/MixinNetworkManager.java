package viaforge.mixin;

import de.florianmichael.vialoadingbase.netty.event.CompressionReorderEvent;
import io.netty.channel.Channel;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import viaforge.ViaForge;

import java.util.concurrent.Future;

@Mixin(NetworkManager.class)
public abstract class MixinNetworkManager {
  @Shadow
  private Channel channel;

  @Inject(method = "setCompressionTreshold", at = @At("RETURN"))
  private void viaforge$reorderPipeline(int treshold, CallbackInfo ci) {
    channel.pipeline().fireUserEventTriggered(new CompressionReorderEvent());
  }

  @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetworkManager;isChannelOpen()Z"), cancellable = true)
  private void viaforge$handleSentPacket1(Packet<?> packet, CallbackInfo ci) {
    if (ViaForge.handle(packet)) {
      ci.cancel();
    }
  }

  @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;[Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetworkManager;isChannelOpen()Z"), cancellable = true)
  private void viaforge$handleSentPacket2(Packet<?> packet, GenericFutureListener<? extends Future<? super Void>> gfl1, GenericFutureListener<? extends Future<? super Void>>[] gfl2, CallbackInfo ci) {
    if (ViaForge.handle(packet)) {
      ci.cancel();
    }
  }
}
