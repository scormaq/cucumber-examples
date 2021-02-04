package com.github.scormaq.utils;

import static io.cucumber.messages.internal.com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

import java.io.File;
import java.net.URL;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtils {

    @SneakyThrows
    public static File getTestResource(String resourcePath) {
        final URL resource = Thread.currentThread().getContextClassLoader()
            .getResource(resourcePath);
        checkArgument(nonNull(resource), String.format("file %s should exist!", resourcePath));
        return new File(resource.toURI());
    }
}
