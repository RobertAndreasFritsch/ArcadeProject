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
public class TangiblePlayerRightTest
{
  private static final Component DUMMY = new Component ()
  {
  };
  @Test
  public void testAbsolutePlayerRight ()
  {
    // GENERATE INPUT, UPDATE THE INTERFACE AND TEST
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_A));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_G));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_L));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_8));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_COMMA));
    PlayerControl.takeFrame ();

    assertFalse (PlayerControl.ABS_NORTH.isButtonOne ());
    assertFalse (PlayerControl.ABS_EAST.isButtonOne ());
    assertFalse (PlayerControl.ABS_SOUTH.isButtonOne ());
    assertFalse (PlayerControl.ABS_WEST.isButtonOne ());

    assertFalse (PlayerControl.ABS_NORTH.isUp ());
    assertFalse (PlayerControl.ABS_EAST.isUp ());
    assertFalse (PlayerControl.ABS_SOUTH.isUp ());
    assertFalse (PlayerControl.ABS_WEST.isUp ());

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

    assertTrue (PlayerControl.ABS_NORTH.isRight ());
    assertTrue (PlayerControl.ABS_EAST.isRight ());
    assertTrue (PlayerControl.ABS_SOUTH.isRight ());
    assertTrue (PlayerControl.ABS_WEST.isRight ());

    // FOR KEYBOARD TESTS RESET THE KEYS
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_A));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_G));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_L));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_8));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_COMMA));
    PlayerControl.takeFrame ();
  }

  @Test
  public void testRelativePlayerRight ()
  {
    // GENERATE INPUT, UPDATE THE INTERFACE AND TEST
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_D));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_H));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_L));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_6));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_COMMA));
    PlayerControl.takeFrame ();

    assertFalse (PlayerControl.REL_NORTH.isButtonOne ());
    assertFalse (PlayerControl.REL_EAST.isButtonOne ());
    assertFalse (PlayerControl.REL_SOUTH.isButtonOne ());
    assertFalse (PlayerControl.REL_WEST.isButtonOne ());

    assertFalse (PlayerControl.REL_NORTH.isUp ());
    assertFalse (PlayerControl.REL_EAST.isUp ());
    assertFalse (PlayerControl.REL_SOUTH.isUp ());
    assertFalse (PlayerControl.REL_WEST.isUp ());

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

    assertTrue (PlayerControl.REL_NORTH.isRight ());
    assertTrue (PlayerControl.REL_EAST.isRight ());
    assertTrue (PlayerControl.REL_SOUTH.isRight ());
    assertTrue (PlayerControl.REL_WEST.isRight ());

    // FOR KEYBOARD TESTS RESET THE KEYS
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_D));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_H));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_L));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_6));
    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_COMMA));
    PlayerControl.takeFrame ();
  }
}
