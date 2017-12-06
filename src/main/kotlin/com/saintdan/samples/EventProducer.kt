package com.saintdan.samples

import com.lmax.disruptor.RingBuffer

/**
 * Event producer.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 06/12/2017
 * @since JDK1.8
 */
interface EventProducer {

  fun startProducing(ringBuffer: RingBuffer<CakeEvent>, count: Int)
}