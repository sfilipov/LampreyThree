package eel.seprphase2.Persistence;

/**
 * ISaveGame defines the specification for a persistence provider. It contains save, load and path functions
 * @author James Thorne
 */
public interface ISaveGame {
    public void save(String persistData);
    public String load() throws Exception;
    public String filePath();
    
}
