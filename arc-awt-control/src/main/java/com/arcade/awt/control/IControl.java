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
 * User interface for accessing key states using the {@link java.awt}-Framework
 */
public interface IControl
{

  /**
   * @return a {@link java.awt.event.KeyListener} that connects {@link Control} to a {@link java.awt.Container}
   */
  KeyListener getKeyListener ();

  /**
   * @param keyCode
   *         the code to check the state
   *
   * @return the state (pressed = TRUE, not pressed = FALSE) of a key
   */
  boolean isPressed (int keyCode);

  /**
   * updates the key states
   */
  void takeFrame ();
}
