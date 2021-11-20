package org.nala.airframe;

import org.nala.airframe.annotation.Autowired;

/**
 * A dummy test service with a nested dependency.
 */
public class TestServiceOne {

    /**
     * The nested dependency.
     */
    public TestServiceTwo nested;

    /**
     * Dependency Injection Constructor.
     *
     * @param testService the {@link TestServiceTwo}
     */
    @Autowired
    public TestServiceOne(TestServiceTwo testService) {
        this.nested = testService;
    }
}
