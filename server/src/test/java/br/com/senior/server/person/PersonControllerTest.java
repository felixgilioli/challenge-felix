package br.com.senior.server.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @Test
    public void getService() {
        assertEquals(personService, personController.getService());
    }

    @Test
    @DisplayName("filter in the service is called")
    void filterInServiceIsCalled() {
        personController.filter(null);
        verify(personService).filter(null);
    }

    @Test
    @DisplayName("complete in the service is called")
    void completeInServiceIsCalled() {
        personController.complete(null);
        verify(personService).complete(null);
    }
}
