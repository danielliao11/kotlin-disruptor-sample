package com.saintdan.samples

import com.lmax.disruptor.EventHandler

/**
 * Event consumer.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 06/12/2017
 * @since JDK1.8
 */
interface EventConsumer {

  fun getEventHandler(): Array<EventHandler<CakeEvent>>
}