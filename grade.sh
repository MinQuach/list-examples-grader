CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

git clone $1 student-submission
echo 'Finished cloning'


# Draw a picture/take notes on the directory structure that's set up after
# getting to this point
file='student-submission/ListExamples.java'
if [[ -e $file ]] && [[ -f $file ]]
then 
    echo "Found ListExamples.java file"
else 
    echo "Can't find ListExamples.java file"
    exit 1
fi

cp -r lib $file TestListExamples.java grading-area
cd grading-area

# Then, add here code to compile and run, and do any post-processing of the
# tests
javac -cp $CPATH *.java TestListExamples.java
if [[ $? -ne 0 ]]
then 
    echo "Compile Error"
    exit 1
fi
java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > output.txt
grep "OK" output.txt && echo "pass" && exit 0
echo -n $(grep -oE "Failures: [0-9]+" output.txt | grep -oE "[0-9]+") 
echo " /" $(grep -oE "Tests run: [0-9]+" output.txt | grep -oE "[0-9]+") 
 
