package modules

import com.google.inject.AbstractModule
import security.SecurityConfig
import security.impl.SecurityConfigGoogle

/**
 * Guice DI module to be included in application.conf
 */
class SecurityModule extends AbstractModule {

  override def configure() = {
//    bind(classOf[SecurityConfig]).to(classOf[DummyBasicAuthSecurityConfig]).asEagerSingleton()
    bind(classOf[SecurityConfig]).to(classOf[SecurityConfigGoogle]).asEagerSingleton()
  }

}
