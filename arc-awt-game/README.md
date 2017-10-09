Game
====

_Game Module_
_By Robert Andreas Fritsch_

## CONFIGURATION

_COMING_SOON_

## USAGE


We will now begin by creating a game instance with a loading screen.
Its always good to start with a loading screen to tell the players,
that the game is not broken while we are loading our contents.

To create a Game instance we simply use the Game Constructor:
``` java
Game game = new Game ();
```

Not that hard, right?
No we can begin with a loading screen ... 

Everything in our game has to be a so called GameObject.
So if we create a GameObject we have to use the keyword "implements"
and the Interface "GameObject":

``` java
public class TestLoading implements GameObject
{
}
```

As we create a GameObject,
we also have to implements its update method:

``` java
public class TestLoading implements GameObject
{
   @Override
   public void update (final long elapsed, final Graphics2D graphics)
   {
   }
}
```

The update-Method is called every few milliseconds to generate
around 60FPS for your Game.
This is not always that precise, but we can use the elapsed parameter
of the update-Method to correct our speed if we want.

We now fill this GameObject with a simple animation:

``` java
import java.awt.*;

public class TestLoading implements GameObject
{
  private Game game;
  private double i = 0;

  public TestLoading (Game game)
  {
    this.game = game;
  }

  @Override
  public void update (final long elapsed, final Graphics2D graphics)
  {
    i += 0.01 * elapsed;

    int hwidth  = game.getWindow ().getFrame ().getWidth () >> 1;
    int hheight = game.getWindow ().getFrame ().getHeight () >> 1;

    graphics.drawLine (hwidth, hheight, hwidth + (int) (360 * Math.sin (i)), hheight + (int) (360 * Math.cos (i)));
  }
}
```

After this is done we will now display the screen loader to the window
by adding it to our game object. In this example we are initialize the game object
using the tangible module to simplify key presses.
If you dont do so please read the README.md related to the Controller Topic,
to implement your own Controller.

``` java
package com.arcade.awt.game;

import com.arcade.awt.control.PlayerControl;

import java.util.*;

public class TestGame extends Game
{
  public static void main (String[] args)
  {
    Game game = new Game (PlayerControl.getControl());

    // generate a lightweight loading screen
    // and add this to the game
    game.getGameObjects ().add (new TestLoading (game));
  }
}
```

While our Screen loader is active we can load our content.
Therefore we need come content.
So we create a simple movable object.

``` java
import com.arcade.awt.control.PlayerControl;

import java.awt.*;

public class TestDot implements GameObject
{
  private Game          game;
  private PlayerControl player;

  private double x;
  private double y;
  private double speed;

  public TestDot (Game game, PlayerControl player)
  {
    this.game = game;
    this.player = player;
    this.speed = 0.5;

    x = game.getWindow ().getFrame ().getWidth () >> 1;
    y = game.getWindow ().getFrame ().getHeight () >> 1;
  }

  @Override
  public void update (final long elapsed, final Graphics2D graphics)
  {
    /// update the player
    if (player.isUp ())
    {
      x -= speed * elapsed;
    }
    if (player.isRight ())
    {
      y += speed * elapsed;
    }
    if (player.isDown ())
    {
      x += speed * elapsed;
    }
    if (player.isLeft ())
    {
      y -= speed * elapsed;
    }

    /// Draw the Player
    graphics.fillOval ((int) x - 10, (int) y - 10, 20, 20);
  }
}
```

(You may have noticed that we have this PlayerControl parameter.
If you want to know how this work, look into the awt-control Module
of the Arcade Project.)

In our last step we add our testObject to the game.
this time we use the setCollection-Method to overwrite the loading screen.

``` java
import com.arcade.awt.control.PlayerControl;

import java.util.*;

public class TestGame extends Game
{
  public static void main (String[] args)
  {
    Game game = new Game ();

    // generate a lightweight loading screen
    // and add this to the game
    game.getGameObjects ().add (new TestLoading (game));

    // now load all game resources and GameObjects
    // note: do not add them to the game until all resources are loaded
    Collection<GameObject> gameObjects = new ArrayList<> ();
    gameObjects.add (new TestDot (game, PlayerControl.ABS_NORTH));
    gameObjects.add (new TestDot (game, PlayerControl.ABS_EAST));
    gameObjects.add (new TestDot (game, PlayerControl.ABS_SOUTH));
    gameObjects.add (new TestDot (game, PlayerControl.ABS_WEST));

    // simulate resource loading time
    try
    {
      Thread.sleep (3000);
    }
    catch (InterruptedException exception)
    {
      exception.printStackTrace ();
    }

    // after all resources are loaded we remove the loading screen and add the gameObjects
    game.setGameObjects (gameObjects);
  }
}
```
