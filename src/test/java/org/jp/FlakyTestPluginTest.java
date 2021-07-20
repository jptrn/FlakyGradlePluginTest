package org.jp;

import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ResolvedArtifact;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlakyTestPluginTest {
	@Test
	public void test() throws Exception {
		int iteration = 0;
		while (true) {
			System.out.println("Test iteration: " + ++iteration);
			final Project project = ProjectBuilder.builder()
					.withProjectDir(Files.createTempDirectory("flaky-").toFile())
					.withName("test")
					.build();
			project.getPlugins().apply(FlakyTestPlugin.class);
			project.getRepositories().add(project.getRepositories().mavenCentral());

			// Force the same dependency to appear twice
			final Configuration testConfiguration = project.getConfigurations().create("testConfiguration");
			project.getConfigurations().getByName("implementation").extendsFrom(testConfiguration);

			project.getDependencies().add("testConfiguration", "org.joda:joda-convert:2.1.1");
			project.getDependencies().add("implementation", "org.joda:joda-convert:2.1.1");

			final Set<ResolvedArtifact> resolved = project.getConfigurations()
					.getByName("runtimeClasspath")
					.getResolvedConfiguration()
					.getResolvedArtifacts();
			assertEquals(resolved.size(), 1);
		}
	}
}