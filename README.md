# Prerequisite

* A docker registry runs under `localhost:5000`.

# Build

```
(cd filter \
    && mvn clean install)
(cd service-1 \
    && mvn clean install \
    && docker build . -t localhost:5000/service-1 \
    && docker push localhost:5000/service-1)
(cd service-2 \
    && mvn clean install \
    && docker build . -t localhost:5000/service-2 \
    && docker push localhost:5000/service-2)
```

# Run

```
docker-compose up
```

# Demo

```
curl -H "Content-Type: text/plain" -d "cURL!" localhost:18080
```

The response is "Hello, cURL!" and the log for service-1 displays the request with prefix "
service-1".

```
curl -H "Content-Type: text/plain" -d "cURL!" localhost:19080
```

The response is again "Hello, cURL!" and the logs for service-1 and service-2 display the request
with prefix "service-2" as service-1 is re-using the correlation ID being passed in from service-2
when service-2 makes the request to service-1.