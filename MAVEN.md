# Publish a project to Maven repository
https://central.sonatype.org/pages/ossrh-guide.html

Because this is a JavaFX library, the easiest way to build it is by using the LibericaJDK.

## Very first step 
When never published to Maven before:
* Create an account and a ticket on https://issues.sonatype.org/secure/CreateIssue.jspa?issuetype=21&pid=10134
* You will need to create a TEXT record on your DNS with the ticket number to prove you own the domainname

## Do not use parent maven pom file!
https://central.sonatype.org/pages/apache-maven.html

"In the past all the plugin configuration and other setup was managed by a Maven parent POM with the latest coordinates of org.sonatype.oss:oss-parent:9. 
**This project leaked SCM, URL and other details and its usage is discouraged.**" 

## Create a public key with 
* Install from https://www.gnupg.org/download/index.html
* Run and create a public published key, remember to store the passphrase!

## Configure your Maven settings.xml file with GnuPG
* Create a file (Windows): C:\Users\USERNAME\.m2\settings.xml - "Kleopatra"
* Keep the id "ossrh" and fill in the username and password of https://issues.sonatype.org/
```
<settings>
  <servers>
    <server>
      <id>ossrh</id>
      <username>USERNAME</username>
      <password>PASSWORD</password>
    </server>
  </servers>
</settings>
````

## Publish to repository
For a snapshot version
```
mvn clean deploy
```

For a release, update the version in pom.xml first, then:
```
mvn release:clean release:prepare
   --> Answer the prompts for versions and tags
mvn release:perform
```

## Check your published artifact
* https://oss.sonatype.org/ with the same credentials as https://issues.sonatype.org/