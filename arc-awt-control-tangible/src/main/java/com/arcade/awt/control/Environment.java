/**************************************************************************************************
 * Copyright (c) 2017 Robert Andreas Fritsch                                                      *
 *                                                                                                *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR                     *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,                       *
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE                    *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER                         *
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,                  *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE                  *
 * SOFTWARE.                                                                                      *
 **************************************************************************************************/
package com.arcade.awt.control;

import java.io.*;
import java.util.*;

/**
 * represents the resources of this Module
 *
 * @author Robert A. Fritsch
 */
class Environment extends Properties
{
  private static Environment instance = new Environment ();

  private Environment ()
  {
    try
    {
      InputStream inputStream = getClass ().getResourceAsStream ("/com/arcade/awt/control/environment.properties");
      load (inputStream);
      inputStream.close ();
    }
    catch (IOException exception)
    {
      exception.printStackTrace ();
    }
  }

  /**
   * return the properties of this {@link Environment}
   */
  public static Environment getInstance ()
  {
    return instance;
  }

  public int getInt (String key, int defaultValue)
  {
    try
    {
      String result = getProperty (key);
      return result == null ? defaultValue : Integer.parseInt (result);
    }
    catch (Exception exception)
    {
      exception.printStackTrace ();
      return defaultValue;
    }
  }
}
