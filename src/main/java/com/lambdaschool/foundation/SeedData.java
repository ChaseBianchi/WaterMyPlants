package com.lambdaschool.foundation;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.lambdaschool.foundation.models.Role;
import com.lambdaschool.foundation.models.User;
import com.lambdaschool.foundation.models.UserRoles;
import com.lambdaschool.foundation.models.Plant;
import com.lambdaschool.foundation.services.RoleService;
import com.lambdaschool.foundation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@ConditionalOnProperty(
    prefix = "command.line.runner",
    value = "enabled",
    havingValue = "true",
    matchIfMissing = true)
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        userService.deleteAll();
        roleService.deleteAll();
        Role r1 = new Role("admin");


        r1 = roleService.save(r1);

        // admin, data, user
        User u1 = new User("admin",
            "password",
                "100-011-1010");
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        u1.getPlants()
            .add(new Plant(
                    "Piney",
                    "Pine Tree",
                    "monthly",
                    u1
            ));
        u1.getPlants()
                .add(new Plant(
                        "Prickles",
                        "Cactus",
                        "never",
                        u1
                ));

        userService.save(u1);

        // data, user
        User u2 = new User("Jalpa",
            "1234567",
            "555-561-6628");
        u2.getRoles()
            .add(new UserRoles(u2,
                r1));
        u2.getPlants()
                .add(new Plant(
                        "Herman",
                        "Worm Wood",
                        "daily",
                        u1
                ));
        u2.getPlants()
                .add(new Plant(
                        "Judith",
                        "Juniper Tree",
                        "weekly",
                        u1
                ));
        u2.getPlants()
                .add(new Plant(
                        "Red",
                        "Rose",
                        "biweekly",
                        u1
                ));
        userService.save(u2);

        // user
        User u3 = new User("justin",
            "asdfasdf",
            "800-159-7530");
        u3.getRoles()
            .add(new UserRoles(u3,
                r1));
        u3.getPlants()
                .add(new Plant(
                        "Bob Barker",
                        "Chia Pet",
                        "daily",
                        u1
                ));
        userService.save(u3);

        User u4 = new User("Nick",
            "secret",
            "223-665-8855");
        u4.getRoles()
            .add(new UserRoles(u4,
                r1));
        userService.save(u4);

        User u5 = new User("Growtopia",
            "password",
            "206-455-5675");
        u5.getRoles()
            .add(new UserRoles(u5,
                r1));
        userService.save(u5);

//        if (false)
//        {
//            // using JavaFaker create a bunch of regular users
//            // https://www.baeldung.com/java-faker
//            // https://www.baeldung.com/regular-expressions-java
//
//            FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
//                new RandomService());
//            Faker nameFaker = new Faker(new Locale("en-US"));
//
//            for (int i = 0; i < 25; i++)
//            {
//                new User();
//                User fakeUser;
//
//                fakeUser = new User(nameFaker.name()
//                    .username(),
//                    "password",
//                    nameFaker.internet()
//                        .emailAddress());
//                fakeUser.getRoles()
//                    .add(new UserRoles(fakeUser,
//                        r1));
//                fakeUser.getPlants()
//                    .add(new Plant(fakeUser,
//                        fakeValuesService.bothify("????##@gmail.com")));
//                userService.save(fakeUser);
//            }
//        }
    }
}