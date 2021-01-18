package com.ruchi.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.fail;

public class ReadFileUtil {

    public static String readFile(String fileName) {
        try {
            Path path = Paths.get("src/test/resources/" +fileName);

            return new String(Files.readAllBytes(path));
        } catch (Exception e) {
            fail("can not read file");
            return null;
        }
    }
}
