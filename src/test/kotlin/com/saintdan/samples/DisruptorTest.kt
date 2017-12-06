package com.saintdan.samples

import com.lmax.disruptor.RingBuffer
import com.lmax.disruptor.YieldingWaitStrategy
import com.lmax.disruptor.dsl.Disruptor
import com.lmax.disruptor.dsl.ProducerType
import com.lmax.disruptor.util.DaemonThreadFactory
import org.junit.Test


/**
 * Test case.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 06/12/2017
 * @since JDK1.8
 */
class DisruptorTest {

  private val threadFactory = DaemonThreadFactory.INSTANCE
  private val cakeLog = CakeLog()

  private var disruptor: Disruptor<CakeEvent> = Disruptor<CakeEvent>(CakeEvent().eventFactory,
      16,
      threadFactory,
      ProducerType.SINGLE,
      YieldingWaitStrategy())

  private fun createDisruptor(producerType: ProducerType, eventConsumer: EventConsumer) {
    disruptor = Disruptor<CakeEvent>(CakeEvent().eventFactory,
        16,
        threadFactory,
        producerType,
        YieldingWaitStrategy())
    disruptor.handleEventsWith(eventConsumer.getEventHandler().get(0))
  }

  private fun startProducing(ringBuffer: RingBuffer<CakeEvent>, count: Int, eventProducer: EventProducer) {
    eventProducer.startProducing(ringBuffer, count)
  }

  @Test
  fun whenMultipleProducerSingleConsumer() {
    val eventConsumer = SingleCakeTestConsumer(cakeLog)
    val eventProducer = DelayedMultiCakeProducer()
    createDisruptor(ProducerType.MULTI, eventConsumer)
    val ringBuffer = disruptor.start()

    startProducing(ringBuffer, 32, eventProducer)

    disruptor.halt()
    disruptor.shutdown()
  }

  @Test
  fun whenSingleProducerSingleConsumer() {
    val eventConsumer = SingleCakeTestConsumer(cakeLog)
    val eventProducer = SingleCakeProducer()
    createDisruptor(ProducerType.SINGLE, eventConsumer)
    val ringBuffer = disruptor.start()

    startProducing(ringBuffer, 32, eventProducer)

    disruptor.halt()
    disruptor.shutdown()
  }

  @Test
  fun whenSingleProducerMultipleConsumer() {
    val eventConsumer = MultiCakeTestConsumer(cakeLog)
    val eventProducer = SingleCakeProducer()
    createDisruptor(ProducerType.SINGLE, eventConsumer)
    val ringBuffer = disruptor.start()

    startProducing(ringBuffer, 32, eventProducer)

    disruptor.halt()
    disruptor.shutdown()
  }

  @Test
  fun whenMultipleProducerMultipleConsumer() {
    val eventConsumer = MultiCakeTestConsumer(cakeLog)
    val eventProducer = DelayedMultiCakeProducer()
    createDisruptor(ProducerType.MULTI, eventConsumer)
    val ringBuffer = disruptor.start()

    startProducing(ringBuffer, 32, eventProducer)

    disruptor.halt()
    disruptor.shutdown()
  }
}