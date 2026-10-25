package dev.axiom.modules;

import dev.axiom.module.Module;
import dev.axiom.render.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

public final class Hitboxes extends Module {
    public Hitboxes() {
        super("Hitboxes", "Visuals", "Entity-Hitboxen anzeigen");
    }

    @Override
    public void onHud(DrawContext g, float tickDelta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null || mc.player == null) return;
        int sw = g.getScaledWindowWidth(), sh = g.getScaledWindowHeight();

        for (Entity e : mc.world.getEntities()) {
            if (e == mc.player || !e.isAlive()) continue;
            if (!(e instanceof LivingEntity)) continue;
            if (e.squaredDistanceTo(mc.player) > 64 * 64) continue;

            Box box = e.getBoundingBox().expand(0.02);
            boolean player = e instanceof PlayerEntity;
            int color = player
                ? RenderUtil.argb(0.95f, 1f, 0.25f, 0.35f)
                : RenderUtil.argb(0.95f, 0.35f, 0.85f, 1f);
            RenderUtil.box2d(g, box, color, sw, sh);
        }
    }
}
