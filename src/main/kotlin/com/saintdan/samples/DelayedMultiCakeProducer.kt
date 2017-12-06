package com.saintdan.samples

import com.lmax.disruptor.RingBuffer

/**
 * Multi bakers.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 06/12/2017
 * @since JDK1.8
 */
class DelayedMultiCakeProducer : EventProducer {

  override fun startProducing(ringBuffer: RingBuffer<CakeEvent>, count: Int) {
    val simpleProducer = { produce(ringBuffer, count, false) }
    val delayProducer = { produce(ringBuffer, count, true) }
    Thread(simpleProducer).start()
    Thread(delayProducer).start()
  }

  private fun produce(ringBuffer: RingBuffer<CakeEvent>, count: Int, addDelay: Boolean) {
    for (i in 0 until count) {
      val seq = ringBuffer.next()
      val valueEvent = ringBuffer.get(seq)
      valueEvent.seq = i
      ringBuffer.publish(seq)
      if (addDelay) {
        addDelay()
      }
    }
  }

  private fun addDelay() {
    try {
      Thread.sleep(1000)
    } catch (interruptedException: InterruptedException) {
      // No-Op lets swallow it
    }

  }
}