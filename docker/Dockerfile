FROM openjdk:8
ARG JAR_FILE
ENV PATH_TARGET="/share/media/anime" PATH_SOURCE="'/share/download/qb','/share/download/aria','/share/download/115'"
COPY ./target/${JAR_FILE} /usr/local/app.jar
WORKDIR /usr/local/
ENTRYPOINT ["java","-jar","/usr/local/app.jar"]