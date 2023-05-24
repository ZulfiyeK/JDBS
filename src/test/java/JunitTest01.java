import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JunitTest01 {

    @Test
    public void test01(){
        assertEquals(1,1);// Bu methodun parantez icindeki parameterleri esitse pass olur, degilse fail olur.(1,1)==> 1,1'e esitse pass

        assertTrue(true);//Bu methodun parantez icin true isi pass olur, degilse fail olur.

    }


    @Test
    public void test02(){
        assertEquals(2,2);//esitlik aglanmazsa hata alir ve kizarir. esitlik saglaninca yesil tik atilir.

        assertTrue("Hello".contains("e"));

    }

}
