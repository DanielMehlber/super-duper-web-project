package com.esports.manager.util;

import com.esports.manager.util.exceptions.NotImplementedException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionContext;

import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * <p>Custom implementation of the {@link HttpSession} used for unit testing purposes (because apparently, unit testing
 * was not a topic in the year 2000, duh...)</p>
 *
 * <p>Warning: Not all methods are implemented. If a method is not implemented it will throw a {@link NotImplementedException}</p>
 *
 */
public class MockHttpSession implements HttpSession {

    private LocalDateTime lastAccessed;

    private Map<String, Object> attributes;

    public MockHttpSession() {
        attributes = new HashMap<>();
    }

    @Override
    public long getCreationTime() {
        throw new NotImplementedException("getCreationTime");
    }

    @Override
    public String getId() {
        throw new NotImplementedException("getId");
    }

    @Override
    public long getLastAccessedTime() {
        throw new NotImplementedException("getLastAccessedTime");
    }

    @Override
    public ServletContext getServletContext() {
        throw new NotImplementedException("getServletContext");
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        throw new NotImplementedException("setMaxInactiveInterval");
    }

    @Override
    public int getMaxInactiveInterval() {
        throw new NotImplementedException("getMaxInactiveInterval");
    }

    @Override
    public HttpSessionContext getSessionContext() {
        throw new NotImplementedException("getSessionContext");
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public Object getValue(String name) {
        return getAttribute(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return new Vector<>(attributes.keySet()).elements();
    }

    @Override
    public String[] getValueNames() {
        throw new NotImplementedException("getValueNames");
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    @Override
    public void putValue(String name, Object value) {
        setAttribute(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    @Override
    public void removeValue(String name) {
        throw new NotImplementedException("removeValue");
    }

    @Override
    public void invalidate() {
        throw new NotImplementedException("invalidate");
    }

    @Override
    public boolean isNew() {
        throw new NotImplementedException("isNew");
    }
}
