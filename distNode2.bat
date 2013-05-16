set CLASSPATH="target\classes"
set DEPEND=target\dependency
set CLASSPATH=%CLASSPATH%;"%DEPEND%\infinispan-core-5.1.0.CR2.jar"
rem set CLASSPATH=%CLASSPATH%:"%DEPEND%\infinispan-core-5.1.0.CR2.jar"
set CLASSPATH=%CLASSPATH%;"%DEPEND%\jboss-logging-3.1.0.CR1.jar"
set CLASSPATH=%CLASSPATH%;"%DEPEND%\jboss-marshalling-1.3.0.GA.jar"
set CLASSPATH=%CLASSPATH%;"%DEPEND%\jboss-marshalling-river-1.3.0.GA.jar"
set CLASSPATH=%CLASSPATH%;"%DEPEND%\jboss-transaction-api-1.0.1.GA.jar"
set CLASSPATH=%CLASSPATH%;"%DEPEND%\jgroups-3.0.1.Final.jar"
set CLASSPATH=%CLASSPATH%;"%DEPEND%\rhq-pluginAnnotations-3.0.4.jar"
set CLASSPATH=%CLASSPATH%;"%DEPEND%\stax2-api-3.1.1.jar"
set CLASSPATH=%CLASSPATH%;"%DEPEND%\staxmapper-1.0.0.Final.jar"
set CLASSPATH=%CLASSPATH%;"%DEPEND%\woodstox-core-asl-4.1.1.jar"

rem java -classpath %CLASSPATH% org.infinispan.quickstart.clusteredcache.distribution.Node0
rem java -classpath %CLASSPATH% org.infinispan.quickstart.clusteredcache.distribution.Node0
rem java -classpath %CLASSPATH% org.infinispan.quickstart.clusteredcache.replication.Node0
java -classpath %CLASSPATH% org.infinispan.quickstart.clusteredcache.distribution.Node2

