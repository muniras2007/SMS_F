package jpa.entitymodels;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "student")
//student class carries data related to one student
public class Student {
    @Id //jpa parameter that is used to specify which column is the primary key
    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(50)")
    private String sEmail;     //unique student identifier

    @Basic //@Basic - All members are fields of the specified table. @Basic used to specify manually a member variable is field of the table
    @Column(name= "name")
    private String sName;

    @Basic
    @Column (name= "password")
    private String sPass;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name ="sEmail",referencedColumnName = "email"),
            inverseJoinColumns = @JoinColumn(name= "cId", referencedColumnName = "id")
    )
    private List<Course> sCourses= new ArrayList<>();


//first constructor takes no values and initialises every member to an initial value
    public Student() {
        super();
//        this.sEmail= " ";
//        this.sName = " ";
//        this.sPass = " ";
//        this.sCourses = new ArrayList();
    }

    //second constructor must initialize every private member with a parameter provided to constructor


    public Student(String sEmail, String sName, String sPass, List<Course> sCourses) {
       super();
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.sCourses = sCourses;
    }
    //getters and setters for each
    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    public List<Course> getsCourses() {
        return sCourses;
    }

    public void setsCourses(List<Course> sCourses) {
        this.sCourses = sCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sEmail='" + sEmail + '\'' +
                ", sName='" + sName + '\'' +
                ", sPass='" + sPass + '\'' +
                //", sCourses=" + sCourses +
                '}';
    }
}
