Инструкция по запуску
1. Скомпилировать файлы:
javac *.java
2. Собрать исполняемый файл:
jar cvfm Analyzer.jar manifest.txt *.class
3. Запустить утилиту:
Например:
java -jar Analyzer.jar in1.txt in2.txt
java -jar Analyzer.jar -f -p sample- -o /some/path in1.txt in2.txt
