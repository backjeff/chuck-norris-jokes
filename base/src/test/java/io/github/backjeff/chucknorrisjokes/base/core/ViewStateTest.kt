package io.github.backjeff.chucknorrisjokes.base.core

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ViewStateTest {

    @Test
    fun `isSuccess MUST return true WHEN viewState status == SUCCESS`() {
        val subject = ViewState<Boolean>(
            ViewState.Status.SUCCESS
        )

        assertTrue(subject.isSuccess())
        assertFalse(subject.isError())
        assertFalse(subject.isLoading())
        assertFalse(subject.isNeutral())
    }

    @Test
    fun `isError MUST return true WHEN viewState status == ERROR`() {
        val subject = ViewState<Boolean>(
            ViewState.Status.ERROR
        )

        assertTrue(subject.isError())
        assertFalse(subject.isSuccess())
        assertFalse(subject.isLoading())
        assertFalse(subject.isNeutral())
    }

    @Test
    fun `isLoading MUST return true WHEN viewState status == LOADING`() {
        val subject = ViewState<Boolean>(
            ViewState.Status.LOADING
        )

        assertTrue(subject.isLoading())
        assertFalse(subject.isSuccess())
        assertFalse(subject.isError())
        assertFalse(subject.isNeutral())
    }

    @Test
    fun `isNeutral MUST return true WHEN viewState status == NEUTRAL`() {
        val subject = ViewState<Boolean>(
            ViewState.Status.NEUTRAL
        )

        assertTrue(subject.isNeutral())
        assertFalse(subject.isLoading())
        assertFalse(subject.isSuccess())
        assertFalse(subject.isError())
    }

}