/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Persistence;

import eel.seprphase2.Simulator.PhysicalModel.PhysicalModel;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eel.seprphase2.Persistence.SaveGame;
import eel.seprphase2.Simulator.FailureModel.FailureModel;
import eel.seprphase2.Utilities.Percentage;
import java.io.IOException;

/**
 *
 * @author drm511
 */
public class Persistence {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     *
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public String serialize(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    /**
     *
     * @param representation
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public SaveGame deserializeSaveGame(String representation) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(representation, SaveGame.class);
    }
    
    /**
     *
     * @param representation
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public FailureModel deserializeFailureModel(String representation) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(representation, FailureModel.class);
    }

    
    /**
     *
     * @param representation
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public PhysicalModel deserializePhysicalModel(String representation) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(representation, PhysicalModel.class);
    }

    /**
     *
     * @param representation
     * @return
     * @throws IOException
     */
    public Percentage deserializePercentage(String representation) throws IOException {
        return mapper.readValue(representation, Percentage.class);
    }
}
