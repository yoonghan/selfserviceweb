package com.jaring.jom.rest.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.jaring.jom.rest.interceptor.ContentHeaderModifier;
import com.jaring.jom.rest.services.HtmlRenderer;

public class RestApplication extends Application{
    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> classes = new HashSet<Class<?>>();

    public RestApplication() {
        this.singletons.add(new HtmlRenderer());
        this.singletons.add(new ContentHeaderModifier());
    }

    public Set<Class<?>> getClasses()
    {
        return this.classes;
    }

    public Set<Object> getSingletons()
    {
        return this.singletons;
    }
}
