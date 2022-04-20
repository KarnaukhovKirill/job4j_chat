## job4j_chat

Учебный проект

Реализация Rest Api с использованием Spring Boot

## Как запустить приложение с использованием docker-compose
1. Используем команду, чтобы склонировать исходный код
````
git clone https://github.com/KarnaukhovKirill/job4j_chat
````
2. Для создания образа docker используем команду ниже. Команду запускаем из корневой папки проекта.
````
docker build -t shortcut .
````
3. Запускаем кластер базы данных. Кластер объединяет несколько баз данных в одном месте. Если кластер уже есть, то меняем
пункты ENVIRONMENT в файле docker-compose.yml, в корне проекта. 
````
docker run -d \
   --name postgres \
   -e POSTGRES_PASSWORD=password \
   -e PGDATA=/var/lib/postgresql/data/pgdata \
   -p 5432:5432 \
   -v postgres_db:/var/lib/postgresql/data \
postgres
````
5. Запускаем скрипт docker-compose
````
docker-compose up
````
6. Для проверки можно использовать Postman или cUrl. Адрес доступа к Rest Api http://localhost:8080

#### Команда для регистрации в приложении
````
curl --location --request POST 'http://localhost:8080/person/sign-up' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "newUser",
    "password": "123456",
    "role": {
        "name": "ROLE_USER"
    }
}'
````
![alt text](https://github.com/KarnaukhovKirill/job4j_chat/blob/main/img/sing-up.png)

#### Команда для аутентификации в приложении. 
В ответ на эту команды, в Header, вы получите Authorization Token. 
Этот Token нужно использовать в последующих запросах, как показоно в следующей команде.
````
curl -i -H 'Content-Type: application/json' -X POST -d '{"username": "newUser", "password": "123456"}' http://localhost:8080/login
````
![alt text](https://github.com/KarnaukhovKirill/job4j_chat/blob/main/img/login.png)

#### Пример команды после аутентификации. 
В данном примере получаем все сообщения. Список всех команд будет описан позже.
````
curl -H 'Authorization: Bearer Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjUwODc0MzY3fQ.deMgc4yxudR1QHHdoaeRFE8wg-XTQrjNuEgxgJR8VlxP2XvlsKi0YpzY4PV9gC4P8Az0vpKU5tdO3PpWTEG_0w' http://localhost:8080/message/
````
![alt text](https://github.com/KarnaukhovKirill/job4j_chat/blob/main/img/getMessage.png)

## Как запустить приложение в Kubernetes 
Скачиваем и устанавливаем kubertl
```
curl -LO https://storage.googleapis.com/kubernetes-release/release/`curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt`/bin/linux/amd64/kubectl
```
````
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
````
Скачиваем и устанавливаем minikube
````
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
````
```
sudo install minikube-linux-amd64 /usr/local/bin/minikube
```
Будем использовать docker дефолтным провайдером контейнеров
Запускаем кластер
````
minikube start
````
Для конфигурирования нужно скачать 4 файла. 
````
wget https://raw.githubusercontent.com/KarnaukhovKirill/job4j_chat/main/k8s/postgresdb-configmap.yml
````
````
wget https://raw.githubusercontent.com/KarnaukhovKirill/job4j_chat/main/k8s/postgresdb-deployment.yml
````
````
wget https://raw.githubusercontent.com/KarnaukhovKirill/job4j_chat/main/k8s/postgresdb-secret.yml
````
````
wget https://github.com/KarnaukhovKirill/job4j_chat/blob/main/k8s/spring-boot-deployment.yml
````
Для хранения имени пользователя и пароля создадит secret. По-умолчанию, логин: "postgres" , пароль: "password". 
Данные закодированы в base64. 
````
kubectl apply -f postgresdb-secret.yml
````
Создаём конфигурацию для базы данных. 
````
kubectl apply -f postgresdb-configmap.yml
````
Создадим deployment для Postgres. 
````
kubectl apply -f postgresdb-deployment.yml
````
Создадим deployment для job4j_chat. Нужный image скачается с репозитория DockerHub
````
kubectl apply -f spring-boot-deployment.yml
````
Готово! Чтобы узнать адрес, по которому доступен сервис, введите команду
````
minikube service --url spring-boot-service
````
Все запросы должны быть адресованы по этому url. 

[![Java CI with Maven](https://github.com/KarnaukhovKirill/job4j_chat/actions/workflows/github-actions-demo.yml/badge.svg)](https://github.com/KarnaukhovKirill/job4j_chat/actions/workflows/github-actions-demo.yml)
