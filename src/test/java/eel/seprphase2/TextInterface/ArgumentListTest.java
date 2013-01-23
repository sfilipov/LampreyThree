package eel.seprphase2.TextInterface;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class ArgumentListTest {

    @Test
    public void shouldProvideSingleArgument() throws ArgumentException {
        ArgumentList al = new ArgumentList("");
        al.add("10");
        assertEquals(10, al.at(0).asInteger());
    }

    @Test(expected = ArgumentCountException.class)
    public void shouldThrowArgumentCountException() throws ArgumentCountException {
        ArgumentList al = new ArgumentList("");
        al.add("10");
        al.at(1);
    }
}
