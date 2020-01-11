# Codename: REN Project

## Tema Proiectului

Platforma de tip stack overflow cu functionalitati de social network

## Echipa de proiect

Ene Mihai-Lucian, Radu Razvan Marian, Neagu Andrei-Cosmin

## Functionalitati

Platforma va oferi un spatiu de discutii intre utilizatorii inregistrati care vor avea
posibilitatea de a adauga si de a raspunde la intrebari din diferite categorii. Acestia vor putea
cauta intrebari sau raspunzuri pe baza unor tag-uri, adauga sau sterge de la favorite si vor oferi
o nota celorlalti utilizatori pe baza raspunsului oferit. In cazul in care intrebarea este mai
complexa, utilizatorii beneficiaza de un chat in timp real in care vor putea aprofunda subiectul.

## Tehnologii

Autentificare: Aws Cognito
Caching: Redis
Baza de Date: MySQL cu Hibernate ORM
Search: Elastic Search
Chat in timp real: SignalR
Unit test: Junit
Back-end: Spring
Front-end: Angular

# Docker

## MySql

```docker
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:latest
```
