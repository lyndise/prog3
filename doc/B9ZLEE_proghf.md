---
title: 'Feladatlista alkalmazás'
subtitle: "A programozás alapjai 3. házi feladat"
author:
- Telek Veronika, B9ZLEE
date: '2020.11.30.'
hyperrefoptions:
- linktoc=all
- colorlinks=false
papersize: a4
documentclass: article
geometry:
- top=25mm
- left=25mm
- bottom=25mm
- right=25mm
- heightrounded
linestretch: 1.5
toc: true
numbersections: true
mainfont: 'Linux Libertine O'
lang: hu
---
# User manuál

A házi feladatban egy grafikus feladatlista alkalmazást valósítottam meg. A programban lehetőség van táblák felvételére, melyeken belül kategóriákat lehet létrehozni. A kategóriákhoz lehet felvenni feladatlistákat és szöveges emlékeztetőket. Ezek külön-külön oszlopban jelennek meg a kategória alapján. A táblákban van egy alapértelmezett oszlop, amiben a címke nélküli, illetve a kategória szintű címkével nem rendelkező bejegyzések szerepelnek. A bejegyzések több oszlopban is megjelenhetnek, ha több olyan címke is szerepel rajta, amely kategória is.

Az egyes feladatlistáknak, emlékeztetőknek lehet címet adni, illetve ezekhez lehet címkéket hozzárendelni, levenni. Az emlékeztetőknél szabad szöveges részt lehet megadni, a feladatlistáknál pedig részfeladatokat lehet felvenni, melyek állapota jelölőnégyzettel változtatható. Az emlékeztetők, feladatlisták törölhetőek. A kategóriák is levehetőek, az olvan bejegyzések, amelyek nem rendelkeznek kategória szintű címkével, átkerülnek az alapértelmezett oszlopba.

A táblákban szereplő elemeket archiválni lehet, ami által az adott elem lekerül a táblájáról, és az archivált elemek közé kerül. Az archivált elemeket is lehetséges szerkeszteni, kategóriákba sorolni. Az archivált táblát nem lehet törölni, csak a benne szereplő bejegyzéseket lehetséges.

A program mentéskor az adatokat egy fájlba menti, és betöltéskor ebből olvassa be azokat. 

# Osztálydiagram

# Adatszerkezetek, osztályok, tervezési megfontolások

(melyik osztály, típus és adatszerkezet mire való, mit tárol, miért arra esett a választás) 

A programot a JavaFX grafikus keretrendszer segítségével valósítom meg.

# Use-case-ek

A felhasználó a következő funkciókat hajthatja végre:

1. Táblák listázása

A program indulásakor a felhasználó a táblák listáját látja. A táblák listája alatt új tábla létrehozása gomb és keresőmező szerepelnek. Az archivált elemek táblája is gombnyomásra érhető el, ezt nem lehet törölni.

2. Tábla megnyitása

A táblák listázása oldalon egy meglévő tábla nevére kattintva megjelenik a kiválasztott tábla.

3. Új tábla létrehozása

A táblák listázása oldalon a megfelelő gombra kattintva, vagy a menüből az új tábla létrehozását kiválasztva megjelenik egy felugró ablak, amiben meg lehet adni az új üres tábla nevét. Ezután az új tábla a táblák listájában megjelenik.

4. Tábla nevének szerkesztése

A tábla megnyitása után a megfelelő gombra kattintva szerkeszthetővé válik a tábla neve, és megjelenik egy mentés gomb. A mentés gombra kattintva a tábla neve rögzítésre kerül, ha üres marad a szövegdoboz, akkor nem történik változás.

5. Tábla törlése

A tábla megnyitása után a megfelelő gombra kattintva törölni lehet a táblát. A törléshez megerősítést kér a felhasználótól a program.

6. Új kategória felvétele

A tábla nézetnél egy gomb és szövegmező segítségével új kategóriát lehet felvenni. A kategória egy oszlopként jelenik meg a táblán.

7. Kategória levétele a tábláról

A kategóriához tartozó gombra kattintva az adott kategória levételre kerül a tábláról.

8. Új feladat létrehozása

A kategórián belüli gombra kattintva az adott kategóriához új feladatot vehetünk fel. Az új feladat típusát egy felugró ablakban lehet kiválasztani, utána egy másik felugró ablakban a feladat nevét lehet megadni.

9. Feladat megnyitása

A feladathoz tartozó gombra kattintva megjelenik az adott feladat. A feladat neve, a feladat szövege, a részfeladatok és a címkék ezen a képernyőn szerkeszthetők.

10. Részfeladat hozzáadása a feladathoz

Új részfeladatot egy gomb segítségével tudunk felvenni a feladat szerkesztése képernyőn.

11. Részfeladat törlése

Adott részfeladatot a hozzá tartozó gombbal tudunk törölni.

12. Részfeladat megjelölése

A részfeladathoz tartozik egy jelölőnégyzet, amire kattintva a részfeladat megjelölésének állapota változtatható.

13. Címke hozzárendelése feladathoz

A feladat szerkesztése képernyőn egy szövegdoboz segítségével a mellette lévő gombra kattintva vehetünk fel a feladathoz új címkét.

14. Címke levétele a feladatról

A címke melletti gombra kattintva az adott címkét levehetjük a feladatról.

15. Feladat archiválása

A feladat szerkesztése képernyőn az archiválás gombra kattintva a feladat átkerül az archivált elemek táblára. Az archiváláshoz megerősítést kér a felhasználótól a program.

16. Feladat törlése

A feladat szerkesztése képernyőn a törlés gombra kattintva a feladat törlésre kerül. A törléshez megerősítést kér a felhasználótól a program.

17. Feladat keresése

A táblák listájának oldalán a kereső mezőbe a kifejezést beírva megjelenik a találathoz tartozó feladatok listája. Az adott feladathoz tartozó gombra kattintva megnyithatjuk a feladatot. A keresés mező mellett kiválaszthatjuk, hogy az archivált elemek között is keressen-e.

18. Táblák mentése

A menüben a mentést kiválasztva fájlba lehet menteni a táblák állapotát. Mentés előtt a felhasználótól megerősítést kér.

19. Táblák betöltése

A menüben a betöltést kiválasztva fájlból be lehet olvasni a táblák állapotát. Betöltés előtt a felhasználótól megerősítést kér.

# Fájlok szerkezete

Pontos specifikáció arról, hogy milyen bemenetek, kimenetek vannak

Az adatokat JSON formátumú fájlokban tárolom. A táblák listája egy fájlba kerül, valamint minden egyes tábla külön fájlba kerül a hozzá tartozó feladatokkal együtt.