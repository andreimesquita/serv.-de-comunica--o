import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserFunction;

/**
 * @author Christopher Deckers
 */
public class WebBrowserFunctionExample extends JPanel {

  private static final String LS = System.getProperty("line.separator");

  public WebBrowserFunctionExample() {
    super(new BorderLayout());
    JPanel webBrowserPanel = new JPanel(new BorderLayout());
    webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Native Web Browser component"));
    JWebBrowser webBrowser = new JWebBrowser();
    webBrowser.setBarsVisible(false);
    final JTextArea functionCallsTextArea = new JTextArea(7, 0);
    webBrowser.registerFunction(new WebBrowserFunction("invokeJava") {
      @Override
      public Object invoke(JWebBrowser webBrowser, Object... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("-> " + getName() + "() called from Javascript with args:");
        for (int i=0; i<args.length; i++) {
          Object arg = args[i];
          if (arg == null) {
            sb.append(LS).append("  null");
          } else {
            sb.append(LS).append("  ").append(arg instanceof Object[]? Arrays.deepToString((Object[])arg): arg.toString()).append(" (").append(arg.getClass().getSimpleName()).append(")");
          }
        }
        sb.append(LS).append("-> return Object[] result:").append(LS).append("  (short)3").append(LS).append("  true").append(LS).append("  null").append(LS).append("  new Object[] {\"A String\", false}").append(LS).append("  \"Hello World!\"").append(LS).append("  2.0f / 3.0f");
        functionCallsTextArea.setText(sb.toString());
        functionCallsTextArea.setCaretPosition(0);
        return new Object[] {(short)3, true, null, new Object[] {"A String", false}, "Hello World!", 2.0f / 3.0f};
      }
    });
    webBrowser.registerFunction(new WebBrowserFunction("invokeJavaWithError") {
      @Override
      public Object invoke(JWebBrowser webBrowser, Object... args) {
        functionCallsTextArea.setText("-> " + getName() + "() called from Javascript." + LS + "-> Generating a Java runtime exception.");
        functionCallsTextArea.setCaretPosition(0);
        // This will generate an error
        return  10 / 0;
      }
    });
    webBrowser.setHTMLContent(
        "<html>" + LS +
        "  <head>" + LS +
        "    <script language=\"JavaScript\">" + LS +
        "      function callJava() {" + LS +
        "        var result = invokeJava(123, false, null, [1.2, ['hi', true]], 'swing');" + LS +
        "        var s = 'Result from invokeJava():';" + LS +
        "        for (var i = 0; i < result.length; i++) {" + LS +
        "          s += '\\n    ' + result[i];" + LS +
        "        }" + LS +
        "        alert(s);" + LS +
        "      }" + LS +
        "      function callJavaWithError() {" + LS +
        "        var s = 'Result from invokeJavaWithError():\\n    ';" + LS +
        "        try {" + LS +
        "          invokeJavaWithError();" + LS +
        "          s += 'success';" + LS +
        "        } catch (e) {" + LS +
        "          s += 'Java error (' + e.message + ')';" + LS +
        "        }" + LS +
        "        alert(s);" + LS +
        "      }" + LS +
        "    </script>" + LS +
        "  </head>" + LS +
        "  <body>" + LS +
        "    <p>" + LS +
        "      Object[] invokeJava(123, false, null, [1.2, ['hi', true]], 'swing'):<br/><input id=button type=\"button\" value=\"call\" onclick=\"callJava();\">" + LS +
        "    </p><p>" + LS +
        "      Object invokeJavaWithError():<br/><input id=button type=\"button\" value=\"call\" onclick=\"callJavaWithError();\">" + LS +
        "    </p>" + LS +
        "  </body>" + LS +
        "</html>");
    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
    webBrowserPanel.setPreferredSize(new Dimension(100, 200));
    add(webBrowserPanel, BorderLayout.NORTH);
    // Create an additional area to show the function calls.
    JPanel functionalCallsPanel = new JPanel(new BorderLayout());
    functionalCallsPanel.setBorder(BorderFactory.createTitledBorder("Java log for Javascript custom function calls"));
    functionCallsTextArea.setEditable(false);
    functionalCallsPanel.add(new JScrollPane(functionCallsTextArea));
    add(functionalCallsPanel, BorderLayout.CENTER);
  }

  /* Standard main method to try that test as a standalone application. */
  public static void main(String[] args) {
    UIUtils.setPreferredLookAndFeel();
    NativeInterface.open();
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("DJ Native Swing Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new WebBrowserFunctionExample(), BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
      }
    });
    NativeInterface.runEventPump();
  }

}