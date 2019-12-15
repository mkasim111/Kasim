# Kasim
#created maven project using command line as below

mvn archetype:generate -DgroupId=com.gojek.test -DartifactId=KasimMavenProject -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

#Testng.xml Files are present under below folder
"\src\test\resources\TestNgXMLS\testng.xml"

#logs can be viewed under logs folder
#Report can be view under report folder. Need to open the html in browser to see the extent Reports

#Screenshots under Screenshots folder(if required)

#Run the test case from Command Line:
Navigate to the project folder:

"mvn clean test -Dsurefire.suiteXmlFiles=path to testng.xml(provided above) -Dbrowser=firefox/chrome"

Note: I have parameterised suitexmlFile but can be directly used too.


