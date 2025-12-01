package com.mycompany.{{cookiecutter.project_name|lower|replace(' ', '_')|replace('-', '_')}}.{{cookiecutter.object_model}}.dataaccess;

import java.util.List;

public record PaginatedList<T>(List<T> items, int total, String nextToken) {
    
    public PaginatedList(List<T> items, int total) {
        this(items, total, null);
    }
    
    public List<T> getItems() {
        return items;
    }
    
    public int getTotal() {
        return total;
    }
    
    public String getNextToken() {
        return nextToken;
    }
}
