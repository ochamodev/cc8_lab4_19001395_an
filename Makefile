compile:
	javac -cp .:/lib/javax.mail-1.6.2.jar:/lib/sqlite-jdbc-3.42.0.0.jar -d out src/CCVIII/Lab04/data/*.java src/CCVIII/Lab04/exception/*.java src/CCVIII/Lab04/model/*.java src/CCVIII/Lab04/*.java

jar:
	jar -cvfm SMTP.jar manifest.txt -C out/ . -C lib/ .
	jar tf SMTP.jar

run:
	java -cp out:lib/sqlite-jdbc-3.42.0.0.jar:lib/javax.mail-1.6.2.jar CCVIII.Lab04.SMTP

runJar:
	java -cp SMTP.jar CCVIII.Lab04.SMTP

cleanJar:
	rm SMTP.jar