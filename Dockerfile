FROM airhacks/glassfish
COPY ./target/blog.war ${DEPLOYMENT_DIR}
