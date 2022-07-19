#FROM java:11 as build
FROM openjdk:11 as build
WORKDIR /usr/src/app
ENV MILL_VERSION 0.10.5
RUN \
  curl -L -o /usr/local/bin/mill https://github.com/lihaoyi/mill/releases/download/$MILL_VERSION/$MILL_VERSION && \
  chmod +x /usr/local/bin/mill

COPY . .
RUN pwd
RUN mill  kubernetes.assembly
RUN ls -ls
RUN pwd

#deno compile --unstable --allow-net app.ts -p 8080
FROM openjdk:11
ENV TZ=Asia/Chongqing

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /app
COPY --from=build /usr/src/app/out/kubernetes/assembly/dest/out-tmp.jar /app
EXPOSE 8080
ENTRYPOINT [ "java","-cp", "/app/out-tmp.jar", "e.M"]
