package com.example.jobis.domain.recruit.domain.repository;

import com.example.jobis.domain.recruit.domain.TechList;
import com.example.jobis.domain.recruit.domain.TechListId;
import org.springframework.data.repository.CrudRepository;

public interface TechListRepository extends CrudRepository<TechList, TechListId> {
}
