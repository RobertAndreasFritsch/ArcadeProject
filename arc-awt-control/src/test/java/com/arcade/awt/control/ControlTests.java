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

import org.junit.Test;

import java.awt.*;
import java.awt.event.*;

import static org.junit.Assert.*;

/**
 * unit tests for the com.arcade.awt package in the control component
 *
 * @author Robert A. Fritsch
 */
@SuppressWarnings ("deprecation")
public class ControlTests
{
  private static final Component DUMMY = new Component ()
  {
  };

  /**
   * tests if the Control-constructor is working as expected with the
   * parameter Mode.KEYBOARD:Mode
   */
  @Test
  public void defaultConstructorTest ()
  {
    Control CONTROL = new Control ();

    assertTrue (CONTROL.getKeyListener () != null);
    assertTrue (CONTROL.getKeyListener () instanceof Keyboard);
  }

  /**
   * tests if the Keyboard and the Control are working as
   * expected
   */
  @Test
  public void feedbackTest ()
  {
    Control  control     = new Control ();
    Keyboard keyboard    = (Keyboard) control.getKeyListener ();
    KeyEvent dummyEvent = new KeyEvent (DUMMY, 0, 3l, 0, 48, '0', 0);

    keyboard.keyPressed (dummyEvent);
    control.takeFrame ();
    assertTrue (control.isPressed (48));

    keyboard.keyReleased (dummyEvent);
    keyboard.keyPressed (dummyEvent);
    control.takeFrame ();
    assertTrue (control.isPressed (48));

    keyboard.keyReleased (dummyEvent);
    control.takeFrame ();
    assertFalse (control.isPressed (48));

    keyboard.keyPressed (dummyEvent);
    keyboard.keyReleased (dummyEvent);
    control.takeFrame ();
    assertFalse (control.isPressed (48));
  }

  /**
   * tests if the Control-constructor is working as expected with the
   * parameters Mode.KEYBOARD:Mode and 0xf:int
   */
  @Test
  public void initializationConstructorTest ()
  {
    Control control = new Control (Keyboard::new, 0);
    assertTrue (control.getKeyListener () != null);
  }
}
