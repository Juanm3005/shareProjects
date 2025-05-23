package model;

public class Student {

    private String id;
    private double[] grades;

    public boolean hasMoreThanHalfGrades() {

        double half = grades.length / 2;
        double count = 0;
        for (double d : grades) {
            if (d > 0.0) {
                count++;
            }
        }
        if (count >= half) {

            return true;
        }

        return false;
    }

    public Student(String identifier, int totalGrades) {
        id = identifier;
        grades = new double[totalGrades];
    }

    public void setGrade(int gradeNumber, double grade) throws ArrayIndexOutOfBoundsException {
        grades[gradeNumber - 1] = grade;
    }

    public double getGrade(int gradeNumber) throws ArrayIndexOutOfBoundsException {
        return grades[gradeNumber - 1];
    }

    public String getId() {
        return id;
    }

    public String showGrades() {

        String msg = "[";

        for (int i = 0; i < grades.length; i++) {

            if (i < grades.length - 1) {
                msg += " " + grades[i] + " |";
            } else {
                msg += " " + grades[i] + " ]";
            }

        }

        return msg;

    }
}
