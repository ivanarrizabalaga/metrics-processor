# Metrics processor

Super simple metrics processor that receives events from a metrics collector and persist them into a MongoDB database.

### Data format
Each saved record has the following structure:
 * key: The id that identifies the metrics measured (Ex: 'jvm-usage').
 * timeStamp: When was the measurement taken.
 * value: The value of the measurement whatever that means.
 * error: An optional error message describing what went wrong while capturing the measurement.

### Tech

The metrics processor system runs on top of:

 * Spring boot
 * Hypermedia-based RESTful front end
 * Spring Data REST
 * Spring Data MongoDB

### Requisites
 * MongoDB running (http://docs.mongodb.org/manual/installation/)
 * JDK 1.7

### Run
 * Run directly with gradle: ./gradlew bootRun
 * Build: ./gradlew build
 * From jar: java -jar build/libs/metrics-processor-0.1.0.jar
 * Change default port: java -Dserver.port=8081 -jar build/libs/metrics-processor-0.1.0.jar

###Usage

Check top level service:

```
$ curl http://localhost:8080
{
  "_links" : {
    "measurements" : {
      "href" : "http://localhost:8080/measurements{?page,size,sort}",
      "templated" : true
    }
  }
}
```

List measurements
```
$ curl http://localhost:8080/measurements
{
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/measurements{?page,size,sort}",
      "templated" : true
    }
  },
  "_embedded" : {
    "measurements" : [ {
      "key" : "num-registered-users",
      "timeStamp" : "2015-12-20T00:00:00.000+0000",
      "value" : 3.0,
      "error" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/measurements/54955d8444ae0f9f7ed65827"
        }
      }
    }, {
      "key" : "jvm-usage",
      "timeStamp" : "2013-03-03T21:15:00.030+0000",
      "value" : 0.10,
      "error" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/measurements/54955e1d44ae0f9f7ed65828"
        }
      }
    } ]
  },
  "page" : {
    "size" : 20,
    "totalElements" : 2,
    "totalPages" : 1,
    "number" : 0
  }
```
Get one specific measurement:
```
$ curl http://localhost:8080/measurements/54955e1d44ae0f9f7ed65828
{
  "key" : "jvm-usage",
  "timeStamp" : "2013-03-03T21:15:00.030+0000",
  "value" : 0.10,
  "error" : null,
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/measurements/54955e1d44ae0f9f7ed65828"
    }
  }
```

Add a new record:
```
$ curl -i -X POST -H "Content-Type:application/json" -d '{  "key" : "jvm-Stamp":"2015-12-12T12:12:12.000Z","value":0.10 }' http://localhost:8080/measurements
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
Location: http://localhost:8080/measurements/5495645b44ae9593322d33f7
Content-Length: 0
Date: Sat, 20 Dec 2014 11:58:19 GMT

```

Finally, since metric values should be checked independently, an additional find operation has been added:
```
curl http://localhost:8080/measurements/search/findByKey?key=jvm-usage
{
  "_embedded" : {
    "measurements" : [ {
      "key" : "jvm-usage",
      "timeStamp" : "2014-12-20T12:25:24.216+0000",
      "value" : null,
      "error" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/measurements/54955c2a44ae496a41e4612a"
        }
      }
    } ]
  }
}
```