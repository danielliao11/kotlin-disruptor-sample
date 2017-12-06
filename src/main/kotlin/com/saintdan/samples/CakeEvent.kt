package com.saintdan.samples

import com.lmax.disruptor.EventFactory
import java.util.concurrent.ThreadLocalRandom

/**
 * Cake event
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 06/12/2017
 * @since JDK1.8
 */
class CakeEvent(
    var name: String = "Cheese cake",
    var seq: Int = 0
) {
  val eventFactory: EventFactory<CakeEvent> = EventFactory { CakeEvent(
      name = CakeArray().cakes[ThreadLocalRandom.current().nextInt(0, 16)]
  ) }
}