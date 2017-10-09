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

package com.arcade.awt.window;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class Window
{
  protected GraphicsEnvironment graphicsEnvironment;
  protected GraphicsDevice      graphicsDevice;
  protected DisplayMode         displayMode;

  protected JFrame frame;
  protected Canvas canvas;
  protected JPanel panel;

  public Window ()
  {
    frame = new JFrame ();
    canvas = new Canvas ();
    panel = new JPanel ();

    graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment ();
    graphicsDevice = graphicsEnvironment.getDefaultScreenDevice ();
    displayMode = graphicsDevice.getDisplayMode ();

    Rectangle fullScreen = new Rectangle (0, 0, displayMode.getWidth (), displayMode.getHeight ());

    frame.setBackground (Color.BLACK);
    frame.setLocationRelativeTo (null);

    if (Toolkit.getDefaultToolkit ().isFrameStateSupported (JFrame.MAXIMIZED_BOTH)){
      System.out.println ("FrameState:MAXIMIZED_BOTH supported");
      frame.setExtendedState (JFrame.MAXIMIZED_BOTH);
    }
    else {
      System.out.println ("FrameState:MAXIMIZED_BOTH not supported");
      frame.setBounds (fullScreen);
    }

    frame.setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);
    frame.setUndecorated (true);
    frame.setAlwaysOnTop (true);

    panel.setLayout (new BorderLayout());
    panel.setBounds (frame.getBounds ());
    panel.setVisible (true);

    canvas.setBounds (panel.getBounds ());
    canvas.setIgnoreRepaint (true);
    canvas.setVisible (true);

    frame.setContentPane (panel);
    panel.add (canvas, BorderLayout.CENTER);

    frame.pack ();
    frame.setResizable (false);


    canvas.createBufferStrategy (2);
    // REMOVED BECAUSE OF BUGS ON CERTAIN DESKTOP ENVIRONMENTS...
    /// if (graphicsDevice.isFullScreenSupported ())
    /// {
    ///   System.out.println ("FullScreenWindow supported");
    ///   graphicsDevice.setFullScreenWindow (frame);
    /// }

    frame.setVisible (true);
  }

  public void render ()
  {
    if (!canvas.getBufferStrategy ().contentsLost ())
    {
      canvas.getBufferStrategy ().getDrawGraphics ().dispose ();
      canvas.getBufferStrategy ().show ();
      Toolkit.getDefaultToolkit ().sync ();
    }
  }

  public JFrame getFrame ()
  {
    return frame;
  }

  public Canvas getCanvas ()
  {
    return canvas;
  }

  public JPanel getPanel ()
  {
    return panel;
  }

  public GraphicsEnvironment getGraphicsEnvironment ()
  {
    return graphicsEnvironment;
  }

  public GraphicsDevice getGraphicsDevice ()
  {
    return graphicsDevice;
  }

  public DisplayMode getDisplayMode ()
  {
    return displayMode;
  }

  @Override
  public String toString ()
  {
    return "Window{" +
            "graphicsEnvironment=" + graphicsEnvironment +
            ", graphicsDevice=" + graphicsDevice +
            ", displayMode=" + displayMode +
            ", frame=" + frame +
            ", canvas=" + canvas +
            ", panel=" + panel +
            '}';
  }
}
