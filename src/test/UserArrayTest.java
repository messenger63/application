package test;

import com.vlevytskyy.ADT.ArrayDataType;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created with IntelliJ IDEA.
 * User: Artist
 * Date: 17.11.13
 * Time: 2:34
 * To change this template use File | Settings | File Templates.
 */
public class UserArrayTest {
    @Test
    public void shouldInsertElements () {
        ArrayDataType dataType = new ArrayDataType();
        dataType.add(5);
        System.out.println(dataType.get(0));
    }

    @Test
    public void shouldCreateCollectionCorrectSize () {
        ArrayDataType dataType = new ArrayDataType();

        int count = 5;
        for (int i = 0; i < count; i++) {
            dataType.add(Math.random());
        }

        assertEquals(count, dataType.size());
    }

    @Test
    public void shouldIncreaseSizeWhenInsertElements () {
        int size = 5;
        ArrayDataType dataType = new ArrayDataType(size);

        for (int i = 0; i < size * 2; i++) {
            dataType.add(Math.random());
        }

        assertEquals(size * 2, dataType.size());
    }

    @Test
    public void shouldAddElementsInTurn () {
        ArrayDataType dataType = new ArrayDataType();

        dataType.add(10);
        dataType.add(100);
        dataType.add(1);

        assertEquals(100, dataType.get(1));
    }

    @Test
    public void shouldBeEmptyWhenDeleteAllElements () {
        ArrayDataType dataType = new ArrayDataType();

        dataType.add(Math.random());
        dataType.remove(0);

        assertEquals(true, dataType.isEmpty());
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void shouldExceptionWhenGetFromEmptyCollection () {
        ArrayDataType dataType = new ArrayDataType();

        dataType.get(0);
    }

}
