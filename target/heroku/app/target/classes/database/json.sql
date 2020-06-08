DROP DATABASE renu_objects;

CREATE DATABASE renu_objects;
\c renu_objects;

CREATE TABLE jsonData(
    id serial PRIMARY KEY,
    info JSON
);

INSERT INTO jsonData (info) VALUES ('
{
  "name" : "Water",
  "Description": "Something to wash down the meal",
  "Types": [
      {
        "id" : "1",
        "name":"Normal",
        "imageurl" : "http://img.recipepuppy.com/5.jpg",
        "Description":"Just plain water",
        "sizes":[
          {
            "Size" : "Small",
            "imageurl" : "http://img.recipepuppy.com/5.jpg",
            "Price":"Ksh.70"
          },
          {
            "Size" : "Medium",
            "imageurl" : "http://img.recipepuppy.com/5.jpg",
            "Price":"Ksh.90"
          },
          {
            "Size" : "Large",
            "imageurl" : "http://img.recipepuppy.com/5.jpg",
            "Price":"Ksh.110"
          }
        ]
      },
      {
        "name":"Mineral",
        "Description":"From the springs",
        "price":"Ksh.90",
        "sizes":[
          {
            "Size" : "Small",
            "Price":"Ksh.70"
          },
          {
            "Size" : "Medium",
            "Price":"Ksh.90"
          },
          {
            "Size" : "Large",
            "Price":"Ksh.110"
          }
        ]
      }
      ]
}');
