package com.saintdan.samples

/**
 * Cake consume log.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 06/12/2017
 * @since JDK1.8
 */
class CakeLog {

  fun log(cake: CakeEvent, sequence: Long) {
    println("Baker No.[" + cake.seq + "] made [" + cake.name + "], and consumer No. [" + sequence + "] ate it.")
  }
}