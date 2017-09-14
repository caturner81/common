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

package tech.pronghorn.util

const val MAX_POW2 = 1 shl 30

fun roundToNextPowerOfTwo(value: Int): Int {
    if (value > MAX_POW2) {
        throw IllegalArgumentException("There is no larger power of 2 int for value:$value since it exceeds 2^31.")
    }
    if (value < 0) {
        throw IllegalArgumentException("Given value:$value. Expecting value >= 0.")
    }
    return 1 shl 32 - Integer.numberOfLeadingZeros(value - 1)
}

fun isPowerOfTwo(value: Int): Boolean = value == roundToNextPowerOfTwo(value)
