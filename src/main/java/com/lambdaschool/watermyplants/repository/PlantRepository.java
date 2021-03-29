package com.lambdaschool.watermyplants.repository;

import com.lambdaschool.watermyplants.models.Plant;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD Repository connecting Plant to the rest of the application
 */
public interface PlantRepository
    extends CrudRepository<Plant, Long>
{
}
