package br.com.senior.server.person;

import br.com.senior.server.crud.BaseController;
import br.com.senior.server.crud.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController extends BaseController<Person, Long> {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Override
    protected BaseService<Person, Long> getService() {
        return personService;
    }

    @GetMapping("/filter")
    public Page<Person> filter(@PageableDefault(sort = "id") Pageable pageable) {
        return personService.filter(pageable);
    }

    @GetMapping("/complete")
    public List<Person> complete(@RequestParam("q") String query) {
        return personService.complete(query);
    }
}
