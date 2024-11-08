package spreadsheet;

import java.io.InputStreamReader;


/**
 * The main program to launch the spreadsheet application, using a simple text interface.
 */
public class SpreadSheetProgram {

  /**
   * The entry point of the spreadsheet application.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    SpreadSheet model = new SparseSpreadSheet();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    SpreadSheetController controller = new SpreadSheetController(model, rd, ap);
    controller.startController();
  }
}