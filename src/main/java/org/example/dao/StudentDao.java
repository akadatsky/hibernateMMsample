package org.example.dao;


import org.example.model.Group;
import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

@SuppressWarnings("unchecked")
public class StudentDao {
    private SessionFactory sessionFactory;

    public StudentDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        }
    }

    public List<String> _getStudentsByGroup(String groupName) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createSQLQuery("SELECT t1.studentname FROM Student as t1 " +
                            "JOIN student_group as t2 on t1.id = t2.student_id " +
                            "JOIN groups as t3 on t2.group_id = t3.groupId " +
                            "where lower(t3.groupName) = lower(:groupName)")
                    .setParameter("groupName", groupName)
                    .list();
        }
    }

    public List<String> _getGroupsByStudentName(String studentName) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createSQLQuery("SELECT t1.groupname FROM groups as t1 " +
                            "JOIN student_group as t2 on t1.groupId = t2.group_id " +
                            "JOIN Student as t3 on t2.student_id = t3.id " +
                            "where lower(t3.studentName) = lower(:studentName)")
                    .setParameter("studentName", studentName)
                    .list();
        }
    }

    public List<Student> getStudentsByGroup(String groupName) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("SELECT students from Group where lower(groupName) = lower(:groupName)")
                    .setParameter("groupName", groupName)
                    .list();
        }
    }

    public List<Group> getGroupsByStudentName(String studentName) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("SELECT groups from Student where lower(studentName) = lower(:studentName)")
                    .setParameter("studentName", studentName)
                    .list();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}