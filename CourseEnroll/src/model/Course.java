package model;

import customExceptions.GradesCancelationException;
import customExceptions.LateEnrollmentExeption;
import customExceptions.OutOfRangeGradeException;
import customExceptions.QuotaEnrollExceedException;
import customExceptions.AlreadyEnrolledStudentException;

import java.util.concurrent.CancellationException;

public class Course {

    private double maxGrade;
    private double minGrade;
    private int currentWeek;
    private int totalGradesAmount;
    private int maxQuota;

    private Student[] studentsEnrolled;

    public Course(int tga, double mx, double mn, int mq) {
        currentWeek = 1;
        maxGrade = mx;
        minGrade = mn;
        totalGradesAmount = tga;
        maxQuota = mq;

        studentsEnrolled = new Student[mq];
    }

    public void enroll(String id) throws QuotaEnrollExceedException, LateEnrollmentExeption, AlreadyEnrolledStudentException{
        int posNewStudent = searchFirstAvailable();

        if (searchStudent(id) != -1) {
            throw new AlreadyEnrolledStudentException();
        }

        if (currentWeek >= 2) {
            throw new LateEnrollmentExeption();
        } else if (posNewStudent == -1) {
            throw new QuotaEnrollExceedException(maxQuota);

        } else {
            studentsEnrolled[posNewStudent] = new Student(id, totalGradesAmount);
        }

    }

    public void cancelEnrollment(String id) throws GradesCancelationException {

        int posStudent = searchStudent(id);

        if (studentsEnrolled[posStudent].hasMoreThanHalfGrades()) {
            throw new CancellationException();
        }
        studentsEnrolled[posStudent] = null;
    }

    public void setStudentGrade(String id, int gradeNumber, double grade)
            throws ArrayIndexOutOfBoundsException, OutOfRangeGradeException {
        if (grade < minGrade || grade > maxGrade) {
            throw new OutOfRangeGradeException(grade, maxGrade, minGrade);
        }

        int posStudent = searchStudent(id);
        studentsEnrolled[posStudent].setGrade(gradeNumber, grade);
    }

    public void advanceWeek() {
        currentWeek++;
    }

    private int searchFirstAvailable() {
        int pos = -1;
        for (int i = 0; i < studentsEnrolled.length && pos == -1; i++) {
            Student current = studentsEnrolled[i];
            if (current == null) {
                pos = i;
            }
        }

        return pos;
    }

    private int searchStudent(String id) {
        int pos = -1;

        for (int i = 0; i < studentsEnrolled.length && pos == -1; i++) {
            Student current = studentsEnrolled[i];
            if (current != null) {
                if (id.contentEquals(current.getId())) {
                    pos = i;
                }
            }
        }

        return pos;
    }

    public String showEnrolledStudents() {

        String msg = "";

        for (int i = 0; i < studentsEnrolled.length; i++) {

            if (studentsEnrolled[i] != null) {

                msg += studentsEnrolled[i].getId() + "\n";

            }

        }

        return msg;

    }

    public String showStudentGrades(String id) {

        int pos = searchStudent(id);

        if (pos != -1) {

            return studentsEnrolled[pos].showGrades();

        }

        return "Error";

    }
}
