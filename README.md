# Simple Bloom Filter API - Kotlin + Spring Boot

### The Objective was to solve this Kata: http://codekata.com/kata/kata05-bloom-filters/ 

### I took the opportunity to learn more about Kotlin using Spring Boot to help me handle all dependencies

### I'm using a spell check .txt file as a dataset to avoid OS dependencies 
### You can find it on `main/resources/static` folder it's include A LOT of words

### I choose to use Spring Boot to create an API instead of a `regular` Kotlin project  

## ENDPOINTS

### There are 2 Endpoints

### GET /{word}
##### Provide a word as param, and the bloom filter will verify if knows it or not
##### It will return True or False and a Message
#### Example GET /carolina will return:
```
{
  "found": true,
  "message": "Word carolina Found"
}
```
### POST /
##### Create a random 5 character word and verify with bloom filter if knows it
##### It will return True or False and a Message
##### Case it's True, Probably it is a False Positive
##### Case it's False it will add that word to the dictionary
#### POST / will return something like this (False Positive):
```
{
  "found": true,
  "message": "Word Iaarv 'Found' Probably a False Positive"
}
```
#### POST / will return something like this (not know):
```
{
  "found": false,
  "message": "Word IkLty Not Found And Added to The Dictionary"
}
```

## Test
### Please go to `test` folder and take a look on the unit and integration tests

## Fell free to play and improve it
#### I also added an Insominia export file to help with the Endpoints
