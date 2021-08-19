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

### String GenerateToken()

Generise token za svakog korisnika koji sluzi za dalju autentifikaciju

### String postojiKorisnik(String username, String email)

Proverava da li vec postoji korisnik sa prosledjenim username-om i email-om u bazi, i pomocu f-je GenerateToken() osigurava da je korisnicki token jedinstven u bazi

### boolean  registruj(Korisnik korisnik) 

Prima parametre sa stranice registracije i registruje Radnika ili Poslodavca u zavisnosti od izbora. Prvo generise osnovne informacije u tabeli `glavna` a zatim na osnovu tipa upisuje licne informacije u tabele `radnik` ili `poslodavac` u zavisnosti od tipa

### boolean  registruj(Korisnik korisnik,String pomToken)

Overloadana funkcija registruj koja dozvoljava adminu da registruje novog admina

### String Login(String username,String password)

Funkcija koja sluzi za logovanje na sajt, prvo proverava da li username postoji u bazi, a zatim proverava da li prosledjena plaintext sifra odgovara sacuvanoj hashiranoj sifri
Vraca token pri uspesnom logovanju, null inace

### int TokenToId(String token)

Za prosledjeni token vraca jedinstveni Id korisnika ako taj token postoji u tabeli `glavna` inace vraca -1

### ArrayList<Oglas> oglasiPoslodavca(String token)

Proverava da li poslodavac postoji u bazi i poziva overloadanu funkciju oglasiPoslodavca(int IdPoslodavca)

### ArrayList<Oglas> oglasiPoslodavca(int IdPoslodavca)

Za dobijeni id poslodavca, stavlja sve njegove oglase u Arraylistu

### ArrayList<Poslodavac> traziPoslodavca(String pretraga)

Za primljeni String pretraga vrsi case insensitive regex pretragu, slika je encodirana u base64 zbog lakseg prikaza.

### boolean daLiJeMojOglas(String token, int idOgl)

Za primljeni token korisnika i id oglasa, proverava da li je taj korisnik(poslodavac) vlasnik poster tog oglasa (true ako jeste)

### ArrayList<Radnik> prijavljeniNaOglas(String token,int idOgl)

Prvo pita da li oglas (idOgl) pripada poslodavcu sa prosledjenim tokenom, ako da vraca Arraylistu sa osnovnim podacima radnika koji su se prijavili na taj oglas

### ArrayList<Oglas> mojePrijaveOglasa(String token)

Za primljeni token radnika vraca Arraylistu svih oglasa na koje se on prijavio

### boolean prijaveMeNaOglas(String token,String cv,int idOglasa)

Prvo proverava da li je validan primljeni token, zatim da li je tip korisnika radnik, ukoliko oba jesu validni, onda pita da li je radnik vec prijavljen, ako nije onda trazi email poslodavca i salje mu email sa attachmentom i ubacuje prijavu tog radnika u tabelu `prijave`

### vratiType(int id)

Za primljeni id korisnika vraca kog je tipa (1-radnik,2-poslodavac,3-admin)

### boolean postOglas(String token,Oglas oglas)

Proverava validnost tokena i da li je korisnik poslodavac, i ako jeste dodaje u tabelu `oglas` novi oglas ciji su detalji dobijeni u objektu Oglas oglas (prosledjen od strane kontrolera)
Vraca true/false u zavisnosti da li je oglas uspesno postan

### boolean postKom(String token,int idOgl,String tekst)

Proverava validnost tokena korisnika i ako je validan  dodaje novi komentar u tabelu `komentari`

### ArrayList<String> basicInfo(int iduser)

Vraca najosnvnije podakte korisnika koje se na frontend-u ispisuju kada korisnik doda komentar na oglas

### ArrayList<Komentar> listKom(int idogl)

Izlistava sve komentare koje su korisnici dodali na oglas sa primljenim id-om idogl.




### boolean daLiJeMojKom(int idkom,String token)
Vraca true ili false u zavisnosti da li je `idkom` pripada korisniku sa tokenom `token`.

### boolean brisiKom(int idkom,String token)

Metoda kojom admin brise komentare sa nekog oglasa.
Na pocetku se vrsi autentikacija da li token pripada adminu ako pripada komentar ce se izbrisati is tabele `komentari`.
Vraca true ili false u zavisnosti od uspeha.

### boolean brisiOgl(String token,int idogl)

Metoda na pocetku proverava da li postoji oglas sa tim id-jem nakon toga proverava da li token pripada adminu ili poslodavcu koji je objavio taj oglas. U koliko su prethodni zahtevi zadovoljeni oglas pocinje sa brisanje. Tako sto prvo brise prijave , lajkove , komentare i na kraju oglas i sliku tog oglasa.
Vraca true ili false u zavisnosti od uspeha.

### boolean lajkDislajk(String token,int idogl)

Metoda koja dodaje lajk ako on ne postoji ,a ako postoji ona ga brise iz baze.
Prvi se vrsi verifikacija da li je onaj koji lajkuje oglas radnik u koliko jeste.
Vraca true ili false u zavisnosti od uspeha


### boolean updateLajk(int idogl)

Prebrojava broj lajkova u tabeli `lajkovioglasa` i azurira taj broj u tabeli `oglas` gde je id oglasa odgovaracuji.
Vraca true ili false u zavisnosti od uspeha.


### boolean brisiPrijaveRadnika(int iduser)

Brise prijave radnika iz tabele `prijave`

### void updateLajkPostDel()

Osvesi broj lajkova nakon brisanja lajkova radnika.

### boolean brisiLajkoveRadnika(int iduser)

Metoda za brisanje lajkova radnika koristi se tokom brisanja radnika. Brise lajkove iz tabele `lajkovioglasa`.


### boolean brisiUsera(int iduser,String token)

Metoda kojom admin brise korisnika. Admin prosledjuje svoj token i id korisnika kojeg zeli da obrise. Ako token stvarno pripada adminu korisnik ce biti obrisan.
Vraca true ili false  u zavisnosti od uspeha.

### ArrayList<Komentar> listSveKom(int idKorisnika)

Vraca listu svih komentara koji je neki korisnik postovao.

### boolean brisiUsera(String token)

Dozvoljava korisniku da obrise svoj account. Trenutno nije implementovana u frond end.


### ArrayList<Podaci> listUsers(String token)

Dobija token i u slucaju da je to token admina funkcija ce izvrsiti upit ka tabeli `glavna` odakle ce izvuci osnovne podatke o korisnicima i vratiti ih adminu.


### ArrayList<Oglas> sviOglasi()

Vrsi upit ka tabeli `oglas` gde se nalaze oglasi pravi objekte oglasa i smesta ih u listu oglasa. Na kraju vraca listu oglasa.


### ArrayList<String> listKat()

Vraca sve kategorije iz tabele `kategorije` kao listu stringova.

### ArrayList<String> listPodKat(String kat)

Dobija naziv kategorije i pomocu njega vrsi upit ka tabeli `kategorije` gde ce izuci svoj id. I onda upita tabelu `podkategorije` gde je idKategorije jednak izvucenom id-ju. Vraca listu stringova podkategorija te kategorije.


### ArrayList<Oglas> pretOglase(String naziv,String kat,String podkat,String remote)

Dobija parametre za pretragu oglasa. U sebi poziva metodu `sviOglasi();` koja ce da vrati sve oglase. Nakon toga onda regexom proverava da li se prosledjeni parametri nalaze u nekom oglas i ako se nalaza ti oglasi se dodaju u novu listu koja se vraca.

### String encoder(String imagePath)
Dobija imagePath gde se nalazi lokacija slike. Zatim tu sliku encodira u base64 da bi mogla da se prikaze na front end-u. I vraca base64 string te slike.


### Oglas vratiOglas(int id)
Vraca oglas sa informacijama za koji je prosledjen id.

### boolean oceniPoslodavca(String token,int idpos,int ocena)

Dodaje se ocena poslodavcu u tabeli `ocene`. Ukoliko je radnik vec ocenio poslodavca ocena se samo azurira.
Vraca true ili false u zavisnosti da li je funkcija izvrsene bez greske.


### boolean updateOcene(int idpos)

Uzima srednju ocenu poslodavca iz tabele `ocene` i postavlja je na ocenu u `poslodavac` tabeli.
Vraca true ili false u zavisnosti da li je funkcija izvrsene bez greske.


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
