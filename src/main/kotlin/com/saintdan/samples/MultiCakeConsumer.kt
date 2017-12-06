package com.saintdan.samples

import com.lmax.disruptor.EventHandler

/**
 * Multi cake consumers.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 06/12/2017
 * @since JDK1.8
 */
class MultiCakeConsumer(private val cakeLog: CakeLog) : EventConsumer {

  override fun getEventHandler(): Array<EventHandler<CakeEvent>> {
    val eventHandler = EventHandler({ event: CakeEvent, sequence: Long, endOfBatch: Boolean -> cakeLog.log(event, sequence) })
    val anotherEventHandler = EventHandler({ event: CakeEvent, sequence: Long, endOfBatch: Boolean -> cakeLog.log(event, sequence) })
    return arrayOf(eventHandler, anotherEventHandler)
  }
}