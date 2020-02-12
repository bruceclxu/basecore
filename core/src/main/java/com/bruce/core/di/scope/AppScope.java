package com.bruce.core.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author bruce
 * Dagger AppScope
 */
@Scope
@Retention(RUNTIME)
public @interface AppScope {
}