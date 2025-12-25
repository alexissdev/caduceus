package dev.alexissdev.caduceus.api.service;

/**
 * Represents a general service interface that can be started and stopped.
 * Implementations of this interface should define the specific behavior
 * for starting and stopping the service.
 * <p>
 * The {@code start} method is typically used to initialize and begin the
 * service's core functionality, while the {@code stop} method is used to
 * terminate the service and release resources.
 * <p>
 * The exact behavior and state transitions induced by these methods are
 * dependent on the implementing class and should be well-documented.
 */

public interface Service {

    /**
     * Initiates the service, transitioning it to the started state.
     * This method is intended to perform the necessary setup and operations
     * required to begin or resume the service's core functionality.
     * <p>
     * Implementing classes should define the specific actions
     * required to start the service. It is the caller's responsibility
     * to ensure that this method is invoked before attempting to use
     * the service.
     * <p>
     * Note that the behavior of calling this method on an already-started
     * service implementation may vary and should be documented by the
     * implementing class.
     */

    void start();

    /**
     * Stops the service, transitioning it to the stopped state.
     * This method is intended to perform the necessary cleanup and operations
     * required to halt the service's core functionality and release resources.
     * <p>
     * Implementing classes should provide the specific actions needed to
     * successfully stop the service. It is generally the caller's responsibility
     * to ensure that this method is invoked when the service is no longer required.
     * <p>
     * The behavior of calling this method on an already-stopped service or
     * without starting the service first may vary depending on the implementation
     * and should be documented by the implementing class.
     */

    default void stop() {}
}
