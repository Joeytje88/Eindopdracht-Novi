# Eindopdracht-Novi

Snelle installatie gids


Om mijn project te kunnen draaien, zijn de volgende programma’s nodig:

- PostgreSQL
- IntelliJ (of een andere IDEA)
- Visual Studio Code (geen vereiste kan ook met de IDEA)

Nu kunt u beginnen met het starten van de IDEA en het openen van het backend gedeelte. 
Voor het draaien van de backend moet eerst de juiste gegevens voor de postgreSQL database worden ingevoerd in de application.properties bestand. Dit bestand is te vinden in de resources folder van de backend.

Deze staan nu op mijn gegevens, maar de kans is groot dat deze niet werken bij u, aangezien u waarschijnlijk andere inloggegevens heeft.
Dit kunt u eventueel checken door (in IntelliJ) rechtsboven op het scherm op database (in kleine letters tegen de zijkant van het scherm) te klikken en daar te testen of er een verbinding is met de database of daar  eventueel een connectie aanmaken met de database. Deze heeft bij mij de naam test gekregen. Deze naam kunt u eventueel wijzigen, aangezien er iedere keer dat de backend gestart wordt de database opnieuw gevuld wordt. 
Echter als u de naam verandert, dient deze ook in de application.properties bestand worden aangepast.

Het opstarten van de backend gaat door middel van het commando : mvn spring-boot:run in te voeren in de terminal van de IDEA.

Zodra u een connectie heeft gemaakt, kunt ook het frontend gedeelte opstarten. Dit kan door het project in een nieuw venster in IntelliJ te openen of de folder te openen in Visual Studio code. Voor dat u het frontend gedeelte start, dient u eerst de npm packages te installeren. Dit doet u door de volgende commando in de terminal in  te voeren: NPM install. Bij mij kreeg ik na het verwijderen van de Node Modules een foutmelding bij het opnieuw installeren ervan.  Mocht dit ook bij u gebeuren, draai dan ook even npm audit fix. Dat was de enige oplossing, waardoor ik hem weer draaiende kreegZodra dit eenmaal geïnstalleerd is, kunt u ook de frontend draaien door het commando npm start in de terminal in te voeren.

Zodra dit draait , wordt de website geopend in uw browser en kunt u aan de slag met de website. Mocht dit niet gebeuren, ga dan naar http:// localhost:3000 om de pagina alsnog te openen.
