// 
// Decompiled by Procyon v0.5.36
// 

package dev.fxcte.creepyware.features.modules.combat;

import net.minecraft.util.EnumHand;
import dev.fxcte.creepyware.features.command.Command;
import net.minecraft.block.BlockEnderChest;
import dev.fxcte.creepyware.util.InventoryUtil;
import net.minecraft.block.BlockObsidian;
import dev.fxcte.creepyware.util.BlockUtil;
import java.util.Iterator;
import net.minecraft.init.Blocks;
import dev.fxcte.creepyware.util.Util;
import dev.fxcte.creepyware.util.creepywareutils.CreepyWareUtils;
import com.mojang.realmsclient.gui.ChatFormatting;
import dev.fxcte.creepyware.CreepyWare;
import net.minecraft.entity.Entity;
import dev.fxcte.creepyware.util.EntityUtil;
import java.util.HashMap;
import java.util.HashSet;
import net.minecraft.util.math.BlockPos;
import java.util.Map;
import net.minecraft.util.math.Vec3d;
import java.util.Set;
import dev.fxcte.creepyware.util.Timer;
import dev.fxcte.creepyware.features.setting.Setting;
import dev.fxcte.creepyware.features.modules.Module;

public class Surround extends Module
{
    public static boolean isPlacing;
    private final Setting<Integer> blocksPerTick;
    private final Setting<Integer> delay;
    private final Setting<Boolean> noGhost;
    private final Setting<Boolean> center;
    private final Setting<Boolean> rotate;
    private final Timer timer;
    private final Timer retryTimer;
    private final Set<Vec3d> extendingBlocks;
    private final Map<BlockPos, Integer> retries;
    private int isSafe;
    private BlockPos startPos;
    private boolean didPlace;
    private boolean switchedItem;
    private int lastHotbarSlot;
    private boolean isSneaking;
    private int placements;
    private int extenders;
    private int obbySlot;
    private boolean offHand;
    
    public Surround() {
        super("Surround", "Surrounds you with Obsidian", Category.COMBAT, true, false, false);
        this.blocksPerTick = (Setting<Integer>)this.register(new Setting("BlocksPerTick", (T)12, (T)1, (T)20));
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)0, (T)0, (T)250));
        this.noGhost = (Setting<Boolean>)this.register(new Setting("Speed", "PacketPlace", 0.0, 0.0, (T)false, 0));
        this.center = (Setting<Boolean>)this.register(new Setting("Speed", "TPCenter", 0.0, 0.0, (T)false, 0));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Speed", "Rotate", 0.0, 0.0, (T)true, 0));
        this.timer = new Timer();
        this.retryTimer = new Timer();
        this.extendingBlocks = new HashSet<Vec3d>();
        this.retries = new HashMap<BlockPos, Integer>();
        this.didPlace = false;
        this.placements = 0;
        this.extenders = 1;
        this.obbySlot = -1;
        this.offHand = false;
    }
    
    @Override
    public void onEnable() {
        if (fullNullCheck()) {
            this.disable();
        }
        this.lastHotbarSlot = Surround.mc.field_71439_g.field_71071_by.field_70461_c;
        this.startPos = EntityUtil.getRoundedBlockPos((Entity)Surround.mc.field_71439_g);
        if (this.center.getValue()) {
            CreepyWare.positionManager.setPositionPacket(this.startPos.func_177958_n() + 0.5, this.startPos.func_177956_o(), this.startPos.func_177952_p() + 0.5, true, true, true);
        }
        this.retries.clear();
        this.retryTimer.reset();
    }
    
    @Override
    public void onTick() {
        this.doFeetPlace();
    }
    
    @Override
    public void onDisable() {
        if (nullCheck()) {
            return;
        }
        Surround.isPlacing = false;
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
    }
    
    @Override
    public String getDisplayInfo() {
        switch (this.isSafe) {
            case 0: {
                return ChatFormatting.RED + "Unsafe";
            }
            case 1: {
                return ChatFormatting.YELLOW + "Safe";
            }
            default: {
                return ChatFormatting.GREEN + "Safe";
            }
        }
    }
    
    private void doFeetPlace() {
        if (this.check()) {
            return;
        }
        if (!CreepyWareUtils.isSafe((Entity)Surround.mc.field_71439_g, 0, true)) {
            this.isSafe = 0;
            this.placeBlocks(Surround.mc.field_71439_g.func_174791_d(), CreepyWareUtils.getUnsafeBlockArray((Entity)Surround.mc.field_71439_g, 0, true), true, false, false);
        }
        else if (!CreepyWareUtils.isSafe((Entity)Surround.mc.field_71439_g, -1, false)) {
            this.isSafe = 1;
            this.placeBlocks(Surround.mc.field_71439_g.func_174791_d(), CreepyWareUtils.getUnsafeBlockArray((Entity)Surround.mc.field_71439_g, -1, false), false, false, true);
        }
        else {
            this.isSafe = 3;
            if (Util.mc.field_71441_e.func_180495_p(EntityUtil.getRoundedBlockPos((Entity)Util.mc.field_71439_g)).func_177230_c().equals(Blocks.field_150477_bB) && Util.mc.field_71439_g.field_70163_u != EntityUtil.getRoundedBlockPos((Entity)Util.mc.field_71439_g).func_177956_o()) {
                this.placeBlocks(Surround.mc.field_71439_g.func_174791_d(), CreepyWareUtils.getUnsafeBlockArray((Entity)Surround.mc.field_71439_g, 1, false), false, false, true);
            }
            else {
                this.isSafe = 4;
            }
        }
        this.processExtendingBlocks();
        if (this.didPlace) {
            this.timer.reset();
        }
    }
    
    private void processExtendingBlocks() {
        if (this.extendingBlocks.size() == 2 && this.extenders < 1) {
            final Vec3d[] array = new Vec3d[2];
            int i = 0;
            final Iterator<Vec3d> iterator = this.extendingBlocks.iterator();
            while (iterator.hasNext()) {
                final Vec3d vec3d = array[i] = iterator.next();
                ++i;
            }
            final int placementsBefore = this.placements;
            if (this.areClose(array) != null) {
                this.placeBlocks(this.areClose(array), CreepyWareUtils.getUnsafeBlockArrayFromVec3d(this.areClose(array), 0, true), true, false, true);
            }
            if (placementsBefore < this.placements) {
                this.extendingBlocks.clear();
            }
        }
        else if (this.extendingBlocks.size() > 2 || this.extenders >= 1) {
            this.extendingBlocks.clear();
        }
    }
    
    private Vec3d areClose(final Vec3d[] vec3ds) {
        int matches = 0;
        for (final Vec3d vec3d : vec3ds) {
            for (final Vec3d pos : CreepyWareUtils.getUnsafeBlockArray((Entity)Surround.mc.field_71439_g, 0, true)) {
                if (vec3d.equals((Object)pos)) {
                    ++matches;
                }
            }
        }
        if (matches == 2) {
            return Surround.mc.field_71439_g.func_174791_d().func_178787_e(vec3ds[0].func_178787_e(vec3ds[1]));
        }
        return null;
    }
    
    private boolean placeBlocks(final Vec3d pos, final Vec3d[] vec3ds, final boolean hasHelpingBlocks, final boolean isHelping, final boolean isExtending) {
        boolean gotHelp = true;
        for (final Vec3d vec3d : vec3ds) {
            gotHelp = true;
            final BlockPos position = new BlockPos(pos).func_177963_a(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
            switch (BlockUtil.isPositionPlaceable(position, false)) {
                case 1: {
                    if (this.retries.get(position) == null || this.retries.get(position) < 4) {
                        this.placeBlock(position);
                        this.retries.put(position, (this.retries.get(position) == null) ? 1 : (this.retries.get(position) + 1));
                        this.retryTimer.reset();
                        break;
                    }
                    if (CreepyWare.speedManager.getSpeedKpH() != 0.0 || isExtending) {
                        break;
                    }
                    if (this.extenders >= 1) {
                        break;
                    }
                    this.placeBlocks(Surround.mc.field_71439_g.func_174791_d().func_178787_e(vec3d), CreepyWareUtils.getUnsafeBlockArrayFromVec3d(Surround.mc.field_71439_g.func_174791_d().func_178787_e(vec3d), 0, true), hasHelpingBlocks, false, true);
                    this.extendingBlocks.add(vec3d);
                    ++this.extenders;
                    break;
                }
                case 2: {
                    if (!hasHelpingBlocks) {
                        break;
                    }
                    gotHelp = this.placeBlocks(pos, BlockUtil.getHelpingBlocks(vec3d), false, true, true);
                }
                case 3: {
                    if (gotHelp) {
                        this.placeBlock(position);
                    }
                    if (!isHelping) {
                        break;
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean check() {
        if (nullCheck()) {
            return true;
        }
        final int obbySlot = InventoryUtil.findHotbarBlock(BlockObsidian.class);
        final int eChestSot = InventoryUtil.findHotbarBlock(BlockEnderChest.class);
        if (obbySlot == -1 && eChestSot == -1) {
            this.toggle();
        }
        this.offHand = InventoryUtil.isBlock(Surround.mc.field_71439_g.func_184592_cb().func_77973_b(), BlockObsidian.class);
        Surround.isPlacing = false;
        this.didPlace = false;
        this.extenders = 1;
        this.placements = 0;
        this.obbySlot = InventoryUtil.findHotbarBlock(BlockObsidian.class);
        final int echestSlot = InventoryUtil.findHotbarBlock(BlockEnderChest.class);
        if (this.isOff()) {
            return true;
        }
        if (this.retryTimer.passedMs(2500L)) {
            this.retries.clear();
            this.retryTimer.reset();
        }
        if (this.obbySlot == -1 && !this.offHand && echestSlot == -1) {
            Command.sendMessage("<" + this.getDisplayName() + "> " + ChatFormatting.RED + "No Obsidian in hotbar disabling...");
            this.disable();
            return true;
        }
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        if (Surround.mc.field_71439_g.field_71071_by.field_70461_c != this.lastHotbarSlot && Surround.mc.field_71439_g.field_71071_by.field_70461_c != this.obbySlot && Surround.mc.field_71439_g.field_71071_by.field_70461_c != echestSlot) {
            this.lastHotbarSlot = Surround.mc.field_71439_g.field_71071_by.field_70461_c;
        }
        if (!this.startPos.equals((Object)EntityUtil.getRoundedBlockPos((Entity)Surround.mc.field_71439_g))) {
            this.disable();
            return true;
        }
        return !this.timer.passedMs(this.delay.getValue());
    }
    
    private void placeBlock(final BlockPos pos) {
        if (this.placements < this.blocksPerTick.getValue()) {
            final int originalSlot = Surround.mc.field_71439_g.field_71071_by.field_70461_c;
            final int obbySlot = InventoryUtil.findHotbarBlock(BlockObsidian.class);
            final int eChestSot = InventoryUtil.findHotbarBlock(BlockEnderChest.class);
            if (obbySlot == -1 && eChestSot == -1) {
                this.toggle();
            }
            Surround.isPlacing = true;
            Surround.mc.field_71439_g.field_71071_by.field_70461_c = ((obbySlot == -1) ? eChestSot : obbySlot);
            Surround.mc.field_71442_b.func_78765_e();
            this.isSneaking = BlockUtil.placeBlock(pos, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.rotate.getValue(), this.noGhost.getValue(), this.isSneaking);
            Surround.mc.field_71439_g.field_71071_by.field_70461_c = originalSlot;
            Surround.mc.field_71442_b.func_78765_e();
            this.didPlace = true;
            ++this.placements;
        }
    }
    
    static {
        Surround.isPlacing = false;
    }
}
