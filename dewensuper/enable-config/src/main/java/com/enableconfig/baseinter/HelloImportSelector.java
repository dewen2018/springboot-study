package com.enableconfig.baseinter;

import com.enableconfig.baseanno.HelloConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class HelloImportSelector implements ImportSelector {

    private final String key = "dewen";

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        if ("dewen".equals(key)) {
            return new String[]{HelloDewenConfiguration.class.getName()};
        }

        return new String[]{HelloConfiguration.class.getName()};
    }
} 