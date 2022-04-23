package zest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class StatsAnalyzerTest {
    List<String> mockallcourses = new ArrayList<>();
    List<String> Teachernames = new ArrayList<>();
    List <Integer> ECTS = new ArrayList<>();


    void addCourse( String CourseName, String Teachername, int numberofECTS){
        mockallcourses.add(CourseName);
        Teachernames.add(Teachername);
        ECTS.add(numberofECTS);
}
    int sumECTS(List<Integer> List){
        int sum = 0;
        for (int i=0;i<List.size();i++){
            sum+=List.get(i);
        }
        return sum;
    }
    List<String> distinctTeachers(List<String> Teacherlist){
        List<String> dTeachers = Teacherlist;
        for (int i=0; i<dTeachers.size()-1;i++){
            for (int j= i+1; j<dTeachers.size(); j++){
                if (dTeachers.get(i)==dTeachers.get(j)){
                    dTeachers.remove(i);
                }
            }
        }
        return dTeachers;


    }
    @Test
    void CountECTSforTeacher(){
        CourseCurriculum mycuriculum = mock(CourseCurriculum.class);
        addCourse("EST","Luis",3);
        addCourse("CG","Marvin",6  );
        addCourse("CGG","Marvin",2);

        when(mycuriculum.allCourses()).thenReturn(mockallcourses);
        for (int i =0; i<mockallcourses.size(); i++) {
            when(mycuriculum.ectsCourse(mockallcourses.get(i))).thenReturn(ECTS.get(i));
            when(mycuriculum.teacherCourse(mockallcourses.get(i))).thenReturn(Teachernames.get(i));
        }
        StatsAnalyzer statsAnalyzer = new StatsAnalyzer(mycuriculum);
        assertEquals(8, statsAnalyzer.getTeachersECTS("Marvin"));
    }
    @Test
    void AverageByTeacher(){
        CourseCurriculum curiculum = mock(CourseCurriculum.class);
        addCourse("EST","Luis",3);
        addCourse("CG","Marvin",6  );
        addCourse("CGG","Marvin",2);
        when(curiculum.allTeachers()).thenReturn(distinctTeachers(Teachernames));
        when(curiculum.totalEcts()).thenReturn(sumECTS(ECTS));

        StatsAnalyzer statsAnalyzer = new StatsAnalyzer(curiculum);
        assertEquals(5, statsAnalyzer.getAvgECTSByTeacher());

    }

}