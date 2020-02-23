mvn -f github/text-model/pom.xml clean install -Dmaven.test.skip=true
mvn -f github/nlp-analyzers/nlp-analyzers/pom.xml clean install -Dmaven.test.skip=true
mvn -f github/taxonomy-core/pom.xml clean install -Dmaven.test.skip=true
mvn -f github/hieralchical-classifiers/pom.xml clean install -Dmaven.test.skip=true
mvn -f eclipse-workspace/core-semantic-engine/pom.xml clean install -Dmaven.test.skip=true
mvn -f eclipse-workspace/fast-semantic-engine/pom.xml clean install -Dmaven.test.skip=true

