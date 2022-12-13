package com.here.common.constant;

public enum ModuleConstant {

    HERE_WEB(1, "here-web"),
    HERE_ADMIN(2, "here-admin");
    private long code;
    private String moduleName;

    private ModuleConstant(long code, String moduleName) {
        this.code = code;
        this.moduleName = moduleName;
    }

    public long getCode() {
        return code;
    }

    public String getModuleName() {
        return moduleName;
    }
}
