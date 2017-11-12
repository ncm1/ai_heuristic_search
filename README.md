To get a list of targets: ant -p build.xml

To run main class: ant run

To change main class: 
1) go into build.xml
2) go to line 16
3) change value of the "Main-Class" attribute to the package + class name (ex: main.RunMVCTest)

To run test classes: ant test
