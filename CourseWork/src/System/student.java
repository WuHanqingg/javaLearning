package System;

public class student {
    public String name;
    class takenCourse{
        String name;
        double score;
        takenCourse(String courseName,double scores){
            this.name=courseName;
            this.score=scores;
        }
    }//用于储存学生参加的某个课程
    public takenCourse[] takenCourses;
    student(String name){
        this.name=name;
        takenCourses=new takenCourse[100];
    }//此数组用于存放学生参加的所有课程
    public int searchCourse(String courseName){
        for(int i=0;takenCourses[i]!=null;i++){
            if(takenCourses[i].name.equals(courseName))
                return i;
        }
        return -1;
    }
    public String getName(){
        return name;
    }
    public void addCourse(String courseName,double scores){
        for(int i=0;i<10;i++)
            if(takenCourses[i]==null){
                takenCourses[i]= new takenCourse(courseName, scores);
                break;
        }
    }
    public void deleteCourse(String courseName){
        int count=0;
        for(int i=0;i<takenCourses.length;i++){
            if(takenCourses[i].name.equals(courseName)){
                count=i;
                break;
            }
            if(takenCourses[i]==null){
                return;
            }
        }
        while(true){
            takenCourses[count]=takenCourses[count+1];
            if(takenCourses[count]==null&&count>0) {
                takenCourses[count - 1] = null;
                break;
            }
            count++;
        }
    }
    public void showCourses(){
        for(int i=0;i<10;i++){
            if(this.takenCourses[i]==null)
                break;
            System.out.println(this.takenCourses[i].name);
        }
    }
    public void showAllScores(){
        if(takenCourses[0]==null){
            System.out.println("This student has taken no courses! ");
            return;
        }        for(int i=0;i<takenCourses.length;i++){
            if(takenCourses[i]==null)break;
            System.out.println(takenCourses[i].name+"\t\t"+takenCourses[i].score);
        }
    }
}
