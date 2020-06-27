# reporter


Program Reporter sluzy do analizy godzin pracy pracowników w poszczególnych projektach (z podziałem na lata i miesiące). Analiza odbywa się na podstawie plików xls zawierających dane na temat pracy i projektów. Pliki muszą być umieszczone w folderach ustrukturyzowanych następująco:


![alt text](https://i.imgur.com/mVc43v9.jpg)


Jeden plik dotyczy jednego pracownika. Nazwa pliku musi być zgodna z poniższym szablonem:
Nazwisko_Imię.xls (np. Kowalski_Jan.xls)
Nawza projektu powinna być nazwą arkusza w pliku excela. W pierwszej kolumnie umieszczamy datę, w drugiej nazwę zadania, w trzeciej ilość godzin. Pierwszy wiersz zawsze jest nagłówkiem.

Przykład:
 
![alt text](https://i.imgur.com/91eJmUm.jpg)






**Instrukcja:**

Program uruchamiany jest z wiersza poleceń. 
Plik do pobrania dostępny jest: 
https://github.com/mhendzel123/reporter/raw/report5/target/reporter-1.0-SNAPSHOT-jar-with-dependencies.jar


**Polecenia do generowania poszczególnych raportów:**

**Wymagane:**

-s <ścieżka do katalogu źródłowego>

-r \<typ raportu\> (dostępne wartości od 1 do 5)

-q \<wyszukiwana fraza\> (parametr tylko dla raportu 5, można podać tylko jedną wyszukiwaną frazę, wielkość znaków nie ma znaczenia)

**Opcjonalne:**

-x (zapisuje wygenerowany raport w pliku xls)

-ch (wyświetla raport w postaci wykresu, dla raportów 1 – 3)

-png (zapisuje wykres w pliku png)

-ef \<tekst\> (dla raportów 1 i 3, wygenerują się tylko dla pracowników, których imię lub nazwisko zawiera podany tekst, można podać tylko jedną wyszukiwaną frazę, wielkość znaków nie ma znaczenia)

-pf \<tekst\> (dla raportów 2 - 5, wygenerują się tylko dla projektów, których nazwa zawiera podany tekst, można podać tylko jedną wyszukiwaną frazę, wielkość znaków nie ma znaczenia)



**Przykłady użycia:**
1.	Generowanie raportu 1 na konsolę dla plików w folderze resources (wraz z podfolderami)
java -jar target\reporter-1.0-SNAPSHOT-jar-with-dependencies.jar -s src\main\resources -r 1
2.	Generowanie raportu 5 wyszukującego zadania ze słowem „projekt”, zapisanie go w pliku xls oraz zapisanie wykresu w pliku png
java -jar target\reporter-1.0-SNAPSHOT-jar-with-dependencies.jar -s src\main\resources -r 5 -q projekt -x -png
3.	Generowanie raportu 3 dla pracowników, których nazwisko zawiera frazę “Nowak” i projektów, których nazwa zawiera frazę „Projekt2”
java -jar target\reporter-1.0-SNAPSHOT-jar-with-dependencies.jar -s src\main\resources -r 3 -ef Nowak -pf Projekt2


