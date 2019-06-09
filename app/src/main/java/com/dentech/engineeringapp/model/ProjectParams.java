package com.dentech.engineeringapp.model;

public class ProjectParams {
    private String psh, dod, sysEff, doa, sysVoltage, safetyFactor;

    public ProjectParams() {
    }

    public ProjectParams(String psh, String dod, String sysEff, String doa, String sysVoltage, String safetyFactor) {
        this.psh = psh;
        this.dod = dod;
        this.sysEff = sysEff;
        this.doa = doa;
        this.sysVoltage = sysVoltage;
        this.safetyFactor = safetyFactor;
    }

    public String getPsh() {
        return psh;
    }

    public void setPsh(String psh) {
        this.psh = psh;
    }

    public String getDod() {
        return dod;
    }

    public void setDod(String dod) {
        this.dod = dod;
    }

    public String getSysEff() {
        return sysEff;
    }

    public void setSysEff(String sysEff) {
        this.sysEff = sysEff;
    }

    public String getDoa() {
        return doa;
    }

    public void setDoa(String doa) {
        this.doa = doa;
    }

    public String getSysVoltage() {
        return sysVoltage;
    }

    public void setSysVoltage(String sysVoltage) {
        this.sysVoltage = sysVoltage;
    }

    public String getSafetyFactor() {
        return safetyFactor;
    }

    public void setSafetyFactor(String safetyFactor) {
        this.safetyFactor = safetyFactor;
    }
}
