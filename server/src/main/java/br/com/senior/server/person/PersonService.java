package br.com.senior.server.person;

import br.com.senior.server.crud.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService extends BaseService<Person, Long> {

    Page<Person> filter(Pageable pageable);

    List<Person> complete(String query);
}
