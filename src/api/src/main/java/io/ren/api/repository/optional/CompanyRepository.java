package io.ren.api.repository.optional;

import io.ren.api.domain.optional.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
