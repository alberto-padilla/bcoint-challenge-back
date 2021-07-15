
# bcoint-challenge-back
###### Autor: Alberto Padilla

Microservicio desarrollando en Java 11 y Spring Boot que genera un ranking de palabras según un texto dado, esta integrado con el API W###d Co###er (censored).

## Requisitos
- API W###d Co###er en ejecución en puerto 8080.
- Instalar y configurar Maven

## Instalación

Ejecute los siguientes comandos en la terminal, debe  situarce en la carpeta raiz del proyecto

```bash
maven package
mvn spring-boot:run
```
la aplicacion iniciara en el puerto 8081 por defecto.
## Uso API

Obtener un texto random sin paginar
```bash
http://localhost:8081/bcoint-challange-back/text
```
Obtener un texto por id sin paginar
```bash
http://localhost:8081/bcoint-challange-back/text/{id}
```
Obtener ranking de un texto sin paginar
```bash
http://localhost:8081/bcoint-challange-back/text/{id}/ranking
```