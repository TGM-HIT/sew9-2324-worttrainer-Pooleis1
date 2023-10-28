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

---

## Statistik
Für die Statistik gibt es eine eigene Klasse inder alle Versuche und Daten dazu gespeichert werden sollen.

## Persitence
Die Persitence wurde mittels Strategy Pattern umgesetzt um dieses Austauschbar zu machen. Hier gibt es ersteinmal ein Interface welches laden und speichern Methoden vorschreibt. 
### JSON
Der Worttrainer wird in ein JSON File gespeichert indem alle Wortpaare, sowie das ausgewählte Wort und die Statistiken gespeichert werden.
```json
{
  "wordpairs":[
    {
      "word":"tgm",
      "imageUrl":"https://upload.wikimedia.org/wikipedia/commons/b/ba/TGM_Logo.png"
    },
    {
      "word":"untis",
      "imageUrl":"https://neilo.webuntis.com/assets/images/logo.png"
    }
  ],
  "correct":10,
  "wrong":90,
  "tryed": 100,
  "picked": {
      "word":"tgm",
      "imageUrl":"https://upload.wikimedia.org/wikipedia/commons/b/ba/TGM_Logo.png"
   }
}
```

Somit kann man auch Worte hinzufügen ohne den Code anzugreifen.
