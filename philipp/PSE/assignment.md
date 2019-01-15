Guten Morgen zusammen,

damit euch heute (und besonders morgen und am Wochenende 😉) nicht langweilig wird hier die nächste Aufgabe. Die Bowling-Aufgabe ist natürlich weiter relevant und werden wir gemeinsam (insb. mit Blick auf Exception-Handling) kommende Woche besprechen.
Die nachfolgende Aufgabe bitte sorgfältig bearbeiten und aufheben. Die werden wir später nochmal aufleben lassen und eine Datenbank daran anbinden 😊 

Beginnt mit der Aufgabe gemeinsam sobald alle mit der Bowling-Aufgabe fertig sind. Sollten dort noch Unklarheiten offen sein, besprecht diese gemeinsam und/oder notiert sie und wir besprechen die Punkte kommende Woche.

Aufgabe: Periodensystem

Modelliert das Periodensystem in Java. Baut dabei auf die bisher gelernten Konzepte der Objektorientierung auf und sorgt dafür, dass eure Klassen und Methoden durch sinnvolle Testfälle abgedeckt sind.

a)	Legt eine Klasse ChemicalElement an. Ein chemisches Element besitzt eine Ordnungsnummer, ein Symbol, einen Namen sowie eine Position im Periodensystem (repräsentiert durch zwei Zahlen, die Periode 1 bis 18 und die Gruppe 1-7). 
b)	Chemische Elemente können verschiedenen Serien zugeordnet sein und unterschiedliche Aggregatzustände besitzen (siehe dazu https://de.wikipedia.org/wiki/Periodensystem). Die Attribute eines ChemicalElements dürfen sich, einmal belegt, nicht mehr ändern lassen. Fügt eurer Klasse Methoden hinzu wie bspw. isAlkali(), isEarthAlkali(), isFluid() oder isGas(). Setzt mindestens drei dieser Methoden um und benutzt für keine von ihnen if-Abfragen, sondern arbeitet jeweils einmal mit einem Switch-Ausdruck, mit einem boolschen Ausdruck und mit einem statischen boolschen Array, in dem der Index die Ordnungsnummer darstellt. 
c)	Schreibt einen Testfall, der drei chemische Elemente anlegt und die korrekte Belegung der Methoden aus b) prüft.
d)	Wir wollen nun chemische Elemente vergleichen können. Lasst dazu ChemicalElement das Interface Comparable implementieren. Als Vergleichskriterium soll die Ordnungszahl dienen. Erweitert eure Testfälle aus c) um die korrekten Vergleiche.
e)	Schreibt eine Klasse PeriodicSystem. Die Klasse soll eine Methode besitzen, mit der man ein ChemicalElement unter Angabe der Periode und Gruppe erhält. Diese Methode sollte eine passende Exception werfen, wenn an dieser Position kein Element existiert, und eine andere passende Exception, wenn die Parameter für Periode/Gruppe Unfug sind. Die neue Klasse soll außerdem eine Methode besitzen, um alle enthaltenen ChemicalElements in der korrekten Reihenfolge auf der Konsole auszugeben. 
f)	Im Anhang findet ihr eine csv-Datei, die weite Teile des Periodensystems inklusive ihrer Gruppen und Perioden enthält. Lest die Datei in eurem Java-Programm ein und erzeugt daraus ein vollständiges PeriodicSystem.
g)	Von der Klasse PeriodicSystem sollte es maximal eine Instanz geben dürfen (da in der Realität ja nicht mehr als ein Periodensystem existiert). Macht euch mit dem Singleton-Pattern vertraut und versucht dieses auf das PeriodicSystem anzuwenden.

Viel Spaß – bei Fragen kommt gerne auf Patrick, Markus, Maya oder mich zu 😊


Viele Grüße,
Michi 
