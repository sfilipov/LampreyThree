package eel.seprphase2.Persistence;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eel.seprphase2.Simulator.PhysicalModel;
import eel.seprphase2.Utilities.Percentage;
import java.io.IOException;

/**
 * JSON Serialization
 * @author David
 */
public class Persistence {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     *
     * @param obj
     *
     * @return
     *
     * @throws JsonProcessingException
     */
    public String serialize(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    /**
     *
     * @param representation
     *
     * @return
     *
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public SaveGame deserializeSaveGame(String representation) throws JsonParseException, JsonMappingException,
                                                                      IOException {
        return mapper.readValue(representation, SaveGame.class);
    }

    /**
     *
     * @param representation
     *
     * @return
     *
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public PhysicalModel deserializePhysicalModel(String representation) throws JsonParseException, JsonMappingException,
                                                                                IOException {
        return mapper.readValue(representation, PhysicalModel.class);
    }

    /**
     *
     * @param representation
     *
     * @return
     *
     * @throws IOException
     */
    public Percentage deserializePercentage(String representation) throws IOException {
        return mapper.readValue(representation, Percentage.class);
    }
}
