package classes;

/**
 * Created by Beng Haun on 2/12/2017.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student extends User {
    int studentID;
    Map<String,Integer> questionsMap = new HashMap<>();  //Subjects and no of questions asked
    Map<String,Integer> answersMap = new HashMap<>();    //Subjects and answers added
    Map<String,Double> scoresQn  = new HashMap<>();
    Map<String,Double> scoresAns  = new HashMap<>();
    Map<String,Double> finalGrade = new HashMap<>();

    public Student(){
        super();
        //public constructor for Firebase
    }
    public Student(String username, String uid) {
        super(username, uid);
    }

    public void addSubject(Subject subject){
        subjects.add(subject);
        //initialize subject
        questionsMap.put(subject.getKey(),0);
        answersMap.put(subject.getKey(),0);
        subject.addStudent(subject.getKey());
    }



    //This function calculates the student grades. The second argument determines the grades for
    //invoker type (Student or Teacher).
    //questionsMap : Hash map that contains course code and the corresponding number of questions
    //answersMap : Hash map that contains course code and the corresponding number of answers
    public String[] calculateScore(ArrayList<Week> allWeeks, String type) {
        Map<String, Integer> qnsForCourses = new HashMap<>();
        for (Week t : allWeeks) {
            String key = t.getKey().split(" ")[0];
            if (questionsMap.containsKey(key)) {
                if (qnsForCourses.containsKey(key)){
                    qnsForCourses.put(key, qnsForCourses.get(key) + t.getQuestions().size());
                }

                else{
                    qnsForCourses.put(key, t.getQuestions().size());
                }
            }
        }

        for (String courseCode : questionsMap.keySet()) {
            //double mean = (qnsForCourses.get(courseCode)) / (Subject.getTotalStudents(courseCode));
            double val = questionsMap.get(courseCode);
            scoresQn.put(courseCode, val);
        }


        //Get answer score
        Map<String,Integer> ansForCourses = new HashMap<>();
        for (Week t: allWeeks){
            String key = t.getKey().split(" ")[0];
            if (answersMap.containsKey(key)){
                ArrayList<Question> qns = t.getQuestions();
                int answers = 0;
                for (Question q: qns){
                    answers += q.getAnswersTotal();
                }

                if (ansForCourses.containsKey(key)){
                    ansForCourses.put(key,ansForCourses.get(key)+ answers);
                }
                else{
                    ansForCourses.put(key,answers);
                }
            }
        }

        for (String courseCode: answersMap.keySet()){
            //Log.i("keshik",String.valueOf(ansForCourses.get(courseCode)));
            //double mean = (ansForCourses.get(courseCode))/(Subject.getTotalStudents(courseCode));
            double val = answersMap.get(courseCode);
            scoresAns.put(courseCode,val);
        }


        //Calculate final grade
        for (String courseCode: answersMap.keySet()){
            finalGrade.put(courseCode,(scoresAns.get(courseCode) + scoresQn.get(courseCode))/2.0);
        }


        //Determine the grade using the threshold points defined for the data analytics portion
        String gradeStd = "";
        String gradeTeacherQn = "";
        String gradeTeacherAns = "";
        for (String courseCode: finalGrade.keySet()){
            String courseName = courseCode.substring(0,2) + "." + courseCode.substring(2,courseCode.length()) + ":\t";
            gradeStd += courseName;
            gradeTeacherQn += courseName + questionsMap.get(courseCode);
            gradeTeacherAns += courseName + answersMap.get(courseCode);
            if (finalGrade.get(courseCode) >= 15){
                gradeStd += "A";
            }
            else if (finalGrade.get(courseCode) >= 10){
                gradeStd += "B";
            }
            else if (finalGrade.get(courseCode) >= 5){
                gradeStd += "C";
            }
            else{
                gradeStd += "D";
            }

            if (!courseCode.equals(finalGrade.keySet().equals(courseCode))){
                gradeStd += "\n";
                gradeTeacherAns += "\n";
                gradeTeacherQn += "\n";
            }
        }

        String[] solution = new String[2];
        if (type.equals("Teacher")){
            solution[0] = gradeTeacherQn;
            solution[1] = gradeTeacherAns;
            if (gradeTeacherQn.equals("")){
                solution[0] = "0";
            }
            if (gradeTeacherAns.equals("")){
                solution[1] = "0";
            }
        }
        else{
            solution[0] = gradeStd;
            solution[1] = null;
        }
        return solution;
    }

    //public getters and setters for Firebase
    public Map<String, Integer> getQuestionMap() {
        return questionsMap;
    }

    public void setQuestionMap(Map<String, Integer> questionMap) {
        this.questionsMap = questionMap;
    }

    public Map<String, Integer> getAnswerMap() {
        return answersMap;
    }

    public void setAnswerMap(Map<String, Integer> answerMap) {
        this.answersMap = answerMap;
    }

    public Map<String, Double> getScoresQn() {
        return scoresQn;
    }

    public void setScoresQn(Map<String, Double> scoresQn) {
        this.scoresQn = scoresQn;
    }

    public Map<String, Double> getScoresAns() {
        return scoresAns;
    }

    public void setScoresAns(Map<String, Double> scoresAns) {
        this.scoresAns = scoresAns;
    }

    public Map<String, Double> getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Map<String, Double> finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Map<String, Integer> getQuestionsMap() {
        return questionsMap;
    }

    public void setQuestionsMap(Map<String, Integer> questionsMap) {
        this.questionsMap = questionsMap;
    }

    public Map<String, Integer> getAnswersMap() {
        return answersMap;
    }

    public void setAnswersMap(Map<String, Integer> answersMap) {
        this.answersMap = answersMap;
    }


    public int getStudentID(){
        return studentID;
    }

    public void setStudentID(int studentID){
        this.studentID = studentID;
    }

}