package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Plant;
import com.lambdaschool.foundation.models.User;

import java.util.List;


/**
 * The Service that works with the Plant Model
 * <p>
 * Note: Emails are added through the add user process
 */
public interface PlantService
{
    /**
     * Returns a list of all users and their emails
     *
     * @return List of users and their emails
     */
    List<Plant> findAll();

    /**
     * Returns the user email combination associated with the given id
     *
     * @param id The primary key (long) of the user email combination you seek
     * @return The user email combination (Plant) you seek
     */
    Plant findPlantById(long id);

    /**
     * Remove the user email combination referenced by the given id
     *
     * @param id The primary key (long) of the user email combination you seek
     */
    void delete(long id);

    /**
     * Replaces the email of the user email combination you seek
     *
     * @param plantid  The primary key (long) of the user email combination you seek
     * @param nickname The new plant nickname (String) for this user plant combination
     * @return The Plant object that you updated including the new nickname
     */
    Plant update(
        long plantid,
        String nickname);

    /**
     * Add a new User Email combination
     *
     * @param userid       the userid of the new user email combination
     * @param nickname The new plant nickname (String) for this user plant combination
     * @return the new user plant combination
     */
    Plant save(
        Plant plant,
        long userid
        );
}
