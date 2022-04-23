package zest;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class StatsAnalyzerTest {
    @Test
    void CountECTSforTeacher(){



        //when(StatsAnalyzer.getTeachersECTS()).thenReturn(mymockECTS);
        CourseCurriculum mycuriculum = mock(CourseCurriculum.class);

        List<String> mockallcourses = new ArrayList<>();
        List<String> Teachername = new ArrayList<>();
        List <Integer> ECTS = new ArrayList<>();
        
        }

        String Teacher = "Luis";
        String CourseTitle = "EST";
        int ECTS = 6;
        when(mycuriculum.addCourse(Teacher,CourseTitle,ECTS)).then(mycuriculum.ectsCourse(CourseTitle)==ECTS);


        when(mycuriculum.allCourses()).thenReturn()





        StatsAnalyzer statsAnalyzer = new StatsAnalyzer(CourseCurriculum mycuriculum);




        curriculum.addCourse("Bagliatelli","EST",6);
        curriculum.addCourse("Rolf","ITSecurity",3);
        curriculum.addCourse("xyz","CG",3);
        curriculum.addCourse("lsdjk","InfoII",6);
        when(curriculum.addCourse(Teacher,CourseTitle,ECTS))

        List<Course> listOfCourses = Arrays.asList(EST,ITSecurity,InfoII,CG);
        when(curriculum.allCourses()).thenReturn(listOfCourses);
        when(curriculum.teacherCourse(Course AnyCourse)).then

    }

}