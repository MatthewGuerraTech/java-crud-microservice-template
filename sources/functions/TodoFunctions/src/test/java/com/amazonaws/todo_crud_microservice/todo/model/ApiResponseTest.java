/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package com.amazonaws.todo_crud_microservice.todo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    @Test
    void successResponseOkReturns200StatusCode() {
        SuccessResponse response = SuccessResponse.ok("{\"id\":\"123\"}");
        
        assertEquals(200, response.statusCode());
        assertEquals("{\"id\":\"123\"}", response.body());
    }

    @Test
    void successResponseCreatedReturns201StatusCode() {
        SuccessResponse response = SuccessResponse.created("{\"id\":\"456\"}");
        
        assertEquals(201, response.statusCode());
        assertEquals("{\"id\":\"456\"}", response.body());
    }

    @Test
    void errorResponseBadRequestReturns400StatusCode() {
        ErrorResponse response = ErrorResponse.badRequest("Invalid input");
        
        assertEquals(400, response.statusCode());
        assertEquals("Bad request", response.error());
        assertEquals("Invalid input", response.message());
        assertTrue(response.body().contains("Bad request"));
        assertTrue(response.body().contains("Invalid input"));
    }

    @Test
    void errorResponseNotFoundReturns404StatusCode() {
        ErrorResponse response = ErrorResponse.notFound("Todo not found");
        
        assertEquals(404, response.statusCode());
        assertEquals("Not found", response.error());
        assertEquals("Todo not found", response.message());
        assertTrue(response.body().contains("Not found"));
        assertTrue(response.body().contains("Todo not found"));
    }

    @Test
    void errorResponseInternalErrorReturns500StatusCode() {
        ErrorResponse response = ErrorResponse.internalError();
        
        assertEquals(500, response.statusCode());
        assertEquals("Internal Server Error", response.error());
        assertEquals("Unexpected error", response.message());
        assertTrue(response.body().contains("Internal Server Error"));
        assertTrue(response.body().contains("Unexpected error"));
    }

    @Test
    void successResponseImplementsApiResponse() {
        ApiResponse response = SuccessResponse.ok("test");
        
        assertTrue(response instanceof ApiResponse);
        assertTrue(response instanceof SuccessResponse);
    }

    @Test
    void errorResponseImplementsApiResponse() {
        ApiResponse response = ErrorResponse.badRequest("test");
        
        assertTrue(response instanceof ApiResponse);
        assertTrue(response instanceof ErrorResponse);
    }

    @Test
    void errorResponseBodyUsesTextBlockFormat() {
        ErrorResponse response = ErrorResponse.badRequest("test message");
        String body = response.body();
        
        assertTrue(body.startsWith("{\"error\":"));
        assertTrue(body.contains("\"message\":"));
    }
}
