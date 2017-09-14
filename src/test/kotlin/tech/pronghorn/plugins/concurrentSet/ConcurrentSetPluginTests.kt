/*
 * Copyright 2017 Pronghorn Technology LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.pronghorn.plugins.concurrentSet

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.RepeatedTest
import tech.pronghorn.test.PronghornTest
import tech.pronghorn.test.repeatCount
import kotlin.concurrent.thread

class ConcurrentSetPluginTests : PronghornTest() {
    /*
     * Tests the default concurrent set plugin functions properly.
     */
    @RepeatedTest(repeatCount)
    fun concurrentMapPlugin() {
        val map = ConcurrentSetPlugin.get<String>()

        val threadA = thread(start = false) {
            var x = 0
            while (x < 10000) {
                map.add("$x")
                x += 2
            }
        }

        val threadB = thread(start = false) {
            var x = 1
            while (x < 10000) {
                map.add("$x")
                x += 2
            }
        }

        threadA.start()
        threadB.start()
        threadA.join()
        threadB.join()

        assertEquals(10000, map.size)
    }
}
