#Large cities suggestion service.

## Base info

Service provides auto-complete suggestions for large cities.

- The endpoint is exposed at `api/v1/cities/suggestions`
- The partial (or complete) search term is passed as a querystring parameter `q`
- The caller's location can optionally be supplied via querystring parameters `latitude` and `longitude` to help improve relative scores
- The endpoint returns a JSON response with an array of scored suggested matches
    - The suggestions are sorted by descending score
    - Each suggestion has a score between 0 and 1 (inclusive) indicating confidence in the suggestion (1 is most confident)
    - Each suggestion has a name which can be used to disambiguate between similarly named locations
    - Each suggestion has a latitude and longitude

The service provided sorted auto-complete suggestions for large cities. 
The parameters for find and sort algorithm are name and distance from requester. 
It is also checks the alternative names of the cities like ‘NYC’ for New York. 
The database is a part of US/Canada cities dump by GeoNames. 
This is just a PoC, so the list is not full.

#API
2.API

The service is available by the link below.
(This is basic app-url and dosnt provide any raw endpoints. 
You have to compose the URL using base-url, endpoint path and query parameters.
You can find exemples in the "example of request URL" below)

https://city-suggestions.herokuapp.com/

###The base service url 
| base url|
|  :--------: | 
| api/v1/cities| 

###The endpoint

| Headers       | Body        | Method |path|Description|
| :-------------: |:-------------:| :-----:| :-------------: |:-------------:|
| -     | - |GET|/suggestion|provides main functionality|


| RequestHeaders     | Body        | Method |path|Description|
| :-------------: |:-------------:| :-----:| :-------------: |:-------------:|
| -     | - |GET|/suggestion|endpoint|


#Necessary and unnecessary URL parameters

| Param Name    | necessary       | example|target type|Description|
| :-------------: |:-------------:| :-----:| :-------------: |:-------------|
|  q     | + |?q=Lon|String|charsequence to search for any matches with names or alt.names of the cities. Can be the alt.name, the part or full name of the city user is looking at.
latitude |-|?laltitude=34.5456|double|The latitude of the point user looking at. It can be use as a user location to specify the closest targets
longitude|-|?longitude=-45|double|The longitude. Same as previous.|

###The example of the request URL:
​​https://city-suggestions.herokuapp.com/api/v1/cities/suggestions?latitude=32.05&q=jo&longitude=-34.56


## Sample responses

These responses are meant to provide guidance. The exact values can vary based on the data in the request params.

**Match**

    GET /suggestions?q=Yor&latitude=43.70011&longitude=-79.4163

```json
{"suggestions":[
  {
    "name":"York, PA, USA",
    "latitude":"39.96260",
    "longitude":"-76.72774",
    "score":0.5
  },
  {
    "name":"York, SC,USA",
    "latitude":"34.99430",
    "longitude":"-81.24202",
    "score":0.5
  },
  {
    "name":"Joplin, MO, USA",
    "latitude":"37.08423",
    "longitude":"-94.51328",
    "score":0.4
  },{
    "name":"Johnston, IA, USA",
    "latitude":"41.67304",
    "longitude":"-93.69772",
    "score":0.3
  },{
    "name":"Jonquière, 10, Canada",
    "latitude":"48.41648",
    "longitude":"-71.24884",
    "score":0.3
  },{
    "name":"Jonesboro, AR, USA",
    "latitude":"35.84230",
    "longitude":"-90.70428",
    "score":0.3
  },{
    "name":"Johnsburg, IL, USA",
    "latitude":"42.38002",
    "longitude":"-88.24203",
    "score":0.2
  }
]}

```

**No match**

    GET /suggestions?q=HoHoHoHoLand

```json
{
  "suggestions": []
}
```

###Additional api 
The service provided actuator info endpoint.
Use the links:
https://city-suggestions.herokuapp.com/actuator/info


## References

- Geonames provides city lists Canada and the USA http://download.geonames.org/export/dump/readme.txt
