package mycom.DemoMockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import DemoMockito.Person;
import DemoMockito.PersonDao;
import DemoMockito.PersonService;

/**
 *
 * 
 */
public class PersonServiceTest {
    @Mock
    private PersonDao personDAO;
    private PersonService personService;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        personService = new PersonService(personDAO);
    }
    
    @Test
    public void shouldUpdatePersonName() {
        Person person = new Person(1, "Phillip");
        when(personDAO.fetchPerson(1)).thenReturn(person);
        boolean updated = personService.update(1, "David");
        assertTrue(updated);
        verify(personDAO).fetchPerson(1);
        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personDAO).update(personCaptor.capture());
        Person updatedPerson = personCaptor.getValue();
        assertEquals("David", updatedPerson.getPersonName());
        assertEquals("1", updatedPerson.getPersonID().toString());
        // asserts that during the test, there are no other calls to the mock object.
        verifyNoMoreInteractions(personDAO);
    }
    
    @Test
    public void shouldNotUpdateIfPersonNotFound() {
        when(personDAO.fetchPerson(1)).thenReturn(null);
        boolean updated = personService.update(1, "David");
        assertFalse(updated);
        verify(personDAO).fetchPerson(1);
        verifyZeroInteractions(personDAO);
        verifyNoMoreInteractions(personDAO);
    }
    
    @Test
    public void testOne() {
        // You can mock concrete classes, not just interfaces
        LinkedList mockedList = mock(LinkedList.class);
        
        // stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());
        
        // following prints "first"
        System.out.println(mockedList.get(0));
        
        // following throws runtime exception
        System.out.println(mockedList.get(0));
        
        // following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
        
        // Although it is possible to verify a stubbed invocation, usually it's just redundant
        // If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        // If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
        verify(mockedList).get(0);
        
    }
    
}
