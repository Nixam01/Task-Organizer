# GUI
## Proponowane elementy
1. Okno startowe.
2. Okno kalendarza.
3. Okno ustawień.
4. Okno zarządzania zadaniami.

## Opis
Interfejs użytkownika może zostać zrealizowany w dowolny sposób. Skłaniamy się jednak do graficznego interfejsu użytkownika, tak aby zapewniał on jak największą wygodę użytkowania. Dodatkowo, zastosowanie w tym przypadku graficznego interfejsu pozwala na wyeliminowanie dużej ilości potencjalnych błędów mogących się pojawiać w przypadku implementacji, przykładowo CLI. Twórców aplikacji nie ograniczamy również co do wyboru biblioteki zapewniającej narzędzia do projektowania interfejsu użytkownika. Proponujemy jedynie rozwiązania jakie według nas powinny zostać zaimplementowane, aby spełniać założenia projektu oraz przedstawiać przypadku użycia opisane dokumentacji.

## Okno startowe
Okno to miałoby być oknem włączającym się wraz ze startem programu oraz jego "ekranem dowodzenia".  
Zawrzeć tam możemy takie elementy jak:
  - Podgląd kalendarza
  - Odnośniki do innych elementów GUI
  - Informacje o kilku zadaniach z najwyższym priorytetem

## Okno kalendarza
W tym oknie zawierałyby się dane odnośnie zadań w ścisłym powiązaniu z kalendarzem.  
Proponowane opcje:  
   - Wskazanie ilości zadań z deadlinem na określony dzień w kalendarzu.
   - Wyświetlenie zadań powiązanych poprzez deadline z konkretnym dniem w kalendarzu.

## Okno ustawień
Okno ustawień powinno zapewniać użytkownikowi możliwość zmiany preferencji odnośnie użytkowania programu.  
Proponowane przez nas ustawienia opisane zostały w przypadkach użycia programu.

## Okno zarządzania zadaniami
Okno zarządzania zadaniami powinno zapewniać użytkownikowi łatwy dostęp do wszystkich zapisanych zadań oraz do ich  edycji. Możliwości edycji zadania oraz parametrów dotyczących zadania zostały opisane w dokumentacji. W tym oknie powinny również zawierać się opcje dotyczące zapisu wybranych zadań (w czytelnej postaci) do pliku.

## Zarządzanie powiadomieniami
Nie ograniczamy możliwości wyboru, ani kreatywności twórców aplikacji, dlatego nie prezentujemy konkretnej opcji dotyczącej prezentowania wysyłanych powiadomień. Proponujemy jednak trzy możliwości, które same się narzucają.  
1. Pierwszą opcją jest wyświetlanie powiadomień w odpowiednim segmencie aplikacji. Jego wygląd oraz możliwości pozostają zależne tylko od kreatywności twórców aplikacji.
2. W przypadku systemu Windows 10, istnieje stosunkowo łatwa do implementacji (przetestowana przez nas) możliwość wyświetlania powiadomień systemowych.
3. Istnieje również możliwość wyświetlania powiadomień jako prostych okien dialogowych. Ich wygląd jest również zależny od projektantów aplikacji. 
