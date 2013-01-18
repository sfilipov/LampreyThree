/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java.eel.seprphase2.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author James
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
{eel.seprphase2.Persistence.SaveGameFileTest.class, eel.seprphase2.Persistence.PersistenceTest.class})
public class PersistenceSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
