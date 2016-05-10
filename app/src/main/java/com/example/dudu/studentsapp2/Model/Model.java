package com.example.dudu.studentsapp2.Model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michael on 3/27/2016.
 */
public class Model {

    private final static Model instance = new Model();

    private Model()
    {
        Init();
    }

    public static Model instance(){
        return instance;
    }

    List<Student> data = new LinkedList<Student>();

    void Init()
    {
        for(int i=0; i<4; i++)
        {
            Student st = new Student("" + i, "name" + i, "" + (i * 13), "address", "16/7/92", "14:52", "image");

            if (i % 2 == 0)
            {
                st.setChecked(true);
            }
            else
            {
                st.setChecked(false);
            }

            this.Add(st);
        }
    }

    public void Add(Student student)
    {
        data.add(student);
    }

    public Student getStudent(String id)
    {
        for (Student student:data)
        {
            if(student.getId().equals(id))
            {
                return student;
            }
        }

        return null;
    }

    public List<Student> getStudents()
    {
        return data;
    }

    public Student getStudent(int position) {
        if (position != -1) {
            return data.get(position);
        }
        else {
            Student student = new Student();
            data.add(student);
            return student;
        }
    }

    public void delete(Student student)
    {
        data.remove(student);
    }
    public void delete(int position)
    {
        data.remove(position);
    }
}
