package dev.axiom.modules;

import dev.axiom.module.Module;
import dev.axiom.render.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public final class BlockOverlay extends Module {
    public BlockOverlay() {
        super("BlockOverlay", "Visuals", "Anvisierten Block highlighten");
    }

    @Override
    public void onHud(DrawContext g, float tickDelta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null || mc.player == null) return;
        if (mc.crosshairTarget == null || mc.crosshairTarget.getType() != HitResult.Type.BLOCK) return;

        BlockHitResult hit = (BlockHitResult) mc.crosshairTarget;
        BlockPos pos = hit.getBlockPos();
        if (mc.world.getBlockState(pos).isAir()) return;

        int sw = g.getScaledWindowWidth(), sh = g.getScaledWindowHeight();
        RenderUtil.box2d(g, new Box(pos).expand(0.002),
            RenderUtil.argb(1f, 0.75f, 0.55f, 1f), sw, sh);
    }
}
