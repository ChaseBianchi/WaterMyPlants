package com.lambdaschool.watermyplants.services;

import com.lambdaschool.watermyplants.WaterMyPlantsAppTesting;
import com.lambdaschool.watermyplants.exceptions.ResourceNotFoundException;
import com.lambdaschool.watermyplants.models.Plant;
import com.lambdaschool.watermyplants.models.Role;
import com.lambdaschool.watermyplants.models.User;
import com.lambdaschool.watermyplants.models.UserRoles;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WaterMyPlantsAppTesting.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplWithDBTest
{
    @Autowired
    private UserService userService;

    @MockBean
    HelperFunctions helperFunctions;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void B_findUserById()
    {
        assertEquals("admin",
            userService.findUserById(4)
                .getUsername());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void BA_findUserByIdNotFound()
    {
        assertEquals("admin",
            userService.findUserById(10)
                .getUsername());
    }

    @Test
    public void C_findAll()
    {
        assertEquals(5,
            userService.findAll()
                .size());
    }

    @Test
    public void D_delete()
    {
        userService.delete(13);
        assertEquals(4,
            userService.findAll()
                .size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void DA_notFoundDelete()
    {
        userService.delete(100);
        assertEquals(4,
            userService.findAll()
                .size());
    }

    @Test
    public void E_findByUsername()
    {
        assertEquals("admin",
            userService.findByName("admin")
                .getUsername());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void AA_findByUsernameNotfound()
    {
        assertEquals("admin",
            userService.findByName("turtle")
                .getUsername());
    }

    @Test
    public void AB_findByNameContaining()
    {
        assertEquals(4,
            userService.findByNameContaining("a")
                .size());
    }

    @Test
    public void F_save()
    {
        Role r2 = new Role("user");
        r2.setRoleid(2);

        User u2 = new User("tiger",
            "ILuvMath!",
            "tiger@school.lambda");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));
        u2.getPlants()
                .add(new Plant(
                        "Piney",
                        "Pine Tree",
                        "monthly",
                        u2
                ));

        User saveU2 = userService.save(u2);

        System.out.println("*** DATA ***");
        System.out.println(saveU2);
        System.out.println("*** DATA ***");

        assertEquals("tiger@tiger.local",
            saveU2.getPlants()
                .get(0)
                .getNickname());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void FA_saveputnotfound()
    {
        Role r2 = new Role("user");
        r2.setRoleid(2);

        User u2 = new User("tiger",
            "ILuvMath!",
            "tiger@school.lambda");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));
        u2.getPlants()
                .add(new Plant(
                        "Piney",
                        "Pine Tree",
                        "monthly",
                        u2
                ));
        u2.setUserid(777);

        User saveU2 = userService.save(u2);

        System.out.println("*** DATA ***");
        System.out.println(saveU2);
        System.out.println("*** DATA ***");

        assertEquals("tiger@tiger.local",
            saveU2.getPlants()
                .get(0)
                .getNickname());
    }

    @Test
    public void FA_saveputfound()
    {
        Role r2 = new Role("user");
        r2.setRoleid(2);

        User u2 = new User("mojo",
            "ILuvMath!",
            "mojo@school.lambda");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));
        u2.getPlants()
                .add(new Plant(
                        "Piney",
                        "Pine Tree",
                        "monthly",
                        u2
                ));
        u2.setUserid(4);

        User saveU2 = userService.save(u2);

        System.out.println("*** DATA ***");
        System.out.println(saveU2);
        System.out.println("*** DATA ***");

        assertEquals("mojo@corgi.local",
            saveU2.getPlants()
                .get(0)
                .getNickname());
    }

    @Test
    public void G_update()
    {
        Mockito.when(helperFunctions.isAuthorizedToMakeChange(anyString()))
            .thenReturn(true);

        Role r2 = new Role("user");
        r2.setRoleid(2);

        User u2 = new User("cinnamon",
            "password",
            "cinnamon@school.lambda");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));

        u2.getPlants()
                .add(new Plant(
                        "Piney",
                        "Pine Tree",
                        "monthly",
                        u2
                ));
        u2.getPlants()
                .add(new Plant(
                        "Piney",
                        "Pine Tree",
                        "monthly",
                        u2
                ));
        u2.getPlants()
                .add(new Plant(
                        "Piney",
                        "Pine Tree",
                        "monthly",
                        u2
                ));

        User updatedu2 = userService.update(u2,
            7);

        System.out.println("*** DATA ***");
        System.out.println(updatedu2);
        System.out.println("*** DATA ***");

        int checking = updatedu2.getPlants()
            .size() - 1;
        assertEquals("bunny@email.thump",
            updatedu2.getPlants()
                .get(checking)
                .getNickname());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void GB_updateNotCurrentUserNorAdmin()
    {
        Role r2 = new Role("user");
        r2.setRoleid(2);

        User u2 = new User("cinnamon",
            "password",
            "cinnamon@school.lambda");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));
        u2.getPlants()
                .add(new Plant(
                        "Piney",
                        "Pine Tree",
                        "monthly",
                        u2
                ));
        u2.getPlants()
                .add(new Plant(
                        "Piney",
                        "Pine Tree",
                        "monthly",
                        u2
                ));
        u2.getPlants()
                .add(new Plant(
                        "Piney",
                        "Pine Tree",
                        "monthly",
                        u2
                ));

        Mockito.when(helperFunctions.isAuthorizedToMakeChange(anyString()))
            .thenReturn(false);

        User updatedu2 = userService.update(u2,
            8);

        System.out.println("*** DATA ***");
        System.out.println(updatedu2);
        System.out.println("*** DATA ***");

        int checking = updatedu2.getPlants()
            .size() - 1;
        assertEquals("bunny@email.thump",
            updatedu2.getPlants()
                .get(checking)
                .getNickname());
    }
}