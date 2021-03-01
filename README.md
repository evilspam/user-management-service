### Завдання
Реалізувати OTP та CRUD

OTP (One time password):
* Для користування CRUD - користувачу потрібно авторизуватись. Авторизацію виконуємо за емейлом.
* У випадку успішної авторизації - генерувається OTP та відправляється на емейл користувача.
* Час життя ОТП - 5хв.
* Проводимо валідацію OTP. OTP валідний - генерується токен, який передається з кожним est запитом до CRUD. У разі не валідності OTP - помилка. Доступ до CRUD можливий тільки з токеном.

CRUD:
* Реалізувати CRUD для сутності User з полями: емейл, імя, прізвище, дата народження, сімейний стан
* Реалізувати обробку помилок
* Використати міграцію(початково 4+ юзерів)

Результат:
* робоче завдання
* інструкція по запуску
* docker файл
* без UI, достатньо списку з REST ендпоінтів
* використання Spring Security за бажанням або власна реалізація логіки з перевірки токена


### Technology used
* Java 14
* Spring Boot, MVC, Data JPA, Security
* Hibernate
* MySQL 5.7
* Apache Maven 3.6.x
* Flyway
* Docker

### Build and run via docker-compose
1. For SMTP client to work, before running the app, configure environment variables:
* SMTP_HOST
* SMTP_PORT
* SMTP_USERNAME
* SMTP_PASSWORD
* SMTP_FROM
2. Open a terminal and run the following command from the root folder of the project to build the whole project.

```
docker-compose up
```

The server will start. Database will be populated with 5 default users (you can change resources\migration\V0.2__add_users.sql to add more custom users). 
Token default expiration will be set to 15 days.

3. Use curl or other http client to send requests to application endpoints.

### Application provide next endpoints:

* **POST /codes/generate** - generate new otp code and send it to specified email. Provided email should be present in the database.
* **POST /codes/validate** - Validate otp code received in email
* **POST /users** - create new user
* **PATCH /users/{id}** - change user data by user id
* **DELETE /users/{id}** - delete user by user id
* **GET /users/** - retrieve all users
* **GET /users/{id}** - retrieve user by user id



### Generate otp code
```
curl -X POST \
  http://localhost:8080/codes/generate \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{"email": "test_user1@gmail.com"}'

Response: 200
No body
```

### Validate otp code
```
curl -X POST \
  http://localhost:8080/codes/validate \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{"email": "test_user1@gmail.com", "code": "4301"}'
  
Response: 
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0X3VzZXIxQGdtYWlsLmNvbSIsImV4cCI6MTYxNTc1OTIwMH0.J38PV5Myd1Ao0heY2J3uND5VxHFndjqNeebrvkVYAAmBIJtLHmVTfRuO7mGspbMGH0OtLcoCFr4EPd7pvcAaoQ"
}
```

### Create user
```
curl -X POST \
  http://localhost:8080/users \
  -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0X3VzZXIxQGdtYWlsLmNvbSIsImV4cCI6MTYxNTc1OTIwMH0.J38PV5Myd1Ao0heY2J3uND5VxHFndjqNeebrvkVYAAmBIJtLHmVTfRuO7mGspbMGH0OtLcoCFr4EPd7pvcAaoQ' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{       
        "email": "new_ueeeser@gmail.com",
        "name": "Tom33",
        "surname": "Lions",
        "birthDate": "1991-01-12",
        "maritalStatus": "single"
}'

Response:
{
    "id": 6,
    "email": "new_ueeeser@gmail.com",
    "name": "Tom33",
    "surname": "Lions",
    "birthDate": "1991-01-12",
    "maritalStatus": "single"
}
```

### Update user
```
curl -X PATCH \
  http://localhost:8080/users/4 \
  -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0X3VzZXIxQGdtYWlsLmNvbSIsImV4cCI6MTYxNTc1OTIwMH0.J38PV5Myd1Ao0heY2J3uND5VxHFndjqNeebrvkVYAAmBIJtLHmVTfRuO7mGspbMGH0OtLcoCFr4EPd7pvcAaoQ' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{       
        "email": "new_mail@gmail.com",
        "name": "new_name",
        "surname": "new Surname",
        "birthDate": "1988-01-10",
        "maritalStatus": "single"
}'

Response: 
{
    "id": 4,
    "email": "new_mail@gmail.com",
    "name": "new_name",
    "surname": "new Surname",
    "birthDate": "1988-01-10",
    "maritalStatus": "single"
}
```

### Delete user
```
curl -X DELETE \
  http://localhost:8080/users/4 \
  -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0X3VzZXIxQGdtYWlsLmNvbSIsImV4cCI6MTYxNTc1OTIwMH0.J38PV5Myd1Ao0heY2J3uND5VxHFndjqNeebrvkVYAAmBIJtLHmVTfRuO7mGspbMGH0OtLcoCFr4EPd7pvcAaoQ' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'
  
Resonse: 204
```

### Get user
```
curl -X GET \
  http://localhost:8080/users/2 \
  -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0X3VzZXIxQGdtYWlsLmNvbSIsImV4cCI6MTYxNTc1OTIwMH0.J38PV5Myd1Ao0heY2J3uND5VxHFndjqNeebrvkVYAAmBIJtLHmVTfRuO7mGspbMGH0OtLcoCFr4EPd7pvcAaoQ' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'  

Resonse: 
{
    "id": 2,
    "email": "test_user2@gmail.com",
    "name": "Peter",
    "surname": "Porter",
    "birthDate": "1991-02-19",
    "maritalStatus": "married"
}
```

### Get all users
```
curl -X GET \
  http://localhost:8080/users \
  -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0X3VzZXIxQGdtYWlsLmNvbSIsImV4cCI6MTYxNTc1OTIwMH0.J38PV5Myd1Ao0heY2J3uND5VxHFndjqNeebrvkVYAAmBIJtLHmVTfRuO7mGspbMGH0OtLcoCFr4EPd7pvcAaoQ' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'  
  
Response:
List of users

```

### Exception Handling
The app throws custom exceptions wherever necessary which are captured through a controller advice. It then returns the appropriate error response to the caller

* CodeExpiredException - 410 code
* CodeNotFoundException - 400 code
* EmailAlreadyExistException - 409 code
* EntityAlreadyExistException - 409 code
* EntityNotFoundException - 404 code









