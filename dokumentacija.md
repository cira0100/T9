# API





## Registracija.java

### /registrujRadnika

Parametri :
1. String username
2. String password
3. String mail
4. String ime
5. String prezime
6. String date
7. String slika

Vrsi registraciju radnika u bazi.

### /registrujPoslodavca

Parametri :
1. String username
2. String password
3. String mail
4. String naziv
5. String adresa
6. String slika

Vrsi registraciju poslodavca u bazi.

### /registrujAdmina

Parametri :
1. String username
2. String password
3. String mail
4. String ime
5. String prezime
6. String token

Vrsi registraciju admina u bazi.

### /registrujOglas

Parametri :
1. String naziv
2. String datum
3. String kategorija
4. String podkategorija
5. int plata
6. String slika
7. String tekst
8. boolean remote
9. String token

Omogucava poslodavcu da registruje oglas na sajtu.

### /vratiTip

Parametri :
1. String token

Vraca tip usera koji je poslao svoj token.

### /getPodKategorije

Parametri :
1. String kategorija

Salje listu stringova pod kategorija za selektovano kategoriju.

### /lajkujOglas

Parametri :
1. String token
2. int idOgl

Lajkuje/Dislajkuje oglas sa idOgl u slucaju da token pripada radniku.


### /postKom

Parametri :
1. String token
2. int idOgl
3. String tekst

Postuje komentar na oglasu sa poslatim id-jem.

### /prijavaOglas

Parametri :
1. String token
2. int idOgl
3. String cv

Prijavljuje radnika na oglas.

### /obrisiKom

Parametri :
1. String token
2. int idKom

Brise komentar.

### /obrisiOglas

Parametri :
1. String token
2. int idOglas

Brise oglas.

### /obrisiKorisnika

Parametri :
1. String token
2. int idUsera

Brise korisnika.

### /oceniPoslodavca

Parametri :
1. String token
2. int idPos
2. int ocena

Ocenjuje poslodavca sa datom ocenom.











## PageController.java

### /

Vraca index stranicu.


### /trazi

Parametri :
1. String pretraga
2. String kategorija
3. String podkategorija
4. String remote

Vrsi pretraga oglasa po poslatim parametrima i vraca stranicu index.

### /listaPoslodavaca

Parametri :
1. String pretraga
2. String kategorija
3. String podkategorija
4. String remote

Vrsi pretraga oglasa po poslatim parametrima i vraca stranicu index.

### /login1

Vraca login stranicu


### /register1

Vraca register stranicu

### /error

Vraca error stranicu



### /adminIndex

Vraca index stranicu za admina.


### /adminIndexTrazi

Parametri :
1. String pretraga
2. String kategorija
3. String podkategorija
4. String remote

Vrsi pretraga oglasa po poslatim parametrima i vraca stranicu indexa za admina.

### /listaKorisnika

Parametri :
1. String token

Ukoliko token pripada adminu vraca stranicu svih registrovanih korisnika.

### /dodajAdmina

Vraca stranicu sa formom za dodavanje novog admina.

### /oglas

Parametri :
1. int idOglasa

Vraca stranicu oglasa koji zadovoljava id.

### /oglasRadnik

Parametri :
1. int idOglasa
2. String token


Vraca stranicu oglasa koji zadovoljava id i ukoliko token pripada radniku dodaje funkcionalnosti vezane za radnika.

### /oglasPoslodavac

Parametri :
1. int idOglasa
2. String token


Vraca stranicu oglasa koji zadovoljava id i ukoliko token pripada poslodavcu dodaje funkcionalnosti vezane za poslodavca.

### /oglasAdmin

Parametri :
1. int idOglasa
2. String token


Vraca stranicu oglasa koji zadovoljava id i ukoliko token pripada adminu dodaje funkcionalnosti vezane za admina.

### /poslodavacIndex

Vraca index stranicu za poslodavca.

### /poslodavacIndexTrazi

Parametri :
1. String pretraga
2. String kategorija
3. String podkategorija
4. String remote

Vrsi pretraga oglasa po poslatim parametrima i vraca stranicu indexa za poslodavca.

### /poslodavacIndexTrazi

Parametri :
1. String token


Vrsi stranicu sa oglasima poslodavca koji je prosledio svoj token.

### /dodajOglas


Vrsi stranicu sa formom za dodavanje oglasa.

### /radnikIndex

Vraca index stranicu za radnika.

### /radnikIndexTrazi

Parametri :
1. String pretraga
2. String kategorija
3. String podkategorija
4. String remote

Vrsi pretraga oglasa po poslatim parametrima i vraca stranicu indexa za radnika.

### /listaPoslodavacaRadnik


Vraca listu poslodavaca za radnika.

### /mojePrijave

Parametri :
1. String token

Vraca stranicu sa prijavama radnika koji je poslao svoj token.


### /listaPoslodavacaTrazi

Parametri :
1. String pretraga

Pretrazuje poslodavce po nazivu i vraca stranicu onih koji zadovoljavaju pretragu.



### /listaPoslodavacaRadnikTrazi

Parametri :
1. String pretraga

Pretrazuje poslodavce po nazivu i vraca stranicu onih koji zadovoljavaju pretragu.





## UploadController.java

### /upload

Parametri :
1. MultipartFile file

Dobija file i uploaduje ga na serveru u direktorijum files.File mora biti manji od 10 MB.





## Login.java



# Klase

## Admin.java

### Prosiruje klasu korisnik sa:

String ime - ime admina
String prezime - prezime admina

## Baza.java

## EMailSender.java

### void SendEmail(String email,String Naziv,String cv)

Funkcija za testiranje slanja email-ova, zamenjena funkcijom void SendEmailAttachement(String email,String Naziv,String fileToAttach)
Nije u upotrebi - DEPRECATED

### void SendEmailAttachement(String email,String Naziv,String fileToAttach)

Aktuelna funkcija za slanje emailova poslodavcu pri prijavi radnika, uz attachovan CV, od strane zvanicnog emaila sajta

## Komentar.java

### Klasa koja sluzi za postovanje komentara na neki oglas

String s1 - Ukoliko je poster Poslodavac predstavlja Naziv , inace Ime
String s2 - Ukoliko je poster Poslodavac predstavlja Adresu , inace prezime
String tekst - sadrzaj komentara
int id - autonumber koji se prosledjuje kada admin pregleda stranicu da bi mogao da obrise komentar

## Korisnik.java

### Klasa koja sadrzi sve osnovne informacije o korisniku, dalje prosirena na osnovu tipa(1-radnik,2-poslodavac,3-admin):

int id - autonumber jedinstveni id korisnika
String username - jedinstveni username korisnika
String password - hashirana sifra korisnika
String mail - email korisnika
int type - tip korisnika
String token - jedinstveni generisani token korisnika koji se cuva u cookie-u

## Oglas.java

### Sadrzi sve Informacije o oglasu:

int IdOglasa - autonumber jedinstveni id oglasa
String Naziv - Naslov Oglasa
int IdPoslodavca - jedinstveni id posladavaca koji je objavio oglas
Date DatumPostavljanja - automatski setovan datum objave oglasa
Date DatumIsteka - datum do kada se radnici mogu prijaviti na oglas
int Lajkovi - broj lajkova oglasa
int Posete - NOT IMPLEMENTED
String Kategorija - Kategorija radnog mesta
String PodKategorije - Podkategorija kategorije
int plata - ponudjena plata za radno mesto; ukoliko je ostavljena prazna setuje se na -1 a na oglasu korisnik vidi "Po dogovoru"
String Slika - hashiran string naziva slike koja se cuva u files/ 
String Tekst - detaljan opis radnog mesta
boolean Remote - da li je oglas od kuce ili ne (true-da;false-ne)

## PasswordCrypt.java

### String hashPasword(String password)

Prima plain text sifru (String password), hashira je, zatim vraca hashiranu sifru (String passwordH)
Pozivana je u bazi prilikom registracije korisnika (boolean registruj)

### boolean checkPassword(String password_plaintext, String stored_hash)
	
Prima plain text sifru (String password_plaintext) i hashiranu sifru (String stored_hash) , uporedjuje da li je plaintext sifra odgovarajuca ili ne
Pozivana je u bazi prilikom logina gde joj se setuje (String stored_hash) na odgovarajucu vrednost iz kolone a (String password_plaintext) se dobija od usera koji pokusava da se uloguje

## Podaci.java

### Klasa koja sluzi sa izlistavanje osnovnih podataka na admin stranici za brisanje korisnika

int id - jedinstveni id preko koga se dalje u tabeli svih korisnika on trazi i brise
String username - username korisnika; prikazani samo da bi admin znao koga da obrise
String mail - email korisnika; prikazani samo da bi admin znao koga da obrise

## Poslodavac.java

### Prosiruje klasu korisnik sa:

String naziv - naziv firme poslodavca
String adresa - adresa firme poslodavca
String slika - autosetovani string naziva slike koja se cuva u files/
Double ocena - prosek svih ocena dodeljenih od strane radnika

## Radnik.java

### Prosiruje klasu korisnik sa:

String ime - ime radnika
String prezime - prezime danika
Date date - datum rodjenja radnika
String slika - autosetovani string naziva slike koja se cuva u files/
