package io.ren.api.repository.optional;

import io.ren.api.domain.optional.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {
}
