FROM quay.io/keycloak/keycloak:24.0.3

ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

# limite de recursos por causa do railway
ENV JAVA_OPTS_APPEND="-Xms128m -Xmx256m"


ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]
CMD ["start-dev"]
