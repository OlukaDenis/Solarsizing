package com.dentech.engineeringapp.model;

public class OnMarket {
    private String panels, panelCurrent, batteryCapacity;

    public OnMarket() {
    }

    public OnMarket(String panels, String panelCurrent, String batteryCapacity) {
        this.panels = panels;
        this.panelCurrent = panelCurrent;
        this.batteryCapacity = batteryCapacity;
    }

    public String getPanels() {
        return panels;
    }

    public void setPanels(String panels) {
        this.panels = panels;
    }

    public String getPanelCurrent() {
        return panelCurrent;
    }

    public void setPanelCurrent(String panelCurrent) {
        this.panelCurrent = panelCurrent;
    }

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
