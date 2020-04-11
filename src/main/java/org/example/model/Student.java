package org.example.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@Proxy(lazy = false)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String studentName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_group",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")}
    )
    private Set<Group> groups = new HashSet<>();


    public Student() {
    }

    public Student(int id, String studentName, Set<Group> groupSet) {
        this.id = id;
        this.studentName = studentName;
        this.groups = groupSet;
    }

    public Student(String studentName) {
        this.studentName = studentName;
    }

    public void addGroup(Group group) {
        groups.add(group);
        group.getStudents().add(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return studentName;
    }

    public Set<Group> getGroupSet() {
        return this.groups;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String studentName) {
        this.studentName = studentName;
    }

    public void setGroups(Set<Group> groupSet) {
        this.groups = groupSet;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                '}';
    }

}
