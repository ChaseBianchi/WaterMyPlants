package com.lambdaschool.watermyplants.services;

import com.lambdaschool.watermyplants.exceptions.ResourceNotFoundException;
import com.lambdaschool.watermyplants.models.Plant;
import com.lambdaschool.watermyplants.models.User;
import com.lambdaschool.watermyplants.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the PlantService Interface
 */
@Transactional
@Service(value = "plantService")
public class PlantServiceImpl
    implements PlantService
{
    /**
     * Connects this service to the Plant model
     */
    @Autowired
    private PlantRepository plantRepository;

    /**
     * Connects this servive to the User Service
     */
    @Autowired
    private UserService userService;

    @Autowired
    private HelperFunctions helperFunctions;

    @Override
    public List<Plant> findAll()
    {
        List<Plant> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        plantRepository.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Plant findPlantById(long id)
    {
        return plantRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Plant with id " + id + " Not Found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (plantRepository.findById(id)
            .isPresent())
        {
            if (helperFunctions.isAuthorizedToMakeChange(plantRepository.findById(id)
                .get()
                .getUser()
                .getUsername()))
            {
                plantRepository.deleteById(id);
            }
        } else
        {
            throw new ResourceNotFoundException("Plant with id " + id + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Plant update(Plant plantupdate,
        long plantid)
    {
        if (plantRepository.findById(plantid)
            .isPresent())
        {
            if (helperFunctions.isAuthorizedToMakeChange(plantRepository.findById(plantid)
                .get()
                .getUser()
                .getUsername()))
            {
                Plant plant = findPlantById(plantid);
                if(plantupdate.getNickname()!=null) {
                    plant.setNickname(plantupdate.getNickname());
                }
                if(plantupdate.getSpecies()!=null) {
                    plant.setSpecies(plantupdate.getSpecies());
                }
                if(plantupdate.getH2ofrequency()!=null) {
                    plant.setH2ofrequency(plantupdate.getH2ofrequency());
                }
                if(plantupdate.getUser()!=null) {
                    plant.setUser(plantupdate.getUser());
                }
                return plantRepository.save(plant);
            } else
            {
                // note we should never get to this line but is needed for the compiler
                // to recognize that this exception can be thrown
                throw new ResourceNotFoundException("This user is not authorized to make change");
            }
        } else
        {
            throw new ResourceNotFoundException("Plant with id " + plantid + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Plant save(
            Plant plant,
            long userid
    )
    {
        User currentUser = userService.findUserById(userid);

        if (helperFunctions.isAuthorizedToMakeChange(currentUser.getUsername()))
        {
            Plant newUserPlant = new Plant(plant.getNickname(), plant.getSpecies(), plant.getH2ofrequency(), currentUser);
            return plantRepository.save(newUserPlant);
        } else
        {
            // note we should never get to this line but is needed for the compiler
            // to recognize that this exception can be thrown
            throw new ResourceNotFoundException("This user is not authorized to make change");
        }
    }
}
