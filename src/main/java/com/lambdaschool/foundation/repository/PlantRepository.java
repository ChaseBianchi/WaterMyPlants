package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.Plant;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD Repository connecting Plant to the rest of the application
 */
public interface PlantRepository
    extends CrudRepository<Plant, Long>
{
}
