package br.com.senior.server.person;

import br.com.senior.server.crud.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl extends BaseServiceImpl<Person, Long> implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    protected JpaRepository<Person, Long> getRepository() {
        return personRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Person> filter(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> complete(String query) {
        return personRepository.findByDocumentOrFullNameContainingIgnoreCase(query, query);
    }

}
