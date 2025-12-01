package com.mycompany.{{cookiecutter.project_name|lower|replace(' ', '_')|replace('-', '_')}}.{{cookiecutter.object_model}}.model;

public sealed interface ApiResponse permits SuccessResponse, ErrorResponse {
    int statusCode();
    String body();
}
