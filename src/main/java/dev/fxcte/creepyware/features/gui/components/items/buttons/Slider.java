// 
// Decompiled by Procyon v0.5.36
// 

package dev.fxcte.creepyware.features.gui.components.items.buttons;

import org.lwjgl.input.Mouse;
import java.util.Iterator;
import dev.fxcte.creepyware.features.gui.components.Component;
import dev.fxcte.creepyware.features.gui.CreepyWareGui;
import dev.fxcte.creepyware.util.ColorUtil;
import dev.fxcte.creepyware.CreepyWare;
import dev.fxcte.creepyware.util.MathUtil;
import dev.fxcte.creepyware.features.modules.client.HUD;
import dev.fxcte.creepyware.features.modules.client.ClickGui;
import dev.fxcte.creepyware.util.RenderUtil;
import dev.fxcte.creepyware.features.setting.Setting;

public class Slider extends Button
{
    public Setting setting;
    private final Number min;
    private final Number max;
    private final int difference;
    
    public Slider(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.min = setting.getMin();
        this.max = setting.getMax();
        this.difference = this.max.intValue() - this.min.intValue();
        this.width = 15;
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.dragSetting(mouseX, mouseY);
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.isHovering(mouseX, mouseY) ? -2007673515 : 290805077);
        if (ClickGui.getInstance().rainbowRolling.getValue()) {
            final int color = ColorUtil.changeAlpha(HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), CreepyWare.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue());
            final int color2 = ColorUtil.changeAlpha(HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), CreepyWare.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue());
            RenderUtil.drawGradientRect(this.x, this.y, (this.setting.getValue().floatValue() <= this.min.floatValue()) ? 0.0f : ((this.width + 7.4f) * this.partialMultiplier()), this.height - 0.5f, this.isHovering(mouseX, mouseY) ? color : ((int)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight))), this.isHovering(mouseX, mouseY) ? color2 : ((int)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight))));
        }
        else {
            RenderUtil.drawRect(this.x, this.y, (this.setting.getValue().floatValue() <= this.min.floatValue()) ? this.x : (this.x + (this.width + 7.4f) * this.partialMultiplier()), this.y + this.height - 0.5f, this.isHovering(mouseX, mouseY) ? CreepyWare.colorManager.getColorWithAlpha(CreepyWare.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : CreepyWare.colorManager.getColorWithAlpha(CreepyWare.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue()));
        }
        CreepyWare.textManager.drawStringWithShadow(this.getName() + " §7" + ((this.setting.getValue() instanceof Float) ? this.setting.getValue() : Double.valueOf(this.setting.getValue().doubleValue())), this.x + 2.3f, this.y - 1.7f - CreepyWareGui.getClickGui().getTextOffset(), -1);
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
            this.setSettingFromX(mouseX);
        }
    }
    
    @Override
    public boolean isHovering(final int mouseX, final int mouseY) {
        for (final Component component : CreepyWareGui.getClickGui().getComponents()) {
            if (!component.drag) {
                continue;
            }
            return false;
        }
        return mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() + 8.0f && mouseY >= this.getY() && mouseY <= this.getY() + this.height;
    }
    
    @Override
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    private void dragSetting(final int mouseX, final int mouseY) {
        if (this.isHovering(mouseX, mouseY) && Mouse.isButtonDown(0)) {
            this.setSettingFromX(mouseX);
        }
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    private void setSettingFromX(final int mouseX) {
        final float percent = (mouseX - this.x) / (this.width + 7.4f);
        if (this.setting.getValue() instanceof Double) {
            final double result = this.setting.getMin() + this.difference * percent;
            this.setting.setValue(Math.round(10.0 * result) / 10.0);
        }
        else if (this.setting.getValue() instanceof Float) {
            final float result2 = this.setting.getMin() + this.difference * percent;
            this.setting.setValue(Math.round(10.0f * result2) / 10.0f);
        }
        else if (this.setting.getValue() instanceof Integer) {
            this.setting.setValue(this.setting.getMin() + (int)(this.difference * percent));
        }
    }
    
    private float middle() {
        return this.max.floatValue() - this.min.floatValue();
    }
    
    private float part() {
        return this.setting.getValue().floatValue() - this.min.floatValue();
    }
    
    private float partialMultiplier() {
        return this.part() / this.middle();
    }
}
