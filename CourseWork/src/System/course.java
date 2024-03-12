package System;

public class course {
    student[] students;
    teacher[] teachers;
    public String courseName;
    course(String courseName,String teacherName){
        this.courseName=courseName;
        students= new student[100];
        teachers= new teacher[10];
        teachers[0]=new teacher(teacherName);
    }
    public void showNameList(){
        System.out.println("Namelist");
        for(int i=0;i<this.students.length;i++) {
            if(students[i]==null){
                break;}
            System.out.println(students[i].getName());
        }
    }
    public String getCourseName(){
        return courseName;
    }
    public void addStudent(student[] studentList,String studentName){
        int position=0;
        for(int i=0;i<studentList.length;i++){
            if(studentList[i]==null){
                position=-1;
                return;
            }
            else if(studentList[i].getName().equals(studentName)){
                position=i;
                    break;
            }
        }
        for(int i=0;i<students.length;i++){
            if(students[i]==null){
                students[i]=studentList[position];
                studentList[position].addCourse(this.getCourseName(),0);
                return;
            }
        }
        }
    public void deleteStudent(String studentName){
        int count=0;
        for(int i=0;i<students.length;i++){
            if(students[i]==null){
                System.out.println("Student "+studentName+" is not found");
                return;
            }
            if(students[i].getName().equals(studentName)){
                count=i;
                break;
            }
        }
        students[count].deleteCourse(this.courseName);
        while(true){
            students[count]=students[count+1];
            count++;
            if(students[count+1]==null){
                students[count]=null;
                break;
            }
        }
    }
    public void showNumbers(){
        int count=0;
        for(int i=0;i< students.length;i++)
            if(students[i]==null){
                count=i;
                break;
            }
        System.out.println("There are "+count+" students in course "+this.courseName);
    }
    public int Numbers(){
        int count=0;
        for(int i=0;i< students.length;i++)
            if(students[i]==null){
                count=i;
                break;
            }
        return  count;
    }
    public void showteacher() {
        System.out.println("Teacher of course "+this.getCourseName()+" is "+this.teachers[0].getName());
        System.out.println();
    }
    public void showAllScores(){
        System.out.println("NameList\t\tScores");
        for(int i=0;students[i]!=null;i++){
            System.out.println(students[i].getName()+"\t\t\t"+
                    students[i].takenCourses[students[i].searchCourse(courseName)].score);
        }
    }
    public void showAverageScores(){
        double sum=0;
        int count=Numbers();
        //用于计算本课学生人数
        for(int i=0;students[i]!=null;i++)
            sum+=students[i].takenCourses[students[i].searchCourse(courseName)].score;
        System.out.println("The average scores of course "+this.courseName+
                " is "+sum/count);
        //searchCourse用于定位该课程在学生参课名单中的位置
    }
    public void setScores(String studentName,double newScores){
        int des=0;
        for(int i=0;students[i]!=null;i++){
            if(students[i].getName().equals(studentName)){
                des=i;
                break;
            }//查询学生位置
            else if(students[i+1]==null){
                System.out.println("Student "+studentName+" is not in course "+this.courseName);
                return;
            }//若不在名单中则输出不在本课内
        }
        students[des].takenCourses[students[des].searchCourse(this.courseName)].score=newScores;
        //修改成绩
    }
    public int searchStudent(String studentName){
        for(int i=0;i<students.length;i++){
            if(students[i]==null){
                return -1;
            }
            if(students[i].getName().equals(studentName)){
                return i;
            }
        }
        return -1;
    }
    public void rankingMarks(){
        student[] temp=new student[students.length];
        for(int i=0;students[i]!=null;i++){
            temp[i]=students[i];
        }//创建临时数组储存学生，以免打乱原有学生顺序
        student store=new student("null");
        for(int i=0;i<Numbers()-1;i++)
            for (int j = 0; j < Numbers() - 1-i; j++)
                if (temp[j].takenCourses[temp[j].searchCourse(this.courseName)].score <
                        temp[j + 1].takenCourses[temp[j + 1].searchCourse(this.courseName)].score) {
                    store = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = store;
                }//进行排序
        System.out.println("NameList\t\tScores");
        for(int i=0;temp[i]!=null;i++){
            System.out.println(temp[i].getName()+"\t\t\t"+
                    temp[i].takenCourses[temp[i].searchCourse(courseName)].score);
        }//打印排名之后的成绩
    }
    public void divideRank(){
        int A=0;int B=0;int C=0;int D=0;int E=0;
        for(int i=0;students[i]!=null;i++)
        {
            double score=students[i].takenCourses[students[i].searchCourse(this.courseName)].score;
            if(score>=90)
                A++;
            else if(score>=80)
                B++;
            else if(score>=70)
                C++;
            else if(score>=60)
                D++;
            else
                E++;
        }
        System.out.println("There are "+A+" Excellent students "+A*100/(double)this.Numbers()+"%");
        System.out.println("There are "+B+" Good students "+B*100/(double)this.Numbers()+"%");
        System.out.println("There are "+C+" Medium students "+C*100/(double)this.Numbers()+"%");
        System.out.println("There are "+D+" Pass students "+D*100/(double)this.Numbers()+"%");
        System.out.println("There are "+E+" Failed students "+E*100/(double)this.Numbers()+"%");
    }
}
