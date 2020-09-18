package br.com.senior.server.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    void getRepository() {
        assertEquals(personRepository, personService.getRepository());
    }

    @Test
    @DisplayName("findAllPageable in the repository is called")
    void findAllPageableInRepositoryIsCalled() {
        var page = PageRequest.of(0, 10);
        personService.filter(page);
        verify(personRepository).findAll(page);
    }

    @Test
    @DisplayName("complete in the repository is called")
    void completeInRepositoryIsCalled() {
        var query = "q";
        personService.complete(query);
        verify(personRepository).findByDocumentOrFullNameContainingIgnoreCase(query, query);
        verify(personRepository, never()).findByDocumentOrFullNameContainingIgnoreCase(query, "a");
    }

}
