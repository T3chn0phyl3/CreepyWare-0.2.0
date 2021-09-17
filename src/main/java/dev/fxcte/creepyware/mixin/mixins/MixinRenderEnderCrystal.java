// 
// Decompiled by Procyon v0.5.36
// 

package dev.fxcte.creepyware.mixin.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import dev.fxcte.creepyware.util.EntityUtil;
import java.awt.Color;
import dev.fxcte.creepyware.util.RenderUtil;
import dev.fxcte.creepyware.features.modules.client.Colors;
import org.lwjgl.opengl.GL11;
import dev.fxcte.creepyware.event.events.RenderEntityModelEvent;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityEnderCrystal;
import dev.fxcte.creepyware.features.modules.render.CrystalScale;
import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelBase;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.RenderEnderCrystal;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ RenderEnderCrystal.class })
public class MixinRenderEnderCrystal
{
    @Shadow
    @Final
    private static ResourceLocation field_110787_a;
    private static ResourceLocation glint;
    
    @Redirect(method = { "doRender" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    public void renderModelBaseHook(final ModelBase model, final Entity entity, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale) {
        if (CrystalScale.INSTANCE.isEnabled()) {
            if (CrystalScale.INSTANCE.animateScale.getValue() && CrystalScale.INSTANCE.scaleMap.containsKey(entity)) {
                GlStateManager.func_179152_a((float)CrystalScale.INSTANCE.scaleMap.get(entity), (float)CrystalScale.INSTANCE.scaleMap.get(entity), (float)CrystalScale.INSTANCE.scaleMap.get(entity));
            }
            else {
                GlStateManager.func_179152_a((float)CrystalScale.INSTANCE.scale.getValue(), (float)CrystalScale.INSTANCE.scale.getValue(), (float)CrystalScale.INSTANCE.scale.getValue());
            }
        }
        if (CrystalScale.INSTANCE.isEnabled() && CrystalScale.INSTANCE.wireframe.getValue()) {
            final RenderEntityModelEvent event = new RenderEntityModelEvent(0, model, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            CrystalScale.INSTANCE.onRenderModel(event);
        }
        if (CrystalScale.INSTANCE.isEnabled() && CrystalScale.INSTANCE.chams.getValue()) {
            GL11.glPushAttrib(1048575);
            GL11.glDisable(3008);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glLineWidth(1.5f);
            GL11.glEnable(2960);
            if (CrystalScale.INSTANCE.rainbow.getValue()) {
                final Color rainbowColor1 = CrystalScale.INSTANCE.colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color(RenderUtil.getRainbow(CrystalScale.INSTANCE.speed.getValue() * 100, 0, CrystalScale.INSTANCE.saturation.getValue() / 100.0f, CrystalScale.INSTANCE.brightness.getValue() / 100.0f));
                final Color rainbowColor2 = EntityUtil.getColor(entity, rainbowColor1.getRed(), rainbowColor1.getGreen(), rainbowColor1.getBlue(), CrystalScale.INSTANCE.alpha.getValue(), true);
                if (CrystalScale.INSTANCE.throughWalls.getValue()) {
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                }
                GL11.glEnable(10754);
                GL11.glColor4f(rainbowColor2.getRed() / 255.0f, rainbowColor2.getGreen() / 255.0f, rainbowColor2.getBlue() / 255.0f, CrystalScale.INSTANCE.alpha.getValue() / 255.0f);
                model.func_78088_a(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                if (CrystalScale.INSTANCE.throughWalls.getValue()) {
                    GL11.glEnable(2929);
                    GL11.glDepthMask(true);
                }
            }
            else if (CrystalScale.INSTANCE.xqz.getValue() && CrystalScale.INSTANCE.throughWalls.getValue()) {
                final Color hiddenColor = CrystalScale.INSTANCE.colorSync.getValue() ? EntityUtil.getColor(entity, CrystalScale.INSTANCE.hiddenRed.getValue(), CrystalScale.INSTANCE.hiddenGreen.getValue(), CrystalScale.INSTANCE.hiddenBlue.getValue(), CrystalScale.INSTANCE.hiddenAlpha.getValue(), true) : EntityUtil.getColor(entity, CrystalScale.INSTANCE.hiddenRed.getValue(), CrystalScale.INSTANCE.hiddenGreen.getValue(), CrystalScale.INSTANCE.hiddenBlue.getValue(), CrystalScale.INSTANCE.hiddenAlpha.getValue(), true);
                final Color color;
                final Color visibleColor = color = (CrystalScale.INSTANCE.colorSync.getValue() ? EntityUtil.getColor(entity, CrystalScale.INSTANCE.red.getValue(), CrystalScale.INSTANCE.green.getValue(), CrystalScale.INSTANCE.blue.getValue(), CrystalScale.INSTANCE.alpha.getValue(), true) : EntityUtil.getColor(entity, CrystalScale.INSTANCE.red.getValue(), CrystalScale.INSTANCE.green.getValue(), CrystalScale.INSTANCE.blue.getValue(), CrystalScale.INSTANCE.alpha.getValue(), true));
                if (CrystalScale.INSTANCE.throughWalls.getValue()) {
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                }
                GL11.glEnable(10754);
                GL11.glColor4f(hiddenColor.getRed() / 255.0f, hiddenColor.getGreen() / 255.0f, hiddenColor.getBlue() / 255.0f, CrystalScale.INSTANCE.alpha.getValue() / 255.0f);
                model.func_78088_a(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                if (CrystalScale.INSTANCE.throughWalls.getValue()) {
                    GL11.glEnable(2929);
                    GL11.glDepthMask(true);
                }
                GL11.glColor4f(visibleColor.getRed() / 255.0f, visibleColor.getGreen() / 255.0f, visibleColor.getBlue() / 255.0f, CrystalScale.INSTANCE.alpha.getValue() / 255.0f);
                model.func_78088_a(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            }
            else {
                final Color color2;
                final Color visibleColor = color2 = (CrystalScale.INSTANCE.colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : EntityUtil.getColor(entity, CrystalScale.INSTANCE.red.getValue(), CrystalScale.INSTANCE.green.getValue(), CrystalScale.INSTANCE.blue.getValue(), CrystalScale.INSTANCE.alpha.getValue(), true));
                if (CrystalScale.INSTANCE.throughWalls.getValue()) {
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                }
                GL11.glEnable(10754);
                GL11.glColor4f(visibleColor.getRed() / 255.0f, visibleColor.getGreen() / 255.0f, visibleColor.getBlue() / 255.0f, CrystalScale.INSTANCE.alpha.getValue() / 255.0f);
                model.func_78088_a(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                if (CrystalScale.INSTANCE.throughWalls.getValue()) {
                    GL11.glEnable(2929);
                    GL11.glDepthMask(true);
                }
            }
            GL11.glEnable(3042);
            GL11.glEnable(2896);
            GL11.glEnable(3553);
            GL11.glEnable(3008);
            GL11.glPopAttrib();
            if (CrystalScale.INSTANCE.glint.getValue()) {
                GL11.glDisable(2929);
                GL11.glDepthMask(false);
                GlStateManager.func_179141_d();
                GlStateManager.func_179131_c(1.0f, 0.0f, 0.0f, 0.13f);
                model.func_78088_a(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                GlStateManager.func_179118_c();
                GL11.glEnable(2929);
                GL11.glDepthMask(true);
            }
        }
        else {
            model.func_78088_a(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
        if (CrystalScale.INSTANCE.isEnabled()) {
            if (CrystalScale.INSTANCE.animateScale.getValue() && CrystalScale.INSTANCE.scaleMap.containsKey(entity)) {
                GlStateManager.func_179152_a(1.0f / CrystalScale.INSTANCE.scaleMap.get(entity), 1.0f / CrystalScale.INSTANCE.scaleMap.get(entity), 1.0f / CrystalScale.INSTANCE.scaleMap.get(entity));
            }
            else {
                GlStateManager.func_179152_a(1.0f / CrystalScale.INSTANCE.scale.getValue(), 1.0f / CrystalScale.INSTANCE.scale.getValue(), 1.0f / CrystalScale.INSTANCE.scale.getValue());
            }
        }
    }
    
    static {
        MixinRenderEnderCrystal.glint = new ResourceLocation("textures/glint.png");
    }
}
