package jpa.entitymodels;

//this class carries data related to one course

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @Column(name = "id", nullable = false)
    private int cId; //unique course identifier

    @Basic
    @Column ( name = "name")
    private String cName;

    @Basic
    @Column ( name = "Instructor")
    private String cInstructorName; //name of instructor for the course

    @ManyToMany(mappedBy="sCourses")
    private List<Student> students;

    public Course() {
        this.cId=0;
        this.cInstructorName = "";
        this.cName ="";
    }

    public Course(int cId, String cName, String cInstructorName) {
        this.cId = cId;
        this.cName = cName;
        this.cInstructorName = cInstructorName;



    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcInstructorName() {
        return cInstructorName;
    }

    public void setcInstructorName(String cInstructorName) {
        this.cInstructorName = cInstructorName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", cInstructorName='" + cInstructorName + '\'' +
                '}';
    }
}
