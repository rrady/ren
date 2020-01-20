package com.ren.api.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ren.api.domain.Tag;

@Component
public class RenDataSeeder {
    
    private final TagRepository tagRepository;

    @Autowired
    public RenDataSeeder(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    } 

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        seedTags();
    }

    private void seedTags() {
        List<Tag> tags = new ArrayList<Tag>();
        tags.add(new Tag(1L, "java", Collections.emptySet()));
        tags.add(new Tag(2L, "dotnet", Collections.emptySet()));
        tags.add(new Tag(3L, "angular", Collections.emptySet()));
        tags.add(new Tag(4L, "react", Collections.emptySet()));
        tags.add(new Tag(5L, "vuejs", Collections.emptySet()));
        tags.add(new Tag(6L, "spring", Collections.emptySet()));
        tags.add(new Tag(7L, "dotnetcore", Collections.emptySet()));
        tags.add(new Tag(8L, "blazor", Collections.emptySet()));
        tags.add(new Tag(9L, "jsonwebtoken", Collections.emptySet()));
        tags.add(new Tag(10L, "mysql", Collections.emptySet()));

        tagRepository.saveAll(tags);
    }
}