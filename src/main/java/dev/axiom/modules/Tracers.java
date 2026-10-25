package dev.axiom.modules;

import dev.axiom.module.Module;
import dev.axiom.render.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public final class Tracers extends Module {
    public Tracers() {
        super("Tracers", "Visuals", "Linien zu Spielern");
    }

    @Override
    public void onHud(DrawContext g, float tickDelta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null || mc.player == null) return;
        int sw = g.getScaledWindowWidth(), sh = g.getScaledWindowHeight();
        float cx = sw / 2f, cy = sh / 2f;
        int col = RenderUtil.argb(0.85f, 1f, 0.3f, 0.35f);

        for (PlayerEntity e : mc.world.getPlayers()) {
            if (e == mc.player) continue;
            if (e.squaredDistanceTo(mc.player) > 128 * 128) continue;
            Vec3d to = e.getBoundingBox().getCenter();
            float[] s = RenderUtil.worldToScreen(to, sw, sh);
            if (s == null) continue;
            RenderUtil.line(g, cx, cy, s[0], s[1], col);
        }
    }
}
