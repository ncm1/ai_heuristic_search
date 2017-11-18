/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.junit.Test;

/**
 *
 * @author seobo
 */
public class gridTest {
    
    public gridTest() {

    
    }
    /*
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    */
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
       
    @Test   
    public void testGetWeightByTypeAndCoord() throws Exception{
        assertEquals(Grid.getWeightByTypeAndCoord(0,0,1,1,'a','b'), ((double)0.25 * Math.sqrt(2)* (1 + 0.5) ), 10^-9);
        assertEquals(Grid.getWeightByTypeAndCoord(0,0,0,1,'a','b'), ((double)0.25 * 1 * (1 + 0.5) ), 10^-9);
        assertEquals(Grid.getWeightByTypeAndCoord(0,0,1,0,'2','2'), ((double) (1 + 1) ), 10^-9);
        assertEquals(Grid.getWeightByTypeAndCoord(0,0,1,0,'1','1'), ((double) (1) ), 10^-9);
    }
}
