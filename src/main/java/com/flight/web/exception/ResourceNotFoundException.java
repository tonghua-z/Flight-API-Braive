package com.flight.web.exception;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;


@Accessors(chain = true)
@Setter
public class ResourceNotFoundException extends RuntimeException {


    private String resourceName;
    private Long id;

    @Override
    public String getMessage() {
        return StringUtils.capitalize(resourceName) + " with id " + id + " is not found.";
    }

}
