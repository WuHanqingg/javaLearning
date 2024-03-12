package System;
import java.util.Scanner;
public class mainSystem {

    public static void main(String[] args) {
        student[] studentList=new student[100];
        account[] accountList=new account[100];
        course[] courseList=new course[100];
        Scanner sc=new Scanner(System.in);
        String []nameList={"Harry","Jerry","Dolly","Jeffery","jerry"};
        for(int i=0;i<5;i++)
            addNewStudentToList(studentList,nameList[i]);
        addAccount(accountList,"HELLO","world");
        addAccount(accountList,"hello","world");
        course math=createCourse("math","Anderson");courseList[0]=math;
        course Chinese=createCourse("Chinese","Liu");courseList[1]=Chinese;
        course English=createCourse("English","David");courseList[2]=English;
        math.addStudent(studentList,"Harry");
        math.addStudent(studentList,"jerry");
        math.addStudent(studentList,"Dolly");
        math.addStudent(studentList,"Jeffery");
        math.setScores("Harry",45.7);
        math.setScores("Dolly",67.9);
        math.setScores("jerry",95.7);
        math.setScores("Jeffery",87.6);
        System.out.println("默认账号为“HELLO”,默认密码为“world”\n" +
                "已经自带math Chinese English三个课程和Harry Jerry Dolly Jeffery jerry五名学生\n" +
                "输入课程名字和学生名字时区分大小写");
        accountStep:
        while(true){
            System.out.println("Please input account:");
            String account=sc.nextLine();
            String choice="";
            passwordStep:
            while(true){
                System.out.println("Please input password:");
                String password=sc.nextLine();
                if(logIn(accountList,account,password)){
                    break accountStep;
                }
                while(true){
                    choice=sc.next();
                    sc.nextLine();
                    if(choice.equals("1"))
                        continue accountStep;
                    else if(choice.equals("0"))
                        continue passwordStep;
                    else if(choice.equals("2")) {
                        System.out.println("System closed.");
                        System.exit(1);
                    }
                    else{
                        System.out.println("Please input 0, 1 or 2:");
                        continue;
                    }

                }
            }
        }
        outputForConvenient();
        FunctionStep:
        while(true){
            String choice="";
            choice=sc.next();
            sc.nextLine();
            switch (choice){
                case "1":{
                    chooseCourseFunction:
                    while(true){
                        String courseName="";String choiceInOne="";
                        System.out.print("Please input the name of course:");
                        courseName=sc.nextLine();
                        int position=searchCourse(courseList,courseName);
                        if(position!=-1){
                            courseFunctionStep:
                            while(true){
                                System.out.println("Input 1 to show namelist of "+courseList[position].getCourseName()
                                +"\t\tInput 2 to show scores of students\n"
                                +"Input 3 to rank students\t\t\t\t" +
                                        "Input 4 to divide students to different levels\n" +
                                        "Input 5 to set scores of a student\t\t"+ "Input 6 to choose another course\n"+
                                        "Input 7 to delete student\t\t\t\t"+"Input 8 to add a new student\n"+
                                        "Input 9 back to main menu");
                                choiceInOne=sc.nextLine();
                                switch(choiceInOne){
                                    case "1":{
                                        courseList[position].showNumbers();
                                        courseList[position].showNameList();
                                        courseList[position].showteacher();
                                        continue courseFunctionStep;
                                    }
                                    case "2":{
                                        courseList[position].showAllScores();
                                        courseList[position].showAverageScores();
                                        continue courseFunctionStep;
                                    }
                                    case "3":{
                                        courseList[position].rankingMarks();
                                        continue courseFunctionStep;
                                    }
                                    case "4":{
                                        courseList[position].divideRank();
                                        continue courseFunctionStep;
                                    }
                                    case "5":{
                                        setScoresStep:
                                        while(true){
                                            String studentName; double newScores=0;
                                            System.out.print("Please input name of student:");
                                            studentName=sc.nextLine();
                                            System.out.print("Please input the new score:");
                                            newScores=sc.nextDouble();
                                            sc.nextLine();
                                            for(int i=0;i<courseList[position].students.length;i++){
                                                if(courseList[position].students[i]==null){
                                                    System.out.println("Student not found");
                                                    labelInOneForFalse:
                                                    while(true){
                                                        System.out.println("Input 1 to input student again");
                                                        System.out.println("Input 2 to use other course functions");
                                                        choiceInOne=sc.nextLine();
                                                        if(choiceInOne.equals("1")){
                                                            continue setScoresStep;
                                                        }
                                                        else if(choiceInOne.equals("2")){
                                                            continue courseFunctionStep;
                                                        }
                                                        else{
                                                            System.out.println("Please input 1 or 2");
                                                            continue labelInOneForFalse;
                                                        }
                                                    }

                                                }
                                                if(courseList[position].students[i].getName().equals(studentName)){
                                                    courseList[position].setScores(courseList[position].students[i].getName(),newScores);
                                                    System.out.println("Set scores succesfully!");
                                                    continue courseFunctionStep;
                                                }
                                            }
                                        }

                                    }
                                    case "6":{
                                        continue chooseCourseFunction;
                                    }
                                    case "7":{
                                        labelForDeleteStudent:
                                        while(true){
                                            String studentName="";
                                            System.out.print("Please input name of student:");
                                            studentName=sc.nextLine();
                                            if(courseList[position].searchStudent(studentName)==-1){
                                                courseList[position].deleteStudent(studentName);
                                                continue courseFunctionStep;
                                            }
                                           courseList[position].deleteStudent(studentName);
                                           if(courseList[position].searchStudent(studentName)==-1){
                                               System.out.println("Delete successfully!");
                                               continue courseFunctionStep;
                                           }
                                           else{
                                               continue courseFunctionStep;
                                           }
                                        }
                                    }
                                    case "8":{
                                        while(true){
                                            String studentName;
                                            System.out.print("Please input name of student:");
                                            studentName=sc.nextLine();
                                            int studentPosition=searchStudent(studentList,studentName);
                                            if(studentPosition==-1){
                                                System.out.println("Student "+studentName+" is not in the student list");
                                                continue courseFunctionStep;
                                            }
                                            else if(studentList[studentPosition].searchCourse(courseName)!=-1){
                                                System.out.println("Student "+courseList[position].students[studentPosition].getName()+" had taken this course already!");
                                                continue courseFunctionStep;
                                            }
                                            else{
                                                courseList[position].addStudent(studentList,studentName);
                                                System.out.println("Added successfully!");
                                                continue courseFunctionStep;
                                            }
                                        }
                                    }
                                    case "9":{
                                        outputForConvenient();
                                        continue FunctionStep;
                                    }
                                }
                            }
                        }
                        if(position==-1){
                            labelInOneForFalse:
                            while(true){
                                System.out.println("Student not found");
                                System.out.println("Input 0 to use other functions\n" +
                                        "Input 1 to choose another course");
                                choiceInOne=sc.nextLine();
                                if(choiceInOne.equals("0"))
                                    continue FunctionStep;
                                else if(choiceInOne.equals("1"))
                                    continue chooseCourseFunction;
                                else{
                                    System.out.println("Please input 0 or 1");
                                    continue labelInOneForFalse;
                                }
                            }
                        }

                    }
                }
                case "2": {
                    chooseStudentStep:
                    while(true){
                        String studentName="";String choiceInTwo="";
                        System.out.print("Please input the name of student:");
                        studentName=sc.nextLine();
                        int position=searchStudent(studentList,studentName);
                        if(position!=-1){
                            studentFunctionStep:
                            while (true){
                                System.out.println("Input 1 to show courses "+studentList[position].getName()+" taked\t\t" +
                                        "Input 2 to show the scores of "+studentList[position].getName()
                                +"\nInput 3 to back to main menu");
                                choiceInTwo=sc.nextLine();
                                labelInTwoForTrue:
                                while(true){
                                    if(choiceInTwo.equals("1")){
                                        studentList[position].showCourses();
                                        continue  studentFunctionStep;
                                    }
                                    else if (choiceInTwo.equals("2")) {
                                        studentList[position].showAllScores();
                                        continue studentFunctionStep;
                                    }
                                    else if (choiceInTwo.equals("3")) {
                                        outputForConvenient();
                                        continue FunctionStep;
                                    }
                                    else{
                                        System.out.println("Please input 1 or 2 or 3");
                                        continue labelInTwoForTrue;
                                    }
                                }
                            }
                        }
                        if(position==-1){
                            labelInTwoForFalse:
                            while(true){
                            System.out.println("Student not found");
                            System.out.println("Input 0 to use other functions\n" +
                                    "Input 1 to choose another student");
                            choiceInTwo=sc.nextLine();
                            if(choiceInTwo.equals("0")){
                                outputForConvenient();
                                continue FunctionStep;
                            }
                            else if(choiceInTwo.equals("1"))
                                continue chooseStudentStep;
                            else{
                                System.out.println("Please input 0 or 1");
                                continue labelInTwoForFalse;
                            }
                            }
                        }

                    }
                }
                case "3":{
                    addAccountStep:
                    while(true){
                        String account="";String  password="";String choiceInThree;
                        System.out.print("Please input new account:");
                        account=sc.nextLine();
                        System.out.print("Please input new password");
                        password=sc.nextLine();
                        if(addAccount(accountList,account,password)) {
                            System.out.println("Added succesfully!");
                            System.out.println("Input 0 to add another account\n" +
                                    "Input 1 to use other functions");
                             labelForChoiceInthree:
                             while(true){
                                 choiceInThree = sc.next();
                                 sc.nextLine();
                                 if(choiceInThree.equals("1")){
                                  outputForConvenient();
                                  continue FunctionStep;
                                 }
                                 else if(choiceInThree.equals("0"))
                                   continue addAccountStep;
                                 else{
                                     System.out.println("Please input 0 or 1");
                                     continue labelForChoiceInthree;
                                 }
                             }
                        }
                        else{
                            System.out.println("Input 0 to add another account\n" +
                                    "Input 1 to use other functions");
                            labelForChoiceThreeForFalse:
                            while(true){
                                choiceInThree=sc.next();
                                sc.nextLine();
                                if(choiceInThree.equals("0"))
                                    continue addAccountStep;
                                    else if (choiceInThree.equals("1")){
                                        outputForConvenient();
                                        continue FunctionStep;
                                    }
                                    else{
                                        System.out.println("Please input 0 or 1");
                                        continue labelForChoiceThreeForFalse;
                                }
                            }
                        }
                    }
                }
                case "4": {
                    addStudentStep:
                    while(true){
                        String studentName="";String choiceInThree;
                        System.out.print("Please input new student:");
                        studentName=sc.nextLine();
                        if(addNewStudentToList(studentList,studentName)) {
                            System.out.println("Added succesfully!");
                            System.out.println("Input 0 to add another account\n" +
                                    "Input 1 to use other functions");
                            labelForChoiceInFour:
                            while(true){
                                choiceInThree = sc.next();
                                sc.nextLine();
                                if(choiceInThree.equals("1")){
                                    outputForConvenient();
                                    continue FunctionStep;
                                }
                                else if(choiceInThree.equals("0"))
                                    continue addStudentStep;
                                else{
                                    System.out.println("Please input 0 or 1");
                                    continue labelForChoiceInFour;
                                }
                            }
                        }
                        else{
                            System.out.println("Input 0 to add another account\n" +
                                    "Input 1 to use other functions");
                            labelForChoiceFourForFalse:
                            while(true){
                                choiceInThree=sc.next();
                                sc.nextLine();
                                if(choiceInThree.equals("0"))
                                    continue addStudentStep;
                                else if (choiceInThree.equals("1")){
                                    outputForConvenient();
                                    continue FunctionStep;
                                }
                                else{
                                    System.out.println("Please input 0 or 1");
                                    continue labelForChoiceFourForFalse;
                                }
                            }
                        }
                    }
                }
                case "5":{
                    deleteAccountStep:
                    while(true){
                        String choiceInFive="";
                        System.out.print("Please input the account:");
                        String account=sc.next();
                        sc.nextLine();
                        if(deleteAccount(accountList,account)){
                            System.out.println("Delete succesfully!");
                            System.out.println("Input 0 to delete another account\n" +
                                    "Input 1 to use other functions");
                            labelInFive:
                            while(true){
                                choiceInFive=sc.next();
                                sc.nextLine();
                                if(choiceInFive.equals("0"))
                                    continue deleteAccountStep;
                                else if(choiceInFive.equals("1")) {
                                 outputForConvenient();
                                    continue FunctionStep;
                                }
                                else{
                                    System.out.println("Please input 0 or 1");
                                    continue labelInFive;
                                }
                            }
                        }
                        else {
                            System.out.println("Input 0 to input another account\n" +
                                    "Input 1 to use other functions");
                            labelInFiveForFalse:
                            while (true) {
                                choiceInFive = sc.next();
                                sc.nextLine();
                                if (choiceInFive.equals("0")) {
                                    outputForConvenient();
                                    continue deleteAccountStep;
                                }
                                else if (choiceInFive.equals("1")) {
                                    outputForConvenient();
                                    continue FunctionStep;
                                }
                                else{
                                    System.out.println("Please input 0 or 1");
                                    continue labelInFiveForFalse;
                                }
                            }

                        }
                    }
                }
                case "6": {
                    deleteStudentStep:
                    while(true){
                        String choiceInSix="";
                        System.out.print("Please input the name of student:");
                        String studentName=sc.next();
                        sc.nextLine();
                        int position=searchStudent(studentList,studentName);
                        if(deleteStudent(courseList,studentList,studentName)){
                            System.out.println("Delete succesfully!");
                            System.out.println("Input 0 to delete another student\n" +
                                    "Input 1 to use other functions");
                            labelInSix:
                            while(true){
                                choiceInSix=sc.next();
                                sc.nextLine();
                                if(choiceInSix.equals("0"))
                                    continue deleteStudentStep;
                                else if(choiceInSix.equals("1")) {
                                    outputForConvenient();
                                    continue FunctionStep;
                                }
                                else{
                                    System.out.println("Please input 0 or 1");
                                    continue labelInSix;
                                }
                            }
                        }
                        else {
                            System.out.println("Input 0 to input another student\n" +
                                    "Input 1 to use other functions");
                            labelInFiveForFalse:
                            while (true) {
                                choiceInSix = sc.next();
                                sc.nextLine();
                                if (choiceInSix.equals("0")) {
                                    outputForConvenient();
                                    continue deleteStudentStep;
                                }
                                else if (choiceInSix.equals("1")) {
                                    outputForConvenient();
                                    continue FunctionStep;
                                }
                                else{
                                    System.out.println("Please input 0 or 1");
                                    continue labelInFiveForFalse;
                                }
                            }

                        }
                    }

                }
                case "7":{
                    addStudentStep:
                    while(true){
                        String CourseName="";String TeacherName="";String choiceInSeven;
                        System.out.print("Please input name of new course:");
                        CourseName=sc.nextLine();
                        System.out.print("Please input teacher's name");
                        TeacherName=sc.nextLine();
                        if(addCourse(courseList,CourseName,TeacherName)) {
                            System.out.println("Added succesfully!");
                            System.out.println("Input 0 to add another course\n" +
                                    "Input 1 to use other functions");
                            labelForChoiceInSeven:
                            while(true){
                                choiceInSeven = sc.next();
                                sc.nextLine();
                                if(choiceInSeven.equals("1")){
                                    outputForConvenient();
                                    continue FunctionStep;
                                }
                                else if(choiceInSeven.equals("0"))
                                    continue addStudentStep;
                                else{
                                    System.out.println("Please input 0 or 1");
                                    continue labelForChoiceInSeven;
                                }
                            }
                        }
                        else{
                            System.out.println("Input 0 to add another course\n" +
                                    "Input 1 to use other functions");
                            labelForChoiceSevenForFalse:
                            while(true){
                                choiceInSeven=sc.next();
                                sc.nextLine();
                                if(choiceInSeven.equals("0"))
                                    continue addStudentStep;
                                else if (choiceInSeven.equals("1")){
                                    outputForConvenient();
                                    continue FunctionStep;
                                }
                                else{
                                    System.out.println("Please input 0 or 1");
                                    continue labelForChoiceSevenForFalse;
                                }
                            }
                        }
                    }
                }
                case "8":
                    System.exit(0);
                default:{
                    System.out.println("Please input 1, 2, 3, 4, 5, 6 or 7");
                    continue;
                }
            }
        }
    }
    public static course createCourse(String name,String teacherName){
        return new course(name,teacherName);
    }
    public static boolean addNewStudentToList(student[] studentList,String studentName){
        for(int i=0;i<100;i++){
            if(studentList[i]==null){
                studentList[i]=new student(studentName);
                return true;
            }
            if(studentList[i].getName().equals(studentName)){
                System.out.println("This student has been record");
                return false;
            }
        }
        return false;
    }
    public static boolean deleteStudent(course[] courseList,student[] studentList,String studentName){
        if(studentList[0]!=null&&studentList[1]==null&&studentList[0].getName().equals(studentName)){
            System.out.println("There should be at least one student!");
            return false;
        }
        for(int i=0;i<studentList.length;i++){
            if(studentList[i]!=null&&studentList[i].getName().equals(studentName)){
                int position=searchStudent(studentList,studentName);
                for(int k=0;i<studentList[0].takenCourses.length;k++){
                    if(studentList[position].takenCourses[k]!=null){
                        for(int j=0;j<courseList.length;j++){
                            if(courseList[j]!=null){
                                courseList[j].deleteStudent(studentName);
                            }
                        }
                    }
                    if(studentList[position].takenCourses[k]==null){
                        break;
                    }
                }
                for(int k=i;k<studentList.length;k++){
                    if(studentList[k+1]!=null)
                        studentList[k]=studentList[k+1];
                    if(studentList[k+1]==null&&studentList[k]!=null){
                        studentList[k]=null;
                        return true;
                    }
                }
            }
            if(studentList[i+1]==null||i+1>=studentList.length){
                System.out.println("Student not found");
                return false;
            }
        }
        return false;
    }
    public static int searchStudent(student[] studentList,String studentName){
        for(int i=0;i<studentList.length;i++){
            if(studentList[i]==null){
                System.out.println("Nor found");
                return -1;
            }
            else if(studentList[i].getName().equals(studentName)){
                return i;
            }
        }
        return -1;
    }
    public static int searchCourse(course[] courseList,String courseName){
        for(int i=0;i<100;i++) {
            if (courseList[i]!=null&&courseList[i].getCourseName().equals(courseName))
                return i;
            if(i==99)
                System.out.println("Not found");
        }
        return -1;
    }
    public static boolean addAccount(account[] accountList,String account,String password){
        for(int i=0;i<accountList.length;i++){
            if(accountList[i]==null){
                accountList[i]=new account(account,password);
                return true;
            }
            if(accountList[i].getAccount().equals(account)){
                System.out.println("This account has been created");
                return false;
            }
        }
        return false;
    }
    public static boolean deleteAccount(account[] accountList,String account){
        if(accountList[0]!=null&&accountList[1]==null&&accountList[0].getAccount().equals(account)){
            System.out.println("There should be at least one account!");
            return false;
        }
        for(int i=0;i<accountList.length;i++){
            if(accountList[i]!=null&&accountList[i].getAccount().equals(account)){
                for(int k=i;k<accountList.length;k++){
                    if(accountList[k+1]!=null)
                    accountList[k]=accountList[k+1];
                    if(accountList[k+1]==null&&accountList[k]!=null){
                        accountList[k]=null;
                    return true;
                    }
                }
            }
            if(accountList[i+1]==null||i+1>=accountList.length){
                System.out.println("Account not found");
                return false;
            }
        }
        return false;
    }
    public static boolean addCourse(course[] courseList,String courseName,String teacherName){
        for(int i=0;i<courseList.length;i++){
            if(courseList[i]==null){
                courseList[i]=new course(courseName,teacherName);
                return true;
            }
            if(courseList[i].getCourseName().equals(courseName)){
                System.out.println("This course has been created");
                return false;
            }
        }
        return false;
    }
    public static boolean logIn(account[] accountList,String account,String password){
        for(int i=0;accountList[i]!=null;i++){
            if(accountList[i].getAccount().equals(account)){
                if(accountList[i].getPassword().equals(password)){
                    System.out.println("Succesfully log in!");
                    return true;
                }
                else{
                    System.out.println("Wrong password!");
                    System.out.println("input to 0 to input password again\nInput 1 to change account\n" +
                            "input 2 to quit system.");
                    return false;
                }
            }
            if(accountList[i+1]==null&&i+1<accountList.length){
                System.out.println("Account not found");
                System.out.println("input to 0 to input password again\nInput 1 to change account\n" +
                        "input 2 to quit system.");
                return false;
            }
        }
        return false;
    }
    public static void outputForConvenient(){
        System.out.println("Input 1 to choose course\t\tInput 2 to choose a student\n" +
                "Input 3 to add a new account\tInput 4 to add a student"+
                "\nInput 5 to delete a account\t\tInput 6 to delete a student"
                +"\nInput 7 to add a new course\t\tInput 8 to quit system");
    }
    public static void showStudentList(student[] studentList){
        for(int i=0;i<studentList.length;i++){
            System.out.println(studentList[i].getName());
            if(studentList[i+1]==null)
                break;
        }
    }
}
