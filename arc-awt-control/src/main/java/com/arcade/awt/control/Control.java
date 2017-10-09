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

import java.awt.event.KeyListener;

/**
 * a simple implementation of {@link IControl}
 *
 * @author Robert A. Fritsch
 */
public class Control implements IControl
{
  public static final int                 DEFAULT_KEY_BANDWIDTH       = 0x800;
  public static final IAdapterConstructor DEFAULT_ADAPTER_CONSTRUCTOR = Keyboard::new;

  private final Object LOCK_FRAME = new Object ();
  private IAdapter  adapter;
  private boolean[] frame;

  /**
   * default constructor
   * <p>
   * invokes the initial constructor
   */
  public Control ()
  {
    this (Control.DEFAULT_ADAPTER_CONSTRUCTOR, Control.DEFAULT_KEY_BANDWIDTH);
  }

  /**
   * initial constructor
   * <p>
   * initials this object using the parameter
   *
   * @param adapterConstructor
   *         the adapter to construct with
   * @param bandWidth
   *         the bandWidth to check the keystates starting with 0 ending with bandWidth
   */
  public Control (IAdapterConstructor adapterConstructor, int bandWidth)
  {
    this.adapter = adapterConstructor.invoke (bandWidth);
    this.frame = new boolean[bandWidth];
  }

  @Override
  public KeyListener getKeyListener ()
  {
    return this.adapter;
  }

  @Override
  public boolean isPressed (int keyCode)
  {
    synchronized (this.LOCK_FRAME)
    {
      if ((0 <= keyCode) && (keyCode < this.frame.length))
      {
        return this.frame[keyCode];
      }
    }
    return false;
  }

  @Override
  public void takeFrame ()
  {
    synchronized (this.LOCK_FRAME)
    {
      this.adapter.takeFrame (this.frame);
    }
  }
}
