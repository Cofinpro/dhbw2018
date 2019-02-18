Übungsaufgabe Minesweeper
 
Nachdem ihr euch in der letzten Aufgabe jeweils mit einzelnen Elementen des MVC-Patterns auseinander gesetzt habt (und dadurch vermehrt auf die Abstimmung untereinander angewiesen wart), könnt ihr euch für diese Aufgabe wieder selbst um alle drei Elemente des MVC-Patterns kümmern. 
1. Programmiert das Spiel „Minesweeper“. Setzt dabei das bereits bekannte MVC-Pattern ein. Für die View könnt ihr wahlweise eine Konsolenausgabe nutzen (was den Controller zu einer Konsoleneingabe macht), ein JavaFX-Frontend oder eine andere Frontend-Lösung eurer Wahl. Es soll zwischen drei Schwierigkeitsgraden gewählt werden können, die bspw. Auswirkung auf die Feldgröße oder Anzahl Bomben haben können.  
2. Die einzelnen Spiele sollen keine Persistenz besitzen. Allerdings soll es eine Highscore-Liste geben, die am Ende eines Spiels angezeigt wird (und in einer CSV-Datei gespeichert wird). Nutzt diese Anforderung dazu, das DAO-Pattern zu wiederholen bzw. zu vertiefen. Achtet insbesondere darauf, wie im letzten Review besprochen, euer DAO durch eine CSV-Datei in euren Unit-Tests gründlich zu testen. 
3. Erweitert das Standard-Minesweeper um eine konfigurierbare Zahl (Default: 1) an Superbomben, die bei der Vorhersage der umliegenden Felder als zwei Bomben zählt. Beispiel: Ein leeres Feld, das nur die Superbombe benachbart hat, wird „2“ anzeigen, ein leeres Feld mit zwei normalen und einer Superbombe benachbart „4“. Nutzt zur Implementierung der Superbome eines der bisher kennengelernten Konstrukte „Interface“, „Vererbung“ oder „abstrakte Klasse“ (oder ein ganz anderes, solltet ihr gute Gründe dafür finden). 
4. Macht euch mit UML Klassendiagrammen sowie einem Tool zur Zeichnung von solchen Diagrammen vertraut. Entwerft ein UML-Klassendiagramm für eure Minesweeper-Implementierung, in der die zum Verständnis relevanten Klassen auftauchen. Liefert eine gute Begründung, warum ihr ggf. manche Klassen nicht im Diagramm aufgenommen habt. 
5. Findet heraus, wo wir im VisualVest Projekt UML bzw. Klassendiagramme benutzen. 
 
 
Viel Spaß mit der Aufgabe und bis nächste Woche,
Michael