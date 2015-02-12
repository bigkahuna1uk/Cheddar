/*
 * Copyright 2014 Click Travel Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.clicktravel.cheddar.infrastructure.messaging;

import java.util.List;

/**
 * A named queue of {@link Message} elements.
 * @param <T> message type accepted by this queue
 */
public interface MessageQueue<T extends Message> extends MessageSender<T> {

    /**
     * @return The queue name
     */
    String getName();

    /**
     * Receives any number of messages on the queue, but does not delete them. No order or priority of messages is
     * guaranteed.
     * @return List of received {@code Message}s
     * @throws InterruptedException
     */
    List<T> receive() throws InterruptedException;

    /**
     * Receives any number of messages up to the maximum specified, but does not delete them. This call will spend up to
     * the wait time given for a message to arrive in the queue before returning.
     * @param waitTimeSeconds The duration (in seconds) for which the call will wait for a message to arrive in the
     *            queue before returning. If a message is available, the call will return sooner.
     * @param maxMessages The maximum number of messages to return. Will never return more messages than this value but
     *            may return fewer. Values can be from 1 to 10.
     * @return
     */
    List<T> receive(int waitTimeSeconds, int maxMessages) throws InterruptedException;

    /**
     * Deletes a message previously received from this queue.
     * @param typedMessage {@code Message} to delete
     */
    void delete(T message);

}
