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
public class TangiblePlayerStaticTest
{
  private static final Component DUMMY = new Component ()
  {
  };
  @Test
  public void testStaticButtonStartRequests ()
  {
    // GENERATE INPUT, UPDATE THE INTERFACE AND TEST
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_N));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_COMMA));
    PlayerControl.takeFrame ();

    assertTrue (PlayerControl.isStart ());

    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_N));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_COMMA));
    PlayerControl.takeFrame ();

    assertFalse (PlayerControl.isStart ());
  }

  @Test
  public void testStaticButtonExitRequests ()
  {
    // GENERATE INPUT, UPDATE THE INTERFACE AND TEST
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_M));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_COMMA));
    PlayerControl.takeFrame ();

    assertTrue (PlayerControl.isExit ());

    PlayerControl.getKeyListener ().keyReleased (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_M));
    PlayerControl.getKeyListener ().keyPressed (new KeyEvent (DUMMY, 0, currentTimeMillis (), 0, VK_COMMA));
    PlayerControl.takeFrame ();

    assertFalse (PlayerControl.isExit ());
  }
}
