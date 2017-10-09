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

import com.arcade.awt.control.Control;
import com.arcade.awt.window.Window;

import java.awt.*;
import java.util.*;

import static java.awt.RenderingHints.*;

/**
 *
 */
public class Game
{
  protected Window                 window;
  protected Collection<GameObject> gameObjects;
  protected Control                control;

  /**
   *
   */
  public Game (Control control)
  {
    gameObjects = new ArrayList<> ();
    this.control = control;

    window = new Window ();
    window.getFrame ().addKeyListener (control.getKeyListener ());

    long mspf = (long) (1000 / (window.getDisplayMode ().getRefreshRate ()));
    new GameLoop (this::update, mspf).start ();
  }

  /**
   *
   */
  public Collection<GameObject> getAndSetGameObjects (Collection<GameObject> gameObjects)
  {
    Collection<GameObject> tmp = this.gameObjects;
    this.gameObjects = gameObjects;
    return tmp;
  }

  /**
   *
   */
  public Collection<GameObject> getGameObjects ()
  {
    return gameObjects;
  }

  /**
   *
   */
  public void setGameObjects (Collection<GameObject> gameObjects)
  {
    this.gameObjects = gameObjects;
  }

  /**
   *
   */
  public Window getWindow ()
  {
    return window;
  }

  /**
   *
   */
  protected void update (long elapsed)
  {
    try
    {
      Graphics2D graphics = (Graphics2D) window.getCanvas ().getBufferStrategy ().getDrawGraphics ();
      graphics.clearRect (0, 0, window.getFrame ().getWidth (), window.getFrame ().getHeight ());

      graphics.setRenderingHint (KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);
      graphics.setRenderingHint (KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

      Collection<GameObject> tmp = new ArrayList<> (gameObjects);
      tmp.forEach (g -> g.update (elapsed, graphics));

      this.control.takeFrame ();
      window.render ();
    }
    catch (Exception exception)
    {
      exception.printStackTrace ();
    }
  }

  @Override
  public String toString ()
  {
    return "Game{" +
            "window=" + window +
            ", gameObjects=" + gameObjects +
            ", control=" + control +
            '}';
  }
}
