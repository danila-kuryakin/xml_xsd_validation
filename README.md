# XML-XSD-validation

1) При помощи Gradle создаем Docker-образ с сервисом. Переходим в папку с проектом и пишем:
./gradlew build docker

2) Запускаем контейнер:
docker run -p 80:80 -d ru.kuryakin/xml-xsd-validation

3) Загружаем XML и XSD файлы через JSP форму по адресу http://localhost:80/validate/form:
3.1) Добавим валидные файлы

![valid files](/screens/form_valid.png)

3.2) Не будем добавлять файлы

![without_files](/screens/form_without_files.png)

3.3) Добавим невалидные файлы
![invalid](/screens/form_invalid.png)

4) Отправим POST запрос через Postman по адресу http://localhost:80/validate:

![Postman request](/screens/poatman.png)
