package com.springsecurityLesson.restControl;

import com.springsecurityLesson.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonRestControl {

    private List<Person> PERSON = Stream.of(
            new Person(1, "Ivan", "Ivanov"),
            new Person(2, "Petr", "Petrov"),
            new Person(3, "Sergey", "Sergeev")
    ).collect(Collectors.toList());

    @GetMapping
    public List<Person> getAll() {
        return PERSON;
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable int id) {
        return PERSON.stream().filter(person -> person.getId()==id).findFirst()
                .orElse(null);
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        this.PERSON.add(person);
        return person;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        this.PERSON.removeIf(person -> person.getId() == id);
    }
}
