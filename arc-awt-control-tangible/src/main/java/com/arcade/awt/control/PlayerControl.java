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

import java.awt.*;
import java.awt.event.KeyListener;

import static java.awt.Color.*;
import static java.awt.event.KeyEvent.*;

/**
 * represents all player seats in a relative and an absolute view
 * <p>
 * the relative view describes the player's joystick from the viewpoint of the player
 * and the absolute view describes the player's joystick from an on top view of the table.
 *
 * @author Robert A. Fritsch
 */
public enum PlayerControl
{
  REL_NORTH (RED, 1, "REL_NORTH"),
  REL_EAST (BLUE, 2, "REL_EAST"),
  REL_SOUTH (YELLOW, 4, "REL_SOUTH"),
  REL_WEST (GREEN, 8, "REL_WEST"),

  ABS_NORTH (RED, 16, "ABS_NORTH"),
  ABS_EAST (BLUE, 32, "ABS_EAST"),
  ABS_SOUTH (YELLOW, 64, "ABS_SOUTH"),
  ABS_WEST (GREEN, 128, "ABS_WEST");

  private static Control control;

  private static int codeButtonStart;
  private static int codeButtonExit;

  static
  {
    switch (Environment.getInstance ().getInt ("mode", 1))
    {
      case 0:
        PlayerControl.control = new Control (Keyboard::new, 91);
        break;
      case 1:
        PlayerControl.control = new Control (Microcontroller::new, 91);
        break;
      default:
        PlayerControl.control = new Control ();
        break;
    }

    PlayerControl.codeButtonStart = VK_N;
    PlayerControl.codeButtonExit = VK_M;

    PlayerControl.REL_NORTH.playerOpposite = PlayerControl.REL_SOUTH;
    PlayerControl.REL_NORTH.playerRight = PlayerControl.REL_WEST;
    PlayerControl.REL_NORTH.playerLeft = PlayerControl.REL_EAST;

    PlayerControl.REL_EAST.playerOpposite = PlayerControl.REL_WEST;
    PlayerControl.REL_EAST.playerRight = PlayerControl.REL_NORTH;
    PlayerControl.REL_EAST.playerLeft = PlayerControl.REL_SOUTH;

    PlayerControl.REL_SOUTH.playerOpposite = PlayerControl.REL_NORTH;
    PlayerControl.REL_SOUTH.playerRight = PlayerControl.REL_WEST;
    PlayerControl.REL_SOUTH.playerLeft = PlayerControl.REL_WEST;

    PlayerControl.REL_WEST.playerOpposite = PlayerControl.REL_EAST;
    PlayerControl.REL_WEST.playerRight = PlayerControl.REL_SOUTH;
    PlayerControl.REL_WEST.playerLeft = PlayerControl.REL_NORTH;

    PlayerControl.ABS_NORTH.playerOpposite = PlayerControl.ABS_SOUTH;
    PlayerControl.ABS_NORTH.playerRight = PlayerControl.ABS_WEST;
    PlayerControl.ABS_NORTH.playerLeft = PlayerControl.ABS_EAST;

    PlayerControl.ABS_EAST.playerOpposite = PlayerControl.ABS_WEST;
    PlayerControl.ABS_EAST.playerRight = PlayerControl.ABS_NORTH;
    PlayerControl.ABS_EAST.playerLeft = PlayerControl.ABS_SOUTH;

    PlayerControl.ABS_SOUTH.playerOpposite = PlayerControl.ABS_NORTH;
    PlayerControl.ABS_SOUTH.playerRight = PlayerControl.ABS_WEST;
    PlayerControl.ABS_SOUTH.playerLeft = PlayerControl.ABS_WEST;

    PlayerControl.ABS_WEST.playerOpposite = PlayerControl.ABS_EAST;
    PlayerControl.ABS_WEST.playerRight = PlayerControl.ABS_SOUTH;
    PlayerControl.ABS_WEST.playerLeft = PlayerControl.ABS_NORTH;
  }

  private Integer codeUp;
  private Integer codeDown;
  private Integer codeLeft;
  private Integer codeRight;
  private Integer codeButtonOne;
  private Integer codeButtonTwo;
  private Color   color;
  private int     bitmap;

  private PlayerControl playerOpposite;
  private PlayerControl playerRight;
  private PlayerControl playerLeft;

  PlayerControl (Color color, int bitmap, String propertiesPrefix)
  {
    codeUp = Environment.getInstance ().getInt (propertiesPrefix + ".up", -1);
    codeDown = Environment.getInstance ().getInt (propertiesPrefix + ".down", -1);
    codeLeft = Environment.getInstance ().getInt (propertiesPrefix + ".left", -1);
    codeRight = Environment.getInstance ().getInt (propertiesPrefix + ".right", -1);
    codeButtonOne = Environment.getInstance ().getInt (propertiesPrefix + ".buttonOne", -1);
    codeButtonTwo = Environment.getInstance ().getInt (propertiesPrefix + ".buttonTwo", -1);

    this.color = color;
    this.bitmap = bitmap;
  }

  /**
   * @return the control
   */
  public static Control getControl ()
  {
    return control;
  }

  /**
   * delegates {@link Control#takeFrame()}
   */
  public static void takeFrame ()
  {
    PlayerControl.control.takeFrame ();
  }

  /**
   * delegates {@link Control#getKeyListener()}
   *
   * @return {@link Control#getKeyListener()}
   */
  public static KeyListener getKeyListener ()
  {
    return PlayerControl.control.getKeyListener ();
  }

  /**
   * delegates {@link Control#isPressed(int)} with the static key code to start something
   *
   * @return {@link Control#isPressed(int)}
   */
  public static boolean isStart ()
  {
    return PlayerControl.control.isPressed (PlayerControl.codeButtonStart);
  }

  /**
   * delegates {@link Control#isPressed(int)} with the static key code to exit something
   *
   * @return {@link Control#isPressed(int)}
   */
  public static boolean isExit ()
  {
    return PlayerControl.control.isPressed (PlayerControl.codeButtonExit);
  }

  /**
   * @return the {@link java.awt.Color} associated with the {@link PlayerControl}
   */
  public Color getColor ()
  {
    return this.color;
  }

  /**
   * @return a player identifier bit
   */
  public int getBitmap ()
  {
    return this.bitmap;
  }

  /**
   * delegates {@link Control#isPressed(int)} with the player's specific key code
   *
   * @return {@link Control#isPressed(int)}
   */
  public boolean isUp ()
  {
    return PlayerControl.control.isPressed (this.codeUp);
  }

  /**
   * delegates {@link Control#isPressed(int)} with the player's specific key code
   *
   * @return {@link Control#isPressed(int)}
   */
  public boolean isRight ()
  {
    return PlayerControl.control.isPressed (this.codeRight);
  }

  /**
   * delegates {@link Control#isPressed(int)} with the player's specific key code
   *
   * @return {@link Control#isPressed(int)}
   */
  public boolean isDown ()
  {
    return PlayerControl.control.isPressed (this.codeDown);
  }

  /**
   * delegates {@link Control#isPressed(int)} with the player's specific key code
   *
   * @return {@link Control#isPressed(int)}
   */
  public boolean isLeft ()
  {
    return PlayerControl.control.isPressed (this.codeLeft);
  }

  /**
   * delegates {@link Control#isPressed(int)} with the player's specific key code
   *
   * @return {@link Control#isPressed(int)}
   */
  public boolean isButtonOne ()
  {
    return PlayerControl.control.isPressed (this.codeButtonOne);
  }

  /**
   * delegates {@link Control#isPressed(int)} with the player's specific key code
   *
   * @return {@link Control#isPressed(int)}
   */
  public boolean isButtonTwo ()
  {
    return PlayerControl.control.isPressed (this.codeButtonTwo);
  }

  /**
   * @return the {@link PlayerControl} on the opposite of the table
   */
  public PlayerControl getPlayerOpposite ()
  {
    return this.playerOpposite;
  }

  /**
   * @return the {@link PlayerControl} on the right side
   */
  public PlayerControl getPlayerRight ()
  {
    return this.playerRight;
  }

  /**
   * @return the {@link PlayerControl} on the left side
   */
  public PlayerControl getPlayerLeft ()
  {
    return this.playerLeft;
  }

  @Override
  public String toString ()
  {
    return "PlayerControl{" +
            "codeUp=" + codeUp +
            ", codeDown=" + codeDown +
            ", codeLeft=" + codeLeft +
            ", codeRight=" + codeRight +
            ", codeButtonOne=" + codeButtonOne +
            ", codeButtonTwo=" + codeButtonTwo +
            ", color=" + color +
            ", bitmap=" + bitmap +
            ", playerOpposite.bitmap=" + playerOpposite.getBitmap () +
            ", playerRight.bitmap=" + playerOpposite.getBitmap () +
            ", playerLeft.bitmap=" + playerOpposite.getBitmap () +
            '}';
  }
}
