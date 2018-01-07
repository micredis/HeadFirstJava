The approach that is considered in this chapter is obsolete.

To avoid java.lang.UnsupportedClassVersionError
the files could be compiled using -source parameter:

% javac -source 1.4 *.java

(here the version of JDK 1.4 is deliberately chosen
to avoid errors while attempting to run).


See for example:
https://blogs.oracle.com/darcy/new-javac-warning-for-setting-an-older-source-without-bootclasspath
or
http://qaru.site/questions/963/how-to-fix-javalangunsupportedclassversionerror-unsupported-majorminor-version

*javac warning for setting an older source without bootclasspath*
By: Joe Darcy
To use
javac
from JDK N to cross-compiler to an older platform version, the correct practice is to:

Use the older
-source
setting.
Set the
bootclasspath
to compile against the
rt.jar
(or equivalent) for the older platform.
If the second step is not taken,
javac
will dutifully use the old language rules combined with new libraries, which can result in class files that do not work on the older platform since references to non-existent methods can get included.

Thanks to work by Jon Gibbons, in JDK 7 build 121 and later
javac
detects and warns about this suspicious situation; for example:

$ javac -source 6 HelloWorld.java
warning: [options] bootstrap class path not set in conjunction with -source 1.6
One way to address the warning is to set the
bootclasspath
. If that is inappropriate, the warning can be disabled with a new suboption within the
-Xlint
family,
-Xlint:-options
.

With this change, a likely problematic combination of options to
javac
that can lead to subtle build errors are diagnosed by the compiler and can easily by either directly addressed, or documented as part of the build process via the new
-Xlint
suboption.