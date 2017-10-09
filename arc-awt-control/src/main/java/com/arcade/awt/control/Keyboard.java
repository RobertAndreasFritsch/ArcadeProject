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

import java.awt.event.*;

/**
 * a simple implementation of {@link IAdapter} using keyboard states
 *
 * @author Robert A. Fritsch
 */
public class Keyboard extends KeyAdapter implements IAdapter
{
  private final Object    LOCK_KEYS;
  private final boolean[] keys;

  /**
   * initial constructor
   * <p>
   * initials this object using the parameter
   *
   * @param keyBandwidth
   *         the keyBandwidth to check the key states starting with 0 ending with keyBandwidth
   */
  public Keyboard (int keyBandwidth)
  {
    this.LOCK_KEYS = new Object ();
    this.keys = new boolean[keyBandwidth];
  }

  @Override
  public void takeFrame (boolean[] frame)
  {
    synchronized (this.LOCK_KEYS)
    {
      System.arraycopy (this.keys, 0, frame, 0, Math.min (this.keys.length, frame.length));
    }
  }

  @Override
  public void keyPressed (KeyEvent keyEvent)
  {
    synchronized (this.LOCK_KEYS)
    {
      if ((keyEvent.getKeyCode () >= 0) && (keyEvent.getKeyCode () < this.keys.length))
      {
        this.keys[keyEvent.getKeyCode ()] = true;
      }
    }
  }

  @Override
  public void keyReleased (KeyEvent keyEvent)
  {
    synchronized (this.LOCK_KEYS)
    {
      if ((keyEvent.getKeyCode () >= 0) && (keyEvent.getKeyCode () < this.keys.length))
      {
        this.keys[keyEvent.getKeyCode ()] = false;
      }
    }
  }
}
