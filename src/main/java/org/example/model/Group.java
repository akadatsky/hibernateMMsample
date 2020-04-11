package org.example.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
@Proxy(lazy = false)
public class Group {
    @Id
    private int groupId;

    @Column
    private String groupName;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();

    public Group() {
    }

    public Group(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public Group(int groupId, String groupName, Set<Student> students) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.students = students;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }


    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}