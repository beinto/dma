@echo off
echo *******************************************************************************
echo * 2008-2018 Public Domain
echo * Marco Lopes (marcolopespt@gmail.com)
echo * https://xmlbeans.apache.org/docs/2.0.0/guide/tools.html
echo *******************************************************************************
setlocal

set SCHEMA_FOLDER=.
if not "%2"=="" set SCHEMA_FOLDER=%2%
set SOURCE_FOLDER=..\src
set OUTPUT_FOLDER=%SCHEMA_FOLDER%\output
set CONFIG_XSD=%SCHEMA_FOLDER%\%1.xsdconfig
set OUTPUT_JAR=%OUTPUT_FOLDER%\%1.jar
md %OUTPUT_FOLDER%

set SOURCE_XSD=%SCHEMA_FOLDER%\%1.xsd
if exist "%SOURCE_XSD%" goto JAVA
echo %1.xsd not found in %SCHEMA_FOLDER%!
set SOURCE_XSD=%SCHEMA_FOLDER%\%1.wsdl
if exist "%SOURCE_XSD%" goto JAVA

echo %1.wsdl not found in %SCHEMA_FOLDER%!
echo USAGE: COMP SCHEMA_FILE [SCHEMA_FOLDER]
goto END

:JAVA
set JAVA_HOME=C:\Program Files\Java\jdk1.7.0
if exist "%JAVA_HOME%\bin\javac.exe" goto XMLBEANS
echo JAVA 64bits SDK NOT FOUND! PLEASE install JAVA
goto END

:XMLBEANS
set BEANS_HOME=..
set BEANS_LIB=%BEANS_HOME%\xbean-3.0.1
set BEANS_CP=%BEANS_LIB%\xbean.jar
rem set BEANS_CP=%BEANS_CP%;%BEANS_LIB%\jsr173_1.0_api.jar
rem set BEANS_CP=%BEANS_CP%;%BEANS_LIB%\resolver.jar
rem set BEANS_CP=%BEANS_CP%;%BEANS_LIB%\xbean_xpath.jar
rem set BEANS_CP=%BEANS_CP%;%BEANS_LIB%\xmlbeans-qname.jar
rem set BEANS_CP=%BEANS_CP%;%BEANS_LIB%\xmlpublic.jar
if exist %BEANS_LIB% goto COMPILE
echo XMLBEANS LIB NOT FOUND @ %BEANS_LIB%
goto END

:COMPILE
set JAVA=%JAVA_HOME%\bin\java.exe
set JAVAC=%JAVA_HOME%\bin\javac.exe
echo COMPILING: %SOURCE_XSD%

"%JAVA%" -Xmx2048m -classpath %BEANS_CP% org.apache.xmlbeans.impl.tool.SchemaCompiler -out %OUTPUT_JAR% %SOURCE_XSD% -compiler "%JAVAC%" -javasource "1.5"

:END
