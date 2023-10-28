# Worttrainer
Dies ist ein Tool mit dem man lernen kann wie man Wörter richtig schreibt. Hier wird einem ein Bild angezeigt, wozu man das passende Wort dazu schreiben soll. Danach soll geprüft werden ob dieses Wort richtig geschrieben wurde und dies soll mittels einer Statistik ausgegeben werden. 
## Aufbau
Der Worttrainer besteht aus 3 Teilen:
- Wortpaar
- Worttrainer
- GUI

## Wortpaar
Das Wortpaar speichert eine gültige Image URL sowie ein beschreibendes Wort des Bildes. Hier muss darauf aufgepasst werden das die URL direkt zu einem Bild führt und eine vollständige Domain enthält, also mittels https://... beginnt. 
## Worttrainer
Der Worttrainer ist für die Verarbeitung der Daten zuständig. Dieser speichert sich eine Liste von Wortpaaren die dem Benutzer zum lernen gegeben werden sollen. Hier soll er dann die Nutzer eingabe auf die Richtigkeit überprüfen und Statistiken ausgeben wie viele Versuche es gab und wie viele dabei richtig und falsch waren. Außerdem enthält der Worttrainer eine speichern und laden Methode und man kann im Konstruktor sowohl einen Pfad als auch einen SaveManager übergeben. Dies ist dafür da, das direkt ein alter Wortrainer geladen werden kann und um die Persistence umzusetzen.
## GUI
Die GUI ist für alle Anzeigen auf dem Bildschirm da. Also sowohl für die verarbeitung der Bildanzeige als auch für die Ausgabe der Statistik und die weiterleitung der Nutzereingaben.
