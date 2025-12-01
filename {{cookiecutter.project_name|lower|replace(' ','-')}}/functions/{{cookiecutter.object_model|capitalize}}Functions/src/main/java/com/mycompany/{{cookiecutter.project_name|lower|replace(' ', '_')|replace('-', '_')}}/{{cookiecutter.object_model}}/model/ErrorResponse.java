package com.mycompany.{{cookiecutter.project_name|lower|replace(' ', '_')|replace('-', '_')}}.{{cookiecutter.object_model}}.model;

public record ErrorResponse(int statusCode, String error, String message) implements ApiResponse {
    
    @Override
    public String body() {
        return """
                {"error":"%s", "message":"%s"}""".formatted(error, message);
    }
    
    public static ErrorResponse badRequest(String message) {
        return new ErrorResponse(400, "Bad request", message);
    }
    
    public static ErrorResponse notFound(String message) {
        return new ErrorResponse(404, "Not found", message);
    }
    
    public static ErrorResponse internalError() {
        return new ErrorResponse(500, "Internal Server Error", "Unexpected error");
    }
}
