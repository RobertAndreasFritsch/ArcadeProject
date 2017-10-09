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
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;
import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.*;

@SuppressWarnings ("deprecation")
public class TangiblePlayerUpTest
{
  private static final Component DUMMY = new Component ()
  {
  };

  @Test
  public void testAbsolutePlayerUp ()
  {
    // TEST RELATIVE UP
    // GENERATE INPUT, UPDATE THE INTERFACE AND TEST
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_S));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_H));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_I));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_4));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_COMMA));
    PlayerControl.takeFrame ();

    assertFalse (PlayerControl.ABS_NORTH.isButtonOne ());
    assertFalse (PlayerControl.ABS_EAST.isButtonOne ());
    assertFalse (PlayerControl.ABS_SOUTH.isButtonOne ());
    assertFalse (PlayerControl.ABS_WEST.isButtonOne ());

    assertTrue (PlayerControl.ABS_NORTH.isUp ());
    assertTrue (PlayerControl.ABS_EAST.isUp ());
    assertTrue (PlayerControl.ABS_SOUTH.isUp ());
    assertTrue (PlayerControl.ABS_WEST.isUp ());

    assertFalse (PlayerControl.ABS_NORTH.isButtonTwo ());
    assertFalse (PlayerControl.ABS_EAST.isButtonTwo ());
    assertFalse (PlayerControl.ABS_SOUTH.isButtonTwo ());
    assertFalse (PlayerControl.ABS_WEST.isButtonTwo ());

    assertFalse (PlayerControl.ABS_NORTH.isLeft ());
    assertFalse (PlayerControl.ABS_EAST.isLeft ());
    assertFalse (PlayerControl.ABS_SOUTH.isLeft ());
    assertFalse (PlayerControl.ABS_WEST.isLeft ());

    assertFalse (PlayerControl.ABS_NORTH.isDown ());
    assertFalse (PlayerControl.ABS_EAST.isDown ());
    assertFalse (PlayerControl.ABS_SOUTH.isDown ());
    assertFalse (PlayerControl.ABS_WEST.isDown ());

    assertFalse (PlayerControl.ABS_NORTH.isRight ());
    assertFalse (PlayerControl.ABS_EAST.isRight ());
    assertFalse (PlayerControl.ABS_SOUTH.isRight ());
    assertFalse (PlayerControl.ABS_WEST.isRight ());

    // FOR KEYBOARD TESTS RESET THE KEYS
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_S));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_H));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_I));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_4));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_COMMA));
    PlayerControl.takeFrame ();
  }

  @Test
  public void testRelativePlayerUp ()
  {
    // TEST RELATIVE UP
    // GENERATE INPUT, UPDATE THE INTERFACE AND TEST
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_W));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_T));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_I));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_8));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_COMMA));
    PlayerControl.takeFrame ();

    assertFalse (PlayerControl.REL_NORTH.isButtonOne ());
    assertFalse (PlayerControl.REL_EAST.isButtonOne ());
    assertFalse (PlayerControl.REL_SOUTH.isButtonOne ());
    assertFalse (PlayerControl.REL_WEST.isButtonOne ());

    assertTrue (PlayerControl.REL_NORTH.isUp ());
    assertTrue (PlayerControl.REL_EAST.isUp ());
    assertTrue (PlayerControl.REL_SOUTH.isUp ());
    assertTrue (PlayerControl.REL_WEST.isUp ());

    assertFalse (PlayerControl.REL_NORTH.isButtonTwo ());
    assertFalse (PlayerControl.REL_EAST.isButtonTwo ());
    assertFalse (PlayerControl.REL_SOUTH.isButtonTwo ());
    assertFalse (PlayerControl.REL_WEST.isButtonTwo ());

    assertFalse (PlayerControl.REL_NORTH.isLeft ());
    assertFalse (PlayerControl.REL_EAST.isLeft ());
    assertFalse (PlayerControl.REL_SOUTH.isLeft ());
    assertFalse (PlayerControl.REL_WEST.isLeft ());

    assertFalse (PlayerControl.REL_NORTH.isDown ());
    assertFalse (PlayerControl.REL_EAST.isDown ());
    assertFalse (PlayerControl.REL_SOUTH.isDown ());
    assertFalse (PlayerControl.REL_WEST.isDown ());

    assertFalse (PlayerControl.REL_NORTH.isRight ());
    assertFalse (PlayerControl.REL_EAST.isRight ());
    assertFalse (PlayerControl.REL_SOUTH.isRight ());
    assertFalse (PlayerControl.REL_WEST.isRight ());

    // FOR KEYBOARD TESTS RESET THE KEYS
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_W));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_T));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_I));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_8));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_COMMA));
    PlayerControl.takeFrame ();
  }
}
