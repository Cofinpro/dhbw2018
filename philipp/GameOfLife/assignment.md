Game of Life
In dieser Übung wollen wir einen Zell-Automaten (zur Simulation von Zellbewegungen) programmieren. Dabei bauen wir nicht nur auf den bisher gelernten Konzepten der Objektorientierung und Testing auf, sondern nutzen fortgeschrittene Visualisierung in JavaFX für eine erste „Echtzeit“ Anwendung.

a)	Legt eine Klasse GameOfLifeCell an, die als Variable ein Boolean alive besitzt sowie einen Wert x und y für die Position. Das Feld alive repräsentiert, ob diese Zelle lebendig ist.
 
b)	Legt eine Klasse GameOfLife an, die ein zweidimensionales Spielfeld abbilden soll. Führt dazu eine Variable gameField ein, die ein zweidimensionales Array von GameOfLifeCells ist (für den Anfang 15 mal 15 Elemente). Pro Java Instanz soll es nur eine Instanz von GameOfLife geben können. Macht euch daher mit dem Singleton-Pattern vertraut, setzt den Konstruktor der Klasse auf private und implementiert eine statische Methode getInstance(), das die einzige Instanz des GameOfLife zurückliefert oder initialisiert, sofern es sie noch nicht gibt. Das GameOfLife soll eine Möglichkeit besitzen, auf der Konsole ausgegeben zu werden.
 
c)	Die Zellen im Game of Life verändern sich in Abhängigkeit von ihren Nachbarzellen. Legt dazu eine Methode nextIteration() an, die die Zellen nach folgendem Regelwerk verändert:

•	Zellen, die lebendig sind

i.	sterben, wenn sie einen oder keinen lebendigen Nachbarn haben (Einsamkeit).

ii.	sterben, wenn sie vier oder mehr Nachbarn haben (Überbevölkerung)

iii.	überleben, bei zwei oder drei Nachbarn.

•	Zellen, die tot sind

i.	werden lebendig, wenn sie genau drei Nachbarn  haben.

Dazu wird es notwendig sein, dass die Zellen das Spielfeld kennen (um ihre Nachbarn zu erfahren) oder das Spielfeld über das Leben der Zellen entscheidet (oder die Problematik der Nachbarn über eine ganz andere Lösung abbildet). Überlegt euch die Vorteile/Nachteile der Lösungen (auch mit Blick auf Objektorientierung) und deckt insbesondere die obigen Regeln mit Unit-Testfällen ab.
 
d)	Visualisiert euer GameOfLife analog zum Koordinatensystem in JavaFX. Achtet darauf, dass jeder Aufruf der nextIteration() zu einem Update der Graphik führen soll. Dabei kann euch das Observer-Pattern helfen.
 
e)	(optional) Fügt eurem Frontend einen Button hinzu, um mit einem Klick die nächste Iteration auslösen zu können.

f)	(optional) Fügt eurem Frontend einen Button hinzu, um mit einem Klick zur vorigen Iteration zurück zu können 😊 

Ein Beispiel für das Game of Life findet ihr hier: https://playgameoflife.com/

Zusatz nach Fertigstellung: 
Code Refactoring
- keine Duplikate
- speaking methods
- Kapselung (private bei Objekten)

Viel Spaß,
Michi
