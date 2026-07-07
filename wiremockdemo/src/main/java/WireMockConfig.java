
import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Configuration
@Profile("dev") // 仅在 dev 环境激活
public class WireMockConfig {

    @Bean(destroyMethod = "stop") // Spring 会在容器关闭时调用 stop()
    public WireMockServer wireMockServer() {
        WireMockServer server = new WireMockServer(options().port(2345));
        server.start();
        return server;
    }

}
