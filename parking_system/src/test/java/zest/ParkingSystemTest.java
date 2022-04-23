package zest;
import net.jqwik.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingSystemTest {
    @Property
    void pass(@ForAll("Pass")
                      List<Integer> Parkable){
        int Big = Parkable.get(0);
        int Medium = Parkable.get(1);
        int Small = Parkable.get(2);
        assertTrue(Big+Medium+Small<=1000);
        ParkingSystem parkingSystem= new ParkingSystem(Big,Medium,Small);
        boolean ParkOK=true;
        while (Big>0&&ParkOK){
            ParkOK=parkingSystem.addCar(1);
            Big--;
        }
        while (Medium>0&&ParkOK){
            parkingSystem.addCar(2);
            Medium--;
        }
        while (Small>0&&ParkOK){
            parkingSystem.addCar(3);
            Small--;
        }
    assertTrue(ParkOK);
    }
@Provide
    Arbitrary<List<Integer>> Pass(){
        Arbitrary<List<Integer>> Parking =Arbitraries.integers().between(0,1000).list().ofSize(3).filter(list->list.get(0)+list.get(1)+list.get(2)<=1000);
        return Parking;
}
    @Property
    void fail(@ForAll("Fail")
                      List<Integer> Parkable){
        boolean ExceptionThrown=false;
        int Big = Parkable.get(0);
        int Medium = Parkable.get(1);
        int Small = Parkable.get(2);
        try{ ParkingSystem parkingSystem= new ParkingSystem(Big,Medium,Small);
        while (Big>0){
            if(parkingSystem.addCar(1));
            Big--;
        }
        while (Medium>0){
            parkingSystem.addCar(2);
            Medium--;
         }
        while (Small>0){
            parkingSystem.addCar(3);
            Small--;
        }}
        catch (Exception e){ExceptionThrown=true;}
        assertTrue(ExceptionThrown);//should catch all exceptions except the one thrown by the additional car type
    }
    @Provide
    Arbitrary<List<Integer>> Fail(){
        Arbitrary<List<Integer>> Toomany =Arbitraries.integers().between(0,1000).list().ofSize(3).filter(list->list.get(0)+list.get(1)+list.get(2)>1000);
        Arbitrary<List<Integer>> TomanyOfOneType =Arbitraries.integers().between(1001,100000).list().ofSize(3);
        Arbitrary<List<Integer>> NegativeValues =Arbitraries.integers().between(-1000,-1).list().ofSize(3).filter(list->list.get(0)+list.get(1)+list.get(2)<=1000);

        return Arbitraries.oneOf(Toomany,TomanyOfOneType,NegativeValues);
    }
    @Property
    void AdditionalCarTypeFail(@ForAll("AdditionalCarType") Integer InvalidCarType){
        boolean RaisedExeption= false;
        ParkingSystem parkingSystem = new ParkingSystem(3,4,5);
        try{parkingSystem.addCar(InvalidCarType);}
        catch (Exception e){RaisedExeption=true;}
        assertTrue(RaisedExeption);
    }
    @Provide
    Arbitrary<Integer> AdditionalCarType(){
        Arbitrary<Integer> NonValidValues = Arbitraries.integers().filter(number->number!=1&&number!=2&&number!=3);
        return NonValidValues;
    }
}
