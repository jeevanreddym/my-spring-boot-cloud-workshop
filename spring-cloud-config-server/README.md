# Config Server

# Actuator
# application.properties entries:
management.endpoints.web.exposure.include=*
# urls:
http://localhost:8080/actuator/configprops - all config props.


# spring Profiles
command line way:
java -jar my-spring-config-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev 