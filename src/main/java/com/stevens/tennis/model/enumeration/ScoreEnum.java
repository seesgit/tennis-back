package com.stevens.tennis.model.enumeration;

public enum ScoreEnum {
    ZERO("0"), QUINZE("15"), TRENTE("30"), QUARANTE("40"), AVANTAGE("A"), WINADVANTAGE("A+");

    public final String label;

    private ScoreEnum(String label) {
        this.label = label;
    }
}
