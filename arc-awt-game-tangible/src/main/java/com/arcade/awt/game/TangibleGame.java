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

package com.arcade.awt.game;

import com.arcade.awt.control.PlayerControl;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TangibleGame extends Game
{
  public TangibleGame ()
  {
    super (PlayerControl.getControl ());

    int w = window.getDisplayMode ().getWidth ();
    int h = window.getDisplayMode ().getHeight ();

    if (w > h)
    {
      window.getPanel ().setBounds (w - h >> 1, 0, h, h);
    }
    else
    {
      window.getPanel ().setBounds (0, h - w >> 1, w, w);
    }

  }
}
