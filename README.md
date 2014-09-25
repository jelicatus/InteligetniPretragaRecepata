<h1> 1. Opis problema </h1>
Aplikacija je izdrađena u okviru predmeta [Inteligentni sistemi](http://is.fon.rs/) koji se realizuje na Fakultetu Organizacionih nauka u Beogradu. Kreiran je interfejs, koji omogućava pretragu po različitim kriterijumima recepata, koji su predstavljeni korišćenjem RDF vokabulara [schema:Recipe](http://schema.org/Recipe) i smešteni u lokalni RDF repozitorijum. Pretraga poziva odgovarajuće SPARQL upite nad RDF repozitorijumom.	      

<h1> 2. Domenski model </h1>
U skladu sa RDF vokabularom [schema:Recipe], kreiran je domenski model, predstavljen sledećim dijagramom:
 
 <img src="https://github.com/jelicatus/InteligetniPretraga/blob/master/domenskiModelIntFinalni.png" />

<h1> 3. Rešenje </h1>
Kreirana je aplikacija koja omogućava vršenje pretrage recepata korišćenjem forme koja je implementirana pomoću JSF-a. Pretraga poziva SPARQL upit kojem je moguće zadati sledeće parametre: limit, minRatingValue, maxRatingValue, minCalories, maxCalories, mincarbohydrateContent, maxcarbohydrateContent, minFatContent, maxFatContent, minProteinContent, maxProteinContent, minFiberContent, maxFiberContent, maxCookTime, ingredients.

<h1> 4. Tehnička realizacija </h1>
Aplikacija je rađena u programskom jeziku Java.
U aplikaciji je korišćena [Jenabean biblioteka](https://code.google.com/p/jenabean/). [Jena TDB](http://jena.apache.org/documentation/tdb/) je komponeneta [Jena framework](https://jena.apache.org/)-a koja se koristi se za skladištenje RDF podataka i omogućava izvršavanje SPARQL upita nad sačuvanim podacima.
Primer SPARQL upita: vrati sve recepte koji sadrže jaja i imaju maksimalno 500 kalorija: <br/>
PREFIX schema: <http://schema.org/> PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>SELECT ?recipe  WHERE { ?recipe a schema:Recipe . ?recipe schema:ingredients ?eggs. FILTER (regex(STR(?eggs), "eggs", "i" )) ?recipe schema:nutrition ?nutrition. ?nutrition schema:calories ?calories. FILTER (?calories < 500) } <br/>
Forma preko koje korisnik unosi parametre za upite je rađena u biblioteci [JavaServer Faces - JSF](http://www.oracle.com/technetwork/java/javaee/javaserverfaces-139869.html). JSF je Java web framework koji se koristi za izgradnju korisničkih interfejsa na bazi komponenata za veb aplikacije.

<h1> Scenario of Use </h1>
Izrađena aplikacija se koristi na sledeći način: pri njemom pokretanju korisniku se prikazuje forma preko koje on unosi kriterijume pretrage, što je prikazano na sledećoj slici:
 <img src="https://github.com/jelicatus/InteligetniPretragaRecepata/blob/master/formaPretraga.png" />

Klikom na dugme "Return all recipes" korsniku se prikazuje tabela koja sadrži sve informacije o receptima, koji zadovoljavaju kriterijume pretrage, kako se i vidi na slici:
 <img src="https://github.com/jelicatus/InteligetniPretragaRecepata/blob/master/nadjeniRecepti.png" />



