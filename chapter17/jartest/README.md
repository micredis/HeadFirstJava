By using the <b>-d</b> flag, you get to decide which directory the compile code lands in,
rather than accepting the default of class files landing in the same directrory
as the source code. To compile all .java files in the source directory, use:

<b>%javac -d ../classes *.java</b>


<H2>Making an executable JAR</H2>

<ol>
  <li>Make sure all of your class files are in the classes directory</li>
  <li>Create a manifest.txt file that states which class has the main() method
  <br>Make a text file named <b>manifest.txt</b> that has a one line:

  <b>Main-Class: MyApp</b>
  (don’t put the .class on the end).

  Press the return key after typing the Main-Class line, or your manifest may not work correctly. Put the manifest file into the “classes” directory.</li>
  <li>Run the jar tool to create a JAR file that contains everything in the classes directory, plus the manifest.

  <b>%cd MiniProject/classes
  <br>%jar -cvmf manifest.txt app1.jar *.class</b>
  <br>OR
  <br><b>%jar -cvmf manifest.txt app1.jar MyApp.class</b>
  <br>
  Running (executing) the JAR

  <b>%cd MyProject/classes</b>

  <b>%java -jar app1.jar</b></li>
</ol>


<H2>Compiling and running with packages</H2>

Compiling with the <b>-d</b> (<i>directory</i>) flag:

// Stay in the source directory! Do NOT cd down
// into the directory where the .java files is!

<b>%cd MyProject/source

%javac -d ../classes com/headfirstjava/PackageExercise.java</b>

To compile all the .java files in the com.headfirstjava package use:

<b>%javac -d ../classes com/headfirstjava/*.java</b>


<H2>Running your code</H2>

<b>%cd MyProject/classes

%java com.headfirstjava.PackageExercise</b>

The <b>-d</b> flag tells the compiler,
<br><em>“Put the class into its package directory structure, using the class specified after the <b>-d</b> as the root directory. But... if the directories aren’t there, create them first and then put the class in the right place!”</em>
