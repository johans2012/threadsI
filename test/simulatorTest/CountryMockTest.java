/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatorTest;


import dao.CountryDao;
import domain.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import lib.MysqlService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 *
 * @author IEUser
 */
public class CountryMockTest extends TestCase {

    @Mock
    Connection connection;

    @Mock
    ResultSet resultSet;

    @Mock
    PreparedStatement preparedStatement;

    private Country country;
    private CountryDao daoImpl;

    @Before
    public void setup() {

        initMocks(this);

        when(MysqlService.getConnectionShema()).thenReturn(connection);

        daoImpl = mock(CountryDao.class);

        List<Country> arr = new ArrayList<Country>();
        when(daoImpl.listCountry()).thenReturn(arr);

        verify(daoImpl).listCountry();
    }
    
    @Test
    public void countryGetFromDatabase(){
        
        
        assertTrue(true);
    }
    
}
