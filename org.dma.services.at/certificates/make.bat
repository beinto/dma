@echo off
echo *******************************************************************************
echo * Marco Lopes (marcolopespt@gmail.com)
echo * Importacao da chave publica para KEY STORE
echo *******************************************************************************
setlocal

set OPENSSL_CONF=
set OPENSSL_PATH=C:\openssl\bin
set PATH=%PATH%;%OPENSSL_PATH%
if exist %OPENSSL_PATH%\openssl.exe goto START
echo OpenSSL NOT FOUND in %OPENSSL_PATH%
goto END

REM =======
REM  START
REM =======
:START
echo EXTRAI A CHAVE PUBLICA DO CERTIFICADO AT (formato:PEM, encoding:BASE64)
openssl x509 -in ChavePublicaAT.cer -pubkey -noout > ChavePublicaAT.pem

echo CONVERTE A CHAVE PUBLICA (formato:DER)
openssl enc -base64 -d -in ChavePublicaAT.pem > ChavePublicaAT.der

echo IMPORTA A CHAVE PUBLICA DO CERTIFICADO AT (formato:JKS, alias:sapubkey)
del ChavePublicaAT.jks
keytool -importcert -noprompt -alias "sapubkey" -file ChavePublicaAT.cer -keystore ChavePublicaAT.jks -storepass 123456

:END
pause
