# Patterns in spring cloud environment

1) Load Balancing, Scaling MicroServices - Registry & Discovery server for Service Discovery,
2) Fault Tolerance & Resilience - Timeout, Circuit Breaker, retry, ratelimiter - resilience4j, Hystriz (deprecated Netflix dependency)
3) Configuration


# Changing port, profile using VM options:

-Dserver.port=8084 -Dspring.profiles.active=dev

![img.png](img.png)
