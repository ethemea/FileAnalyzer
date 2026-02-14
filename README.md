# Инструкция по запуску

Версия Java: java 21.0.10 2026-01-20 LTS

1. Скомпилировать файлы:
`javac *.java`
2. Собрать исполняемый jar файл:
`jar cvfm Analyzer.jar manifest.txt *.class`
3. Запустить утилиту:
Примеры запуска:
`java -jar Analyzer.jar in1.txt in2.txt`
`java -jar Analyzer.jar -f -p sample- -o /some/path in1.txt in2.txt`
