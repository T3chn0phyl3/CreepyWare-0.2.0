// 
// Decompiled by Procyon v0.5.36
// 

package dev.fxcte.creepyware.features.modules.render;

import dev.fxcte.creepyware.features.setting.Setting;
import dev.fxcte.creepyware.features.modules.Module;

public class CameraClip extends Module
{
    private static CameraClip INSTANCE;
    public Setting<Boolean> extend;
    public Setting<Double> distance;
    
    public CameraClip() {
        super("CameraClip", "Makes your Camera clip.", Category.RENDER, false, false, false);
        this.extend = (Setting<Boolean>)this.register(new Setting("Speed", "Extend", 0.0, 0.0, (T)false, 0));
        this.distance = (Setting<Double>)this.register(new Setting("Distance", (T)10.0, (T)0.0, (T)50.0, v -> this.extend.getValue(), "By how much you want to extend the distance."));
        this.setInstance();
    }
    
    public static CameraClip getInstance() {
        if (CameraClip.INSTANCE == null) {
            CameraClip.INSTANCE = new CameraClip();
        }
        return CameraClip.INSTANCE;
    }
    
    private void setInstance() {
        CameraClip.INSTANCE = this;
    }
    
    static {
        CameraClip.INSTANCE = new CameraClip();
    }
}
