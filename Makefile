all: doc sokoban.jar

doc:
		# génération de la documentation
		javadoc -sourcepath src -d doc -subpackages main

cls:
		# compilation des classes
		javac -cp res -sourcepath src -d classes src/main/*.java

test: cls
				# tests
				javac -d classes -classpath ./lib/junit-platform-console-standalone-1.9.0.jar ./src/main/*.java  ./src/main/gfx/*.java ./src/main/input/*.java  ./src/main/menu/*.java ./src/main/ui/*.java ./src/main/levels/*.java ./test/main/ui/*.java ./test/main/mocks/*.java  ./test/main/menu/*.java ./test/main/gfx/*.java ./test/main/levels/*.java ./test/main/input/*.java
				java -jar ./lib/junit-platform-console-standalone-1.9.0.jar -cp classes --scan-classpath --disable-banner



sokoban.jar: cls
	# construction du jar
	jar cvfm sokoban.jar META-INF/MANIFEST.MF -C classes main

sokoban:
	# execution du jar
	java -jar sokoban.jar


clean:
	# nettoyage des fichiers
	rm -r doc classes *.jar
