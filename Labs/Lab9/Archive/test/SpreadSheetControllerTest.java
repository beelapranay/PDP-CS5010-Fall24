import org.junit.Test;
import spreadsheet.SpreadSheet;
import spreadsheet.SpreadSheetController;
import spreadsheet.SparseSpreadSheet;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class SpreadSheetControllerTest {

    @Test
    public void testAssignValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilder expected = new StringBuilder();
        expected.append("Welcome to the spreadsheet program!"+System.lineSeparator() +
                "Supported user instructions are: " + System.lineSeparator() +
                "assign-value row-num col-num value (set a cell to a value)" + System.lineSeparator() +
                "print-value row-num col-num (print the value at a given cell)" + System.lineSeparator() +
                "menu (Print supported instruction list)"+ System.lineSeparator() +
                "q or quit (quit the program) " + System.lineSeparator());
        for (int i=1;i<=3;i+=1) {
            for (int j=0;j<2;j+=1) {
                expected.append("Type instruction: ");
                sb.append("assign-value "+(char)('A'+j)+ " " +i + " -10"+System.lineSeparator());
            }
        }
        for (int i=1;i<6;i+=1) {
            for (int j=0;j<3;j+=1) {
                expected.append("Type instruction: ");
                sb.append("print-value "+(char)('A'+j)+" "+i+System.lineSeparator());
                if ((j<2) && (i<=3)) {
                    expected.append("Value: -10.0"+System.lineSeparator());
                }
                else {
                    expected.append("Value: 0.0"+System.lineSeparator());
                }
            }
        }
        expected.append("Thank you for using this program!");
        Readable input = new StringReader(sb.toString());
        Appendable output = new StringBuilder();
        SpreadSheet model = new SparseSpreadSheet();
        SpreadSheetController c = new SpreadSheetController(model,input,output);
        c.control();
        assertEquals(expected.toString(),output.toString());

    }
}