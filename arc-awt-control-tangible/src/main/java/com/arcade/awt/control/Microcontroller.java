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
import java.util.Arrays;

/**
 * a implementation of {@link IControl} that decodes key events given by a microcontroller
 *
 * @author Robert A. Fritsch
 */
public class Microcontroller extends KeyAdapter implements IAdapter
{
  private final Object    LOCK_KEYS;
  private final Object    LOCK_KEYS_BUFFER;
  private       boolean[] keys;
  private       boolean[] keysBuffer;

  /**
   * initial constructor
   * <p>
   * initials this object using the parameter
   *
   * @param keyBandwidth
   *         the keyBandwidth to check the keystates starting with 0 ending with keyBandwidth
   */
  public Microcontroller (int keyBandwidth)
  {
    this.LOCK_KEYS = new Object ();
    this.LOCK_KEYS_BUFFER = new Object ();

    this.keys = new boolean[keyBandwidth];
    this.keysBuffer = Arrays.copyOf (this.keys, keyBandwidth);
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
    int keyCode = keyEvent.getKeyCode ();

    if (44 == keyCode)
    {
      synchronized (this.LOCK_KEYS)
      {
        synchronized (this.LOCK_KEYS_BUFFER)
        {
          boolean[] swap = this.keys;
          this.keys = this.keysBuffer;
          this.keysBuffer = swap;
          Arrays.fill (this.keysBuffer, false);
        }
      }
    }
    else if ((0 <= keyCode) && (keyCode < this.keysBuffer.length))
    {
      synchronized (this.LOCK_KEYS)
      {
        this.keysBuffer[keyCode] = true;
      }
    }
  }
}
