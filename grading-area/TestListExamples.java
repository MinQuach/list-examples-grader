import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

public class TestListExamples {

  @Test(timeout = 500)
  public void testMergeEmpty() {
    List<String> left = Arrays.asList();
    List<String> right = Arrays.asList();
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList();
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeAllIncluded() {
    List<String> left = Arrays.asList("a", "a", "a");
    List<String> right = Arrays.asList("b", "b");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "a", "b", "b");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeInRightOrder() {
    List<String> left = Arrays.asList("a", "b", "e", "g");
    List<String> right = Arrays.asList("c", "d", "f");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
    assertEquals(expected, merged);
  }

  @Test
    public void testFilterAddsSingleElementForWhichStringCheckerIsTrue(){
      StringChecker sc = (s) -> true;
        List<String> inputList = new ArrayList<>();
        inputList.add("Test");
        List<String> outList = ListExamples.filter(inputList, sc);
        assertEquals(1, outList.size());
        assertEquals("Test", outList.get(0));
    }  

    @Test
    public void testFilterRetainsOrderOfInput(){
        StringChecker sc = (s) -> true;
        List<String> inputList = new ArrayList<>();
        inputList.add("A");
        inputList.add("B");
        List<String> outList = ListExamples.filter(inputList, sc);
        assertEquals(2, outList.size());
        assertEquals("A", outList.get(0));
        assertEquals("B", outList.get(1));
    }

    @Test
    public void testFilterDoesNotIncludeElementsWhereSCReturnsFalse(){
        StringChecker sc = (s) -> false;
        List<String> inputList = new ArrayList<>();
        inputList.add("A");
        inputList.add("B");
        List<String> outList = ListExamples.filter(inputList, sc);
        assertEquals(0, outList.size());
    }
}
