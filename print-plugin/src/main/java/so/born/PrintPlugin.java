package so.born;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "print", defaultPhase = LifecyclePhase.COMPILE)
public class PrintPlugin extends AbstractMojo {

    @Parameter(property = "name", defaultValue = "name", readonly = false, required = false)
    private String name = "plugin";

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Hello from " + name);
    }
}
