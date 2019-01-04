# MyPhotosApi

## API Photos

| Fonctionnalite  | Realise | Url |
| ------------- | ------------- |  ------------- | 
| recupere toutes les photos entre 2 dates | X | /api/v1/photos/date/{datedebut}/{datefin} |
| recupere toutes les photos liees a� un evenement | X | /api/v1/photos/evt/{id} |
| recupere toutes les photos sans evenements | X | /api/v1/photos/sansevt |
| recupere toutes les photos situe a� moins de 5 km d'un endroit  | X | /api/v1/photos/geo/{lattitude}/{longitude} |
| renvoi le binaire d'une photo en fonction de son id  | X | /api/v1/photos/binary/{id} |
| met la photo en tant que favorite  | X | /api/v1/photos/fav/{id} |

## API Evenements

| Fonctionnalite  | Realise | Url |
| ------------- | ------------- | ------------- | 
| recupere tous les evenements valides | X | /api/v1/evenements/validated/ok |
| recupere tous les evenements entre 2 dates valides | X | /api/v1/evenements/date/{datedebut}/{datefin} |
| recupere tous les evenements non valides | X | /api/v1/evenements/validated/no |
| recupere un evenement non valide au hasard | X | /api/v1/evenements/validated/no/one |
| modifie le titre d'un evenement | X | /api/v1/evenements/edit/nom/{id}/{name} |

## Slideshow

Une fois la liste des photos a� afficher recuperer je lance la page :

1- On affiche les 5 premieres photos de manières verticales (tant qu'elles ne sont pas chargées on mais en attente)

2- On scroll vers la bas automatiquement d'une photo à chaque fois qu'une nouvelle a bien été affichée
