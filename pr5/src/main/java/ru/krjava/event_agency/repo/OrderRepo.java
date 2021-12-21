package ru.krjava.event_agency.repo;

import org.springframework.data.repository.CrudRepository;
import ru.krjava.event_agency.domain.Order;


public interface OrderRepo extends CrudRepository<Order, Long> {
    Order findOrdersById(Long id);
    Iterable<Order> findAllByUserid(Long userid);
}