Derweil hier bereits die nächste Aufgabe: Rekursive Funktionen

Modelliert die folgenden Algorithmen in Java. Baut dabei auf den bisher gelernten Konzepten der Objektorientierung auf und sorgt dafür, dass eure Klassen und Methoden durch sinnvolle Testfälle abgedeckt sind. 

a)	Schreibt eine Klasse Addierer, die zwei Zahlen addieren kann. Schreibt dazu zwei Methoden und nutzt einmal primitive Datentypen und einmal die gekapselten Datentypen. Berücksichtigt in euren Methoden und Testfällen auch null-Parameter. 

b)	Fügt eurer Klasse eine dritte Methode addiereRekursiv hinzu, die zwei Zahlen rekursiv addiert:

•	addiereRekursiv(a,b) ist addiereRekursiv(a, b-1) +1 , falls b > 0 ist.

•	addiereRekursiv(a,b) ist a, falls b = 0 ist. 

c)	Schreibt eine Klasse Palindrom, die prüft, ob ein übergebener String (oder wahlweise ein char-Array) ein Palindrom ist. Ein Wort ist dann ein Palindrom, wenn es sich vorwärts und rückwärts gelesen identisch ist. Löst die Aufgabe einmal mittels eine Schleife und einmal rekursiv.

Die Aufgaben sind vom Code-Umfang deutlich weniger als die letzten, dafür inhaltlich etwas kniffliger. Solltet ihr bei der Bearbeitung der rekursiven Aufgaben auf einen StackOverflowError stoßen findet heraus, was es damit auf sich hat. Damit kennt ihr dann auch die Namensherkunft der passenden Seite 😉


Viele Grüße und viel Spaß,
Michi
