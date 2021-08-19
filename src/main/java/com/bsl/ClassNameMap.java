package com.bsl;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is a singleton that contains a mapping between the standard java
 * types and an int, in order to use a switch-statement based on the name
 * of a class.
 * <p>
 * Instead of the following snippet:
 * <pre>
 * String classname = anObject.getClass().getName();
 * if (classname.equals("java.lang.String")) {
 *   doThis();
 * } else if (classname.equals("java.lang.Boolean")) {
 *   doThat();
 * ...
 * </pre>
 * <p>..instead we can do this:</p>
 * <pre>
 * int id = ClassNameMap.getClassId(anObject);
 * switch (id) {
 *   case ClassNameMap.JAVA_LANG_STRING:
 *     doThis();
 *     break;
 *   case ClassNameMap.JAVA_LANG_BOOLEAN:
 *     doThat();
 *     break;
 * ...
 * </pre>
 * It's not only easier to read; it's also much faster...
 * @author Håkan Gustavsson
 * @version $Id: 1.0 $
 */
public final class ClassNameMap
{
  //-------------------------------------------------------- The integer values
  public static final int UNKNOWN_TYPE              = -1;
  public static final int JAVA_LANG_BOOLEAN         =  0;
  public static final int JAVA_LANG_BYTE            =  1;
  public static final int JAVA_LANG_CHARACTER       =  2;
  public static final int JAVA_LANG_DOUBLE          =  3;
  public static final int JAVA_LANG_FLOAT           =  4;
  public static final int JAVA_LANG_INTEGER         =  5;
  public static final int JAVA_LANG_LONG            =  6;
  public static final int JAVA_LANG_SHORT           =  7;
  public static final int JAVA_LANG_STRING          =  8;
  public static final int JAVA_LANG_StringBuilder    =  9;

  public static final int PRIMITIVE_BOOLEAN         = 10;
  public static final int PRIMITIVE_BYTE            = 11;
  public static final int PRIMITIVE_CHAR            = 12;
  public static final int PRIMITIVE_DOUBLE          = 13;
  public static final int PRIMITIVE_FLOAT           = 14;
  public static final int PRIMITIVE_INT             = 15;
  public static final int PRIMITIVE_LONG            = 16;
  public static final int PRIMITIVE_SHORT           = 17;

  public static final int JAVA_LANG_NUMBER          = 18;

  /** The map containing the names and id's. */
  private final Map m_mpNames;

  /** The one and only instance of this class. */
  private static final ClassNameMap INSTANCE = new ClassNameMap();

  /** A hidden constructor. */
  private ClassNameMap() {
    // Initialize the map
    m_mpNames = new HashMap(20);

    // Add all types to the map
    m_mpNames.put("java.lang.Boolean",       new Integer(JAVA_LANG_BOOLEAN));
    m_mpNames.put("java.lang.Byte",          new Integer(JAVA_LANG_BYTE));
    m_mpNames.put("java.lang.Character",     new Integer(JAVA_LANG_CHARACTER));
    m_mpNames.put("java.lang.Double",        new Integer(JAVA_LANG_DOUBLE));
    m_mpNames.put("java.lang.Float",         new Integer(JAVA_LANG_FLOAT));
    m_mpNames.put("java.lang.Integer",       new Integer(JAVA_LANG_INTEGER));
    m_mpNames.put("java.lang.Long",          new Integer(JAVA_LANG_LONG));
    m_mpNames.put("java.lang.Number",        new Integer(JAVA_LANG_NUMBER));
    m_mpNames.put("java.lang.Short",         new Integer(JAVA_LANG_SHORT));
    m_mpNames.put("java.lang.String",        new Integer(JAVA_LANG_STRING));
    m_mpNames.put("java.lang.StringBuilder",  new Integer(JAVA_LANG_StringBuilder));

    m_mpNames.put("boolean",                 new Integer(PRIMITIVE_BOOLEAN));
    m_mpNames.put("byte",                    new Integer(PRIMITIVE_BYTE));
    m_mpNames.put("char",                    new Integer(PRIMITIVE_CHAR));
    m_mpNames.put("double",                  new Integer(PRIMITIVE_DOUBLE));
    m_mpNames.put("float",                   new Integer(PRIMITIVE_FLOAT));
    m_mpNames.put("int",                     new Integer(PRIMITIVE_INT));
    m_mpNames.put("long",                    new Integer(PRIMITIVE_LONG));
    m_mpNames.put("short",                   new Integer(PRIMITIVE_SHORT));
  }

  //------------------------------------------------------------ Public methods


  //----------------------------------------------------------- Private methods

  /**
   * Returns the ID of the specified object. If the object type is unknown;
   * <code>-1</code> is returned.
   * <p>
   * <b>Note!</b> Only the standard java and primitive types are known!
   * @param o The object to get the id of.
   * @return The id of the object or <code>-1</code> is returned if the object
   * type is unknown.
   */
  private final int getClassIdImpl(Object o) {
    if (o == null) {
      return UNKNOWN_TYPE;
    }
    return getClassIdWithNameImpl(o.getClass().getName());
  }

  /**
   * Returns the ID of the specified object. If the object type is unknown;
   * <code>-1</code> is returned.
   * <p>
   * <b>Note!</b> Only the standard java and primitive types are known!
   * @param name The name of the object to get the id of.
   * @return The id of the object or <code>-1</code> is returned if the object
   * type is unknown.
   */
  private final int getClassIdWithNameImpl(String name) {
    if (name == null) {
      return UNKNOWN_TYPE;
    }
    Integer id = (Integer)m_mpNames.get(name);
    return id == null ? UNKNOWN_TYPE : id.intValue();
  }
}