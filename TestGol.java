import org.junit.Test;
import static org.junit.Assert.*;

public class TestGol {

    @Test
    public void testGameOfLifeInit() {
		int x_size = 10;
		int y_size = 100;
		System.out.println("Testing initialising GameOfLife class");
		GameOfLife testing = new GameOfLife(x_size, y_size);
		assertEquals(x_size, testing.getRowLen());
		assertEquals(y_size, testing.getColumnLen());
		System.out.println("All method's tests pass");
    }

    @Test
    public void testDefault() {
		GameOfLife testing = new GameOfLife();
		assertEquals(GameOfLife.DEFAULT_ROW_LEN, testing.getRowLen());
		assertEquals(GameOfLife.DEFAULT_COL_LEN, testing.getColumnLen());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalColumnSize() {
		GameOfLife testing = new GameOfLife(10, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalRowSize() {
		GameOfLife testing = new GameOfLife(-10, 1);
    }
        
    @Test
    public void testGetCellValue(){
		GameOfLife testing = new GameOfLife();
		assertFalse(testing.getCellValue(0, 0));
		assertFalse(testing.getCellValue(-1, 0));
		assertFalse(testing.getCellValue(0, 1));
    }

    @Test
    public void TestSetCellValue(){
	    GameOfLife testing = new GameOfLife();
	    assertFalse(testing.getCellValue(0, 0));
	    testing.setCellValue(0, 0, true);
	    assertTrue(testing.getCellValue(0, 0));
	    testing.setCellValue(1, 0, true);
	    assertTrue(testing.getCellValue(1, 0));
	    testing.setCellValue(1, 0, false);
	    assertFalse(testing.getCellValue(1, 0));
	    testing.setCellValue(99, 99, true);
	    assertTrue(testing.getCellValue(99, 99));
    }

}
