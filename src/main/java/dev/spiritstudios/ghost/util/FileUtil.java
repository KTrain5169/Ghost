package dev.spiritstudios.ghost.util;

import com.google.common.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileUtil {
    public static String getResource(String fileName, ClassLoader classLoader) {
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            if (inputStream == null) return null;
            return new String(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Path> getFiles(String basePath) {
        try (InputStream stream = getContextClassLoader().getResourceAsStream(basePath)) {
            return Stream.of(new String(stream.readAllBytes()).split("\n")).map(Path::of).toList();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private FileUtil() {
        Util.utilError();
    }
}
