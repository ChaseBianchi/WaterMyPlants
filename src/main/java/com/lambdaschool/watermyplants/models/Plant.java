package com.lambdaschool.watermyplants.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * The entity allowing interaction with the useremails table
 * <p>
 * requires each combination of user and useremail to be unique. The same email cannot be assigned to the same user more than once.
 */
@Entity
@Table(name = "plants")
public class Plant
    extends Auditable
{
    /**
     * The primary key (long) of the useremails table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long plantid;

    /**
     * Email (String) for this user. Cannot be nullable.
     * Must be in the format userid@domain.upperLevelDomain
     */
    @Column(nullable = false)
    private String nickname;

    private String species;

    private String h2ofrequency;

    /**
     * The userid of the user assigned to this email is what is stored in the database.
     * This is the entire user object!
     * <p>
     * Forms a Many to One relationship between useremails and users.
     * A user can have many emails.
     */
    @ManyToOne
    @JoinColumn(name = "userid",
        nullable = false)
    @JsonIgnoreProperties(value = "plants",
        allowSetters = true)
    private User user;

    /**
     * The default controller is required by JPA
     */
    public Plant()
    {
    }

    /**
     * Given the parameters, create a new useremail object
     *
     * @param user      the user (User) assigned to the email
     * @param nickname useremail (String) for the given user
     */
//    public Plant(
//        User user,
//        String nickname)
//    {
//        this.nickname = nickname;
//        this.user = user;
//    }

    public Plant(String nickname, String species, String h2ofrequency, User user) {
        this.nickname = nickname;
        this.species = species;
        this.h2ofrequency = h2ofrequency;
        this.user = user;
    }

    /**
     * Getter for useremailid
     *
     * @return the primary key (long) of this useremail object
     */
    public long getPlantid()
    {
        return plantid;
    }

    /**
     * Setter for useremailid. Used for seeding data
     *
//     * @param useremailid the new primary key (long) of this useremail object
     */
    public void setPlantid(long plantid)
    {
        this.plantid = plantid;
    }

    /**
     * Getter for useremail
     *
     * @return the email (String) associated with this useremail object in lowercase
     */
    public String getNickname()
    {
        return nickname;
    }


    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getH2ofrequency() {
        return h2ofrequency;
    }

    public void setH2ofrequency(String h2ofrequency) {
        this.h2ofrequency = h2ofrequency;
    }

    /**
     * Getter for user
     *
     * @return the user object associated with this useremail.
     */
    public User getUser()
    {
        return user;
    }

    /**
     * Setter for user
     *
     * @param user the user object to replace the one currently assigned to this useremail object
     */
    public void setUser(User user)
    {
        this.user = user;
    }
}
