<p>Proiectul pe care am decis sa il implementez este un boardgame ce mi s-a parut atractiv(idee proprie, nu se afla in propunerile de proiecte)-> se numeste Razboi in 8 si se joaca pe o tabla de sah. Sunt 2 playeri, fiecare are piesele sale; piesele pot fi mutate doar pe diagonala, acesta este si modul in care poti lua piesa adversarului-> atacand pe diagonala. Castiga cel care are cel mai mare scor. </p>
<p>Voi descrie in cele ce urmeaza detaliile proiectului pe care le-am implementat:</p><br />
Organizarea fisierelor
<ul type="bullet">
	<li>Am incercat sa fiu cat mai organizat, pentru ca totul sa fie usor de citit si modificat, usor de gasit. Resursele precum imagini, documente css, fisiere media si file-uri FXML ale Scene Builder se regasesc in folderul "resources"</li>
</ul>
Interfata grafica JavaFX
<ul type="bullet">
	<li>JavFX s-a dovedit a fi alegerea perfecta pentru proiectul meu, imi doream sa folosesc o tehnologie mai avansata, si care imi poate oferi mult mai mult decat ar putea Swing</li>
</ul>
Comunicare cu o baza de date relationala sau de alt fel (no-sql, object-oriented, graph, etc); sau cu alt sistem de gestiune a datelor (xml, json, etc).
<ul type="bullet">
	<li>Playerii se autentifica sau se inregistreaza inainte sa intre in joc. Se foloseste astfel o baza de date Oracle. De asemenea am folosit adesea lucrul cu fisiere pentru a salva diverse date din cadrul jocului</li>
</ul>
O parte algoritmica
<ul type="bullet">
	<li>Algoritmul meu e pur si simplu legat de mutarile pe tabla de sah, modurile in care cei doi jucatori se pot lua unul pe altul, modul de castig, scorul</li>
</ul>
Orice alte tehnologii suplimentare pot creste valoarea proiectului (servicii REST, programare de retea, multi-threading, etc)
<ul type="bullet">
	<li>Lucrurile interesante pe care le-am folosit: Singleton-uri pentru a salva starea(am avut nevoie pentru a-mi salva stage-ul si sa am mereu FullScreen, dar si ca sa pot inchide audio-ul din setarile jocului), multi-threading pentru a implementa un timer in JavaFX, SceneBuilder(a fost provocator sa invat sa il utilizez)</li>
</ul>
Probleme intampinate
<ul type="bullet">
	<li>A fost greu sa invat JavaFX, deoarece nu prea a fost abordat la laborator, a fost ceva nou.</li>
</ul>
Cum as putea sa progresez mai departe
<ul type="bullet">
	<li>Mi-ar fi placut ca jocul sa fie in retea. De asemenea, ar fi fost super un mod Player vs Computer, insa nu am mai avut timp pentru asta</li>
</ul>

<br />