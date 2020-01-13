package com.ren.api.service;

import com.ren.api.dto.TagDto;

import java.util.List;

/**
 * Created by aneagu on 13/01/2020.
 */
public interface TagService {
    List<TagDto> findAllTags();

    List<TagDto> findAllByIds(List<Long> ids);
}
