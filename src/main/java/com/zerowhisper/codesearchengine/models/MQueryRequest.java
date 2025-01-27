package com.zerowhisper.codesearchengine.models;

import java.util.List;

public class MQueryRequest {
    private List<String> tables;
    private List<MFilter>filters;

    public MPagination getPagination() {
        return pagination;
    }

    public void setPagination(MPagination pagination) {
        this.pagination = pagination;
    }

    private MPagination pagination;

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public List<MFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<MFilter> filters) {
        this.filters = filters;
    }
}
