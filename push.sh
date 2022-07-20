 mvn deploy:deploy-file -Dfile=./kubernetes_3-0.0.1.jar -DpomFile=./kubernetes_3-0.0.1.pom  -DrepositoryId=github   -Durl=https://maven.pkg.github.com/ranglang/packages -Dtoken=$GH_TOKEN
