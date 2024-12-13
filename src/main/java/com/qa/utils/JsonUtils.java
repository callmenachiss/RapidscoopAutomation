package com.qa.utils;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;

import static org.apache.logging.log4j.util.Strings.isNotEmpty;

public class JsonUtils {

    public static final Logger LOGGER = LogManager.getLogger();

    public static <T> T convert(String fileName, Class<T> clazz) {
        String fileContent = readFile(fileName);
        try {
            if (isNotEmpty(fileContent)) {
                return new ObjectMapper().readValue(fileContent, clazz);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            LOGGER.error("Error while converting string to pojo object: " + fileName);
        }
        return null;
    }

    public static String readFile(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(Constants.File.TEST_RESOURCES_PATH + fileName);) {
            StringBuilder content = new StringBuilder();
            int data;
            while ((data = fileInputStream.read()) != -1) {
                content.append((char) data);
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Error while reading file: " + fileName);
        }
        return null;
    }
}
