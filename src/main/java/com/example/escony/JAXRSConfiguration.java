package com.example.escony;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api") // Service URL: /api/*
public class JAXRSConfiguration extends Application {
}
