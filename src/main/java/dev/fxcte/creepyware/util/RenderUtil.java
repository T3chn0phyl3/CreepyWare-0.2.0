// 
// Decompiled by Procyon v0.5.36
// 

package dev.fxcte.creepyware.util;

import java.util.HashMap;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.EXTFramebufferObject;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.util.math.Vec2f;
import java.util.ArrayList;
import java.util.Calendar;
import org.lwjgl.util.glu.Disk;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.glu.Sphere;
import net.minecraft.world.IBlockAccess;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderGlobal;
import dev.fxcte.creepyware.features.modules.client.Colors;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.Minecraft;
import dev.fxcte.creepyware.CreepyWare;
import dev.fxcte.creepyware.features.modules.player.Speedmine;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import java.util.Objects;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import java.awt.Color;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.RenderItem;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import net.minecraft.client.renderer.culling.Frustum;

public class RenderUtil implements Util
{
    private static final Frustum frustrum;
    private static final FloatBuffer screenCoords;
    private static final IntBuffer viewport;
    private static final FloatBuffer modelView;
    private static final FloatBuffer projection;
    public static RenderItem itemRender;
    public static ICamera camera;
    private static boolean depth;
    private static boolean texture;
    private static boolean clean;
    private static boolean bind;
    private static boolean override;
    
    public static void boxESP(final BlockPos blockPos, final Color color, final float f, final boolean bl, final boolean bl2, final int n, final boolean bl3) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.func_177958_n() - RenderUtil.mc.func_175598_ae().field_78730_l, blockPos.func_177956_o() - RenderUtil.mc.func_175598_ae().field_78731_m, blockPos.func_177952_p() - RenderUtil.mc.func_175598_ae().field_78728_n, blockPos.func_177958_n() + 1 - RenderUtil.mc.func_175598_ae().field_78730_l, blockPos.func_177956_o() + 1 - RenderUtil.mc.func_175598_ae().field_78731_m, blockPos.func_177952_p() + 1 - RenderUtil.mc.func_175598_ae().field_78728_n);
        RenderUtil.camera.func_78547_a(Objects.requireNonNull(RenderUtil.mc.func_175606_aa()).field_70165_t, RenderUtil.mc.func_175606_aa().field_70163_u, RenderUtil.mc.func_175606_aa().field_70161_v);
        if (RenderUtil.camera.func_78546_a(new AxisAlignedBB(axisAlignedBB.field_72340_a + RenderUtil.mc.func_175598_ae().field_78730_l, axisAlignedBB.field_72338_b + RenderUtil.mc.func_175598_ae().field_78731_m, axisAlignedBB.field_72339_c + RenderUtil.mc.func_175598_ae().field_78728_n, axisAlignedBB.field_72336_d + RenderUtil.mc.func_175598_ae().field_78730_l, axisAlignedBB.field_72337_e + RenderUtil.mc.func_175598_ae().field_78731_m, axisAlignedBB.field_72334_f + RenderUtil.mc.func_175598_ae().field_78728_n))) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179097_i();
            GlStateManager.func_179120_a(770, 771, 0, 1);
            GlStateManager.func_179090_x();
            GlStateManager.func_179132_a(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(f);
            final double d8 = RenderUtil.mc.field_71442_b.field_78770_f;
            float f2 = Speedmine.getInstance().timer.getPassedTimeMs() / 1000.0f / (Speedmine.getInstance().breakTime * CreepyWare.serverManager.getTpsFactor());
            f2 = Math.min(f2, 1.0f);
            double d9;
            double d10;
            double d11;
            double d12;
            double d13;
            double d14;
            if (bl3) {
                d9 = axisAlignedBB.field_72340_a + 0.5 - f2 / 2.0f;
                d10 = axisAlignedBB.field_72338_b + 0.5 - f2 / 2.0f;
                d11 = axisAlignedBB.field_72339_c + 0.5 - f2 / 2.0f;
                d12 = axisAlignedBB.field_72336_d - 0.5 + f2 / 2.0f;
                d13 = axisAlignedBB.field_72337_e - 0.5 + f2 / 2.0f;
                d14 = axisAlignedBB.field_72334_f - 0.5 + f2 / 2.0f;
            }
            else {
                d9 = axisAlignedBB.field_72340_a + 0.5 - d8 / 2.0;
                d10 = axisAlignedBB.field_72338_b + 0.5 - d8 / 2.0;
                d11 = axisAlignedBB.field_72339_c + 0.5 - d8 / 2.0;
                d12 = axisAlignedBB.field_72336_d - 0.5 + d8 / 2.0;
                d13 = axisAlignedBB.field_72337_e - 0.5 + d8 / 2.0;
                d14 = axisAlignedBB.field_72334_f - 0.5 + d8 / 2.0;
            }
            final AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(d9, d10, d11, d12, d13, d14);
            if (bl2) {
                drawFilledBox(axisAlignedBB2, new Color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n / 255.0f).getRGB());
            }
            if (bl) {
                drawBlockOutline(axisAlignedBB2, new Color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, 1.0f), Speedmine.getInstance().lineWidth.getValue());
            }
            GL11.glDisable(2848);
            GlStateManager.func_179132_a(true);
            GlStateManager.func_179126_j();
            GlStateManager.func_179098_w();
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
        }
    }
    
    public static void updateModelViewProjectionMatrix() {
        GL11.glGetFloat(2982, RenderUtil.modelView);
        GL11.glGetFloat(2983, RenderUtil.projection);
        GL11.glGetInteger(2978, RenderUtil.viewport);
        final ScaledResolution res = new ScaledResolution(Minecraft.func_71410_x());
        GLUProjection.getInstance().updateMatrices(RenderUtil.viewport, RenderUtil.modelView, RenderUtil.projection, res.func_78326_a() / (float)Minecraft.func_71410_x().field_71443_c, res.func_78328_b() / (float)Minecraft.func_71410_x().field_71440_d);
    }
    
    public static void drawRectangleCorrectly(final int x, final int y, final int w, final int h, final int color) {
        GL11.glLineWidth(1.0f);
        Gui.func_73734_a(x, y, x + w, y + h, color);
    }
    
    public static void drawWaypointImage(final BlockPos pos, final GLUProjection.Projection projection, final Color color, final String name, final boolean rectangle, final Color rectangleColor) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b(projection.getX(), projection.getY(), 0.0);
        final String text = name + ": " + Math.round(RenderUtil.mc.field_71439_g.func_70011_f((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p())) + "M";
        final float textWidth = (float)CreepyWare.textManager.getStringWidth(text);
        CreepyWare.textManager.drawString(text, -(textWidth / 2.0f), -(CreepyWare.textManager.getFontHeight() / 2.0f) + CreepyWare.textManager.getFontHeight() / 2.0f, color.getRGB(), false);
        GlStateManager.func_179137_b(-projection.getX(), -projection.getY(), 0.0);
        GlStateManager.func_179121_F();
    }
    
    public static AxisAlignedBB interpolateAxis(final AxisAlignedBB bb) {
        return new AxisAlignedBB(bb.field_72340_a - RenderUtil.mc.func_175598_ae().field_78730_l, bb.field_72338_b - RenderUtil.mc.func_175598_ae().field_78731_m, bb.field_72339_c - RenderUtil.mc.func_175598_ae().field_78728_n, bb.field_72336_d - RenderUtil.mc.func_175598_ae().field_78730_l, bb.field_72337_e - RenderUtil.mc.func_175598_ae().field_78731_m, bb.field_72334_f - RenderUtil.mc.func_175598_ae().field_78728_n);
    }
    
    public static void drawTexturedRect(final int x, final int y, final int textureX, final int textureY, final int width, final int height, final int zLevel) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder BufferBuilder2 = tessellator.func_178180_c();
        BufferBuilder2.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        BufferBuilder2.func_181662_b((double)x, (double)(y + height), (double)zLevel).func_187315_a((double)(textureX * 0.00390625f), (double)((textureY + height) * 0.00390625f)).func_181675_d();
        BufferBuilder2.func_181662_b((double)(x + width), (double)(y + height), (double)zLevel).func_187315_a((double)((textureX + width) * 0.00390625f), (double)((textureY + height) * 0.00390625f)).func_181675_d();
        BufferBuilder2.func_181662_b((double)(x + width), (double)y, (double)zLevel).func_187315_a((double)((textureX + width) * 0.00390625f), (double)(textureY * 0.00390625f)).func_181675_d();
        BufferBuilder2.func_181662_b((double)x, (double)y, (double)zLevel).func_187315_a((double)(textureX * 0.00390625f), (double)(textureY * 0.00390625f)).func_181675_d();
        tessellator.func_78381_a();
    }
    
    public static void drawOpenGradientBox(final BlockPos pos, final Color startColor, final Color endColor, final double height) {
        for (final EnumFacing face : EnumFacing.values()) {
            if (face != EnumFacing.UP) {
                drawGradientPlane(pos, face, startColor, endColor, height);
            }
        }
    }
    
    public static void drawClosedGradientBox(final BlockPos pos, final Color startColor, final Color endColor, final double height) {
        for (final EnumFacing face : EnumFacing.values()) {
            drawGradientPlane(pos, face, startColor, endColor, height);
        }
    }
    
    public static void drawTricolorGradientBox(final BlockPos pos, final Color startColor, final Color midColor, final Color endColor) {
        for (final EnumFacing face : EnumFacing.values()) {
            if (face != EnumFacing.UP) {
                drawGradientPlane(pos, face, startColor, midColor, true, false);
            }
        }
        for (final EnumFacing face : EnumFacing.values()) {
            if (face != EnumFacing.DOWN) {
                drawGradientPlane(pos, face, midColor, endColor, true, true);
            }
        }
    }
    
    public static void drawGradientPlane(final BlockPos pos, final EnumFacing face, final Color startColor, final Color endColor, final boolean half, final boolean top) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder builder = tessellator.func_178180_c();
        final IBlockState iblockstate = RenderUtil.mc.field_71441_e.func_180495_p(pos);
        final Vec3d interp = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.field_71439_g, RenderUtil.mc.func_184121_ak());
        final AxisAlignedBB bb = iblockstate.func_185918_c((World)RenderUtil.mc.field_71441_e, pos).func_186662_g(0.0020000000949949026).func_72317_d(-interp.field_72450_a, -interp.field_72448_b, -interp.field_72449_c);
        final float red = startColor.getRed() / 255.0f;
        final float green = startColor.getGreen() / 255.0f;
        final float blue = startColor.getBlue() / 255.0f;
        final float alpha = startColor.getAlpha() / 255.0f;
        final float red2 = endColor.getRed() / 255.0f;
        final float green2 = endColor.getGreen() / 255.0f;
        final float blue2 = endColor.getBlue() / 255.0f;
        final float alpha2 = endColor.getAlpha() / 255.0f;
        double x1 = 0.0;
        double y1 = 0.0;
        double z1 = 0.0;
        double x2 = 0.0;
        double y2 = 0.0;
        double z2 = 0.0;
        if (face == EnumFacing.DOWN) {
            x1 = bb.field_72340_a;
            x2 = bb.field_72336_d;
            y1 = bb.field_72338_b + (top ? 0.5 : 0.0);
            y2 = bb.field_72338_b + (top ? 0.5 : 0.0);
            z1 = bb.field_72339_c;
            z2 = bb.field_72334_f;
        }
        else if (face == EnumFacing.UP) {
            x1 = bb.field_72340_a;
            x2 = bb.field_72336_d;
            y1 = bb.field_72337_e / (half ? 2 : 1);
            y2 = bb.field_72337_e / (half ? 2 : 1);
            z1 = bb.field_72339_c;
            z2 = bb.field_72334_f;
        }
        else if (face == EnumFacing.EAST) {
            x1 = bb.field_72336_d;
            x2 = bb.field_72336_d;
            y1 = bb.field_72338_b + (top ? 0.5 : 0.0);
            y2 = bb.field_72337_e / (half ? 2 : 1);
            z1 = bb.field_72339_c;
            z2 = bb.field_72334_f;
        }
        else if (face == EnumFacing.WEST) {
            x1 = bb.field_72340_a;
            x2 = bb.field_72340_a;
            y1 = bb.field_72338_b + (top ? 0.5 : 0.0);
            y2 = bb.field_72337_e / (half ? 2 : 1);
            z1 = bb.field_72339_c;
            z2 = bb.field_72334_f;
        }
        else if (face == EnumFacing.SOUTH) {
            x1 = bb.field_72340_a;
            x2 = bb.field_72336_d;
            y1 = bb.field_72338_b + (top ? 0.5 : 0.0);
            y2 = bb.field_72337_e / (half ? 2 : 1);
            z1 = bb.field_72334_f;
            z2 = bb.field_72334_f;
        }
        else if (face == EnumFacing.NORTH) {
            x1 = bb.field_72340_a;
            x2 = bb.field_72336_d;
            y1 = bb.field_72338_b + (top ? 0.5 : 0.0);
            y2 = bb.field_72337_e / (half ? 2 : 1);
            z1 = bb.field_72339_c;
            z2 = bb.field_72339_c;
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179097_i();
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_179132_a(false);
        builder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
        if (face == EnumFacing.EAST || face == EnumFacing.WEST || face == EnumFacing.NORTH || face == EnumFacing.SOUTH) {
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        }
        else if (face == EnumFacing.UP) {
            builder.func_181662_b(x1, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        }
        else if (face == EnumFacing.DOWN) {
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        }
        tessellator.func_78381_a();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
        GlStateManager.func_179121_F();
    }
    
    public static void drawGradientPlane(final BlockPos pos, final EnumFacing face, final Color startColor, final Color endColor, final double height) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder builder = tessellator.func_178180_c();
        final IBlockState iblockstate = RenderUtil.mc.field_71441_e.func_180495_p(pos);
        final Vec3d interp = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.field_71439_g, RenderUtil.mc.func_184121_ak());
        final AxisAlignedBB bb = iblockstate.func_185918_c((World)RenderUtil.mc.field_71441_e, pos).func_186662_g(0.0020000000949949026).func_72317_d(-interp.field_72450_a, -interp.field_72448_b, -interp.field_72449_c).func_72321_a(0.0, height, 0.0);
        final float red = startColor.getRed() / 255.0f;
        final float green = startColor.getGreen() / 255.0f;
        final float blue = startColor.getBlue() / 255.0f;
        final float alpha = startColor.getAlpha() / 255.0f;
        final float red2 = endColor.getRed() / 255.0f;
        final float green2 = endColor.getGreen() / 255.0f;
        final float blue2 = endColor.getBlue() / 255.0f;
        final float alpha2 = endColor.getAlpha() / 255.0f;
        double x1 = 0.0;
        double y1 = 0.0;
        double z1 = 0.0;
        double x2 = 0.0;
        double y2 = 0.0;
        double z2 = 0.0;
        if (face == EnumFacing.DOWN) {
            x1 = bb.field_72340_a;
            x2 = bb.field_72336_d;
            y1 = bb.field_72338_b;
            y2 = bb.field_72338_b;
            z1 = bb.field_72339_c;
            z2 = bb.field_72334_f;
        }
        else if (face == EnumFacing.UP) {
            x1 = bb.field_72340_a;
            x2 = bb.field_72336_d;
            y1 = bb.field_72337_e;
            y2 = bb.field_72337_e;
            z1 = bb.field_72339_c;
            z2 = bb.field_72334_f;
        }
        else if (face == EnumFacing.EAST) {
            x1 = bb.field_72336_d;
            x2 = bb.field_72336_d;
            y1 = bb.field_72338_b;
            y2 = bb.field_72337_e;
            z1 = bb.field_72339_c;
            z2 = bb.field_72334_f;
        }
        else if (face == EnumFacing.WEST) {
            x1 = bb.field_72340_a;
            x2 = bb.field_72340_a;
            y1 = bb.field_72338_b;
            y2 = bb.field_72337_e;
            z1 = bb.field_72339_c;
            z2 = bb.field_72334_f;
        }
        else if (face == EnumFacing.SOUTH) {
            x1 = bb.field_72340_a;
            x2 = bb.field_72336_d;
            y1 = bb.field_72338_b;
            y2 = bb.field_72337_e;
            z1 = bb.field_72334_f;
            z2 = bb.field_72334_f;
        }
        else if (face == EnumFacing.NORTH) {
            x1 = bb.field_72340_a;
            x2 = bb.field_72336_d;
            y1 = bb.field_72338_b;
            y2 = bb.field_72337_e;
            z1 = bb.field_72339_c;
            z2 = bb.field_72339_c;
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179097_i();
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_179132_a(false);
        builder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
        if (face == EnumFacing.EAST || face == EnumFacing.WEST || face == EnumFacing.NORTH || face == EnumFacing.SOUTH) {
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        }
        else if (face == EnumFacing.UP) {
            builder.func_181662_b(x1, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        }
        else if (face == EnumFacing.DOWN) {
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
            builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
        }
        tessellator.func_78381_a();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
        GlStateManager.func_179121_F();
    }
    
    public static void drawGradientRect(final int x, final int y, final int w, final int h, final int startColor, final int endColor) {
        final float f = (startColor >> 24 & 0xFF) / 255.0f;
        final float f2 = (startColor >> 16 & 0xFF) / 255.0f;
        final float f3 = (startColor >> 8 & 0xFF) / 255.0f;
        final float f4 = (startColor & 0xFF) / 255.0f;
        final float f5 = (endColor >> 24 & 0xFF) / 255.0f;
        final float f6 = (endColor >> 16 & 0xFF) / 255.0f;
        final float f7 = (endColor >> 8 & 0xFF) / 255.0f;
        final float f8 = (endColor & 0xFF) / 255.0f;
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179103_j(7425);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder vertexbuffer = tessellator.func_178180_c();
        vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        vertexbuffer.func_181662_b(x + (double)w, (double)y, 0.0).func_181666_a(f2, f3, f4, f).func_181675_d();
        vertexbuffer.func_181662_b((double)x, (double)y, 0.0).func_181666_a(f2, f3, f4, f).func_181675_d();
        vertexbuffer.func_181662_b((double)x, y + (double)h, 0.0).func_181666_a(f6, f7, f8, f5).func_181675_d();
        vertexbuffer.func_181662_b(x + (double)w, y + (double)h, 0.0).func_181666_a(f6, f7, f8, f5).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179103_j(7424);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
    }
    
    public static void drawBoundingBoxBottomBlockPosXInMiddle2(final BlockPos bp, final float width, final int r, final int g, final int b, final int alpha) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(width);
        final Minecraft mc = Minecraft.func_71410_x();
        final double x = bp.func_177958_n() - mc.func_175598_ae().field_78730_l;
        final double y = bp.func_177956_o() - mc.func_175598_ae().field_78731_m;
        final double z = bp.func_177952_p() - mc.func_175598_ae().field_78728_n;
        final AxisAlignedBB bb = new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181669_b(r, g, b, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181669_b(r, g, b, alpha).func_181675_d();
        tessellator.func_78381_a();
        GL11.glDisable(2848);
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }
    
    public static void drawBoundingBoxBottomBlockPosXInMiddle(final BlockPos bp, final float width, final int r, final int g, final int b, final int alpha) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(width);
        final Minecraft mc = Minecraft.func_71410_x();
        final double x = bp.func_177958_n() - mc.func_175598_ae().field_78730_l;
        final double y = bp.func_177956_o() - mc.func_175598_ae().field_78731_m;
        final double z = bp.func_177952_p() - mc.func_175598_ae().field_78728_n;
        final AxisAlignedBB bb = new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181669_b(r, g, b, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181669_b(r, g, b, alpha).func_181675_d();
        tessellator.func_78381_a();
        GL11.glDisable(2848);
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }
    
    public static void drawGradientBlockOutline(final BlockPos pos, final Color startColor, final Color endColor, final float linewidth, final double height) {
        final IBlockState iblockstate = RenderUtil.mc.field_71441_e.func_180495_p(pos);
        final Vec3d interp = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.field_71439_g, RenderUtil.mc.func_184121_ak());
        drawGradientBlockOutline(iblockstate.func_185918_c((World)RenderUtil.mc.field_71441_e, pos).func_186662_g(0.0020000000949949026).func_72317_d(-interp.field_72450_a, -interp.field_72448_b, -interp.field_72449_c).func_72321_a(0.0, height, 0.0), startColor, endColor, linewidth);
    }
    
    public static void drawProperGradientBlockOutline(final BlockPos pos, final Color startColor, final Color midColor, final Color endColor, final float linewidth) {
        final IBlockState iblockstate = RenderUtil.mc.field_71441_e.func_180495_p(pos);
        final Vec3d interp = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.field_71439_g, RenderUtil.mc.func_184121_ak());
        drawProperGradientBlockOutline(iblockstate.func_185918_c((World)RenderUtil.mc.field_71441_e, pos).func_186662_g(0.0020000000949949026).func_72317_d(-interp.field_72450_a, -interp.field_72448_b, -interp.field_72449_c), startColor, midColor, endColor, linewidth);
    }
    
    public static void drawProperGradientBlockOutline(final AxisAlignedBB bb, final Color startColor, final Color midColor, final Color endColor, final float linewidth) {
        final float red = endColor.getRed() / 255.0f;
        final float green = endColor.getGreen() / 255.0f;
        final float blue = endColor.getBlue() / 255.0f;
        final float alpha = endColor.getAlpha() / 255.0f;
        final float red2 = midColor.getRed() / 255.0f;
        final float green2 = midColor.getGreen() / 255.0f;
        final float blue2 = midColor.getBlue() / 255.0f;
        final float alpha2 = midColor.getAlpha() / 255.0f;
        final float red3 = startColor.getRed() / 255.0f;
        final float green3 = startColor.getGreen() / 255.0f;
        final float blue3 = startColor.getBlue() / 255.0f;
        final float alpha3 = startColor.getAlpha() / 255.0f;
        final double dif = (bb.field_72337_e - bb.field_72338_b) / 2.0;
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(linewidth);
        GL11.glBegin(1);
        GL11.glColor4d((double)red, (double)green, (double)blue, (double)alpha);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glColor4d((double)red2, (double)green2, (double)blue2, (double)alpha2);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b + dif, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b + dif, bb.field_72339_c);
        GL11.glColor4f(red3, green3, blue3, alpha3);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glColor4d((double)red, (double)green, (double)blue, (double)alpha);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glColor4d((double)red2, (double)green2, (double)blue2, (double)alpha2);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b + dif, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b + dif, bb.field_72334_f);
        GL11.glColor4d((double)red3, (double)green3, (double)blue3, (double)alpha3);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glColor4d((double)red, (double)green, (double)blue, (double)alpha);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glColor4d((double)red2, (double)green2, (double)blue2, (double)alpha2);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b + dif, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b + dif, bb.field_72334_f);
        GL11.glColor4d((double)red3, (double)green3, (double)blue3, (double)alpha3);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glColor4d((double)red, (double)green, (double)blue, (double)alpha);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glColor4d((double)red2, (double)green2, (double)blue2, (double)alpha2);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b + dif, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b + dif, bb.field_72339_c);
        GL11.glColor4d((double)red3, (double)green3, (double)blue3, (double)alpha3);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glColor4d((double)red3, (double)green3, (double)blue3, (double)alpha3);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glEnd();
        GL11.glDisable(2848);
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }
    
    public static void drawGradientBlockOutline(final AxisAlignedBB bb, final Color startColor, final Color endColor, final float linewidth) {
        final float red = startColor.getRed() / 255.0f;
        final float green = startColor.getGreen() / 255.0f;
        final float blue = startColor.getBlue() / 255.0f;
        final float alpha = startColor.getAlpha() / 255.0f;
        final float red2 = endColor.getRed() / 255.0f;
        final float green2 = endColor.getGreen() / 255.0f;
        final float blue2 = endColor.getBlue() / 255.0f;
        final float alpha2 = endColor.getAlpha() / 255.0f;
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(linewidth);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        tessellator.func_78381_a();
        GL11.glDisable(2848);
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }
    
    public static void drawGradientFilledBox(final BlockPos pos, final Color startColor, final Color endColor) {
        final IBlockState iblockstate = RenderUtil.mc.field_71441_e.func_180495_p(pos);
        final Vec3d interp = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.field_71439_g, RenderUtil.mc.func_184121_ak());
        drawGradientFilledBox(iblockstate.func_185918_c((World)RenderUtil.mc.field_71441_e, pos).func_186662_g(0.0020000000949949026).func_72317_d(-interp.field_72450_a, -interp.field_72448_b, -interp.field_72449_c), startColor, endColor);
    }
    
    public static void drawGradientFilledBox(final AxisAlignedBB bb, final Color startColor, final Color endColor) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        final float alpha = endColor.getAlpha() / 255.0f;
        final float red = endColor.getRed() / 255.0f;
        final float green = endColor.getGreen() / 255.0f;
        final float blue = endColor.getBlue() / 255.0f;
        final float alpha2 = startColor.getAlpha() / 255.0f;
        final float red2 = startColor.getRed() / 255.0f;
        final float green2 = startColor.getGreen() / 255.0f;
        final float blue2 = startColor.getBlue() / 255.0f;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }
    
    public static void drawGradientRect(final float x, final float y, final float w, final float h, final int startColor, final int endColor) {
        final float f = (startColor >> 24 & 0xFF) / 255.0f;
        final float f2 = (startColor >> 16 & 0xFF) / 255.0f;
        final float f3 = (startColor >> 8 & 0xFF) / 255.0f;
        final float f4 = (startColor & 0xFF) / 255.0f;
        final float f5 = (endColor >> 24 & 0xFF) / 255.0f;
        final float f6 = (endColor >> 16 & 0xFF) / 255.0f;
        final float f7 = (endColor >> 8 & 0xFF) / 255.0f;
        final float f8 = (endColor & 0xFF) / 255.0f;
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179103_j(7425);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder vertexbuffer = tessellator.func_178180_c();
        vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        vertexbuffer.func_181662_b(x + (double)w, (double)y, 0.0).func_181666_a(f2, f3, f4, f).func_181675_d();
        vertexbuffer.func_181662_b((double)x, (double)y, 0.0).func_181666_a(f2, f3, f4, f).func_181675_d();
        vertexbuffer.func_181662_b((double)x, y + (double)h, 0.0).func_181666_a(f6, f7, f8, f5).func_181675_d();
        vertexbuffer.func_181662_b(x + (double)w, y + (double)h, 0.0).func_181666_a(f6, f7, f8, f5).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179103_j(7424);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
    }
    
    public static void drawFilledCircle(final double x, final double y, final double z, final Color color, final double radius) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder builder = tessellator.func_178180_c();
        builder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
    }
    
    public static void drawGradientBoxTest(final BlockPos pos, final Color startColor, final Color endColor) {
    }
    
    public static void blockESP(final BlockPos b, final Color c, final double length, final double length2) {
        blockEsp(b, c, length, length2);
    }
    
    public static void drawBoxESP(final BlockPos pos, final Color color, final boolean secondC, final Color secondColor, final float lineWidth, final boolean outline, final boolean box, final int boxAlpha, final boolean air) {
        if (box) {
            drawBox(pos, new Color(color.getRed(), color.getGreen(), color.getBlue(), boxAlpha));
        }
        if (outline) {
            drawBlockOutline(pos, secondC ? secondColor : color, lineWidth, air);
        }
    }
    
    public static void drawSexyBoxPhobosIsRetardedFuckYouESP(final AxisAlignedBB a, final Color boxColor, final Color outlineColor, final float lineWidth, final boolean outline, final boolean box, final boolean colorSync, float alpha, final float scale, final float slab) {
        final double f = 0.5 * (1.0f - scale);
        final AxisAlignedBB bb = interpolateAxis(new AxisAlignedBB(a.field_72340_a + f, a.field_72338_b + f + (1.0f - slab), a.field_72339_c + f, a.field_72336_d - f, a.field_72337_e - f, a.field_72334_f - f));
        float rB = boxColor.getRed() / 255.0f;
        float gB = boxColor.getGreen() / 255.0f;
        float bB = boxColor.getBlue() / 255.0f;
        float aB = boxColor.getAlpha() / 255.0f;
        float rO = outlineColor.getRed() / 255.0f;
        float gO = outlineColor.getGreen() / 255.0f;
        float bO = outlineColor.getBlue() / 255.0f;
        float aO = outlineColor.getAlpha() / 255.0f;
        if (colorSync) {
            rB = Colors.INSTANCE.getCurrentColor().getRed() / 255.0f;
            gB = Colors.INSTANCE.getCurrentColor().getGreen() / 255.0f;
            bB = Colors.INSTANCE.getCurrentColor().getBlue() / 255.0f;
            rO = Colors.INSTANCE.getCurrentColor().getRed() / 255.0f;
            gO = Colors.INSTANCE.getCurrentColor().getGreen() / 255.0f;
            bO = Colors.INSTANCE.getCurrentColor().getBlue() / 255.0f;
        }
        if (alpha > 1.0f) {
            alpha = 1.0f;
        }
        aB *= alpha;
        aO *= alpha;
        if (box) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179097_i();
            GlStateManager.func_179120_a(770, 771, 0, 1);
            GlStateManager.func_179090_x();
            GlStateManager.func_179132_a(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            RenderGlobal.func_189696_b(bb, rB, gB, bB, aB);
            GL11.glDisable(2848);
            GlStateManager.func_179132_a(true);
            GlStateManager.func_179126_j();
            GlStateManager.func_179098_w();
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
        }
        if (outline) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179097_i();
            GlStateManager.func_179120_a(770, 771, 0, 1);
            GlStateManager.func_179090_x();
            GlStateManager.func_179132_a(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(lineWidth);
            final Tessellator tessellator = Tessellator.func_178181_a();
            final BufferBuilder bufferbuilder = tessellator.func_178180_c();
            bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
            tessellator.func_78381_a();
            GL11.glDisable(2848);
            GlStateManager.func_179132_a(true);
            GlStateManager.func_179126_j();
            GlStateManager.func_179098_w();
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
        }
    }
    
    public static void drawBoxESP(final BlockPos pos, final Color color, final boolean secondC, final Color secondColor, final float lineWidth, final boolean outline, final boolean box, final int boxAlpha, final boolean air, final double height, final boolean gradientBox, final boolean gradientOutline, final boolean invertGradientBox, final boolean invertGradientOutline, final int gradientAlpha) {
        if (box) {
            drawBox(pos, new Color(color.getRed(), color.getGreen(), color.getBlue(), boxAlpha), height, gradientBox, invertGradientBox, gradientAlpha);
        }
        if (outline) {
            drawBlockOutline(pos, secondC ? secondColor : color, lineWidth, air, height, gradientOutline, invertGradientOutline, gradientAlpha);
        }
    }
    
    public static void glScissor(final float x, final float y, final float x1, final float y1, final ScaledResolution sr) {
        GL11.glScissor((int)(x * sr.func_78325_e()), (int)(RenderUtil.mc.field_71440_d - y1 * sr.func_78325_e()), (int)((x1 - x) * sr.func_78325_e()), (int)((y1 - y) * sr.func_78325_e()));
    }
    
    public static void drawLine(final float x, final float y, final float x1, final float y1, final float thickness, final int hex) {
        final float red = (hex >> 16 & 0xFF) / 255.0f;
        final float green = (hex >> 8 & 0xFF) / 255.0f;
        final float blue = (hex & 0xFF) / 255.0f;
        final float alpha = (hex >> 24 & 0xFF) / 255.0f;
        GlStateManager.func_179094_E();
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        GlStateManager.func_179103_j(7425);
        GL11.glLineWidth(thickness);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b((double)x, (double)y, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)x1, (double)y1, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179103_j(7424);
        GL11.glDisable(2848);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
        GlStateManager.func_179121_F();
    }
    
    public static void drawBox(final BlockPos pos, final Color color) {
        final AxisAlignedBB bb = new AxisAlignedBB(pos.func_177958_n() - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() - RenderUtil.mc.func_175598_ae().field_78731_m, pos.func_177952_p() - RenderUtil.mc.func_175598_ae().field_78728_n, pos.func_177958_n() + 1 - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() + 1 - RenderUtil.mc.func_175598_ae().field_78731_m, pos.func_177952_p() + 1 - RenderUtil.mc.func_175598_ae().field_78728_n);
        RenderUtil.camera.func_78547_a(Objects.requireNonNull(RenderUtil.mc.func_175606_aa()).field_70165_t, RenderUtil.mc.func_175606_aa().field_70163_u, RenderUtil.mc.func_175606_aa().field_70161_v);
        if (RenderUtil.camera.func_78546_a(new AxisAlignedBB(bb.field_72340_a + RenderUtil.mc.func_175598_ae().field_78730_l, bb.field_72338_b + RenderUtil.mc.func_175598_ae().field_78731_m, bb.field_72339_c + RenderUtil.mc.func_175598_ae().field_78728_n, bb.field_72336_d + RenderUtil.mc.func_175598_ae().field_78730_l, bb.field_72337_e + RenderUtil.mc.func_175598_ae().field_78731_m, bb.field_72334_f + RenderUtil.mc.func_175598_ae().field_78728_n))) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179097_i();
            GlStateManager.func_179120_a(770, 771, 0, 1);
            GlStateManager.func_179090_x();
            GlStateManager.func_179132_a(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            RenderGlobal.func_189696_b(bb, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glDisable(2848);
            GlStateManager.func_179132_a(true);
            GlStateManager.func_179126_j();
            GlStateManager.func_179098_w();
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
        }
    }
    
    public static void drawBetterGradientBox(final BlockPos pos, final Color startColor, final Color endColor) {
        final float red = startColor.getRed() / 255.0f;
        final float green = startColor.getGreen() / 255.0f;
        final float blue = startColor.getBlue() / 255.0f;
        final float alpha = startColor.getAlpha() / 255.0f;
        final float red2 = endColor.getRed() / 255.0f;
        final float green2 = endColor.getGreen() / 255.0f;
        final float blue2 = endColor.getBlue() / 255.0f;
        final float alpha2 = endColor.getAlpha() / 255.0f;
        final AxisAlignedBB bb = new AxisAlignedBB(pos.func_177958_n() - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() - RenderUtil.mc.func_175598_ae().field_78731_m, pos.func_177952_p() - RenderUtil.mc.func_175598_ae().field_78728_n, pos.func_177958_n() + 1 - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() + 1 - RenderUtil.mc.func_175598_ae().field_78731_m, pos.func_177952_p() + 1 - RenderUtil.mc.func_175598_ae().field_78728_n);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder builder = tessellator.func_178180_c();
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        builder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
    }
    
    public static void drawBetterGradientBox(final BlockPos pos, final Color startColor, final Color midColor, final Color endColor) {
        final float red = startColor.getRed() / 255.0f;
        final float green = startColor.getGreen() / 255.0f;
        final float blue = startColor.getBlue() / 255.0f;
        final float alpha = startColor.getAlpha() / 255.0f;
        final float red2 = endColor.getRed() / 255.0f;
        final float green2 = endColor.getGreen() / 255.0f;
        final float blue2 = endColor.getBlue() / 255.0f;
        final float alpha2 = endColor.getAlpha() / 255.0f;
        final float red3 = midColor.getRed() / 255.0f;
        final float green3 = midColor.getGreen() / 255.0f;
        final float blue3 = midColor.getBlue() / 255.0f;
        final float alpha3 = midColor.getAlpha() / 255.0f;
        final AxisAlignedBB bb = new AxisAlignedBB(pos.func_177958_n() - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() - RenderUtil.mc.func_175598_ae().field_78731_m, pos.func_177952_p() - RenderUtil.mc.func_175598_ae().field_78728_n, pos.func_177958_n() + 1 - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() + 1 - RenderUtil.mc.func_175598_ae().field_78731_m, pos.func_177952_p() + 1 - RenderUtil.mc.func_175598_ae().field_78728_n);
        final double offset = (bb.field_72337_e - bb.field_72338_b) / 2.0;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder builder = tessellator.func_178180_c();
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        builder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72339_c).func_181666_a(red3, green3, blue3, alpha3).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72334_f).func_181666_a(red3, green3, blue3, alpha3).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72334_f).func_181666_a(red3, green3, blue3, alpha3).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b + offset, bb.field_72334_f).func_181666_a(red3, green3, blue3, alpha3).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72339_c).func_181666_a(red3, green3, blue3, alpha3).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72339_c).func_181666_a(red3, green3, blue3, alpha3).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72339_c).func_181666_a(red3, green3, blue3, alpha3).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72334_f).func_181666_a(red3, green3, blue3, alpha3).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72334_f).func_181666_a(red3, green3, blue3, alpha3).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b + offset, bb.field_72334_f).func_181666_a(red3, green3, blue3, alpha3).func_181675_d();
        tessellator.func_78381_a();
        GL11.glDisable(2848);
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }
    
    public static void drawEvenBetterGradientBox(final BlockPos pos, final Color startColor, final Color midColor, final Color endColor) {
        final float red = startColor.getRed() / 255.0f;
        final float green = startColor.getGreen() / 255.0f;
        final float blue = startColor.getBlue() / 255.0f;
        final float alpha = startColor.getAlpha() / 255.0f;
        final float red2 = endColor.getRed() / 255.0f;
        final float green2 = endColor.getGreen() / 255.0f;
        final float blue2 = endColor.getBlue() / 255.0f;
        final float alpha2 = endColor.getAlpha() / 255.0f;
        final float red3 = midColor.getRed() / 255.0f;
        final float green3 = midColor.getGreen() / 255.0f;
        final float blue3 = midColor.getBlue() / 255.0f;
        final float alpha3 = midColor.getAlpha() / 255.0f;
        final AxisAlignedBB bb = new AxisAlignedBB(pos.func_177958_n() - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() - RenderUtil.mc.func_175598_ae().field_78731_m, pos.func_177952_p() - RenderUtil.mc.func_175598_ae().field_78728_n, pos.func_177958_n() + 1 - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() + 1 - RenderUtil.mc.func_175598_ae().field_78731_m, pos.func_177952_p() + 1 - RenderUtil.mc.func_175598_ae().field_78728_n);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder builder = tessellator.func_178180_c();
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        builder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        tessellator.func_78381_a();
        GL11.glDisable(2848);
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }
    
    public static void drawBox(final BlockPos pos, final Color color, final double height, final boolean gradient, final boolean invert, final int alpha) {
        if (gradient) {
            final Color endColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
            drawOpenGradientBox(pos, invert ? endColor : color, invert ? color : endColor, height);
            return;
        }
        final AxisAlignedBB bb = new AxisAlignedBB(pos.func_177958_n() - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() - RenderUtil.mc.func_175598_ae().field_78731_m, pos.func_177952_p() - RenderUtil.mc.func_175598_ae().field_78728_n, pos.func_177958_n() + 1 - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() + 1 - RenderUtil.mc.func_175598_ae().field_78731_m + height, pos.func_177952_p() + 1 - RenderUtil.mc.func_175598_ae().field_78728_n);
        RenderUtil.camera.func_78547_a(Objects.requireNonNull(RenderUtil.mc.func_175606_aa()).field_70165_t, RenderUtil.mc.func_175606_aa().field_70163_u, RenderUtil.mc.func_175606_aa().field_70161_v);
        if (RenderUtil.camera.func_78546_a(new AxisAlignedBB(bb.field_72340_a + RenderUtil.mc.func_175598_ae().field_78730_l, bb.field_72338_b + RenderUtil.mc.func_175598_ae().field_78731_m, bb.field_72339_c + RenderUtil.mc.func_175598_ae().field_78728_n, bb.field_72336_d + RenderUtil.mc.func_175598_ae().field_78730_l, bb.field_72337_e + RenderUtil.mc.func_175598_ae().field_78731_m, bb.field_72334_f + RenderUtil.mc.func_175598_ae().field_78728_n))) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179097_i();
            GlStateManager.func_179120_a(770, 771, 0, 1);
            GlStateManager.func_179090_x();
            GlStateManager.func_179132_a(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            RenderGlobal.func_189696_b(bb, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glDisable(2848);
            GlStateManager.func_179132_a(true);
            GlStateManager.func_179126_j();
            GlStateManager.func_179098_w();
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
        }
    }
    
    public static void drawBlockOutline(final BlockPos pos, final Color color, final float linewidth, final boolean air) {
        final IBlockState iblockstate = RenderUtil.mc.field_71441_e.func_180495_p(pos);
        if ((air || iblockstate.func_185904_a() != Material.field_151579_a) && RenderUtil.mc.field_71441_e.func_175723_af().func_177746_a(pos)) {
            final Vec3d interp = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.field_71439_g, RenderUtil.mc.func_184121_ak());
            drawBlockOutline(iblockstate.func_185918_c((World)RenderUtil.mc.field_71441_e, pos).func_186662_g(0.0020000000949949026).func_72317_d(-interp.field_72450_a, -interp.field_72448_b, -interp.field_72449_c), color, linewidth);
        }
    }
    
    public static void drawBlockOutline(final BlockPos pos, final Color color, final float linewidth, final boolean air, final double height, final boolean gradient, final boolean invert, final int alpha) {
        if (gradient) {
            final Color endColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
            drawGradientBlockOutline(pos, invert ? endColor : color, invert ? color : endColor, linewidth, height);
            return;
        }
        final IBlockState iblockstate = RenderUtil.mc.field_71441_e.func_180495_p(pos);
        if ((air || iblockstate.func_185904_a() != Material.field_151579_a) && RenderUtil.mc.field_71441_e.func_175723_af().func_177746_a(pos)) {
            final AxisAlignedBB blockAxis = new AxisAlignedBB(pos.func_177958_n() - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() - RenderUtil.mc.func_175598_ae().field_78731_m, pos.func_177952_p() - RenderUtil.mc.func_175598_ae().field_78728_n, pos.func_177958_n() + 1 - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() + 1 - RenderUtil.mc.func_175598_ae().field_78731_m + height, pos.func_177952_p() + 1 - RenderUtil.mc.func_175598_ae().field_78728_n);
            drawBlockOutline(blockAxis.func_186662_g(0.0020000000949949026), color, linewidth);
        }
    }
    
    public static void drawBlockOutline(final AxisAlignedBB bb, final Color color, final float linewidth) {
        final float red = color.getRed() / 255.0f;
        final float green = color.getGreen() / 255.0f;
        final float blue = color.getBlue() / 255.0f;
        final float alpha = color.getAlpha() / 255.0f;
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(linewidth);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        tessellator.func_78381_a();
        GL11.glDisable(2848);
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }
    
    public static void drawBoxESP(final BlockPos pos, final Color color, final float lineWidth, final boolean outline, final boolean box, final int boxAlpha) {
        final AxisAlignedBB bb = new AxisAlignedBB(pos.func_177958_n() - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() - RenderUtil.mc.func_175598_ae().field_78731_m, pos.func_177952_p() - RenderUtil.mc.func_175598_ae().field_78728_n, pos.func_177958_n() + 1 - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() + 1 - RenderUtil.mc.func_175598_ae().field_78731_m, pos.func_177952_p() + 1 - RenderUtil.mc.func_175598_ae().field_78728_n);
        RenderUtil.camera.func_78547_a(Objects.requireNonNull(RenderUtil.mc.func_175606_aa()).field_70165_t, RenderUtil.mc.func_175606_aa().field_70163_u, RenderUtil.mc.func_175606_aa().field_70161_v);
        if (RenderUtil.camera.func_78546_a(new AxisAlignedBB(bb.field_72340_a + RenderUtil.mc.func_175598_ae().field_78730_l, bb.field_72338_b + RenderUtil.mc.func_175598_ae().field_78731_m, bb.field_72339_c + RenderUtil.mc.func_175598_ae().field_78728_n, bb.field_72336_d + RenderUtil.mc.func_175598_ae().field_78730_l, bb.field_72337_e + RenderUtil.mc.func_175598_ae().field_78731_m, bb.field_72334_f + RenderUtil.mc.func_175598_ae().field_78728_n))) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179097_i();
            GlStateManager.func_179120_a(770, 771, 0, 1);
            GlStateManager.func_179090_x();
            GlStateManager.func_179132_a(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(lineWidth);
            final double dist = RenderUtil.mc.field_71439_g.func_70011_f((double)(pos.func_177958_n() + 0.5f), (double)(pos.func_177956_o() + 0.5f), (double)(pos.func_177952_p() + 0.5f)) * 0.75;
            if (box) {
                RenderGlobal.func_189696_b(bb, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, boxAlpha / 255.0f);
            }
            if (outline) {
                RenderGlobal.func_189694_a(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c, bb.field_72336_d, bb.field_72337_e, bb.field_72334_f, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            }
            GL11.glDisable(2848);
            GlStateManager.func_179132_a(true);
            GlStateManager.func_179126_j();
            GlStateManager.func_179098_w();
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
        }
    }
    
    public static void drawText(final AxisAlignedBB pos, final String text) {
        if (pos == null || text == null) {
            return;
        }
        GlStateManager.func_179094_E();
        glBillboardDistanceScaled((float)pos.field_72340_a + 0.5f, (float)pos.field_72338_b + 0.5f, (float)pos.field_72339_c + 0.5f, (EntityPlayer)RenderUtil.mc.field_71439_g, 1.0f);
        GlStateManager.func_179097_i();
        GlStateManager.func_179137_b(-(CreepyWare.textManager.getStringWidth(text) / 2.0), 0.0, 0.0);
        CreepyWare.textManager.drawStringWithShadow(text, 0.0f, 0.0f, -5592406);
        GlStateManager.func_179121_F();
    }
    
    public static void drawOutlinedBlockESP(final BlockPos pos, final Color color, final float linewidth) {
        final IBlockState iblockstate = RenderUtil.mc.field_71441_e.func_180495_p(pos);
        final Vec3d interp = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.field_71439_g, RenderUtil.mc.func_184121_ak());
        drawBoundingBox(iblockstate.func_185918_c((World)RenderUtil.mc.field_71441_e, pos).func_186662_g(0.0020000000949949026).func_72317_d(-interp.field_72450_a, -interp.field_72448_b, -interp.field_72449_c), linewidth, ColorUtil.toRGBA(color));
    }
    
    public static void blockEsp(final BlockPos blockPos, final Color c, final double length, final double length2) {
        final double x = blockPos.func_177958_n() - RenderUtil.mc.field_175616_W.field_78725_b;
        final double y = blockPos.func_177956_o() - RenderUtil.mc.field_175616_W.field_78726_c;
        final double z = blockPos.func_177952_p() - RenderUtil.mc.field_175616_W.field_78723_d;
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d((double)(c.getRed() / 255.0f), (double)(c.getGreen() / 255.0f), (double)(c.getBlue() / 255.0f), 0.25);
        drawColorBox(new AxisAlignedBB(x, y, z, x + length2, y + 1.0, z + length), 0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glColor4d(0.0, 0.0, 0.0, 0.5);
        drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + length2, y + 1.0, z + length));
        GL11.glLineWidth(2.0f);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void drawRect(final float x, final float y, final float w, final float h, final int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b((double)x, (double)h, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)w, (double)h, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)w, (double)y, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)x, (double)y, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void drawColorBox(final AxisAlignedBB axisalignedbb, final float red, final float green, final float blue, final float alpha) {
        final Tessellator ts = Tessellator.func_178181_a();
        final BufferBuilder vb = ts.func_178180_c();
        vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        ts.func_78381_a();
        vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        ts.func_78381_a();
        vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        ts.func_78381_a();
        vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        ts.func_78381_a();
        vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        ts.func_78381_a();
        vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        ts.func_78381_a();
    }
    
    public static void drawSelectionBoundingBox(final AxisAlignedBB boundingBox) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder vertexbuffer = tessellator.func_178180_c();
        vertexbuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        tessellator.func_78381_a();
        vertexbuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        tessellator.func_78381_a();
        vertexbuffer.func_181668_a(1, DefaultVertexFormats.field_181705_e);
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        tessellator.func_78381_a();
    }
    
    public static void glrendermethod() {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(2929);
        final double viewerPosX = RenderUtil.mc.func_175598_ae().field_78730_l;
        final double viewerPosY = RenderUtil.mc.func_175598_ae().field_78731_m;
        final double viewerPosZ = RenderUtil.mc.func_175598_ae().field_78728_n;
        GL11.glPushMatrix();
        GL11.glTranslated(-viewerPosX, -viewerPosY, -viewerPosZ);
    }
    
    public static void glStart(final float n, final float n2, final float n3, final float n4) {
        glrendermethod();
        GL11.glColor4f(n, n2, n3, n4);
    }
    
    public static void glEnd() {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static AxisAlignedBB getBoundingBox(final BlockPos blockPos) {
        return RenderUtil.mc.field_71441_e.func_180495_p(blockPos).func_185900_c((IBlockAccess)RenderUtil.mc.field_71441_e, blockPos).func_186670_a(blockPos);
    }
    
    public static void drawOutlinedBox(final AxisAlignedBB axisAlignedBB) {
        GL11.glBegin(1);
        GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c);
        GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c);
        GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c);
        GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72338_b, axisAlignedBB.field_72334_f);
        GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72338_b, axisAlignedBB.field_72334_f);
        GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72334_f);
        GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72334_f);
        GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c);
        GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c);
        GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72337_e, axisAlignedBB.field_72339_c);
        GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c);
        GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72339_c);
        GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72338_b, axisAlignedBB.field_72334_f);
        GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f);
        GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72334_f);
        GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f);
        GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72337_e, axisAlignedBB.field_72339_c);
        GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72339_c);
        GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72339_c);
        GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f);
        GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f);
        GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f);
        GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f);
        GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72337_e, axisAlignedBB.field_72339_c);
        GL11.glEnd();
    }
    
    public static void drawFilledBoxESPN(final BlockPos pos, final Color color) {
        final AxisAlignedBB bb = new AxisAlignedBB(pos.func_177958_n() - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() - RenderUtil.mc.func_175598_ae().field_78731_m, pos.func_177952_p() - RenderUtil.mc.func_175598_ae().field_78728_n, pos.func_177958_n() + 1 - RenderUtil.mc.func_175598_ae().field_78730_l, pos.func_177956_o() + 1 - RenderUtil.mc.func_175598_ae().field_78731_m, pos.func_177952_p() + 1 - RenderUtil.mc.func_175598_ae().field_78728_n);
        final int rgba = ColorUtil.toRGBA(color);
        drawFilledBox(bb, rgba);
    }
    
    public static void drawFilledBox(final AxisAlignedBB bb, final int color) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }
    
    public static void drawBoundingBox(final AxisAlignedBB bb, final float width, final int color) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(width);
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        tessellator.func_78381_a();
        GL11.glDisable(2848);
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }
    
    public static void glBillboard(final float x, final float y, final float z) {
        final float scale = 0.02666667f;
        GlStateManager.func_179137_b(x - RenderUtil.mc.func_175598_ae().field_78725_b, y - RenderUtil.mc.func_175598_ae().field_78726_c, z - RenderUtil.mc.func_175598_ae().field_78723_d);
        GlStateManager.func_187432_a(0.0f, 1.0f, 0.0f);
        GlStateManager.func_179114_b(-RenderUtil.mc.field_71439_g.field_70177_z, 0.0f, 1.0f, 0.0f);
        GlStateManager.func_179114_b(RenderUtil.mc.field_71439_g.field_70125_A, (RenderUtil.mc.field_71474_y.field_74320_O == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.func_179152_a(-scale, -scale, scale);
    }
    
    public static void glBillboardDistanceScaled(final float x, final float y, final float z, final EntityPlayer player, final float scale) {
        glBillboard(x, y, z);
        final int distance = (int)player.func_70011_f((double)x, (double)y, (double)z);
        float scaleDistance = distance / 2.0f / (2.0f + (2.0f - scale));
        if (scaleDistance < 1.0f) {
            scaleDistance = 1.0f;
        }
        GlStateManager.func_179152_a(scaleDistance, scaleDistance, scaleDistance);
    }
    
    public static void drawColoredBoundingBox(final AxisAlignedBB bb, final float width, final float red, final float green, final float blue, final float alpha) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(width);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, 0.0f).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, 0.0f).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, 0.0f).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, 0.0f).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, 0.0f).func_181675_d();
        tessellator.func_78381_a();
        GL11.glDisable(2848);
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }
    
    public static void drawSphere(final double x, final double y, final double z, final float size, final int slices, final int stacks) {
        final Sphere s = new Sphere();
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.2f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        s.setDrawStyle(100013);
        GL11.glTranslated(x - RenderUtil.mc.field_175616_W.field_78725_b, y - RenderUtil.mc.field_175616_W.field_78726_c, z - RenderUtil.mc.field_175616_W.field_78723_d);
        s.draw(size, slices, stacks);
        GL11.glLineWidth(2.0f);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawBar(final GLUProjection.Projection projection, final float width, final float height, final float totalWidth, final Color startColor, final Color outlineColor) {
        if (projection.getType() == GLUProjection.Projection.Type.INSIDE) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179137_b(projection.getX(), projection.getY(), 0.0);
            drawOutlineRect(-(totalWidth / 2.0f), -(height / 2.0f), totalWidth, height, outlineColor.getRGB());
            drawRect(-(totalWidth / 2.0f), -(height / 2.0f), width, height, startColor.getRGB());
            GlStateManager.func_179137_b(-projection.getX(), -projection.getY(), 0.0);
            GlStateManager.func_179121_F();
        }
    }
    
    public static void drawProjectedText(final GLUProjection.Projection projection, final float addX, final float addY, final String text, final Color color, final boolean shadow) {
        if (projection.getType() == GLUProjection.Projection.Type.INSIDE) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179137_b(projection.getX(), projection.getY(), 0.0);
            CreepyWare.textManager.drawString(text, -(CreepyWare.textManager.getStringWidth(text) / 2.0f) + addX, addY, color.getRGB(), shadow);
            GlStateManager.func_179137_b(-projection.getX(), -projection.getY(), 0.0);
            GlStateManager.func_179121_F();
        }
    }
    
    public static void drawChungusESP(final GLUProjection.Projection projection, final float width, final float height, final ResourceLocation location) {
        if (projection.getType() == GLUProjection.Projection.Type.INSIDE) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179137_b(projection.getX(), projection.getY(), 0.0);
            RenderUtil.mc.func_110434_K().func_110577_a(location);
            GlStateManager.func_179098_w();
            GlStateManager.func_179084_k();
            RenderUtil.mc.func_110434_K().func_110577_a(location);
            drawCompleteImage(0.0f, 0.0f, width, height);
            RenderUtil.mc.func_110434_K().func_147645_c(location);
            GlStateManager.func_179147_l();
            GlStateManager.func_179090_x();
            GlStateManager.func_179137_b(-projection.getX(), -projection.getY(), 0.0);
            GlStateManager.func_179121_F();
        }
    }
    
    public static void drawCompleteImage(final float posX, final float posY, final float width, final float height) {
        GL11.glPushMatrix();
        GL11.glTranslatef(posX, posY, 0.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(0.0f, height, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(width, height, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(width, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    public static void drawOutlineRect(final float x, final float y, final float w, final float h, final int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_187441_d(1.0f);
        GlStateManager.func_179120_a(770, 771, 1, 0);
        bufferbuilder.func_181668_a(2, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b((double)x, (double)h, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)w, (double)h, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)w, (double)y, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)x, (double)y, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void draw3DRect(final float x, final float y, final float w, final float h, final Color startColor, final Color endColor, final float lineWidth) {
        final float alpha = startColor.getAlpha() / 255.0f;
        final float red = startColor.getRed() / 255.0f;
        final float green = startColor.getGreen() / 255.0f;
        final float blue = startColor.getBlue() / 255.0f;
        final float alpha2 = endColor.getAlpha() / 255.0f;
        final float red2 = endColor.getRed() / 255.0f;
        final float green2 = endColor.getGreen() / 255.0f;
        final float blue2 = endColor.getBlue() / 255.0f;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_187441_d(lineWidth);
        GlStateManager.func_179120_a(770, 771, 1, 0);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b((double)x, (double)h, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)w, (double)h, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)w, (double)y, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b((double)x, (double)y, 0.0).func_181666_a(red, green, blue, alpha).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void drawClock(final float x, final float y, final float radius, final int slices, final int loops, final float lineWidth, final boolean fill, final Color color) {
        final Disk disk = new Disk();
        final int hourAngle = 180 + -(Calendar.getInstance().get(10) * 30 + Calendar.getInstance().get(12) / 2);
        final int minuteAngle = 180 + -(Calendar.getInstance().get(12) * 6 + Calendar.getInstance().get(13) / 10);
        final int secondAngle = 180 + -(Calendar.getInstance().get(13) * 6);
        final int totalMinutesTime = Calendar.getInstance().get(12);
        final int totalHoursTime = Calendar.getInstance().get(10);
        if (fill) {
            GL11.glPushMatrix();
            GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3042);
            GL11.glLineWidth(lineWidth);
            GL11.glDisable(3553);
            disk.setOrientation(100020);
            disk.setDrawStyle(100012);
            GL11.glTranslated((double)x, (double)y, 0.0);
            disk.draw(0.0f, radius, slices, loops);
            GL11.glEnable(3553);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
        }
        else {
            GL11.glPushMatrix();
            GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glEnable(3042);
            GL11.glLineWidth(lineWidth);
            GL11.glDisable(3553);
            GL11.glBegin(3);
            final ArrayList<Vec2f> hVectors = new ArrayList<Vec2f>();
            float hue = System.currentTimeMillis() % 7200L / 7200.0f;
            for (int i = 0; i <= 360; ++i) {
                final Vec2f vec = new Vec2f(x + (float)Math.sin(i * 3.141592653589793 / 180.0) * radius, y + (float)Math.cos(i * 3.141592653589793 / 180.0) * radius);
                hVectors.add(vec);
            }
            Color color2 = new Color(Color.HSBtoRGB(hue, 1.0f, 1.0f));
            for (int j = 0; j < hVectors.size() - 1; ++j) {
                GL11.glColor4f(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
                GL11.glVertex3d((double)hVectors.get(j).field_189982_i, (double)hVectors.get(j).field_189983_j, 0.0);
                GL11.glVertex3d((double)hVectors.get(j + 1).field_189982_i, (double)hVectors.get(j + 1).field_189983_j, 0.0);
                color2 = new Color(Color.HSBtoRGB(hue += 0.0027777778f, 1.0f, 1.0f));
            }
            GL11.glEnd();
            GL11.glEnable(3553);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
        }
        drawLine(x, y, x + (float)Math.sin(hourAngle * 3.141592653589793 / 180.0) * (radius / 2.0f), y + (float)Math.cos(hourAngle * 3.141592653589793 / 180.0) * (radius / 2.0f), 1.0f, Color.WHITE.getRGB());
        drawLine(x, y, x + (float)Math.sin(minuteAngle * 3.141592653589793 / 180.0) * (radius - radius / 10.0f), y + (float)Math.cos(minuteAngle * 3.141592653589793 / 180.0) * (radius - radius / 10.0f), 1.0f, Color.WHITE.getRGB());
        drawLine(x, y, x + (float)Math.sin(secondAngle * 3.141592653589793 / 180.0) * (radius - radius / 10.0f), y + (float)Math.cos(secondAngle * 3.141592653589793 / 180.0) * (radius - radius / 10.0f), 1.0f, Color.RED.getRGB());
    }
    
    public static void GLPre(final float lineWidth) {
        RenderUtil.depth = GL11.glIsEnabled(2896);
        RenderUtil.texture = GL11.glIsEnabled(3042);
        RenderUtil.clean = GL11.glIsEnabled(3553);
        RenderUtil.bind = GL11.glIsEnabled(2929);
        RenderUtil.override = GL11.glIsEnabled(2848);
        GLPre(RenderUtil.depth, RenderUtil.texture, RenderUtil.clean, RenderUtil.bind, RenderUtil.override, lineWidth);
    }
    
    public static void GlPost() {
        GLPost(RenderUtil.depth, RenderUtil.texture, RenderUtil.clean, RenderUtil.bind, RenderUtil.override);
    }
    
    private static void GLPre(final boolean depth, final boolean texture, final boolean clean, final boolean bind, final boolean override, final float lineWidth) {
        if (depth) {
            GL11.glDisable(2896);
        }
        if (!texture) {
            GL11.glEnable(3042);
        }
        GL11.glLineWidth(lineWidth);
        if (clean) {
            GL11.glDisable(3553);
        }
        if (bind) {
            GL11.glDisable(2929);
        }
        if (!override) {
            GL11.glEnable(2848);
        }
        GlStateManager.func_187401_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GL11.glHint(3154, 4354);
        GlStateManager.func_179132_a(false);
    }
    
    public static float[][] getBipedRotations(final ModelBiped biped) {
        final float[][] rotations = new float[5][];
        final float[] headRotation = { biped.field_78116_c.field_78795_f, biped.field_78116_c.field_78796_g, biped.field_78116_c.field_78808_h };
        rotations[0] = headRotation;
        final float[] rightArmRotation = { biped.field_178723_h.field_78795_f, biped.field_178723_h.field_78796_g, biped.field_178723_h.field_78808_h };
        rotations[1] = rightArmRotation;
        final float[] leftArmRotation = { biped.field_178724_i.field_78795_f, biped.field_178724_i.field_78796_g, biped.field_178724_i.field_78808_h };
        rotations[2] = leftArmRotation;
        final float[] rightLegRotation = { biped.field_178721_j.field_78795_f, biped.field_178721_j.field_78796_g, biped.field_178721_j.field_78808_h };
        rotations[3] = rightLegRotation;
        final float[] leftLegRotation = { biped.field_178722_k.field_78795_f, biped.field_178722_k.field_78796_g, biped.field_178722_k.field_78808_h };
        rotations[4] = leftLegRotation;
        return rotations;
    }
    
    private static void GLPost(final boolean depth, final boolean texture, final boolean clean, final boolean bind, final boolean override) {
        GlStateManager.func_179132_a(true);
        if (!override) {
            GL11.glDisable(2848);
        }
        if (bind) {
            GL11.glEnable(2929);
        }
        if (clean) {
            GL11.glEnable(3553);
        }
        if (!texture) {
            GL11.glDisable(3042);
        }
        if (depth) {
            GL11.glEnable(2896);
        }
    }
    
    public static void drawArc(final float cx, final float cy, final float r, final float start_angle, final float end_angle, final int num_segments) {
        GL11.glBegin(4);
        for (int i = (int)(num_segments / (360.0f / start_angle)) + 1; i <= num_segments / (360.0f / end_angle); ++i) {
            final double previousangle = 6.283185307179586 * (i - 1) / num_segments;
            final double angle = 6.283185307179586 * i / num_segments;
            GL11.glVertex2d((double)cx, (double)cy);
            GL11.glVertex2d(cx + Math.cos(angle) * r, cy + Math.sin(angle) * r);
            GL11.glVertex2d(cx + Math.cos(previousangle) * r, cy + Math.sin(previousangle) * r);
        }
        glEnd();
    }
    
    public static void drawArcOutline(final float cx, final float cy, final float r, final float start_angle, final float end_angle, final int num_segments) {
        GL11.glBegin(2);
        for (int i = (int)(num_segments / (360.0f / start_angle)) + 1; i <= num_segments / (360.0f / end_angle); ++i) {
            final double angle = 6.283185307179586 * i / num_segments;
            GL11.glVertex2d(cx + Math.cos(angle) * r, cy + Math.sin(angle) * r);
        }
        glEnd();
    }
    
    public static void drawCircleOutline(final float x, final float y, final float radius) {
        drawCircleOutline(x, y, radius, 0, 360, 40);
    }
    
    public static void drawCircleOutline(final float x, final float y, final float radius, final int start, final int end, final int segments) {
        drawArcOutline(x, y, radius, (float)start, (float)end, segments);
    }
    
    public static void drawCircle(final float x, final float y, final float radius) {
        drawCircle(x, y, radius, 0, 360, 64);
    }
    
    public static void drawCircle(final float x, final float y, final float radius, final int start, final int end, final int segments) {
        drawArc(x, y, radius, (float)start, (float)end, segments);
    }
    
    public static void drawOutlinedRoundedRectangle(final int x, final int y, final int width, final int height, final float radius, final float dR, final float dG, final float dB, final float dA, final float outlineWidth) {
        drawRoundedRectangle((float)x, (float)y, (float)width, (float)height, radius);
        GL11.glColor4f(dR, dG, dB, dA);
        drawRoundedRectangle(x + outlineWidth, y + outlineWidth, width - outlineWidth * 2.0f, height - outlineWidth * 2.0f, radius);
    }
    
    public static void drawRectangle(final float x, final float y, final float width, final float height) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(2);
        GL11.glVertex2d((double)width, 0.0);
        GL11.glVertex2d(0.0, 0.0);
        GL11.glVertex2d(0.0, (double)height);
        GL11.glVertex2d((double)width, (double)height);
        glEnd();
    }
    
    public static void drawRectangleXY(final float x, final float y, final float width, final float height) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(2);
        GL11.glVertex2d((double)(x + width), (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)(y + height));
        GL11.glVertex2d((double)(x + width), (double)(y + height));
        glEnd();
    }
    
    public static void drawFilledRectangle(final float x, final float y, final float width, final float height) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(7);
        GL11.glVertex2d((double)(x + width), (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)(y + height));
        GL11.glVertex2d((double)(x + width), (double)(y + height));
        glEnd();
    }
    
    public static Vec3d to2D(final double x, final double y, final double z) {
        GL11.glGetFloat(2982, RenderUtil.modelView);
        GL11.glGetFloat(2983, RenderUtil.projection);
        GL11.glGetInteger(2978, RenderUtil.viewport);
        final boolean result = GLU.gluProject((float)x, (float)y, (float)z, RenderUtil.modelView, RenderUtil.projection, RenderUtil.viewport, RenderUtil.screenCoords);
        if (result) {
            return new Vec3d((double)RenderUtil.screenCoords.get(0), (double)(Display.getHeight() - RenderUtil.screenCoords.get(1)), (double)RenderUtil.screenCoords.get(2));
        }
        return null;
    }
    
    public static void drawTracerPointer(final float x, final float y, final float size, final float widthDiv, final float heightDiv, final boolean outline, final float outlineWidth, final int color) {
        final boolean blend = GL11.glIsEnabled(3042);
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        hexColor(color);
        GL11.glBegin(7);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)(x - size / widthDiv), (double)(y + size));
        GL11.glVertex2d((double)x, (double)(y + size / heightDiv));
        GL11.glVertex2d((double)(x + size / widthDiv), (double)(y + size));
        GL11.glVertex2d((double)x, (double)y);
        GL11.glEnd();
        if (outline) {
            GL11.glLineWidth(outlineWidth);
            GL11.glColor4f(0.0f, 0.0f, 0.0f, alpha);
            GL11.glBegin(2);
            GL11.glVertex2d((double)x, (double)y);
            GL11.glVertex2d((double)(x - size / widthDiv), (double)(y + size));
            GL11.glVertex2d((double)x, (double)(y + size / heightDiv));
            GL11.glVertex2d((double)(x + size / widthDiv), (double)(y + size));
            GL11.glVertex2d((double)x, (double)y);
            GL11.glEnd();
        }
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        if (!blend) {
            GL11.glDisable(3042);
        }
        GL11.glDisable(2848);
    }
    
    public static int getRainbow(final int speed, final int offset, final float s, final float b) {
        final float hue = (float)((System.currentTimeMillis() + offset) % speed);
        return Color.getHSBColor(hue / speed, s, b).getRGB();
    }
    
    public static void hexColor(final int hexColor) {
        final float red = (hexColor >> 16 & 0xFF) / 255.0f;
        final float green = (hexColor >> 8 & 0xFF) / 255.0f;
        final float blue = (hexColor & 0xFF) / 255.0f;
        final float alpha = (hexColor >> 24 & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }
    
    public static boolean isInViewFrustrum(final Entity entity) {
        return isInViewFrustrum(entity.func_174813_aQ()) || entity.field_70158_ak;
    }
    
    public static boolean isInViewFrustrum(final AxisAlignedBB bb) {
        final Entity current = Minecraft.func_71410_x().func_175606_aa();
        RenderUtil.frustrum.func_78547_a(Objects.requireNonNull(current).field_70165_t, current.field_70163_u, current.field_70161_v);
        return RenderUtil.frustrum.func_78546_a(bb);
    }
    
    public static void drawRoundedRectangle(final float x, final float y, final float width, final float height, final float radius) {
        GL11.glEnable(3042);
        drawArc(x + width - radius, y + height - radius, radius, 0.0f, 90.0f, 16);
        drawArc(x + radius, y + height - radius, radius, 90.0f, 180.0f, 16);
        drawArc(x + radius, y + radius, radius, 180.0f, 270.0f, 16);
        drawArc(x + width - radius, y + radius, radius, 270.0f, 360.0f, 16);
        GL11.glBegin(4);
        GL11.glVertex2d((double)(x + width - radius), (double)y);
        GL11.glVertex2d((double)(x + radius), (double)y);
        GL11.glVertex2d((double)(x + width - radius), (double)(y + radius));
        GL11.glVertex2d((double)(x + width - radius), (double)(y + radius));
        GL11.glVertex2d((double)(x + radius), (double)y);
        GL11.glVertex2d((double)(x + radius), (double)(y + radius));
        GL11.glVertex2d((double)(x + width), (double)(y + radius));
        GL11.glVertex2d((double)x, (double)(y + radius));
        GL11.glVertex2d((double)x, (double)(y + height - radius));
        GL11.glVertex2d((double)(x + width), (double)(y + radius));
        GL11.glVertex2d((double)x, (double)(y + height - radius));
        GL11.glVertex2d((double)(x + width), (double)(y + height - radius));
        GL11.glVertex2d((double)(x + width - radius), (double)(y + height - radius));
        GL11.glVertex2d((double)(x + radius), (double)(y + height - radius));
        GL11.glVertex2d((double)(x + width - radius), (double)(y + height));
        GL11.glVertex2d((double)(x + width - radius), (double)(y + height));
        GL11.glVertex2d((double)(x + radius), (double)(y + height - radius));
        GL11.glVertex2d((double)(x + radius), (double)(y + height));
        glEnd();
    }
    
    public static void renderOne(final float lineWidth) {
        checkSetupFBO();
        GL11.glPushAttrib(1048575);
        GL11.glDisable(3008);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(lineWidth);
        GL11.glEnable(2848);
        GL11.glEnable(2960);
        GL11.glClear(1024);
        GL11.glClearStencil(15);
        GL11.glStencilFunc(512, 1, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1032, 6913);
    }
    
    public static void renderTwo() {
        GL11.glStencilFunc(512, 0, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1032, 6914);
    }
    
    public static void renderThree() {
        GL11.glStencilFunc(514, 1, 15);
        GL11.glStencilOp(7680, 7680, 7680);
        GL11.glPolygonMode(1032, 6913);
    }
    
    public static void renderFour(final Color color) {
        setColor(color);
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
        GL11.glEnable(10754);
        GL11.glPolygonOffset(1.0f, -2000000.0f);
        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0f, 240.0f);
    }
    
    public static void renderFive() {
        GL11.glPolygonOffset(1.0f, 2000000.0f);
        GL11.glDisable(10754);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(2960);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glEnable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        GL11.glPopAttrib();
    }
    
    public static void rotationHelper(final float xAngle, final float yAngle, final float zAngle) {
        GlStateManager.func_179114_b(yAngle, 0.0f, 1.0f, 0.0f);
        GlStateManager.func_179114_b(zAngle, 0.0f, 0.0f, 1.0f);
        GlStateManager.func_179114_b(xAngle, 1.0f, 0.0f, 0.0f);
    }
    
    public static void setColor(final Color color) {
        GL11.glColor4d(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0, color.getAlpha() / 255.0);
    }
    
    public static void checkSetupFBO() {
        final Framebuffer fbo = RenderUtil.mc.field_147124_at;
        if (fbo != null && fbo.field_147624_h > -1) {
            setupFBO(fbo);
            fbo.field_147624_h = -1;
        }
    }
    
    private static void setupFBO(final Framebuffer fbo) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(fbo.field_147624_h);
        final int stencilDepthBufferID = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT(36161, stencilDepthBufferID);
        EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, RenderUtil.mc.field_71443_c, RenderUtil.mc.field_71440_d);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, stencilDepthBufferID);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, stencilDepthBufferID);
    }
    
    static {
        frustrum = new Frustum();
        screenCoords = BufferUtils.createFloatBuffer(3);
        viewport = BufferUtils.createIntBuffer(16);
        modelView = BufferUtils.createFloatBuffer(16);
        projection = BufferUtils.createFloatBuffer(16);
        RenderUtil.itemRender = RenderUtil.mc.func_175599_af();
        RenderUtil.camera = (ICamera)new Frustum();
        RenderUtil.depth = GL11.glIsEnabled(2896);
        RenderUtil.texture = GL11.glIsEnabled(3042);
        RenderUtil.clean = GL11.glIsEnabled(3553);
        RenderUtil.bind = GL11.glIsEnabled(2929);
        RenderUtil.override = GL11.glIsEnabled(2848);
    }
    
    public static class RenderTesselator extends Tessellator
    {
        public static RenderTesselator INSTANCE;
        
        public RenderTesselator() {
            super(2097152);
        }
        
        public static void prepare(final int mode) {
            prepareGL();
            begin(mode);
        }
        
        public static void prepareGL() {
            GL11.glBlendFunc(770, 771);
            GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.func_187441_d(1.5f);
            GlStateManager.func_179090_x();
            GlStateManager.func_179132_a(false);
            GlStateManager.func_179147_l();
            GlStateManager.func_179097_i();
            GlStateManager.func_179140_f();
            GlStateManager.func_179129_p();
            GlStateManager.func_179141_d();
            GlStateManager.func_179124_c(1.0f, 1.0f, 1.0f);
        }
        
        public static void begin(final int mode) {
            RenderTesselator.INSTANCE.func_178180_c().func_181668_a(mode, DefaultVertexFormats.field_181706_f);
        }
        
        public static void release() {
            render();
            releaseGL();
        }
        
        public static void render() {
            RenderTesselator.INSTANCE.func_78381_a();
        }
        
        public static void releaseGL() {
            GlStateManager.func_179089_o();
            GlStateManager.func_179132_a(true);
            GlStateManager.func_179098_w();
            GlStateManager.func_179147_l();
            GlStateManager.func_179126_j();
        }
        
        public static void drawBox(final BlockPos blockPos, final int argb, final int sides) {
            final int a = argb >>> 24 & 0xFF;
            final int r = argb >>> 16 & 0xFF;
            final int g = argb >>> 8 & 0xFF;
            final int b = argb & 0xFF;
            drawBox(blockPos, r, g, b, a, sides);
        }
        
        public static void drawBox(final float x, final float y, final float z, final int argb, final int sides) {
            final int a = argb >>> 24 & 0xFF;
            final int r = argb >>> 16 & 0xFF;
            final int g = argb >>> 8 & 0xFF;
            final int b = argb & 0xFF;
            drawBox(RenderTesselator.INSTANCE.func_178180_c(), x, y, z, 1.0f, 1.0f, 1.0f, r, g, b, a, sides);
        }
        
        public static void drawBox(final BlockPos blockPos, final int r, final int g, final int b, final int a, final int sides) {
            drawBox(RenderTesselator.INSTANCE.func_178180_c(), (float)blockPos.func_177958_n(), (float)blockPos.func_177956_o(), (float)blockPos.func_177952_p(), 1.0f, 1.0f, 1.0f, r, g, b, a, sides);
        }
        
        public static BufferBuilder getBufferBuilder() {
            return RenderTesselator.INSTANCE.func_178180_c();
        }
        
        public static void drawBox(final BufferBuilder buffer, final float x, final float y, final float z, final float w, final float h, final float d, final int r, final int g, final int b, final int a, final int sides) {
            if ((sides & 0x1) != 0x0) {
                buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x2) != 0x0) {
                buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x4) != 0x0) {
                buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x8) != 0x0) {
                buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x10) != 0x0) {
                buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x20) != 0x0) {
                buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            }
        }
        
        public static void drawLines(final BufferBuilder buffer, final float x, final float y, final float z, final float w, final float h, final float d, final int r, final int g, final int b, final int a, final int sides) {
            if ((sides & 0x11) != 0x0) {
                buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x12) != 0x0) {
                buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x21) != 0x0) {
                buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x22) != 0x0) {
                buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x5) != 0x0) {
                buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x6) != 0x0) {
                buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x9) != 0x0) {
                buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0xA) != 0x0) {
                buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x14) != 0x0) {
                buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x24) != 0x0) {
                buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x18) != 0x0) {
                buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            }
            if ((sides & 0x28) != 0x0) {
                buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
                buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            }
        }
        
        public static void drawBoundingBox(final AxisAlignedBB bb, final float width, final float red, final float green, final float blue, final float alpha) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_179097_i();
            GlStateManager.func_179120_a(770, 771, 0, 1);
            GlStateManager.func_179090_x();
            GlStateManager.func_179132_a(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(width);
            final Tessellator tessellator = Tessellator.func_178181_a();
            final BufferBuilder bufferbuilder = tessellator.func_178180_c();
            bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
            tessellator.func_78381_a();
            GL11.glDisable(2848);
            GlStateManager.func_179132_a(true);
            GlStateManager.func_179126_j();
            GlStateManager.func_179098_w();
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
        }
        
        public static void drawFullBox(final AxisAlignedBB bb, final BlockPos blockPos, final float width, final int argb, final int alpha2) {
            final int a = argb >>> 24 & 0xFF;
            final int r = argb >>> 16 & 0xFF;
            final int g = argb >>> 8 & 0xFF;
            final int b = argb & 0xFF;
            drawFullBox(bb, blockPos, width, r, g, b, a, alpha2);
        }
        
        public static void drawFullBox(final AxisAlignedBB bb, final BlockPos blockPos, final float width, final int red, final int green, final int blue, final int alpha, final int alpha2) {
            prepare(7);
            drawBox(blockPos, red, green, blue, alpha, 63);
            release();
            drawBoundingBox(bb, width, (float)red, (float)green, (float)blue, (float)alpha2);
        }
        
        public static void drawHalfBox(final BlockPos blockPos, final int argb, final int sides) {
            final int a = argb >>> 24 & 0xFF;
            final int r = argb >>> 16 & 0xFF;
            final int g = argb >>> 8 & 0xFF;
            final int b = argb & 0xFF;
            drawHalfBox(blockPos, r, g, b, a, sides);
        }
        
        public static void drawHalfBox(final BlockPos blockPos, final int r, final int g, final int b, final int a, final int sides) {
            drawBox(RenderTesselator.INSTANCE.func_178180_c(), (float)blockPos.func_177958_n(), (float)blockPos.func_177956_o(), (float)blockPos.func_177952_p(), 1.0f, 0.5f, 1.0f, r, g, b, a, sides);
        }
        
        static {
            RenderTesselator.INSTANCE = new RenderTesselator();
        }
    }
    
    public static final class GeometryMasks
    {
        public static final HashMap<EnumFacing, Integer> FACEMAP;
        
        static {
            (FACEMAP = new HashMap<EnumFacing, Integer>()).put(EnumFacing.DOWN, 1);
            GeometryMasks.FACEMAP.put(EnumFacing.WEST, 16);
            GeometryMasks.FACEMAP.put(EnumFacing.NORTH, 4);
            GeometryMasks.FACEMAP.put(EnumFacing.SOUTH, 8);
            GeometryMasks.FACEMAP.put(EnumFacing.EAST, 32);
            GeometryMasks.FACEMAP.put(EnumFacing.UP, 2);
        }
        
        public static final class Line
        {
            public static final int DOWN_WEST = 17;
            public static final int UP_WEST = 18;
            public static final int DOWN_EAST = 33;
            public static final int UP_EAST = 34;
            public static final int DOWN_NORTH = 5;
            public static final int UP_NORTH = 6;
            public static final int DOWN_SOUTH = 9;
            public static final int UP_SOUTH = 10;
            public static final int NORTH_WEST = 20;
            public static final int NORTH_EAST = 36;
            public static final int SOUTH_WEST = 24;
            public static final int SOUTH_EAST = 40;
            public static final int ALL = 63;
        }
        
        public static final class Quad
        {
            public static final int DOWN = 1;
            public static final int UP = 2;
            public static final int NORTH = 4;
            public static final int SOUTH = 8;
            public static final int WEST = 16;
            public static final int EAST = 32;
            public static final int ALL = 63;
        }
    }
}
