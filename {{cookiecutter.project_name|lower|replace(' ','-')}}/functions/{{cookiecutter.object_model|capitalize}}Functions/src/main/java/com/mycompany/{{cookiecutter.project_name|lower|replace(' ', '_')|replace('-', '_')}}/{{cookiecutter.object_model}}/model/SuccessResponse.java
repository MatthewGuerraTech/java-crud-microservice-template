package com.mycompany.{{cookiecutter.project_name|lower|replace(' ', '_')|replace('-', '_')}}.{{cookiecutter.object_model}}.model;

public record SuccessResponse(int statusCode, String body) implements ApiResponse {
    
    public static SuccessResponse ok(String body) {
        return new SuccessResponse(200, body);
    }
    
    public static SuccessResponse created(String body) {
        return new SuccessResponse(201, body);
    }
}
