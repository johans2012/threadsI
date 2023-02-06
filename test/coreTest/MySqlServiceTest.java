/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreTest;

import lib.MysqlService;
import static junit.framework.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class MySqlServiceTest {

    MysqlService service;

    @Before
    protected void init() {
     
    }

    @Test
    protected void chechLengthRegisters() {
        assertNotNull(this.service);
    }
}
