# Incomex Test!

Esta es una api de ejemplo en base a lo solictado

La api tiene:
De base SqlLite no necesita instalacion . Al iniciar automaticamente creara la bd.
Para versionamiento db Flyway, este correra los script. Una vez corrido la primera vez no los volvera a correr. 
Posdata si me modifica algunos de los archivos de version despues de la creacion de la bd. Habra que eliminar la bd
Tiene implementacion con redis . Se deja desabilitado por si no tiene un Docker. para pruebas de carga se recomienda intalar un docker de redis.
(https://hub.docker.com/_/redis)
Se incluye configuracion de Kubernet por si se desea desplegar en la nube.

Para la documentacion Incluye swagger.
Url Local del proyect (http://localhost:8060/api/clientes/swagger-ui/index.html#)

Por falta de tiempo no realize los test, mas no estaban solicitados.
De seguridad como tal podria manejar con autorization en la api. pero es mejor manejarlo en el api gateway de la nube con una lamnda o un llamado ms. 