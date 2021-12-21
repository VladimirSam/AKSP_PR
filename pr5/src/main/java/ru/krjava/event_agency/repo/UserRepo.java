package ru.krjava.event_agency.repo;


import org.springframework.data.repository.CrudRepository;
import ru.krjava.event_agency.domain.User;

public interface UserRepo extends CrudRepository<User,Long> {
    User findByUsername(String name);
}