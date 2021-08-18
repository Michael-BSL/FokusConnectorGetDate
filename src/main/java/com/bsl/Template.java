package com.bsl;

import java.io.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import gnu.regexp.*; // As we're stepping over to Java 1.4, use Javas' own...

/**
 * The <code>Template</code> class provides methods for performing
 * variable substitution in templates.
 * <p>
 * Study the following example:
 * <code>
 * <pre>
 * Template t = new Template("Your name is: ${name} and you are ${age} years old!!!");
 * Map      m = new HashMap(2);
 * m.put("name", "Clark Kent");
 * m.put("age",  "many");
 * System.out.println(t.expand(m));
 * </pre>
 * </code>
 * This code would produce the output
 * <code>Your name is: Clark Kent and you are many years old!!!</code>.
 * @author Håkan Gustavsson
 * @version $Id 1.0 $
 */
public class Template
{
  /** The input reader. */
  private        String  m_sText;

  /** The variable-matching regexp. */
  //private static RE      s_oRegExp;
  private static Pattern s_oPattern;

  /**
   * Initializes the variable-matching regexp.
   */
  static {
    /*
    try {
      s_oRegExp = new RE("\\$\\{([0-9A-Za-z_.]+)\\}");
    } catch (REException e) {
      throw new RuntimeException(e.getMessage());
    }
    */
    try {
      s_oPattern = Pattern.compile("\\$\\{([0-9A-Za-z_.]+)\\}");
    } catch (Throwable t) {
      throw new RuntimeException("Failed to create pattern", t);
    }
  }

  /**
   * Creates a <code>Template</code> object containing the specified template.
   * @param sTpl the template.
   */
  public Template(final String sTpl) {
    m_sText = sTpl;
  }

  /**
   * Creates a <code>Template</code> with the contents of the
   * specified reader.
   * @param reader the reader.
   * @exception IOException If the reader could not be read from.
   */
  public Template(final Reader reader) throws IOException {
    final BufferedReader br  = new BufferedReader(reader);
    final StringBuilder   buf = new StringBuilder(1024);
    final String         ls  = System.getProperty("line.separator", "\n");
    String               s   = null;

    for (int i = 0; (s = br.readLine()) != null; i++) {
       if (i != 0) {
          buf.append(ls);
       }
       buf.append(s);
    }
    m_sText = buf.toString();
  }

  /**
   * Applies the template to a given map and writes the results to a writer.
   * @param map    The map containg the values to substitute.
   * @param writer The writer.
   * @param leaveItems If <tt>true</tt>, leave the ${***} items as they were,
   * otherwise they are removed from the returned string.
   * @return The number of performed substitutions.
   * @exception IOException If an I/O error occurs.
   */
  public final int expand(final Map map, final Writer writer, final boolean leaveItems)
    throws IOException {
    final BufferedWriter out    = new BufferedWriter(writer);
    int                  nCount = 0;
    int                  nPos   = 0;

    /*
    //--| Old code as of Java 1.4 |--------------------------------------------
    for (REMatch rem = null; (rem = s_oRegExp.getMatch(m_sText, nPos)) != null; nCount++) {
      final String sKey = rem.toString(1);
      out.write(m_sText, nPos, rem.getStartIndex() - nPos);

      if (map.containsKey(sKey)) {
        final String sVal = (String)map.get(sKey);
        if (sVal != null) {
          out.write((String)map.get(sKey));
        } else if (leaveItems) {
          out.write((int)'$');
          out.write((int)'{');
          out.write(sKey);
          out.write((int)'}');
        }
      }
      nPos = rem.getEndIndex();
    }
    */

    //--| New code as of Java 1.4 |--------------------------------------------
    final Matcher match = s_oPattern.matcher(m_sText);
    for (boolean bMatch = true; (bMatch = match.find()); ++nCount) {
      final String sKey = match.group(1);
      out.write(m_sText, nPos, match.start() - nPos);

      final String sVal = (String)map.get(sKey);
      if (sVal != null) {
        out.write((String)map.get(sKey));
      } else if (leaveItems) {
        out.write((int)'$');
        out.write((int)'{');
        out.write(sKey);
        out.write((int)'}');
      }
      nPos = match.end();
    }

    if (nPos < m_sText.length()) {
      out.write(m_sText, nPos, m_sText.length() - nPos);
    }
    out.flush();
    return nCount;
  }

  /**
   * Applies the template to a given map and returns the result as a string.
   * @param map The map containg the values to substiture.
   * @return The substituted template.
   * @exception IOException If an I/O error occurs.
   */
  public final String expand(final Map map) throws IOException {
    final StringWriter writer = new StringWriter();
    expand(map, writer, false);
    return writer.toString();
  }

  /**
   * Applies the template to a given map and returns the result as a string.
   * @param map The map containg the values to substiture.
   * @param leaveItems If <tt>true</tt>, leave the ${***} items as they were,
   * otherwise they are removed from the returned string.
   * @return The substituted template.
   * @exception IOException If an I/O error occurs.
   */
  public final String expand(final Map map, final boolean leaveItems)
    throws IOException {
    final StringWriter writer = new StringWriter();
    expand(map, writer, leaveItems);
    return writer.toString();
  }

  /**
   * Applies the template to a given map and returns the result as a string.
   * @param sTpl The template.
   * @param map  The map containg the values to substitute.
   * @return The substituted template.
   * @exception IOException If an I/O error occurs.
   */
  public final static String expand(final String sTpl, final Map map)
    throws IOException {
    return new Template(sTpl).expand(map);
  }

  /**
   * Applies the template to a given map and returns the result as a string.
   * @param sTpl The template.
   * @param map  The map containg the values to substitute.
   * @param leaveItems If <tt>true</tt>, leave the ${***} items as they were,
   * otherwise they are removed from the returned string.
   * @return The substituted template.
   * @exception IOException If an I/O error occurs.
   */
  public final static String expand(final String  sTpl,
                                    final Map     map,
                                    final boolean leaveItems)
    throws IOException {
    return new Template(sTpl).expand(map, leaveItems);
  }

  /**
   * Applies the template to a given map and returns the result as a string.
   * @param reader The reader from which to read the template.
   * @param map  The map containg the values to substitute.
   * @return The substituted template.
   * @exception IOException If an I/O error occurs.
   */
  public final static String expand(final Reader reader, final Map map)
    throws IOException {
    return new Template(reader).expand(map);
  }

  /**
   * Applies the template to a given map and returns the result as a string.
   * @param reader The reader from which to read the template.
   * @param map  The map containg the values to substitute.
   * @param leaveItems If <tt>true</tt>, leave the ${***} items as they were,
   * otherwise they are removed from the returned string.
   * @return The substituted template.
   * @exception IOException If an I/O error occurs.
   */
  public final static String expand(final Reader  reader,
                                    final Map     map,
                                    final boolean leaveItems)
    throws IOException {
    return new Template(reader).expand(map, leaveItems);
  }

  /**
   * Applies the template to a given map and writes the results to a writer.
   * @param sTpl The template.
   * @param map  The map containg the values to substitute.
   * @param writer The writer.
   * @return The number of performed substitutions.
   * @exception IOException If an I/O error occurs.
   */
  public final static int expand(final String sTpl,
                                 final Map    map,
                                 final Writer writer)
    throws IOException {
    return new Template(sTpl).expand(map, writer, false);
  }

  /**
   * Applies the template to a given map and writes the results to a writer.
   * @param sTpl The template.
   * @param map  The map containg the values to substitute.
   * @param writer The writer.
   * @param leaveItems If <tt>true</tt>, leave the ${***} items as they were,
   * otherwise they are removed from the returned string.
   * @return The number of performed substitutions.
   * @exception IOException If an I/O error occurs.
   */
  public final static int expand(final String  sTpl,
                                 final Map     map,
                                 final Writer  writer,
                                 final boolean leaveItems)
    throws IOException {
    return new Template(sTpl).expand(map, writer, leaveItems);
  }

  /**
   * Applies the template to a given map and writes the results to a writer.
   * @param reader The reader from which to read the template.
   * @param map  The map containg the values to substitute.
   * @param writer The writer.
   * @return The number of performed substitutions.
   * @exception IOException If an I/O error occurs.
   */
  public final static int expand(final Reader reader,
                                 final Map    map,
                                 final Writer writer)
    throws IOException {
    return new Template(reader).expand(map, writer, false);
  }

  /**
   * Applies the template to a given map and writes the results to a writer.
   * @param reader The reader from which to read the template.
   * @param map  The map containg the values to substitute.
   * @param writer The writer.
   * @param leaveItems If <tt>true</tt>, leave the ${***} items as they were,
   * otherwise they are removed from the returned string.
   * @return The number of performed substitutions.
   * @exception IOException If an I/O error occurs.
   */
  public final static int expand(final Reader  reader,
                                 final Map     map,
                                 final Writer  writer,
                                 final boolean leaveItems)
    throws IOException {
    return new Template(reader).expand(map, writer, leaveItems);
  }

  /**
   * The main test-method. Calls upon <tt>Template.expand(...)</tt> using the
   * system properties, and dumps the result to <tt>System.out</tt>.
   * @param args Ignored command line arguments.
   */
  public static void main(String[] args) {
    try {
      final Map    map = System.getProperties();
      final String str =
        "${UglyStuff}My username is ${user.name}, my platform is ${user.country}-centered and I'm running ${os.name}." +
        "${line.separator}My CPU is of the ${os.arch} architecture (${sun.arch.data.model} bits), and my endians are ${sun.cpu.endian}...";
      System.out.println(Template.expand(str, map, false));
    } catch (Throwable t) {
      t.printStackTrace(System.out);
    }
  }
}