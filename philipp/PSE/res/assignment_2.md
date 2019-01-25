Übungsaufgabe MVC Konto
 
Teil 1 - MVC Grundlagen
Diese Übungsaufgabe soll die Grundlage zum MVC-Pattern etablieren. Es ist wichtig, diese Aufgabe gründlich zu bearbeiten und ein gemeinsames Verständnis zu schaffen, ehe ihr die nächste Aufgabe angeht.
 
 a) Macht euch mit dem MVC-Pattern vertraut. 
 
 b) Erweitert unsere Übung zum Periodensystem, um mittels des MVC-Patterns eine Anzeige hinzuzufügen und eine Interaktion zu vermöglichen:
            - Nutzt den Code des Periodensystems. Das ist euer Modell. Führt falls nötig ein Refactoring durch, so dass sich der Code auch gut als Modell eignet. 
            - Schreibt als View eine JavaFX-GUI, die genau ein Elemente des Periodensystems anzeigt (zum Beispiel mit Nummer und Name). Diese GUI soll darüber hinaus einen Button besitzen. 
            - Erstellt eine neue Klasse PeriodicSystemController, die euren Controller darstellt. Dieser soll auf den Button hören und bei Klick auf den Button dafür sorgen, dass unsere GUI das jeweils nächsthöhere Element im Periodensystem anzeigt.
             
 
Teil 2 - MVC Entwicklung im Team
In dieser Übungsaufgabe wollen wir uns genauer mit dem MVC-Pattern vertraut machen. Dazu implementieren wir eine erste Bankapplikation, mitsamt Datenmodell, Login, Oberfläche und erster Logik. Die Idee dahinter ist, die einzelnen Elemente aus MVC (namentlich das Modell, die View und den Controller) auch weitestgehend unabhängig voneinander zu entwickeln. Um das zu erreichen, soll jeder von euch nur für genau eine Komponente aus MVC zuständig sein - die Zusammenarbeit erfolgt dann über GIT. Das schließt natürlich nicht aus, dass ihr Teile der Aufgabe gemeinsam bearbeitet (optional: Macht euch dazu mit der Methodik "Pair Programming" vertraut).
Achtet bei der Umsetzung darauf, dass ihr unsere besprochenen Themen zu sauberem Code beachtet, sprechende Variablen- und Methodennamen benutzt und Konstanten (wo sinnvoll) entsprechend rauszieht. Lernt dabei drei neue Shortcuts in IntelliJ 😉 
 
Das Datenmodell: Entwickelt verschiedene Klassen, die die nachgenannten fachlichen Anforderungen erfüllen. Deckt diese mit sinnvollen Testfällen ab.
- Legt eine Klasse "User" an, die neben Attributen wie Vorname und Nachname auch einen eindeutigen Nutzernamen und ein Passwort besitzt sowie ein Attribut, das den User als Admin markiert.
- Leitet davon die Klasse "Kunde" ab, die zusätzlich das Attribut Kundennummer besitzt und über mehrere Konten (siehe nachfolgend) verfügen kann. Die Zahl der Konten kann sich während der Laufzeit des Programms ändern.
- Legt eine abstrakte Klasse "Konto" an. Das abstrakte Konto soll eine Kontonummer haben, einen Kontostand, sowie jeweile eine Methode zum Ein- und Auszahlen. Zusätzlich soll die Klasse über die abstrakten Methoden processMonthlyInterest() und processMonthlyFees() verfügen. 
- Leitet vom abstrakten Konto die Klasse "Girokonto" ab. Ein Girokonto ist ein normales Konto und soll monatlich keine Zinsen geben, dafür jedoch einen konstant 5 Euro an monatlicher Servicegebühr kosten. 
- Leitet vom abstrakten Konto die Klasse "Sparbuch" ab. Ein Sparbuch ist ein normales Konto und soll monatlich 1% Zinsen geben, dafür jedoch keine Gebühr kosten. 
- Leitet vom abstrakten Konto die Klasse "Premiumkonto" ab. Ein Premiumkonto ist ein normales Konto und soll monatlich keine Zinsen geben, dafür jedoch 0.5% Gebühr kosten. 
- Optional: Leitet vom abstrakten Konto die Klasse "Metallkonto" ab. Ein Metallkonto besitzt zwei eigene Attribute, und zwar eine Menge an Gold (in kg) sowie den Umrechnungskurs Gold zu Euro. Abfragen zur Höhe des Kontos sollen stets von der aktuellen Goldmenge den Wert dynamisch ausrechnen. Ein Metallkonto gibt keine Zinsen und kostet 100 Euro an monatlicher Servicegebühr.
- Es soll für einen Kunden möglich sein, neue Konten hinzuzufügen oder zu löschen.
 
Die View: Überlegt euch im Vorfeld, welche Methoden eure View von eurem Modell benötigt, um dieses sauber anzeigen zu können. Eure View sollte möglichst keine Logik enthalten sondern naiv die Inhalte des Modells anzeigen bzw. bei Interaktionen den Controller informieren. Die View sollte sich (in der Regel aktualisieren), sobald sich das Modell geändert hat. 
- Bei Start der Anwendung soll der User auf eine Login-Seite kommen, auf der er ein Username und Passwort eingeben kann.
- Nach erfolgreichem Login landet der User auf einer Übersichtsseite, die seine Stammdaten anzeigt, eine Liste der verfügbaren Konten (samt Kontostand) sowie Buttons, um Konten hinzuzufügen oder zu löschen. Ebenso soll es (auf dieser oder einer weiteren Seite) möglich sein, auf Konten ein- bzw. auszuzahlen. Zuletzt sollte die Übersichtseite einen Button zum Logout besitzen. 
 
Der Controller: Der Controller sollte sich an passenden Ereignissen in der View registrieren (vergleichbar zum Observer-Pattern) und darauf mit sinnvollen Aktionen im Modell reagieren.
- Ein Klick auf den Login-Button sollte dazu führen, dass der Controller die eingegeben Daten zu Nutzername und Passwort gegen das Modell validiert (dafür sollte das Modell eine passende Methode besitzen). Bei erfolgreichem Abgleich sollte der Controller die View (entweder direkt oder über eine Änderung im Modell) dazu bewegen, die Übersichtsseite anzuzeigen.
- Analog soll der Controller bei Interaktionen auf den Buttons der Übersichtsseite vorgehen: Ein Klick auf Konto hinzufügen bzw. Konto löschen sollte im Modell zur passenden Aktion führen, ebenso eine Ein- bzw. Auszahlung. 
 - Ein Klick auf den Button Logout sollte wieder auf den Login-Screen führen. 
 
Weiterführende Anforderungen (die ihr gemeinsam bearbeiten oder unter euch aufteilen könnt)
Die Konten eines Benutzers sollen nach der Kontonummer sortiert angezeigt werden.
Wenn ich mich als User mit Admin-Rechten einlogge möchte ich sämtliche Konten sehen, nicht nur meine eigenen. 
Invalide Interaktionen (bspw. das Löschen von Konten ohne verfügbares Konto oder das Auszahlen ohne verfügbares Geld) sollen im Frontend zu Popup-Fehlemeldungen führen. 
Optionale knifflige Aufgabe: Haltet das Datenmodell nicht nur im Java-Speicher, sondern speichert die relevanten Entitäten in eine Datenbank. Sorgt ebenso dafür, dass die Daten beim Start der Anwendung aus selbiger Datenbnak geladen werden können. Macht euch dazu mit dem DAO-Pattern vertraut.
 
 
Viel Spaß bei der Bearbeitung, wir gehen morgen gerne nochmal zusammen drüber 😊 
 

Viele Grüße,
Michael