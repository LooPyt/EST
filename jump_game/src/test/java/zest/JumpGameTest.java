package zest;

import net.jqwik.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class JumpGameTest {
    int AssertsForFail  = 0;
    int AssertsForPass = 0;
    boolean CheckForZeroGaps(List<Integer> ListToCheck, int length) {
        boolean CanJump=true;
        if (length==1){
            return true;
        }
        if (ListToCheck.get(0)==0){
            return false;
        }

        for (int i = 1; i < length-1; i++) {
            if (ListToCheck.get(i) == 0) {
                CanJump = false;
                for (int j = i-1; j >= 0; j--) {
                    if (i - j < ListToCheck.get(j)) {
                        CanJump = true;
                        break;
                    }
                }
                if (!CanJump){break;}
            }
        }
        return CanJump;
    }
    //private final JumpGame jg=new JumpGame();
    @Property
    void fail(@ForAll("RandomList") List<Integer> NonJumpableArray) {
        int[] array = new int[NonJumpableArray.size()];
        for(int i = 0; i < NonJumpableArray.size();i++)
        {array[i] = NonJumpableArray.get(i);}
                final JumpGame jg=new JumpGame(array);
        if (!CheckForZeroGaps(NonJumpableArray,NonJumpableArray.size())){
            assertFalse(jg.canJump());
            AssertsForFail ++;
        }
    }
    @Property
    void pass(
            @ForAll("RandomList") List<Integer> JumpableArray) {
        int[] array = new int[JumpableArray.size()];
        for(int i = 0; i < JumpableArray.size();i++)
        {array[i] = JumpableArray.get(i);}
        final JumpGame jg=new JumpGame(array);
        if (CheckForZeroGaps(JumpableArray,JumpableArray.size())){
            assertTrue(jg.canJump());
            AssertsForPass++;
        }
    }
    @Provide
    Arbitrary<List<Integer>> RandomList(){
        Arbitrary <List<Integer>> Candidate= Arbitraries.integers().between(0,1000).list().ofMaxSize(1000).ofMinSize(1);
        return Candidate;
    }

@Property
    void invalid(
        @ForAll("InputRaisesException") List<Integer> invalidList) {
    int[] InvalidArray = new int[invalidList.size()];
    for(int i = 0; i < invalidList.size();i++)
    {InvalidArray[i] = invalidList.get(i);}
    boolean ExceptionThrown =false;
        try{JumpGame jg = new JumpGame(InvalidArray);}
        catch (Exception e){
           ExceptionThrown = true;}
        assertTrue(ExceptionThrown);
    }

    @Provide
    Arbitrary<List<Integer>> InputRaisesException(){
        Arbitrary <List<Integer>> ToomanyValues= Arbitraries.integers().between(1,1000).list().ofMinSize(1001).ofMaxSize(10000);
        Arbitrary <List<Integer>> ToohightValues= Arbitraries.integers().between(10001,10000000).list().ofMaxSize(1000).ofMinSize(1);
        Arbitrary <List<Integer>> ToolowValues= Arbitraries.integers().between(-10000000,-1).list().ofMinSize(1).ofMaxSize(1000);
        Arbitrary <List<Integer>> ToofewValues= Arbitraries.integers().between(1,1000).list().ofSize(0);
        return Arbitraries.oneOf(ToomanyValues,ToohightValues,ToolowValues,ToofewValues);
    }
    }
