package win.Domaines.config;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;

@Component
public class DatabaseSeedRunner implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseSeedRunner.class);

    private final JdbcTemplate jdbcTemplate;
    private final ResourceLoader resourceLoader;

    @Value("${app.seed.enabled:true}")
    private boolean seedEnabled;

    @Value("${app.seed.table-check:actualites_categories}")
    private String tableToCheck;

    @Value("${app.seed.sql-path:file:./config/seed-data.sql}")
    private String seedSqlPath;

    public DatabaseSeedRunner(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (!seedEnabled) {
            log.info("Seed disabled (app.seed.enabled=false).");
            return;
        }

        if (!tableToCheck.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
            throw new IllegalArgumentException("Invalid app.seed.table-check value: " + tableToCheck);
        }

        Integer rowCount;
        try {
            rowCount = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM " + tableToCheck,
                    Integer.class
            );
        } catch (DataAccessException ex) {
            log.warn("Seed skipped: table '{}' is not accessible yet ({})", tableToCheck, ex.getMessage());
            return;
        }

        if (rowCount != null && rowCount > 0) {
            log.info("Seed skipped: table '{}' already contains {} row(s).", tableToCheck, rowCount);
            return;
        }

        String resolvedSeedPath = Objects.requireNonNull(seedSqlPath, "app.seed.sql-path must not be null");
        Resource seedScript = resourceLoader.getResource(resolvedSeedPath);
        if (!seedScript.exists()) {
            log.warn("Seed skipped: SQL script not found at '{}'.", resolvedSeedPath);
            return;
        }

        DataSource dataSource = jdbcTemplate.getDataSource();
        if (dataSource == null) {
            log.warn("Seed skipped: no DataSource available.");
            return;
        }

        log.info("Running DB seed script from '{}'.", resolvedSeedPath);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(seedScript);
        populator.setSeparator(";");
        populator.execute(dataSource);
        log.info("DB seed completed successfully.");
    }
}
