// 
// Decompiled by Procyon v0.5.36
// 

package dev.fxcte.creepyware.features.modules.player;

import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import dev.fxcte.creepyware.util.Util;
import dev.fxcte.creepyware.util.ReflectionUtil;
import dev.fxcte.creepyware.features.gui.CreepyWareGui;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import dev.fxcte.creepyware.features.command.Command;
import dev.fxcte.creepyware.event.events.ClientEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import dev.fxcte.creepyware.event.events.PacketEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketCloseWindow;
import dev.fxcte.creepyware.features.Feature;
import java.util.Iterator;
import net.minecraft.inventory.Slot;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiInventory;
import dev.fxcte.creepyware.util.InventoryUtil;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import dev.fxcte.creepyware.features.setting.Bind;
import dev.fxcte.creepyware.features.setting.Setting;
import dev.fxcte.creepyware.features.modules.Module;

public class XCarry extends Module
{
    private static XCarry INSTANCE;
    private final Setting<Boolean> simpleMode;
    private final Setting<Bind> autoStore;
    private final Setting<Integer> obbySlot;
    private final Setting<Integer> slot1;
    private final Setting<Integer> slot2;
    private final Setting<Integer> slot3;
    private final Setting<Integer> tasks;
    private final Setting<Boolean> store;
    private final Setting<Boolean> shiftClicker;
    private final Setting<Boolean> withShift;
    private final Setting<Bind> keyBind;
    private final AtomicBoolean guiNeedsClose;
    private final Queue<InventoryUtil.Task> taskList;
    private GuiInventory openedGui;
    private boolean guiCloseGuard;
    private boolean autoDuelOn;
    private boolean obbySlotDone;
    private boolean slot1done;
    private boolean slot2done;
    private boolean slot3done;
    private List<Integer> doneSlots;
    
    public XCarry() {
        super("XCarry", "Uses the crafting inventory for storage", Category.PLAYER, true, false, false);
        this.simpleMode = (Setting<Boolean>)this.register(new Setting("Speed", "Simple", 0.0, 0.0, (T)false, 0));
        this.autoStore = (Setting<Bind>)this.register(new Setting("Speed", "AutoDuel", 0.0, 0.0, (T)new Bind(-1), 0));
        this.obbySlot = (Setting<Integer>)this.register(new Setting("ObbySlot", (T)2, (T)1, (T)9, v -> this.autoStore.getValue().getKey() != -1));
        this.slot1 = (Setting<Integer>)this.register(new Setting("Slot1", (T)22, (T)9, (T)44, v -> this.autoStore.getValue().getKey() != -1));
        this.slot2 = (Setting<Integer>)this.register(new Setting("Slot2", (T)23, (T)9, (T)44, v -> this.autoStore.getValue().getKey() != -1));
        this.slot3 = (Setting<Integer>)this.register(new Setting("Slot3", (T)24, (T)9, (T)44, v -> this.autoStore.getValue().getKey() != -1));
        this.tasks = (Setting<Integer>)this.register(new Setting("Actions", (T)3, (T)1, (T)12, v -> this.autoStore.getValue().getKey() != -1));
        this.store = (Setting<Boolean>)this.register(new Setting("Speed", "Store", 0.0, 0.0, (T)false, 0));
        this.shiftClicker = (Setting<Boolean>)this.register(new Setting("Speed", "ShiftClick", 0.0, 0.0, (T)false, 0));
        this.withShift = (Setting<Boolean>)this.register(new Setting("WithShift", (T)true, v -> this.shiftClicker.getValue()));
        this.keyBind = (Setting<Bind>)this.register(new Setting("ShiftBind", (T)new Bind(-1), v -> this.shiftClicker.getValue()));
        this.guiNeedsClose = new AtomicBoolean(false);
        this.taskList = new ConcurrentLinkedQueue<InventoryUtil.Task>();
        this.openedGui = null;
        this.guiCloseGuard = false;
        this.autoDuelOn = false;
        this.obbySlotDone = false;
        this.slot1done = false;
        this.slot2done = false;
        this.slot3done = false;
        this.doneSlots = new ArrayList<Integer>();
        this.setInstance();
    }
    
    public static XCarry getInstance() {
        if (XCarry.INSTANCE == null) {
            XCarry.INSTANCE = new XCarry();
        }
        return XCarry.INSTANCE;
    }
    
    private void setInstance() {
        XCarry.INSTANCE = this;
    }
    
    @Override
    public void onUpdate() {
        if (this.shiftClicker.getValue() && XCarry.mc.field_71462_r instanceof GuiInventory) {
            final boolean bl;
            final boolean ourBind = bl = (this.keyBind.getValue().getKey() != -1 && Keyboard.isKeyDown(this.keyBind.getValue().getKey()) && !Keyboard.isKeyDown(42));
            final Slot slot;
            if (((Keyboard.isKeyDown(42) && this.withShift.getValue()) || ourBind) && Mouse.isButtonDown(0) && (slot = ((GuiInventory)XCarry.mc.field_71462_r).getSlotUnderMouse()) != null && InventoryUtil.getEmptyXCarry() != -1) {
                final int slotNumber = slot.field_75222_d;
                if (slotNumber > 4 && ourBind) {
                    this.taskList.add(new InventoryUtil.Task(slotNumber));
                    this.taskList.add(new InventoryUtil.Task(InventoryUtil.getEmptyXCarry()));
                }
                else if (slotNumber > 4 && this.withShift.getValue()) {
                    boolean isHotBarFull = true;
                    boolean isInvFull = true;
                    for (final int i : InventoryUtil.findEmptySlots(false)) {
                        if (i > 4 && i < 36) {
                            isInvFull = false;
                        }
                        else {
                            if (i <= 35) {
                                continue;
                            }
                            if (i >= 45) {
                                continue;
                            }
                            isHotBarFull = false;
                        }
                    }
                    if (slotNumber > 35 && slotNumber < 45) {
                        if (isInvFull) {
                            this.taskList.add(new InventoryUtil.Task(slotNumber));
                            this.taskList.add(new InventoryUtil.Task(InventoryUtil.getEmptyXCarry()));
                        }
                    }
                    else if (isHotBarFull) {
                        this.taskList.add(new InventoryUtil.Task(slotNumber));
                        this.taskList.add(new InventoryUtil.Task(InventoryUtil.getEmptyXCarry()));
                    }
                }
            }
        }
        if (this.autoDuelOn) {
            this.doneSlots = new ArrayList<Integer>();
            if (InventoryUtil.getEmptyXCarry() == -1 || (this.obbySlotDone && this.slot1done && this.slot2done && this.slot3done)) {
                this.autoDuelOn = false;
            }
            if (this.autoDuelOn) {
                if (!this.obbySlotDone && !XCarry.mc.field_71439_g.field_71071_by.func_70301_a(this.obbySlot.getValue() - 1).field_190928_g) {
                    this.addTasks(36 + this.obbySlot.getValue() - 1);
                }
                this.obbySlotDone = true;
                if (!this.slot1done && !XCarry.mc.field_71439_g.field_71069_bz.field_75151_b.get(this.slot1.getValue()).func_75211_c().field_190928_g) {
                    this.addTasks(this.slot1.getValue());
                }
                this.slot1done = true;
                if (!this.slot2done && !XCarry.mc.field_71439_g.field_71069_bz.field_75151_b.get(this.slot2.getValue()).func_75211_c().field_190928_g) {
                    this.addTasks(this.slot2.getValue());
                }
                this.slot2done = true;
                if (!this.slot3done && !XCarry.mc.field_71439_g.field_71069_bz.field_75151_b.get(this.slot3.getValue()).func_75211_c().field_190928_g) {
                    this.addTasks(this.slot3.getValue());
                }
                this.slot3done = true;
            }
        }
        else {
            this.obbySlotDone = false;
            this.slot1done = false;
            this.slot2done = false;
            this.slot3done = false;
        }
        if (!this.taskList.isEmpty()) {
            for (int j = 0; j < this.tasks.getValue(); ++j) {
                final InventoryUtil.Task task = this.taskList.poll();
                if (task != null) {
                    task.run();
                }
            }
        }
    }
    
    private void addTasks(final int slot) {
        if (InventoryUtil.getEmptyXCarry() != -1) {
            int xcarrySlot = InventoryUtil.getEmptyXCarry();
            if ((this.doneSlots.contains(xcarrySlot) || !InventoryUtil.isSlotEmpty(xcarrySlot)) && (this.doneSlots.contains(++xcarrySlot) || !InventoryUtil.isSlotEmpty(xcarrySlot)) && (this.doneSlots.contains(++xcarrySlot) || !InventoryUtil.isSlotEmpty(xcarrySlot)) && (this.doneSlots.contains(++xcarrySlot) || !InventoryUtil.isSlotEmpty(xcarrySlot))) {
                return;
            }
            if (xcarrySlot > 4) {
                return;
            }
            this.doneSlots.add(xcarrySlot);
            this.taskList.add(new InventoryUtil.Task(slot));
            this.taskList.add(new InventoryUtil.Task(xcarrySlot));
            this.taskList.add(new InventoryUtil.Task());
        }
    }
    
    @Override
    public void onDisable() {
        if (!Feature.fullNullCheck()) {
            if (!this.simpleMode.getValue()) {
                this.closeGui();
                this.close();
            }
            else {
                XCarry.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketCloseWindow(XCarry.mc.field_71439_g.field_71069_bz.field_75152_c));
            }
        }
    }
    
    @Override
    public void onLogout() {
        this.onDisable();
    }
    
    @SubscribeEvent
    public void onCloseGuiScreen(final PacketEvent.Send event) {
        if (this.simpleMode.getValue() && event.getPacket() instanceof CPacketCloseWindow) {
            final CPacketCloseWindow packet = event.getPacket();
            if (packet.field_149556_a == XCarry.mc.field_71439_g.field_71069_bz.field_75152_c) {
                event.setCanceled(true);
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGuiOpen(final GuiOpenEvent event) {
        if (!this.simpleMode.getValue()) {
            if (this.guiCloseGuard) {
                event.setCanceled(true);
            }
            else if (event.getGui() instanceof GuiInventory) {
                event.setGui((GuiScreen)(this.openedGui = this.createGuiWrapper((GuiInventory)event.getGui())));
                this.guiNeedsClose.set(false);
            }
        }
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent event) {
        if (event.getStage() == 2 && event.getSetting() != null && event.getSetting().getFeature() != null && event.getSetting().getFeature().equals(this)) {
            final Setting setting = event.getSetting();
            final String settingname = event.getSetting().getName();
            if (setting.equals(this.simpleMode) && setting.getPlannedValue() != setting.getValue()) {
                this.disable();
            }
            else if (settingname.equalsIgnoreCase("Store")) {
                event.setCanceled(true);
                this.autoDuelOn = !this.autoDuelOn;
                Command.sendMessage("<XCarry> §aAutostoring...");
            }
        }
    }
    
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState() && !(XCarry.mc.field_71462_r instanceof CreepyWareGui) && this.autoStore.getValue().getKey() == Keyboard.getEventKey()) {
            this.autoDuelOn = !this.autoDuelOn;
            Command.sendMessage("<XCarry> §aAutostoring...");
        }
    }
    
    private void close() {
        this.openedGui = null;
        this.guiNeedsClose.set(false);
        this.guiCloseGuard = false;
    }
    
    private void closeGui() {
        if (this.guiNeedsClose.compareAndSet(true, false) && !Feature.fullNullCheck()) {
            this.guiCloseGuard = true;
            XCarry.mc.field_71439_g.func_71053_j();
            if (this.openedGui != null) {
                this.openedGui.func_146281_b();
                this.openedGui = null;
            }
            this.guiCloseGuard = false;
        }
    }
    
    private GuiInventory createGuiWrapper(final GuiInventory gui) {
        try {
            final GuiInventoryWrapper wrapper = new GuiInventoryWrapper();
            ReflectionUtil.copyOf(gui, wrapper);
            return wrapper;
        }
        catch (IllegalAccessException | NoSuchFieldException ex2) {
            final ReflectiveOperationException ex;
            final ReflectiveOperationException e = ex;
            e.printStackTrace();
            return null;
        }
    }
    
    static {
        XCarry.INSTANCE = new XCarry();
    }
    
    private class GuiInventoryWrapper extends GuiInventory
    {
        GuiInventoryWrapper() {
            super((EntityPlayer)Util.mc.field_71439_g);
        }
        
        protected void func_73869_a(final char typedChar, final int keyCode) throws IOException {
            if (XCarry.this.isEnabled() && (keyCode == 1 || this.field_146297_k.field_71474_y.field_151445_Q.isActiveAndMatches(keyCode))) {
                XCarry.this.guiNeedsClose.set(true);
                this.field_146297_k.func_147108_a((GuiScreen)null);
            }
            else {
                super.func_73869_a(typedChar, keyCode);
            }
        }
        
        public void func_146281_b() {
            if (XCarry.this.guiCloseGuard || !XCarry.this.isEnabled()) {
                super.func_146281_b();
            }
        }
    }
}
