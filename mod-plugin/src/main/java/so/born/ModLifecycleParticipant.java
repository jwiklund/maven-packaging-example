package so.born;

import java.io.StringReader;

import org.apache.maven.AbstractMavenLifecycleParticipant;
import org.apache.maven.MavenExecutionException;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginExecution;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.codehaus.plexus.util.xml.Xpp3DomBuilder;

public class ModLifecycleParticipant extends AbstractMavenLifecycleParticipant {

    @Override
    public void afterProjectsRead(MavenSession session) throws MavenExecutionException {
        for (MavenProject project : session.getAllProjects()) {
            if (!"mod-jar".equals(project.getPackaging())) {
                continue;
            }
            for (Plugin plugin : project.getModel().getBuild().getPlugins()) {
                if (!"so.born:print-plugin".equals(plugin.getKey())) {
                    continue;
                }
                for (PluginExecution execution : plugin.getExecutions()) {                        
                    Xpp3Dom config = (Xpp3Dom) execution.getConfiguration();
                    if (config == null) {
                        config = new Xpp3Dom("configuration");
                    }
                    Xpp3Dom mod;
                    try {
                        mod = Xpp3DomBuilder.build(new StringReader("<configuration><name>MOD</name></configuration>"));
                    } catch (Exception e) {
                        throw new MavenExecutionException("Invalid default configuration", e);
                    }
                    config = Xpp3Dom.mergeXpp3Dom(config, mod);
                    execution.setConfiguration(config);
                }
            }
        }
    }
}
