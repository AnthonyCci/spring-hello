/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.demohello.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Anthony Flores Boza
 */
public class JSONParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONParser.class);

    public static final String parseObjectToJSON(Object object) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException ex) {
            LOGGER.error("Error parse object", ex);
        }
        return null;
    }

}
