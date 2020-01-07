package io.ren.api.repository.optional;

import io.ren.api.domain.optional.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
