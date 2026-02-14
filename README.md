# Инструкция по запуску

Версия Java: java 21.0.10 2026-01-20 LTS

1. Скомпилировать файлы:

`javac *.java`

2. Собрать исполняемый jar файл:

`jar cvfm Analyzer.jar manifest.txt *.class`

3. Запустить утилиту:

Примеры запуска:


Фильтрация и вывод из файлов in1.txt, in2.txt: 

`java -jar Analyzer.jar in1.txt in2.txt`


Фильтрация и вывод из файлов in1.txt, in2.txt с дозаписью в выходные файлы: 

`java -jar Analyzer.jar -а in1.txt in2.txt`


Фильтрация и вывод из файлов in1.txt, in2.txt с дозаписью в выходные файлы и выводом краткой статистики: 

`java -jar Analyzer.jar -а -s in1.txt in2.txt`


Фильтрация и вывод из файлов in1.txt, in2.txt по пути /some/path в файлы с префиксом sample- и выводом полной статистики: 

`java -jar Analyzer.jar -f -p sample- -o /some/path in1.txt in2.txt`
