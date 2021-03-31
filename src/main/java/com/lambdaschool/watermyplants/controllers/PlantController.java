package com.lambdaschool.watermyplants.controllers;

import com.lambdaschool.watermyplants.models.Plant;
import com.lambdaschool.watermyplants.services.PlantService;
import com.lambdaschool.watermyplants.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * The entry point for client to access user, email combinations
 */
@RestController
@RequestMapping("/api/plants")
public class PlantController
{
    /**
     * Using the Plant service to process user, email combinations data
     */
    @Autowired
    PlantService plantService;

    @Autowired
    UserService userService;

    /**
     * List of all users emails
     * <br>Example: <a href="http://localhost:2019/useremails/useremails">http://localhost:2019/useremails/useremails</a>
     *
     * @return JSON list of all users emails
     */
    @GetMapping(value = "/plants",
        produces = "application/json")
    public ResponseEntity<?> listAllPlants()
    {
        List<Plant> allUserPlants = plantService.findAll();
        return new ResponseEntity<>(allUserPlants,
            HttpStatus.OK);
    }

    /**
     * Return the user email combination referenced by the given primary key
     * <br>Example: <a href="http://localhost:2019/useremails/useremail/8">http://localhost:2019/useremails/useremail/8</a>
     *
     * @param plantid the primary key of the user email combination you seek
     * @return JSON object of the user email combination you seek with a status of OK
     */
    @GetMapping(value = "/plant/{plantid}",
        produces = "application/json")
    public ResponseEntity<?> getUserPlantById(
        @PathVariable
            Long plantid)
    {
        Plant ue = plantService.findPlantById(plantid);
        return new ResponseEntity<>(ue,
            HttpStatus.OK);
    }

    /**
     * Removes the given user email combination
     * <br>Example: <a href="http://localhost:2019/useremails/useremail/8">http://localhost:2019/useremails/useremail/8</a>
     *
     * @param plantid the primary key of the user email combination you wish to remove
     * @return Status of OK
     */
    @DeleteMapping(value = "/plant/{plantid}")
    public ResponseEntity<?> deleteUserPlantById(
        @PathVariable
            long plantid)
    {
        plantService.delete(plantid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Change the email associated with the given user email combination
     * <br>Example: <a href="http://localhost:2019/useremails/useremail/9/email/favbun@hops.local">http://localhost:2019/useremails/useremail/9/email/favbun@hops.local</a>
     *
     * @param plantid  The primary key of the user email combination you wish to change
//     * @param nickname The new email (String)
     * @return Status of OK
     */
    @PutMapping(value = "/plant/{plantid}", consumes = "application/json")
    public ResponseEntity<?> updateUserPlant(
            @PathVariable
            long plantid,
            @RequestBody
            Plant plant
        )
    {

        plantService.update(plant, plantid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Adds a new user email combination
     *
     * @param userid       the user id of the new user plant combination
//     * @param nickname the plant nickname of the new user plant combination
     * @return A location header with the URI to the newly created user plant combination and a status of CREATED
     * @throws URISyntaxException Exception if something does not work in creating the location header
//     * @see PlantService#save(long, String) PlantService.save(long, String)
     */

    @PostMapping(value = "/plant/{userid}", consumes = "application/json")
    public ResponseEntity<?> addNewUserPlant(
            @Valid
            @RequestBody Plant newPlant,
            @PathVariable long userid

    ) throws
                                 URISyntaxException
    {
        plantService.save(newPlant, userid);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserPlantURI = ServletUriComponentsBuilder.fromCurrentServletMapping()
            .path("/plants/plant/{plantid}")
            .buildAndExpand(newPlant.getPlantid())
            .toUri();
        responseHeaders.setLocation(newUserPlantURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }
}
