public class Example3 {
    private class InnerClass1 extends Example1{
        public String getName(){
            return super.getName();
        }
        public int getAge(){
            return super.getAge();
        }
    }
    private class InnerClass2 extends Example2{
        public String getSex(){
            return super.getSex();
        }
    }
    public void printInfo(){
        System.out.println("Age is "+new InnerClass1().getAge());
        System.out.println("Name is "+new InnerClass1().getName());
        System.out.println("Sex is "+new InnerClass2().getSex());
    }
}
