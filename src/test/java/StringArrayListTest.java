import org.example.ElementNotFoundException;
import org.example.InvalidArgumentException;
import org.example.StringListIndexOutOfBoundsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StringArrayListTest {
    private StringArrayList out=new StringArrayList(5);
    @BeforeEach
    public void fillList(){
        out.add("one");
        out.add("two");
        out.add("three");
        out.add("four");
        out.add("five");
    }
    @AfterEach
    public void clearList(){
        out.clear();
    }
    @Test
    public void simpleAdditionPositiveTest(){
        int size=out.size();
        assertEquals("six",out.add("six"));
        assertEquals(size+1,out.size());
    }
    @Test
    public void indexedAdditionPositiveTest(){
        int size=out.size();
        int index=1;
        assertEquals("six",out.add(index,"six"));
        assertEquals( index,out.indexOf("six"));
        assertEquals(size+1,out.size());
    }
    @Test
    public void indexedAdditionNegativeTest(){
        assertThrows(StringListIndexOutOfBoundsException.class,()->out.add(5,"six"));
    }
    @Test
    public void setingPositiveTest(){
        int size=out.size();
        int index=1;
        assertEquals("six",out.set(index,"six"));
        assertEquals(index,out.indexOf("six"));
        assertEquals(size,out.size());
    }
    @Test
    public void settingNegativeTest(){
        assertThrows(StringListIndexOutOfBoundsException.class,()->out.set(5,"six"));
    }
    @Test
    public void removeByValuePositiveTest(){
        int size=out.size();
        assertEquals("one",out.remove("one"));
        assertEquals(size-1,out.size());
    }
    @Test
    public void removeByValueNegativeTest(){
        assertThrows(ElementNotFoundException.class,()->out.remove("six"));
    }
    @Test
    public void removeByIndexPositiveTest(){
        int size=out.size();
        assertEquals("one",out.remove(0));
        assertEquals(size-1,out.size());
    }
    @Test
    public void removeByIndexNegativeTest(){
        assertThrows(StringListIndexOutOfBoundsException.class,()->out.remove(5));
    }
    public static Stream<Arguments>argumentsForContainsPositiveTest(){
        return Stream.of(Arguments.of("one"),
                         Arguments.of("three"),
                         Arguments.of("five"));
    }
    @ParameterizedTest
    @MethodSource("argumentsForContainsPositiveTest")
    public void containsPositiveTest(String srt){
        assertTrue(out.contains(srt));
    }
    public static Stream<Arguments>argumentsForContainsNegativeTest(){
        return Stream.of(Arguments.of("six"),Arguments.of("seven"),Arguments.of("eight"));
    }
    @ParameterizedTest
    @MethodSource("argumentsForContainsNegativeTest")
    public void  containsNegativeTest(String srt){
        assertFalse(out.contains(srt));
    }
    public static Stream<Arguments>argumentsForIndexOfPositiveTest(){
        return Stream.of(Arguments.of("one",0),
                Arguments.of("two",1),
                Arguments.of("three",2),
                Arguments.of("four",3),
                Arguments.of("five",4));
    }
    @ParameterizedTest
    @MethodSource("argumentsForIndexOfPositiveTest")
    public void indexOfPositiveTest(String srt,int index){
        assertEquals(index,out.indexOf(srt));
    }
    public static Stream<Arguments>argumentsForIndexOfNegativetiveTest(){
        return Stream.of(Arguments.of("six",-1),Arguments.of("Hello Word",-1));
    }
    @ParameterizedTest
    @MethodSource("argumentsForIndexOfNegativetiveTest")
    public void IndexOfNegativetiveTest(String srt,int index){
        assertEquals(index,out.indexOf(srt));
    }
    public static Stream<Arguments>argumentsForLastIndexOfTest(){
        return Stream.of(Arguments.of("one",5),Arguments.of("two",6),Arguments.of("six",-1));
    }
    @ParameterizedTest
    @MethodSource("argumentsForLastIndexOfTest")
    public void LastIndexOfTest(String str,int index){
        out.add("one");
        out.add("two");
        assertEquals(index,out.lastIndexOf(str));
    }
    public static Stream<Arguments>argumentsForGetPositiveTest(){
        return Stream.of(Arguments.of("one",0),Arguments.of("two",1),Arguments.of("three",2),
                Arguments.of("four",3), Arguments.of("five",4));
    }
    @ParameterizedTest
    @MethodSource("argumentsForGetPositiveTest")
    public void getPositiveTest(String str,int index){
        assertEquals(str,out.get(index));
    }
    @Test
    public void getNegativeTest(){
        assertThrows(StringListIndexOutOfBoundsException.class,()->out.get(5));
    }
    @Test
    public void egualsPositiveTest(){
        StringArrayList test=new StringArrayList(5);
        test.add("one");
        test.add("two");
        test.add("three");
        test.add("four");
        test.add("five");
        assertTrue(out.eguals(test));
    }
    @Test
    public void egualsNullNegativeTest(){
        assertThrows(InvalidArgumentException.class,()->out.eguals(null));

    }
    public static Stream<Arguments>argumentsForEgualsNegativeTest(){
        return Stream.of(Arguments.of((new StringArrayList("one","two","three"))),
                         Arguments.of(new StringArrayList("one","two","three","four","six")),
                         Arguments.of(new StringArrayList("zero","two","three","four","five")),
                         Arguments.of(new StringArrayList("one","two","drei","four","five")));
    }
    @ParameterizedTest
    @MethodSource("argumentsForEgualsNegativeTest")
    public void egualsNegativeTest(StringArrayList arg){
        assertFalse(out.eguals(arg));
    }
    @Test
    public void isEmptyPositiveTest(){
        StringArrayList test=new StringArrayList(5);
        assertTrue(test.isEmpty());
    }


    @Test
    public void isEmptyNegativeTest(){
        assertFalse(out.isEmpty());
    }
    @Test
    public void toArrayTest(){
        String[] test={"one","two","three","four","five"};
        assertArrayEquals(test,out.toArray());
    }
}
