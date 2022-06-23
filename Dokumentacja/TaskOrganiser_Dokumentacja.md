# TaskOrganiser
## Biblioteka 

Biblioteka TaskOrganiser służy do organizowania zadań w powiązaniu z kalendarzem. Udostępnia zestaw klas i metod ułatwiających zarządzanie czasem. Pozwala na dodawanie nowych zadań, a następnie podpowiada optymalną kolejność ich wykonywania oraz przypomina o nich za pomocą systemu powiadomień. Biblioteka działa w powiązaniu z czasem, co umożliwia dynamiczne planowanie codziennych zadań.



Biblioteka składa się z siedmiu klas :  *Task, TaskDatabase, Notification, NotificationManager, NotificationType, FileManagement, DeadlinePriority*.

Klasa *Task* jest reprezentacją zadania do wykonania i zawiera jego ogólną charakterystykę: nazwę, opis, deadline, priorytet. Posiada zestaw metod pozwalających na aktualizacje pozostałego czasu na wykonanie oraz zmieniającego się w czasie priorytetu. Używa klasy *DeadlinePriority* typu enumeration do określenia kategorii priorytetu i następnego obliczania jego wartości na podstawie ilości pozostałego czasu.



Klasa *TaskDatabase* jest bazą danych, ma na celu przechowywanie zadań oraz udostępnianie metod do zarządzania nimi. Jest w relacji agregacji częściowej z klasą *Task* oraz korzysta z klasy *FileManagement*, która implementuje metody zapisu i odczytu z plików w formacie JSON w oparciu o bibliotekę jackson.



Klasa *Notification* reprezentuje powiadomienie, które będzie wysyłane przez system do użytkownika, atrybutami tej klasy są ogólne informacje na temat przypomnienia: tytuł, opis (tekst powiadomienia), powiązane z nim zadanie i typ. Klasa ta korzysta z klasy *NotificationType* typu enumeration definiującej typy wysyłanych powiadomień oraz odpowiadające im schematy.



Klasa *NotificationManager* udostępnia metody pozwalające na zarządzanie tworzeniem, przechowywaniem i przekazywaniem powiadomień. Jest w relacji agregacji częściowej z klasą Notification.



*!TaskOrganiser_Dokumentacja_Klas.md zawiera diagram wyżej wymienionych klas oraz bardziej szczegółowy opis atrybutów i metod!*

----------------------------------------------------------------------------------------

## Aplikacja 

Celem aplikacji jest ułatwienie codziennego życia poprzez zwrócenie uwagi na wykonywanie najważniejszych dla użytkownika zadań. Aplikacja stwarza przyjazne dla użytkownika środowisko do zarządzania zadaniami. Dzięki personalizowanym ustawieniom możliwe jest dostosowanie aplikacji do indywidualnych potrzeb użytkownika. Aplikacja wykorzystuje bibliotekę *TaskOrganiser*.  

Aplikacja zawiera trzy klasy: *Controler*, *Settings* oraz *TimeManagement*. 

Klasa *Controler* służy do zarządzania aplikacją. Umożliwia dostęp do *TaskDatabase* – bazy danych zawierającej informacje o zadaniach. Służy również jako punkt dostępowy do klas *NotificationManager*, *TimeManagement* oraz *Settings*. Klasa udostępnia zestaw metod potrzebnych do zarządzania zadaniami. 

Klasa *Settings* przechowuje informacje o preferencjach użytkownika dotyczących działania aplikacji. Umożliwia m.in. ustawienie preferowanego czasu otrzymywania powiadomień oraz ich dzienną liczbę. Informacje przechowywane są w pliku, co pozwala na zachowanie ustawień i wczytanie ich po ponownym uruchomieniu programu. 

*TimeManagement* służy do kontrolowania czasu w programie. Zawiera funkcję wywoływaną o każdej pełnej godzinie. 

*!TaskOrganiser_Dokumentacja_Klas.md zawiera diagram wyżej wymienionych klas oraz bardziej szczegółowy opis atrybutów i metod!* 

 

### Przypadki użycia aplikacji z perspektywy użytkownika: 

1. Zarządzanie zadaniami – aplikacja umożliwia dodanie nowego zadania, usunięcie, modyfikację jego szczegółów oraz oznaczenia zadania jako wykonane. Pozwala na nadanie nazwy, ostatecznego terminu wykonania oraz opisu, który może zawierać istotne szczegóły dotyczące zadania, np. miejsce lub przewidywany czas trwania. Dodatkowo udostępnia opcję nadania priorytetu (w skali od 1-10), który będzie miał wpływ na pozycjonowania zadania na liście. 

2. Wyświetlenie informacji o wybranym przez użytkownika zadaniu 

3. Otrzymywanie powiadomień - każdego dnia, o godzinie wybranej przez użytkownika w ustawieniach aplikacji, pojawia się lista zadań do wykonania na dany dzień. Lista zadań ustawiana jest według priorytetu tak, że najważniejsze zadania znajdują się na jej początku. Ponadto możliwe jest otrzymywanie powiadomień typu DEADLINE (informacja o zbliżającym się terminie wykonania zadania) oraz WARNING (informacja o przekroczeniu terminu wykonania zadania). Powiadomienia te nie pojawiają się o regularnej godzinie, gdyż dotyczą konkretnego zadania i zależą od ustawionego czasu na jego wykonanie. 

4. Ustawienia użytkownika - możliwa jest zmiana preferowanej godziny otrzymywania dziennych powiadomień, limitu liczby zadań na konkretny dzień oraz preferowanego czasu wysłania przypomnienia typu DEADLINE przed przewidzianym terminem wykonania zadania. Możliwe jest również ustawienie godziny rozpoczęcia i zakończenia czasu pracy, aby powiadomienia nie były wysyłane w czasie wolnym. 

5. Zapis do pliku - użytkownik może zachować wyświetloną w programie listę zadań na dany dzień poprzez zapisanie jej zawartości do pliku. 

6. Kalendarz – podczas dodawania nowego zadania do aplikacji ustawiany jest termin jego wykonania. Możliwe jest wyświetlenie zadań w powiązaniu z kalendarzem na podstawie ostatecznych terminów ich wykonania. 