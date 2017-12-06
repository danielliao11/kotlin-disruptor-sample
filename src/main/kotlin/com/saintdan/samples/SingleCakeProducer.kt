package com.saintdan.samples

import com.lmax.disruptor.RingBuffer

/**
 * Single cake baker.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 06/12/2017
 * @since JDK1.8
 */
class SingleCakeProducer : EventProducer {

  override fun startProducing(ringBuffer: RingBuffer<CakeEvent>, count: Int) {
    val producer = { produce(ringBuffer, count) }
    Thread(producer).start()
  }

  private fun produce(ringBuffer: RingBuffer<CakeEvent>, count: Int) {
    for (i in 0 until count) {
      val seq = ringBuffer.next()
      val valueEvent = ringBuffer.get(seq)
      valueEvent.seq = i
      ringBuffer.publish(seq)
    }
  }
}