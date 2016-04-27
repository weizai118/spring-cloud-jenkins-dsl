package io.springframework.cloud.sonar

import io.springframework.cloud.common.ConsulTrait
import javaposse.jobdsl.dsl.DslFactory
/**
 * @author Marcin Grzejszczak
 */
class ConsulSonarBuildMaker extends SonarBuildMaker implements ConsulTrait {
	private  static final String ONCE_PER_DAY = "H H * * *"

	ConsulSonarBuildMaker(DslFactory dsl) {
		super(dsl)
	}

	void buildSonar() {
		super.buildSonar('spring-cloud-sonar')
	}

	@Override
	Closure defaultSteps() {
		return buildStep {
			shell postConsulShell()
		} << super.defaultSteps() <<  buildStep {
			shell preConsulShell()
		}
	}
}
