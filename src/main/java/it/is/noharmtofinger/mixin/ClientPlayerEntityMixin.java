package it.is.noharmtofinger.mixin;
// 导入Mixin框架的类
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Shadow;

// 使用@Mixin注解来指定要修改的目标类
@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
	@Shadow @Final
	protected MinecraftClient client;
	// 使用@Redirect注解来重定向一个方法调用
	@Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;isPressed()Z"))
	private boolean redirectSprintey(KeyBinding keyBinding) {
		// 如果调用的是疾跑键的isPressed()方法则返回相反的值
		if (keyBinding == this.client.options.sprintKey) return !keyBinding.isPressed();
		// 否则，返回原来的值
		return keyBinding.isPressed();
	}
}
