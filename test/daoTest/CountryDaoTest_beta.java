package daoTest;

import org.mockito.junit.MockitoRule;
import org.mockito.junit.MockitoJUnit;
import org.junit.Rule;
import dao.CountryDao;
import domain.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 *
 * @author IEUser
 */
public class CountryDaoTest_beta {
    
    @Rule 
    public MockitoRule mockito = MockitoJUnit.rule();
    
    @Mock
    Connection connection;
    
    @Mock
    ResultSet resultSet;
    
    @Mock
    PreparedStatement preparedStatement;
    
    private Country country;
    private CountryDao dao;
    
    @Before
    public void setup() {
        dao = new CountryDao("relational_shema");
        initMocks(this);
        
    }
    
    @Test
    public void testListCountry() {
        List<Country> arr = new ArrayList<Country>();
        when(dao.listCountry()).thenReturn(arr);
    }
    
    @Test
    public void testDontFoundId() {
      
        Country c = dao.findCountryById(0);
        assertEquals(null, c);
    }
    
    @Test
    public void testConnection() {
        // CountryDao.getconnection();        
    }
    
    
}
