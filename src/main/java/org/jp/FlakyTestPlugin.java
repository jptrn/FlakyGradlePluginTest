package org.jp;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaLibraryPlugin;

public class FlakyTestPlugin implements Plugin<Project> {
	@Override
	public void apply(final Project target) {
		target.getPlugins().apply(JavaLibraryPlugin.class);
	}
}
