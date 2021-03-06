
# Проект "To DO" лист

* [Описание](#описание)
* [Функционал](#функционал)
* [Технологии](#технологии)
* [Архитектура](#архитектура)
* [Интерфейс](#интерфейс)
* [Автор](#автор)

## Описание
MVC REST API - приложение, todo-список дел.
Для хранения данных применяется Hibernate. Данные на главную страницу
(где располагается сам список действий и интерфейс работы с ним) 
подгружаются через AJAX-запросы, которые возвращают данные в формате JSON. 
При изменении списка он так же изменяется динамически без перезагрузки страницы.

## Функционал
* Регистрация пользователя
* Аутентификация на сервлет-фильтрах
* Авторизация через БД PostgreSQL
* Добавление задачи в TODO-list
* Пометить задачу как выполненную
* Вывод всех задач
* Вывод только незавершённых задач
* Для каждого зарегистрированного пользователя хранится свой собственный список
 
## Технологии
* Hibernate
* Java14
* PostgreSQL
* AJAX jquery
* Servlet&JSP
* HTML, BOOTSTRAP
* Apache Tomcat Server
* Junit
* Travis CI

## Архитектура
Проект реализован по шаблону MVC и разделён на слои:


1. Модели данных(Model)
   
   1.1 [Item](src/main/java/model/Item.java) -
    Модель для хранения данных задачи.
   
   1.2 [User](src/main/java/model/User.java) -
    Модель для хранения данных пользователя. 

   1.3 [Category](src/main/java/model/Category.java) -
    Модель для хранения данных пользователя.

2. Хранилища(Store)
   
   2.1.1 [UserStore](src/main/java/store/UserStore.java) -
   Инерфейс для Hibernate - хранилища пользователей. 
    
   2.1.2 [HbrUserStore](src/main/java/store/HbrUserStore.java) -
   Hibernate-хранилище для авторизационных пользователей. 
   HbrService является родителем этого класса
   
   2.2.1 [ItemStore](src/main/java/store/ItemStore.java) -
   Инерфейс для Hibernate - хранилища задач из TODO-списка.    

   2.2.2 [HbrItemStore](src/main/java/store/HbrItemStore.java) -
   Hibernate-хранилище для задач из TODO-списка.
   HbrService является родителем этого класса

   2.3.1 [CategoryStore](src/main/java/store/CategoryStore.java) -
   Инерфейс для Hibernate - хранилища категорий задач.

   2.3.2 [HbrCategoryStore](src/main/java/store/HbrCategoryStore.java) -
   Hibernate-хранилище для категорий задач.
   HbrService является родителем этого класса
   
   2.4.1 [HbrService](src/main/java/store/HbrService.java)
   Абстрактный класс для общих переменных. HbrItemStore и HbrUserStore
   являются потомками этого класса 
   
3. JSP - View

   3.1 [index.jsp](src/main/webapp/index.jsp) - главная страница приложения.
   На ней отображаются все задачи и интерфейс взаимодействия с ними, форма для
   добавления новых задач.
   
   3.2 [login.jsp](src/main/webapp/login.jsp) - страница авторизации. Поддерживает валидацию данных.
   Если пользователя с данным логином не существует быдет вывидено сообщение об ошибке.
   
   3.3 [reg.jsp](src/main/webapp/reg.jsp) - страница регистрации. Поддерживает валидацию даннных.
   Если пользователь с введённым логином уже существует, будет вывидено сообщение об ошибке.

4. Сервлеты - Controller

   4.1 [LoginServlet](src/main/java/servlet/LoginServlet.java) -
   Сервлет авторизации. Принимает запрос с JSP, производит валидацию введённых
   пользователем данных, существует ли в PostgreSQL хранилище такой пользователь. В случае успешной авторизации, добавляет
   пользователя в Http-сессию.

   4.2 [RegServlet](src/main/java/servlet/RegServlet.java) -
   Сервлет регистрации. Принимает запрос с JSP, производит валидацию. В случае успешной
   регистрации добавляет нового пользователя в PostgreSQL- хранилище и переводит на страницу авторизации.

   4.3 [IndexServlet](src/main/java/servlet/IndexServlet.java) -
   Сервлет обмена данными.
   * doGet метод - отправка актуальных данных о списке задач в формате JSON
   в ответ на AJAX-запрос на главную страницу приложения.
   * doPost метод:
   Добавление новой задачи в хранилище.

   4.4 [DoneServlet](src/main/java/servlet/DoneServlet.java) -
   
   Сервлет для пометки задания как выполненого. 

5. Фильтры
   
   5.1 [LoginFilter](src/main/java/filter/LoginFilter.java) -
   Блокирует доступ неавторизованному пользователю ко всем страницами приложения,
   кроме страниц авторизации и регистрации.
   
   4.2 [CORSFilter](src/main/java/filter/CORSFilter.java) -
   Cross-Origin-Resource-Sharing фильтр. Допускает междоменные запросы, когда
   клиент и сервер находятся на разных машинах.
   

## Интерфейс

* Интерфейс приложения
  ![ScreenShot](img/index.PNG)
  
* Регистрируемся
  ![ScreenShot](img/reg.PNG)

* Авторизируемся 
  ![ScreenShot](img/login.PNG)

* вводим верный пароль
  ![ScreenShot](img/wrongPass.PNG)

* Добавляем задачи
  ![ScreenShot](img/AddTasks.PNG)

* По умолчанию мы видем только незавершеные задачи
  ![ScreenShot](img/tasks.PNG)

* Нажимаем галку и видем все задачи
  ![ScreenShot](img/AllTasks.PNG)
  
## Автор

Маркелов Роман Игоревич

Java-разработчик

roman.sercent@gmail.com

+79178764086

[![Build Status](https://travis-ci.com/RamonOga/job4j_ToDo.svg?branch=master)](https://travis-ci.com/RamonOga/job4j_ToDo)