compile:
	javac -classpath ../lib/library1.jar:../lib/library2.jar  src/CCVIII/Lab04/data/*.java src/CCVIII/Lab04/*.java

jar:
	jar -cvfm SMTP.jar manifest.txt -C src .

run:
	java -cp *.jar CCVII.Lab04.SMTP