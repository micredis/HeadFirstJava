<H4>To run the whole thing, run in separate terminals in that order:</H4>
<ol>
	<li>rmiregistry</li>
	<li>java ServiceServerImpl</li>
	<li>java ServiceBrowser</li>
</ol>
<hr>
<H1>The approach that is considered in this chapter is obsolete!</H1>

To avoid java.lang.UnsupportedClassVersionError
the files could be compiled using <b>-source</b> parameter:

<b>% javac -source 1.4 *.java</b>

(here the version of JDK 1.4 is deliberately chosen
to avoid errors while attempting to run).


See for example:<br>
<br>
<u>https://blogs.oracle.com/darcy/new-javac-warning-for-setting-an-older-source-without-bootclasspath</u><br>
or<br>
<u>http://qaru.site/questions/963/how-to-fix-javalangunsupportedclassversionerror-unsupported-majorminor-version</u>

<H2>javac warning for setting an older source without bootclasspath</H2>
<p><i>By: Joe Darcy</i></p>
<p>To use <b>javac</b> from JDK N to cross-compiler to an older platform version, the correct practice is to:<br>
Use the older <b>-source</b> setting. Set the bootclasspath to compile against the rt.jar (or equivalent) for the older platform. If the second step is not taken, javac will dutifully use the old language rules combined with new libraries, which can result in class files that do not work on the older platform since references to non-existent methods can get included.

Thanks to work by Jon Gibbons, in JDK 7 build 121 and later javac detects and warns about this suspicious situation; for example:
<br>
<br>
<i>$ javac -source 6 HelloWorld.java</i>
<br>
warning: [options] bootstrap class path not set in conjunction with -source 1.6
<p>One way to address the warning is to set the bootclasspath. If that is inappropriate, the warning can be disabled with a new suboption within the -Xlint family, -Xlint:-options.</p>

With this change, a likely problematic combination of options to javac that can lead to subtle build errors are diagnosed by the compiler and can easily by either directly addressed, or documented as part of the build process via the new -Xlint suboption.
