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

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 *
 */
class GameLoop
{
  private Consumer<Long> consumer;
  private long           millis;
  private Thread         thread;
  private AtomicBoolean  running;

  /**
   *
   */
  public GameLoop (Consumer<Long> consumer, long millis)
  {
    this.consumer = consumer;
    this.millis = millis - 1;

    running = new AtomicBoolean (Boolean.FALSE);
  }

  /**
   *
   */
  private void run ()
  {
    long elapsed;
    long time = System.currentTimeMillis ();

    while (running.get ())
    {
      elapsed = System.currentTimeMillis () - time;

      if (elapsed >= millis)
      {
        consumer.accept (elapsed);
        time += elapsed;
      }
      else
      {
        try
        {
          Thread.sleep (millis - elapsed);
        }
        catch (InterruptedException exception)
        {
          exception.printStackTrace ();
        }
      }
    }
  }

  /**
   *
   */
  public void start ()
  {
    if (!running.getAndSet (Boolean.TRUE))
    {
      thread = new Thread (this::run);
      thread.setName (this.getClass ().getName ());
      thread.start ();
    }
  }

  /**
   *
   */
  public void stop ()
  {
    if (running.getAndSet (Boolean.FALSE))
    {
      try
      {
        thread.join ();
      }
      catch (Exception exception)
      {
        exception.printStackTrace ();
      }
      finally
      {
        thread = null;
      }
    }
  }

  @Override
  public String toString ()
  {
    return "GameLoop{" +
            "consumer=" + consumer +
            ", millis=" + millis +
            ", thread=" + thread +
            ", running=" + running +
            '}';
  }
}

