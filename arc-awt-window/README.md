Window
======

_Window Module_
_By Robert Andreas Fritsch_

## Usage

``` java
import java.awt.*;
import com.arcade.awt.window;

public class TestWindow
{
  public static void main (String[] args)
  {
    // create a window instance
    Window window = new Window ();

    // do until you are done
    boolean done = false;
    while (!done)
    {
      // get the graphics content
      // every time you invoke render, you also
      // have to get the new graphics instance
      Graphics graphics = window.getGraphics ();

      // draw some stuff into the graphics
      graphics.drawLine (100, 100, 400, 400);

      // print the graphics onto the screen
      window.render ();
    }
  }
}
```
