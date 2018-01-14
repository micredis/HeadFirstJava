To compile OMaticServlet.java successfully
it is necessary to show the compiler
where the servlet-api.jar is located,
as well as where PhraseOMatic.java is:<br>

<b>% sudo javac -cp /opt/apache-tomcat-8.5.23/lib/servlet-api.jar ~/IdeaProjects/HeadFirstJava/chapter18/PhraseOMaticServlet/PhraseOMatic.java OMaticServlet.java</b><br>

<b>-cp</b> stands for "CLASSPATH"<br>

To run the compiled classes successfully, see the instructions
in the README.md file in the <i>servletAtest</i> directory.
Basically, you need to copy class-files into tomcat's webapp directory:<br>

<b>% sudo cp OMaticServlet.class PhraseOMatic.class /opt/apache-tomcat-8.5.23/webapps/examples/WEB-INF/classes</b><br>

then to describe servlets and mapping in this file:<br>

<b>/opt/apache-tomcat-8.5.23/webapps/examples/WEB-INF/web.xml</b><br>

and then run the tomcat.<br>


The web-page for this example will be available at:<br>

<b>localhost:8080/examples/servlets/servlet/OMaticServlet</b>