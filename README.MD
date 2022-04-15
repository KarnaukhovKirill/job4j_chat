## job4j_auth

Учебный проект

Реализация Rest Api с использованием Spring Boot

Как запустить приложение с использованием docker-compose
1. Используем команду, чтобы склонировать исходный код "git clone https://github.com/KarnaukhovKirill/job4j_chat"
2. Для создания образа docker используем команду "docker build -t shortcut ." Команду запускаем из корневой папки проекта.
3. Запускаем кластер базы данных. Кластер объединяет несколько баз данных в одном месте. Если кластер уже есть, то меняем
пункты ENVIRONMENT в файле docker-compose.yml в корне проекта. 
   
"docker run -d \
   --name postgres \
   -e POSTGRES_PASSWORD=password \
   -e PGDATA=/var/lib/postgresql/data/pgdata \
   -p 5432:5432 \
   -v postgres_db:/var/lib/postgresql/data \
   postgres"

5. Запускаем скрипт docker-compose "docker-compose up"
6. Для проверки можно использовать Postman или cUrl. Адрес доступа к Rest Api http://localhost:8080

Команда для регистрации в приложении
![alt text](https://github.com/KarnaukhovKirill/job4j_chat/blob/main/img/sing-up.png)

Команда для аутентификации в приложении
![alt text](https://github.com/KarnaukhovKirill/job4j_chat/blob/main/img/login.png)

Пример команды после аутентификации. В данном примере получаем все сообщения. Список всех команд будет описан позже.
![alt text](https://github.com/KarnaukhovKirill/job4j_chat/blob/main/img/getMessage.png)

[![Java CI with Maven](https://github.com/KarnaukhovKirill/job4j_chat/actions/workflows/github-actions-demo.yml/badge.svg)](https://github.com/KarnaukhovKirill/job4j_chat/actions/workflows/github-actions-demo.yml)