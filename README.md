# MyPhotosApi

## API Photos

| Fonctionnalite  | Realise | Url |
| ------------- | ------------- |  ------------- | 
| recupere toutes les photos entre 2 dates | X | /api/v1/photos/date/{datedebut}/{datefin} |
| recupere toutes les photos liees a  un evenement | X | /api/v1/photos/evt/{id} |
| recupere toutes les photos sans evenements | X | /api/v1/photos/sansevt |
| recupere toutes les photos situe a  moins de 5 km d'un endroit  | X | /api/v1/photos/geo/{lattitude}/{longitude} |
| renvoi le binaire d'une photo en fonction de son id  | X | /api/v1/photos/binary/{id} |
| met la photo en tant que favorite  | X | /api/v1/photos/fav/{id} |
| supprime la photo en tant que favorite  | X | /api/v1/photos/fav/delete/{id} |
| recupere toutes les photos favorites entre 2 dates | X | /api/v1/photos/date/fav/{datedebut}/{datefin} |

## API Evenements

| Fonctionnalite  | Realise | Url |
| ------------- | ------------- | ------------- | 
| recupere tous les evenements valides | X | /api/v1/evenements/validated/ok |
| recupere tous les evenements entre 2 dates valides | X | /api/v1/evenements/date/{datedebut}/{datefin} |
| recupere tous les evenements non valides | X | /api/v1/evenements/validated/no |
| recupere un evenement non valide au hasard | X | /api/v1/evenements/validated/no/one |
| modifie le titre d'un evenement | X | /api/v1/evenements/edit/nom/{id}/{name} |

## Slideshow

Une fois la liste des photos a  afficher recuperer je lance la page :

1- On affiche les 5 premieres photos de manieres verticales (tant qu'elles ne sont pas chargees on mais en attente)

2- On scroll vers la bas automatiquement d'une photo a chaque fois qu'une nouvelle a bien ete affichee


## Demarrer l'api

java -jar apiPhoto-0.0.1-SNAPSHOT.jar --spring.config.location=file:/emplacement/absolue/vers/application.properties

### Voici le contenu du fichier application.properties :

spring.data.mongodb.database=myphotos

spring.data.mongodb.uri=mongodb+srv://USERNAME:PASSWORD@cluster0-6neyj.mongodb.net/test?retryWrites=true

logging.level.org.springframework.data=error
logging.level.=error


### Avec Docker


```

git clone https://github.com/alexgit95/MyPhotosApi.git

cd MyPhotosApi

```

Modifier le fichier application.properties (exemple avec mongodb atlas) :

```

spring.data.mongodb.database=photos
spring.data.mongodb.uri=mongodb+srv://USERNAME:PASSWORD@cluster0-XXXX.mongodb.net/test?retryWrites=true&w=majority


logging.level.org.springframework.data=error
logging.level.=error

```

Puis faire :

```

docker build -t myphotosapi .

```

Ou sur un raspberry

```

docker build -t myphotosapi -f Dockerfile-rasp  .

```

Puis pour finir :

```

docker run -p 8080:8080 myphotosapi

```


