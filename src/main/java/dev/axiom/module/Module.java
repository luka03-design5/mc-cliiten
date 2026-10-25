package dev.axiom.module;

import net.minecraft.client.gui.DrawContext;

public abstract class Module {
    private final String name;
    private final String category;
    private final String desc;
    private boolean enabled;

    protected Module(String name, String category, String desc) {
        this.name = name;
        this.category = category;
        this.desc = desc;
    }

    public String name() { return name; }
    public String category() { return category; }
    public String desc() { return desc; }
    public boolean enabled() { return enabled; }

    public void setEnabled(boolean on) {
        if (enabled == on) return;
        enabled = on;
        if (on) onEnable(); else onDisable();
        ModuleManager.save();
    }

    public void toggle() { setEnabled(!enabled); }

    protected void onEnable() {}
    protected void onDisable() {}

    public void onHud(DrawContext g, float tickDelta) {}
    public void onTick() {}
}
