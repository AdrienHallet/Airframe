package org.nala.airframe.core.store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nala.airframe.TestServiceOne;
import org.nala.airframe.TestServiceStatic;
import org.nala.airframe.TestServiceTwo;

/**
 * Unit tests of the {@link DependencyStoreTest}.
 */
class DependencyStoreTest {

    /**
     * We can create nested dependency.
     */
    @Test
    void shouldCreateNestedDependency() {
        Object created = DependencyStore.getStore().getOrLoad(TestServiceOne.class);
        Assertions.assertNotNull(created);
        Assertions.assertNotNull(((TestServiceOne) created).nested);
    }

    /**
     * We can create dependency.
     */
    @Test
    void shouldCreateDependency() {
        Object created = DependencyStore.getStore().getOrLoad(TestServiceTwo.class);
        Assertions.assertNotNull(created);
    }

    /**
     * Errors when something bad happens.
     */
    @Test
    void shouldErrorWhenNotInstantiable() {
        Assertions.assertThrows(RuntimeException.class, () ->
                DependencyStore.getStore().getOrLoad(TestServiceStatic.class));
    }
}