package org.wfswarm.rhsummit;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Ken Finnigan
 */
@ApplicationScoped
public class MessageGenerator {
    private static final String template = "Hello, %s!";

    public String getMessage(String name) {
        return String.format(template, name);
    }
}
