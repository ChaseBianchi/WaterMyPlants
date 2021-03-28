# Foundation / Scaffolding for Build Week

## Introduction

This is a basic database scheme with users, user emails, and user roles. This Java Spring REST API application will provide endpoints for clients to read various data sets contained in the application's data. This application will also form the basis of a user authentication application developed elsewhere in the course

### Database layout

The table layout is similar to the initial version with the following exceptions:

* The join table userroles is explicitly created. This allows us to add additional columns to the join table
* Since we are creating the join table ourselves, the Many to Many relationship that formed the join table is now two Many to One relationships
* All tables now have audit fields

Thus the new table layout is as follows

* User is the driving table.
* Useremails have a Many-To-One relationship with User. Each User has many user email combinations. Each user email combination has only one User.
* Roles have a Many-To-Many relationship with Users.

![Image of Database Layout](usersfinaldb.png)

Using the provided seed data, expand each endpoint below to see the output it generates.

<details>
<summary>http://localhost:2019/plants/plants</summary>

```JSON
[
    {
        "useremailid": 5,
        "plant": "admin@email.local",
        "user": {
            "userid": 4,
            "username": "admin",
            "primaryemail": "admin@lambdaschool.local",
            "roles": [
                {
                    "role": {
                        "roleid": 3,
                        "name": "DATA"
                    }
                },
                {
                    "role": {
                        "roleid": 1,
                        "name": "ADMIN"
                    }
                },
                {
                    "role": {
                        "roleid": 2,
                        "name": "USER"
                    }
                }
            ]
        }
    },
    {
        "useremailid": 6,
        "plant": "admin@mymail.local",
        "user": {
            "userid": 4,
            "username": "admin",
            "primaryemail": "admin@lambdaschool.local",
            "roles": [
                {
                    "role": {
                        "roleid": 3,
                        "name": "DATA"
                    }
                },
                {
                    "role": {
                        "roleid": 1,
                        "name": "ADMIN"
                    }
                },
                {
                    "role": {
                        "roleid": 2,
                        "name": "USER"
                    }
                }
            ]
        }
    },
    {
        "useremailid": 8,
        "plant": "cinnamon@mymail.local",
        "user": {
            "userid": 7,
            "username": "cinnamon",
            "primaryemail": "cinnamon@lambdaschool.local",
            "roles": [
                {
                    "role": {
                        "roleid": 2,
                        "name": "USER"
                    }
                },
                {
                    "role": {
                        "roleid": 3,
                        "name": "DATA"
                    }
                }
            ]
        }
    },
    {
        "useremailid": 9,
        "plant": "hops@mymail.local",
        "user": {
            "userid": 7,
            "username": "cinnamon",
            "primaryemail": "cinnamon@lambdaschool.local",
            "roles": [
                {
                    "role": {
                        "roleid": 2,
                        "name": "USER"
                    }
                },
                {
                    "role": {
                        "roleid": 3,
                        "name": "DATA"
                    }
                }
            ]
        }
    },
    {
        "useremailid": 10,
        "plant": "bunny@email.local",
        "user": {
            "userid": 7,
            "username": "cinnamon",
            "primaryemail": "cinnamon@lambdaschool.local",
            "roles": [
                {
                    "role": {
                        "roleid": 2,
                        "name": "USER"
                    }
                },
                {
                    "role": {
                        "roleid": 3,
                        "name": "DATA"
                    }
                }
            ]
        }
    },
    {
        "useremailid": 12,
        "plant": "barnbarn@email.local",
        "user": {
            "userid": 11,
            "username": "barnbarn",
            "primaryemail": "barnbarn@lambdaschool.local",
            "roles": [
                {
                    "role": {
                        "roleid": 2,
                        "name": "USER"
                    }
                }
            ]
        }
    }
]
```

</details>

<details>
<summary>http://localhost:2019/plants/plant/8</summary>

```JSON
{
    "useremailid": 8,
    "plant": "cinnamon@mymail.local",
    "user": {
        "userid": 7,
        "username": "cinnamon",
        "primaryemail": "cinnamon@lambdaschool.local",
        "roles": [
            {
                "role": {
                    "roleid": 2,
                    "name": "USER"
                }
            },
            {
                "role": {
                    "roleid": 3,
                    "name": "DATA"
                }
            }
        ]
    }
}
```

</details>

<details>
<summary>DELETE http://localhost:2019/plants/plant/8</summary>

```TEXT
No Body Data

Status OK
```

</details>


<details>
<summary>PUT http://localhost:2019/plants/plant/9/email/favbun@hops.local</summary>

OUTPUT

```TEXT
Status OK
```

</details>

<details>
<summary>http://localhost:2019/plants/plant/9</summary>

```JSON
{
    "useremailid": 9,
    "plant": "favbun@hops.local",
    "user": {
        "userid": 7,
        "username": "cinnamon",
        "primaryemail": "cinnamon@lambdaschool.local",
        "roles": [
            {
                "role": {
                    "roleid": 2,
                    "name": "USER"
                }
            },
            {
                "role": {
                    "roleid": 3,
                    "name": "DATA"
                }
            }
        ]
    }
}
```

</details>

<details>
<summary>POST http://localhost:2019/plants/user/14/email/favbun@hops.local</summary>

OUTPUT

```TEXT
Status CREATED

Location Header: http://localhost:2019/plants/plant/15
```

</details>

<details>
<summary>http://localhost:2019/plants/plant/15</summary>

```JSON
{
    "useremailid": 15,
    "plant": "favbun@hops.local",
    "user": {
        "userid": 14,
        "username": "misskitty",
        "primaryemail": "misskitty@school.lambda",
        "roles": [
            {
                "role": {
                    "roleid": 2,
                    "name": "USER"
                }
            }
        ]
    }
}
```

</details>

---

<details>
<summary>http://localhost:2019/roles/roles</summary>

```JSON
[
    {
        "roleid": 1,
        "name": "ADMIN",
        "users": [
            {
                "user": {
                    "userid": 4,
                    "username": "admin",
                    "primaryemail": "admin@lambdaschool.local",
                    "plants": [
                        {
                            "useremailid": 5,
                            "plant": "admin@email.local"
                        },
                        {
                            "useremailid": 6,
                            "plant": "admin@mymail.local"
                        }
                    ]
                }
            }
        ]
    },
    {
        "roleid": 2,
        "name": "USER",
        "users": [
            {
                "user": {
                    "userid": 14,
                    "username": "misskitty",
                    "primaryemail": "misskitty@school.lambda",
                    "plants": [
                        {
                            "useremailid": 15,
                            "plant": "favbun@hops.local"
                        }
                    ]
                }
            },
            {
                "user": {
                    "userid": 13,
                    "username": "puttat",
                    "primaryemail": "puttat@school.lambda",
                    "plants": []
                }
            },
            {
                "user": {
                    "userid": 11,
                    "username": "barnbarn",
                    "primaryemail": "barnbarn@lambdaschool.local",
                    "plants": [
                        {
                            "useremailid": 12,
                            "plant": "barnbarn@email.local"
                        }
                    ]
                }
            },
            {
                "user": {
                    "userid": 7,
                    "username": "cinnamon",
                    "primaryemail": "cinnamon@lambdaschool.local",
                    "plants": [
                        {
                            "useremailid": 9,
                            "plant": "favbun@hops.local"
                        },
                        {
                            "useremailid": 10,
                            "plant": "bunny@email.local"
                        }
                    ]
                }
            },
            {
                "user": {
                    "userid": 4,
                    "username": "admin",
                    "primaryemail": "admin@lambdaschool.local",
                    "plants": [
                        {
                            "useremailid": 5,
                            "plant": "admin@email.local"
                        },
                        {
                            "useremailid": 6,
                            "plant": "admin@mymail.local"
                        }
                    ]
                }
            }
        ]
    },
    {
        "roleid": 3,
        "name": "DATA",
        "users": [
            {
                "user": {
                    "userid": 4,
                    "username": "admin",
                    "primaryemail": "admin@lambdaschool.local",
                    "plants": [
                        {
                            "useremailid": 5,
                            "plant": "admin@email.local"
                        },
                        {
                            "useremailid": 6,
                            "plant": "admin@mymail.local"
                        }
                    ]
                }
            },
            {
                "user": {
                    "userid": 7,
                    "username": "cinnamon",
                    "primaryemail": "cinnamon@lambdaschool.local",
                    "plants": [
                        {
                            "useremailid": 9,
                            "plant": "favbun@hops.local"
                        },
                        {
                            "useremailid": 10,
                            "plant": "bunny@email.local"
                        }
                    ]
                }
            }
        ]
    }
]
```

</details>

<details>
<summary>http://localhost:2019/roles/role/3</summary>

```JSON
{
    "roleid": 3,
    "name": "DATA",
    "users": [
        {
            "user": {
                "userid": 4,
                "username": "admin",
                "primaryemail": "admin@lambdaschool.local",
                "plants": [
                    {
                        "useremailid": 5,
                        "plant": "admin@email.local"
                    },
                    {
                        "useremailid": 6,
                        "plant": "admin@mymail.local"
                    }
                ]
            }
        },
        {
            "user": {
                "userid": 7,
                "username": "cinnamon",
                "primaryemail": "cinnamon@lambdaschool.local",
                "plants": [
                    {
                        "useremailid": 9,
                        "plant": "favbun@hops.local"
                    },
                    {
                        "useremailid": 10,
                        "plant": "bunny@email.local"
                    }
                ]
            }
        }
    ]
}
```

</details>

<details>
<summary>http://localhost:2019/roles/role/name/data</summary>

```JSON
{
    "roleid": 3,
    "name": "DATA",
    "users": [
        {
            "user": {
                "userid": 4,
                "username": "admin",
                "primaryemail": "admin@lambdaschool.local",
                "plants": [
                    {
                        "useremailid": 5,
                        "plant": "admin@email.local"
                    },
                    {
                        "useremailid": 6,
                        "plant": "admin@mymail.local"
                    }
                ]
            }
        },
        {
            "user": {
                "userid": 7,
                "username": "cinnamon",
                "primaryemail": "cinnamon@lambdaschool.local",
                "plants": [
                    {
                        "useremailid": 9,
                        "plant": "favbun@hops.local"
                    },
                    {
                        "useremailid": 10,
                        "plant": "bunny@email.local"
                    }
                ]
            }
        }
    ]
}
```

</details>

<details>
<summary>POST http://localhost:2019/roles/role</summary>

DATA

```JSON
{
    "name" : "ANewRole"
}
```

OUTPUT

```TEXT
Status CREATED

Location Header: http://localhost:2019/roles/role/16
```

</details>

<details>
<summary>http://localhost:2019/roles/role/name/anewrole</summary>

```JSON
{
    "roleid": 16,
    "name": "ANEWROLE",
    "users": []
}
```

</details>

<details>
<summary>PUT http://localhost:2019/roles/role/16</summary>

DATA

```JSON
{
    "name" : "ANewRole"
}
```

OUTPUT

```TEXT
Status OK
```

</details>

---

<details>
<summary>http://localhost:2019/users/users</summary>

```JSON
[
    {
        "userid": 4,
        "username": "admin",
        "primaryemail": "admin@lambdaschool.local",
        "plants": [
            {
                "useremailid": 5,
                "plant": "admin@email.local"
            },
            {
                "useremailid": 6,
                "plant": "admin@mymail.local"
            }
        ],
        "roles": [
            {
                "role": {
                    "roleid": 3,
                    "name": "DATA"
                }
            },
            {
                "role": {
                    "roleid": 1,
                    "name": "ADMIN"
                }
            },
            {
                "role": {
                    "roleid": 2,
                    "name": "USER"
                }
            }
        ]
    },
    {
        "userid": 7,
        "username": "cinnamon",
        "primaryemail": "cinnamon@lambdaschool.local",
        "plants": [
            {
                "useremailid": 9,
                "plant": "favbun@hops.local"
            },
            {
                "useremailid": 10,
                "plant": "bunny@email.local"
            }
        ],
        "roles": [
            {
                "role": {
                    "roleid": 2,
                    "name": "USER"
                }
            },
            {
                "role": {
                    "roleid": 3,
                    "name": "DATA"
                }
            }
        ]
    },
    {
        "userid": 11,
        "username": "barnbarn",
        "primaryemail": "barnbarn@lambdaschool.local",
        "plants": [
            {
                "useremailid": 12,
                "plant": "barnbarn@email.local"
            }
        ],
        "roles": [
            {
                "role": {
                    "roleid": 2,
                    "name": "USER"
                }
            }
        ]
    },
    {
        "userid": 13,
        "username": "puttat",
        "primaryemail": "puttat@school.lambda",
        "plants": [],
        "roles": [
            {
                "role": {
                    "roleid": 2,
                    "name": "USER"
                }
            }
        ]
    },
    {
        "userid": 14,
        "username": "misskitty",
        "primaryemail": "misskitty@school.lambda",
        "plants": [
            {
                "useremailid": 15,
                "plant": "favbun@hops.local"
            }
        ],
        "roles": [
            {
                "role": {
                    "roleid": 2,
                    "name": "USER"
                }
            }
        ]
    }
]
```

</details>

<details>
<summary>http://localhost:2019/users/user/7</summary>

```JSON
{
    "userid": 7,
    "username": "cinnamon",
    "primaryemail": "cinnamon@lambdaschool.local",
    "plants": [
        {
            "useremailid": 9,
            "plant": "favbun@hops.local"
        },
        {
            "useremailid": 10,
            "plant": "bunny@email.local"
        }
    ],
    "roles": [
        {
            "role": {
                "roleid": 2,
                "name": "USER"
            }
        },
        {
            "role": {
                "roleid": 3,
                "name": "DATA"
            }
        }
    ]
}
```

</details>

<details>
<summary>http://localhost:2019/users/user/name/cinnamon</summary>

```JSON
{
    "userid": 7,
    "username": "cinnamon",
    "primaryemail": "cinnamon@lambdaschool.local",
    "plants": [
        {
            "useremailid": 9,
            "plant": "favbun@hops.local"
        },
        {
            "useremailid": 10,
            "plant": "bunny@email.local"
        }
    ],
    "roles": [
        {
            "role": {
                "roleid": 2,
                "name": "USER"
            }
        },
        {
            "role": {
                "roleid": 3,
                "name": "DATA"
            }
        }
    ]
}
```

</details>

<details>
<summary>http://localhost:2019/users/user/name/like/da</summary>

```JSON
[]
```

</details>

<details>
<summary>POST http://localhost:2019/users/user</summary>

DATA

```JSON
{
    "username": "Mojo",
    "primaryemail": "mojo@lambdaschool.local",
    "password" : "Coffee123",
    "plants": [
        {
            "plant": "mojo@mymail.local"
        },
        {
            "plant": "mojo@email.local"
        }
        ],
    "roles": [
        {
            "role": {
                "roleid": 1
            }
        },
        {
            "role": {
                "roleid": 2
            }
        }
    ]
}
```

OUTPUT

```TEXT
No Body Data

Location Header: http://localhost:2019/users/user/17
Status 201 Created
```

</details>

<details>
<summary>http://localhost:2019/users/user/name/mojo</summary>

</details>

<details>
<summary>PUT http://localhost:2019/users/user/14</summary>

DATA

```JSON
{
    "username": "stumps",
    "primaryemail": "stumps@lambdaschool.local",
    "password" : "EarlGray123",
    "plants": [
        {
            "plant": "stumps@mymail.local"
        },
        {
            "plant": "stumps@email.local"
        }
        ],
    "roles": [
        {  
            "role": {
                "roleid": 3
            }
        },
        {  
            "role": {
                "roleid": 1
            }
        }
    ]
}
```

OUTPUT

```TEXT
No Body Data

Status OK
```

</details>

<details>
<summary>http://localhost:2019/users/user/name/stumps</summary>

```JSON
{
    "userid": 16,
    "username": "stumps",
    "primaryemail": "stumps@lambdaschool.local",
    "plants": [
        {
            "useremailid": 19,
            "plant": "stumps@mymail.local"
        },
        {
            "useremailid": 20,
            "plant": "stumps@email.local"
        }
    ],
    "roles": [
        {
            "role": {
                "roleid": 1,
                "name": "ADMIN"
            }
        },
        {
            "role": {
                "roleid": 3,
                "name": "DATA"
            }
        }
    ]
}
```

</details>

<details>
<summary>PATCH http://localhost:2019/users/user/7</summary>

DATA

```JSON
{
    "username": "cinabun",
    "primaryemail": "cinabun@lambdaschool.home",
    "plants": [
    {
            "plant": "cinnamon@mymail.home"
    },
    {
            "plant": "hops@mymail.home"
    },
    {
            "plant": "bunny@email.home"
    }
    ]
}
```

OUTPUT

```TEXT
No Body Data

Status OK
```

</details>

<details>
<summary>http://localhost:2019/users/user/name/cinabun</summary>

</details>

```JSON
{
    "userid": 7,
    "username": "cinabun",
    "primaryemail": "cinabun@lambdaschool.home",
    "plants": [
        {
            "useremailid": 21,
            "plant": "cinnamon@mymail.home"
        },
        {
            "useremailid": 22,
            "plant": "hops@mymail.home"
        },
        {
            "useremailid": 23,
            "plant": "bunny@email.home"
        }
    ],
    "roles": [
        {
            "role": {
                "roleid": 2,
                "name": "USER"
            }
        },
        {
            "role": {
                "roleid": 3,
                "name": "DATA"
            }
        }
    ]
}
```

<details>

<summary>DELETE http://localhost:2019/users/user/14</summary>

```TEXT
No Body Data

Status OK
```

</details>
