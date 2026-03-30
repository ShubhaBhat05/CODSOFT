import java.util.Scanner;
public class StudentgradeCalculator{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of subjects");
        int n=sc.nextInt();
        int marks[]=new int[n];
        int total=0;
        char grade;
        System.out.println("Enter marks for each subjects");
        for(int i=0;i<n;i++){
            System.out.println("Enter marks for subject "+(i+1)+":");
            marks[i]=sc.nextInt();
            total+=marks[i];
        }
        double percentage=(total/n);
       
        if(percentage>=90){
            grade='A';
        }
        else if(percentage>=80){
             grade='B';
       }
        else if(percentage>=70){
            grade='C';
        }
        else if(percentage>=60){
            grade='D';
        }
        else{
            grade='F';
        }
        System.out.println("Dsiplayong a result:");
        System.out.println("Marks obtained:"+total);
        System.out.println("Percenatge obtained:"+percentage);
        System.out.println("Grade:"+grade);
        sc.close();
    } 
    
}
