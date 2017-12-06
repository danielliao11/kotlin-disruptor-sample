package com.saintdan.samples

import com.lmax.disruptor.EventHandler
import org.junit.Assert.assertEquals

/**
 *
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 06/12/2017
 * @since JDK1.8
 */
class MultiCakeTestConsumer(private val cakeLog: CakeLog) : EventConsumer {

  override fun getEventHandler(): Array<EventHandler<CakeEvent>> {
    val eventHandler = EventHandler({ event: CakeEvent, sequence: Long, endOfBatch: Boolean -> cakeLog.log(event, sequence) })
    val anotherEventHandler = EventHandler({ event: CakeEvent, sequence: Long, endOfBatch: Boolean -> cakeLog.log(event, sequence) })
    return arrayOf(eventHandler, anotherEventHandler)
  }

  private var expectedValue = -1
  private var otherExpectedValue = -1

  private fun assertExpectedValue(id: Int) {
    assertEquals(++expectedValue, id)
  }

  private fun assertOtherExpectedValue(id: Int) {
    assertEquals(++otherExpectedValue, id)
  }
}