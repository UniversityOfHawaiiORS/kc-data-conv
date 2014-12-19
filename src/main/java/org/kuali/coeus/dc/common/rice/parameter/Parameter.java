package org.kuali.coeus.dc.common.rice.parameter;

public class Parameter {

    private ParameterKey parameterKey;

    private String value;
    private String description;
    private String parameterTypeCode;
    private String evaluationOperatorCode;

    public ParameterKey getParameterKey() {
        return parameterKey;
    }

    public void setParameterKey(ParameterKey parameterKey) {
        this.parameterKey = parameterKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParameterTypeCode() {
        return parameterTypeCode;
    }

    public void setParameterTypeCode(String parameterTypeCode) {
        this.parameterTypeCode = parameterTypeCode;
    }

    public String getEvaluationOperatorCode() {
        return evaluationOperatorCode;
    }

    public void setEvaluationOperatorCode(String evaluationOperatorCode) {
        this.evaluationOperatorCode = evaluationOperatorCode;
    }
}
