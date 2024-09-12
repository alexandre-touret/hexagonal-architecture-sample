package info.touret.hexagonal_architecture_sample;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = {"info.touret.hexagonal_architecture_sample.application",
                            "info.touret.hexagonal_architecture_sample.domain",
                            "info.touret.hexagonal_architecture_sample.infrastructure"},
                importOptions = DoNotIncludeTests.class)
public class ArchitectureTest {

    @ArchTest
    public static final ArchRule should_return_hexagonal_architecture_is_respected =
            onionArchitecture().domainModels("..domain.(**).model..")
                    .domainServices("..domain.(**).service..")
                    .applicationServices("..application..")
                    .adapter("persistence", "..infrastructure.database..");

}

