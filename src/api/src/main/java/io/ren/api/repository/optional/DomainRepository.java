package io.ren.api.repository.optional;


import io.ren.api.domain.optional.Domain;
import org.springframework.data.repository.CrudRepository;

public interface DomainRepository extends CrudRepository<Domain, Long> {
}
