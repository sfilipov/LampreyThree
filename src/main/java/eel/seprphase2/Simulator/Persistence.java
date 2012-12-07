/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eel.seprphase2.Utilities.Percentage;
import java.io.IOException;

/**
 *
 * @author drm511
 */
public class Persistence {

    private ObjectMapper mapper = new ObjectMapper();

    public String serialize(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    public PhysicalModel deserializePhysicalModel(String representation) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(representation, PhysicalModel.class);
    }

    public Percentage deserializePercentage(String representation) throws IOException {
        return mapper.readValue(representation, Percentage.class);
    }
}
