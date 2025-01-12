package com.interface21.webmvc.servlet.mvc.mapping;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import jakarta.servlet.http.HttpServletRequest;

public class HandlerMappings {

    private final Set<HandlerMapping> mappings;

    public HandlerMappings(HandlerMapping... handlerMappings) {
        this.mappings = new HashSet<>(List.of(handlerMappings));
    }

    public void initialize() {
        mappings.forEach(HandlerMapping::initialize);
    }

    public Optional<Object> getHandler(HttpServletRequest request) throws Exception {
        for (HandlerMapping mapping : mappings) {
            Object handler = mapping.getHandler(request);
            if (handler != null) {
                return Optional.of(handler);
            }
        }
        return Optional.empty();
    }

    public void addHandlerMapping(AnnotationHandlerMapping mapping) {
        mappings.add(mapping);
    }
}
