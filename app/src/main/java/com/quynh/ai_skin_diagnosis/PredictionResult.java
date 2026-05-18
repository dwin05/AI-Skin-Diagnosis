package com.quynh.ai_skin_diagnosis;
public class PredictionResult {
    private String label;
    private float benignPercent;
    private float malignantPercent;
    public PredictionResult(String label, float benignPercent, float malignantPercent) {
        this.label = label;
        this.benignPercent = benignPercent;
        this.malignantPercent = malignantPercent;
    }
    public String getLabel() {
        return label;
    }
    public float getBenignPercent() {
        return benignPercent;
    }
    public float getMalignantPercent() {
        return malignantPercent;
    }
}